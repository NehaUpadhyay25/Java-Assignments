package Assignment9_1;

import java.util.Comparator;

/**
* This class used compares points according to z coordinate in ascending order.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class ZComparator implements Comparator<Point>{
    
    /**
     * This method is used to compare integers according to user-defined order.
     * 
     * @param     a       Integer to be compared.
     * @param     b       Integer to be compared.
     * @return    int     0 if equal, negative integer when less than 
     *                    or positive integer when greater.
     */
    public int compare( Point left, Point right ){   
        
            return (left.z).compareTo(right.z);
       
    }
    
}  //  AscendingComparator