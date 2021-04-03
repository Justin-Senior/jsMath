package jsMath.matrices;
//Represents 3x3 matrices with float values.
public class Matrix3f {
	
	public float[][] matrix = new float[3][3];
	
	private static final float[][] idf = {{1,0,0},{0,1,0},{0,0,1}};
	
	public static final Matrix3f id = new Matrix3f(idf);
	
	
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
	//Multiplies the matrix by a constant and returns a new matrix
	public static Matrix3f constMult(float c, Matrix3f m) throws Exception {
		
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
	
	public Matrix3f inverse() throws Exception{
		float[][] minors = new float[3][3];
		
		int cnt = 0;
		int fact = 1;
		
		float det = this.determinant();
		
		for(int i = 0; i < 3; i++) {
			for (int j= 0; j < 3; j++){
				if (cnt %2 == 0) fact = 1; else fact = -1;
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
	//TODO finish this lol
	public float[] eigenvalues() {
		float[] vars = new float[9];
		int cnt = 0;
		for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 3; j ++) {
				vars[i] = matrix[i][j];
				cnt ++;
			}
		}
		return vars;
	}
	
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