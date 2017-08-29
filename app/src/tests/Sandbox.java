package tests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.connections.DBConnection;

public class Sandbox extends HttpServlet{
	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) {
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			String result = "";
			while(rs.next()) {
				result += "\n"+rs.getInt("iduser");
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
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		doProcess(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		doProcess(req, res);
	}
}
