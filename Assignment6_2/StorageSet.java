package Assignment6_2;/*
 * StorageSet.java
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
* This class represents an implementation of storage where elements are in a set.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class StorageSet<E> implements StorageI<E> {

        private Node<E> head;
        private Node<E> tail;
        
        private Node<E> curr;
        
        public StorageSet() {
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
            if( obj == null || contains(obj) ){
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
            
            StorageSet<Integer> set = new StorageSet<Integer>();
            set.insert(2);
            set.insert(1);
            set.insert(3);
            set.insert(1);
            System.out.println( "list1 size: " + set.size() );
            System.out.println( "list1 is: ");
            set.display();
            System.out.println( "list1 contains 2: " + set.contains(2) );
            System.out.println( "list1 after remove 2: " + set.remove(2) );
            System.out.println( "list1 contains 2: " + set.contains(2) );
            System.out.println( "list1 size after remove 2: " + set.size() );
            System.out.println( "list1 is: ");
            set.display();
            
            StorageSet<Integer> set2 = new StorageSet<Integer>();
            set2.insert(8);
            set2.insert(6);
            set2.insert(7);
            set2.insert(5);
            System.out.println( "list2 size: " + set2.size() );
            set.addAll(set2);
            System.out.println( "list1 size after addAll(list2): " + set.size() );
            System.out.println( "list1 is: ");
            set.display();
            
            StorageSet<String> strSet = new StorageSet<String>();
            strSet.insert("acd");
            strSet.insert("abc");
            strSet.insert("abc");
            strSet.insert(" bc");
            strSet.insert(" Bc");
            System.out.println( "set of strings size: " + strSet.size() );
            System.out.println( "set of strings is: ");
            strSet.display();
            strSet.remove(" Bc");
            System.out.println( "set of strings after remove(Bc) is: ");
            strSet.display();
            
            StorageSet<Boolean> boolSet = new StorageSet<Boolean>();
            boolSet.insert(true);
            boolSet.insert(false);
            boolSet.insert(true);
            System.out.println( "set of booleans size: " + boolSet.size() );
            System.out.println( "set of booleans is: ");
            boolSet.display();
            boolSet.remove(true);
            System.out.println( "set of booleans size after remove(true): " + boolSet.size() );
            System.out.println( "set of booleans is: ");
            boolSet.display();

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
