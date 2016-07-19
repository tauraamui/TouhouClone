package graphics;

import game.Game;
import game.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

import javax.swing.JPanel;

public class Renderer {
	
	public long totalRenderTime;
	private Canvas canvas = new Canvas();
	private GraphicsContext graphicsContext;

	public Renderer() {
		graphicsContext = canvas.getGraphicsContext2D();
	}

	public void render() {
		Game.render(graphicsContext);
	}

	public Canvas getCanvas() {return canvas;}
}
