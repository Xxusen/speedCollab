/**
 * 
 */
package services;

import java.sql.SQLException;
import java.util.List;

import beans.User;
import db.dao.implementations.UserDao;

/**
 * @author HADANAHM
 *
 */
public class UserService {
	public User authenticate(User user) throws SQLException {
		UserDao udao = new UserDao();
		List<User> lu = udao.findByCriteria(user);
		User rUser = null;
		if(lu.size() > 0) {
			rUser = lu.get(0);
		}
		return rUser;
	}
}
