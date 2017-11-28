package Assignment7_2;

import java.util.Comparator;

/**
* This class used compares integers according to ascending order.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class AscendingComparator implements Comparator<Integer>{
    
    /**
     * This method is used to compare integers according to user-defined order.
     * 
     * @param     a       Integer to be compared.
     * @param     b       Integer to be compared.
     * @return    int     0 if equal, negative integer when less than 
     *                    or positive integer when greater.
     */
    public int compare( Integer left, Integer right ){   
        
            return left.compareTo(right);
       
    }
    
}  //  AscendingComparator