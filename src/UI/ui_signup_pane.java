package UI;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import java.lang.*;
import javafx.scene.layout.*;

public class ui_signup_pane extends Pane {

    private final Label lbl_Signup;
    private final ImageView imageView;
    private final Label lbl_Password;
    private final TextField txtField;
    private final PasswordField pwdField;
    private final Label lbl_Username;
    private final PasswordField pwdField_Confirm;
    private final Label lbl_ConfirmPass;
    private final Button btn;

    public ui_signup_pane() {

        lbl_Signup = new Label();
        imageView = new ImageView();
        lbl_Password = new Label();
        txtField = new TextField();
        pwdField = new PasswordField();
        lbl_Username = new Label();
        pwdField_Confirm = new PasswordField();
        lbl_ConfirmPass = new Label();
        btn = new Button();
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #FEAD80;");

        lbl_Signup.setLayoutX(181.0);
        lbl_Signup.setLayoutY(20.0);
        lbl_Signup.setText("Sign Up");
        lbl_Signup.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl_Signup.setFont(new Font(64.0));

        imageView.setFitHeight(150.0);
        imageView.setFitWidth(200.0);
        imageView.setLayoutX(425.0);
        imageView.setLayoutY(12.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("Logo.png").toExternalForm()));

        lbl_Password.setLayoutX(256.0);
        lbl_Password.setLayoutY(237.0);
        lbl_Password.setText("Password:");
        lbl_Password.setFont(new Font(18.0));

        txtField.setLayoutX(217.0);
        txtField.setLayoutY(187.0);

        pwdField.setLayoutX(217.0);
        pwdField.setLayoutY(260.0);

        lbl_Username.setLayoutX(254.0);
        lbl_Username.setLayoutY(162.0);
        lbl_Username.setText("Username:");
        lbl_Username.setFont(new Font(18.0));

        pwdField_Confirm.setLayoutX(217.0);
        pwdField_Confirm.setLayoutY(332.0);

        lbl_ConfirmPass.setLayoutX(219.0);
        lbl_ConfirmPass.setLayoutY(309.0);
        lbl_ConfirmPass.setText("Confirm Password:");
        lbl_ConfirmPass.setFont(new Font(18.0));

        btn.setLayoutX(519.0);
        btn.setLayoutY(359.0);
        btn.setMnemonicParsing(false);
        btn.setStyle("-fx-background-color: #FFFFFF;");
        btn.setText("Sign Up");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                main.signup(txtField.getText(), pwdField.getText(), pwdField_Confirm.getText());
            }
        });

        getChildren().add(lbl_Signup);
        getChildren().add(imageView);
        getChildren().add(lbl_Password);
        getChildren().add(txtField);
        getChildren().add(pwdField);
        getChildren().add(lbl_Username);
        getChildren().add(pwdField_Confirm);
        getChildren().add(lbl_ConfirmPass);
        getChildren().add(btn);

    }
}
