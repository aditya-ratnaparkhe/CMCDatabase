import java.util.Scanner;


/**
 * @author Aditya Ratnaparkhe
 * @version 1.0 7th April 2013
 */
public class UserInteractor {


	/**
	 * Scanner instance to accept user input
	 */
	Scanner user_input;

	/**
	 * Constructor to initialise class instance
	 */
	public UserInteractor()
	{
		//dbconnect = new DBConnector();
		user_input = new Scanner( System.in );
	}
	/**
	 * @param args arguments to main function.
	 * 
	 * Displays a menu to the user with a list of possible user actions
	 */
	public static void main(String[] args) {

		UserInteractor usr= new UserInteractor(); 
		usr.initParams();
		DBConnector.executeQuery();
		DBConnector.closeConnection();
		
	}

	/**
	 * Initialises the connection parameters
	 */
	public void initParams()
	{
		System.out.println("Enter username:\n");
		String username = user_input.next();
		System.out.println("Enter password:\n");
		String password = user_input.next();
		DBConnector.setUsername(username);
		DBConnector.setPassword(password);
		DBConnector.connectToDatabase();
	}

}
