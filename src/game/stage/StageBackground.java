package game.stage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class StageBackground extends BufferedImage {

	public StageBackground(int width, int height, int imageType) {
		super(width, height, imageType);
	}

	public StageBackground(int width, int height, int imageType, IndexColorModel cm) {
		super(width, height, imageType, cm);
	}

	public StageBackground(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied,
			Hashtable<?, ?> properties) {
		super(cm, raster, isRasterPremultiplied, properties);
	}

	public void update(float deltaTime) {
		
	}
	
	public void render(Graphics canvas) {
		
	}
}
