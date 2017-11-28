package Assignment3;/*
 * X_1.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */

public class X_1 {

  public static boolean a()	{ 	return false;		}
  public static boolean c()	{ 	return false;		}
  public static boolean e()	{ 	return true;		}
  public static String b()	{ 	return "b";		}
  public static String d()	{ 	return "d";		}
  public static String f()	{ 	return "f";		}
  public static String g()	{ 	return "g";		}

  public static void questionMcolon()	{
	  
	// Parenthesis evaluated first and a() returns false so c() executes and 
	// returns false. 
	// Output is:-
	// 1: false
	System.out.println("1: " +  ( a() ? b() : c() ) );
	
	// Parenthesis evaluated first and ! has higher precedence where a() returns
	// false so !false = true and b() executes and returns "b".
	// Output is:-
	// 2: b
	System.out.println("2: " +  ( ! a() ? b() : c() ) );
	
	// Parenthesis evaluated first and a() returns false so e() is evaluated 
	// returning so true leading b() + g() to execute where b() first returns "b"
	// resulting in string concatenation with output of g() ie "g" giving "bg".
	// Output is:-
	// 3: bg
	System.out.println("3: " +  ( a() || e() ? b() + g() : c() )) ;
	
	// Parenthesis evaluated first. Start from innermost e() returns true resulting
	// in f() to return "f". c() returns false resulting in "f". Similarly a() 
	// returns false resulting in "f". 
	// Output is:-
	// 4: f
	System.out.println("4: " +  ( a() ? b() : (c() ? d() : (e() ? f() : g())) ) );
	
	// Parenthesis evaluated first and ?: is right associative. So start from innermost ?:.
	// e() returns true resulting in f() to return "f". c() returns false resulting in "f". 
	// Similarly a() returns false resulting in "f". 
	// Output is :-
	// 5: f
	System.out.println("5: " +  ( a() ? b() : c() ? d() : e() ? f() : g() )) ;
	
	// Parenthesis evaluated first and first goto innermost parenthesis.
	// ?: is right associative so start with e(). e() returns true resulting
	// in f() to return "f".  c() returns false resulting in "f". ! has higher
	// precedence a() returns false giving !false = true so b() returns "b".
	// Output is:-
	// 6: b
	System.out.println("6: " +  ( ! a() ? b() : ( c() ? d() : e() ? f() : g() ) )) ;
	
	// Parenthesis evaluated first and first goto innermost parenthesis. 
	// ! has higher precedence causing c() to execute and return false so
	// !false = true. But ?: is right associative so start with innermost 
	// e() returns true resulting in f() to return "f". But d() will execute 
	// because !c() gave true. d() returns "d". Again ! has higher present !a()
	// is true && !e() = false means true && false gives false returning "d".
	// Output is:-
	// 7: d
	System.out.println("7: " +  ( ! a() && ! e()  ? b() : ( ! c() ? d() : e() ? f() : g() ) )) ;
  }
  
  public static void main(String args[]) {
	questionMcolon();
  }
} // X_1