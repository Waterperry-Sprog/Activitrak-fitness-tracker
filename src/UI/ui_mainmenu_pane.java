package UI;


import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import java.lang.*;
import javafx.scene.layout.*;

public class ui_mainmenu_pane extends Pane {

    private final Label lbl_Data1;
    private final Label lbl_Data2;
    private final Label lbl_Data3;
    private final Rectangle rect_Track;
    private final Rectangle rect_Results;
    private final Rectangle rect_Goals;
    private final Rectangle rect_Profile;
    private final Label lbl_Track;
    private final Label lbl_Results;
    private final Label lbl_Goals;
    private final Label lbl_Profile;
    private final ImageView img;
    private final Label lbl_Logout;

    public ui_mainmenu_pane() {

        lbl_Data1 = new Label();
        lbl_Data2 = new Label();
        lbl_Data3 = new Label();
        rect_Track = new Rectangle();
        rect_Results = new Rectangle();
        rect_Goals = new Rectangle();
        rect_Profile = new Rectangle();
        lbl_Track = new Label();
        lbl_Results = new Label();
        lbl_Goals = new Label();
        lbl_Profile = new Label();
        img = new ImageView();
        lbl_Logout = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(400.0);
        setStyle("-fx-background-color: #FEAD80;");

        lbl_Data1.setLayoutX(352.0);
        lbl_Data1.setLayoutY(14.0);
        lbl_Data1.setText("Label");

        lbl_Data2.setLayoutX(352.0);
        lbl_Data2.setLayoutY(31.0);
        lbl_Data2.setText("Label");

        lbl_Data3.setLayoutX(352.0);
        lbl_Data3.setLayoutY(48.0);
        lbl_Data3.setText("Label");

        rect_Track.setArcHeight(5.0);
        rect_Track.setArcWidth(5.0);
        rect_Track.setFill(javafx.scene.paint.Color.valueOf("#fd4c06"));
        rect_Track.setHeight(105.0);
        rect_Track.setLayoutX(14.0);
        rect_Track.setLayoutY(100.0);
        rect_Track.setStroke(javafx.scene.paint.Color.BLACK);
        rect_Track.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rect_Track.setStrokeWidth(0.0);
        rect_Track.setWidth(374.0);

        rect_Track.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToTrack();
            }
        });

        rect_Results.setArcHeight(5.0);
        rect_Results.setArcWidth(5.0);
        rect_Results.setFill(javafx.scene.paint.Color.valueOf("#fd4c06"));
        rect_Results.setHeight(105.0);
        rect_Results.setLayoutX(14.0);
        rect_Results.setLayoutY(225.0);
        rect_Results.setStroke(javafx.scene.paint.Color.BLACK);
        rect_Results.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rect_Results.setStrokeWidth(0.0);
        rect_Results.setWidth(374.0);

        rect_Results.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToResults();
            }
        });

        rect_Goals.setArcHeight(5.0);
        rect_Goals.setArcWidth(5.0);
        rect_Goals.setFill(javafx.scene.paint.Color.valueOf("#fd4c06"));
        rect_Goals.setHeight(105.0);
        rect_Goals.setLayoutX(14.0);
        rect_Goals.setLayoutY(350.0);
        rect_Goals.setStroke(javafx.scene.paint.Color.BLACK);
        rect_Goals.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rect_Goals.setStrokeWidth(0.0);
        rect_Goals.setWidth(374.0);

        rect_Goals.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToGoals();
            }
        });

        rect_Profile.setArcHeight(5.0);
        rect_Profile.setArcWidth(5.0);
        rect_Profile.setFill(javafx.scene.paint.Color.valueOf("#fd4c06"));
        rect_Profile.setHeight(105.0);
        rect_Profile.setLayoutX(14.0);
        rect_Profile.setLayoutY(475.0);
        rect_Profile.setStroke(javafx.scene.paint.Color.BLACK);
        rect_Profile.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rect_Profile.setStrokeWidth(0.0);
        rect_Profile.setWidth(374.0);

        rect_Profile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToProfile();
            }
        });

        lbl_Track.setLayoutX(14.0);
        lbl_Track.setLayoutY(100.0);
        lbl_Track.setText("Track");
        lbl_Track.setTextFill(javafx.scene.paint.Color.WHITE);
        lbl_Track.setFont(new Font(36.0));

        lbl_Track.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToTrack();
            }
        });

        lbl_Results.setLayoutX(14.0);
        lbl_Results.setLayoutY(225.0);
        lbl_Results.setText("Results");
        lbl_Results.setTextFill(javafx.scene.paint.Color.WHITE);
        lbl_Results.setFont(new Font(36.0));

        lbl_Results.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToResults();
            }
        });

        lbl_Goals.setLayoutX(14.0);
        lbl_Goals.setLayoutY(350.0);
        lbl_Goals.setText("Goals");
        lbl_Goals.setTextFill(javafx.scene.paint.Color.WHITE);
        lbl_Goals.setFont(new Font(36.0));

        lbl_Goals.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToGoals();
            }
        });

        lbl_Profile.setLayoutX(14.0);
        lbl_Profile.setLayoutY(475.0);
        lbl_Profile.setText("Profile");
        lbl_Profile.setTextFill(javafx.scene.paint.Color.WHITE);
        lbl_Profile.setFont(new Font(36.0));

        lbl_Profile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToProfile();
            }
        });

        img.setFitHeight(45.0);
        img.setFitWidth(45.0);
        img.setLayoutX(1.0);
        img.setLayoutY(1.0);
        img.setPickOnBounds(true);
        img.setImage(new Image(getClass().getResource("BackArrow.png").toExternalForm()));

        lbl_Logout.setLayoutX(176.0);
        lbl_Logout.setLayoutY(6.0);
        lbl_Logout.setText("Log Out");

        lbl_Logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.logout();
            }
        });

        getChildren().add(lbl_Data1);
        getChildren().add(lbl_Data2);
        getChildren().add(lbl_Data3);
        getChildren().add(rect_Track);
        getChildren().add(rect_Results);
        getChildren().add(rect_Goals);
        getChildren().add(rect_Profile);
        getChildren().add(lbl_Track);
        getChildren().add(lbl_Results);
        getChildren().add(lbl_Goals);
        getChildren().add(lbl_Profile);
        getChildren().add(img);
        getChildren().add(lbl_Logout);

    }
}
