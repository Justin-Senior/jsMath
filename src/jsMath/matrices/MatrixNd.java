package jsMath.matrices;

import jsMath.exceptions.MatrixIndexException;
import jsMath.exceptions.MatrixInitException;
import jsMath.exceptions.MatrixSizeException;

public class MatrixNd {
	
	public int len;
	private double[][] matrix;
	
	
	// Class for representation of nxn matrices
	public MatrixNd(double[][] m) throws MatrixInitException{
		int l = m[0].length;
		int k = m.length;
		
		if (k != l) throw new MatrixInitException("Matrix not square");
		
		for(int i = 0; i < m.length; i ++) {
			if (m[i].length != l) throw new MatrixInitException("Rows not same length");
		}
		
		matrix = m;
		len = m.length;
	}
	
	public double get(int x, int y) throws MatrixIndexException {
		if (x >= len || y >= len) throw new MatrixIndexException("Index Out of Bounds");
		return matrix[x][y];
	}
	
	
	public static MatrixNd constMult(float c, MatrixNd m) throws Exception {
		
		double[][] entries = new double[m.len][m.len];
		
		for(int i = 0; i < m.len; i++) {
			for(int j = 0; j < m.len; j++) {
				entries[i][j] = m.get(i, j) * c;
			}
		}
		
		MatrixNd ret = new MatrixNd(entries);
		return ret;
	}
	//Returns the sum of a row
	public float rowSum(int x) {
	
		float sum = 0;
		for(int i = 0; i < len; i++) {
			sum += matrix[x][i];
		}
	
		return sum;
	
	}

	//returns the sum of one of the columns
	public float colSum(int x) throws MatrixIndexException{
		
		float sum = 0;
		for(int i = 0; i < 2; i++) {
			sum += get(i,x);
		}
		
		return sum;
		
	}
	
	public static MatrixNd multiply(MatrixNd m1, MatrixNd m2) throws MatrixIndexException, MatrixSizeException, MatrixInitException{
		
		if (m1.len!=m2.len)throw new MatrixSizeException("Matricies must be the same size");
		int len = m1.len;
		
		double[][] mat = new double[len][len];
		
		for(int i = 0; i < len; i ++) {
			for (int j = 0; j < len; j++) {
				for (int k = 0; k < len; k++) {
					mat[i][j] += m1.get(i, k) * m2.get(k,j);
				}
			}
		}
		
		MatrixNd m3 = new MatrixNd(mat);
		return m3;
		
	}
	
	public MatrixNd transpose() {
		
		double[][] trans = new double[len][len];
		
		for(int i = 0; i < len; i++) {
			for (int j=0; j< 3; j++) {
				trans[j][i] = this.get(i, j);
			}
		}
		
		return (new MatrixNd(trans));
		
	}
	
	public boolean approxEqual(MatrixNd m2, double tol) {
		
		if (len!=m2.len)throw new MatrixSizeException("Matricies must be the same size");
		
		for(int i = 0; i < len; i ++) {
			for(int j = 0; j < len; j ++) {
				if(Math.abs(this.get(i, j)- m2.get(i, j)) > tol) return false; 
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ret += matrix[i][j] + ",";
			}
			ret += "\n";
		}
		return ret;
	}
	
}
