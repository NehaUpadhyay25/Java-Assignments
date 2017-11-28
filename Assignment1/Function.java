package Assignment1;/*
 * FileName : Function.java
 * 
 * Version : 1.0
 * 
 * author : Neha Upadhyay 
 * author : Kritka Sahni
 * 
 * Revisions: Initial Revision
 * 
 */

/**
 * Description : The class below determines whether the 
 * |f(x) - correctf(x)| < DELTA and DELTA > 0
 * 
 * @author Neha Upadhyay
 * @author Kritka Sahni
 *
 */

class Function {
		/**
		 * The main method below calculates the summation function and 
		 * stops when the value comes between 0 and DELTA
		 *  
		 *  @param    args    command line arguments(ignored)
		 * 	
		*/
        public static void main( String [] args ) {
        	
                int N = 0; // limit of the summation
                double X = 0.1; // value for which function is to be evaluated
                int factorial_value = 1; // value to store factorial result
                double DELTA = 0.05; // assumed DELTA value
                double correct_fx = 0; // value of correctf(x) initially assumed

                while( N >= 0 ) {

                	double value1 = Math.pow( -1, N ); //Stores(-1)^N
                    int value2 = 2 * N + 1; //value stores(2 * N + 1)

                   	// calculates factorial
                    for ( int range = 1 ; range <= value2; range++ ) {
                    	factorial_value = factorial_value * range;
                    }
                        
                    // value stores ( -1 ) ^ N / ( 2 * N + 1 )!
                    double value3 = value1 / (double)factorial_value; 
                    // value stores function value
                    double fx = value3 * Math.pow( X, value2 ) + correct_fx;
                    //calculates f(x) - correct(fx)
                    double function_difference = Math.abs( fx - correct_fx ); 
                       
                    // prints the final result
                    if( function_difference <  Math.abs( DELTA ) ) {                     	
                    	System.out.println( "We reached final : " +fx );
                        System.out.print( "Difference between sets : " );
                        System.out.println(function_difference);
                        System.out.println( "Value of N = " +N );
                        break;
                    }
                    N = N + 1; // increments N
                    factorial_value = 1; //Factorial_value again initialised to 1 for new N
                    correct_fx = fx; // swaps correct(fx)
                }
        }
} // Function