package Assignment7_3;

import java.util.Comparator;
import java.util.Scanner;

/**
* This class used implements StorageOrder using user defined order.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class StorageOrderHandled <E>{

    int initialSize = 1;
    int filled  = 0;
    @SuppressWarnings("unchecked")
    E[] data = (E[])new Object[initialSize];
    static int    interatorPosition = 0;
    Comparator<E> comp = null;
    
    /**
     * This is the constructor which initializes array to null.
     * 
     * @param 	comp 	Object of Comparator
     */
    public StorageOrderHandled( Comparator<E> comp )   {
        this.comp = comp;
        for (int index=0; index<data.length-1; index++)     {
            data[index] =  null;
        }
        
    }
    
    /**
     * This method is used to copy values from one array to other.
     * 
     * @param    to    Array the values are to be copied in
     * @param    from  Array from which the values are to be copied. 
     */
    
    
    public void copy(E[] to, E[] from)  {
        for (int index=0; index< filled ; index++)     {
            to[index] = from[index];
        }
            
    }
    
    /**
     * This method is merge method of merge sort.
     * 
     * @param     data     Storage to be sorted.
     * @param     p        Starting point.
     * @param     q        Middle point.
     * @param     r        Ending point.
     */
    @SuppressWarnings("unchecked")
    private void merge( E[] data, int p, int q, int r ){
       int m = q-p+1;
       int n = r-q;
       
       E[] a = (E[])new Object[m];
       E[] b = (E[])new Object[n];
       
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
    private void sortUtil( E[] data, int p, int r ){
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
        if ( data.length == 1 )
            return;
        
        sortUtil(data, 0, filled-1);
        
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
        @SuppressWarnings("unchecked")
        E[] newData = (E[])new Object[initialSize];
        copy( newData, data );
        data = newData;
    }
    
    /**
     * This method is used to insert element in the storage.
     * 
     * @param     e       Element to be inserted.
     * @return    boolean true if success else false 
     */
    
    public boolean add(E e){
        
       if ( e == null ){
           return false;
       }
       
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
    
    public boolean remove(E e)    {
        
        try{
            for (int index = 0; index < filled; index++)     {
                if (  comp.compare(data[index],e) == 0 )   {
                    data[index] = data[filled-1];
                    data[filled-1] = null;
                    filled --;
                    sort();
                    return true;
                }
            }
        }catch(NullPointerException npe){
            System.out.println("null value detected in remove()");
                
        }
        
        return false;
    }
    
    /**
     * This method is used to determine if element is present in the storage.
     * 
     * @param     e       Element to be checked.
     * @return    boolean true if yes else false 
     */
    
    public boolean contain(E e)   {
        
        try{
            for (int index = 0; index < filled; index++)     {
                if (  comp.compare(data[index],e) == 0   )
                    return true;
            }
        
        }catch(NullPointerException npe){
            System.out.println("null value detected in contain()");      
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
    
    public E next()   {
        
        return data[interatorPosition++];
    }
    
    /**
     * This method is used to add elements present in one storage to another storage.
     *
     * @param    c     storage whose elements to be entered 
     */
    
    public boolean addAll(StorageOrderHandled<E> c)   {
        assert ( interatorPosition == 0 ):"need to start from beginning";
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
        assert (interatorPosition == 0 ):"need to start from beginning";
        while ( hasNext() ) {
            
            Integer aInteger = (Integer) next();
            // added a check for null value in array and 0  
            if(aInteger!=null && aInteger!=0){
                result += 1.0 / aInteger ;
            }
        }
        assert result!=Double.POSITIVE_INFINITY;
        return result;
    }
    
    /**
     * This method is used to perform test for Integer values in array.
     * 
     * @param    type    Ascending order of elements or Descending order 
     */
    
    public static void testIntegerStorage( int type ) {

        Comparator<Integer> comp = (type == 1) ? new GenericAscendingComparator<Integer>() : 
            new GenericDescendingComparator<Integer>();
        
        StorageOrderHandled<Integer> aStorageOrder = new StorageOrderHandled<Integer>( comp );
        
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
        
        StorageOrderHandled<Integer> aStorageOrder2 = new StorageOrderHandled<Integer>( comp );
        
        aStorageOrder2.add(6);
        aStorageOrder2.add(4);
        aStorageOrder2.add(5);
        
        aStorageOrder.addAll(aStorageOrder2);
        System.out.println(aStorageOrder);
    }
    
    /**
     * This method is used to perform test for String values in array.
     * 
     * @param    type    Ascending order of elements or Descending order 
     */
    
    public static void testStringStorage( int type ) {
        
        
        Comparator<String> comp = (type == 1) ? new GenericAscendingComparator<String>() : 
            new GenericDescendingComparator<String>();

        StorageOrderHandled<String> aStorageOrder = new StorageOrderHandled<String>( comp );
        
        aStorageOrder.add("a");
        aStorageOrder.add("b");
        aStorageOrder.add("c");
        aStorageOrder.add("d");

        System.out.println("aStorageOrder.size(); " + aStorageOrder.size() );
        System.out.println(aStorageOrder);
        System.out.println("aStorageOrder.contains(a)  " + aStorageOrder.contain("a"));
        System.out.println("aStorageOrder.contains(f)  " + aStorageOrder.contain("e"));
        System.out.println("aStorageOrder.remove(a)  " + aStorageOrder.remove("b"));
        System.out.println("aStorageOrder.remove(a)  " + aStorageOrder.remove("c"));
        System.out.println(aStorageOrder);
        aStorageOrder.startFromBeginning();
        while ( aStorageOrder.hasNext() ) {
            System.out.println("    " + aStorageOrder.next() ) ;
        }
    }
    
    /**
     * This method is the main method.
     * 
     * @param    args    command line arguments(ignored) 
     */
    
    
    public static void main(String args[] )     {
        Scanner sc = new Scanner(System.in);
        System.out.println( "Do you want to sort elements in ascending order(1) "
                + "or descending order(2)?");
        
        int type = sc.nextInt();
        
        sc.close();
        
        
        testIntegerStorage(type);
        testStringStorage(type);
       

    }
} // StorageOrderHandled

