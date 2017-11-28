package assignment1;

/*
 * FileName : Pikachu2.java
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
 * Description : The class below makes subsets of the values 
 * and adds them to check whether the sum equals the value
 * 53 that is the price of a pikachu.
 * 
 * @author Neha Upadhyay
 * @author Kritka Sahni
 *
 */


public class CoinsSubsets {
	
	static int[] coins = new int[]{1,2,5,10,10,25,25,25,50}; // stores coins
	static int counter = 0, cost = 53; // stores counter and price of pikachu
	
	/*
	 * The main method which calculates the value of subsets , compares 
	 * the value with the price of pikachu and prints whether yes or no
	 * 
	 */
	public static void main( String [] args ) {
		
		// runs till the values of coins
		for ( int subsets_range = 1; subsets_range <= coins.length; subsets_range++ ) {
        compute_subset( 0, 0, subsets_range );
		}
	    
	    // prints yes if value matches the price
	    if ( counter == 1 ) {
	    	System.out.println( "Yes" );
	    }
	    
	    // prints no if value doesn't match the price
	    else {
	    	System.out.println( "No" );
	    }
	}
	
	/*
	 * The method below computes the subsets and adds the value in the set 
	 * and computes the counter which is send in the main method to compute
	 * yes or no.
	 * 
	 */ 
	static void compute_subset( int start_index, int end_index, int num_elements ) {
		
		// stores the values of different variables to be used
		int range1, range2, sum_subset = 0, temporary_sum = 0, sum = 0;
		
		// compares the difference of index with number of elements
		if ( end_index - start_index + 1  ==  num_elements ) {
			
			// checks for the subsets with 1 value
			if ( num_elements  ==  1 ) {
				
				// runs till the number of coins available
				for ( range1 = 0; range1 < coins.length; range1++ ){
					sum_subset = coins[ range1 ];
				}
				
				// makes counter = 1 if single value in set is 53
				if( sum_subset == cost ){
					counter = 1;
				}
			}
			else {
				
				// runs till the number of coins available
				for ( range1 = end_index; range1 < coins.length; range1++ ) {
					sum = 0;
				
					// runs till the end index of the set  
					for ( range2 = start_index; range2 < end_index; range2++ ) {
						// adds the value of subsets
						sum = sum + coins[ range2 ];
					}
				
					// stores the value in a temporary variable 
					temporary_sum = sum;
					temporary_sum = sum + coins[ range1 ];
		        
					// makes counter 1 if value of subset matches that of pikachu
					if( temporary_sum == cost ) {
						counter = 1;
					}
				}
			
				// makes other subsets with the same value 
				//ex: makes other subsets for order 3
				if ( start_index != coins.length - num_elements ) {
					compute_subset( start_index + 1, start_index + 1, num_elements );
				}
			}
		}
		else {
			// runs again to make other subsets with different value
			compute_subset( start_index, end_index + 1, num_elements );
		}
	}	
}