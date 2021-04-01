package main;

import java.math.BigInteger;

import jsMath.functions.Function1;
import jsMath.probability.Probability;
import jsMath.twoD.LinearEq;
import jsMath.twoD.Point2D;
import jsMath.twoD.Quadratic;
import jsMath.twoD.Vector2D;


public class Main {

	//Simple testing of functions
	public static void main(String[] args) throws Exception {
		Function1 f = new Function1('x', "cos(x)");
		System.out.println(f.evaluate(0).y);
		
	}
	
}
