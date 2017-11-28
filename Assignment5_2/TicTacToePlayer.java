/*
 * TicTacToePlayer.java
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
* This class represents a Player in the TicTacToe game.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class TicTacToePlayer extends Player {
	
	//Marker of the player
	private char marker;
	
	public TicTacToePlayer( Grid grid, String name, char marker) {
		this.grid = grid;
		this.name = name;
		this.marker = marker;
	}
	
	/**
     * This method is used to determine if any ship of the player is hit.
   	 * 
   	 * @param     x      x-coordinate of target point    	
   	 * @param     y      y-coordinate of target point
     * @return    int    Uses TheGame constants for miss, over, already marked, 
     *                   no-success, draw.
   	 */
	public int reportStatusOfAttack( int x, int y ) {	
		
		int result = 0;
		
		if( !grid.checkCoordinatesInRange( x, y ) ) {
		    return TicTacToeGame.MISS;
		}
		
		if( grid.isMarked(x, y) ){
		    return TicTacToeGame.ALREADY_MARKED;
		}
		
		// mark (x,y)
		grid.mark( x, y, this.marker );
		
		// check if this player won 
		result = ( ( TicTacToeGrid ) grid ).checkResult( this.marker );
		grid.showGrid();
		
		return result;
		
	}


} // TicTacToePlayer
