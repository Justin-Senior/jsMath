package main;

import java.math.BigInteger;

import jsMath.functions.Function1;
import jsMath.matrices.Matrix2f;
import jsMath.matrices.Matrix2i;
import jsMath.matrices.Matrix3f;
import jsMath.probability.Probability;
import jsMath.twoD.LinearEq;
import jsMath.twoD.Point2D;
import jsMath.twoD.Quadratic;
import jsMath.twoD.Vector2D;


public class Main {

	//Simple testing of functions
	public static void main(String[] args) throws Exception {

		float [] top = {5,8,3};
		float [] mid = {1,2,3};
		float [] bot = {3,8,5};
		
		float[] top1 = {3,8};
		float[] bot1 = {8,9};
		
		Matrix3f m1 = new Matrix3f(top, mid, bot);
		Matrix3f m4 = m1.inverse();
		
		Matrix2f m2 = new Matrix2f(top1, bot1);
		Matrix2f m3 = m2.inverse();
		
		assert (Matrix2f.multiply(m2, m3).approxEqual(Matrix2f.id, 0.001));
		assert (Matrix2f.multiply(m3, m2).approxEqual(Matrix2f.id, 0.001));
		
		assert (Matrix3f.multiply(m1, m4).approxEqual(Matrix3f.id, 0.001));
		assert (Matrix3f.multiply(m4, m1).approxEqual(Matrix3f.id, 0.001));
		
		System.out.println(m2.eigenvalues()[0] + "," + m2.eigenvalues()[1]);
	}
	
}
