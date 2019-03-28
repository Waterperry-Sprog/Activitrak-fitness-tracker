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
    
    
    private static void insertUserData(String tableName, String user, String name, int age, char gender, int steps, int calories, int water) {
        try {
            stmt = conn.createStatement();
            //stmt.execute(""/*//TODO INSERT DATA SYNTAX HERE*/);
            stmt.executeUpdate("INSERT INTO " + tableName + " VALUES (" + user + "," + name +"," + age + "," + gender + "," + steps + "," + calories + "," + water +")");
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
    
    private static void selectData()
    {
        try {
            //Example
            ResultSet rs = stmt.executeQuery("SELECT Name FROM USERS WHERE userName = 'tazzaboy234'");

            while (rs.next()) {
                String name = rs.getString("Name");
                System.out.println(name + "\n");
            }
        }

        catch (SQLException e) {
            System.err.print("Unable to execute!");
        }
    }
    
    protected static void getDataFromWorkoutLog(String columnName, String searchQuery, String returnQuery) {
    	try {
    		ResultSet rs = null;
			stmt = conn.createStatement();
	    	rs = stmt.executeQuery("SELECT " + returnQuery + " FROM WORKOUTLOG WHERE " + columnName + " = '" + searchQuery +"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static String getDataFromUsernameTable(String columnName, String searchQuery, String searchColumn) {
    	try {
    		Statement stt;
    		stt = conn.createStatement();
    		System.out.println("query made.");
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
