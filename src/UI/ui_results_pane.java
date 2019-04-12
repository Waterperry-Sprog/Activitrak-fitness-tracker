package UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

import java.lang.*;
import java.util.Vector;

import javafx.scene.layout.*;

public class ui_results_pane extends Pane {

    private final ImageView imageView;
    private final Label label;
    private final ChoiceBox choiceBox;
    private final NumberAxis xAxis;
    private final NumberAxis yAxis;
    private final LineChart<Number,Number> lineChart;
    private final CategoryAxis categoryAxis0;
    private final NumberAxis numberAxis0;
    private final BarChart barChart;
    private final Label label0;
    private final Button btn;
    private final ToggleButton sortBtn;

    public ui_results_pane() {

        imageView = new ImageView();
        label = new Label();
        choiceBox = new ChoiceBox();

        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.getXAxis().setOpacity(0);
        categoryAxis0 = new CategoryAxis();
        numberAxis0 = new NumberAxis();
        barChart = new BarChart(categoryAxis0, numberAxis0);
        label0 = new Label();
        btn = new Button();
        sortBtn = new ToggleButton();
        
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #FEAD80;");

        imageView.setFitHeight(45.0);
        imageView.setFitWidth(45.0);
        imageView.setLayoutX(11.0);
        imageView.setLayoutY(11.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("BackArrow.png").toExternalForm()));
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToMainMenu();
            }
        });

        label.setLayoutX(189.0);
        label.setLayoutY(17.0);
        label.setText("Results");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        label.setFont(new Font(64.0));
        

        choiceBox.setLayoutX(39.0);
        choiceBox.setLayoutY(141.0);
        choiceBox.setPrefHeight(0.0);
        choiceBox.setPrefWidth(154.0);
        choiceBox.setItems(FXCollections.observableArrayList("Calories", "Water", "Steps", "Exercise Duration"));
        
        btn.setLayoutX(195.0);
        btn.setLayoutY(141.0);
        btn.setPrefHeight(0.0);
        btn.setPrefWidth(60);
        btn.setText("Update");
        
        sortBtn.setLayoutX(260.0);
        sortBtn.setLayoutY(141.0);
        sortBtn.setPrefHeight(0.0);
        sortBtn.setPrefWidth(30);
        sortBtn.setText("'-.");
        
        lineChart.setLayoutX(24.0);
        lineChart.setLayoutY(184.0);
        lineChart.setPrefHeight(177.0);
        lineChart.setPrefWidth(265.0);

        categoryAxis0.setSide(javafx.geometry.Side.BOTTOM);

        numberAxis0.setSide(javafx.geometry.Side.LEFT);
        barChart.setLayoutX(300.0);
        barChart.setLayoutY(184.0);
        barChart.setPrefHeight(177.0);
        barChart.setPrefWidth(256.0);

        label0.setLayoutX(357.0);
        label0.setLayoutY(148.0);
        label0.setText("Friends' Results");

        getChildren().add(imageView);
        getChildren().add(label);
        getChildren().add(choiceBox);
        getChildren().add(lineChart);
        getChildren().add(barChart);
        getChildren().add(label0);
        getChildren().add(btn);
        getChildren().add(sortBtn);
        
        Vector<backend.UserData> topFriends = new Vector<backend.UserData>();
        topFriends = backend.DataHandler.getTopThreeFriendsForUser(main.getUserID());
        
//        XYChart.Series personalData = new XYChart.Series();
          String userGoalName = backend.DataHandler.findUserGoal(main.getUserID());
          int goalProgress = (int) ((double) backend.DataHandler.getProgressForUser(userGoalName, main.getUserID())/(double) backend.DataHandler.getGoalForUser(userGoalName, main.getUserID()) * 100);
          XYChart.Series data = new XYChart.Series();
          try {
        	data.getData().add(new XYChart.Data(main.getUserID(), goalProgress));
        	data.getData().add(new XYChart.Data(topFriends.get(0).getUsername(), topFriends.get(0).getUserGoalProgress()));
        	data.getData().add(new XYChart.Data(topFriends.get(1).getUsername(), topFriends.get(1).getUserGoalProgress()));
        	data.getData().add(new XYChart.Data(topFriends.get(2).getUsername(), topFriends.get(2).getUserGoalProgress()));
          } catch (ArrayIndexOutOfBoundsException e) {
        	
        }
        
        barChart.setBarGap(1.0);
        barChart.setCategoryGap(1.0);
        barChart.getData().add(data);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event){
            	final NumberAxis xAxis = new NumberAxis();
                final NumberAxis yAxis = new NumberAxis();
                LineChart.Series<Number,Number> series = new LineChart.Series<Number,Number>();
                xAxis.setLabel("Workout");
                yAxis.setLabel(choiceBox.getValue().toString());        
                
                //import int[] to a variable and then add every element to the workoutDataHistorical
                int[] values = backend.DataHandler.getMetricFromWorkoutHistory(main.getUserID(), choiceBox.getValue().toString());
                Vector<Integer> workoutDataHistorical = new Vector<Integer>();
                for(int i : values) {
                	workoutDataHistorical.add(i);
                }
                
                for(int i = 0; i < values.length; i++) {
                	if(values[i] != 0) {
                		workoutDataHistorical.add(values[i]);
                	}
                }
                if(sortBtn.isSelected()) {
                	workoutDataHistorical = backend.Operations.sortNumbers(workoutDataHistorical);
                }
                
            	for (int i = 0; i < workoutDataHistorical.size(); i++) {
                  	series.getData().add( new LineChart.Data<Number,Number>( i, workoutDataHistorical.get(i)) );
          		  }
               
            	//update the x and y number lists and update the chart.
            	lineChart.getData().clear();
            	lineChart.getData().add(series);
            	lineChart.setVerticalGridLinesVisible(false);
            	lineChart.getXAxis().setOpacity(0);
            }
        });
    }
}
