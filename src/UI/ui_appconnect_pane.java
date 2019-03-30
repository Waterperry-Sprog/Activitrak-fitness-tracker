package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import java.lang.*;
import javafx.scene.layout.*;

public class ui_appconnect_pane extends Pane {

    private final Label lbl_Connect;
    private final TextArea txtArea;
    private final RadioButton radioBtn_Suunto;
    private final RadioButton radioBtn_AppleHealth;
    private final RadioButton radioBtn_Garmin;
    private final Label lbl_Paste;
    private final Button btn;
    private final ImageView img;
    private final ToggleGroup toggleGroup = new ToggleGroup();

    public ui_appconnect_pane() {

        lbl_Connect = new Label();
        txtArea = new TextArea();
        radioBtn_Suunto = new RadioButton();
        radioBtn_AppleHealth = new RadioButton();
        radioBtn_Garmin = new RadioButton();
        lbl_Paste = new Label();
        btn = new Button();
        img = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #FEAD80;");

        lbl_Connect.setLayoutX(145.0);
        lbl_Connect.setLayoutY(14.0);
        lbl_Connect.setText("Connect With App");
        lbl_Connect.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl_Connect.setFont(new Font(36.0));

        txtArea.setLayoutX(48.0);
        txtArea.setLayoutY(100.0);
        txtArea.setPrefHeight(200.0);
        txtArea.setPrefWidth(505.0);

        radioBtn_Suunto.setLayoutX(48.0);
        radioBtn_Suunto.setLayoutY(300.0);
        radioBtn_Suunto.setMnemonicParsing(false);
        radioBtn_Suunto.setToggleGroup(toggleGroup);
        radioBtn_Suunto.setText("Suunto");

        radioBtn_AppleHealth.setLayoutX(48.0);
        radioBtn_AppleHealth.setLayoutY(322.0);
        radioBtn_AppleHealth.setMnemonicParsing(false);
        radioBtn_AppleHealth.setText("Apple Health");
        radioBtn_AppleHealth.setToggleGroup(toggleGroup);

        radioBtn_Garmin.setLayoutX(48.0);
        radioBtn_Garmin.setLayoutY(344.0);
        radioBtn_Garmin.setMnemonicParsing(false);
        radioBtn_Garmin.setText("Garmin");
        radioBtn_Garmin.setToggleGroup(toggleGroup);

        lbl_Paste.setLayoutX(50.0);
        lbl_Paste.setLayoutY(83.0);
        lbl_Paste.setText("Paste CSV data here:");

        btn.setLayoutX(494.0);
        btn.setLayoutY(329.0);
        btn.setMnemonicParsing(false);
        btn.setText("Submit");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String app;
                switch (toggleGroup.getSelectedToggle().toString()) {
                    case "Suunto":
                        app = "suunto";
                        break;
                    case "Apple Health":
                        app = "apple";
                        break;
                    case "Garmin:":
                        app = "garmin";
                        break;
                    default:
                        app = "";
                        break;
                }
                main.appConnectInput(app, txtArea.getText());
            }
        });

        img.setFitHeight(45.0);
        img.setFitWidth(45.0);
        img.setLayoutX(21.0);
        img.setLayoutY(21.0);
        img.setPickOnBounds(true);
        img.setImage(new Image(getClass().getResource("BackArrow.png").toExternalForm()));

        img.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToMainMenu();
            }
        });

        getChildren().add(lbl_Connect);
        getChildren().add(txtArea);
        getChildren().add(radioBtn_Suunto);
        getChildren().add(radioBtn_AppleHealth);
        getChildren().add(radioBtn_Garmin);
        getChildren().add(lbl_Paste);
        getChildren().add(btn);
        getChildren().add(img);

    }
}
