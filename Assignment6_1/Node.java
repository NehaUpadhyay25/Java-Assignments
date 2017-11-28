/*
 * Node.java
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
* This class represents a node in linked list.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class Node<E> {
    public E data;
    public Node<E> next; 
    
    Node( E value ){
        this.data = value;
        this.next = null;
    }
}
