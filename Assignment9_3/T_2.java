package Assignment9_3;

/**
* This is a given class of thread which executes the given functions.
* The possible outputs are in the comments.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class T_2 extends Thread    {
    int id = 1;
    static int  theValue  = 0;
    T_2(int id)       {
        this.id = id;
    }
    public void run () {
           theValue = id;
    }      
        
    /** Possible outputs :
     * 
     * 0 , 0 : if the main method executes completely before the run methods of
     * 			other threads
     * 
     * 0 , 1 : if the main method thread executes till the first print statement
     * 		   and the first thread executes its run method and prints the last
     * 		   statement
     * 
     * 0 , 2 : if the main method thread executes till the first print statement
     * 		   and the second thread executes its run method and prints the last
     * 		   statement
     * 
     * 0 , 3 : if the main method thread executes till the first print statement
     * 		   and the third thread executes its run method and prints the last
     * 		   statement
     * 
     * 0 , 4: if the main method thread executes till the first print statement
     * 		   and the last thread executes its run method and prints the last
     * 		   statement
     * 
     * 1 , 0 : the first thread completes its execution and prints and then the 
     * 			main thread executes and prints the result.
     * 
     * 1 , 1 : only the first thread executes its run methods and prints both the
     * 		   the times.
     * 
     * 1 , 2 : the first thread executes and prints the result and then the second 
     * 		   thread executes and then prints.
     * 
     * 1 , 3 : the first thread executes and prints the result and then the third 
     * 		   thread executes and then prints.
     * 
     * 1 , 4 : the first thread executes and prints the result and then the fourth 
     * 		   thread executes and then prints.
     * 
     * 2 , 0 : the second thread completes its execution and prints and then the 
     * 			main thread executes and prints the result.
     * 
     * 2 , 1 : the second thread executes and prints the result and then the first 
     * 		   thread executes and then prints.
     * 
     * 2 , 2 : only the second thread executes its run methods and prints both the
     * 		   the times.
     * 
     * 2 , 3 : the second thread executes and prints the result and then the third 
     * 		   thread executes and then prints.
     * 
     * 2 , 4 : the second thread executes and prints the result and then the fourth 
     * 		   thread executes and then prints.
     * 
     * 3 , 0 : the third thread completes its execution and prints and then the 
     * 			main thread executes and prints the result.
     * 
     * 3 , 1 : the third thread executes and prints the result and then the first 
     * 		   thread executes and then prints.
     * 
     * 3 , 2 : the third thread executes and prints the result and then the second
     * 		   thread executes and then prints.
     * 
     * 3 , 3 : only the third thread executes its run methods and prints both the
     * 		   the times.
     * 
     * 3 , 4 : the third thread executes and prints the result and then the fourth 
     * 		   thread executes and then prints.
     *
     */
    public static void main (String args []) {
        new T_2(1).start();;
        new T_2(2).start();;
        new T_2(3).start();;
            
        System.out.println("theValue = " + theValue );
        new T_2(4).start();;
        System.out.println("theValue = " + theValue );
    }       
}  