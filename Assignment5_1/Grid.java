/*
 * Grid.java
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
 * This class represents a Grid in a game.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */
public abstract class Grid {
	
	// min and max (x,y) of the Grid
	int minRow;
	int minCol;
	int maxRow;
	int maxCol;
	
	public Grid( int minRow, int minCol, int maxRow, int maxCol ) {
	
		this.minRow = minRow;
		this.minCol = minCol;
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		
	}
	
	/**
     * This method is used to determine whether entered coordinates
     * lie within Grid or not.
     * 
     * @param     x1      x-coordinate of starting point
     * @param     y1      y-coordinate of starting point
     * @return    boolean Returns true if coordinates meet conditions
     */
    public boolean checkCoordinatesInRange( int x1, int y1 ) {
        
        if( x1 < minRow || y1 < minCol ||  
                x1 > maxRow || y1 > maxCol ) {
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

	public abstract void initializeGrid();
	public abstract void showGrid();
	public abstract void mark( int x, int y, int marker );
	public abstract boolean isMarked( int x, int y );
	
} // Grid
