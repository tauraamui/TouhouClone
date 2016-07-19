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

import game.physics.AABB;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;

public class ImageTools {

	public static BufferedImage invertColors(final BufferedImage imagetoinvert) {
		
		final short[] inverttable = new short[256];
		for (int i = 0; i < inverttable.length; i++) {
			inverttable[i] = (short)(255-i);
		}
		
		final int srcwidth = imagetoinvert.getWidth();
		final int srcheight = imagetoinvert.getHeight();
		
		final BufferedImage outputimage = new BufferedImage(srcwidth, srcheight, BufferedImage.TYPE_INT_RGB);
		final BufferedImageOp invertoperation = new LookupOp(new ShortLookupTable(0, inverttable), null);
		return invertoperation.filter(imagetoinvert, outputimage);
	}

	public static BufferedImage makeColorTransparent(BufferedImage image, final Color color) {
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				if (image.getRGB(x, y) == color.getRGB()) {
					image.setRGB(x, y, 0x00FFFFFF);
				}
			}
		}
		return image;
	}

	public static BufferedImage replaceColor(BufferedImage image, final  Color colorToReplace, final Color colorToReplaceWith) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (image.getRGB(x, y) == colorToReplace.getRGB()) {
					image.setRGB(x, y, colorToReplaceWith.getRGB());
				}
			}
		}
		return image;
	}

	public static boolean intersects(AABB bb1, AABB bb2, BufferedImage img1, BufferedImage img2) {
        if (!bb2.intersects(bb1)) return false;
         
        AABB union = bb1.union(bb2);
         
        for (int height=0, y1=union.position.y-bb1.position.y, y2=union.position.y-bb2.position.y; height<union.size.y; height++, y1++, y2++) {
            for (int width=0, x1=union.position.x-bb1.position.x, x2=union.position.x-bb2.position.x; width<union.size.x; width++, x1++, x2++) {
                if ((img1.getRGB(x1, y1) & 0xFF000000) == 0xFF000000 && (img2.getRGB(x2, y2) & 0xFF000000) == 0xFF000000)
                    return true;
            }
        }
        return false;
         
    }
}
