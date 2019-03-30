package backend;

import java.util.Vector;

import UI.ui_login_pane;

import java.io.*;

public class DataHandler {
	
	static Vector<Integer> time = new Vector<Integer>();
	static Vector<Integer> heartRate = new Vector<Integer>();
	
	public DataHandler() {
	}
	
	public static void addToDB(String username, String password) {
		database.Database.addToLoginTable(username,Operations.hashPassword(password));
	}
	
	private void printTables() {
		for (int i = 0; i < time.size(); i++) {
			System.out.println("Time:\t" + time.get(i).toString() +"\tHR:\t"+ heartRate.get(i).toString());
		}
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
