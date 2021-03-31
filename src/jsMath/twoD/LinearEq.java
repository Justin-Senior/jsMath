package jsMath.twoD;

public class LinearEq {
	
	public float a;
	public float b;
	//A class representing a linear equation of the form ax + b
	public LinearEq(float a, float b) {
		this.a = a;
		this.b = b;
	}
	//Get the point at the given x value
	public Point2D getPoint(float xVal) {
		float y = (a*xVal + b);
		Point2D p = new Point2D(xVal, y);
		return p;
	}
	//Find the intersection point of 2 lines
	public static Point2D intersection(LinearEq l1, LinearEq l2) throws Exception {
		
		if (l1.a == l2.a) {
			throw new Exception("Invlaid lines: same slope");
		}
		
		float x = (l1.b - l2.b)/(l2.a-l1.a);
		float y = (l1.a*x) + l1.b;
		
		Point2D p = new Point2D(x,y);
		
		return p;
	}
	
	public float getSlope() {
		return a;
	}
	
	public float getYInt() {
		return b;
	}
	
	//Returns the angle between 2 linear equations (unfinished, right now the result is not consistent on whether
	// the returned angle is acute or obtuse positive or negative.)
	public static float angle(LinearEq l1, LinearEq l2) {
		
		float theta1 = (float) Math.atan(l1.a);
		float theta2 = (float) Math.atan(l2.a);
		
		
		return (theta1 - theta2);
	}
	//Find the x-intercept of a line
	public static float xIntercept(LinearEq l1) {
		float x = -l1.b/l1.a;
		return x;
	}
	@Override
	public String toString() {
		String s = a + "x + "  + b;
		return s;
	}

}
