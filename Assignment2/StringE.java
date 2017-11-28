package assignment2;

/*
 * StringE.java
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
 * This program considers a given string aText and carries out the following 
 * operations:
 * 1. Count number of characters in aText.
 * 2. Count number of lines starting with sequence "We" or "Wh".
 * 3. Count number of lines having sequence "on".
 * 4. Count number of times sequence "on" appears.
 * 5. Converts each upper case character to lower case and vice-versa.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */

class StringE {

  // Given aText string.
  static String[] aText = {
		"Oh let the sun beat down upon my face, stars to fill my dream ",
		"I am a traveler of both time and space, to be where I have been ",
		"To sit with elders of the gentle race, this world has seldom seen ",
		"They talk of days for which they sit and wait and all will be revealed ",
		"",
		"Talk and song from tongues of lilting grace, whose sounds caress my ear ",
		"But not a word I heard could I relate, the story was quite clear ",
		"Oh, oh. ",
		"",
		"Oh, I been flying... mama, there ain't no denyin' ",
		"I've been flying, ain't no denyin', no denyin' ",
		"",
		"All I see turns to brown, as the sun burns the ground ",
		"And my eyes fill with sand, as I scan this wasted land ",
		"Trying to find, trying to find where I've been. ",
		"",
		"Oh, pilot of the storm who leaves no trace, like thoughts inside a dream ",
		"Heed the path that led me to that place, yellow desert stream ",
		"My Shangri-La beneath the summer moon, I will return again ",
		"Sure as the dust that floats high in June, when movin' through Kashmir. ",
		"",
		"Oh, father of the four winds, fill my sails, across the sea of years ",
		"With no provision but an open face, along the straits of fear ",
		"Ohh. ",
		"",
		"When I'm on, when I'm on my way, yeah ",
		"When I see, when I see the way, you stay-yeah ",
		"",
		"Ooh, yeah-yeah, ooh, yeah-yeah, when I'm down... ",
		"Ooh, yeah-yeah, ooh, yeah-yeah, well I'm down, so down ",
		"Ooh, my baby, oooh, my baby, let me take you there ",
		"",
		"Let me take you there. Let me take you there",
	 };
  
  /**
   * This method is the main method.
   * 
   * @param    args    command line arguments(ignored) 
   */
  
  public static void main( String args[] ) {
	 
	  // Variables for indexing.
	  int i = 0, j = 0;
	  
	  // Array to store length of each string in aText.
	  // This is done to reduce number of method calls later in the program
	  // as length of each string is needed multiple times.
	  int[] aLen = new int[ aText.length ];
	  
	  // Count stores total number of characters in aText.
	  int noOfChars = 0;
	  
	  // linesStartWithSeq stores the number of lines that start with "We" 
	  // or "Wh".
	  int linesStartWithSeq = 0;
	  
	  // countLinesHavingSeq stores number of lines having sequence "on".
	  int countLinesHavingSeq = 0;
	  
	  // countSeq stores the number of times the sequence "on" appears in
	  // aText.
	  int countSeq = 0;
	  
	  // The sequence "on" is stored in seqOn.
	  String seqOn = "on";
	  
	  
	  for ( i = 0; i < aText.length; i++ ) {
		  
		  // Consider each string one by one to find its length using String
		  // class' length() method which is also added to noOfChars variable.
		  aLen[i] = aText[i].length();
		  noOfChars += aLen[i];
		  
		  // Consider each string one by one to determine whether it starts 
		  // with sequence "We" or "Wh" and store count in linesStartWithSeq.
		  if ( aText[i].startsWith( "We" ) || aText[i].startsWith( "Wh" ) ) {
			  linesStartWithSeq++;
		  }
		  
		  // Consider each string one by one to determine whether it contains 
		  // the sequence "on" and store count in countLinesHavingSeq.
		  //
		  if ( aText[i].contains(seqOn)) {
			  countLinesHavingSeq++;
			  
			  // For each string in aText, consider every substring starting at 
			  // offset 0,1...n-1 and determine whether it starts with "on".
			  for ( j = 0; j < aLen[i]; j++ ) { 	
				  
				  if ( aText[i].startsWith( seqOn, j ) ) {
							  countSeq++;
				  }
				  
			  }  
		  }
	  } 
	  
	  // Print number of characters in aText.
	  System.out.println( "Number of characters in aText are : " + noOfChars );
	  
	  // Prints number of lines in aText starting with "We" or "Wh".
	  System.out.println( "\nNumber of lines in aText that start with \"We\" or \"Wh\" are : " + linesStartWithSeq );
		
	  // Prints number of lines in aText having sequence "on".
	  System.out.println( "\nNumber of lines having sequence \"on\" are : " + countLinesHavingSeq);
	  
	  // Prints number of times the sequence "on" appears in aText.
	  System.out.println( "\nNumber of times the sequence \"on\" appears in aText are : " + countSeq );
	  
	  
	  // newText stores aText after case-inversion of all characters.
	  String[] newText = new String[aText.length];
	  
	  // Temporary array to store characters of each string one-by-one.
	  char[] chars = null;
	  
	  // Value used for case inversion.
	  int diff = 'a' - 'A';
	  
	  for ( i = 0; i < aText.length; i++ ) {
		  chars = aText[i].toCharArray();
		  
		  // Consider every character.
		  for ( j = 0; j < aLen[i]; j++) {
			  
			  // If its upper case then convert to lower case else vice-versa.
			  if ( chars[j] >= 'A' && chars[j] <= 'Z'  ) {
				  chars[j] += diff;
			  } else if ( chars[j] >= 'a' && chars[j] <= 'z'  ) {
				  chars[j] -= diff;
			  }   
		  }
		  
		  newText[i] = new String(chars);
	  }
	  
	  // Prints newText with character in aText after case-inversion.
	  System.out.println( "\nConverted upper case characters in "
	  						+ "aText to lower case and vice-versa:-\n" );
	  
	  for ( i = 0; i < newText.length; i++ ) {
		  System.out.println(newText[i]);
	  }
	  
  }

}// StringE
