package backend;

import java.util.Vector;
import java.io.*;

public class DataHandler {
	
	static Vector<Integer> time = new Vector<Integer>();
	static Vector<Integer> heartRate = new Vector<Integer>();
	
	/**
	 * Default constructor (never used).
	 */
	public DataHandler() {
	}
	
	/**
	 * @author tb791
	 * This method adds a login entry to the userlogininfo table of the database.
	 * @param username the username to be logged.
	 * @param password the password as plain text to be logged.
	 */
	public static void addToDB(String username, String password) {
		database.Database.createConnection();
		database.Database.addToLoginTable(username,Operations.hashPassword(password));
	}
	
	/**
	 * @author tb791
	 * This method logs a workout using an int array and a username.
	 * @param username the user whose workout is being logged.
	 * @param metrics an int[] of metrics in the order: steps, calories, water, duration, weight.
	 */
	public static void logWorkout(String username, int[] metrics) {
		database.Database.createConnection();
		database.Database.logUserWorkout(username, metrics);
	}
	
	/**
	 * @author tb791
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
	
	/**
	 * @author tb791
	 * This method authenticates a username and password pair by checking the userlogininfo database.
	 * @param user the username to be checked.
	 * @param password the plaintext password to be checked.
	 * @return true if the pair exist in the database, false otherwise.
	 */
	public static boolean authenticateUserPasswordPair(String user, String password){
		database.Database.createConnection();
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
	
	/**
	 * @author tb791
	 * This method gets an int array of the goals for a certain user. This method is really for internal use only.
	 * @param username the user who set the goals being retrieved.
	 * @return an int array by goals in the order steps, calories, water, duration, weight (only one entry should really be non-zero).
	 */
	private static int[] getUserGoals(String username){
		database.Database.createConnection();
		try {
			return database.Database.getGoalsForUser(username);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * @author tb791
	 * This method gets a specific goal for a username rather than an int array like getUserGoals returns.
	 * @param goalName the name of the goal (either steps, calories, water, duration, or weight).
	 * @param username the user who set the goal.
	 * @return the goal value as an int.
	 */
	public static int getGoalForUser(String goalName, String username) {
		database.Database.createConnection();
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
		return 0;
	}
	
	/**
	 * @author tb791
	 * This method gets a user's progress towards the set/specified goal.
	 * @param goalName the name of the goal which the user is progressing towards.
	 * @param username the username of the user whose goal is being retrieved.
	 * @return the user's current progress as an integer.
	 */
	public static int getProgressForUser(String goalName, String username) {
		database.Database.createConnection();
		try {
			return sumColumn(goalName, "WORKOUTLOGS", username);
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
	/**
	 * @author tb791
	 * This method is for internal use only. See database.Database.sumColumn for more information.
	 * @param columnName See database.Database.sumColumn for more information.
	 * @param tableName See database.Database.sumColumn for more information.
	 * @param username See database.Database.sumColumn for more information.
	 * @return See database.Database.sumColumn for more information.
	 */
	private static int sumColumn(String columnName, String tableName, String username) {
		database.Database.createConnection();
		return database.Database.sumColumn(columnName, tableName, username);
	}
	
	/**
	 * @author tb791
	 * This method is for internal use only. See database.Database.addGoal for more information.
	 * @param username See database.Database.addGoal for more information.
	 * @param goalName See database.Database.addGoal for more information.
	 * @param goal See database.Database.addGoal for more information.
	 */
	public static void setUserGoal(String username, String goalName, String goal) {
		database.Database.createConnection();
		int goalValue = Operations.toInt(goal);
		int goalIndex = -1;
    	switch(goalName) {	//goal label from UI is different to simple text, so that is sorted here rather than in the db code.
    		case "Steps:" : goalIndex = 0;
    			break;
    		case "Calories (kCal):" : goalIndex = 1;
    			break;
    		case "Water (ml):" : goalIndex = 2;
    			break;
    		case "Exercise Duration (Mins):" :  goalIndex = 3;
    			break;
    		case "Weight (kg):" :  goalIndex = 4;
    			break;
    	}
		database.Database.addGoal(username, goalIndex, goalValue);
		
	}
	
	/**
	 * This method is for internal use only. See database.Database.getFriendsForUser for more information.
	 * @param username the username to get friends for.
	 * @return a string array of the user's friends (max 5).
	 */
	public static String[] getFriendsForUser(String username){
		database.Database.createConnection();
		try {
			String[] sa = database.Database.getFriendsForUser(username);
			return sa;
		} catch (ArrayIndexOutOfBoundsException e) {
			String[] s = {};
			return s;
		}

	}
	
	public static boolean addFriendForUser(String username, String friend) {
		database.Database.createConnection();
		String[] friends = database.Database.getFriendsForUser(username);
		for(String s : friends) {
			if(s.equals(friend)) {
				return true;
			}
		}
		if(database.Database.doesUserExist(friend)) {
			database.Database.addFriendForUser(username, friend);
			return true;
		}
		return false;
	}
	
	public static void unfriend(String username, String friend) {
		database.Database.createConnection();
		database.Database.removeFriendForUser(username, friend);
	}

	public static int getNumberOfFriends(String username) {
		int returnValue = 0;
		String[] friends = getFriendsForUser(username);
		if(friends.length==0) {
			return 0;
		}
		for(String s : friends) {
			if(doesUserExist(s) && areFriends(s, username)) {
				returnValue++;
			}
		}
		return returnValue;
	}
	
	public static boolean doesUserExist(String username) {
		database.Database.createConnection();
		return database.Database.doesUserExist(username);
	}
	
	public static boolean areFriends(String user1, String user2){
		database.Database.createConnection();
		String[] friends1 = database.Database.getFriendsForUser(user1);
		String[] friends2 = database.Database.getFriendsForUser(user2);
		boolean one = false;
		boolean two = false;
		for(String s : friends1) {
			if(s.equalsIgnoreCase(user2)) {
				one = true;
				break;
			}
		}
		for(String s : friends2) {
			if(s.equalsIgnoreCase(user1)) {
				two = true;
				break;
			}
		}
		if(one&&two) {
			return true;
		}
		return false;
	}
	
	public static boolean hasUserSetGoal(String username) {
		int[] goals = getUserGoals(username);
		for(int i : goals) {
			if(i!=0) {
				return true;
			}
		}
		return false;
	}
	
	public static int[][] getWorkoutsForUser(String username){
		database.Database.createConnection();
		int[][] returnArray = database.Database.getWorkoutsForUser(username);
		return returnArray;
	}
	
	public static int[] getMetricFromWorkoutHistory(String username, String metric) {
		int arrayIndex = -1;
		switch (metric) {
			case "Steps" : arrayIndex = 0;
				break;
			case "Calories" : arrayIndex = 1;
				break;
			case "Water" : arrayIndex = 2;
				break;
			case "Exercise Duration" :  arrayIndex = 3;
				break;
			case "Weight" : arrayIndex = 4;
				break;
		}
		int[][] dummy = getWorkoutsForUser(username);
		int[] returnArray = new int[30];
		for(int i = 0; i<30;i++) {
			returnArray[i] = dummy[i][arrayIndex];
		}
		return returnArray;
	}
	
	public static String findUserGoal(String username) {
		int[] goals = getUserGoals(username);
		int goalIndex = -1;
		try {
		for(int i = 0; i < goals.length; i++) {
			if(goals[i]!=0) {
				goalIndex = i;
			}
		}
		} catch(NullPointerException e) {
		}
		
		switch(goalIndex) {
			case 0 : return "steps";
			case 1 : return "calories";
			case 2 : return "water";
			case 3 : return "duration";
		}
		return "";
	}
	
	public static Vector<UserData> getTopThreeFriendsForUser(String username) {
		Vector<UserData> dataToSort = new Vector<UserData>();
		String[] friends = getFriendsForUser(username);
		if(getNumberOfFriends(username)==0) {
			return dataToSort;
		}
		for (int i = 0; i < friends.length; i++) {
			if(!friends[i].equals("")) {
				String userGoalName = findUserGoal(friends[i]);
				int goalProgress = (int) ((double) getProgressForUser(userGoalName, friends[i])/((double) getGoalForUser(userGoalName, friends[i])) * 100);
				dataToSort.add(new UserData(friends[i], goalProgress));
				dataToSort = Operations.sort(dataToSort);
			}
		}
		
		Vector<UserData> returnVector = new Vector<UserData>();
		try {
			returnVector.add(dataToSort.get(0));
			returnVector.add(dataToSort.get(1));
			returnVector.add(dataToSort.get(2));
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return returnVector;
	}
	
	/**
	 * @author tb791
	 * This method is a driver method to test different features of the code and to eventually run the actual program.
	 * @param args command line arguments (shouldn't be any).
	 */
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
 		
 		
	    UI.main.showUI();
		
	}
}