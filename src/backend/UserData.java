package backend;

public class UserData {
	
	String username;
	int userGoalProgress;
	
	public UserData(String username, int userGoalProgress) {
		this.userGoalProgress = userGoalProgress;
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public int getUserGoalProgress() {
		return userGoalProgress;
	}
	
}
