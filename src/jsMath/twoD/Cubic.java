package jsMath.twoD;

public class Cubic {

	float a;
	float b; 
	float c; 
	float d;
	// Class for cubics (mostly for solving eigenvalues of a 3x3 matrix)
	public Cubic(float a, float b, float c, float d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	// find the roots of a cubic(if they exist)
	public float[] roots(){
		float[] ret = new float[3];
		
		for(int i= 0; i < solveCubic(a,b,c,d).length; i++) {
			ret[i] = (float) solveCubic(a,b,c,d)[i];
		}
		
		return ret;
	}
	//Does the heavy lifting of the root finding; from https://stackoverflow.com/questions/43559140
	public static double[] solveCubic(double d, double a, double b, double c) {
        double[] result;
        if (d != 1) {
            a = a / d;
            b = b / d;
            c = c / d;
        }

        double p = b / 3 - a * a / 9;
        double q = a * a * a / 27 - a * b / 6 + c / 2;
        double D = p * p * p + q * q;

        if (Double.compare(D, 0) >= 0) {
            if (Double.compare(D, 0) == 0) {
                double r = Math.cbrt(-q);
                result = new double[2];
                result[0] = 2 * r;
                result[1] = -r;
            } else {
                double r = Math.cbrt(-q + Math.sqrt(D));
                double s = Math.cbrt(-q - Math.sqrt(D));
                result = new double[1];
                result[0] = r + s;
            }
        } else {
            double ang = Math.acos(-q / Math.sqrt(-p * p * p));
            double r = 2 * Math.sqrt(-p);
            result = new double[3];
            for (int k = -1; k <= 1; k++) {
                double theta = (ang - 2 * Math.PI * k) / 3;
                result[k + 1] = r * Math.cos(theta);
            }

        }
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] - a / 3;
        }
        return result;
    }


    public static double[] solveCubic(double a, double b, double c) {
        double d = 1;
        return solveCubic(a, b, c, d);
    }
    
	public String toString() {
		String s = a + "x^3 + " + b + "x^2 + " + c + "x + " + d;
		return s;
	}
	
}
