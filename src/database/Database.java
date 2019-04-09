package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
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

    public static void executeCommand(String sql) {
    	try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println("next");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * @author tb791
     * This method adds a username/password hash pair to the logins database.
     * @param username the username of the new entry
     * @param passwordHash the hashed password of the user (not salted).
     */
    public static void addToLoginTable(String username, String passwordHash) {
    	try {
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO USERLOGININFO VALUES ('" + username + "','','" + passwordHash + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * @author tb791
     * This method finds a user's information from the login table
     * @param columnName could be password hash, PUK, etc.
     * @param searchQuery the value by which to identify a user.
     * @param searchColumn the column to be searched (normally user).
     * @return the string from the target column specified by columnName.
     */
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
    
    /**
     * @author tb791
     * This method gets all user goals and returns them as an int array.
     * @param username the user whose goals are to be retrieved.
     * @return an int[] of all the user's goals.
     */
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
    
    /**
     * @author tb791
     * This method takes a username and an int array of workout metrics, and logs them in the workout table.
     * @param username the user whose workout is being logged.
     * @param metrics an int[] of metrics in the order: steps, calories, water, duration, weight.
     */
    public static void logUserWorkout(String username, int[] metrics) {
    	try {
    		stmt = conn.createStatement();
    		stmt.executeUpdate("INSERT INTO WORKOUTLOGS VALUES ('"+username+"', "+metrics[0]+", "+metrics[1]+", "+metrics[2]+", "+metrics[3]+", "+metrics[4]+")");
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * @author tb791
     * @param columnName the column in which the entries to be summed exist.
     * @param tableName the table which contains the column being summed.
     * @param username the user whose column sum is needed.
     * @return an int sum of all the entries in the table:column belonging to the user
     */
    public static int sumColumn(String columnName, String tableName, String username) {
    	try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUM("+columnName+") FROM "+tableName+" WHERE USERNAME = '"+username+"'");
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch 
		} 
    	return 0;
    }
    
    /**
     * @author tb791
     * Removes an old user goal if exists, and adds the new one.
     * @param username the user who is adding a goal.
     * @param goalIndex the index of the goal to be added (see backend method setUserGoal for how this is calculated).
     * @param goalValue the value of the goal being set.
     */
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

    /**
     * @author tb791
     * This method retrieves friends' names for a given user.
     * @param username The username to get friends for
     * @return a string array of friend names
     */
    public static String[] getFriendsForUser(String username){
    	Vector<String> returnMe = new Vector<String>();
    	try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT FRIEND FROM FRIENDCONNECTIONS WHERE USERNAME = '"+username+"'");
			int i = 1;
			String dummy;
			while (rs.next()) {
				dummy = rs.getString(i);
				returnMe.add(dummy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String[] s = {"","","","","","","","","","",""};
    	int x = 0;
    	if(returnMe.size()==0) {
    		return s;
    	}
    	for(String str : returnMe) {
    		s[x] = str;
    		x++;
    	}
    	return s;
    }
    
    public static boolean doesUserExist(String username) {
    	try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT USERNAME FROM USERLOGININFO WHERE USERNAME = '"+username+"'");
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
    public static void addFriendForUser(String username, String friend) {
    	try {
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO FRIENDCONNECTIONS VALUES ('"+username+"','"+friend+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static void removeFriendForUser(String username, String friend){
    	try {
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM FRIENDCONNECTIONS WHERE USERNAME = '"+username+"' AND FRIEND = '"+friend+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static int[][] getWorkoutsForUser(String username){
    	int[][] returnArray = new int[30][5];
    	try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM WORKOUTLOGS WHERE USERNAME = '"+username+"'");
			int y = 0;
			while(rs.next()) {
				for(int i = 2; i<6; i++) {
					returnArray[y][i-2] = rs.getInt(i);
				}
				y++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return returnArray;
    }
    
    /**
     * @author awpk21
     * This method closes the connection to the database (only used on a clean exit from the application).
     */
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
