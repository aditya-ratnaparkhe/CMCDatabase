
import java.sql.*;


/**
 * @author Aditya Ratnaparkhe
 * @version 1.0 7th April 2013
 * 
 */
public class DBConnector 
{
	
	/**
	 * holds the database url
	 */
	private static String dbUrl = "jdbc:postgresql://localhost:5432/CMC";
	
	/**
	 * holds the username
	 */
	private static String username;
	/**
	 * holds user password
	 */
	private static String password;
	/**
	 * holds the connection object for the database
	 */
	private static Connection connection;
	
	/**
	 *  holds the statement
	 */
	private static Statement statement;
	/**
	 * holds Resultset returned by the query 
	 */
	private static ResultSet rs;
	
	/**
	 * the prerapedstatement used for database 
	 */
	private static PreparedStatement queryStmt;
	
	/**
	 * @return databaseURL
	 */
	public static String getDbUrl() {
		return dbUrl;
	}
	/**
	 * @param dbUrl (The database url)
	 */
	public static void setDbUrl(String dbUrl) {
		DBConnector.dbUrl = dbUrl;
	}
	/**
	 * @return username of user
	 */
	public static String getUsername() {
		return username;
	}
	/**
	 * @param username (username of user)
	 */
	public static void setUsername(String username) {
		DBConnector.username = username;
	}
	/**
	 * @return password of the user
	 */
	public static String getPassword() {
		return password;
	}
	/**
	 * @param password (password of the user)
	 */
	public static void setPassword(String password) {
		DBConnector.password = password;
	}

	public DBConnector() {
	}
	
	/**
	 * This method connects to the database using the username and password
	 */
	public static void connectToDatabase()
	{
		try {
			Class.forName("org.postgresql.Driver");
			String url = dbUrl;
			connection = DriverManager.getConnection (url, username, password);
			statement = connection.createStatement();
			System.out.println("Connection to database successful !");
		} catch (Exception e) {
			System.out.println("Invalid Connection Parameters !");
			e.printStackTrace();
		}
	}
	
	/**
	 * @param queryString The query to be executed
	 * @return Resultset The resultset that is generated after executing the query
	 * Executes the query passed on the database
	 */
	public static ResultSet executeQuery()
	{
		try 
		{
			queryStmt = connection.prepareStatement("select * from \"Group Project\".user"); 
			rs = queryStmt.executeQuery();
			printResult(rs);
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error in executing query: ");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param rs Resultset to be printed
	 * This method prints data from the resultset passed to it
	 */
	public static void printResult(ResultSet rs)
	{
		String uid;
		String uname;
		String upass;
		System.out.println("ID\t\tName\t\tPassword");
		try {
			while (rs.next()) {  
				uid = rs.getString("u_id");
				uname = rs.getString("u_uname");
				upass  = rs.getString("u_pass");
				System.out.println(uid+"\t\t"+uname+"\t\t"+upass);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method closes the connection to the database
	 */
	public static void closeConnection()
	{
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
