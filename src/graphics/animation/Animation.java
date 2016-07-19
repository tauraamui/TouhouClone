package graphics.animation;

import game.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

	protected BufferedImage[] frames;
	protected int frameIndex = 0;
	protected long startTime = System.currentTimeMillis();

	public Animation() {
		frames = new BufferedImage[] {null};
	}

	public Animation(BufferedImage[] frames) {
		this.frames = frames;
	}

	public void update() {
		if (frameIndex + 1 >= frames.length) {
			frameIndex = 0;
		} else {
			if (System.currentTimeMillis()-startTime >= 250) {
				frameIndex++;
				startTime = System.currentTimeMillis();
			}
		}
	}
	
	public int getFrameWidth() {
		return frames[frameIndex].getWidth();
	}
	
	public int getFrameHeight() {
		return frames[frameIndex].getHeight();
	}
	
	public void render(Graphics canvas, int x, int y) {
		canvas.drawImage(frames[frameIndex], x, y, Game.Renderer);
	}
	
	public void reset() {
		frameIndex = 0;
	}
}
