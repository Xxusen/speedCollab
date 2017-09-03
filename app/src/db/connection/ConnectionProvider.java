/**
 * 
 */
package db.connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author HADANAHM
 *
 */
public class ConnectionProvider {

	
	// making the default constructor private so this class cannot be instantiated
	private ConnectionProvider() {}
	
	
	/**
	* retrieves a connection for the specified data source
	* @param 	datasource : item of Datasource enum
	* @return	a connection from the specified datasource
	* @throws	
	*/
	public static Connection getConnection(Datasource datasource) {
		Connection connection = null;
		try {
				Context context = new InitialContext();
				DataSource ds 	= (DataSource) context.lookup("java:comp/env/"+datasource.getValue());
				connection		= ds.getConnection();
		} catch (NamingException | SQLException e) {
				e.printStackTrace();
		}
		return connection;
	}
}
