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

import java.io.PrintWriter;

/**
 * This class represents the Ocean in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class Ocean {

    int[][] grid;
    // min and max (x,y) of the Grid
    int minRow;
    int minCol;
    int maxRow;
    int maxCol;

    public Ocean(int minRow, int minCol, int maxRow, int maxCol) {
        // super( minRow, minCol, maxRow, maxCol );
        this.minRow = minRow;
        this.minCol = minCol;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        grid = new int[maxRow - minRow + 1][maxCol - minCol + 1];
    }

    /**
     * This method is used to determine whether entered coordinates lie within
     * Grid or not.
     * 
     * @param x1
     *            x-coordinate of starting point
     * @param y1
     *            y-coordinate of starting point
     * @return boolean Returns true if coordinates meet conditions
     */
    public boolean checkCoordinatesInRange(int x1, int y1) {

        if (x1 < minRow || y1 < minCol || x1 > maxRow || y1 > maxCol) {
            return false;
        }

        return true;
    }

    /**
     * This method is used to return minimum x coordinate.
     * 
     * @return int Returns x1.
     */
    public int getMinRow() {
        return minRow;
    }

    /**
     * This method is used to return minimum y coordinate.
     * 
     * @return int Returns y1.
     */
    public int getMinCol() {
        return minCol;
    }

    /**
     * This method is used to return maximum x coordinate.
     * 
     * @return int Returns x2.
     */
    public int getMaxRow() {
        return maxRow;
    }

    /**
     * This method is used to return maximum y coordinate.
     * 
     * @return int Returns y2.
     */
    public int getMaxCol() {
        return maxCol;
    }

    /**
     * This method is used to display grid.
     * 
     */
    public void showGrid() {
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                System.out.print(grid[i][j] + " ");
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
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                grid[i][j] = 0;
            }
        }
    }

    /**
     * This method is used to mark (x,y).
     * 
     * @param x
     *            x-coordinate of target point
     * @param y
     *            y-coordinate of target point
     * 
     */
    public void mark(int x, int y, int marker) {
        grid[x][y] = marker;
    }

    /**
     * This method is used to determine if (x,y) already marked.
     * 
     * @param x
     *            x-coordinate of target point
     * @param y
     *            y-coordinate of target point
     * 
     * @return true True if already marked else false
     * 
     */
    public boolean isMarked(int x, int y) {
        if (grid[x][y] != 0) {
            return true;
        }
        return false;
    }

} // Ocean
