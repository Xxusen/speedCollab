/**
 * 
 */
package db.dao.implementations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import db.dao.interfacesAndAbstracts.A_MysqlDao;

/**
 * @author hadanahm
 *
 */
public class UserDao extends A_MysqlDao {

	/* (non-Javadoc)
	 * @see db.dao.interfacesAndAbstracts.I_Dao#findByID(long)
	 */
	@Override
	public User findByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see db.dao.interfacesAndAbstracts.I_Dao#findByCriteria(java.lang.Object)
	 */
	@Override
	public List<User> findByCriteria(Object bean) throws SQLException {
		User cHolder			= (User) bean;
		List<User> retrieved 	= new ArrayList<User>();
		String query			= "select id, name, firstname, email from user";
		String options			= "";
		
		options	=	cHolder.getName() == null ? "" : UserDao.getSelectOpt(options, "name", cHolder.getName());
		options	+= 	cHolder.getFirstName() == null ? "" : UserDao.getSelectOpt(options, "firstname", cHolder.getFirstName());
		options	+= 	cHolder.getEmail() == null ? "" : UserDao.getSelectOpt(options, "email", cHolder.getEmail());
		options	+= 	cHolder.getPassword() == null ? "" : UserDao.getSelectOpt(options, "password", cHolder.getPassword());
		
		if(options.length() > 0) {
			query += options;
			System.out.println(query);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				User temp = new User();
				temp.setId(rs.getLong(1));
				temp.setName(rs.getString(2));
				temp.setFirstName(rs.getString(3));
				temp.setEmail(rs.getString(4));
				retrieved.add(temp);
			}
		}
		
		return retrieved;
	}

	/* (non-Javadoc)
	 * @see db.dao.interfacesAndAbstracts.I_Dao#findAll()
	 */
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see db.dao.interfacesAndAbstracts.I_Dao#create(java.lang.Object)
	 */
	@Override
	public User create(Object bean) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see db.dao.interfacesAndAbstracts.I_Dao#update(java.lang.Object)
	 */
	@Override
	public User update(Object bean) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see db.dao.interfacesAndAbstracts.I_Dao#delete(java.lang.Object)
	 */
	@Override
	public int delete(Object bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see db.dao.interfacesAndAbstracts.I_Dao#delete(long)
	 */
	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
