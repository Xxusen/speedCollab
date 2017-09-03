package tests;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import db.connection.ConnectionProvider;
import db.connection.Datasource;
import db.dao.implementations.UserDao;

public class Sandbox extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5463828589586814320L;

	public void doProcess(HttpServletRequest req, HttpServletResponse res) { 
		// test with mysql server
		try {
			User user = new User("admin@speedCollab", "password");
			User retrieved = null;
			UserDao udao = new UserDao();
			List<User> lu = udao.findByCriteria(user);
			Iterator<User> it = lu.iterator();
			while(it.hasNext()) {
				retrieved = it.next();
			}
			System.out.println("Nous avons récupéré "+lu.size()+" users, et le dernier s'appelle "+lu.get(lu.size()-1).getName());
		
			Connection con = ConnectionProvider.getConnection(Datasource.RELATIONAL);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			String result = "";
			while (rs.next()) {
				result += "\n" + rs.getInt("id");
				result += rs.getString("name");
				result += rs.getString("firstname");
				result += rs.getString("email");
			}

			res.getWriter().print(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Connection connection = ConnectionProvider.getConnection(Datasource.GRAPH);
			String query = "MATCH (n:Movie) RETURN n";
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				HashMap a = (HashMap) rs.getObject("n");
				System.out.println(i++);
				System.out.println(a.get("title"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		doProcess(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		doProcess(req, res);
	}
}
