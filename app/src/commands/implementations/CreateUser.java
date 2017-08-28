/**
 * 
 */
package commands.implementations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import commands.interfaces.ICommand;

/**
 * @author HADANAHM
 *
 */
public class CreateUser implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		return "createUser";
	}

}
