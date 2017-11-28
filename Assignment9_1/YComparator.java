import java.util.Comparator;

/**
* This class used compares points according to y coordinate in ascending order.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class YComparator implements Comparator<Point>{
    
    /**
     * This method is used to compare integers according to user-defined order.
     * 
     * @param     a       Integer to be compared.
     * @param     b       Integer to be compared.
     * @return    int     0 if equal, negative integer when less than 
     *                    or positive integer when greater.
     */
    public int compare( Point left, Point right ){   
        
            return (left.y).compareTo(right.y);
       
    }
    
}  //  AscendingComparator