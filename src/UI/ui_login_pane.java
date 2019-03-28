import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.scene.control.*;
import java.lang.*;
import javafx.scene.layout.*;

public class ui_login_pane extends Pane {

    private final Label lbl_Username;
    private final PasswordField pwdField;
    private final TextField txtField;
    private final Label lbl_Password;
    private final Label lbl_ForgotPassword;
    private final Label lbl_Login;
    private final Label lbl_Signup;
    private final Button btn;
    private final ImageView img;

    public ui_login_pane() {

        lbl_Username = new Label();
        pwdField = new PasswordField();
        txtField = new TextField();
        lbl_Password = new Label();
        lbl_ForgotPassword = new Label();
        lbl_Login = new Label();
        lbl_Signup = new Label();
        btn = new Button();
        img = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #FEAD80;");

        lbl_Username.setLayoutX(65.0);
        lbl_Username.setLayoutY(150.0);
        lbl_Username.setText("Username:");
        lbl_Username.setFont(new Font(18.0));

        pwdField.setLayoutX(65.0);
        pwdField.setLayoutY(250.0);

        txtField.setLayoutX(65.0);
        txtField.setLayoutY(175.0);

        lbl_Password.setLayoutX(65.0);
        lbl_Password.setLayoutY(225.0);
        lbl_Password.setText("Password:");
        lbl_Password.setFont(new Font(18.0));

        lbl_ForgotPassword.setLayoutX(245.0);
        lbl_ForgotPassword.setLayoutY(369.0);
        lbl_ForgotPassword.setText("Forgot Password?");
        lbl_ForgotPassword.setUnderline(true);

        lbl_Login.setLayoutX(217.0);
        lbl_Login.setLayoutY(14.0);
        lbl_Login.setText("Login");
        lbl_Login.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl_Login.setFont(new Font(64.0));

        lbl_Signup.setLayoutX(233.0);
        lbl_Signup.setLayoutY(314.0);
        lbl_Signup.setText("Click here to Sign Up!");
        lbl_Signup.setUnderline(true);

        lbl_Signup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToSignup();
            }
        });

        btn.setLayoutX(274.0);
        btn.setLayoutY(250.0);
        btn.setMnemonicParsing(false);
        btn.setStyle("-fx-background-color: #FFFFFF;");
        btn.setText("Login");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                main.login(txtField.getText(), pwdField.getText());
            }
        });

        img.setFitHeight(150.0);
        img.setFitWidth(200.0);
        img.setLayoutX(423.0);
        img.setLayoutY(12.0);
        img.setPickOnBounds(true);
        img.setPreserveRatio(true);
        img.setImage(new Image(getClass().getResource("Logo.png").toExternalForm()));

        getChildren().add(lbl_Username);
        getChildren().add(pwdField);
        getChildren().add(txtField);
        getChildren().add(lbl_Password);
        getChildren().add(lbl_ForgotPassword);
        getChildren().add(lbl_Login);
        getChildren().add(lbl_Signup);
        getChildren().add(btn);
        getChildren().add(img);

    }
}
