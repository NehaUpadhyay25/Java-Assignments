package Assignment12RMI;/*
 * Ship.java
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
 * This class is a template for Ship in the Battleship game. Each ship has
 * length, name, coordinates and also maintains its state like hits at its
 * coordinates, sunk or not and whether is arranged in ocean or lying idle in
 * the fleet.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */

public class Ship {

    int length;
    int x1;
    int y1;
    int x2;
    int y2;
    boolean[] hits = null;
    boolean sunk = false;
    String name;
    boolean isArranged = false;

    // variables to determine neighbor locations
    private static int[] x_nbr = { 0, 0, 1, -1 };
    private static int[] y_nbr = { 1, -1, 0, 0 };

    /**
     * This method is used to give the ship its coordinates.
     * 
     * @param x1
     *            x-coordinate of starting point
     * @param y1
     *            y-coordinate of starting point
     * @param x2
     *            x-coordinate of end point
     * @param y2
     *            y-coordinate of end point
     */
    public void arrange(int x1, int y1, int x2, int y2) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.isArranged = true;

        // get ready for hits
        this.hits = new boolean[this.length];

    }

    /**
     * This method is used to return whether ship arranged or not.
     * 
     * @return boolean Returns true if ship present or ocean else false.
     */
    public boolean isArranged() {
        return this.isArranged;
    }

    /**
     * This method is used to determine if given coordinates overlap or touch
     * the current ship.
     * 
     * @param x1
     *            x-coordinate of starting point
     * @param y1
     *            y-coordinate of starting point
     * @param x2
     *            x-coordinate of end point
     * @param y2
     *            y-coordinate of end point
     * @return boolean True if overlap or touch else false.
     */
    public boolean isOverlap(int x1, int y1, int x2, int y2) {

        int x = 0, y = 0, k = 0;
        int x_co = 0, y_co = 0;

        // check adjacency
        // for each of coordinates of this ship, check if (x,y) is in its
        // neighbourhood

        // establish start and end based on direction
        if (this.x1 == this.x2) {
            x = this.x1;
            for (y = this.y1; y <= this.y2; y++) {
                for (k = 0; k < 4; k++) {
                    x_co = x + x_nbr[k];
                    y_co = y + y_nbr[k];

                    if ((x_co >= x1 && x_co <= x2) && (y_co >= y1
                            && y_co <= y2)) {
                        return true;
                    }

                }

            }
        } else {
            y = this.y1;
            for (x = this.x1; x <= this.x2; x++) {
                for (k = 0; k < 4; k++) {
                    x_co = x + x_nbr[k];
                    y_co = y + y_nbr[k];

                    if ((x_co >= x1 && x_co <= x2) && (y_co >= y1
                            && y_co <= y2)) {
                        return true;
                    }

                }
            }
        }

        return false;
    }

    /**
     * This method is used to determine if the ship hit at its coordinates.
     * 
     * @param x
     *            x-coordinate of target point
     * @param y
     *            y-coordinate of target point
     * @return int Uses TheGame constants for miss, hit, sunk.
     */
    public int reportStatusOfAttack(int x, int y) {

        if ((x >= this.x1 && x <= this.x2) && (y >= this.y1 && y <= this.y2)) {

            hits[(x - x1) + (y - y1)] = true;

            int i = 0;
            while (i < this.length && hits[i]) {
                i++;
            }

            if (i == this.length) {
                sunk = true;
                return BattleshipGame.SUNK;
            }

            return BattleshipGame.HIT;
        }

        return BattleshipGame.MISS;
    }

    /**
     * This method is used to return whether ship sunk or not.
     * 
     * @return boolean Returns true if ship is sunk else false.
     */
    public boolean isSunk() {
        return sunk;
    }

    /**
     * This method is used to return name of the ship.
     * 
     * @return String Returns name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method is used to return length of the ship.
     * 
     * @return int Returns length.
     */
    public int getLength() {
        return this.length;
    }

} // Ship
