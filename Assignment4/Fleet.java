package assignment4;
/*
 * Fleet.java
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
 * This class represents the Fleet in the Battleship game.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */
public class Fleet {

	// Number of ships in the fleet.
	private static final int NO_OF_SHIPS = 4;
	
	private Ship[] ships = new Ship[NO_OF_SHIPS];

	public Fleet() {
		ships[0] = new Battleship();
		ships[1] = new Carrier();
		ships[2] = new Cruiser();
		ships[3] = new Destroyer();
	}
	
	/**
        * This method is used to return number of ships in the fleet.
     	* 
    	* @return int Returns number of ships.
    	*/
	public static int getNumberOfShips() {
		return Fleet.NO_OF_SHIPS;
	}
	
	/**
     	* This method is used to d if given coordinates overlap or touch the 
     	* current ship. For this it delegates calls to other ship objects for check.
     	* 
     	* @param     x1      x-coordinate of starting point
     	* @param     y1      y-coordinate of starting point
     	* @param     x2      x-coordinate of end point
     	* @param     y2      y-coordinate of end point
     	* @return    boolean True if overlap or touch else false.
     	*/
	public boolean checkOverlap ( int x1, int y1, int x2, int y2 ) {
		Ship curr = null; 
		
		// check with other arranged ships in case of overlap
		for( int i = 0; i < NO_OF_SHIPS; i++ ) {
			curr = ships[i];
			
			// found overlap
			if( curr.isArranged() && curr.isOverlap( x1, y1, x2, y2 ) ) {
				return true;
			}
		}
		
		// no overlap found 
		return false;
	}
	
	/**
     	* This method is used to determine whether incoming coordinates
     	* match i'th ship length or not. Also width needs to checked should be 1.
     	* Ship has to be present straight in the ocean and not otherwise.
     	* For this it delegates calls to other ship objects for check.
     	* 
     	* @param     i       i'th ship in the fleet
     	* @param     x1      x-coordinate of starting point
     	* @param     y1      y-coordinate of starting point
     	* @param     x2      x-coordinate of end point
     	* @param     y2      y-coordinate of end point
     	* @return    boolean Returns true if coordinates meet conditions
     	*/
	public boolean isCoordinatesWithinLength( int  i , int x1, int y1, int x2, int y2 ) {
		return ships[i].isCoordinatesWithinLength( x1, y1, x2, y2 );
	}
	
	/**
     	* This method is used to arrange i'th ship in the ocean.
     	* 
     	* @param     i       i'th ship in the fleet
     	* @param     x1      x-coordinate of starting point
     	* @param     y1      y-coordinate of starting point
     	* @param     x2      x-coordinate of end point
     	* @param     y2      y-coordinate of end point
     	*/
	public void arrange( int  i , int x1, int y1, int x2, int y2 ) {
		ships[i].arrange( x1, y1, x2, y2 );
	}
	
	/**
     	* This method is used to determine if all ships sunk.
     	* 
     	* @return    boolean    True if all sunk else false.
     	*/
	public boolean isAllShipsSunk() {
		
		for ( int i = 0; i < NO_OF_SHIPS; i++ ) {
			if( !ships[i].isSunk() ) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
     	* This method is used to determine if any ship is hit.
     	* 
     	* @param     x      x-coordinate of target point
     	* @param     y      y-coordinate of target point
     	* @return    int    Uses TheGame constants for miss, hit, sunk.
     	*/
	public int reportHit( int x, int y ) {
		Ship curr = null;
		int result = TheGame.MISS;
		
		for( int i = 0; i < NO_OF_SHIPS; i++ ) {
			curr = ships[i];
				
			result = curr.reportHit( x, y );
			if( result != TheGame.MISS ) {
				return result;
			}

		}
		
		return TheGame.MISS;
	}
	
	/**
     	* This method is used to return name of the i'th ship.
    	* 
     	* @return String Returns name.
     	*/
	public String getNameOfShip( int i ) {
		return ships[i].getName();
	}
	
	/**
     	* This method is used to return length of the i'th ship.
     	* 
     	* @return int Returns length.
     	*/
	public int getLengthOfShip( int i ) {
		return ships[i].getLength();
	}
	
} // Fleet

