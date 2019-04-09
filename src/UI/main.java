package UI;

import backend.DataHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.image.*;
import static javafx.application.Platform.exit;

public class main extends Application {

    private static Stage stage;
    static Image profilePicture;
    private static String userID;

    protected static String getUserID() {
		return userID;
	}

	@Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        ui_login_pane pane_login = new ui_login_pane();
        Scene scene = new Scene(pane_login, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Activitrak - Login");
        stage.show();
        //switchToResults(); //[DEBUG]
    }

    static void login(String username, String password) {
        if(username.contains("'")||password.contains("'")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("SQL Injection detected.");
            alert.setHeaderText(null);
            alert.setContentText("Nice try.");		//i like this ^_^ -TB

            alert.showAndWait();
            return;
        }

        if(backend.DataHandler.authenticateUserPasswordPair(username, password)) {
        	userID = username;
            switchToMainMenu();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Username/Password");
            alert.setHeaderText(null);
            alert.setContentText("The Username/Password combination you have entered is incorrect, please try again.");

            alert.showAndWait();
        }
    }

    static boolean signup(String username, String password, String password_Confirm) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	try {
    		if(username.contains("'")||password.contains("'")) {
    			alert.setTitle("SQL Injection Detected.");
    	        alert.setHeaderText(null);
    	        alert.setContentText("Nice try.");
    	        alert.showAndWait();
    			return false;
    		}
    		
    		if(password.contentEquals(password_Confirm)) {
    			DataHandler.addToDB(username,password);
    			return true;
    		}
    		else {
    			alert.setTitle("Passwords do not match.");
    	        alert.setHeaderText(null);
    	        alert.setContentText("Please check the entered passwords and try again.");
    	        alert.showAndWait();
    		}
    	} catch(NullPointerException e) {
    		alert.setTitle("Password field is blank.");
	        alert.setHeaderText(null);
	        alert.setContentText("Please enter a password.");
	        alert.showAndWait();
    	}
    	return false;
    }

    static void logout() {
        switchToLogin();
    }

    static void passwordChange() {
        //TODO IMPLEMENT PASSWORD CHANGE
    }

    static void appConnectInput(String app, String data) {
        //TODO HANDLE CSV TEXT
        //app will be either 'apple', 'garmin', 'suunto'
    }

    static boolean addFriend(String friend) {
        return backend.DataHandler.addFriendForUser(userID, friend);
    }

    static void unfriendUser(String username) {
        backend.DataHandler.unfriend(userID,username);
    }

    static void switchToGoals() {
        ui_goals_pane pane_goals = new ui_goals_pane();
        stage.setScene(new Scene(pane_goals, 600, 400));
        stage.setTitle("Activitrak - Goals");
    }

    static void switchToSignup() {
        ui_signup_pane pane_signup = new ui_signup_pane();
        stage.setScene(new Scene(pane_signup, 600, 400));
        stage.setTitle("Activitrak - Signup");
    }

    static void switchToMainMenu() {
        ui_mainmenu_pane pane_mainmenu = new ui_mainmenu_pane();
        stage.setScene(new Scene(pane_mainmenu, 400, 600));
        stage.setTitle("Activitrak - Main Menu");
    }

    static void switchToProfile() {
        ui_profile_pane pane_profile = new ui_profile_pane();
        stage.setScene(new Scene(pane_profile, 400, 400));
        stage.setTitle("Activitrak - Profile");
    }

    static void switchToResults() {
        ui_results_pane pane_results = new ui_results_pane();
        Scene scene = new Scene(pane_results, 600, 400);
        scene.getStylesheets().add("UI/ui_results_stylesheet.css");
        stage.setScene(scene);
        stage.setTitle("Activitrak - Results");
    }

    static void switchToTrack() {
        ui_track_pane pane_track = new ui_track_pane();
        stage.setScene(new Scene(pane_track, 600, 400));
        stage.setTitle("Activitrak - Track");
    }

    static void switchToLogin() {
        ui_login_pane pane_login = new ui_login_pane();
        stage.setScene(new Scene(pane_login, 600, 400));
        stage.setTitle("Activitrak - Login");
    }

    static void switchToAppConnect() {
        ui_appconnect_pane pane_appconnect = new ui_appconnect_pane();
        stage.setScene(new Scene(pane_appconnect, 600, 400));
        stage.setTitle("Activitrak - App Connect");
    }

    static void switchToFriends() {
        ui_friends_pane pane_friends = new ui_friends_pane();
        stage.setScene(new Scene(pane_friends, 600, 400));
        stage.setTitle("Activitrak - Friends");
    }

    @Override
    public void stop() {
        exit();
    }
    
    public static void showUI() {
    	launch();
    }
}
