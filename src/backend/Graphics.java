package backend;
import java.util.Vector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Graphics extends Application{

	static Vector<Integer> classx = new Vector<Integer>();
	static Vector<Integer> classy = new Vector<Integer>();
	
	@Override
	public void start(Stage arg0) {
		arg0.setTitle("Sample graph area");
		
		//defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time /m");
        yAxis.setLabel("Heart Rate /bpm");
        
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("Sample HR vs. time graph");
		
        XYChart.Series series = new XYChart.Series();
        
        for (int i = 0; i < classx.size(); i++) {
        	series.getData().add( new XYChart.Data( classx.get(i)/60, classy.get(i) ) );
		}
        
        
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(series);
        
        Scene scene  = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);
        arg0.setScene(scene);
        arg0.show();
	}
	
	public static void display(Vector<Integer> x, Vector<Integer> y) {
		classx = x;
		classy = y;
		launch();
	}
	
}
