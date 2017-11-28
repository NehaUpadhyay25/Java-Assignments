package assignment2;

/*
 * FileName : :Pi.java
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
 * Description : The class below determines the value of Pi.
 * 
 * @author Neha Upadhyay
 * @author Kritka Sahni
 *
 */

class Pi
{
    /**
     * The method below converts the negative value of fx into 
     * positive value and then compares it.
     */
    public static double positive(double val)
    {
        if(val < 0)
        {
            return ((-1.0) * val);
        }
        else
        {
            return val;
        }
    }

    /**
     * This method is the used to calculate value of pi.
     * 	
     * @param    delta    Delta to calculate pi
     * @return   double   Value of pi is returned.
     */
    public static double pi( double delta ) {
        long N = 0; // variable used to check for N values
        double base = 1.0; // denominator
        double fx = 0.0; // current value of function
        double correct_fx = 0.00; // previous value of function
        
        do
        {
            correct_fx=fx; // passes value of fx in correct fx
            if(N % 2 == 0) // checks for even value of N
            {
                fx = fx + (4.0 / base);  //positive sign for the even terms
            }
            else
            {
                fx = fx - (4.0 / base);	 // negative for the odd term
            }
            base = base + 2; // increments the denominator			
            N = N + 1; // increment N for different values 
        }while( positive((fx-correct_fx)) > delta); // checks for precision with delta
        
        return fx;
    }
    
    /*
     * The main method below checks for the value of pi and then
     * prints it.
     */
    public static void main(String [] args)
    {
        
        double delta = 0.000001; // to check diff between fx and correct fx

        double pi = pi( delta );
        System.out.printf( "pi( %.1E ) = ", delta );
        System.out.print(pi);
    }
}
