package backend;

public class Workout {

	private int duration;
	private int calories;
	private int steps;
	private int water;
	private String username;
	private int averageHR;
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAverageHR() {
		return averageHR;
	}

	public void setAverageHR(int averageHR) {
		this.averageHR = averageHR;
	}

	
	public Workout( String username, int duration, int calories, int steps, int water, int averageHR) {
		this.averageHR = averageHR;
		this.calories = calories;
		this.duration = duration;
		this.steps = steps;
		this.water = water;
		this.averageHR = averageHR;
	}
}
