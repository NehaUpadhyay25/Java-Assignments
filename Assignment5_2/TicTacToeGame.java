/*
 * TicTacToeGame.java
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
* This class starts and maintains the game in the Battleship game.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class TicTacToeGame implements Gameable {

    // Minimum and maximum coordinates of the Grid as specified in the game.
    private static final int MIN_ROW = 0;
    private static final int MIN_COLUMN = 0;
    private static final int MAX_ROW = 2;
    private static final int MAX_COLUMN = 2;
    
	// Constants used to report miss(out-of-bounds), game over, already marked spot, 
	// no success in this pass and draw conditions.
	public static final int MISS = 0;
	public static final int OVER = 1;
	public static final int ALREADY_MARKED = 2;
	public static final int NO_SUCCESS = 3;
	public static final int DRAW = 4;
	
	// grid for the current game
	Grid aGrid;
    
	// players in the current game
    Player playerA;
    Player playerB;
    
	/**
	 * This method is used to play the Battleship game between two players.
	 * 
     * @param    attacker    initial attacker is player A
     * @param    attacker    initial opponent is player B
     */
	public void playGame( Player attacker, Player opponent ) {
		Player temp = null;
		int result = 0;
		Scanner sc = null;
		int k = 0;
		int coords[] = new int[2];
		
		// continues game unless all the ships of one player are sunk
		while(  result != TicTacToeGame.OVER && result != TicTacToeGame.DRAW ) {
			
			// Attacker keeps attacking unless it gets a miss.
			do {
				System.out.println( attacker.getName() + "\'s turn.");
				System.out.println( attacker.getName() + "... enter coordinates x, y of play: ");
		
				sc = new Scanner( System.in ); 
				sc.useDelimiter("\\s");
				k = 0;
				
				while( sc.hasNextInt() ) {
					coords[k++] = sc.nextInt();
				}
		
				result = attacker.reportStatusOfAttack( coords[0], coords[1] );
				
				switch ( result ) {
				    case TicTacToeGame.ALREADY_MARKED:
				        System.out.println( attacker.getName() + " this coordinate is already marked. Try again!"); 
				        break;
				        
				    case TicTacToeGame.MISS:
                        System.out.println( attacker.getName() + " this coordinate is out of bounds of the grid. Try again!"); 
                        break;
				}
				    
				
			} while ( result == TicTacToeGame.MISS || result == TicTacToeGame.ALREADY_MARKED );
		
			switch ( result ) {
                case TicTacToeGame.OVER:
                    System.out.println( attacker.getName() + " won! Game Over!!"); 
                    break;
                
                case TicTacToeGame.DRAW:
                    System.out.println( "This game was a draw!!"); 
                    break;
                   
                case TicTacToeGame.NO_SUCCESS:
                    // If game is not over, switch roles between players.
                    temp = attacker;
                    attacker = opponent;
                    opponent = temp;
                    break;
                    
			}
			
		}
		
	}
	
	
	/**
     * This method is used to create grids in the current game.
     * 
     */
	public void createGrid() {
	    aGrid   = new TicTacToeGrid( MIN_ROW, MIN_COLUMN, MAX_ROW, MAX_COLUMN );
        aGrid.initializeGrid();
        aGrid.showGrid();
	}
	
	/**
     * This method is used to create players in the current game.
     * 
     */
	public void createPlayers() {
	    playerA = new TicTacToePlayer ( aGrid, "Player A", 'X' );
        playerB = new TicTacToePlayer ( aGrid, "Player B", 'O' );
	}
	
	/**
	 * This method is the main method.
	 * 
     * @param    args    command line arguments(ignored) 
     */
	public static void main( String[] args ) {

	    // create current game
	    TicTacToeGame game = new TicTacToeGame();
	    
	    // create a Grid where both players will play
        game.createGrid();
        
        // create players
        game.createPlayers();
        
        // start game between the two players
       	game.playGame( game.playerA, game.playerB );
	}
	
} // TicTacToeGame
