
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import java.lang.*;
import javafx.scene.layout.*;

public class ui_results_pane extends Pane {

    private final ImageView img;
    private final Label lbl;

    public ui_results_pane() {

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

        lbl.setLayoutX(189.0);
        lbl.setLayoutY(17.0);
        lbl.setText("Results");
        lbl.setTextFill(javafx.scene.paint.Color.valueOf("#273c98"));
        lbl.setFont(new Font(64.0));

        //TODO ADD GRAPHING FOR VIEWING GOAL RESULTS
        getChildren().add(img);
        getChildren().add(lbl);

    }
}
