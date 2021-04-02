package jsMath.matrices;
//Represents 2x2 matrices with float values.
//There is not method to invert the matrix because integer matrices are not guaranteed invertible
public class Matrix3f {
	
	public float[][] matrix = new float[3][3];
	
	
	public Matrix3f(float[] top, float [] mid, float[] bottom) throws Exception{
		
		if (top.length != 3 || bottom.length != 3) throw new Exception("Input arrays must be of length 2");
		
		this.matrix[0] = top;
		this.matrix[1] = mid;
		this.matrix[2] = bottom;
		
	}
	
	public Matrix3f(float[][] matrix) {
		assert(matrix[0].length == 3 && matrix[1].length == 3 && matrix[2].length == 3);
		
		this.matrix = matrix;
	}
	//get the entry x,y
	public float get(int x, int y) {
		return matrix[x][y];
	}
	
	public static Matrix3f constMult(float c, Matrix2i m) throws Exception {
		
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
		assert (x == 2);
		
		float sum = 0;
		for(int i = 0; i < 3; i++) {
			sum += matrix[x][i];
		}
		
		return sum;
		
	}
	
	//returns the sum of one of the columns
	public float colSum(int x) {
		assert(x == 2);
		
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
	/*
	public Matrix3f inverse() throws Exception{
		float[][] mat = new float[3][3];
		
		if (this.determinant() == 0) throw new Exception("Not invertible");
		
		float det = Math.abs(this.determinant());
		
		mat[0][0] = matrix[1][1]/det;
		mat[0][1] = -matrix[0][1]/det;
		mat[1][0] = -matrix[1][0]/det;
		mat[1][1] = matrix[0][0]/det;
		
		Matrix3f ret = new Matrix3f(mat);
		return ret;
		
	}
	*/
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ret += Float.toString(matrix[i][j]) + ",";
			}
			ret += "\n";
		}
		return ret;
	}
	
}