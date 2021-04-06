package jsMath.twoD;

import jsMath.exceptions.RootsException;

public class Quadratic {
	
	public float a;
	public float b;
	public float c;
	//ax^x + bx + c
	public Quadratic(float a, float b, float c) {
		
		this.a = a;
		this.b = b;
		this.c = c;
		
	}
	//Returns a point for the corresponding x value.
	public Point2D getPoint(float x) {
		
		float y = a*x*x + b*x + c;
		Point2D p = new Point2D(x, y);
		return p;
		
	}
	//Return the derivative of the quadratic as a linear equation
	public static LinearEq derivative(Quadratic q) {
		float la = 2*q.a;
		float lb = q.b;
		LinearEq l1 = new LinearEq(la, lb);
		return l1;
	}
	//Return the slope of the line at given x value
	public float slopeAt(float x) {
		float la = 2*x*a;
		float lb = b;
		return la + lb;
	}
	@Override
	public String toString() {
		String s = a + "x^2 + " + b + "x + " + c;
		return s;
	}
	//Find the roots of a quadratic equation if they exist, throws an exception if no roots exist, if only one root exists 
	// result is stored in both array indicies
	public float[] roots() throws RootsException{
		float[] roots = new float[2];
		
		float det = (b*b - 4*a*c);
		
		if ((b*b - 4*a*c) < 0) {
			throw new RootsException("No roots");
		}
		if ((b*b - 4*a*c) == 0) {
			roots[0] = -b / 2*a;
			roots[1] = -b / 2*a;
		}
		else {
			roots[0] = (float) (-b + Math.sqrt(det))/(2*a);
			roots[1] = (float) (-b - Math.sqrt(det))/(2*a);
		}
		
		return roots;
	}
	//Find the area under a quadratic from x = 1 to x = u where l is the lower bound and u is the upper bound
	public float integral(float l, float u) {
		
		float t1 = (a*u*u*u)/3-a*l*l*l/3;
		float t2 = b*u*u/2-b*l*l/2;
		float t3 = c*u - c*l;

		return t1 + t2 + t3;
	}

}
