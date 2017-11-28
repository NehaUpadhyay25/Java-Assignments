package assignment1;

/*
 * FileName : Pikachu.java
 * 
 * Version : 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay
 * 
 * Revisions: Initial Revision
 * 
 */

/**
 * The class below determines whether you can or cannot purchase a Pikachu figure
 * of a given price from a given set of coins. It will print a 'yes' if you 
 * can purchase the figure else it will print a 'no'.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */

public class Pikachu {
	
	/**
	 * This method is internally used in the class to find power of 'a' raised 
	 * to 'b'.
	 * 
	 * @param     a         Base
	 * @param     b         Exponent
	 * @return    String    Returns 'yes' if purchase is possible else 'no'.
	 */
	
	private int power( int a, int b ) {
		int pow = 1;
		
		for ( int i = 0; i < b ; i++ ) {
			pow *= a;
		}
		return pow;
	}
	
	/**
	 * This method determines if it is possible to purchase a Pikachu figure
	 * of a given price from a given set of coins.
	 * 
	 * @return    String    Returns 'yes' if purchase is possible else 'no'.
	 */
	
	public String canPurchase() {
		
		// Given coins.
		int[] coins = {1, 2, 5, 10, 10, 25, 25, 25, 50};
		
		// Given price	
	    int price = 53;             
	    
	    // Total number of given coins.
	    int noOfCoins = coins.length;
	    
	    // Total number of subsets possible from the given set of coins i.e.
	    // size of the power set of the given set of coins.
	    int totalSubsets = power ( 2, noOfCoins );
	    
	    int sumSoFar = 0;
	    
	    // Find all the subsets of given set of coins and determine if their 
	    // sum equals the given price or not. 
	    // It returns yes as soon as the sum equals the given price.
	    for ( int i = 1; i < totalSubsets; i++ ) {
	    	
	    	for ( int j = 0; j < noOfCoins; j++ ) {
	    		
	    		if ( ( i & ( 1 << j ) ) != 0) {
	    			
	    			sumSoFar += coins[j]; 
	    			
	    			if ( sumSoFar == price ) {
	    				return "yes";
	    			}
	    		}
	    	}
	    	
	    	// Re-initialize sum for new subsets to be calculated in the
	    	// next iteration.
	    	sumSoFar = 0;
	    }
	    
	    // No possible combination of coins was found that equals the given price.
	    return "no";
	}
	
	/**
	 * This method is the main method.
	 * 
	 * @param    args    command line arguments(ignored) 
	 */
	
	public static void main( String [] args ) {
		
		// obj of type Pikachu is created so that its method could be called
		// to determine whether it is possible to purchase a Pikachu figure.
		// The result is stored and printed.
		
		Pikachu obj = new Pikachu();
		String canPurchase = obj.canPurchase();
		System.out.println( canPurchase );		
	}
} // Pikachu
