/*
 * BattleshipPlayer.java
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
* This class represents the Player in the Battleship game.
* 
* @author    Kritka Sahni
* @author    Neha Upadhyay
*/
public class BattleshipPlayer extends Player {

	
	// Player has Fleet
	private Fleet fleet;
	
	// tracking grid for a player
	private int[][] trackingGrid = null;
	
	public BattleshipPlayer( Grid ocean, String name ) {
		this.grid = ocean;
		this.name = name;
		this.trackingGrid = 
		new int[( ocean.getMaxRow() - ocean.getMinRow() ) + 1][( ocean.getMaxCol() - ocean.getMinCol() ) + 1];
	}
	
	/**
     * This method is used to get coordinates for creating the fleet for this player.
     * 
     * @return    boolean    True if feet was created successfully else false.
     */
	private boolean getCoordinatesFromThePlayerAndArrange( int i, int len ){
		
		int coords[] = new int[3];
		int j = 0, k = 0, x1 = 0, y1 = 0, dir = 0, max_x = 0, max_y = 0;
		
		Scanner sc  = new Scanner( System.in );
		sc.useDelimiter( "\\s" );
		
		System.out.println( "Enter starting coordinates and direction(0-x/1-y)"
				+ " separated by space: ");
		
		while( sc.hasNextInt() ) {
			coords[k++] = sc.nextInt();
		}
		
		x1 = coords[0];
		y1 = coords[1];
		dir = coords[2];
		
		// check ship within bounds of the ocean
		// simply check start and end coordinates
		if( dir == 0 ){ // arranging along x-axis
		    max_x = x1;
		    max_y = y1 + len - 1 ;
		} else { // arranging along y-axis
		    
		    max_x = x1 + len - 1;
		    max_y = y1;
		}
		
		
		if( !grid.checkCoordinatesInRange( x1, y1 ) 
                || !grid.checkCoordinatesInRange( max_x, max_y ) ) {
            System.out.println( "Please enter coordinates again! "
                    + "Your coordinates should be within ocean..." );
            return false;
        }
		
		
		if( fleet.checkOverlap( x1, y1, max_x, max_y ) ) {
            System.out.println( "There is overlap! "
                    + "Please enter coordinates again." );
            return false;
        }
		
		for ( j = x1; j <= max_x; j++ ) {
		    for ( k = y1; k <= max_y; k++ ) {
		        grid.mark( j, k, 1 );
		    }
		}
		
		fleet.arrange( i, x1, y1, max_x, max_y );
		grid.showGrid();
		
		return true;
	}

	
	/**
     * This method is used to create the fleet for this player.
     * 
     * @return    boolean    True if feet was created successfully else false.
     */
	public boolean createTheFleet() {	
		this.fleet = new Fleet();
		
		int noOfShips = Fleet.getNumberOfShips();
		String str = null;
		int len = 0;
		int  i = 0;
		int count = 0;
		
		System.out.println( this.name+". Here is your grid...\n");
		grid.showGrid();
		
		while( i < noOfShips ) {
			
			//send player the name of i'th ship so that player could provide its coordinates
			str = fleet.getNameOfShip( i );
			len = fleet.getLengthOfShip( i );
			
			System.out.println( this.name + "... Enter coordinates for your "+ 
			          str + "(length:"+ len+ ")" );
			
			if( !this.getCoordinatesFromThePlayerAndArrange( i, len ) ) {
				if( count < 3 ) {
					count++;
				} else {
					System.out.println( "You are unable to arrange your ship on ocean. "
							+ "Game over!" );
					return false;
				}
				
			} else {
				count = 0;
				i++;
			}
		}
		
		return true;
	}
	
	/**
     * This method is used to determine if any ship of the player is hit.
     * 
     * @param     x      x-coordinate of target point
     * @param     y      y-coordinate of target point
     * @return    int    Uses TheGame constants for miss, hit, already marked, 
     *                   sunk, game over.
     */
	public int reportStatusOfAttack( int x, int y ) {	
		
		System.out.print(this.name + " says... ");
		
		if( trackingGrid[x][y] != 0) {
			switch( trackingGrid[x][y] ) {
		
				case BattleshipGame.MISS+1 : 
					System.out.println( "You already missed the spot. Try again with new coordinates!");
					break;
				
				case BattleshipGame.HIT+1 : 
					System.out.println( "You already hit here. Try again with new coordinates!");
					break;
				
				case BattleshipGame.SUNK+1 : 
					System.out.println( "You already hit here. Try again with new coordinates!");
					break;
			
			}
			
			return BattleshipGame.ALREADY_MARKED;
		}
		
		int result = fleet.reportStatusOfAttack( x, y );
		
		switch( result ) {
		
			case BattleshipGame.MISS:
				trackingGrid[x][y] = BattleshipGame.MISS+1;
				System.out.println( "It\'s a miss!" );
				break;
			case BattleshipGame.HIT:
				trackingGrid[x][y] = BattleshipGame.HIT+1;
				System.out.println( "My ship has been hit!" );
				break;
			case BattleshipGame.SUNK:
				trackingGrid[x][y] = BattleshipGame.SUNK+1;
				System.out.println( "My ship has been sunk!" );
				if( fleet.isAllShipsSunk() ) {
					result = BattleshipGame.OVER;
				}
				break;
				
		}
		
		return result;
		
	}

} // BattleshipPlayer
