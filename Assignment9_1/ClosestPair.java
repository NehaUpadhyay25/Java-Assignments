import java.util.Comparator;

/**
* This class is used to find minimum distance between 2 points in 3D.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class ClosestPair {

    
    Point p1;
    Point p2;
    
    
    /**
     * This method is merge procedure of merge sort.
     * 
     * @param     data     Storage to be sorted.
     * @param     p        Starting point.
     * @param     q        Middle point.
     * @param     r        Ending point.
     * @param     comp     Comparator for sort.
     */
    private void merge( Point[] data, int p, int q, int r, Comparator<Point> comp ){
       int m = q-p+1;
       int n = r-q;
       
       Point[] a = new Point[m];
       Point[] b = new Point[n];
       
       int i = 0, j = 0, k = 0;
       
       for( k = p; k <= q; k++ ){
           a[i++] = data[k];
       }
       
       for( k = q+1; k <= r; k++ ){
           b[j++] = data[k];
       }
       
       i = 0;
       j = 0;
       k = p;
       
       while( i < m && j < n ){
           if( comp.compare( a[i], b[j] ) <= 0 ){
               data[k++] = a[i++];
           }else{
               data[k++] = b[j++];
           }
       }
       
       while( i< m ){
           data[k++] = a[i++];
       }
       
       while( j < n ){
           data[k++] = b[j++];
       }
       
    }
    
    /**
     * This method is used to merge sort elements in the storage.
     * 
     * @param     data     Storage to be sorted.
     * @param     p        Starting point.
     * @param     r        Ending point.
     * @param     comp     Comparator for sort.
     */
    private void mergeSort( Point[] data, int p, int r , Comparator<Point> comp ) {
        if( r <= p ){
            return;
        }
        
        int q = (p+r)/2;
        
        mergeSort(data,p,q,comp);
        mergeSort(data,q+1,r,comp);
        
        merge(data,p,q,r,comp);
    }
    
    /**
     * This method is used to find Euclidian distance between 2 points.
     * 
     * @param     p1     Point 1.
     * @param     p1     Point 2.
     * @return    double Euclidian distance.
     */
    double dist ( Point p1, Point p2 ) {
       return Math.sqrt( Math.pow( p1.x-p2.x , 2) + Math.pow( p1.y-p2.y , 2) +
               Math.pow( p1.z-p2.z , 2)); 
    }
    
    /**
     * This method is used to find minimum distance between 2 points.
     * 
     * @param     p      Array of points.
     * @param     s      Start point of array.
     * @param     s      End point of array.
     * @return    double Minimum distance.
     */
    double closestPair ( Point[] p, int s, int e, double d, Point[] mp ) {
        double min = d;
        double tmp = 0;
        
        mp[0] = p[s];
        mp[1] = p[s];
        
        for( int i = s; i <= e ; i++ ) {
            for( int j = i+1; j <= e; j++ ) {
                tmp = dist(p[i],p[j]);
                if(  tmp < min ) {
                    min = tmp;
                    mp[0] = p[i];
                    mp[1] = p[j];
                    
                }
            }
        }

        return min;
    }
    
    /**
     * This method is used to find minimum distance between 2 points in 1D.
     * 
     * @param     p      Array of points.
     * @param     s      Start point of array.
     * @param     e      End point of array.
     * @param     d      Minimum distance so far.
     * @return    double Minimum distance.
     */
    private double closest1D(Point[] p, int s, int e, double d , Point[] mp) {
        
        double min = d, tmp = 0;
        mergeSort( p, s, e, new ZComparator() );
        
        for ( int i = s; i <= e; i++ ) {
            for ( int j = i+1; j <= e && (p[j].z - p[i].z) < min ; j++ ) {
                tmp = dist(p[i],p[j]);
                if( tmp < min ){
                    min = tmp;
                    mp[0] = p[i];
                    mp[1] = p[j];
                }
            }
        }
        return min;
    }
    
    /**
     * This method is used to find minimum distance between 2 points in 2D.
     * 
     * @param     p      Array of points.
     * @param     s      Start point of array.
     * @param     s      End point of array.
     * @param     d      Minimum distance so far.
     * @return    double Minimum distance.
     */
    private double closest2DUtil(Point[] p, int s, int e, double d, Point[] mp) {
        if( e - s  <= 2 ) {
            return closestPair( p, s, e, d, mp );
        }
        
        int mid = (s+e)/2;
        Point[] pl = new Point[2];
        Point[] pr = new Point[2];
        double dl = closest2DUtil( p, s, mid, d, pl );
        double dr = closest2DUtil( p, mid+1, e, d, pr );
        
        double min = Math.min(dl, dr);
        if( dl <=  dr ){
            mp[0] = pl[0];
            mp[1] = pl[1];
        }else {
            mp[0] = pr[0];
            mp[1] = pr[1];
        }
        
        
        int n = e-s+1;
        Point[] strip = new Point[n];
        int j = 0;
        
        for( int i = s; i <= e ;i++ ) {
            if( Math.abs(p[i].y-p[mid].y) < min ) {
                strip[j++] = p[i];
            }
        }
        
        Point[] d1 = new Point[2];
        double min_d1 = closest1D(strip,0,j-1,min,d1);
        if( min_d1 < min ) {
            min = min_d1;
            mp[0] = d1[0];
            mp[1] = d1[1];
        }
        
        return min;
    }

    /**
     * This method is used to find minimum distance between 2 points in 2D.
     * 
     * @param     p      Array of points.
     * @param     s      Start point of array.
     * @param     e      End point of array.
     * @param     d      Minimum distance so far.
     * @return    double Minimum distance.
     */
    private double closest2D(Point[] p, int s, int e, double d, Point[] mp) {
        
        mergeSort( p, s, e, new YComparator() );
        
        return closest2DUtil( p, s, e, d, mp);
        
    }
 
    /**
     * This method is used to find minimum distance between 2 points in 3D.
     * 
     * @param     p      Array of points.
     * @param     s      Start point of array.
     * @param     e      End point of array.
     * @return    double Minimum distance.
     */
    private double closest3DUtil ( Point[] p, int s, int e, Point[] mp ) {
        if( e - s  <= 2 ) {
            return closestPair( p, s, e, Double.MAX_VALUE, mp);
        }
        
        int mid = (s+e)/2;
        Point[] pl = new Point[2];
        Point[] pr = new Point[2];
        double dl = closest3DUtil( p, s, mid, pl );
        double dr = closest3DUtil( p, mid+1, e, pr );
        
        double min = Math.min(dl, dr);
        if( dl <=  dr ){
            mp[0] = pl[0];
            mp[1] = pl[1];
        }else {
            mp[0] = pr[0];
            mp[1] = pr[1];
        }
        
        
        int n = e-s+1;
        Point[] plane = new Point[n];
        int j = 0;
        
        for( int i = s; i <= e ;i++ ) {
            if( Math.abs(p[i].x-p[mid].x) < min ) {
                plane[j++] = p[i];
            }
        }
        
        Point[] d2 = new Point[2];
        double min2D = closest2D(plane,0,j-1,min, d2);
        
        
        if( min2D < min ) {
            mp[0] = d2[0];
            mp[1] = d2[1];
            min = min2D;
        }
        
        return min;
    }

    /**
     * This method is used to find minimum distance between 2 points in 3D.
     * 
     * @param     p      Array of points.
     * @param     s      Start point of array.
     * @param     e      End point of array.
     * @return    double Minimum distance.
     */
    double closest3D ( Point[] p, int s, int e ) {  
        
        mergeSort( p, s, e, new XComparator() );
        Point[] mp = new Point[2];
        double min = closest3DUtil( p, s, e,mp );
        p1 = mp[0];
        p2 = mp[1];
        
        return min;
         
    }
    
    /**
     * This method is used to print 2 points with minimum distance between them in 3D.
     */
    public void printClosestPair() {
        System.out.println(p1.x+" "+p1.y+" "+p1.z);
        System.out.println(p2.x+" "+p2.y+" "+p2.z);
    }
    
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
        int  i = 1, j = 0;
        
        for( j = 0; j < n; j++ ) {
            p[j] = new Point();
            p[j].x = Integer.parseInt(args[i++]);
            p[j].y = Integer.parseInt(args[i++]);
            p[j].z = Integer.parseInt(args[i++]);
        }
        
        
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
