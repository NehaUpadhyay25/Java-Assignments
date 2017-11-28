package assignment2;

import java.lang.String;

class Casechar {
  static String[] aText = {"Hello Neha"};
  
  public static void main( String args[] ) {
	  
	  int lengthOfaText = aText.length;
	  String value;
	  int count = 0, count1 = 0;
	  int lines = 0, lines1 = 0;
	  String sequence = "on";
	  
	  for(int range = 0; range < lengthOfaText; range++)
	  {
		  value = aText[range];
		  count += value.length();
		  
		  if((value.startsWith("We") || value.startsWith("Wh"))) {
			  lines++;
		  }  
		  
		  if(value.contains(sequence)) {
			  lines1++;
		  }
		  
		  for(int number = 0 ; number < value.length(); number++)
		  {
			  if(value.charAt(number) == 'o' && value.charAt(number+1) == 'n')
			  {
					  count1++;
			  }
			
			  if(value.charAt(number) >= 65 && value.charAt(number) <=91)
			  {
				  System.out.print(String.valueOf((value.charAt(number))).toLowerCase());
			  }
			  else
			  {
				  System.out.print(String.valueOf((value.charAt(number))).toUpperCase());
			  }
		  }
		  System.out.println("");
	  }
	  
	  System.out.println("Length of aText = " +count);
	  
	  System.out.println("\nLines starting with 'We' or 'Wh' = " +lines);  
	  
	  System.out.println("\nLines having sequence of 'on' in it = " +lines1);
	  	  
	  System.out.println("\nNumber of times sequence of 'on' appears = " + count1+ "\n");
	  
  }
}