/*
 * HpTreeSet.java
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
import java.util.TreeSet;
/**
 * This class implements the Tree Set.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */

@SuppressWarnings({"rawtypes","unchecked","serial"})
public class HpTreeSet<E extends Comparable<E>> extends TreeSet<E> {

    Node<E> root;
    int size = 0;
    public boolean nullPresent = false;
    
    /**
     * This method gets the node from the tree.
     * @param e  node value
     * @return n node in the tree
     */
    private Node<E> getNode(E e) {
        Node<E> n = new Node<E>();
        n.data = e;
        n.l = null;
        n.r = null;
        n.c = 'r';
        n.p = null;
        return n;
    }

    /**
     * Checks whether the tree is empty or not
     * @return boolean value whether the tree has no value or not
     */
    public boolean isEmptyTree() {
        return root == null;
    }
    
    /**
     * This helps in inserting nodes that is the values in the tree
     * @param e the value to be inserted
     * @return z node after inserting the value
     */
    private Node<E> BSTInsert(E e) {
        Node<E> z = getNode(e);

        if (isEmptyTree()) {
            root = z;
            return z;
        }

        boolean flagL = false;
        Node<E> par = null;
        Node<E> root2 = root;
        int t = 0;

        while (root2 != null) {
            par = root2;
            t = e.compareTo(root2.data);
            if (t == 0) {
                return null;
            } else if (t < 0) {
                root2 = root2.l;
                flagL = true;
            } else {
                root2 = root2.r;
                flagL = false;
            }
        }

        z.p = par;

        if (flagL) {
            par.l = z;
        } else {
            par.r = z;
        }

        return z;

    }

    /**
     * This helps in rotating the tree to the left
     * @param z rotates with the z node
     */
    private void leftRotate(Node<E> z) {
        Node<E> y = z.r;
        y.p = z.p;

        if (z.p == null) {
            root = y;
        } else if (z.p.l == z) {
            z.p.l = y;
        } else {
            z.p.r = y;
        }

        z.r = y.l;
        if (y.l != null) {
            y.l.p = z;
        }

        y.l = z;
        z.p = y;
    }

    /**
     * Rotates the tree to the right
     * @param z rotates the tree with the z node
     */
    private void rightRotate(Node<E> z) {
        Node<E> y = z.l;
        y.p = z.p;

        if (z.p == null) {
            root = y;
        } else if (z.p.l == z) {
            z.p.l = y;
        } else {
            z.p.r = y;
        }

        z.l = y.r;
        if (y.r != null) {
            y.r.p = z;
        }

        y.r = z;
        z.p = y;

    }

    /**
     * This method maintains the Red Black property 	
     * @param z node to maintain the property
     */
    private void RBTreeFixup(Node<E> z) {
        Node<E> y = null;
        boolean isUncleRed = false; // null node is black

        // while parent's color is red
        while (z != null && z != root && z.p.c == 'r') { // this is only
                                                         // possible when
                                                         // parent is not the
                                                         // root so there
                                                         // is a grandparent
            isUncleRed = false; // because null is black
            // find color of uncle
            if (z.p == z.p.p.l) {
                y = z.p.p.r;
                if (y != null) {
                    isUncleRed = (y.c == 'r');
                }

                if (isUncleRed) { // simply re-color
                    z.p.c = 'b';
                    y.c = 'b';
                    z.p.p.c = 'r';
                    z = z.p.p;
                } else { // uncle is a null node or uncle is black, need
                         // rotations
                    if (z == z.p.r) { // z is right child of parent
                        z = z.p;
                        leftRotate(z);
                    }
                    z.p.c = 'b';
                    z.p.p.c = 'r';
                    rightRotate(z.p.p);
                }
            } else {

                y = z.p.p.l;
                if (y != null) {
                    isUncleRed = (y.c == 'r');
                }

                if (isUncleRed) { // simply re-color
                    z.p.c = 'b';
                    y.c = 'b';
                    z.p.p.c = 'r';
                    z = z.p.p;
                } else { // uncle is a null node or uncle is black, need
                         // rotations
                    if (z == z.p.l) { // z is right child of parent
                        z = z.p;
                        rightRotate(z);
                    }
                    z.p.c = 'b';
                    z.p.p.c = 'r';
                    leftRotate(z.p.p);
                }

            }
        }

        root.c = 'b';

    }
    
    /**
     * This method maintains the in-order property
     * @param root  the root node
     */
    private void inorderUtil(Node<E> root) {
        if (root == null) {
            return;
        }
        inorderUtil(root.l);
        System.out.println(root.data + " " + root.c);
        inorderUtil(root.r);
    }
    
    /**
     * This method is used for initialization
     */
    public void init() {
        root = null;
        size = 0;
        nullPresent = false;
    }
    
    /**
     * This method is used to parse the tree
     */
    public void parse() {
        Node<E> root2 = root;
        inorderUtil(root2);
    }

    /**
     * This method clears everything in the tree
     */
    public void clear() {
        root = null;
        size = 0;
        nullPresent = false;
    }

    /**
     * This method checks whether the tree contains the object or not
     * @return boolean value
     */
    public boolean contains(Object ob) {

        if( ob == null ) {
            return nullPresent;
        }
        
        Comparable e = (Comparable) ob;
        Node<E> root2 = root;
        int t = 0;

        while (root2 != null) {
            t = e.compareTo(root2.data);
            if (t == 0) {
                return true;
            } else if (t < 0) {
                root2 = root2.l;
            } else {
                root2 = root2.r;
            }
        }

        return false;
    }

    /**
     * This method checks whether the root is empty or not
     * @return boolean value
     */
    public boolean isEmpty() {
        return root == null && nullPresent == false;
    }

    /**
     * This method returns true if set does not already contains the 
     * specified element
     * @param e the node to be added
     * @return boolean value
     */
    public boolean add(E e) {
        // returns true if set does not already contains the specified element

        if (e == null) {
            if( nullPresent == true) {
            return false;
        
            } else {
                size += 1;
                nullPresent = true;
                return true;
            }
        }

        Node<E> z = BSTInsert(e);

        if (z == null) {
            return false;
        }

        size += 1;

        if (z == root) {
            root.c = 'b'; // root is black
            return true;
        }

        RBTreeFixup(z);

        return true;
    }

    /**
     * This method returns the size of the tree
     * @return  size  size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * This method iterates through the tree
     * @return boolean value
     */
    public Iterator<E> iterator() {
        return new HpIterator<E>(this);
    }


}
