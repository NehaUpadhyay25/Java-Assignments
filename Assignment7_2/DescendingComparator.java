import java.util.Comparator;

/**
* This class used compares integers according to descending order.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class DescendingComparator implements Comparator<Integer>{
    
    /**
     * This method is used to compare integers according to user-defined order.
     * 
     * @param     a       Integer to be compared.
     * @param     b       Integer to be compared.
     * @return    int     0 if equal, negative integer when less than 
     *                    or positive integer when greater.
     */
    public int compare( Integer left, Integer right ){   
        
            return right.compareTo(left);
       
    }
    
} // DescendingComparator
