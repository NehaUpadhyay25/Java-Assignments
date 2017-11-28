/*
 * StorageI.java
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
* This interface represents a storage.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public interface StorageI<E> {
    
    /**
     * This method is used to insert element in the storage.
     * 
     * @param     obj     Element to be inserted.
     * @return    boolean true if success else false 
     */
    boolean insert( E obj );
    
    /**
     * This method is used to remove element from the storage.
     * 
     * @param     obj     Element to be removed.
     * @return    boolean true if success else false 
     */
    boolean remove( E obj );
    
    /**
     * This method is used to determine if element is present in the storage.
     * 
     * @param     obj     Element to be removed.
     * @return    boolean true if yes else false 
     */
    boolean contains( E obj );
    
    /**
     * This method is used to determine size of the storage.
     *
     * @return    long    number of elements
     */
    long size();
    
    /**
     * This method is used to determine more elements present in the storage.
     *
     * @return    boolean true if yes else false 
     */
    boolean hasNext();
    
    /**
     * This method is used to return next element present in the storage.
     * 
     * @return    E    next element returned
     */
    E next();
    
    /**
     * This method is used to add elements present in the collection to the storage.
     *
     * @param    collection     collection whose elements to be entered 
     */
    void addAll( StorageI<E> collection );
    
    /**
     * This method is used to add start traversal from beginning of the storage.
     *
     */
    void startFromBeginning();
    
    /**
     * This method is used to add display elements of the storage.
     *
     */
    void display();
}
