package jsMath.matrices;
//Represents 2x2 matrices with integer values.
//There is not method to invert the matrix because integer matrices are not guaranteed invertible
public class Matrix2i {
	
	public int[][] matrix = new int[2][2];
	
	
	public Matrix2i(int[] top, int[] bottom) throws Exception{
		
		if (top.length != 2 || bottom.length != 2) throw new Exception("Input arrays must be of length 2");
		
		this.matrix[0] = top;
		this.matrix[1] = bottom;
		
	}
	
	public Matrix2i(int[][] matrix) {
		assert(matrix[0].length == 2 && matrix[1].length == 2);
		
		this.matrix = matrix;
	}
	//get the entry x,y
	public int get(int x, int y) {
		return matrix[x][y];
	}
	
	public static Matrix2i constMult(int c, Matrix2i m) throws Exception {
		
		int[] top = new int[2];
		int[] bot = new int[2];
		
		for(int i = 0; i < 2; i++) {
			top[i] = m.get(0,i)*c;
			bot[i] = m.get(1,i)*c;
		}
		
		Matrix2i ret = new Matrix2i(top, bot);
		return ret;
		
	}
	//returns the sum of one of the rows
	public int rowSum(int x) {
		assert (x == 2);
		
		int sum = 0;
		for(int i = 0; i < 2; i++) {
			sum += matrix[x][i];
		}
		
		return sum;
		
	}
	
	//returns the sum of one of the columns
	public int colSum(int x) {
		assert(x == 2);
		
		int sum = 0;
		for(int i = 0; i < 2; i++) {
			sum += get(i,x);
		}
		
		return sum;
		
	}
	
	public static Matrix2i multiply(Matrix2i m1, Matrix2i m2) {
		
		int[][] mat = new int[2][2];
		
		for(int i = 0; i < 2; i ++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					mat[i][j] += m1.get(i, k) * m2.get(k,j);
				}
			}
		}
		
		Matrix2i m3 = new Matrix2i(mat);
		return m3;
		
	}
	//return the determinant of a matrix 
	public int determinant() {
		
		int det = matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
		
		return det;
		
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
