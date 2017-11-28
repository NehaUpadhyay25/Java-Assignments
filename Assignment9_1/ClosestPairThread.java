package Assignment9_1;

/**
* This class represents an independent thread to carry out task of finding 2 points with minimum distance.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class ClosestPairThread extends Thread{

    static int count;
    ClosestPairMultiThreaded pair;
    Point[] pts;
    int start;
    int end;
     
    ClosestPairThread( ClosestPairMultiThreaded obj, Point[] p, int s, int e) {
        pair = obj;
        pts = p;
        start = s;
        end = e;
        count++;
    }
    
    /**
     * This method finds closest pair for each thread.
     * 
     */
    public void run(){
      pair.closest3D(pts, start, end);  
    }
    
    public double getMin() {
        return pair.getMin();
    }
    
    public ClosestPairMultiThreaded getPair() {
        return pair;
    }
    
    public static int getCount() {
        return count;
    }
    
    
    
}
