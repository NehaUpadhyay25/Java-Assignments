
import java.lang.reflect.Array; 
import java.util.Arrays;

/**
* This class represents the StorageOrder. The errors in this class
* were fixed with help of debugger.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/


public class StorageOrder {

    // increased initial size from 1 to 10
    int initialSize = 10;
    int filled  = 0;
    Integer[]    data = new Integer[initialSize];
    Integer[]    localData ;
    static int    interatorPosition = 0;

    /**
     * This is the constructor of the class which updates
     * the array as null.
     * 
     */
    public StorageOrder()   {
        for (int index=0; index<data.length-1; index++)     {
            data[index] =  null;
        }
        
    }
    
    /**
     * This method is used to copy elements.
     * 
     * @param     to          Array in which values are to be copied .
     * @param     from        Array from which values are to be copied.
     * 
     */
    public void copy(Integer[] to, Integer[] from)  {
        for (int index=0; index< filled ; index++)     {
            to[index] = from[index];
        }
            
    }
    /**
     * The method below sorts the array.
     * 
     */
    public void sort() {
        if ( data.length == 1 )
            return;
        localData = new Integer[ filled ]; 
        copy(localData, data);
        for (int index=0; index<localData.length - 1; index++)     {
            for (int walker=0; walker<localData.length - index - 1; walker++)  {
            Integer left = localData[walker];
            Integer right = localData[walker+1];
            if ( left.compareTo( right ) > 0 )        {
                Integer tmp = localData[walker];
                localData[walker] = localData[walker + 1];
                localData[walker+1] = tmp;
            }
            }
        }
                copy(data, localData);
    }
    
    /**
     * This method is used to return Integer representation of the storage.
     * @return    Integer    Integer representation of the storage.
     */
    public String toString()   {
        String result = "";
        for (int index = 0; index<data.length; index++)     {
            if ( data[index] != null )
                result = "" + index + ". " + data[index] +
            ( ( index == data.length - 1 ) ? "." : ",\n" ) + result;
        }
        return result;
    }
    
    /**
     * This method is used to insert element in the storage.
     * Added "="  sign to remove the error and also have increased
     * size of array "data" so that adding elements is possible
     * 
     * @param     e       Element to be inserted.
     * @return    boolean true if success else false 
     */
    public boolean add(Integer e)   {
        
        for (int index = 0; index<= data.length-1; index++)
            if ( data[index] == null )  {
                data[index] = e;
                filled++;
                sort();
                return true;
            }

        return false;
    }
    
    /**
     * This method is used to remove elements from the storage.
     * 
     * @param     e       Element to be removed.
     * @return    boolean true if success else false 
     */
    public boolean remove(Integer e)    {
        for (int index = 0; index < filled; index++)     {
            if (  data[index].compareTo(e) == 0 )   {
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
            if (  data[index].compareTo(e) == 0  )
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
        return filled-1;
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
    public boolean addAll(StorageOrder c)   {
        while ( c.hasNext() ) {
               add( c.next() );
        }
        return true;
    }
    
    /**
    * This method is used to evaluate elements in the storage.
    * Here to avoid exception have added a check for null values
    * and checked for 0 because result will go to infinity.
    */
    public double evaluate()    {
        double result = 0;
        while ( hasNext() ) {
            Integer aInteger = next();
            // added a check for null value in array and 0  
            if( aInteger != null && aInteger != 0 ) {
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
        StorageOrder aStorageOrder = new StorageOrder();
        aStorageOrder.add(1);
        aStorageOrder.add(0);
        aStorageOrder.add(2);
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
}