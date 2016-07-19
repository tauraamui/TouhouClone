package game.entities;

import game.Game;
import game.physics.AABB;
import game.physics.Position;
import graphics.animation.Animation;

import java.awt.Graphics;

import utils.Vector2;

public class Entity extends Vector2 {
	
	protected Animation animation;
	private int Width = 30;
	private int Height = 60;
	private AABB boundingBox;
	private Position point;

	public Entity(float x, float y) {
		super(x, y);
		boundingBox = new AABB((int)X, (int)Y, Width, Height);
	}
	
	public void setBoundingBox(AABB boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	public AABB getBoundingBox() {
		boundingBox.position.x = (int)X;
		boundingBox.position.y = (int)Y;
		return boundingBox;
	}
	
	protected void move(Vector2 dir, float deltaTime) {
		X += (dir.X * deltaTime);
		Y += (dir.Y * deltaTime);
	}
	
	public void render(Graphics canvas){
		if (Game.debugMode) {
			AABB boundingBox = getBoundingBox();
			boundingBox.render(canvas);
		}
	}
	
	protected void setWidth(int width) {
		this.Width = width;
		boundingBox = new AABB((int)X, (int)Y, Width, Height);
	}
	
	protected void setHeight(int height) {
		this.Height = height;
		boundingBox = new AABB((int)X, (int)Y, Width, Height);
	}
	
	public int getWidth() {return Width;}
	public int getHeight() {return Height;}
	
	public void update(float deltaTime){}
	protected void checkWithinStageBounds(){}
	public Position getPoint() {
		return new Position((int)X, (int)Y);
	}
	public Vector2 getCenter(){return new Vector2(X+Width/2, Y+Height/2);}
}
