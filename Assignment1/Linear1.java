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

import java.util.*;
import java.lang.Math;

/**
 * Description : The below class implements [ 3 * f( n - 1 ) + 7 * f( n - 2 ) ]
 *  using big integer class with concept of swapping from values from 2 to 100.
 * 
 * @author Neha Upadhyay
 * @author Kritka Sahni
 *
 */

class Linear1 {

        static double F0 = 0.0, F1 = 1.0;
 
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
                        double part1 = 3*F1; 
                    
                        // The variable used to store 7 * f( n - 2 )
                        double part2 = 7*F0; 
                        

                        // The variable is the final result of the function.
                        double F2 = part1+part2;
                                               
                        System.out.print("f(" +range );   // printf f(
                		System.out.printf(") : %.01f",F2);	// prints ): and value of f2
                		System.out.println("");
                		
                        F0= F1;
                        F1= F2;
                }
        }
} // Linear