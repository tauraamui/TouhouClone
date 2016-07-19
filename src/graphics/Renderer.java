package graphics;

import game.Game;
import game.stage.Stage;
import javafx.scene.canvas.Canvas

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends Canvas {
	
	public long totalRenderTime;

	public Renderer() {
		setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics canvas) {
		long before = System.nanoTime();
		canvas.setColor(Color.darkGray);
		canvas.fillRect(0, 0, Stage.Width, Stage.Height);
		Game.render(canvas);
		totalRenderTime = System.nanoTime()-before;
	}
}
