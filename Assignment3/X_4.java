package Assignment3;/*
 * X_4.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */

import java.util.Scanner;
									// a different package(java.util) so need to import it.
									// By importing Scanner here, X_4 can refer to it by 
									// directly using its name.

/**
 * This program creates a Scanner object which prints the strings separated
 * by the delimiters.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */

public class X_4 {

  static String inputForScanners[] = {
	"a b c d e f g", 			// delimiter = one whitespace
	"a   b c    d   e f   g", 		// delimiter = one or more whitespaces
	"a1b2c3d4e5f6g", 			// delimiter = one number between 1 and 9
	"a11b2c3d4e5f10g111h", 			// delimiter = one number between 1 and 11
	"a123b123c23456d23456e1f222123g",	// delimiter = one or more number between 1 and 9
	"a23b123c23456d23456e111f222123g", 	// delimiter = two or more number between 1 and 9
	"a123b1c12d1e12f12g", 			// delimiter = numbers in series from 1 to 3
	"a111b11c1d1e1fg", 			// delimiter = zero up to 3 1's
	"aenebenecenedeneeenefeneg", 		// delimiter = ene
	"brcsdtexfyg", 				// delimiter = r, s, t, x, y
	"b1c2d9e8f5g", 				// delimiter = 1 through 9, but not 4-7
	"bAcBdCeDfZg", 				// delimiter = lowerCase
				   };

  /**
   * This method is the main method.
   * 
   * @param    args    command line arguments(ignored) 
   */
  
  public static void main(String args[]) {
	for (int index = 0; index < inputForScanners.length; index ++ ) {
		Scanner aScanner = new Scanner(inputForScanners[index]);
		switch ( index ) {
			case 0: aScanner.useDelimiter(" ");
				break;
			case 1: aScanner.useDelimiter("\\s+");
				break;
			case 2: aScanner.useDelimiter("[1-9]");
				break;
			case 3: aScanner.useDelimiter("1[01]?|[1-9]");
				break;
			case 4: aScanner.useDelimiter("[1-9]+");
				break;
			case 5: aScanner.useDelimiter("[1-9][1-9]+");
				break;
			case 6: aScanner.useDelimiter("(123)|(12)|(1)");
				break;
			case 7: aScanner.useDelimiter("1{0,3}");//1?1?1?
				break;
			case 8: aScanner.useDelimiter("ene");
				break;
			case 9: aScanner.useDelimiter("[rstxy]");
				break;
			case 10: aScanner.useDelimiter("[1-9&&[^4-7]]");
				break;
			case 11: aScanner.useDelimiter("[a-z]");
				break;
			default: aScanner = new Scanner(System.in);
		}
	
		while ( aScanner.hasNext() )			
			System.out.println(index + ".	" + aScanner.next() );
	}	
  }
} // X_4