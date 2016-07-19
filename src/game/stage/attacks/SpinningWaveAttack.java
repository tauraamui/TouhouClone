package game.stage.attacks;

import game.entities.mobs.Mob;

public class SpinningWaveAttack extends Attack {

	public SpinningWaveAttack(Mob attackingMob) {
		super(attackingMob);
	}

	public SpinningWaveAttack(Mob attackingMob, Mob optionalTargetMob) {
		this(attackingMob);
		this.optionalTargetMob = optionalTargetMob;
	}
	
	@Override
	public void update(float deltaTime) {
		
	}
}
