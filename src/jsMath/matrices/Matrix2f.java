package jsMath.matrices;

import jsMath.exceptions.MatrixIndexException;
import jsMath.exceptions.MatrixInitException;
import jsMath.exceptions.MatrixNoInverseException;
import jsMath.exceptions.RootsException;
import jsMath.twoD.Quadratic;

//Represents 2x2 matrices with float values.

public class Matrix2f {
	
	private float[][] matrix = new float[2][2];
	
	public Matrix2f(float[] top, float[] bottom) {
		
		if (top.length != 2 || bottom.length != 2) throw new MatrixInitException("Input arrays must be of length 2");
		
		this.matrix[0] = top;
		this.matrix[1] = bottom;
	}
	
	public Matrix2f(float[][] matrix) {
		assert(matrix[0].length == 2 && matrix[1].length == 2);
		
		this.matrix = matrix;
	}
	
	
	//returns the identity matrix
	public static Matrix2f id() {
		float[][] idf = {{1,0},{0,1}};;
		
		Matrix2f id = new Matrix2f(idf);
		return id;
	}
	
	//get the entry x,y
	public float get(int x, int y) {
		if (x >= 2 || y >= 2) throw new MatrixIndexException("Index Out of Bounds");
		return matrix[x][y];
	}
	//Multiplies matrix by a constant and returns a new matrix
	public static Matrix2f constMult(float c, Matrix2f m) {
		
		float[] top = new float[2];
		float[] bot = new float[2];
		
		for(int i = 0; i < 2; i++) {
			top[i] = m.get(0,i)*c;
			bot[i] = m.get(1,i)*c;
		}
		
		Matrix2f ret = new Matrix2f(top, bot);
		return ret;
		
	}
	//returns the sum of one of the rows
	public float rowSum(int x) {
		
		float sum = 0;
		for(int i = 0; i < 2; i++) {
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
	
	public static Matrix2f multiply(Matrix2f m1, Matrix2f m2){
		
		float[][] mat = new float[2][2];
		
		for(int i = 0; i < 2; i ++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					mat[i][j] += m1.get(i, k) * m2.get(k,j);
				}
			}
		}
		
		Matrix2f m3 = new Matrix2f(mat);
		return m3;
		
	}
	

	public Matrix2f multiply(Matrix2f m2){
		
		float[][] mat = new float[2][2];
		
		for(int i = 0; i < 2; i ++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					mat[i][j] += this.get(i, k) * m2.get(k,j);
				}
			}
		}
		
		Matrix2f m3 = new Matrix2f(mat);
		return m3;
		
	}
	
	//return the determinant of a matrix 
	public float determinant() {
		
		float det = matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
		
		return det;
		
	}
	
	public Matrix2f inverse() throws MatrixNoInverseException {
		float[][] mat = new float[2][2];
		
		float det = this.determinant();
		if (det == 0) throw new MatrixNoInverseException("Not invertable, determinant is 0");
		
		mat[0][0] = matrix[1][1]/det;
		mat[0][1] = -matrix[0][1]/det;
		mat[1][0] = -matrix[1][0]/det;
		mat[1][1] = matrix[0][0]/det;
		
		Matrix2f ret = new Matrix2f(mat);
		return ret;
		
	}
	
	public boolean approxEqual(Matrix2f m2, double tol){
		for(int i = 0; i < 2; i ++) {
			for(int j = 0; j < 2; j ++) {
				if(Math.abs(this.get(i, j)- m2.get(i, j)) > tol) return false; 
			}
		}
		return true;
	}
	
	public float[] eigenvalues() throws RootsException {
		float x = matrix[0][0];
		float y = matrix[1][1];
		float i = matrix[0][1];
		float j = matrix[1][0];
		
		Quadratic q = new Quadratic(1, x+y, x*y-i*j);
		return q.roots();
		
	}
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				ret += matrix[i][j] + ",";
			}
			ret += "\n";
		}
		return ret;
	}
	
}