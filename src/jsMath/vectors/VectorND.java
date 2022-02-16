package jsMath.vectors;
import jsMath.exceptions.*;

import jsMath.matrices.MatrixNd;

public class VectorND {

	public int size;
	public double[] vector; 
		
	
	public VectorND(double[] v) {
		
		size = v.length;
		vector = v;
		
	}
	
	public void setPos(int x, double n) {
		vector[x] = n;
	}
	
	public double getPos(int x) {
		return vector[x];
	}
	
	public static VectorND addVectors(VectorND v1, VectorND v2) {
		
		double vec[] = new double[3]; 
		VectorND v3 = new VectorND(vec);
		
		if (v1.size != v2.size) { throw new VectorSizeException("Vectors must be of same length!");}
		
		for (int i = 0; i < v1.size; i++) {
			v3.setPos(i,v1.getPos(i) + v2.getPos(i));
		}
		
		return v3;
		
	}
	
	public static double dotProd(VectorND v1, VectorND v2) {
		
		double ret = 0;
		
		if (v1.size != v2.size) { throw new VectorSizeException("Vectors must be of same length!");}
		
		for (int i = 0;i < v1.size;i++) {
			ret += v1.getPos(i) * v2.getPos(i);
		}
		
		return ret;
	}
	
	public double length() {
		
		double ret = 0;
		
		for (int i = 0; i < this.size; i ++) {
			ret += this.vector[i] * this.vector[i];
		}
		
		return (Math.sqrt(ret));
	}
	
	public static VectorND scalarMult(double x, VectorND v) {
		
		double[] vec = new double[v.size];
		VectorND v2 = new VectorND(vec);
		
		for (int i = 0;i < v.size; i ++) {
			v2.setPos(i, v.getPos(i)*x);
		}
		
		return v2; 
	}
	
	public static VectorND normalize(VectorND v1) {
		
		double n = v1.length();
		
		VectorND v2 = scalarMult(1/n,v1);
		
		return v2;
		 
	}
	
	public static boolean isBasis(VectorND[] vl) {
		
		double[][] mat = new double[vl.length][vl.length];
		
		for (int i = 0; i < vl.length; i++) {
			if (vl.length != vl[i].size) { throw new VectorSizeException("Number of dimensions must match number of vectors!");}
		}
		
		for (int j = 0; j < vl.length; j++) {
			mat[j] = vl[j].vector;
		}
		
		MatrixNd ret = new MatrixNd(mat);
		
		System.out.println(ret.determinant());
		
		return (ret.determinant() != 0);
	}
	
	public static VectorND[] gramSchmidt(VectorND[] u) {
		
		if (!isBasis(u)) {throw new VectorBasisException("List of vectors must be a basis for the space");}
		
		VectorND[] v = new VectorND[u.length];
		
		v[0] = u[0];
		
		for (int i = 1; i <u.length; i++) {
			v[i] = u[i];
			for (int j = 0; j < i; j ++) {
				v[i] = addVectors(v[i],  scalarMult(-1 * (dotProd(u[i],v[j])/(dotProd(v[j],v[j]))), v[j])); 
			}
		}
		
		return v;
		
	}
	
	@Override
	public String toString() {
		
		String ret = "";
		
		for(int j = 0; j < size; j++) {
			ret += vector[j] + ",";
		}
		
		return ret;
	}
	
}
