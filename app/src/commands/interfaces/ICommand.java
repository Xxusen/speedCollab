/**
 * 
 */
package commands.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import beans.User;

/**
 * @author HADANAHM
 *
 */
public interface ICommand {
	public String execute(HttpServletRequest req, HttpServletResponse res);
}
