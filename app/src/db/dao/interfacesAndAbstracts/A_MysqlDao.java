/**
 * 
 */
package db.dao.interfacesAndAbstracts;

import java.sql.Connection;
import db.connection.ConnectionProvider;
import db.connection.Datasource;

/**
 * @author hadanahm
 * @param <I>
 *
 */
public abstract class A_MysqlDao<T> implements I_Dao<T> {
	protected final Connection connection = ConnectionProvider.getConnection(Datasource.RELATIONAL); 
	
	public static String getSelectOpt(String options, String optionName, String option) {
		String selectOpt = options.length() == 0 ? " where " : " and ";
		selectOpt += optionName+"='"+option+"'";
		return selectOpt;
	}
}
