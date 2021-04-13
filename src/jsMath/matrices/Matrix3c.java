package jsMath.matrices;

import jsMath.complex.*;
import jsMath.exceptions.MatrixIndexException;
import jsMath.exceptions.MatrixInitException;

public class Matrix3c {

	Complex[][] matrix = new Complex[3][3];
	
	public Matrix3c (Complex[][] matrix) {
		
		if (matrix[0].length != 3 || matrix[1].length != 3 || matrix[2].length != 3) throw new MatrixInitException("Matrix array is not 3x3");
		
		this.matrix = matrix;
	}
	
	//generate a 3x3 matrix with random values
	public static Matrix3c genRandomMatrix() {
			
		int max = 5;
		int min = -5;
		
		Complex[][] mat = new Complex[3][3];
		
		for(int i = 0; i < 3; i++) {
			for(int j  = 0; j < 3; j++) {
				mat[i][j] = new Complex(Math.random()*max + Math.random()*min, Math.random()*max + Math.random()*min); 
			}
		}
		
		return new Matrix3c(mat);
	}
	
	public Complex get(int i, int j) {
		
		if (i >= 3 || j >= 3) throw new MatrixIndexException("Index Out of Bounds");
		
		return matrix[i][j];
	}
	
	public static Matrix3c constMult(Complex c, Matrix3c m) {
		
		Complex[][] mat = new Complex[3][3];
		
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				mat[i][j] = m.get(i,j).multiply(c);
			}
		}
		
		return (new Matrix3c(mat));
	}
	
	
	public Matrix3c constMult(Complex c) {
		
		Complex[][] mat = new Complex[3][3];
		Complex.fill(mat);
		
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				mat[i][j] = get(i,j).multiply(c);
			}
		}
		
		return (new Matrix3c(mat));
	}
	
	
	public Matrix3c multiply(Matrix3c m2) {
		
		Complex[][] mat = new Complex[3][3];
		Complex.fill(mat);
		
		for(int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					mat[i][j] = mat[i][j].add(get(i, k).multiply(m2.get(k,j)));
				}
			}
		}
		
		Matrix3c m3 = new Matrix3c(mat);
		return m3;
		
	}
	
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ret += (matrix[i][j].toString()) + ",";
			}
			ret += "\n";
		}
		return ret;
	}
	
}
