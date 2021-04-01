package jsMath.probability;
import java.math.BigInteger;

public class Probability {
	
	
	//computes the factorial of a number and returns a BigInteger value to account for large factorials.
	public static BigInteger factorial(int n) {
		BigInteger result = BigInteger.ONE;
	    for (int i = 2; i <= n; i++)
	        result = result.multiply(BigInteger.valueOf(i));
	    return result;

	}
	

	// Finds the # of combinations nCr
	public static long combinations(int n, int r) {
		
		BigInteger nFac = factorial(n);
		BigInteger rFac = factorial(r);
		
		BigInteger nr = factorial(n - r);
		
		BigInteger ret = nFac.divide((rFac.multiply(nr)));
		
		long ret1 = ret.longValue();
		
		return ret1;
		
	}
	
	public static long permutations(int n, int r) {
		
		BigInteger nFac = factorial(n);
		BigInteger nr= factorial(n-r);
		
		BigInteger ret = nFac.divide(nr);
		
		return ret.longValue();
		
	}
	
	// Binomial probability given likelihood p, in n trials with x successes
	public static float binomial(double p, int n, int x) {
		
		double q = 1 - p;
		long nCx = combinations(n, x);
		float pX = (float) Math.pow(p, x);
		float qX = (float) Math.pow(q, n-x);
		
		float ret = nCx * pX * qX;
		
		return ret;
		
	}

}
