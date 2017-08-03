#!/usr/bin/env bash


MYSQL_ROOT_PWD="password"
MYSQL_SPEEDCOLLAB_USER_NAME="sc_user"
MYSQL_SPEEDCOLLAB_USER_PASSWORD="password"


add-apt-repository ppa:webupd8team/java -y
apt-get update

# Installing Java 8..
apt-get install -y software-properties-common python-software-properties
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
apt-get install oracle-java8-installer -y
# Setting environment variables for Java 8.."
apt-get install -y oracle-java8-set-default

# Installing neo4j
wget -O - http://debian.neo4j.org/neotechnology.gpg.key | sudo apt-key add -
echo 'deb http://debian.neo4j.org/repo stable/' > /etc/apt/sources.list.d/neo4j.list
apt-get update
apt-get install -y neo4j
sed -i -e 's/#dbms.connectors.default_listen_address=0.0.0.0/dbms.connectors.default_listen_address=0.0.0.0/g' /etc/neo4j/neo4j.conf
sed -i -e 's/#dbms.connector.bolt.listen_address=:7687/dbms.connector.bolt.listen_address=:7687/g' /etc/neo4j/neo4j.conf
service neo4j restart

#Installing mysql-server
echo "mysql-server mysql-server/root_password password "$MYSQL_ROOT_PWD | debconf-set-selections
echo "mysql-server mysql-server/root_password_again password "$MYSQL_ROOT_PWD | debconf-set-selections
apt-get install -y mysql-server
# Allowing mysql-server to answer to other ip addresses not only localhost
sed -i -e 's/bind-address		= 127.0.0.1/# bind-address		= 127.0.0.1/g' /etc/mysql/mysql.conf.d/mysqld.cnf
# Creating our software profile on mysql-server and allowing it to connect from anywhere
mysql --user=root --password=password -e "CREATE USER '"$MYSQL_SPEEDCOLLAB_USER_NAME"'@'%' IDENTIFIED BY '"$MYSQL_SPEEDCOLLAB_USER_PASSWORD"'; GRANT ALL PRIVILEGES ON *.* TO '"$MYSQL_SPEEDCOLLAB_USER_NAME"'@'%' WITH GRANT OPTION;"
service mysql restart