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

	public Renderer() {
		setFocusable(true);
	}
}
