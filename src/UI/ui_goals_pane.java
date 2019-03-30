package UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.scene.control.*;
import java.lang.*;
import javafx.scene.layout.*;

public class ui_goals_pane extends Pane {

    private final Label lbl_GoalsTitle;
    private final Label lbl_SetDaily;
    private final Label lbl_SetWeekly;
    private final Label lbl_DailyData;
    private final TextField txtField_DailyInput;
    private final Label lbl_WeeklyData;
    private final TextField txtField_WeeklyInput;
    private final ImageView img_Logo;
    private final ImageView img_BackArrow;
    private final ChoiceBox choice_Daily;
    private final ChoiceBox choice_Weekly;
    private final Button btn_WeeklyGoal;
    private final Button btn_DailyGoal;
    private final String[] dailyGoals = new String[]{"Calories (kCal):", "Water (ml):", "Steps:", "Exercise Duration (Mins):"};
    private final String[] weeklyGoals = new String[]{"Weight (kg):"};

    public ui_goals_pane() {

        lbl_GoalsTitle = new Label();
        lbl_SetDaily = new Label();
        lbl_SetWeekly = new Label();
        lbl_DailyData = new Label();
        txtField_DailyInput = new TextField();
        lbl_WeeklyData = new Label();
        txtField_WeeklyInput = new TextField();
        img_Logo = new ImageView();
        img_BackArrow = new ImageView();
        choice_Daily = new ChoiceBox();
        choice_Weekly = new ChoiceBox();
        btn_WeeklyGoal = new Button();
        btn_DailyGoal = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #FEAD80;");

        lbl_GoalsTitle.setLayoutX(215.0);
        lbl_GoalsTitle.setLayoutY(14.0);
        lbl_GoalsTitle.setText("Goals");
        lbl_GoalsTitle.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl_GoalsTitle.setFont(new Font(64.0));

        lbl_SetDaily.setLayoutX(14.0);
        lbl_SetDaily.setLayoutY(116.0);
        lbl_SetDaily.setText("Set a daily goal:");
        lbl_SetDaily.setFont(new Font(24.0));

        lbl_SetWeekly.setLayoutX(296.0);
        lbl_SetWeekly.setLayoutY(116.0);
        lbl_SetWeekly.setText("Set a weekly goal:");
        lbl_SetWeekly.setFont(new Font(24.0));

        lbl_DailyData.setLayoutX(14.0);
        lbl_DailyData.setLayoutY(220.0);
        lbl_DailyData.setText("Data:");

        txtField_DailyInput.setLayoutX(30.0);
        txtField_DailyInput.setLayoutY(237.0);

        lbl_WeeklyData.setLayoutX(300.0);
        lbl_WeeklyData.setLayoutY(220.0);
        lbl_WeeklyData.setText("Data:");

        txtField_WeeklyInput.setLayoutX(315.0);
        txtField_WeeklyInput.setLayoutY(237.0);

        img_Logo.setFitHeight(64.0);
        img_Logo.setFitWidth(64.0);
        img_Logo.setLayoutX(522.0);
        img_Logo.setLayoutY(14.0);
        img_Logo.setPickOnBounds(true);
        img_Logo.setPreserveRatio(true);
        img_Logo.setImage(new Image(getClass().getResource("Logo.png").toExternalForm()));

        img_BackArrow.setFitHeight(45.0);
        img_BackArrow.setFitWidth(45.0);
        img_BackArrow.setLayoutX(11.0);
        img_BackArrow.setLayoutY(11.0);
        img_BackArrow.setPickOnBounds(true);
        img_BackArrow.setImage(new Image(getClass().getResource("BackArrow.png").toExternalForm()));

        img_BackArrow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToMainMenu();
            }
        });

        choice_Daily.setLayoutX(38.0);
        choice_Daily.setLayoutY(176.0);
        choice_Daily.setPrefWidth(150.0);
        choice_Daily.setItems(FXCollections.observableArrayList("Calories", "Water", "Steps", "Exercise Duration"));

        choice_Daily.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue observableValue, Number value, Number value_New) {
                lbl_DailyData.setText(dailyGoals[value_New.intValue()]);
            }

        });

        choice_Weekly.setLayoutX(323.0);
        choice_Weekly.setLayoutY(176.0);
        choice_Weekly.setPrefWidth(150.0);
        choice_Weekly.setItems(FXCollections.observableArrayList("Weight"));

        choice_Weekly.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue observableValue, Number value, Number value_New) {
                lbl_WeeklyData.setText(weeklyGoals[value_New.intValue()]);
            }

        });

        btn_WeeklyGoal.setLayoutX(379.0);
        btn_WeeklyGoal.setLayoutY(285.0);
        btn_WeeklyGoal.setMnemonicParsing(false);
        btn_WeeklyGoal.setText("Set");

        btn_WeeklyGoal.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                main.setWeeklyGoal(lbl_WeeklyData.getText(), txtField_WeeklyInput.getText());
            }
        });

        btn_DailyGoal.setLayoutX(94.0);
        btn_DailyGoal.setLayoutY(285.0);
        btn_DailyGoal.setMnemonicParsing(false);
        btn_DailyGoal.setText("Set");

        btn_DailyGoal.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                main.setDailyGoal(lbl_DailyData.getText(), txtField_DailyInput.getText());
            }
        });

        getChildren().add(lbl_GoalsTitle);
        getChildren().add(lbl_SetDaily);
        getChildren().add(lbl_SetWeekly);
        getChildren().add(lbl_DailyData);
        getChildren().add(txtField_DailyInput);
        getChildren().add(lbl_WeeklyData);
        getChildren().add(txtField_WeeklyInput);
        getChildren().add(img_Logo);
        getChildren().add(img_BackArrow);
        getChildren().add(choice_Daily);
        getChildren().add(choice_Weekly);
        getChildren().add(btn_WeeklyGoal);
        getChildren().add(btn_DailyGoal);

    }
}
