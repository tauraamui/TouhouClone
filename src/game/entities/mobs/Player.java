package game.entities.mobs;

import game.Game;
import game.entities.Entity;
import game.entities.mobs.character.TouhouCharacter;
import game.entities.mobs.character.ReimuHakurei;
import game.entities.projectiles.Bullet;
import game.entities.projectiles.Projectile;
import game.stage.Stage;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.Vector2;

public class Player extends Mob {

	private TouhouCharacter character = new ReimuHakurei();
	private int powerUpLevel = 1;
	private long lastFired = System.currentTimeMillis();
	private int hitboxDiametre = 10;
	private static enum dir {
		FORWARD, RIGHT, LEFT, DOWN;
	};
	private dir currentDir = dir.FORWARD;
	private ArrayList<Bullet> bulletsWithinBounds = new ArrayList<Bullet>();

	public Player(float x, float y, Stage stage) {
		super(x, y, stage);
		character.loadRes();
		animation = character.movingForwardAnimation;
		//setWidth((int)character.sprite.getWidth());
		//setHeight((int)character.sprite.getHeight());
		hitboxDiametre = getWidth()/2;
		mobType = MobType.PLAYER;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		/*
		if (!Game.gameinput.Keyboard.AnyKey) {
			currentDir = dir.FORWARD;
		}

		if (Game.gameinput.Keyboard.Up.isDown) {
			velocityGoal.Y = -character.velocityScale;
			currentDir = dir.FORWARD;
		}

		if (Game.gameinput.Keyboard.Left.isDown) {
			velocityGoal.X = -character.velocityScale;
			currentDir = dir.LEFT;
		}

		if (Game.gameinput.Keyboard.Right.isDown) {
			velocityGoal.X = character.velocityScale;
			currentDir = dir.RIGHT;
		}

		if (Game.gameinput.Keyboard.Down.isDown) {
			velocityGoal.Y = character.velocityScale;
			currentDir = dir.DOWN;
		}

		if (Game.gameinput.Keyboard.Space.isDown) {
			if (System.currentTimeMillis()-lastFired >= character.firingDelay) {
				fire();
				lastFired = System.currentTimeMillis();
			}
		}

		if (!Game.gameinput.Keyboard.Up.isDown && !Game.gameinput.Keyboard.Right.isDown
				&& !Game.gameinput.Keyboard.Left.isDown && !Game.gameinput.Keyboard.Down.isDown) {
			currentDir = dir.FORWARD;
		}
		*/

		//move(direction, deltaTime);
		
		checkWindowBoundries();

		switch (currentDir) {
		case FORWARD:
			//			animation.reset();
			animation = character.movingForwardAnimation;
			break;
		case RIGHT:
			//			animation.reset();
			animation = character.movingRightAnimation;
			break;
		case LEFT:
			//			animation.reset();
			animation = character.movingLeftAnimation;
			break;
		default:
			break;
		}
		velocityGoal = new Vector2();
	}
	
	@Override
	public void move(Vector2 dir, float deltaTime) {
		dir.X = approach(velocityGoal.X, direction.X, deltaTime*character.velocityScale);
		dir.Y = approach(velocityGoal.Y, direction.Y, deltaTime*character.velocityScale);
		X += dir.X;
		Y += dir.Y;
	}
	
	@Override
	public void hit(Projectile projectile, Entity firingEntity) {
		if (projectile instanceof Bullet) {
			if (firingEntity instanceof Enemy) {
				if (bulletIntersectsHitOval((Bullet)projectile)) {
					healthManager.decreaseHealth(1);
				}
			}
		}
	}
	
	public boolean bulletIntersectsHitOval(Bullet bullet) {
		if (bullet == null) {return false;}
		boolean intersects = true;
		Vector2 playerCenter = getCenter();
		Vector2 bulletCenter = bullet.getCenter();
		if (bulletCenter.X < playerCenter.X-(hitboxDiametre/2)) intersects = false;
		if (bulletCenter.Y < playerCenter.Y-(hitboxDiametre/2)) intersects = false;
		if (bulletCenter.X > playerCenter.X+(hitboxDiametre/2)) intersects = false;
		if (bulletCenter.Y > playerCenter.Y+(hitboxDiametre/2)) intersects = false;
		return intersects;
	}

	public void checkWindowBoundries() {
		if (X < 0) X = 0;
		if (Y < 0) Y = 0;
		if (X > Stage.Width-getWidth()) X = Stage.Width-getWidth();
		if (Y > Stage.Height-(getHeight()*2)) Y = Stage.Height-(getHeight()*2);
	}

	public void fire() {
		if (powerUpLevel <= 1) {
			Vector2[] smallBulletVectors = new Vector2[] {new Vector2(-1, -1), new Vector2(0,-1), new Vector2(1,-1)};
			for (int i = 0; i < smallBulletVectors.length; i++) {
				Game.stageManager.getCurrentStage().addBullet(new Bullet(X+getWidth()/2, Y+getHeight()/2, smallBulletVectors[i].scale(2), this).setColor(Color.WHITE));
			}
		}
	}

	public void render(GraphicsContext graphicsContext) {
		animation.render(graphicsContext, (int)X, (int)Y);
		animation.update();
		//graphicsContext.setFill(Color.CYAN);
		//graphicsContext.fillRect(X, Y, getWidth(), getHeight());
//		setWidth((int) animation.getFrameWidth());
//		setHeight((int) animation.getFrameHeight());
		if (Game.debugMode) {
//			canvas.setColor(Color.RED);
//			canvas.drawRect((int)X, (int)Y, Width, Height);
			graphicsContext.setFill(Color.YELLOW);
			graphicsContext.fillOval((int)getCenter().X-hitboxDiametre/2, (int)getCenter().Y-hitboxDiametre/2, hitboxDiametre, hitboxDiametre);
			getBoundingBox().render(graphicsContext);
		}
	}
}
