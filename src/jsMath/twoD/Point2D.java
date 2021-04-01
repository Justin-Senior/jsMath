package jsMath.twoD;

public class Point2D {
	
	public double x;
	public double y;
	//A class representjing a point in 2d space
	public Point2D(double x, double y) {
		this.y = y;
		this.x = x;
	}
	
	public void setX(double x2) {
		this.x = x2;
	}
	
	public double getX() {
		return x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getY() {
		return y;
	}
	//distance between 2 points
	public static double distance(Point2D a, Point2D b) {
		double x2 = (double) Math.pow(a.x - b.x, 2);
		double y2 = (double) Math.pow(a.y - b.y, 2);
		double c = (double) Math.sqrt(x2 + y2);
		
		return c;
	}

}
