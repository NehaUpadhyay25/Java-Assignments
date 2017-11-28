/*
 * BattleshipGame.java
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
public class BattleshipGame implements Gameable {
    
	// Minimum and maximum coordinates of the ocean as specified in the game.
	private static final int minRow = 0;
	private static final int minColumn = 0;
	private static final int maxRow = 9;
	private static final int maxColumn = 9;
	
	// Constants used to report ship hit, miss, sunk conditions 
	// and over when all ships of a player are sunk.
	public static final int MISS = 0;
	public static final int HIT = 1;
	public static final int SUNK = 2;
	public static final int OVER = 3;
	public static final int ALREADY_MARKED = 4;
	
	// grids in the current game
	Grid aOcean;
    Grid bOcean;
    
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
		int result = 1;
		Scanner sc = null;
		int k = 0;
		int coords[] = new int[2];
		
		// continues game unless all the ships of one player are sunk
		while(  result != BattleshipGame.OVER ) {
			
			// Attacker keeps attacking unless it gets a miss.
			do {
				System.out.println( attacker.getName() + "\'s turn.");
				System.out.println( attacker.getName() + "... enter coordinates x, y of attack: ");
		
				sc = new Scanner( System.in ); 
				sc.useDelimiter("\\s");
				k = 0;
				
				while( sc.hasNextInt() ) {
					coords[k++] = sc.nextInt();
				}
		
				result = opponent.reportStatusOfAttack( coords[0], coords[1] );
			} while ( result != BattleshipGame.OVER && result != BattleshipGame.MISS );
		
			// check if all the ships are sunk, then game over is displayed
			if ( result == BattleshipGame.OVER ) {
				System.out.println( attacker.getName() + " won! Game Over!!");
			} else {
				
				// If game is not over, switch roles between players.
				temp = attacker;
				attacker = opponent;
				opponent = temp;
			}
		
		}
		
	}
	
	/**
     * This method is used to create grids in the current game.
     * 
     */
	public void createGrid() {
	    aOcean   = new Ocean( minRow, minColumn, maxRow, maxColumn );
	    aOcean.initializeGrid();
	    
	    bOcean   = new Ocean( minRow, minColumn, maxRow, maxColumn );
	    bOcean.initializeGrid();
	}
	
	/**
     * This method is used to create players in the current game.
     * 
     */
	public void createPlayers() {
	 
        playerA = new BattleshipPlayer ( aOcean, "Player A" );
        playerB = new BattleshipPlayer ( bOcean, "Player B" );
    
        // create fleet of player A 
        if ( !((BattleshipPlayer)playerA).createTheFleet() ) {
            return;
        }
    
        // create fleet of player B
        if( !((BattleshipPlayer)playerB).createTheFleet() ) {
            return;
        }
	}
	
	/**
	 * This method is the main method.
	 * 
	 * @param    args    command line arguments(ignored) 
	 */
	public static void main( String[] args ) {
	    
	        // create the Battleship game
	        BattleshipGame game = new BattleshipGame();
	        
	        // create grids for the players
	        game.createGrid();
	        
	        // create players
	        game.createPlayers();
		          
        	// start game between the two players
        	game.playGame( game.playerA, game.playerB );
	}
	
} // BattleshipGame
