package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class ui_results_pane_old extends Pane {

    private final ImageView img;
    private final Label lbl;

    public ui_results_pane_old() {

        img = new ImageView();
        lbl = new Label();

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

        lbl.setLayoutX(189.0);
        lbl.setLayoutY(17.0);
        lbl.setText("Results");
        lbl.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl.setFont(new Font(64.0));

        int goal = backend.DataHandler.getGoalForUser("water", main.getUserID());
        int current = backend.DataHandler.getProgressForUser("water", main.getUserID());
        System.out.println("Goal water: "+goal+"\tCurrent water: "+current);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Current Water", current),
                new PieChart.Data("Remaining Water", ( (goal - current)<=0) ? (0) : (goal-current) )
        );
        
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Water Intake (ml)");
        pieChart.setClockwise(true);
        pieChart.setStartAngle(0);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        pieChart.setLayoutX(173);
        pieChart.setLayoutY(121);
        pieChart.setMaxSize(255,255);

        //TODO ADD GRAPHING FOR VIEWING GOAL RESULTS
        getChildren().add(img);
        getChildren().add(lbl);
        if(backend.DataHandler.hasUserSetGoal(main.getUserID())) {
        	getChildren().add(pieChart);
        }
        
    }
}
