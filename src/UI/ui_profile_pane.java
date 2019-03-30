package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import java.lang.*;
import javafx.scene.layout.*;

public class ui_profile_pane extends Pane {

    private final Label lbl_Logout;
    private final ImageView img_BackArrow;
    private final ImageView img_ProfilePicture;
    private final Label lbl_Data1;
    private final Label lbl_Data2;
    private final Label lbl_Data3;
    private final Label lbl_Data4;
    private final Button btn_ChangePassword;
    private final Button btn_AppConnect;
    private final Label lbl_Profile;
    private final Button btn_Friends;

    public ui_profile_pane() {

        lbl_Logout = new Label();
        img_BackArrow = new ImageView();
        img_ProfilePicture = new ImageView();
        lbl_Data1 = new Label();
        lbl_Data2 = new Label();
        lbl_Data3 = new Label();
        lbl_Data4 = new Label();
        btn_ChangePassword = new Button();
        btn_AppConnect = new Button();
        lbl_Profile = new Label();
        btn_Friends = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(400.0);
        setStyle("-fx-background-color: #FEAD80;");

        lbl_Logout.setLayoutX(176.0);
        lbl_Logout.setLayoutY(14.0);
        lbl_Logout.setText("Log Out");

        lbl_Logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.logout();
            }
        });

        img_BackArrow.setFitHeight(45.0);
        img_BackArrow.setFitWidth(45.0);
        img_BackArrow.setLayoutX(1.0);
        img_BackArrow.setLayoutY(1.0);
        img_BackArrow.setPickOnBounds(true);
        img_BackArrow.setImage(new Image(getClass().getResource("BackArrow.png").toExternalForm()));

        img_BackArrow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.switchToMainMenu();
            }
        });

        img_ProfilePicture.setFitHeight(100.0);
        img_ProfilePicture.setFitWidth(100.0);
        img_ProfilePicture.setLayoutX(24.0);
        img_ProfilePicture.setLayoutY(135.0);
        img_ProfilePicture.setPickOnBounds(true);
        img_ProfilePicture.setPreserveRatio(true);
        img_ProfilePicture.setImage(main.profilePicture);

        lbl_Data1.setLayoutX(142.0);
        lbl_Data1.setLayoutY(150.0);
        lbl_Data1.setText("Data");

        lbl_Data2.setLayoutX(142.0);
        lbl_Data2.setLayoutY(167.0);
        lbl_Data2.setText("Data");

        lbl_Data3.setLayoutX(142.0);
        lbl_Data3.setLayoutY(183.0);
        lbl_Data3.setText("Data");

        lbl_Data4.setLayoutX(142.0);
        lbl_Data4.setLayoutY(198.0);
        lbl_Data4.setText("Data");

        btn_ChangePassword.setLayoutX(31.0);
        btn_ChangePassword.setLayoutY(334.0);
        btn_ChangePassword.setMnemonicParsing(false);
        btn_ChangePassword.setText("Change Password");

        btn_ChangePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                main.passwordChange();
            }
        });

        btn_Friends.setLayoutX(31.0);
        btn_Friends.setLayoutY(290.0);
        btn_Friends.setMnemonicParsing(false);
        btn_Friends.setText("Friends");

        btn_Friends.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                main.switchToFriends();
            }
        });

        btn_AppConnect.setLayoutX(225.0);
        btn_AppConnect.setLayoutY(334.0);
        btn_AppConnect.setMnemonicParsing(false);
        btn_AppConnect.setText("Connect With App");

        btn_AppConnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                main.switchToAppConnect();
            }
        });

        lbl_Profile.setLayoutX(103.0);
        lbl_Profile.setLayoutY(31.0);
        lbl_Profile.setText("Profile");
        lbl_Profile.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl_Profile.setFont(new Font(64.0));

        getChildren().add(lbl_Logout);
        getChildren().add(img_BackArrow);
        getChildren().add(img_ProfilePicture);
        getChildren().add(lbl_Data1);
        getChildren().add(lbl_Data2);
        getChildren().add(lbl_Data3);
        getChildren().add(lbl_Data4);
        getChildren().add(btn_ChangePassword);
        getChildren().add(btn_AppConnect);
        getChildren().add(lbl_Profile);
        getChildren().add(btn_Friends);

    }
}
