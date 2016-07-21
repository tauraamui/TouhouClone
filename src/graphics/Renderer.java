package graphics;

import game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import userinterface.Window;


public class Renderer {
	
	public long totalRenderTime;
	private Canvas canvas = new Canvas(Window.Width, Window.Height);
	private GraphicsContext graphicsContext;

	public Renderer() {
		canvas.setWidth(Window.Width);
		canvas.setHeight(Window.Height);
	}

	public void render() {
		graphicsContext = canvas.getGraphicsContext2D();
		Game.render(graphicsContext);
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
