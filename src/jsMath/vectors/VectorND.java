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
	
	public VectorND addVector(VectorND v2) {
		
		double vec[] = new double[3]; 
		VectorND v3 = new VectorND(vec);
		
		if (this.size != v2.size) { throw new VectorSizeException("Vectors must be of same length!");}
		
		for (int i = 0; i < this.size; i++) {
			v3.setPos(i,this.getPos(i) + v2.getPos(i));
		}
		
		return v3;
		
	}
	
	public double dotProd(VectorND v2) {
		
		double ret = 0;
		
		if (this.size != v2.size) { throw new VectorSizeException("Vectors must be of same length!");}
		
		for (int i = 0;i < this.size;i++) {
			ret += this.getPos(i) * v2.getPos(i);
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
	
	public VectorND scalarMult(double x) {
		
		double[] vec = new double[this.size];
		VectorND v2 = new VectorND(vec);
		
		for (int i = 0;i < this.size; i ++) {
			v2.setPos(i, this.getPos(i)*x);
		}
		
		return v2; 
	}
	
	public VectorND normalize() {
		
		double n = this.length();
		
		VectorND v2 = this.scalarMult(1/n);
		
		return v2;
		 
	}
	// Determine if the given set of vectors is a basis
	public static boolean isBasis(VectorND[] vl) {
		
		double[][] mat = new double[vl.length][vl.length];
		
		for (int i = 0; i < vl.length; i++) {
			if (vl.length != vl[i].size) { throw new VectorSizeException("Number of dimensions must match number of vectors!");}
		}
		
		for (int j = 0; j < vl.length; j++) {
			mat[j] = vl[j].vector;
		}
		
		MatrixNd ret = new MatrixNd(mat);
		
		//System.out.println(ret.determinant());
		
		return (ret.determinant() != 0);
	}
	
	// Perform the Gram-Schmidt process on a basis. Optional boolean for if you want to normalize the vectors
	public static VectorND[] gramSchmidt(VectorND[] u, boolean b) {
		
		if (!isBasis(u)) {throw new VectorBasisException("List of vectors must be a basis for the space");}
		
		VectorND[] v = new VectorND[u.length];
		
		v[0] = u[0];
		
		for (int i = 1; i <u.length; i++) {
			v[i] = u[i];
			for (int j = 0; j < i; j ++) {
				v[i] = v[i].addVector(v[j].scalarMult(-1 * (u[i].dotProd(v[j])/(v[j].dotProd(v[j]))))); 
			}
		}
		
		if (b) {
			for (int k = 0; k < v.length; k++) {
				v[k] = v[k].normalize();
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
