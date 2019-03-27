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
        //createUserTable();

        insertData("USERS", "tazzaboy234", "Tom Bandurka", 19, 'M', 15000, 3000, 2000);
        selectData();

        shutdown();
    }

    // Table should be created - Call it "USERS"
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
            //stmt.execute(""/*//TODO INSERT DATA SYNTAX HERE*/);
            stmt.executeUpdate("INSERT INTO USERS " + "VALUES (" + user + "," + name +"," + age + "," + gender + "," + steps + "," + calorie + "," + water +")");
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
                String name = rs.getstring("Name");
                System.out.println(name + "\n");
            }
        }

        catch (SQLException e) {
            System.err.print("Unable to execute!");
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
