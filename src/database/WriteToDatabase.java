package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class WriteToDatabase {
    private static String dbURL = "jdbc:derby:C:\\Users\\tband\\OneDrive\\Documents\\GitHub\\CSED-2\\src\\database\\newDerby;create=false";	//change this to the required path
    private static String tableName = "USERS";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args) {
        createConnection();
        createUserTable();
//        insertData("USERS", "tazzaboy234", "Tom Bandurka", 19, 'M', 15000, 3000, 2000);
//        selectData();
        shutdown();
    }
    
    private static void createUserTable() {
    	try { 
    		stmt = conn.createStatement();	//this code creates a table with the data originally stored in a text file in v1.
    		stmt.execute(""/*//TODO CREATE TABLE SYNTAX HERE*/);
    	
    	} catch (SQLException se) {
    		se.printStackTrace();
    	}
    }
    
    private static void createConnection() {
        try {
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except) {
            except.printStackTrace();
        }
    }
    
    private static void insertData(String tableName, String user, String name, int age, char gender, int steps, int calories, int water) {
        try {
            stmt = conn.createStatement();
            stmt.execute(""/*//TODO INSERT DATA SYNTAX HERE*/);
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
    
    private static void selectData() throws SQLException { 		//TODO reimplement this with a try/catch rather than a throws.
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