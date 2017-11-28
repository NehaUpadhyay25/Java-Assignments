/*
 * StorageLL.java
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
* This class represents an implementation of storage as a Linked List.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class StorageLL<E> implements StorageI<E> {

    private Node<E> head;
    private Node<E> tail;
    
    private Node<E> curr;
    
    public StorageLL() {
        this.head = null;
        this.tail = null;
    }
    
    /**
     * This method is used to insert element in the storage.
     * 
     * @param     obj     Element to be inserted.
     * @return    boolean true if success else false 
     */
    public boolean insert( E obj ) {
        if( obj == null ){
            return false;
        }
        
        Node<E> ele = new Node<E>(obj); 
       
        if( head == null ) {
            head = tail = ele;
        }else {
            tail.next = ele;
            tail = ele;
        }
       
        return true;
    }

    /**
     * This method is used to remove element from the storage.
     * 
     * @param     obj     Element to be removed.
     * @return    boolean true if success else false 
     */
    public boolean remove(E obj) {
               
        Node<E> it = head;
        Node<E> prev = null;
        
        while( it != null ){
            
            if( it.data.equals(obj) ){
                if( prev == null ) { // if first node removed
                    head = it.next;
                } else { 
                    prev.next = it.next;
                    if( it == tail ){ // if last node removed
                        tail = prev;
                    }
                }
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
        
        Node<E> it = head;
        
        while( it != null ){
            if( it.data.equals(obj) ){
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
        Node<E> it = head;
        
        while( it != null ){
            count++;
            it = it.next;
        }
        
        return count;
    }

    /**
     * This method is used to add start traversal from beginning of the storage.
     *
     */
    public void startFromBeginning(){
        curr = head;
    }
    
    /**
     * This method is used to determine if at least 1 more element present in the storage.
     *
     * @return    boolean true if yes else false 
     */
    public boolean hasNext() {
        return curr != null;
    }

    /**
     * This method is used to return next element present in the storage.
     * 
     * @return    E    next element returned
     */
    public E next() {
        E data = curr.data;
        curr = curr.next;
        return data;
    }
 
    /**
     * This method is used to add elements present in the collection to the storage.
     *
     * @param    collection     collection whose elements to be entered 
     */
    public void addAll( StorageI<E> collection ) {
        collection.startFromBeginning();
        while(collection.hasNext()){
            insert(collection.next());
        }
        
    }
    
    /**
     * This method is used to add display elements of the storage.
     *
     */
    public void display(){     
        this.startFromBeginning();
        while(this.hasNext()){
            System.out.println(this.next());
        }
    }
    
    /**
     * This method is used to add test current storage.
     *
     */
    public static void test() {
        
        StorageLL<Integer> ll = new StorageLL<Integer>();
        ll.insert(3);
        ll.insert(1);
        ll.insert(2);
        System.out.println( "list1 size: " + ll.size() );
        System.out.println( "list1 is: ");
        ll.display();
        System.out.println( "list1 contains 2: " + ll.contains(2) );
        System.out.println( "list1 remove: " + ll.remove(2) );
        System.out.println( "list1 size after remove: " + ll.size() );
        System.out.println( "list1 contains 2: " + ll.contains(2) );
        System.out.println( "list1 is: ");
        ll.display();
        
        StorageLL<Integer> ll2 = new StorageLL<Integer>();
        ll2.insert(5);
        ll2.insert(8);
        ll2.insert(6);
        ll2.insert(7);
        System.out.println( "list2 size: " + ll2.size() );
        ll.addAll(ll2);
        System.out.println( "list1 size after addAll(list2): " + ll.size() );
        System.out.println( "list1 is: ");
        ll.display();
        
        StorageLL<String> strL = new StorageLL<String>();
        strL.insert("acd");
        strL.insert("abc");
        strL.insert(" bc");
        strL.insert(" Bc");
        System.out.println( "list of strings size: " + strL.size() );
        System.out.println( "list of strings is: ");
        strL.display();
        
        StorageLL<Boolean> boolL = new StorageLL<Boolean>();
        boolL.insert(true);
        boolL.insert(false);
        boolL.insert(true);
        System.out.println( "list of booleans size: " + boolL.size() );
        System.out.println( "list of booleans is: ");
        boolL.display();
        boolL.remove(true);
        System.out.println( "list of booleans size after remove(true): " + boolL.size() );
        System.out.println( "list of booleans is: ");
        boolL.display();

    }
    
    /**
     * This method is the main method.
     * 
     * @param    args    command line arguments(ignored) 
     */
    public static void main(String... args) { 
        test(); 
    }
}
