package graphics.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PercentageBar {
	
	private int totalLength = 100;
	private float percentage = 100;
	private int height = 5;
	
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public void render(GraphicsContext graphicsContext, int x, int y) {
		graphicsContext.setFill(Color.DARKGRAY);
		graphicsContext.fillRect(x, y, totalLength, height);
		graphicsContext.setFill(Color.MAGENTA);
		graphicsContext.fillRect(x, y, percentage, height);
	}
}
