import java.util.Random;

/**
* This class is used to test finding minimum distance between 2 points in 3D using multiple threads.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class ClosestPairMultiThreadedTest {

    /**
     * This method is the main method.
     * 
     * @param    args    command line arguments 
     */
    public static void main ( String... args ) {
        int n = Integer.parseInt(args[0]);
        if( n == 1 ){
            System.out.println("Atleast 2 points needed.");
            return;
        }
        
        
        Point[] p = new Point[n];
        
        int  i = 1, j = 0;
      
        Random rand = new Random();
        for( j = 0; j < n; j++ ) {
            p[j] = new Point();
            p[j].x = rand.nextInt(100);
            p[j].y = rand.nextInt(100);
            p[j].z = rand.nextInt(100);
            
        } 

        ClosestPairMultiThreaded finalPair = new ClosestPairMultiThreaded();
        finalPair.mergeSort( p, 0, n-1, new XComparator() );
        
        int s = 0, e = 0;
        
        int cores = Runtime.getRuntime().availableProcessors();
        boolean validForThreads = false;
        
        
        if ( n / cores >= 3 ) {
            validForThreads = true;
        } else {
            cores--;
            while( cores > 1 ) {
                
                if (n / cores >= 3 )
                {
                    validForThreads = true;
                    break;
                }
                cores--;
            }
            
        }
        
        if ( validForThreads == true ) { // we can carry out calculations simultaneously if each thread has substantial 
                                         // work to execute.

            
            ClosestPairMultiThreaded[] pairs = new ClosestPairMultiThreaded[cores];
            ClosestPairThread[] threads = new ClosestPairThread[cores];
            for (i = 0; i < cores - 1; i++) {
                s = i * ((n - 1) / cores);
                e = s + ((n - 1) / cores) - 1;
                pairs[i] = new ClosestPairMultiThreaded();
                threads[i] = new ClosestPairThread(pairs[i], p, s, e);
            }

            // make last thread
            s = i * ((n - 1) / cores);
            e = s + n - s - 1;
            pairs[i] = new ClosestPairMultiThreaded();
            threads[i] = new ClosestPairThread(pairs[i], p, s, e);

            // start threads and wait for their results
            long startTime = System.currentTimeMillis();
            for (i = 0; i < cores; i++) {
                threads[i].start();
            }
            try {
                for (i = 0; i < cores; i++) {

                    threads[i].join();

                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            //long endTime = System.currentTimeMillis();

            double min = Double.MAX_VALUE;
            ClosestPairMultiThreaded minSoFar = null;
            Point[] mp = new Point[2];
            for (i = 0; i < cores; i++) {
                if (threads[i].getMin() < min) {
                    min = threads[i].getMin();
                    minSoFar = threads[i].getPair();

                }

            }
            
            finalPair = minSoFar;

            int mid = 0, k = 0;
            Point[] plane = null;

            Point[] d2 = new Point[2];
            double min_d2 = 0;
            // find closest pair around each point
            for (i = 0; i < cores - 2; i++) {
                s = i * ((n - 1) / cores);
                mid = s + ((n - 1) / cores) - 1;// end of left array
                e = (i + 1) * ((n - 1) / cores) + ((n - 1) / cores) - 1;
                plane = new Point[e+1];
                k = 0;
                
                // for all x's in min of these, find closest points at distance < min
                for (j = 0; j <= e; j++) {
                    if (Math.abs(p[j].x - p[mid].x) < min) {
                        plane[k++] = p[j];
                    }
                }

                min_d2 = finalPair.closest2D(plane, 0, k - 1, min, d2);
                if (min_d2 < min) {
                    min = min_d2;
                    mp[0] = d2[0];
                    mp[1] = d2[1];
                    finalPair.setPair(mp);
                }
            }

            // for last thread
            s = i * ((n - 1) / cores);
            mid = s + ((n - 1) / cores) - 1;// end of left array
            e = s + n - s - 1;
            plane = new Point[e - s + 1];
            k = 0;

            // for all x's in min of these, find closest points at distance <
            // min
            for ( j = 0; j <= e; j++ ) {
                if (Math.abs(p[j].x - p[mid].x) < min) {
                    plane[k++] = p[j];
                }
            }

            min_d2 = finalPair.closest2D(plane, 0, k - 1, min, d2);
            if ( min_d2 < min ) {
                min = min_d2;
                mp[0] = d2[0];
                mp[1] = d2[1];
                finalPair.setPair(mp);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(
                    "Distance between the closest pair of points is: " + min);
            System.out.println("Points are:");
            finalPair.printClosestPair();

            System.out.println("(Time to find closest pair in milli seconds ("
                    + ClosestPairThread.getCount() + "): " + (endTime
                            - startTime) + ")\n");

        }
        else {

            long startTime = System.currentTimeMillis();
            // just 1 thread
            double min = finalPair.closest3D(p, 0, n - 1);

            long endTime = System.currentTimeMillis();

            System.out.println(
                    "Distance between the closest pair of points is: " + min);
            System.out.println("Points are:");
            finalPair.printClosestPair();

            System.out.println(
                    "(Time to find closest pair in milli seconds (1): "
                            + (endTime - startTime) + ")\n");

        }
        
    }
}
