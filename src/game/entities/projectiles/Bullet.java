package game.entities.projectiles;

import game.Game;
import game.entities.Entity;
import game.physics.AABB;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.Vector2;

public class Bullet extends Projectile {
	
	private Color color = Color.CYAN;
	
	public Bullet(float x, float y, Vector2 direction, Entity firingEntity) {
		super(x, y);
		this.direction = direction;
		setWidth(8);
		setHeight(8);
		setBoundingBox(new AABB((int)X, (int)Y, getWidth(), getHeight()));
		this.firingEntity = firingEntity;
	}
	
	public void setFiringEntity(Entity entity) {
		firingEntity = entity;
	}
	
	public void setDir(Vector2 direction) {
		this.direction = direction;
	}
	
	public Bullet setColor(Color color) {
		this.color = color;
		return this;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		checkWithinStageBounds();
	}
	
	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.setFill(color);
		graphicsContext.fillOval(X, Y, (int)getWidth(), (int)getHeight());
		if (Game.debugMode) {
			getBoundingBox().render(graphicsContext);
		}
	}
}
