package jsMath.matrices;

import jsMath.exceptions.MatrixIndexException;
import jsMath.exceptions.MatrixInitException;
import jsMath.exceptions.MatrixNoInverseException;
import jsMath.twoD.Cubic;

//Represents 3x3 matrices with float values.
public class Matrix3f{
	
	private float[][] matrix = new float[3][3];
	
	public Matrix3f(float[] top, float [] mid, float[] bottom) {
		
		if (top.length != 3 || bottom.length != 3) throw new MatrixInitException("Input arrays must be of length 3");
		
		this.matrix[0] = top;
		this.matrix[1] = mid;
		this.matrix[2] = bottom;
		
	}
	
	public Matrix3f(float[][] matrix) {
		
		if (matrix[0].length != 3 || matrix[1].length != 3 || matrix[2].length != 3) throw new MatrixInitException("Matrix array is not 3x3");
		
		this.matrix = matrix;
	}
	// The identity matrix
	public static Matrix3f id() {
		Matrix3f id;
		float[][] idf = {{1,0,0},{0,1,0},{0,0,1}};
		id = new Matrix3f(idf);

		return id;
	}
	
	//get the entry x,y
	public float get(int x, int y){
		if (x >= 3 || y >= 3) throw new MatrixIndexException("Index Out of Bounds");
		return matrix[x][y];
	}
	//Multiplies the matrix by a constant and returns a new matrix
	public static Matrix3f constMult(float c, Matrix3f m) {
		
		float[] top = new float[3];
		float[] mid = new float[3];
		float[] bot = new float[3];
		
		for(int i = 0; i < 3; i++) {
			top[i] = m.get(0,i)*c;
			mid[i] = m.get(1,i)*c;
			bot[i] = m.get(2,i)*c;
		}
		
		Matrix3f ret = new Matrix3f(top, mid, bot);
		return ret;
		
	}
	//returns the sum of one of the rows
	public float rowSum(int x) {
		
		float sum = 0;
		for(int i = 0; i < 3; i++) {
			sum += matrix[x][i];
		}
		
		return sum;
		
	}
	
