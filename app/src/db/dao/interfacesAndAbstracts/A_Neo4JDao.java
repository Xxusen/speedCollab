/**
 * 
 */
package db.dao.interfacesAndAbstracts;

import java.sql.Connection;
import db.connection.ConnectionProvider;
import db.connection.Datasource;

/**
 * @author hadanahm
 *
 */
public abstract class A_Neo4JDao<T> implements I_Dao<T> {
	protected final Connection connection = ConnectionProvider.getConnection(Datasource.GRAPH);
}
