package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.scene.control.*;
import java.lang.*;
import javafx.scene.layout.*;

public class ui_friends_pane extends Pane {

    private final TextArea listView;
    private final Label lbl_Friends;
    private final Label lbl_addFriend;
    private final TextField txtField_addFriend;
    private final Label lbl_block;
    private final TextField txtField_unblock;
    private final Label lbl_unblock;
    private final TextField txtField_block;
    private final Label lbl_title;
    private final ImageView img;
    private final Button btn;

    public ui_friends_pane() {

        listView = new TextArea();
        lbl_Friends = new Label();
        lbl_addFriend = new Label();
        txtField_addFriend = new TextField();
        lbl_block = new Label();
        txtField_unblock = new TextField();
        lbl_unblock = new Label();
        txtField_block = new TextField();
        lbl_title = new Label();
        img = new ImageView();
        btn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #FEAD80;");

        listView.setLayoutX(386.0);
        listView.setLayoutY(54.0);
        listView.setPrefHeight(336.0);
        listView.setPrefWidth(200.0);

        lbl_Friends.setLayoutX(386.0);
        lbl_Friends.setLayoutY(37.0);
        lbl_Friends.setText("Friends:");

        lbl_addFriend.setLayoutX(49.0);
        lbl_addFriend.setLayoutY(124.0);
        lbl_addFriend.setText("Add a friend:");

        txtField_addFriend.setLayoutX(44.0);
        txtField_addFriend.setLayoutY(141.0);

        lbl_block.setLayoutX(49.0);
        lbl_block.setLayoutY(178.0);
        lbl_block.setText("Unfriend someone:");

        txtField_unblock.setLayoutX(44.0);
        txtField_unblock.setLayoutY(249.0);

        lbl_unblock.setLayoutX(49.0);
        lbl_unblock.setLayoutY(232.0);
        lbl_unblock.setText("Unblock someone:");

        txtField_block.setLayoutX(44.0);
        txtField_block.setLayoutY(195.0);

        lbl_title.setLayoutX(237.0);
        lbl_title.setLayoutY(15.0);
        lbl_title.setText("Friends");
        lbl_title.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl_title.setFont(new Font(36.0));

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

        btn.setLayoutX(249.0);
        btn.setLayoutY(195.0);
        btn.setMnemonicParsing(false);
        btn.setText("Submit");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if( !txtField_addFriend.getText().isEmpty() && !txtField_addFriend.getText().equals(main.getUserID()) ) {
                    if( !main.addFriend(txtField_addFriend.getText()) ) {
                    	Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Sorry, we can't find that user.");
                        alert.setHeaderText(null);
                        alert.setContentText("Please check the username you have entered and try again.");
                        alert.showAndWait();
                    }
                }
                if(!txtField_block.getText().isEmpty()) {
                    main.unfriendUser(txtField_block.getText());
                }
//                if(!txtField_unblock.getText().isEmpty()) {
//                    main.unblockUser(txtField_unblock.getText());
//                }
                listView.clear();
                String[] friendList = backend.DataHandler.getFriendsForUser(main.getUserID());
                for (String s : friendList) {
                	if(backend.DataHandler.areFriends(main.getUserID(), s)) {
                		listView.appendText(s+"\n");
                	}
                }
            }
        });

        getChildren().add(listView);
        getChildren().add(lbl_Friends);
        getChildren().add(lbl_addFriend);
        getChildren().add(txtField_addFriend);
        getChildren().add(lbl_block);
//        getChildren().add(txtField_unblock);
//        getChildren().add(lbl_unblock);
        getChildren().add(txtField_block);
        
        getChildren().add(lbl_title);
        getChildren().add(img);
        getChildren().add(btn);
        
        listView.setEditable(false);
        String[] friendList = backend.DataHandler.getFriendsForUser(main.getUserID());
        for (String s : friendList) {
        	if(backend.DataHandler.areFriends(main.getUserID(), s)) {
        		listView.appendText(s+"\n");
        	}
        }

    }
}
