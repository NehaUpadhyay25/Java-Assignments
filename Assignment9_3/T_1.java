

/**
* This is a given class of thread which executes the given functions.
* The possible outputs are in the comments.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/

public class T_1 extends Thread	{
	static int x = 1;
	String info = "";

	public T_1 (String info) {
		this.info = info;
		x++;
	}

	public void run () {
		x = ( x >= 1 ? ++x : --x );
		System.out.println(x);
	}

	/**Possible outputs:
	 * 
	 * 1) 3 5 : If the main method runs and completes executing it will print 3 as its
	 * 			result. If the thread with a executes and completes execution. It will
	 * 			print 5.
	 * 			
	 * 
	 * 2) 4 5 : If the main thread runs but dooesn't print anything. In the meantime 
	 * 			if the thread with a runs and completes execution it will print 4
	 * 			then the thread with b runs and completes execution the result will
	 * 			be 5.
	 * 
	 * 3) 5 5 :	If both the main method and the thread with a runs and doesn't
	 * 			print anything in the mean time thread with b runs the result will 
	 * 			be 5 so it prints and then later thread with a prints and completes
	 * 			execution.
	 */
	
	public static void main (String args []) {
		new T_1("a").start();
		new T_1("b").start();
	}
}
