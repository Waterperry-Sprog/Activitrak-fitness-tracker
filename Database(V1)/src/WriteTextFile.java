import java.io.*;

public class WriteTextFile 
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
    			//Need to compare file names already existing
    		} 
    		
    		catch (IOException e) 
    		{
    			user = "Unknown";
    		}

            System.out.println("Please enter your data;");
            
            System.out.println("Name:");
            String name = reader.readLine();
            
            System.out.println("Age:");
            String age = reader.readLine();
            
            int c_age = Integer.parseInt(age);
            
            
            while(c_age <= 13)
            {
            	System.err.print("You are too young to use the app.");
            	age = "";
            	System.out.println("Age:");
                age = reader.readLine();
                c_age = Integer.parseInt(age);
            }
            
            System.out.println("Gender (M/F):");
            String gender = reader.readLine();
            /*
            while(!(gender.equals("M")) || !(gender.equals("F")))
            {
            	System.err.print("Invalid gender");
            	gender = "";
            	System.out.println("Gender (M/F):");
                gender = reader.readLine();
            }
            */
            System.out.println("Steps taken:");
            String steps = reader.readLine();
            
            int c_steps = Integer.parseInt(steps);
            
            
            while(c_steps < 0)
            {
            	System.err.print("Invalid step count.");
            	steps = "";
            	System.out.println("Steps taken:");
                steps = reader.readLine();
                c_steps = Integer.parseInt(steps);
            }
            
            System.out.println("Calories (kcal):");
            String calories = reader.readLine();
            int c_calories = Integer.parseInt(calories);
           
            
            while(c_calories <= 0)
            {
            	System.err.print("Invalid calorie input");
            	calories = "";
            	System.out.println("Calories (kcal):");
                calories = reader.readLine();
                c_calories = Integer.parseInt(calories);
            }
            
            System.out.println("Water (litres):");
            String water = reader.readLine();
            int c_water = Integer.parseInt(water);
             
            while(c_water < 0)
            {
            	System.err.print("Invalid input");
            	water = "";
            	System.out.println("Water (litres):");
                water = reader.readLine();
                c_water = Integer.parseInt(age);
            }
            
            System.out.println("Confirm Data:");
            String res = reader.readLine();

            updateData(user, name, age, gender, steps, calories, water, res);
            
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
    }
    
    public static void writeData(String user, String name,String age,String gender,String steps,String calories,String water) throws IOException
    {
    	FileWriter writer = new FileWriter(user + ".txt", true);
    	BufferedWriter bufferedWriter = new BufferedWriter(writer);
    	
    	bufferedWriter.write("Username: " + user);
    	bufferedWriter.newLine();
    	bufferedWriter.write("Name: " + name);
    	bufferedWriter.newLine();
        bufferedWriter.write("Age: " + age + "yrs");
        bufferedWriter.newLine();
        bufferedWriter.write("Gender: " + gender);
        bufferedWriter.newLine();
        bufferedWriter.write("Steps taken: " + steps);
        bufferedWriter.newLine();
        bufferedWriter.write("Calories: " + calories + "kcal");
        bufferedWriter.newLine();
        bufferedWriter.write("Water: " + water + "litres");
        bufferedWriter.newLine();
        
        System.out.println("Data written");
    
        bufferedWriter.close();
    }
    
    public static void updateData(String user, String name, String age, String gender, String steps, String calories, String water, String res) throws IOException
    {
    	
    	if(res.equals("Y"))
        {
        	writeData(user, name, age, gender, steps, calories, water);
        	System.out.println("Data submitted");
            return;
        }

        else 
        {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        	
        	while(!(res.equals("Y")))
        	{
        		System.out.println("What do you want to change?:");
            	String change = reader.readLine();

            	switch(change)
            	{
            		case "user":
            		{
            			System.out.println("Username:");
            			user = reader.readLine();
            			break;
            		}
            		
            		case "name":
            		{
            			System.out.println("Name:");
                        name = reader.readLine();
            			break;
            		}
            		
            		case "age":
            		{
            			System.out.println("Age:");
                        age = reader.readLine();
        	            break;
            		}
            		
            		case "gender":
            		{
            			System.out.println("Gender:");
                        gender = reader.readLine();
        	            break;
            		}
            		
            		case "steps":
            		{
            			System.out.println("Steps:");
                        steps = reader.readLine();
        	            break;
            		}
            		
            		case "calorie":
            		{
            			System.out.println("Calorie:");
                        calories = reader.readLine();
        	            break;
            		}
            		
            		case "water":
            		{
            			System.out.println("Water:");
                        water = reader.readLine();
        	            break;
            		}

            	}
            	
            	System.out.println("Confirm Data:");
                res = reader.readLine();

                updateData(user, name, age, gender, steps, calories, water, res);
        	}

        }
        
        System.out.println("Data submitted");
        
    }
}
