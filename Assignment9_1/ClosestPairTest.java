package Assignment9_1;

import java.util.Random;

/**
* This class is used to test fiding minimum distance between 2 points in 3D using multiple threads.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class ClosestPairTest {

    /**
     * This method is the main method.
     * 
     * @param    args    command line arguments 
     */
    public static void main ( String... args) {
        int n = Integer.parseInt(args[0]);
        if( n == 1 ){
            System.out.println("Atleast 2 points needed.");
            return;
        }
        
        Point[] p = new Point[n];
        
        Random rand = new Random();
        
        for( int j = 0; j < n; j++ ) {
            p[j] = new Point();
            p[j].x = rand.nextInt(100);
            p[j].y = rand.nextInt(100);
            p[j].z = rand.nextInt(100);
            
        } 
        
        /*for( int j = 0; j < n; j++ ) {
            p[j] = new Point();
            p[j].x = Double.parseDouble(args[i++]);
            p[j].y = Double.parseDouble(args[i++]);
            p[j].z = Double.parseDouble(args[i++]);
        }*/
        
        ClosestPair pair = new ClosestPair();
        long startTime = System.currentTimeMillis();
        double min = pair.closest3D( p, 0, n-1 );
        long endTime = System.currentTimeMillis();
        
        
        System.out.println("Distance between the closest pair of points is: "+ min );
        System.out.println("Points are:");
        pair.printClosestPair();
        
        System.out.println("(Time to find closest pair in milli seconds (single threaded): "+ ( endTime - startTime)+")\n" );
        
    }
}
