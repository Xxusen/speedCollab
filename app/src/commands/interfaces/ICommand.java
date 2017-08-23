/**
 * 
 */
package commands.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 * @author HADANAHM
 *
 */
public interface ICommand {
	public JSONObject execute(HttpServletRequest req, HttpServletResponse res);
}
