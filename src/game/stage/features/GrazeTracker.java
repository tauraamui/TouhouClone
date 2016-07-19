package game.stage.features;

import game.Game;
import game.entities.mobs.Player;
import game.entities.projectiles.Bullet;
import game.stage.Stage;

import java.util.ArrayList;

public class GrazeTracker {

	private Stage stage;
	private Player player;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Bullet> bulletsWhichIntersectedHitOval = new ArrayList<Bullet>();
	
	public void addBullet(Bullet bullet) {
		if (bullet == null) return;
		if (!bullets.contains(bullet)) {
			bullets.add(bullet);
		}
	}
	
	public void update() {
		Player player = Game.stageManager.getPlayer();
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			//if bullet intersects with player's bounds
			if (player.getBoundingBox().intersects(bullet.getPoint())) {
				//if bullet intersects with player's hit area
				if (player.bulletIntersectsHitOval(bullet)) {
					//if not already in list of bullets intersecting player's hit area
					if (!bulletsWhichIntersectedHitOval.contains(bullet)) {
						bulletsWhichIntersectedHitOval.add(bullet);
					}
				}
			} else {
				//if the bullet does not intersect with the players hit area
				if (!bulletsWhichIntersectedHitOval.contains(bullet)) {
					Game.stageManager.getCurrentStage().grazePoints++;
				}
				bullets.remove(bullet);
				bulletsWhichIntersectedHitOval.remove(bullet);
			}
		}
	}
	
	//Does nothing
	public void Nothing() {
	}
}
