/*
 * TicTacToeGrid.java
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
 * This class represents the TicTacToeGrid in a TicTacToe game.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */
public class TicTacToeGrid extends Grid {

    char[][] grid;
    
    public TicTacToeGrid( int minRow, int minCol, int maxRow, int maxCol ) {
        super( minRow, minCol, maxRow, maxCol );
        grid = new char[maxRow - minRow + 1][maxCol - minCol + 1];
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
        
    }
    
    /**
     * This method is used to initialize grid.
     * 
     */
    public void initializeGrid() {
        for ( int i = minRow; i <= maxRow; i++ ) {
            for ( int j = minCol; j <= maxCol; j++ ) {
                grid[i][j] = '_';
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
        grid[x][y] = (char)marker;
    }
    
    /**
     * This method is used to determine status at (x,y) in the grid.
     * @param     x      x-coordinate of target point       
     * @param     y      y-coordinate of target point
     * @return    int    Uses TheGame constants for miss, over, already marked, 
     *                   no-success, draw.
     */
    public int checkResult( char marker ) {
        
        int i = 0, j = 0;
        boolean flag  = false;
        
        //check main diagonal
        if ( grid[minRow][minCol] == marker ) {
          
            for ( i = minRow+1; i <= maxRow; i++ ) {
                if ( grid[i][i] != marker ) {
                    break;
                }
            }
            
            if ( i == maxRow + 1 ) {
                return TicTacToeGame.OVER;
            }
        }
        
        
        //check other diagonal
        if ( grid[minRow][maxCol] == marker ) {
          
            for ( i = minRow+1, j = maxCol-1; i <= maxRow; i++, j-- ) {
                if ( grid[i][j] != marker ) {
                   
                    break;
                }
             
            }
            
            if ( i == maxRow + 1 ) {
                return TicTacToeGame.OVER;
            }
        }
        
        
        //check each row
        for ( i = minRow; i <= maxRow; i++ ) {
                flag = true;
            for ( j = minCol; j <= maxCol; j++ ){
                if ( grid[i][j] != marker ){
                    flag = false;
                   break; 
                }
            }
            
            if ( flag ) {
                return TicTacToeGame.OVER;
            }
        } 
        
        //check each column
        for ( j = minCol; j <= maxCol; j++ ) {
                flag = true;
            for ( i = minRow; i <= maxRow; i++ ){
                if ( grid[i][j] != marker ){
                    flag = false;
                   break; 
                }
            }
            
            if ( flag ) {
                return TicTacToeGame.OVER;
            }
        } 
        
        flag = true;
        //check for draw
        //check each row
        for ( i = minRow; i <= maxRow && flag; i++ ) {
            for ( j = minCol; j <= maxCol; j++ ){
               if ( grid[i][j] == '_' ) {
                  flag = false; 
               }
            }
            
        }
        
        if( flag ) {
            return TicTacToeGame.DRAW;
        }
        
        return TicTacToeGame.NO_SUCCESS;
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
        if( grid[x][y] != '_' ){
            return true;
        }
        return false;
    }
    
} //TicTacToeGrid
