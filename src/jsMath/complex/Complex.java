package jsMath.complex;

// A  class representing complex numbers of the form x + i*y
public class Complex {
	
	public double x;
	public double y;
	
	public Complex(double x, double y) {
		this.x = x;
		this.y = y;
	}
	// returns the complex conjugate. x - i*y
	public Complex conjugate() {
		
		return (new Complex(x, -y));
		
	}
	// add 2 complex numbers
	public Complex add(Complex c) {
	
		Complex ret = new Complex(x+ c.x, y + c.y);
	
	return ret;
	}
	// multiply 2 complex numbers
	public Complex multiply(Complex c) {
		
		double t1 = x*c.x - y * c.y;
		double t2 = x*c.y + y * c.x;
		
		Complex ret = new Complex(t1, t2);
		
		return ret;
		
	}
	// divide 2 complex numbers
	public Complex divide(Complex c) {
		
		double t1  = (x * c.x + y * c.y)/(c.x * c .x+ c.y * c.y);
		double t2  = (y  * c.x - x * c.y)/(c.x * c .x+ c.y * c.y);
		
		return (new Complex(t1,t2));
	}
	// For filling an array for use in a matrix with a base complex, 0 + 0*i
	public static void fill(Complex[][] c){
		
		Complex base = new Complex(0,0);
		
		for(int i = 0; i < c.length; i++) {
			for(int j = 0; j < c[0].length; j++) {
				c[i][j] = base;
				System.out.println(".");
			}
		}
	}
	
	@Override
	public String toString() {
		if (y >= 0 ) return (x + " + i * " + y);
		else return (x + " - i * " + y);
	}

}
