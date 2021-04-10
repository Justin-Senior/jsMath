package jsMath.matrices;

import jsMath.exceptions.MatrixIndexException;
import jsMath.exceptions.MatrixInitException;
import jsMath.exceptions.MatrixNoInverseException;
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
	
	public double get(int x, int y) {
		if (x >= len || y >= len) throw new MatrixIndexException("Index Out of Bounds");
		return matrix[x][y];
	}
	// n x n Identity matrix
	public static MatrixNd id(int n) {
		
		double[][] idd  = new double[n][n];
		
		for(int i = 0; i < n; i++) {
			idd[i][i] = 1;
		}
		return (new MatrixNd(idd));
		
		
	}
	
	// Multiply a matrix by a constant
	public static MatrixNd constMult(double c, MatrixNd m) {
		
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
	public float colSum(int x) {
		
		float sum = 0;
		for(int i = 0; i < 2; i++) {
			sum += get(i,x);
		}
		
		return sum;
		
	}
	// Multiply a matrix by another matrix
	public static MatrixNd multiply(MatrixNd m1, MatrixNd m2) {
		
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
	
	public MatrixNd multiply(MatrixNd m2) {
		
		if (this.len!=m2.len)throw new MatrixSizeException("Matricies must be the same size");
		int len = this.len;
		
		double[][] mat = new double[len][len];
		
		for(int i = 0; i < len; i ++) {
			for (int j = 0; j < len; j++) {
				for (int k = 0; k < len; k++) {
					mat[i][j] += this.get(i, k) * m2.get(k,j);
				}
			}
		}
		
		MatrixNd m3 = new MatrixNd(mat);
		return m3;
		
	}
	
	// returns the transpose of a matrix
	public MatrixNd transpose() {
		
		double[][] trans = new double[len][len];
		
		for(int i = 0; i < len; i++) {
			for (int j=0; j< len; j++) {
				trans[j][i] = this.get(i, j);
			}
		}
		
		return (new MatrixNd(trans));
		
	}
	// Determines if 2 matrices are approximately equal within a given tolerance
	public boolean approxEqual(MatrixNd m2, double tol) {
		
		if (len!=m2.len)throw new MatrixSizeException("Matricies must be the same size");
		
		for(int i = 0; i < len; i ++) {
			for(int j = 0; j < len; j ++) {
				if(Math.abs(this.get(i, j)- m2.get(i, j)) > tol) return false; 
			}
		}
		return true;
	}
	// return a submatrix by excluding row x and column y
	public MatrixNd subMatrix(int x, int y) {
		
		int row = 0;
		int col = 0;
		
		double[][] mat = new double[len-1][len-1];
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				if (i != x && j != y) {
					mat[row][col] = matrix[i][j];
					col += 1;
				}
			}
			col = 0;
			if(i != x) row += 1; 
		}
		
		MatrixNd ret = new MatrixNd(mat);
		return ret;
		
	}
	// Find the determinant of an nxn matrix
	public double determinant() {
		
		if (len == 2) {
			return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
		}
			
		double det = 0;
			
		for(int i = 0; i < len; i++) {
			MatrixNd sub = subMatrix(0,i);
			if (i % 2 == 0) det += matrix[0][i] * sub.determinant();
			if (i % 2 == 1) det -= matrix[0][i] * sub.determinant();
		}
			
		return det;
	}
	// Finds the inverse of a matrix via the matrix of minors
	public MatrixNd inverse() throws MatrixNoInverseException{
		double[][] minors = new double[len][len];
		
		int cnt = 0;
		int fact = 1;
		
		double det = determinant();
		
		if (det == 0) throw new MatrixNoInverseException("Matrix is not invertible");
		
		for(int i = 0; i < len; i++) {
			for (int j= 0; j < len; j++){
				if (cnt % 2 == 0) fact = 1; else fact = -1;
				minors[i][j] = this.subMatrix(i, j).determinant()*fact;
				cnt += 1;
			}
			cnt += 1;
		}
		
		MatrixNd cofact = new MatrixNd(minors);
		MatrixNd adj = cofact.transpose();
		
		MatrixNd ret = MatrixNd.constMult(1/det, adj);
		return ret;
		
	}
	// Add a constant multiple of row y to row x
	private MatrixNd rowOp(int x, int y, double c) {
		
		double[][] ret = new double[len][len];
				
		for (int j = 0 ; j < len ; j ++ ) {
			for(int k = 0; k < len; k ++) {
				ret[j][k] = get(j,k);
			}
		}				
		for (int i = 0; i < 3; i ++) {
			ret[x][i] += get(y, i)*c;
		}
		
		return new MatrixNd(ret);
		
	}
	//Perform row operations on a matrix to make it upper triangular
	public MatrixNd gaussElim() {
		
		MatrixNd m2 = new MatrixNd(matrix);
		
		for(int i = 0; i < len; i ++) {
			for(int j = i + 1; j < len; j ++) {
				m2 = m2.rowOp(j, i, -m2.get(j, i)/m2.get(i, i));
			}
		}
		return m2;
		
	}
	
	public boolean isUpperTriangular() {
		
		for(int i = 0; i < len; i ++) {
			for(int j = 0; j < len; j ++) {
				if( i > j && Math.abs(get(i,j)) > 0.001) return false;
			}
		}
		return true;
	}
	//generate an nxn matrix with random values
	public static MatrixNd genRandomMatrix(int n) {
		
		int max = 10;
		int min = -10;
		
		double[][] mat = new double[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j  = 0; j < n; j++) {
				mat[i][j] = Math.random()*max + Math.random()*min; 
			}
		}
		
		return new MatrixNd(mat);
	}
	
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				ret += matrix[i][j] + ",";
			}
			ret += "\n";
		}
		return ret;
	}
	
}
