package game.entities.mobs;

import game.entities.Entity;
import game.entities.projectiles.Projectile;
import game.stage.Stage;
import game.stage.attacks.Attack;

public class Enemy extends Mob {
	
	protected Attack attack = new Attack(this);

	public Enemy(float x, float y, Stage stage) {
		super(x, y, stage);
	}
	
	public void setAttack(Attack attack) {
		this.attack = attack;
	}
	
	public Attack getAttack() {
		return attack;
	}
	
	@Override
	public void hit(Projectile projectile, Entity firingEntity) {
		if (firingEntity instanceof Player) {
			stage.removeEntity(this);
		}
	}
}
