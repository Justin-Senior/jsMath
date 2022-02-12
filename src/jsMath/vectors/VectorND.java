package jsMath.vectors;
import jsMath.exceptions.VectorSizeException;

public class VectorND {

	public int len;
	public double[] vector; 
		
	
	public VectorND(double[] v) {
		
		len = v.length;
		vector = v;
		
	}
	
	public void setPos(int x, double n) {
		vector[x] = n;
	}
	
	public double getPos(int x) {
		return vector[x];
	}
	
	public VectorND addVectors(VectorND v1, VectorND v2) {
		
		double vec[] = new double[3]; 
		VectorND v3 = new VectorND(vec);
		
		if (v1.len != v2.len) { throw new VectorSizeException("Vectors must be of same length!");}
		
		for (int i = 0; i < v1.len; i++) {
			v3.setPos(i,v1.getPos(i) + v2.getPos(i));
		}
		
		return v3;
		
	}
	
	public static double dotProd(VectorND v1, VectorND v2) {
		
		double ret = 0;
		
		if (v1.len != v2.len) { throw new VectorSizeException("Vectors must be of same length!");}
		
		for (int i = 0;i < v1.len;i++) {
			ret += v1.getPos(i) * v2.getPos(i);
		}
		
		ret = Math.sqrt(ret);{
		}
		
		return ret;
	}
	
	public double length() {
		
		return dotProd(this, this);
		
	}
	
	public static VectorND normalize(VectorND v1) {
		
		double[] vec = new double[v1.len];
		vec = v1.vector;
		
		VectorND v2 = new VectorND(vec);
		
		double length = v2.length();
		
		for(int i = 0; i < v2.len; i ++) {
			v2.setPos(i, v1.getPos(i)/length);
		}
		
		return v2;
		
	}
	
	@Override
	public String toString() {
		
		String ret = "";
		
		for(int j = 0; j < len; j++) {
			ret += vector[j] + ",";
		}
		
		return ret;
	}
	
}
