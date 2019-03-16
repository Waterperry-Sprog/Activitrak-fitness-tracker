import java.io.*;

public class ReadTextFile 
{
	public static void main(String[] args) 
    {
        try 
        {
        	System.out.println("Please enter a username: ");
    		String user;
    		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    		
    		try 
    		{
    			user = reader.readLine();
    		} 
    		
    		catch (IOException e) 
    		{
    			user = "Unknown";
    		}
        	
            FileReader freader = new FileReader(user+".txt");
            BufferedReader bufferedReader = new BufferedReader(freader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) 
            {
                System.out.println(line);
            }
            
            freader.close();
 
        } 
        
        catch (IOException e) 
        {
            System.err.println("File does not exist!");
            System.exit(0);
        }
    }
}
