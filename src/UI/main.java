import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;

import static javafx.application.Platform.exit;

public class main extends Application {

    private static Stage stage;
    static Image profilePicture;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        ui_login_pane pane_login = new ui_login_pane();
        Scene scene = new Scene(pane_login, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Activitrak - Login");
        stage.show();
    }

    static void login(String username, String password) {
        //TODO LOGIN CODE
        //Switch to Main Menu
    }

    static void signup(String username, String password, String password_Confirm) {
        //TODO SIGNUP CODE
        //Switch to Login
    }

    static void logout() {
        //TODO LOGOUT CODE
        //Switch to Login
    }

    static void passwordChange() {
        //TODO IMPLEMENT PASSWORD CHANGE
    }

    static void appConnect() {
        //TODO APP CONNECTION
    }

    static void addDailyProgress(String calories, String water, String steps, String exerciseDuration) {
        //TODO HANDLE DAILY GOAL PROGRESSION
    }

    static void addWeeklyProgress(String weight) {
        //TODO HANDLE WEEKLY GOAL PROGRESSION
    }

    static void setDailyGoal(String type, String data) {
        //TODO SET GOALS
    }

    static void setWeeklyGoal(String type, String data) {
        //TODO SET GOALS
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
        stage.setScene(new Scene(pane_results, 600, 400));
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

    @Override
    public void stop() {
        exit();
    }
}
