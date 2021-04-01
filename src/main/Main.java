package main;

import java.math.BigInteger;

import jsMath.functions.Function1;
import jsMath.matrices.Matrix2i;
import jsMath.probability.Probability;
import jsMath.twoD.LinearEq;
import jsMath.twoD.Point2D;
import jsMath.twoD.Quadratic;
import jsMath.twoD.Vector2D;


public class Main {

	//Simple testing of functions
	public static void main(String[] args) throws Exception {

		int [] top = {5,8};
		int [] bot = {3,8};
		
		int[] top1 = {3,8};
		int[] bot1 = {8,9};
		
		Matrix2i m1 = new Matrix2i(top, bot);
		Matrix2i m2 = new Matrix2i(top1, bot1);
		
		Matrix2i m3 = Matrix2i.multiply(m1,m2);
		
		System.out.println(m2.determinant());
			
	}
	
}
