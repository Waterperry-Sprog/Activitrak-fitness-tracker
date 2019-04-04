package backend;

import java.util.Vector;

import UI.main;
import UI.ui_login_pane;

import java.io.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataHandler {
	
	static Vector<Integer> time = new Vector<Integer>();
	static Vector<Integer> heartRate = new Vector<Integer>();
	
	public DataHandler() {
	}
	
	public static void addToDB(String username, String password) {
		database.Database.addToLoginTable(username,Operations.hashPassword(password));
	}
	
	public static void logWorkout(String username, int[] metrics) {
		database.Database.logUserWorkout(username, metrics);
	}
	
	/**
	 * this method parses HR/time data from a Suunto CSV file. Apple Health, 
	 * @param file the file to parse
	 */
	public void importDataFromFile(String fileName) {
		
		File data = new File(fileName);
		BufferedReader reader;
		String line = "";
		try {
			reader = new BufferedReader(new FileReader(data));
			//first line is useless
			reader.readLine();
			//read second line
			line = reader.readLine();
			
			int indexOfHrData = 0;
			int timeDataPair = 0;
			//work out which column the HR data is stored in (suunto records HR data 1 time per second so time can
			//be calculated by delta line number).
			String[] splitLine = line.split(",");
			for (int i = 0; i < splitLine.length; i++) {
				if(splitLine[i].equals("HeartRate")) {
					indexOfHrData = i;
				}
				if(splitLine[i].equals("Time")) {
					indexOfHrData = i;
				}
			}
			
			while( (line = reader.readLine()) != null ) {
				splitLine = line.split(",");
				int hr = Operations.toInt( splitLine[indexOfHrData] );
				if(hr!=0) {
					heartRate.add( (Integer) hr );
					time.add( (Integer) timeDataPair++ );
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean authenticateUserPasswordPair(String user, String password){
		String result = database.Database.getDataFromUsernameTable("PASSWORDHASH",user,"USERNAME");
		try {
			if(Operations.hashPassword(password).contentEquals(result)){
				return true;
			}
			else {
				return false;
			}
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	public static int[] getUserGoals(String username){
		try {
			return database.Database.getGoalsForUser(username);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public static int getGoalForUser(String goalName, String username) {
		try {
			int array[] = getUserGoals(username);
			switch (goalName) {
				case "steps": return array[0];
				case "calories": return array[1];
				case "water": return array[2];
				case "duration": return array[3];
				case "weight" : return array[4];
			}
		} catch (NullPointerException n) {
			//this might be thrown if the username does not exist in the database.
			System.out.println("User does not exist.");
		}
		return -1;
	}
	
	public static int getProgressForUser(String goalName, String username) {
		try {
			return sumColumn(goalName, "WORKOUTLOGS", username);
		} catch (NullPointerException e) {
			return -1;
		}
	}
	
	private static int sumColumn(String columnName, String tableName, String username) {
		return database.Database.sumColumn(columnName, tableName, username);
	}
	
	//driver method
	public static void main(String[] args) {
		/*
		DataHandler suunto = new DataHandler();
		String prefix = System.getProperty("user.dir");
		String filePath = prefix+"\\src\\backend\\"+""+"sample_data_suunto.csv";
		suunto.importDataFromFile(filePath);
		//suunto.printTables();
		
		//launch graphics window
		Graphics.display(time, heartRate);
		System.out.println("");
		*/
		
		Vector<Integer> inputList = new Vector<Integer>();
		String dataType = "";
		/*
		//generate random data
		System.out.println("Populating array.");
		dataType = "pseudo-random";
		for(int i = 0; i<10000000; i++) {
			inputList.add( (int)(Math.random()*10000000) );	//add 10 million values between 1 and 10 million.
		}*/
		
		//generate totally unsorted data
		/*
		dataType = "totally unsorted";
		int lastAddedNumber = 999999999;
		for(int i = 0; i<10000000;) {
			int numToAdd = (int) Math.random()*10000000;
			if(numToAdd <= lastAddedNumber) {
				inputList.add(numToAdd);
				lastAddedNumber = numToAdd;
				i++;
			}
		}
		*/
		
		//generate totally sorted data
		/*
		int i = 0;
		dataType = "totally sorted";
		while(i < 10000000) {
			inputList.add(i++);
		}
		*/
		
		
		System.out.println("Size of array is " + inputList.size() + " (should be 10 million).");
		System.out.println("Worst case 70 million swaps.");
		
		long startTime = System.nanoTime();
		System.out.println("Started to sort "+dataType+" data.");
		Operations.sort(inputList);
		long endTime = System.nanoTime();
		System.out.println("Finished sorting data.");
		System.out.println("Method execution took " + (endTime - startTime)/1000000000 + " seconds (rounded).");
		
		database.Database.createConnection();
	    UI.main.showUI();
		
	}
}