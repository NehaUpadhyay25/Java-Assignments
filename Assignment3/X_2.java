package assignment3;
/*
 * X_2.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */

public class X_2 {

  public static void if_1(int index)	{
	System.out.println("1. " + index );
	if ( ++index == 2 )
		System.out.println("2. " + index );
	else if ( --index  == 3 )
		System.out.println("3. " + index );
	else if ( index++  == 3 )
		System.out.println("4. " + index );
	else
	System.out.println("4. " + index );
  }
  
  public static void loop_1(int n, int k)	{
	label1:
	   while ( n < k )  {
		System.out.println("1. n == " + n);
	
                while ( n < k + 4 )  { 
                        n++;
			System.out.println("2.  n == " + n);
			label2:
			while ( n < k )	{
				if ( n > 2 )
					continue label1;
				else
					continue label2;
			}
			System.out.println("3.  n == " + n + "--------");
                }
                n++;
        }
  }
  
  public static void loop_2(int n, int k)	{
	for ( int index = 0; index < ( n > k ? k : n ); index ++ )	{
		int rennerle = k;
		while ( rennerle > 0 )	{
			if ( ( index - 1 ) % rennerle == 0 )
				break;
		}
	}
  }
  
  public static void main(String args[]) {
	
	  // index = 2. First line prints "1. 2". ++index = 3; 3 == 2 is false
	  // and thus, test fails. So checks else if where --index = 2; 2 == 3 
	  // is false and test fails. So checks else if index++ == 3 is 
	  // ( 2++ == 3 ) being postfix first evaluates to false as and then 
	  // increments to 3. Now it goes to else and prints "4. 3". 
	  // So, this is output:-
	  // 1. 2
	  // 4. 3
	  if_1(2);
	  
	  // index = 4. First line prints "1. 4". ++index = 5; 5 == 2 is false 
	  // and thus, test fails. So checks else if where --index = 4; 4 == 3
	  // is false and test fails. So checks else if where index++ == 3 is 
	  // ( 4++ == 3 ) being postfix first evaluates to false as and then 
	  // increments to 5. Now it goes to else and prints "4. 5".
	  // So, this is output:-
	  // 1. 4
	  // 4. 5
	  if_1(4);
	  
	  // n = 4 and k = 5. While condition 4 < 5 is true so goes inside loop,
	  // prints "1. n == 4". Checks 2nd while condition 4 < 9 is true, 
	  // increments n to 5 after line n++. Prints "2. n == 5". Since 5 < 5 is
	  // false so skips this while loop. Prints "3. n == 5--------". Iterates 
	  // in 2nd while as 5 < 9 is true. Increments n to 6 after line n++.
	  // Prints "2. n == 6". Since 6 < 5 is false so skips this while loop.
	  // Prints "3. n == 6--------". Iterates in 2nd while again till n == 9 and
	  // generates print statements similarly from n = 7 to 9.
	  // So output is :-
	  // 1. n == 4
	  // 2.  n == 5
	  // 3.  n == 5--------
	  // 2.  n == 6
	  // 3.  n == 6--------
	  // 2.  n == 7
	  // 3.  n == 7--------
	  // 2.  n == 8
	  // 3.  n == 8--------
	  // 2.  n == 9
	  // 3.  n == 9--------
	  // When it comes out of 2nd while, increments n to 10 after n++. 
	  // Since 10 < 5 is false so comes out of 1st while.
	  loop_1(4, 5);
	  
	  // n = 0, k = 4. In for loop, index = 0; then due to parenthesis evaluates
	  // ( 0 > 4 ? 4 : 0 ) resulting in 0. index < 0 is false so skips for loop.
	  loop_2(0, 4);
  }
} // X_2