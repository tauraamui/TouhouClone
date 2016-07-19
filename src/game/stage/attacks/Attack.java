package game.stage.attacks;

import game.Game;
import game.entities.mobs.Mob;
import game.entities.projectiles.Projectile;
import game.entities.projectiles.ProjectileEmitter;

import java.util.ArrayList;

import utils.Vector2;

public class Attack {

	protected Mob attackingMob;
	protected Mob optionalTargetMob;
	protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	protected long attackStarted = 0;
	protected long currentAttackTimeSpan = 0;
	
	public Attack(Mob attackingMob) {
		this.attackingMob = attackingMob;
	}
	
	public Attack(Mob attackingMob, Mob optionalTargetMob) {
		this(attackingMob);
		this.optionalTargetMob = optionalTargetMob;
	}
	
	public void setOptionalTargetMob(Mob optionalTargetMob) {
		this.optionalTargetMob = optionalTargetMob;
	}
	
	public void setAttackingMob(Mob attackingMob) {
		this.attackingMob = attackingMob;
	}
	
	public Mob getAttackingMob() {
		return attackingMob;
	}
	
	public Mob getOptionalTargetMob() {
		return optionalTargetMob;
	}
	
	public void update(float deltaTime) {}
	
	public void addProjectile(Projectile projectile) {
		if (projectile == null) return;
		projectiles.add(projectile);
		Game.stageManager.addEntity(projectile);
	}
	
	public void removeProjectile(Projectile projectile) {
		if (projectile == null) return;
		if (projectiles.contains(projectile)) projectiles.remove(projectile);
		Game.stageManager.removeEntity(projectile);
	}
	
	public void fire() {
		Vector2 centerPoint = attackingMob.getCenterPoint();
		ProjectileEmitter projectileEmitter = new ProjectileEmitter(centerPoint.X, centerPoint.Y);
		projectileEmitter.setFiringEntity(attackingMob);
		Game.stageManager.getCurrentStage().addProjectileEmitter(projectileEmitter);
	}
}
