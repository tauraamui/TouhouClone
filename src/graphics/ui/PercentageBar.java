package graphics.ui;

import game.entities.mobs.Mob;

import java.awt.Color;
import java.awt.Graphics;

import utils.Vector2;

public class PercentageBar {
	
	private int totalLength = 100;
	private float percentage = 100;
	private int height = 5;
	
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public void render(Graphics canvas, int x, int y) {
		canvas.setColor(Color.DARK_GRAY);
		canvas.fillRect(x, y, totalLength, height);
		canvas.setColor(Color.MAGENTA);
		canvas.fillRect(x, y, (int)percentage, height);
	}
	
}
