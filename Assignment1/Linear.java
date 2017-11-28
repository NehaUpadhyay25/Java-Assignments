package assignment1;

/*
 * FileName : Linear.java
 * 
 * Version : 1.0
 * 
 * author : Neha Upadhyay 
 * author : Kritka Sahni
 * 
 * Revisions: Initial Revision
 * 
 */

import java.math.BigInteger;

/**
 * Description : The below class implements [ 3 * f( n - 1 ) + 7 * f( n - 2 ) ]
 *  using big integer class with concept of swapping from values from 2 to 100.
 * 
 * @author Neha Upadhyay
 * @author Kritka Sahni
 *
 */

class Linear {

        static BigInteger F0 = new BigInteger("0"); // variable stores value 0
        static BigInteger F1 = new BigInteger("1"); // variable with value 1

        static BigInteger NUMTHREE = new BigInteger("3"); // variable stores 3
        static BigInteger NUMSEVEN = new BigInteger("7"); // variable stores 7
        
        /**
         * The main method below calculates function value and simultaneously 
         * prints them for 2 <= n <= 100.
         * 
         *  @param    args    command line arguments(ignored)
         */
        public static void main( String [] args ) {

                System.out.println( "f(0) : 0" );
                System.out.println( "f(1) : 1" );
              
                // works for the values stated in program that is 2 to 100
                for ( int range= 2 ; range <= 100 ; range++ ) {
                	
                		// The variable used to store 3 * f( n - 1 )
                        BigInteger part1 = F1.multiply(NUMTHREE); 
                    
                        // The variable used to store 7 * f( n - 2 )
                        BigInteger part2 = F0.multiply(NUMSEVEN);

                        // The variable is the final result of the function.
                        BigInteger F2 = part1.add(part2);

                        System.out.print( "f(" +range ); // prints f(
                        System.out.println( ") : " +F2 ); // prints ): and f2

                        F0= F1;
                        F1= F2;
                }
        }
} // Linear