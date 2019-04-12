package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import java.lang.*;

import backend.Operations;
import javafx.scene.layout.*;

public class ui_track_pane extends Pane {

    private final ImageView img;
    private final Label lbl_Track;
    private final Label lbl_DailyUpdate;
    private final TextField txtField_Calories;
    private final TextField txtField_Water;
    private final TextField txtField_Steps;
    private final TextField txtField_ExerciseDuration;
    private final Label lbl_Calories;
    private final Label lbl_Water;
    private final Label lbl_Steps;
    private final Label lbl_ExerciseDuration;
    private final Label lbl_WeeklyUpdate;
    private final Button btn;
    private final Label lbl_Weight;
    private final TextField txtField_Weight;

    public ui_track_pane() {

        img = new ImageView();
        lbl_Track = new Label();
        lbl_DailyUpdate = new Label();
        txtField_Calories = new TextField();
        txtField_Water = new TextField();
        txtField_Steps = new TextField();
        txtField_ExerciseDuration = new TextField();
        lbl_Calories = new Label();
        lbl_Water = new Label();
        lbl_Steps = new Label();
        lbl_ExerciseDuration = new Label();
        lbl_WeeklyUpdate = new Label();
        btn = new Button();
        lbl_Weight = new Label();
        txtField_Weight = new TextField();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #FEAD80;");

        img.setFitHeight(45.0);
        img.setFitWidth(45.0);
        img.setLayoutX(11.0);
        img.setLayoutY(11.0);
        img.setPickOnBounds(true);
        img.setImage(new Image(getClass().getResource("BackArrow.png").toExternalForm()));

        img.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToMainMenu();
            }
        });

        lbl_Track.setLayoutX(203.0);
        lbl_Track.setLayoutY(17.0);
        lbl_Track.setText("Track");
        lbl_Track.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl_Track.setFont(new Font(64.0));

        lbl_DailyUpdate.setLayoutX(17.0);
        lbl_DailyUpdate.setLayoutY(117.0);
        lbl_DailyUpdate.setText("Daily Update:");
        lbl_DailyUpdate.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl_DailyUpdate.setFont(new Font(24.0));

        txtField_Calories.setLayoutX(48.0);
        txtField_Calories.setLayoutY(238.0);
        
        txtField_Water.setLayoutX(48.0);
        txtField_Water.setLayoutY(187.0);

        txtField_Steps.setLayoutX(48.0);
        txtField_Steps.setLayoutY(289.0);

        txtField_ExerciseDuration.setLayoutX(48.0);
        txtField_ExerciseDuration.setLayoutY(337.0);

        lbl_Calories.setLayoutX(48.0);
        lbl_Calories.setLayoutY(221.0);
        lbl_Calories.setText("Calories:");

        lbl_Water.setLayoutX(48.0);
        lbl_Water.setLayoutY(170.0);
        lbl_Water.setText("Water (ML):");

        lbl_Steps.setLayoutX(48.0);
        lbl_Steps.setLayoutY(272.0);
        lbl_Steps.setText("Steps:");

        lbl_ExerciseDuration.setLayoutX(48.0);
        lbl_ExerciseDuration.setLayoutY(320.0);
        lbl_ExerciseDuration.setText("Exercise Duration:");

        lbl_WeeklyUpdate.setLayoutX(374.0);
        lbl_WeeklyUpdate.setLayoutY(117.0);
        lbl_WeeklyUpdate.setText("Weekly Update:");
        lbl_WeeklyUpdate.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl_WeeklyUpdate.setFont(new Font(24.0));

        btn.setLayoutX(490.0);
        btn.setLayoutY(359.0);
        btn.setMnemonicParsing(false);
        btn.setText("Update");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(!txtField_Calories.getText().isEmpty() || !txtField_Water.getText().isEmpty() || !txtField_Steps.getText().isEmpty() || !txtField_ExerciseDuration.getText().isEmpty() || !txtField_Weight.getText().isEmpty()) {
                    String[] entries = {txtField_Steps.getText(), txtField_Calories.getText(), txtField_Water.getText(), txtField_ExerciseDuration.getText(), txtField_Weight.getText()}; //REORDERED TO ALIGN WITH DB
                    int[] dataToBeLogged = {0,0,0,0,0};
                    for (int i = 0; i< entries.length; i++) {
                    	if(!entries[i].isEmpty()) {
                    		dataToBeLogged[i] = Operations.toInt(entries[i]);
                    	}
                    }
                    backend.DataHandler.logWorkout(main.getUserID(), dataToBeLogged);
                }
                
            }
        });

        lbl_Weight.setLayoutX(390.0);
        lbl_Weight.setLayoutY(170.0);
        lbl_Weight.setText("Weight (KG):");

        txtField_Weight.setLayoutX(386.0);
        txtField_Weight.setLayoutY(187.0);

        getChildren().add(img);
        getChildren().add(lbl_Track);
        getChildren().add(lbl_DailyUpdate);
        getChildren().add(txtField_Calories);
        getChildren().add(txtField_Water);
        getChildren().add(txtField_Steps);
        getChildren().add(txtField_ExerciseDuration);
        getChildren().add(lbl_Calories);
        getChildren().add(lbl_Water);
        getChildren().add(lbl_Steps);
        getChildren().add(lbl_ExerciseDuration);
        getChildren().add(lbl_WeeklyUpdate);
        getChildren().add(btn);
        getChildren().add(lbl_Weight);
        getChildren().add(txtField_Weight);
        
        txtField_Water.requestFocus();

    }
}
