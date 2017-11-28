package assignment2;

public class pik {
	static double abs ( double val ) {
		return val > 0 ? val : (val * -1);
	}
	
	static double powOfMinusOne ( int exp ) {
		
		if ( exp == 0 || exp % 2 == 0 ) {
			return 1.0;
		}
		
		return -1.0;
	}
	public static void main ( String... args ) { 
		
		double sum = 0.0;
		double prev = 0.0;
		double curr = 0.0;
		int n = 0;
		double delta = 0.000001;
		double correct = 0.0;
		double value = 0.0;
		
		do { 
			prev = curr;
			value = (double)(2 * n + 1);
			curr = (4.0*(powOfMinusOne(n)/value));
			sum += curr; 
			n++;
		} while ( abs( curr - prev ) >= delta );
		System.out.format("Pi (%.1E) = ", +delta);
		System.out.println(sum);
		
	}

}
