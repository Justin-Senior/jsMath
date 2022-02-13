package main;

import java.math.BigInteger;

import jsMath.functions.Function1;
import jsMath.matrices.Matrix2f;
import jsMath.matrices.Matrix2i;
import jsMath.matrices.Matrix3c;
import jsMath.matrices.Matrix3f;
import jsMath.matrices.MatrixNd;
import jsMath.probability.Probability;
import jsMath.twoD.Cubic;
import jsMath.twoD.LinearEq;
import jsMath.twoD.Point2D;
import jsMath.twoD.Quadratic;
import jsMath.types.Complex;
import jsMath.vectors.VectorND;


public class Main {

	//Simple testing of functions
	public static void main(String[] args) throws Exception {
		
		Complex c = new Complex(1,2);
		Complex c1 = new Complex(3,1);
		Complex c2= new Complex(5,2);
		Complex c3 = new Complex(1,1);
		Complex c4 = new Complex(1,3);
		Complex c5 = new Complex(4,1);
		Complex c6 = new Complex(3,2);
		Complex c7 = new Complex(1,2);
		Complex c8 = new Complex(1,4);
		
		Complex[][] cm = {{c,c1,c2},{c3,c4,c5},{c6,c7,c8}};
		
		
		Matrix3f m1 = Matrix3f.genRandomMatrix();
		Matrix3f m4 = m1.inverse();

		Matrix3f m8 = m1.gaussElim();
	
		Matrix2f m2 = Matrix2f.genRandomMatrix();
		Matrix2f m3 = m2.inverse();
		
		Matrix3f m9 = m1.gaussElim();
		
		MatrixNd m5 = MatrixNd.genRandomMatrix(4);
		MatrixNd m6 = m5.inverse();
		
		Matrix3c m10 = new Matrix3c(cm);
		
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
		
		System.out.println(Probability.binomial(1/2, 4, 3));

	
		double[] vec1 = {1,2,1};
		double[] vec2 = {2,9,0};
		double[] vec3 = {3,3,4};
		VectorND v1 = new VectorND(vec1);
		VectorND v2 = new VectorND(vec2);
		VectorND v3 = new VectorND(vec3);
		
		System.out.println(v1.size);
		
		VectorND[] vl = {v1, v2, v3};
		
		assert (VectorND.isBasis(vl));
		
		System.out.println(v2.toString());
		
	
	}
	
}
