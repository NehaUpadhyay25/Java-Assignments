package assignment4;
/*
 * Ocean.java
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
 * This class represents the Ocean in the Battleship game.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */
public class Ocean {
	
	// min and max (x,y) of the ocean
	private int minRow;
	private int minCol;
	private int maxRow;
	private int maxCol;
	
	public Ocean( int minRow, int minCol, int maxRow, int maxCol ) {
	
		this.minRow = minRow;
		this.minCol = minCol;
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		
	}
	
	/**
     	* This method is used to determine whether entered coordinates
     	* lie within ocean or not.
     	* 
     	* @param     x1      x-coordinate of starting point
     	* @param     y1      y-coordinate of starting point
     	* @param     x2      x-coordinate of end point
     	* @param     y2      y-coordinate of end point
     	* @return    boolean Returns true if coordinates meet conditions
     	*/
	public boolean checkShipCoordinatesInRange( int x1, int y1, int x2, int y2 ) {
		
		if( x1 < minRow || x2 < minRow || y1 < minCol || y2 < minCol || 
				x1 > maxRow || x2 > maxRow || y1 > maxCol || y2 > maxCol ) {
			return false;
		}
		
		return true;
	}
	
	/**
     	* This method is used to return minimum x coordinate.
     	* 
     	* @return    int    Returns x1.
     	*/
	public int getMinRow() {
		return minRow;
	}
	
	/**
     	* This method is used to return minimum y coordinate.
     	* 
     	* @return    int    Returns y1.
     	*/
	public int getMinCol() {
		return minCol;
	}
	
	/**
     	* This method is used to return maximum x coordinate.
     	* 
     	* @return    int    Returns x2.
     	*/
	public int getMaxRow() {
		return maxRow;
	}
	
	/**
     	* This method is used to return maximum y coordinate.
     	* 
     	* @return    int    Returns y2.
     	*/
	public int getMaxCol() {
		return maxCol;
	}

} // Ocean
