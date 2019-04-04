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
        catch (Exception e) {
        	System.out.println("ERROR [FATAL]: Application could not run at this time. Exiting...");
            System.exit(1);
        }
    }
    
    /*
    public static backend.Workout getWorkoutObject(String searchQuery, String returnQuery) {
    	try {
    		ResultSet rs = null;
			stmt = conn.createStatement();
	    	rs = stmt.executeQuery("SELECT * FROM WORKOUTLOG WHERE USERNAME = '" + searchQuery +"'");
	    	System.out.println(rs.getArray(0) + rs.getArray(1 + rs.getArray(2) + rs.getArray(3) + rs.getArray(4) + rs.getArray(5)));
	    	//backend.Workout w = new backend.Workout();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return void;
    }*/
    
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
    		stmt = conn.createStatement();
	    	ResultSet rs = stmt.executeQuery("SELECT " + columnName + " FROM USERLOGININFO WHERE " + searchColumn + " = '" + searchQuery +"'");
	    	if (rs.next()) {
	    		String returnString = rs.getString(1);
	    	    return returnString;
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static int[] getGoalsForUser(String username){
    	try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERGOALS WHERE USERNAME = '"+username+"'");
			int[] returnMe;
			if(rs.next()) {
				returnMe = new int[] {rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getInt(6)};
				return returnMe;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static void logUserWorkout(String username, int[] metrics) {
    	try {
    		stmt = conn.createStatement();
    		stmt.executeUpdate("INSERT INTO WORKOUTLOGS VALUES ('"+username+"', "+metrics[0]+", "+metrics[1]+", "+metrics[2]+", "+metrics[3]+")");
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static int sumColumn(String columnName, String tableName, String username) {
    	try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUM("+columnName+") FROM "+tableName+" WHERE USERNAME = '"+username+"'");
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return -1;
    }
    
    public static void addGoal(String username, int goalIndex, int goalValue) {
    	int[] insertArray = {0,0,0,0,0};
    	insertArray[goalIndex] = goalValue;
    	try {
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM USERGOALS WHERE USERNAME = '"+username+"'");
			stmt.executeUpdate("INSERT INTO USERGOALS VALUES ('"+username+"', "+insertArray[0]+", "+insertArray[1]+", "+insertArray[2]+", "+insertArray[3]+", "+insertArray[4]+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
