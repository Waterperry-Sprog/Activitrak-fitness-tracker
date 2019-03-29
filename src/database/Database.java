package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Database {
    private static String dbURL = "jdbc:derby:C:\\Users\\tband\\OneDrive\\Documents\\GitHub\\CSED-2\\src\\database\\newDerby;create=false";	//change this to the required path
    private static String tableName = "USERS";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public static void createConnection() {
        try {
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except) {
            except.printStackTrace();
        }
    }
    
    public static backend.Workout getDataFromWorkoutLog(String columnName, String searchQuery, String returnQuery) {
    	try {
    		Workout w = new Workout();
    		ResultSet rs = null;
			stmt = conn.createStatement();
	    	rs = stmt.executeQuery("SELECT " + returnQuery + " FROM WORKOUTLOG WHERE " + columnName + " = '" + searchQuery +"'");
	    	return w;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void addToLoginTable(String username, String passwordHash) {
    	try {
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO USERLOGININFO VALUES ('" + username + "','','" + passwordHash + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static String getDataFromUsernameTable(String columnName, String searchQuery, String searchColumn) {
    	try {
    		Statement stt;
    		stt = conn.createStatement();
	    	ResultSet rs = stt.executeQuery("SELECT " + columnName + " FROM USERLOGININFO WHERE " + searchColumn + " = '" + searchQuery +"'");
	    	if (rs.next()) {
	    		String returnString = rs.getString(1);
	    	    return returnString;
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    private static void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept) {
            
        }

    }
}
