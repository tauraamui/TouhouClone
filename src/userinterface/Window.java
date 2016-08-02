package userinterface;

import game.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import userinterface.io.GameInputHandler;

/**
 * Created by tauraaamui on 19/07/2016.
 */
public class Window extends Application {

    public static int Width = 800;
    public static int Height = 600;
    public static Game game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Touhou Clone");
        primaryStage.setWidth(Width);
        primaryStage.setHeight(Height);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(Game.Renderer.getCanvas());
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        Scene rootScene = new Scene(anchorPane);
        Game.input = new GameInputHandler(rootScene);
        game = new Game();
        primaryStage.setScene(rootScene);
        primaryStage.show();
        game.start();
    }

    @Override
    public void stop() {
        Game.quit();
    }

    public static void main(String[] args) { launch(args); }
}