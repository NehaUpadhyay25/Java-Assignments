package assignment3;
/*
 * Maze.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */

import java.util.Scanner;

/**
 * This program determines a path through a maze, if possible.
 * It prints "No path Found" otherwise.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */

public class Maze {
	
	static  String[] mazeStr = {
			"### ########################################################",
			"### ########################################################",
			"### ########################################################",
			"### ########################################################",
			"### ########################################################",
			"###                                    #####################",
			"### ################################## #####################",
			"###            ####################### #####################",
			"############## ####################### #####################",
			"##############                         #####################",
			"#######          ###########################################",
			"############## #############################################",
			"############## #############################################",
			"############## #############################################",
			"############## #############################################",
			"############## #############################################",
			"############## #############################################",
			"############## #############################################",
			"############## #############################################",
			"############## #############################################",
			"##############                                              ",
			"############################################################"	
	};
	
	static int start_i = 0;
	static int start_j = 0;
	
	static int[] x = {0, 0, 1, -1};
	static int[] y = {1,-1, 0, 0};
	
	/**
     * This method is used to find starting coordinates.
     * 
     * @param     maze    Maze
     * @param     m       Number of rows in the maze
     * @param     n       Number of columns in the maze
     * @return    boolean Returns true if entry to maze is possible else false.
     */
	
	static boolean findStart( int[][] maze, int m, int n ) {
		int i = 0;
		m--;
		n--;
		// scan all 4 sides for possible entrances
		
		// Scan first row to find entrance.
		for ( i = 0; i <= n; i++ ) {
			if( maze[0][i] == 0 ) {
				start_i = 0;
				start_j = i;
				return true;
			}
		}
		
		// Scan first column to find entrance.
		for ( i = 1; i <= m; i++ ) {
			if( maze[i][0] == 0 ) {
				start_i = i;
				start_j = 0;
				return true;
			}
		}

		// Scan last column to find entrance.
		for ( i = 1; i <= m; i++ ) {
			if( maze[i][n] == 0 ) {
				start_i = i;
				start_j = n;
				return true;
			}
		}
		
		// Scan last row to find entrance.
		for ( i = 1; i < n; i++ ) {
			if( maze[m][i] == 0 ) {
				start_i = m;
				start_j = i;
				return true;
			}
		}
		
		
		return false;
	}
	
	/**
     * This method is used to find path starting at coordinates (s,t).
     * 
     * @param     maze    Maze
     * @param     m       Number of rows in the maze
     * @param     n       Number of columns in the maze
     * @param     s       Previous x-coord in the path
     * @param     t       Previous y-coord in the path
     * @return    boolean Returns true if path exists else false
     */
	
	static boolean dfs( int[][] maze, int m, int n, int s, int t ) {
	
		int x_i = 0, y_i = 0;
		
		// The path shall end at one of the sides but make sure we are not
		// stuck at start.
		if ( ( s == 0 || t == 0 || s == m - 1   || t == n - 1 ) &&
			 ( s != start_i || t != start_j ) ) {

			return true;
		}
		
		for ( int i = 0; i < x.length; i++ ) {
			x_i = s + x[i];
			y_i = t + y[i];
			
			if( x_i >=0 && x_i < m && y_i >= 0 && y_i < n && maze[x_i][y_i] == 0 ) {
				maze[x_i][y_i] = 2;
				if( dfs( maze, m, n, x_i, y_i ) ){
					return true;
				}else {
					maze[x_i][y_i] = 3;
				}
			}
		}
		
		return false;
		
	}
	
	/**
     * This method is used to find entry points to the maze and check if 
     * path exists on starting at those coordinates.
     * 
     * @param     maze    Maze
     * @param     m       Number of rows in the maze
     * @param     n       Number of columns in the maze
     * @return    boolean Returns true if path exists else false
     */
	
	static boolean path(int[][] maze, int m, int n){
		
		if( !findStart(maze, m, n) ) {
			return false;
		}
		
		// We found an entrance to the maze, now start traversal.
		maze[start_i][start_j] = 2;
		if( dfs( maze, m ,n , start_i, start_j ) ){
				return true;
		} else {
			if( path( maze, m, n ) )
				return true;
			
		}
		
		return false;
	}
	
	/**
     * This method is the main method.
     * 
     * @param    args    command line arguments(ignored) 
     */
	
	public static void main( String... args ) {
		
		// Number of rows and columns in the maze.
		int rows = mazeStr.length, cols = mazeStr[0].length();
		int[][] maze = new int[rows][cols];	
		
		// Variables for indexing and temporary storage.
		int i = 0,  j = 0, tmp = 0;
		
		// Variables used for scanning input.
		Scanner sc = null;
		String str = null, str2 = null;
		
		for ( i = 0; i < rows; i++ ) {
			sc = new Scanner( mazeStr[i] );
			tmp = 0;
			
			// scan for spaces at the beginning
			str2 = sc.findInLine("^\\s+");
			if( str2 != null ) {
				for ( j = 0; j < str2.length(); j++ ) {
					maze[i][tmp+j] = 0;
				}
				tmp += j;
			}
			
			while( sc.hasNext() ) {
				str = sc.next();
				str2 = sc.findInLine("\\s+");
				
				for ( j = 0; j < str.length(); j++ ) {
					maze[i][tmp+j] = 1;
				}
				
				tmp += j;
				
				if( str2 != null ) {
					for ( j = 0; j < str2.length(); j++ ) {
						maze[i][tmp+j] = 0;
					}
					tmp += j;
				}
			}
		}		
		
		// Find if path exists. If yes, then print the maze showing the path
		// else print "No path found!!'.
		if( !path( maze, rows, cols ) ) {
			System.out.println( "No path found!!");
		} else {
			System.out.println( "Found path indicated by \'.\'s in the maze below:-\n");
			for ( i = 0; i < rows; i++ ) {
				for( j = 0; j < cols; j++ ){
					tmp = maze[i][j];
					if( tmp == 1 )
						System.out.print('#');
					else if( tmp == 3 || tmp == 0 )
						System.out.print(' ');
					else
						System.out.print('.');
				}
				
				System.out.println();
			}
		}	
	}

} // Maze
