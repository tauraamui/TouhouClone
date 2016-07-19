package graphics.animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Animation {

	protected Image[] frames;
	protected int frameIndex = 0;
	protected long startTime = System.currentTimeMillis();

	public Animation() {
		frames = new Image[] {null};
	}

	public Animation(Image[] frames) {
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
	
	public double getFrameWidth() {
		return frames[frameIndex].getWidth();
	}
	
	public double getFrameHeight() {
		return frames[frameIndex].getHeight();
	}
	
	public void render(GraphicsContext canvas, int x, int y) {
		canvas.drawImage(frames[frameIndex], x, y);
	}
	
	public void reset() {
		frameIndex = 0;
	}
}
