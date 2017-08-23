/**
 * 
 */
package controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServlet;

import commands.interfaces.ICommand;

/**
 * @author HADANAHM
 *
 */
public class FrontServlet extends HttpServlet {
	HashMap<String, ICommand> commands;
	
	public void init() {
		commands = new HashMap<String, ICommand>();
	}

}
