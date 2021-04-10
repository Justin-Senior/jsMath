package main;

import java.math.BigInteger;

import jsMath.functions.Function1;
import jsMath.matrices.Matrix2f;
import jsMath.matrices.Matrix2i;
import jsMath.matrices.Matrix3f;
import jsMath.matrices.MatrixNd;
import jsMath.probability.Probability;
import jsMath.twoD.Cubic;
import jsMath.twoD.LinearEq;
import jsMath.twoD.Point2D;
import jsMath.twoD.Quadratic;
import jsMath.twoD.Vector2D;


public class Main {

	//Simple testing of functions
	public static void main(String[] args) throws Exception {

		
		Matrix3f m1 = Matrix3f.genRandomMatrix();
		Matrix3f m4 = m1.inverse();

		Matrix3f m8 = m1.gaussElim();
	
		Matrix2f m2 = Matrix2f.genRandomMatrix();
		Matrix2f m3 = m2.inverse();
		
		Matrix3f m9 = m1.gaussElim();
		
		MatrixNd m5 = MatrixNd.genRandomMatrix(4);
		MatrixNd m6 = m5.inverse();
		
		assert (Matrix2f.multiply(m2, m3).approxEqual(Matrix2f.id(), 0.001));
		assert (Matrix2f.multiply(m3, m2).approxEqual(Matrix2f.id(), 0.001));
		
		assert (Matrix3f.multiply(m1, m4).approxEqual(Matrix3f.id(), 0.001));
		assert (Matrix3f.multiply(m4, m1).approxEqual(Matrix3f.id(), 0.001));

		assert (MatrixNd.multiply(m5, m6).approxEqual(MatrixNd.id(4), 0.001));
		assert (MatrixNd.multiply(m6, m5).approxEqual(MatrixNd.id(4), 0.001));
		
		assert(m2.multiply(m3).approxEqual(Matrix2f.id(), 0.001));
		assert(m3.multiply(m2).approxEqual(Matrix2f.id(), 0.001));
		
		assert(m1.multiply(m4).approxEqual(Matrix3f.id(), 0.001));
		assert(m4.multiply(m1).approxEqual(Matrix3f.id(), 0.001));
		
		assert(m2.multiply(m3).approxEqual(Matrix2f.multiply(m2, m3), 0.001));
		assert(m3.multiply(m2).approxEqual(Matrix2f.id(), 0.001));
		
		assert(m8.isUpperTriangular());
		assert(m9.isUpperTriangular());
	}
	
}
