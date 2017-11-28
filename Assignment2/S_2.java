package assignment2;
/*
 * S_2.java
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
 * This program considers various operations on strings and 
 * prints their results.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */
class S_2 {

  public static void method(String id, String literal, String aNewString)	{
    System.out.println("method!" + id + ". " + (literal == aNewString ));
  }
  
  /**
   * This method is the main method.
   * 
   * @param    args    command line arguments(ignored) 
   */
  public static void main( String args[] ) {
    String aString = "123";
    String bString = "123";
    int number = 3;
    
    // JVM evaluates from left to right. 
    // == has lower precedence.
    // So, first concatenation happens between "a.  " and "123" giving "a.  123".
    // The final string obtained is then compared using == with aString.
    // Since they are 2 different string literals thus false is printed.
    // 2 strings will be generated here as "123" exists only once in memory.
    // Earliest moment for gc after execution of line. 
    // GC:-
    // "a.  123" after execution of line.
    // "a.  " , "123" at end of program execution.
    System.out.println("a.	" +     "123" == aString   );
    
    // Parenthesis have highest precedence. So, first == compares giving true.
    // Then concatenation happens between "b.  " and true giving us output
    // "b.  true"
    // 2 strings will be generated here as "123" exists only once in memory.
    // Earliest moment for gc is after execution of line. 
    // GC:-
    // "b.  true" after execution of line.
    // "b.  ", "123" and  eligible for gc at end of program execution.
    System.out.println("b.	" +   ( "123" == aString ) );


    // + concatenates "c.  " and 123 at runtime. So, result of this operation is a 
    // string "c.  123". Then + concatenates "c.  123" and 123 resulting in 
    // "c.  123123" as output.
    // 2 strings will be generated here, "c  " and "c  123123".
    // "123" exists only once in memory.
    // Earliest moment for gc is after execution of line.
    // GC:-
    // "c.  123123" after execution of line.
    // "c.  ", "123" eligible for gc at end of program execution.
    System.out.println("c.	" +   aString  + 123 );


    // + concatenates "d.  "  and 123 giving "d.  123" which is concatenated with.
    // 3 giving "d.	1233" which is concatenated with "123" giving string "d. 1233123" 
    // which is the output.
    // 2 strings will be generated here as "123" exists only once in memory.
    // Earliest moment for gc is is after execution of line.
    // GC:-
    // "d. 1233123" after execution of line.
    // "d.  " and "123" eligible for gc at end of program execution.
    System.out.println("d.	" +   123 + number + aString  );
    
    // Parenthesis are evaluated first adding 126 then concatenating to give "126123".
    // Then no parenthesis in expression. So + concatenates "e.	" and "126123"
    // giving "e.	126123" as output.
    // 2 strings will be generated here:"e.  " and "e.  126123". 
    // "123" exists only once in memory.
    // Earliest moment for gc is after execution of line.
    // "e.	126123" after execution of line.
    // "e.	", and  "123" eligible for gc at end of program execution.
    System.out.println("e.	" +   ( 123 + number + aString )  );

    // Parenthesis evaluated first so + generates "123" and again "1233" followed 
    // by "1233123". Then no parenthisis in expression so + concatenates  "f.	" with 
    // "1233123" and giving final string "f.	1233123" as output.
    // 3 strings will be generated here:- "f.  ", "" and "f.  1233123".
    // "123" exists only once in memory.
    // Earliest moment for gc is after execution of line
    // GC:-
    // "f.	1233123"  after line execution. 
    // "", "123",  "f.	"  eligible for gc at end of program execution. 
    System.out.println("f.	" +   ( 123 + "" +  number + aString )  );

    // * has higher precedence than +. 123 is multiplied by number which 
    // evaluates to 3 resulting in 369. + concatenates "g.  " and 369 resulting in 
    // "g.  369". Again + concatenates  "g.  369" with  "123" giving "g.  369123" 
    // as output.
    // 2 strings will be generated here:"g.  " and "g. 369123".
    // "123" exists only once in memory.
    // Earliest moment for gc is after line execution.
    // GC:-
    // "g.  369123" after execution of line.
    // "g.	",  "123" eligible for gc at end of program execution. 
    System.out.println("g.	" +   123 * number + aString  );
    
    // / has higher precedence than +. 123 is divided by 3 resulting in 41. 
    // + concatenates "h.  " with 41 resulting in string "h.  41".
    // +  again concatenates "h.  41" with "123" at runtime giving string "h.  41123"
    // as output.
    // 2 strings will be generated here:"h.  " and "h.  41123".
    // "123" exists only once in memory.
    // Earliest moment for gc is after line execution.
    // GC:-
    // "h.  41123" after line executes.
    // "h.  ",  "123"  eligible for gc at end of program execution. 
    System.out.println("h.	" +   123 / number + aString  );

    // Parenthesis have highest precedence. 
    // 123 - 3 gives 120. No more parenthesis in expression sp + concatenates
    // "i.  " with 120 resulting in string "i.   120" whch is concatenated with
    // "123" giving string "i.  120123" output.
    // 2 strings will be generated here: "i.  " and "i. 120123".
    // "123" exists only once in memory.
    // Earliest moment for gc is after line execution.
    // GC:-
    // "i.  120123" e after line executes.
    // "i.  ", "123" eligible for gc at end of program execution. 
    System.out.println("i.	" +  ( 123 - number )  + aString  );

    // "" + "xyz" is concatenated by compiler to give "xyz".    
    // Since string literals are stored once in memory, so "xyz" == "xyz" is true.
    // Final output is string "method!1.true"
    // 5 strings will be generated in memory as string literals exist once only.
    // "1", "xyz","method!",".","method!1.true"
    // Earliest moment for gc is end of method().
    // GC:-
    // "method!1.true" after s.o.p in method
    // "", "1" and "xyz", "method!" and "." after program execution completes.
    method("1", "xyz", "" + "xyz");

    // Second argument involves creation with new operator so new string generated at runtime.
    // Thus, literal == aNewString results in false.
    // Final output is string "method!2.false"
    // 5 strings will be generated in memory as string literals exist once only.
    // new String("xy"), "z", new String("xy")+"z", "2","method!2.false" 
    // Earliest moment for gc after concatenation of new String("xy") 
    // in new String("xy") + "z".
    // GC:-
    // new String("xy") after concatenation
    // "method!2.false" after s.o.p in method
    // new String object of xyz after method completes
    // "2", "z", "xyz" and "method!" and "." after program execution completes
    method("2", "xyz", new String("xy") + "z" );

    // "x"+"yz" is concatented by compiler to give "xyz".
    // Since string literals are stored once in memory, so "xyz" == "xyz" is true.
    // Final output is string "method!3.true".
    // 4 strings will be generated in memory as string literals exist once only.
    // "3","x","yz","method!3.true"
    // Earliest moment for gc is after s.o.p in method.
    // GC:-
    // "method!3.true" after s.o.p in method
    // "3", "x", "yz", "xyz" and "method!" and "." after program execution completes
    method("3", "xyz", "x" + "yz");

    // "" + "x" + "y" + "z" is concatenated by compiler to give "xyz." 
    // Since string literals are stored once in memory, so "xyz" == "xyz" is true.
    // Final output is string "method!4.true"
    // 3 strings will be generated in memory.
    // "4", "y","method!4.true"
    // Earliest moment for gc is after s.o.p in method.
    // GC:-
    // "method!4.true" after s.o.p in method
    // "", "4", "x", "y","z", "xyz" and "method!" and "." after program execution completes
    method("4", "" + "x" + "y" + "z", "xyz");
    
    // new operator creates new String object "x" in heap at runtime.
    // Also, new operator creates new String object "z" in heap at runtime.
    // Strings computed by concatenation at runtime are newly created and 
    // therefore, distinct. Thus, literal == aNewString results in false.
    // Final output is string "method!5.false"
    // 4 strings will be generated in memory.
    // new String("x"), new String("z"), "5","method!5.false"
    // Earliest moment for gc after concatenation completes:-
    // new String("x") + "y" + new String("z") because there is no reference 
    // to String "x" and String "z".
    // GC:-
    // new String("x"), new String("z") after concatenation
    // "method!5.false" after s.o.p in method
    // "5", "xyz", "y" and "method!" and "." after program execution completes  
    method("5", new String("x") + "y" + new String("z"), "xyz");

    // Parenthesis have highest precedence so first string "xy" is generated.
    // argument 1 is generated at compile-time due to compile-time constant 
    // expression and treated as literal. Similarly, argument 2 is string literal.
    // Since string literals are stored once in memory, so "xyz" == "xyz" is true.
    // Final output is string "method!7.true"
    // 2 string is generated.
    // "7", "method!7.true"
    // Earliest moment for gc is is end of after s.o.p in method.
    // GC:-
    // "method!7.true" after s.o.p in method
    // "7", "xyz" and "method!" and "." after program execution completes
    method("7", ('x' +  "y" ) + "z", "xyz");
  }
} // S_2