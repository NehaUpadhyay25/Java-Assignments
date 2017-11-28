package assignment4;
/*
 * Player.java
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
public class Player {

	private Ocean ocean;
	
	// Name of the player.
	private String name;
		
	// Player has Fleet
	private Fleet fleet;
	
	// tracking grid for a player
	private int[][] trackingGrid = null;
	
	public Player( Ocean ocean, String name ) {
		this.ocean = ocean;
		this.name = name;
		this.trackingGrid = 
		new int[( ocean.getMaxRow() - ocean.getMinRow() ) + 1][( ocean.getMaxCol() - ocean.getMinCol() ) + 1];
	}
	
	/**
     	* This method is used to get coordinates for creating the fleet for this player.
     	* 
     	* @return    boolean    True if feet was created successfully else false.
     	*/
	private boolean getCoordinatesFromThePlayerAndArrange( int i ){
		
		int coords[] = new int[4];
		int k = 0, x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		
		Scanner sc  = new Scanner( System.in );
		sc.useDelimiter( "\\s" );
		
		System.out.println( "Enter minimum and maximum coordinates"
				+ " separated by space: ");
		
		while( sc.hasNextInt() ) {
			coords[k++] = sc.nextInt();
		}
		
		x1 = coords[0];
		y1 = coords[1];
		x2 = coords[2];
		y2 = coords[3];
		
		if( !ocean.checkShipCoordinatesInRange( x1, y1, x2, y2 ) ) {
			System.out.println( "Please enter coordinates again! "
					+ "Your coordinates should be within ocean..." );
			return false;
		}
		
		if( !fleet.isCoordinatesWithinLength( i, x1, y1, x2, y2 ) ) {
			System.out.println( "Please enter coordinates again! Your coordinates"
					+ " should match the length of the ship and ship must have width = 1." );
			return false;
		}
		
		if( fleet.checkOverlap( x1, y1, x2, y2 ) ) {
			System.out.println( "There is overlap! "
					+ "Please enter coordinates again." );
			return false;
		}
		
		
		
		fleet.arrange( i, x1, y1, x2, y2 );
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
		
		while( i < noOfShips ) {
			
			//send player the name of i'th ship so that player could provide its coordinates
			str = fleet.getNameOfShip( i );
			len = fleet.getLengthOfShip( i );
			
			System.out.println( this.name + "... Enter coordinates for your "+ 
			          str + "(length:"+ len+ ")" );
			
			if( !this.getCoordinatesFromThePlayerAndArrange( i ) ) {
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
	public int reportHit( int x, int y ) {	
		
		//System.out.println(trackingGrid[x][y]);
		System.out.print(this.name + " says... ");
		
		if( trackingGrid[x][y] != 0) {
			switch( trackingGrid[x][y] ) {
		
				case TheGame.MISS+1 : 
					System.out.println( "You already missed the spot. Try again with new coordinates!");
					break;
				
				case TheGame.HIT+1 : 
					System.out.println( "You already hit here. Try again with new coordinates!");
					break;
				
				case TheGame.SUNK+1 : 
					System.out.println( "You already hit here. Try again with new coordinates!");
					break;
			
			}
			
			return TheGame.ALREADY_MARKED;
		}
		
		int result = fleet.reportHit( x, y );
		
		switch( result ) {
		
			case TheGame.MISS:
				trackingGrid[x][y] = TheGame.MISS+1;
				System.out.println( "It\'s a miss!" );
				break;
			case TheGame.HIT:
				trackingGrid[x][y] = TheGame.HIT+1;
				System.out.println( "My ship has been hit!" );
				break;
			case TheGame.SUNK:
				trackingGrid[x][y] = TheGame.SUNK+1;
				System.out.println( "My ship has been sunk!" );
				if( fleet.isAllShipsSunk() ) {
					result = TheGame.OVER;
				}
				break;
				
		}
		
		return result;
		
	}
	
	/**
     	* This method is used to return name of the player.
     	* 
     	* @return String Returns name.
     	*/
	public String getName() {
		return this.name;
	}

} // Player
