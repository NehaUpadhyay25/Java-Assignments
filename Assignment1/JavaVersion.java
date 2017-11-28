package assignment1;

/*
 * FileName : JavaVersion.java
 * 
 * Version : 1.0
 * 
 * author : Neha Upadhyay 
 * 
 * Revisions: Initial Revision
 * 
 */

import java.util.Properties;

/**
 * Description : The below class prints the version of java used in the machine
 *  using the System.getProperty function of the System Property library of 
 *  Java
 *  
 * @author Neha Upadhyay
 *
 */

class JavaVersion {	
	/**
	 * The main method prints the version of Java using the java.version method
	 * and simultaneously prints it.
	 * 
	 *  @param    args    command line arguments(ignored)
	 */
	public static void main( String [] args) {
		
		System.out.print( "Java Version : " ); // prints Java Version :
		
		// prints the version of java in use
		
		System.out.println( (String)System.getProperty( "java.version" ) ); 
	}
} // JavaVersion
