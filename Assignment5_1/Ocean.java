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
public class Ocean extends Grid {
	
    int[][] grid;
    
    public Ocean( int minRow, int minCol, int maxRow, int maxCol ) {
        super( minRow, minCol, maxRow, maxCol );
        grid = new int[maxRow - minRow + 1][maxCol - minCol + 1];
    }
    
    /**
     * This method is used to display grid.
     * 
     */
    public void showGrid() {
        for ( int i = minRow; i <= maxRow; i++ ) {
            for ( int j = minCol; j <= maxCol; j++ ) {
                System.out.print( grid[i][j] + " " );
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * This method is used to initialize grid.
     * 
     */
    public void initializeGrid() {
        for ( int i = minRow; i <= maxRow; i++ ) {
            for ( int j = minCol; j <= maxCol; j++ ) {
                grid[i][j] = 0;
            }
        }
    }
    
    /**
     * This method is used to mark (x,y).
     * 
     * @param     x      x-coordinate of target point
     * @param     y      y-coordinate of target point
     * 
     */
    public void mark( int x, int y, int marker ) {
        grid[x][y] = marker;
    }
    
    /**
     * This method is used to determine if (x,y) already marked.
     * 
     * @param     x      x-coordinate of target point
     * @param     y      y-coordinate of target point
     * 
     * @return    true    True if already marked else false
     * 
     */
    public boolean isMarked( int x, int y ) {
        if( grid[x][y] != 0 ){
            return true;
        }
        return false;
    }

} // Ocean
