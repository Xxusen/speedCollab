/**
 * 
 */
package beans;

/**
 * @author HADANAHM
 *
 */
public class User {
	Integer id;
	String name;
	String firstName;
	String email;
	String password;
	
	//constructors
	public User() {}
	
	public User(String email, String password) {
		this.email 		= email;
		this.password 	= password; 
	}
	
	public User(int id, String name, String firstName, String email) {
		this.id			= id;
		this.name		= name;
		this.firstName	= firstName;
		this.email		= email;
	}
	
	public User(String name, String firstName, String email, String password) {
		this.name		= name;
		this.firstName	= firstName;
		this.email		= email;
		this.password	= password;
	}
	
	//Getters
	public Integer getId() { return id;	}
	public String getName() { return name; }
	public String getFirstName() { return firstName; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	
	//Setters
	public void setId(Integer id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }

}
