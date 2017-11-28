/*
 * StorageMain.java
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
* This class used to storage implementations of StorageI.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class StorageMain {

    /**
     * This method is used to add test current storage.
     * 
     * @param    storage    The storage to test.
     * 
     */
    public static void test( StorageI<Integer> storage ){
        System.out.println("\n**** In StorageMain's test() ****\n");
        storage.insert(2);
        storage.insert(1);
        storage.insert(4);
        storage.insert(1);
        storage.insert(3);     
        System.out.println( "storage size: " + storage.size() );
        System.out.println( "storage is: ");
        storage.display();
        System.out.println( "storage contains 2: " + storage.contains(2) );
        System.out.println( "storage remove: " + storage.remove(2) );
        System.out.println( "storage contains 2: " + storage.contains(2) );
        System.out.println( "storage size after remove: " + storage.size() );
        System.out.println( "storage is: ");
        storage.display();
        
        StorageI<Integer> list2 = new StorageOrder<Integer>();
        list2.insert(8);
        list2.insert(6);
        list2.insert(5);
        list2.insert(7);
        System.out.println( "list2 size: " + list2.size() );
        storage.addAll(list2);
        System.out.println( "storage size after addAll(list2): " + storage.size() );
        System.out.println( "storage is: ");
        storage.display();
    }
    
    /**
     * This method is the main method.
     * 
     * @param    args    command line arguments(ignored) 
     */
    public static void main(String... args) { 
        test(new StorageOrder<Integer>()); 
        test(new StorageSet<Integer>()); 
        test(new StorageSpeed<Integer>()); 
    }
}
