/*
 * HpIterator.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */
import java.util.Iterator;
/**
 * This class implements the Iterator.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */

@SuppressWarnings({"rawtypes","serial"})
public class HpIterator<E extends Comparable<E>> implements Iterator<E> {

    HpTreeSet<E> treeSet;
    Node<E> nextEle;
    boolean nullPresent = false;
    /**
     * This is the constructor of the class HpIterator.
     * @param 	treeSet 	the custom tree 
     */
    
    public HpIterator(HpTreeSet<E> treeSet) {
        this.treeSet = treeSet;
        this.nullPresent = treeSet.nullPresent;
        
        if (treeSet.isEmpty()) {
            nextEle = null;
        } else {
            if( this.nullPresent ) {
                nextEle = null;
            } else {
            // goto nextElement to be returned
            Node<E> root = treeSet.root;
            while (root.l != null) {
                root = root.l;
            }
            nextEle = root;
            }
        }
    }
    
    /**
     * This method checks whether there is any element after the 
     * current element in the tree or not.
     * @return boolean value whether there is any element present 
     * 		   after the current element
     */
    public boolean hasNext() {
        return this.nullPresent || (nextEle != null);
    }

    /**
     *Returns the next element from the tree.
     *@return  data  current node
     */
    public E next() {
        Node<E> curr = nextEle;
        E data = null;
        
        if( this.nullPresent ) {
           this.nullPresent = false;
           // goto nextElement to be returned
           Node<E> root = treeSet.root;
           while (root.l != null) {
               root = root.l;
           }
           nextEle = root;
           return data;
           
        } else {
            data = curr.data;
        
        
        // find nextEle for the next iteration
        Node<E> par = null;


        if (curr.r != null) {
            curr = curr.r;
            // leftmost child
            while (curr.l != null) {
                curr = curr.l;
            }
            nextEle = curr;
        } else {
            // there is no right child
            // if curr ele is right child of its parent, keep going up till
            // curr ele is left child of its parent or parent is null
            par = curr.p;
            while (par != null && curr == par.r) {
                curr = par;
                par = curr.p;
            }

            nextEle = par;

        }

        return data;
        }
    }
    /**
     * The remove method
     */
    public void remove() {
        // to make compatible with java version 1.7
    }

}
