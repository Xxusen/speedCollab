#!/usr/bin/env bash

### Settings
MYSQL_ROOT_PASSWORD="password"
MYSQL_SPEEDCOLLAB_USER_NAME="sc_user"
MYSQL_SPEEDCOLLAB_USER_PASSWORD="password"
SPEEDCOLLAB_DB_NAME="speedCollab_db"
NEO4J_INITIAL_USER_PASSWORD="password"
NEO4J_SPEEDCOLLAB_USER_NAME="sc_user"
NEO4J_SPEEDCOLLAB_USER_PASSWORD="password"

### Installing Java 8
add-apt-repository ppa:webupd8team/java -y
apt-get update
apt-get install -y software-properties-common python-software-properties
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
apt-get install oracle-java8-installer -y
#Setting environment variables for Java 8.."
apt-get install -y oracle-java8-set-default

### Installing neo4j
wget -O - http://debian.neo4j.org/neotechnology.gpg.key | sudo apt-key add -
echo 'deb http://debian.neo4j.org/repo stable/' > /etc/apt/sources.list.d/neo4j.list
apt-get update
apt-get install -y neo4j
#Making neo4j server accept connections coming from any ip
sed -i -e 's/#dbms.connectors.default_listen_address=0.0.0.0/dbms.connectors.default_listen_address=0.0.0.0/g' /etc/neo4j/neo4j.conf
sed -i -e 's/#dbms.connector.bolt.listen_address=:7687/dbms.connector.bolt.listen_address=:7687/g' /etc/neo4j/neo4j.conf
service neo4j start
#Making neo4j start with every boot
sudo systemctl enable neo4j
#Waiting for neo4j to be available
echo waiting for neo4j to be available...
SECONDS=0
END=60
while true; do
    [[ "200" = "$(curl --silent --write-out %{http_code} --output /dev/null http://localhost:7474)" ]] && echo "Neo4j is started and available" && break
    [[ "${SECONDS}" -ge "${END}" ]] && echo "Neo4j failed to start (after waiting for over $END seconds)" && exit 1
    sleep 1
done
#Changing inital user's password for more security.
curl -v -u neo4j:neo4j -H Content-Type:application/json -d '{"password":"'$NEO4J_INITIAL_USER_PASSWORD'"}'  http://localhost:7474/user/neo4j/password
#Creating the user profile that is going to be used by the POC on neo4j
curl -v -u neo4j:$NEO4J_INITIAL_USER_PASSWORD -H Content-Type:application/json -d '{"query": "CALL dbms.security.createUser($username,$password,false)", "params": {"username":"'$NEO4J_SPEEDCOLLAB_USER_NAME'", "password":"'$NEO4J_SPEEDCOLLAB_USER_PASSWORD'"}}' http://localhost:7474/db/data/cypher

### Installing mysql-server
echo "mysql-server mysql-server/root_password password $MYSQL_ROOT_PASSWORD" | debconf-set-selections
echo "mysql-server mysql-server/root_password_again password $MYSQL_ROOT_PASSWORD" | debconf-set-selections
apt-get install -y mysql-server
#Allowing mysql-server to answer to other ip addresses not only localhost (disabling address binding)
sed -i -e 's/bind-address		= 127.0.0.1/# bind-address		= 127.0.0.1/g' /etc/mysql/mysql.conf.d/mysqld.cnf
#Creating the POC's database
mysql --user=root --password="$MYSQL_ROOT_PASSWORD" -e "create database $SPEEDCOLLAB_DB_NAME;"
#Creating the user profile that is going to be used by the POC on mysql-server and enabling connections from anywhere with its credentials
mysql --user=root --password="$MYSQL_ROOT_PASSWORD" -e "create user '$MYSQL_SPEEDCOLLAB_USER_NAME'@'%' identified by '$MYSQL_SPEEDCOLLAB_USER_PASSWORD'; grant all privileges on $SPEEDCOLLAB_DB_NAME.* to '$MYSQL_SPEEDCOLLAB_USER_NAME';"
service mysql restart