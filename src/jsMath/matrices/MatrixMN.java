package jsMath.matrices;

import jsMath.exceptions.MatrixIndexException;
import jsMath.exceptions.MatrixInitException;
import jsMath.exceptions.MatrixSizeException;

public class MatrixMN {

	private double[][] data;
	int rows;
	int cols;
	
	// Class for representation of mxn matrices
	// Initialized 
	public MatrixMN(double[][] input)
	 {
	        data = input;
	        rows = input.length;
	        cols = input[0].length;
	        
	 }
	
	public double get(int x, int y) {
		if (x >= rows || y >= cols) throw new MatrixIndexException("Index Out of Bounds");
		return data[x][y];
	}
	
	//Add a scalar to a matrix
	public void scalarAdd(double c) {
		for (int i = 0; i < this.rows; i ++) {
			for (int j = 0; j < this.cols; j ++) {
				this.data[i][j] += c;
			}
		}
	}
	
	// Multiply a matrix by a constant
	public void constMult(double c) {

		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.cols; j++) {
				this.data[i][j] = this.get(i, j) * c;
			}
		}
		
	}
	
	//Add two matrices
	public void add(MatrixMN m2) {
		
		if (this.rows!= m2.rows || this.cols != m2.cols)throw new MatrixSizeException("Matricies must be the same size");
		
		for(int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j++) {
					this.data[i][j] += m2.get(i,j);
			}
		}
		
	}
	
	//Add two matrices
	public static MatrixMN add(MatrixMN m1, MatrixMN m2) {
		
		if (m1.rows!= m2.rows || m1.cols != m2.cols)throw new MatrixSizeException("Matricies must be the same size");
		
		double[][] data = new double[m1.rows][m1.cols];
		
		for(int i = 0; i < m1.rows; i ++) {
			for (int j = 0; j < m1.cols; j++) {
					data[i][j] += m2.get(i,j);
			}
		}
		
		return new MatrixMN(data);
		
	}
	
	//Subtract a matrix from another matrix
	public void subtract(MatrixMN m2) {
		
		m2.constMult(-1);
		
		this.add(m2);
		
	}
	
//	//Subtract a matrix from another matrix
//	public static MatrixMN subtract(MatrixMN m1, MatrixMN m2) {
//		
//		if (m1.len!=m2.len)throw new MatrixSizeException("Matricies must be the same size");
//		int len = m1.len;
//		
//		double[][] data = new double[len][len];
//		
//		for(int i = 0; i < len; i ++) {
//			for (int j = 0; j < len; j++) {
//				for (int k = 0; k < len; k++) {
//					data[i][j] += m1.get(i, k) - m2.get(k,j);
//				}
//			}
//		}
//		
//		return new MatrixMN(data);
//		
//	}
	
	// Multiply a matrix by another matrix	
	public static MatrixMN multiply(MatrixMN m1, MatrixMN m2) {
		
		if (m1.rows!= m2.rows || m1.cols != m2.cols)throw new MatrixSizeException("Matricies must be the same size");
		
		double[][] mat = new double[m1.rows][m2.cols];
		
		for(int i = 0; i < m1.rows; i ++) {
			for (int j = 0; j < m2.cols; j++) {
				for (int k = 0; k < m1.rows; k++) {
					mat[i][j] += m1.get(i, k) * m2.get(k,j);
				}
			}
		}
		
		MatrixMN m3 = new MatrixMN(mat);
		return m3;
		
	}
	
	//Element-wise multiplication of two matricies
	public void mutliply(MatrixMN m2) {
		
		if (this.rows!= m2.rows || this.cols != m2.cols)throw new MatrixSizeException("Matricies must be the same size");
		
		for (int i = 0; i < rows; i ++) {
			for (int j = 0 ; j < cols; j ++) {
				this.data[i][j] *= m2.get(i,j);
			}
		}
		
	}
	
	// returns the transpose of a matrix
	public static MatrixMN transpose(MatrixMN m1) {
		
		double[][] trans = new double[m1.rows][m1.cols];
		
		for(int i = 0; i < m1.rows; i++) {
			for (int j=0; j< m1.cols; j++) {
				trans[j][i] = m1.get(i, j);
			}
		}
		
		return (new MatrixMN(trans));
	}
	
	public void sigmoid() {
		
		for(int i = 0; i < rows; i ++) {
			for (int j= 0 ; j < cols; j ++) {
				this.data[i][j] = 1/Math.exp(-this.data[i][j]);
			}
		}
		
	}
	
	public MatrixMN dsigmoid() {
		
		double[][] dsig = new double[rows][cols];
		
		for (int i = 0; i < rows; i ++) {
			for (int j= 0; j < rows; j ++) {
				dsig[i][j] = this.data[i][j] * (1 - this.data[i][j]);
			}
		}
		
		return new MatrixMN(dsig);
		
	}
	
		
}
