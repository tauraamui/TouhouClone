/*
DON'T BE A DICK PUBLIC LICENSE TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

Do whatever you like with the original work, just don't be a dick.

Being a dick includes - but is not limited to - the following instances:

1a. Outright copyright infringement - Don't just copy this and change the name.
1b. Selling the unmodified original with no work done what-so-ever, that's REALLY being a dick.
1c. Modifying the original work to contain hidden harmful content. That would make you a PROPER dick.

If you become rich through modifications, related works/services, or supporting the original work, share the love. Only a dick would make loads off this work and not buy the original work's creator(s) a pint.

Code is provided with no warranty. Using somebody else's code and bitching when it goes wrong makes you a DONKEY dick. Fix the problem yourself. A non-dick would submit the fix back.
*/

package utils.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class TileSheet {
	public BufferedImage SpriteSheet;
   
	private int spritewidth, spriteheight, MarginX, MarginY;
	public int Rows, Columns;
   
	public TileSheet(int width, int height, int margin, InputStream iostream) {
		try {
			if (iostream != null) {
				SpriteSheet = ImageIO.read(iostream);
				ImageTools.makeColorTransparent(SpriteSheet, new Color(SpriteSheet.getRGB(1, 1)));
//				BufferedImage SpriteSheetTemp = new BufferedImage(SpriteSheet.getWidth(), SpriteSheet.getHeight(), BufferedImage.TYPE_INT_ARGB);
//				for (int x = 0; x < SpriteSheet.getWidth(); x++) {
//					for (int y = 0; y < SpriteSheet.getHeight(); y++) {
//						if (SpriteSheet.getRGB(x, y) == 0) {
//							SpriteSheetTemp.setRGB(x, y, 0xFF000000);
//						} else {
//							SpriteSheetTemp.setRGB(x, y, SpriteSheet.getRGB(x, y));
//						}
//					}
//				}
//				SpriteSheet = SpriteSheetTemp;
				spritewidth = width;
				spriteheight = height;
				MarginX = MarginY = margin;
				width = SpriteSheet.getWidth();
				height = SpriteSheet.getHeight();
				Rows = (int)Math.floor(height/(spriteheight+margin));
				Columns = (int)Math.floor(width/(spritewidth+margin));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TileSheet(int width, int height, int margin, String url) throws IOException {
		SpriteSheet = ImageIO.read(new File(url));
		BufferedImage SpriteSheetTemp = new BufferedImage(SpriteSheet.getWidth(), SpriteSheet.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < SpriteSheet.getWidth(); x++) {
			for (int y = 0; y < SpriteSheet.getHeight(); y++) {
				if (SpriteSheet.getRGB(x, y) == 0) {
					SpriteSheetTemp.setRGB(x, y, 0xFF000000);
				} else {
					SpriteSheetTemp.setRGB(x, y, SpriteSheet.getRGB(x, y));
				}
			}
		}
		SpriteSheet = SpriteSheetTemp;
		spritewidth = width;
		spriteheight = height;
		MarginX = MarginY = margin;
		width = SpriteSheet.getWidth();
		height = SpriteSheet.getHeight();
		Rows = (int)Math.floor(height/(spriteheight+margin));
		Columns = (int)Math.floor(width/(spritewidth+margin));
	}
	public TileSheet(int width, int height, int marginx, int marginy, String url) throws IOException {
		SpriteSheet = ImageIO.read(new File(url));
		BufferedImage SpriteSheetTemp = new BufferedImage(SpriteSheet.getWidth(), SpriteSheet.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < SpriteSheet.getWidth(); x++) {
			for (int y = 0; y < SpriteSheet.getHeight(); y++) {
				if (SpriteSheet.getRGB(x, y) == 0) {
					SpriteSheetTemp.setRGB(x, y, 0xFF000000);
				} else {
					SpriteSheetTemp.setRGB(x, y, SpriteSheet.getRGB(x, y));
				}
			}
		}
		SpriteSheet = SpriteSheetTemp;
		spritewidth = width;
		spriteheight = height;
		MarginX = marginx;
		MarginY = marginy;
		width = SpriteSheet.getWidth();
		height = SpriteSheet.getHeight();
		Rows = (int)Math.floor(height/(spriteheight+marginx));
		Columns = (int)Math.floor(width/(spritewidth+marginy));
	}
	
	public BufferedImage getSprite(int x, int y) {
		if (x >= 0 && x < Columns && y >= 0 && y < Rows) return SpriteSheet.getSubimage((x * spritewidth) + MarginX*x, (y * spriteheight) + MarginY*y, spritewidth, spriteheight);
		return null;
	}	
}