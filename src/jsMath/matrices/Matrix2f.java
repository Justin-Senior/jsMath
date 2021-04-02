package jsMath.matrices;
//Represents 2x2 matrices with float values.
//There is not method to invert the matrix because integer matrices are not guaranteed invertible
public class Matrix2f {
	
	public float[][] matrix = new float[2][2];
	
	
	public Matrix2f(float[] top, float[] bottom) throws Exception{
		
		if (top.length != 2 || bottom.length != 2) throw new Exception("Input arrays must be of length 2");
		
		this.matrix[0] = top;
		this.matrix[1] = bottom;
		
	}
	
	public Matrix2f(float[][] matrix) {
		assert(matrix[0].length == 2 && matrix[1].length == 2);
		
		this.matrix = matrix;
	}
	//get the entry x,y
	public float get(int x, int y) {
		return matrix[x][y];
	}
	
	public static Matrix2f constMult(float c, Matrix2i m) throws Exception {
		
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
		assert (x == 2);
		
		float sum = 0;
		for(int i = 0; i < 2; i++) {
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
	
	public static Matrix2f multiply(Matrix2f m1, Matrix2f m2) {
		
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
	//return the determinant of a matrix 
	public float determinant() {
		
		float det = matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
		
		return det;
		
	}
	
	public Matrix2f inverse() throws Exception{
		float[][] mat = new float[2][2];
		
		if (this.determinant() == 0) throw new Exception("Not invertible");
		
		float det = Math.abs(this.determinant());
		
		mat[0][0] = matrix[1][1]/det;
		mat[0][1] = -matrix[0][1]/det;
		mat[1][0] = -matrix[1][0]/det;
		mat[1][1] = matrix[0][0]/det;
		
		Matrix2f ret = new Matrix2f(mat);
		return ret;
		
	}
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				ret += Float.toString(matrix[i][j]) + ",";
			}
			ret += "\n";
		}
		return ret;
		
		
	}
	
}