package Assignment6_2;/*
 * StorageSpeed.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */

/**
* This class represents an implementation of storage where elements are stored according to hash code.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class StorageSpeed<E> implements StorageI<E> {
    
    private Node<E>[] hash;
    private static int DEFAULT_SIZE = 10;
    
    private int currIdx;
    private Node<E> curr;
    
    @SuppressWarnings({"rawtypes","unchecked"})
    public StorageSpeed() {
       hash = new Node[DEFAULT_SIZE];
       
       //initialize heads
       for( int i = 0; i < DEFAULT_SIZE; i++ ) {
           hash[i]= new Node(0);
       }
    }

    /**
     * This method is used to insert element in the storage.
     * 
     * @param     obj     Element to be inserted.
     * @return    boolean true if success else false 
     */
    public boolean insert(E obj) {
        if ( obj == null){
            return false;
        }
        
        int code = obj.hashCode() % DEFAULT_SIZE;
        
        
        Node<E> it = hash[ code ];
        
        Node<E> curr = new Node<E>(obj);
        
        
        while( it.next != null ){
            
            it = it.next;
        }
        
        it.next = curr;
        
        
        return false;
    }

    /**
     * This method is used to remove element from the storage.
     * 
     * @param     obj     Element to be removed.
     * @return    boolean true if success else false 
     */
    public boolean remove(E obj) {
        
        int code = obj.hashCode() % DEFAULT_SIZE;
        
       
        Node<E> prev = hash[ code ];
        Node<E> it = prev.next;
        
        while ( it != null ) {
            if ( it.data.equals( obj ) ) {
                prev.next = it.next;
                return true;
            }
            prev = it;
            it = it.next;
        }
        
        return false;
    }

    /**
     * This method is used to determine if element is present in the storage.
     * 
     * @param     obj     Element to be removed.
     * @return    boolean true if yes else false 
     */
    public boolean contains(E obj) {
        
        int code = obj.hashCode() % DEFAULT_SIZE;
        
        Node<E> it = hash[ code ];
        it = it.next;
        
        while ( it != null ) {
            if ( it.data.equals( obj ) ) {
                return true;
            }
            it = it.next;
        }
        
        return false;
    }

    /**
     * This method is used to determine size of the storage.
     *
     * @return    long    number of elements
     */
    public long size() {
        long count = 0;
              
        for ( int  i =0; i < DEFAULT_SIZE; i++ ) {
            Node<E> it =  hash[ i ];
            it = it.next;
            
            while ( it != null ) {
                count++;
                it = it.next;
            }
        }
        return count;
    }

    /**
     * This method is used to determine if at least 1 more element present in the storage.
     *
     * @return    boolean true if yes else false 
     */
    public boolean hasNext() {
        
        return currIdx < DEFAULT_SIZE;
    }

    /**
     * This method is used to return next element present in the storage.
     * 
     * @return    E    next element returned
     */
    public E next() {
        
        E tmp = curr.data;
        
        //update next to be returned
        curr = curr.next;
        while ( curr == null && currIdx < DEFAULT_SIZE) {
            currIdx++;
            if( currIdx < DEFAULT_SIZE ) {
                Node<E> it = hash[currIdx];
                curr = it.next;
            }
        }
        
        return tmp;
    }

    /**
     * This method is used to add elements present in the collection to the storage.
     *
     * @param    collection     collection whose elements to be entered 
     */
    public void addAll(StorageI<E> collection) {
        collection.startFromBeginning();
        while(collection.hasNext()){
            insert(collection.next());
        }
        
    }

    /**
     * This method is used to add start traversal from beginning of the storage.
     *
     */
    public void startFromBeginning() {
        currIdx = -1;  
        
       do{  
            currIdx++; 
            
            Node<E> it = hash[currIdx];
            
            curr = it.next;
        } while ( curr == null && currIdx < DEFAULT_SIZE );
        
        
    }

    /**
     * This method is used to add display elements of the storage.
     *
     */
    public void display() {
        this.startFromBeginning();
        
        while( this.hasNext() ){
            System.out.println(this.next());
        }
        
    }
    
    /**
     * This method is used to add test current storage.
     *
     */
    public static void test() {
        
        StorageSpeed<Integer> map = new StorageSpeed<Integer>();
        map.insert(2);
        map.insert(1);
        map.insert(3);
        map.insert(1);
        System.out.println( "list1 size: " + map.size() );
        System.out.println( "list1 is: ");
        map.display();
        System.out.println( "list1 contains 2: " + map.contains(2) );
        System.out.println( "list1 remove: " + map.remove(2) );
        System.out.println( "list1 contains 2: " + map.contains(2) );
        System.out.println( "list1 size after remove: " + map.size() );
        System.out.println( "list1 is: ");
        map.display();
        
        StorageSpeed<Integer> map2 = new StorageSpeed<Integer>();
        map2.insert(8);
        map2.insert(6);
        map2.insert(7);
        map2.insert(5);
        System.out.println( "list2 size: " + map2.size() );
        map.addAll(map2);
        System.out.println( "list1 size after addAll(list2): " + map.size() );
        System.out.println( "list1 is: ");
        map.display();
        
        StorageSpeed<String> strMap = new StorageSpeed<String>();
        strMap.insert("acd");
        strMap.insert("abc");
        strMap.insert("abc");
        strMap.insert(" bc");
        strMap.insert(" Bc");
        System.out.println( "list of strings size: " + strMap.size() );
        System.out.println( "list of strings is: ");
        strMap.display();
        strMap.remove(" Bc");
        System.out.println( "list of strings after remove(Bc) is: ");
        strMap.display();
        
        StorageSpeed<Boolean> boolMap = new StorageSpeed<Boolean>();
        boolMap.insert(true);
        boolMap.insert(false);
        boolMap.insert(true);
        System.out.println( "list of booleans size: " + boolMap.size() );
        System.out.println( "list of booleans is: ");
        boolMap.display();
        boolMap.remove(true);
        System.out.println( "list of booleans size after remove(true): " + boolMap.size() );
        System.out.println( "list of booleans is: ");
        boolMap.display();

    }
    
    /**
     * This method is the main method.
     * 
     * @param    args    command line arguments(ignored) 
     */
    public static void main( String... strings ) {
        test();
    }
    
}
