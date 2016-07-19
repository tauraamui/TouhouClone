package game.entities.projectiles;

import utils.Vector2;
import game.Game;
import game.entities.Entity;
import game.stage.Stage;

public class Projectile extends Entity {

	protected Entity firingEntity = null;
	protected Vector2 direction = new Vector2();

	public Projectile(float x, float y) {
		super(x, y);
	}
	
	@Override
	public void update(float deltaTime) {
		move(direction, deltaTime);
	}
	
	public Entity getFiringEntity() {
		return firingEntity;
	}
	
	@Override
	public void checkWithinStageBounds() {
		if ((int)X+getBoundingBox().size.x < 0 || (int)X > Stage.Width) Game.stageManager.removeEntity(this);
		if ((int)Y+getBoundingBox().size.y < 0 || (int)Y > Stage.Height) Game.stageManager.removeEntity(this);
	}
}
