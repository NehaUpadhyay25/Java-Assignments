import java.util.Comparator;
import java.util.Scanner;

/**
* This class represents the StorageOrder according to a user-defined order.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class StorageOrderCustom {

    int initialSize = 1;
    int filled  = 0;
    Integer[]    data = new Integer[initialSize];
    static int    interatorPosition = 0;
    
    private Comparator<Integer> comp = null;
    
    public StorageOrderCustom( Comparator<Integer> comp )   {
        this.comp = comp;
        for (int index=0; index<data.length-1; index++)     {
            data[index] =  null;
        }
        
    }
    
    /**
     * This method is used to copy elements.
     * 
     * @param     data     Storage to be sorted.
     * @param     p        Starting point.
     * @param     q        Middle point.
     * @param     r        Ending point.
     */
    public void copy(Integer[] to, Integer[] from)  {
        for (int index=0; index< filled ; index++)     {
            to[index] = from[index];
        }
            
    }
    
    /**
     * This method is merge procedure of merge sort.
     * 
     * @param     data     Storage to be sorted.
     * @param     p        Starting point.
     * @param     q        Middle point.
     * @param     r        Ending point.
     */
    private void merge( Integer[] data, int p, int q, int r ){
       int m = q-p+1;
       int n = r-q;
       
       Integer[] a = new Integer[m];
       Integer[] b = new Integer[n];
       
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
     */
    private void sortUtil( Integer[] data, int p, int r ){
        if( r == p ){
            return;
        }
        
        int q = (p+r)/2;
        
        sortUtil(data,p,q);
        sortUtil(data,q+1,r);
        
        merge(data,p,q,r);
    }
    
    /**
     * This method is used to sort elements in the storage.
     * 
     */
    public void sort() {
        
        if ( data.length == 1 ){
            return;
        }
        
        long startTime = System.nanoTime();
        sortUtil(data, 0, filled-1);
        long endTime = System.nanoTime();
        System.out.println("(Time to sort in nano seconds: "+ ( endTime - startTime)+")\n" );
        
    }
    
    /**
     * This method is used to return String representation of the storage.
     * @return    String    String representation of the storage.
     */
    public String toString()   {
        String result = "";
        for (int index = 0; index<data.length; index++)     {
            if ( data[index] != null )
                result = "" + index + ". " + data[index] +
            ( ( index == data.length - 1 ) ? ".\n" : ",\n" ) + result;
        }
        return result;
    }
    
    /**
     * This method is used to internally double capacity of storage.
     *  
     */
    private void increaseCapacity() {
        initialSize *= 2;
        Integer[] newData = new Integer[initialSize];
        copy( newData, data );
        data = newData;
    }
    
    /**
     * This method is used to insert element in the storage.
     * 
     * @param     e       Element to be inserted.
     * @return    boolean true if success else false 
     */
    public boolean add(Integer e) {
        
        if( filled == data.length ){
            //Here means its completely filled so double capacity.
            increaseCapacity(); 
        }
        
        data[filled++] = e;
        sort();
        return true;
    
    }
     
    /**
     * This method is used to remove elements from the storage.
     * 
     * @param     e       Element to be removed.
     * @return    boolean true if success else false 
     */
    public boolean remove(Integer e)    {
        for (int index = 0; index < filled; index++)     {
            if (  comp.compare(data[index],e) == 0 )   {
                data[index] = data[filled-1];
                data[filled-1] = null;
                filled --;
                sort();
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method is used to determine if element is present in the storage.
     * 
     * @param     e       Element to be checked.
     * @return    boolean true if yes else false 
     */
    public boolean contain(Integer e)   {
        for (int index = 0; index < filled; index++)     {
            if (  comp.compare(data[index],e) == 0   )
                return true;
        }
        return false;
    }
    
    /**
     * This method is used to determine size of the storage.
     *
     * @return    int    number of elements
     */
    public int size()   {
        return filled;
    }
    
    /**
     * This method is used to add start traversal from beginning of the storage.
     *
     */
    public void startFromBeginning() {
        interatorPosition = 0;
    }
    
    /**
     * This method is used to determine if at least 1 more element present in the storage.
     *
     * @return   boolean   true if yes else false 
     */
    public boolean hasNext()    {
        return ( interatorPosition < filled );
    }
    
    /**
     * This method is used to return next element present in the storage.
     * 
     * @return    Integer    next element returned
     */
    public Integer next()   {
        
        return data[interatorPosition++];
    }
    
    /**
     * This method is used to add elements present in one storage to another storage.
     *
     * @param    c     storage whose elements to be entered 
     */
    public boolean addAll(StorageOrderCustom c)   {
        while ( c.hasNext() ) {
               add(c.next() );
        }
        
        return true;
    }
    
    /**
     * This method is used to evaluate elements in the storage.
     *
     */
    public double evaluate()    {
        double result = 0;
        while ( hasNext() ) {
            Integer aInteger = next();
            // added a check for null value in array and 0  
            if(aInteger!=null && aInteger!=0){
               result += 1.0 / aInteger;
            }
        }
        return result;
    }
    
    /**
     * This method is the main method.
     * 
     * @param    args    command line arguments(ignored) 
     */
    public static void main(String args[] )     {
        
        Scanner sc = new Scanner(System.in);
        System.out.println( "Do you want to sort integers in ascending order(1) "
                + "or descending order(2)?");
        
        int type = sc.nextInt();
        
        sc.close();
        
        Comparator<Integer> comp = (type == 1) ? new AscendingComparator() : 
                                                 new DescendingComparator();
        StorageOrderCustom aStorageOrder = new StorageOrderCustom( comp );
        
        aStorageOrder.add(3);
        aStorageOrder.add(1);
        aStorageOrder.add(0);
        aStorageOrder.add(2);

        System.out.println("aStorageOrder.size(); " + aStorageOrder.size() );
        System.out.println("aStorageOrder.evaluate(); " + aStorageOrder.evaluate() );
        System.out.println(aStorageOrder);
        System.out.println("aStorageOrder.contains(a)  " + aStorageOrder.contain(0));
        System.out.println("aStorageOrder.contains(f)  " + aStorageOrder.contain(4));
        System.out.println("aStorageOrder.remove(a)  " + aStorageOrder.remove(2));
        System.out.println("aStorageOrder.remove(a)  " + aStorageOrder.remove(1));
        System.out.println(aStorageOrder);
        aStorageOrder.startFromBeginning();
        while ( aStorageOrder.hasNext() ) {
                System.out.println("    " + aStorageOrder.next() ) ;
        }

    }
} //StorageOrderCustom

