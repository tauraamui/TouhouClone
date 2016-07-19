package game.entities.mobs.character;

import utils.graphics.TileSheet;
import utils.resources.ResourceHandler;

public class ReimuHakurei extends TouhouCharacter {
	
	public TileSheet idleSpriteSheet = new TileSheet(26, 44, 0, ResourceHandler.class.getResourceAsStream("/Resources/Characters/ReimuHakureiIdleAni.png"));
	
	public ReimuHakurei() {
		velocityScale = 2.75F;
		MAXHEALTH = 600;
		hitboxSize = 5;
	}
	
	@Override
	public void loadRes() {
		spriteSheet = new TileSheet(27, 45, 3,  ResourceHandler.class.getResourceAsStream("/Resources/Characters/ReimuHakurei.png"));
		sprite = spriteSheet.getSprite(0, 0);
		super.loadRes();
		movingForwardAnimation = ResourceHandler.loadCharacterMovingForwardAnimation(this, idleSpriteSheet);
	}
}
