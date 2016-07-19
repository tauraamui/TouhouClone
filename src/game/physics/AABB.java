package game.physics;

import game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class AABB {

	public Position position, size;

	public AABB(int x, int y, int width, int height) {
		this(new Position(x, y), new Position(width, height));
	}
	
	public AABB(Position pos, Position s) {
		position = pos;
		size = s;
	}

	public Position getMax() { 
		return new Position(position.x+size.x, position.y+size.y);
	}
	
	public AABB expand(AABB box) {
		Position
		boxmax = box.getMax(),
		thismax = getMax(),
		max = new Position(Math.max(thismax.x, boxmax.x), Math.max(thismax.y, boxmax.y)),
		min = new Position(Math.min(box.position.x, position.x), Math.min(box.position.y, position.y)),
		size = new Position(max.x-min.x, max.y-min.y);
		
		return new AABB(min, size);
	}

	public boolean intersects(AABB box) {
		Position
		thismax = getMax(),
		thatmax = box.getMax();
		if(box.position.x > thismax.x) return false;
		if(box.position.y > thismax.y) return false;
		if(thatmax.x < this.position.x) return false;
		if(thatmax.y < this.position.y) return false;
		
		return true;
	}
	
	public boolean intersects(Position point) {
		Position 
		thismax = getMax();
		if (point.x > thismax.x) return false;
		if (point.y > thismax.y) return false;
		if (point.x < position.x) return false;
		if (point.y < position.y) return false;
		return true;
	}
	
	public AABB union(AABB box) {
		if (!intersects(box)) return null;
		
		Position
		thismax = getMax(),
		boxmax = box.getMax(),
		base = new Position(Math.max(box.position.x, position.x), Math.max(box.position.y, position.y)),
		max = new Position(Math.min(boxmax.x, thismax.x), Math.min(boxmax.y, thismax.y));
		return new AABB(new Position(base.x, base.y), new Position(max.x-base.x, max.y-base.y));
	}
	
	@Override
	public String toString() {
		return "[X: " + position.x + ", Y: " + position.y + ", width: " + size.x + ", height: " + size.y;
	}
	
	public AABB clone() {
		return new AABB(position.Clone(), size.Clone());
	}
	
	public void render(Canvas canvas) {
		canvas.getGraphicsContext2D().setFill(Color.RED);
		canvas.getGraphicsContext2D().fillRect(position.x, position.y, size.x, size.y);
	}
}