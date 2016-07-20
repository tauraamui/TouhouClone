package userinterface;

import game.Game;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by tauraaamui on 19/07/2016.
 */
public class Window extends Application {

    public static int Width = 800;
    public static int Height = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Touhou Clone");
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(Game.Renderer.getCanvas());
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}