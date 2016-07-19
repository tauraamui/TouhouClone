/*
DON'T BE A DICK PUBLIC LICENSE TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

Do whatever you like with the original work, just don't be a dick.

Being a dick includes - but is not limited to - the following instances:

1a. Outright copyright infringement - Don't just copy this and change the name.
1b. Selling the unmodified original with no work done what-so-ever, that's REALLY being a dick.
1c. Modifying the original work to contain hidden harmful content. That would make you a PROPER dick.

If you become rich through modifications, related works/services, or supporting the original work, share the love. Only a dick would make loads off this work and not buy the original work's creator(s) a pint.

Code is provided with no warranty. Using somebody else's code and bitching when it goes wrong makes you a DONKEY dick. Fix the problem yourself. A non-dick would submit the fix back.
*/

package utils;

public class Vector2 {

	public float X, Y;
	
	public Vector2() {
		X = 0.0F;
		Y = 0.0F;
	}
	
	public Vector2(float x, float y) {
		X = x;
		Y = y;
	}

	public float X() {
		return X;
	}

	public void setX(float f) {
		X = f;
	}

	public float Y() {
		return Y;
	}

	public Vector2 setY(float y) {
		Y = y;
		return this;
	}
	
	public Vector2 scale(float scale) {
		X *= scale;
		Y *= scale;
		return this;
	}
	
	public Vector2 add(Vector2 vector) {
		X += vector.X;
		Y += vector.Y;
		return this;
	}
	
	public float getMagnitude() {
		return (float)Math.sqrt(X * X + Y * Y);
	}
	
	public Vector2 invert() {
		X *= -1;
		Y *= -1;
		return this;
	}
	
	public Vector2 normalize() {
		float length = getMagnitude();
		X = X / length;
		Y = Y / length;
		return this;
	}
	
	public double toAngleInRad() {
		return Math.atan2(Y, X);
	}
	
	@Override
	public String toString() {
		return "X: "+X+" Y: "+Y;
	}
	
//	public Vector2 rotateAroundPoint(Vector2 point, double angle) {
//		X = (float)(point.X + (X-point.X)*Math.cos(angle) - (Y-point.Y)*Math.sin(angle));
//		Y = (float)(point.Y + (Y-point.Y)*Math.sin(angle) + (Y-point.Y)*Math.cos(angle));
//		return this;
//	}
	
	public Vector2 rotate(double radians) {
		rotate(new Vector2(), radians);
		return this;
	}
	
	public Vector2 rotate(Vector2 pos, double radians) {
		float s = (float)Math.sin(radians);
		float c = (float)Math.cos(radians);
		
		// translate point back to origin:
		X -= pos.X;
		Y -= pos.Y;
		
		// rotate point
		float xnew = X * c - Y * s;
		float ynew = X * s + Y * c;
		
		// translate point back:
		X = xnew + pos.X;
		Y = ynew + pos.Y;
		
		return this;
	}
}
