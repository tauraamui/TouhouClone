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
    private static Scene rootScene;

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
        rootScene = new Scene(anchorPane);
        primaryStage.setScene(rootScene);
        primaryStage.show();
        game = new Game();
        Game.input = new GameInputHandler(rootScene);
        game.start();
    }

    public static Scene getScene() {
         return rootScene;
    }

    @Override
    public void stop() {
        Game.quit();
    }

    public static void main(String[] args) { launch(args); }
}