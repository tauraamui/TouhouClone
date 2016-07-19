package utils.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.CHAR_ARRAY;

import game.entities.mobs.character.TouhouCharacter;
import game.entities.mobs.character.TouhouCharacter.CHARACTER_TYPE;
import graphics.animation.Animation;
import utils.graphics.TileSheet;

public class ResourceHandler {

	public static TileSheet ReimuHakureiSpriteSheet;
	private static TileSheet ReimuHakureiIdleSpriteSheet;
	private static TileSheet ReimuHakureiRightSpriteSheet;

	public static Animation ReimuHakureiMovingForwardAnimation;
	public static Animation ReimuHakureiMovingRightAnimation;
	public static Animation ReimuHakureiMovingLeftAnimation;

	public static Animation loadCharacterMovingForwardAnimation(TouhouCharacter character, TileSheet spriteSheet) {
		BufferedImage[] MovingForwardFrames = new BufferedImage[] {spriteSheet.getSprite(0, 0),
				spriteSheet.getSprite(1, 0),
				spriteSheet.getSprite(2, 0),
				spriteSheet.getSprite(3, 0)};
		return new Animation(MovingForwardFrames);
	}

	public static Animation loadCharacterMovingRightAnimation(TouhouCharacter character, TileSheet spriteSheet) {
		BufferedImage[] MovingRightFrames = new BufferedImage[] {spriteSheet.getSprite(0, 1),
				spriteSheet.getSprite(1, 1),
				spriteSheet.getSprite(2, 1),
				spriteSheet.getSprite(3, 1)};
		return new Animation(MovingRightFrames);
	}

	public static Animation loadCharacterMovingLeftAnimation(TouhouCharacter character, TileSheet spriteSheet) {
		BufferedImage[] movingLeftFrames = new BufferedImage[] {spriteSheet.getSprite(0, 2),
				spriteSheet.getSprite(1, 2),
				spriteSheet.getSprite(2, 2),
				spriteSheet.getSprite(3, 2)};
		return new Animation(movingLeftFrames);
	}

	public static BufferedImage loadStageTitleScreenBackground(String path) {
		try {
			return ImageIO.read(ResourceHandler.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
