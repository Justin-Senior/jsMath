package jsMath.twoD;

public class Point2D {
	
	public float x;
	public float y;
	//A class representjing a point in 2d space
	public Point2D(float x, float y) {
		this.y = y;
		this.x = x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getX() {
		return x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getY() {
		return y;
	}
	//distance between 2 points
	public static float distance(Point2D a, Point2D b) {
		float x2 = (float) Math.pow(a.x - b.x, 2);
		float y2 = (float) Math.pow(a.y - b.y, 2);
		float c = (float) Math.sqrt(x2 + y2);
		
		return c;
	}

}