	//returns the sum of one of the columns
	public float colSum(int x){
		
		float sum = 0;
		for(int i = 0; i < 2; i++) {
			sum += get(i,x);
		}
		
		return sum;
		
	}
	public static Matrix3f multiply(Matrix3f m1, Matrix3f m2) {
		
		float[][] mat = new float[3][3];
		
		for(int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					mat[i][j] += m1.get(i, k) * m2.get(k,j);
				}
			}
		}
		
		Matrix3f m3 = new Matrix3f(mat);
		return m3;
		
	}
	//Compute the transpose of a matrix
	public Matrix3f transpose() {
		
		float[][] trans = new float[3][3];
		
		for(int i = 0; i < 3; i++) {
			for (int j=0; j< 3; j++) {
				trans[j][i] = this.get(i, j);
			}
		}
		
		return (new Matrix3f(trans));
		
	}
	//Create a submatrix from a 3x3 matrix by excluding row x and column y
	public Matrix2f subMatrix(int x, int y) {
		
		int row = 0;
		int col = 0;
		
		float[][] mat = new float[2][2];
		for(int i = 0; i < 3; i++) {

			for(int j = 0; j < 3; j++) {
				if (i != x && j != y) {
					mat[row][col] = matrix[i][j];
					col += 1;
				}
			}
			col = 0;
			if(i != x) row += 1; 
		}
		
		Matrix2f ret = new Matrix2f(mat);
		return ret;
		
	}
	
	//return the determinant of a matrix 
	public float determinant() {
		
		float det = 0;
		
		for(int i = 0; i < 3 ; i++) {
			Matrix2f sub = subMatrix(0,i);
			if (i % 2 == 0) det += matrix[0][i] * sub.determinant();
			if (i % 2 == 1) det -= matrix[0][i] * sub.determinant();
		}
		
		return det;
		
	}
	
	public Matrix3f inverse() throws MatrixNoInverseException{
		float[][] minors = new float[3][3];
		
		int cnt = 0;
		int fact = 1;
		
		float det = this.determinant();
		
		if (det == 0) throw new MatrixNoInverseException("Matrix is not invertible");
		
		for(int i = 0; i < 3; i++) {
			for (int j= 0; j < 3; j++){
				if (cnt % 2 == 0) fact = 1; else fact = -1;
				minors[i][j] = this.subMatrix(i, j).determinant()* fact;
				cnt += 1;
			}
		}
		
		Matrix3f cofact = new Matrix3f(minors);
		Matrix3f adj = cofact.transpose();
		
		Matrix3f ret = Matrix3f.constMult(1/det, adj);
		return ret;
		
	}
	
	
	public boolean approxEqual(Matrix3f m2, double tol) {
		for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 3; j ++) {
				if(Math.abs(this.get(i, j)- m2.get(i, j)) > tol) return false; 
			}
		}
		return true;
	}
	//use a cubic root solver to find roots of characteristic polynomial. Does not return complex eigenvalues.
	public float[] eigenvalues(){
		float[] vars = new float[9];
		int cnt = 0;
		for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 3; j ++) {
				vars[cnt] = matrix[i][j];
				cnt ++;
			}
		}
		float x = vars[0];
		float i = vars[1];
		float j = vars[2];
		float k = vars[3];
		float y = vars[4];
		float l = vars[5];
		float m = vars[6];
		float n = vars[7];
		float z = vars[8];
		
		float t1 = x + y + z;
		float t2 = (j*m + i*k + l*n) - (x*y + x*z + y*z);
		float t3 = x*y*z + i*l*m + j*k*n - m*y*j-n*l*x-z*k*i;
		
		Cubic c = new Cubic(-1,t1,t2,t3);
		
		System.out.println(c.toString());
		
		return c.roots();
	}
	//Mulitply a row of the matrix by a constant
	public Matrix3f rowMult(int x, float c) {
		
		float[][] ret = new float[3][3];
		
		for (int j = 0 ; j < 3 ; j ++ ) {
			for(int k = 0; k < 3; k ++) {
				ret[j][k] = get(j,k);
			}
		}
		
		for (int i = 0; i < 3; i ++) {
			ret[x][i] *= c;
		}
		return new Matrix3f(ret);
		
	}
	// Add a constant multiple of row y to row x
	public Matrix3f rowOp(int x, int y, float c) {
		
		float[][] ret = new float[3][3];
		
		for (int j = 0 ; j < 3 ; j ++ ) {
			for(int k = 0; k < 3; k ++) {
				ret[j][k] = get(j,k);
			}
		}
		
		for (int i = 0; i < 3; i ++) {
			ret[x][i] += get(y, i)*c;
		}
		return new Matrix3f(ret);
		
	}
	
	//Mulitply a row of the matrix by a constant
	public Matrix3f colMult(int x, float c) {
			
		float[][] ret = new float[3][3];
			
		for (int j = 0 ; j < 3 ; j ++ ) {
			for(int k = 0; k < 3; k ++) {
				ret[j][k] = get(j,k);
			}
		}
		
		for (int i = 0; i < 3; i ++) {
			ret[i][x] *= c;
		}
		return new Matrix3f(ret);
			
	}
	
	//Perform row operations on a matrix to make it upper triangular with ones on the diagonal
	public Matrix3f gaussElim() {
		
		Matrix3f r1 = rowOp(1,0, -(this.get(1, 0)/this.get(0, 0))).rowOp(2, 0, -this.get(2, 0)/this.get(0, 0)); // Step 1
		
		Matrix3f r2 = r1.rowOp(2, 1, -r1.get(2, 1)/r1.get(1, 1)); // Step 2
		
		Matrix3f r3 = r2.rowMult(0, 1/r2.get(0,0)).rowMult(1, 1/r2.get(1,1)).rowMult(2, 1/r2.get(2, 2)); // Normalize diagonal
		
		return r3;

	}
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ret += (matrix[i][j]) + ",";
			}
			ret += "\n";
		}
		return ret;
	}
	
}