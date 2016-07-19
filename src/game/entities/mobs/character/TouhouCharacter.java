package game.entities.mobs.character;

import graphics.animation.Animation;
import javafx.scene.image.Image;
import utils.graphics.TileSheet;
import utils.resources.ResourceHandler;

import java.awt.image.BufferedImage;

import game.entities.mobs.character.TouhouCharacter.CHARACTER_TYPE;

public abstract class TouhouCharacter {
	
	public static enum CHARACTER_TYPE {
		GENERIC,
		REIMU_HAKUREI;
	}

	public Image sprite;
	public TileSheet spriteSheet;
	public Animation movingForwardAnimation;
	public Animation movingRightAnimation;
	public Animation movingLeftAnimation;
	public int MAXHEALTH = 100;
	public int hitboxSize = 5;
	public float velocityScale = 1F;
	public long firingDelay = 100;
	public CHARACTER_TYPE type;
	
	public void loadRes() {
		movingForwardAnimation = ResourceHandler.loadCharacterMovingForwardAnimation(this, spriteSheet);
		movingLeftAnimation = ResourceHandler.loadCharacterMovingLeftAnimation(this, spriteSheet);
		movingRightAnimation = ResourceHandler.loadCharacterMovingRightAnimation(this, spriteSheet);
	}
}
