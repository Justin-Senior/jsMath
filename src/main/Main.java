package main;

import jsMath.twoD.LinearEq;
import jsMath.twoD.Point2D;
import jsMath.twoD.Quadratic;
import jsMath.twoD.Vector2D;


public class Main {

	public static void main(String[] args) throws Exception {
		Vector2D v1 = new Vector2D(2,4);
		Vector2D v2 = new Vector2D(4,1);
		
		Vector2D v3 = Vector2D.perp(v1);
		
		float dots = Vector2D.dot(v1, v3);
		
		System.out.println(dots);
		
		LinearEq l1 = new LinearEq(4,3);
		LinearEq l2 = new LinearEq(-1,0);
		
		Quadratic q1 = new Quadratic(2, 3, 0);
		
		
		System.out.println(q1.integral(0, 1));
	}
	
}
