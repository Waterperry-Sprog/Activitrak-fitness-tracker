import java.sql.*;

public class WriteToDatabase 
{
   static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";  
   static final String DB_URL = "/myfiles/awpk21/dos/ActiviTrakDB.accdb.mdb";

   public static void main(String[] args) 
   {
	   Connection conn = null;
	   Statement stmt = null;
   
	   try
	   {
	      Class.forName(JDBC_DRIVER);
	
	      System.out.println("Connecting to a selected database...");
	      
	      conn = DriverManager.getConnection(DB_URL);
	      
	      System.out.println("Connected database successfully...");
	      
	      stmt = conn.createStatement();
	      
	      String sql = "INSERT INTO Registration " + "VALUES ('Hazza', 'Harry', 21, M, 15012, 4005, 6)";
	      stmt.executeUpdate(sql);
	      
	      System.out.println("Inserted record into the table...");
	
	   }
	   
	   catch(SQLException se)
	   {
	      System.out.print("SQL Fault!\n");
	   }
	   
	   catch(Exception e)
	   {
	      System.out.print("Exception Fault!\n");
	   }
	   
	   System.out.println("Goodbye!");
   
   }
}