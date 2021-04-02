package main;

import java.math.BigInteger;

import jsMath.functions.Function1;
import jsMath.matrices.Matrix2f;
import jsMath.matrices.Matrix2i;
import jsMath.probability.Probability;
import jsMath.twoD.LinearEq;
import jsMath.twoD.Point2D;
import jsMath.twoD.Quadratic;
import jsMath.twoD.Vector2D;


public class Main {

	//Simple testing of functions
	public static void main(String[] args) throws Exception {

		float [] top = {5,8};
		float [] bot = {3,8};
		
		float[] top1 = {3,8};
		float[] bot1 = {8,9};
		
		Matrix2f m1 = new Matrix2f(top, bot);
		Matrix2f m2 = new Matrix2f(top1, bot1);
		
		Matrix2f m3 = Matrix2f.multiply(m1,m2);
		Matrix2f m4 = m1.inverse();
		
		Matrix2f m5 = Matrix2f.multiply(m1, m4);
		
		System.out.println(m3.toString());
			
	}
	
}
