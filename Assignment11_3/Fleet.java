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
 * @author Kritka Sahni
 * @author Neha Upadhyay
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
     * This method is used to return basic info of fleet.
     * 
     * @return FleetInfo Returns basic info of fleet.
     */
    public FleetInfo getFleetInfo() {
        FleetInfo info = new FleetInfo(new String[] { ships[0].getName(),
                ships[1].getName(), ships[2].getName(), ships[3].getName() },
                new int[] { ships[0].getLength(), ships[1].getLength(), ships[2]
                        .getLength(), ships[3].getLength() }, NO_OF_SHIPS);
        return info;
    }

    /**
     * This method is used to return number of ships in the fleet.
     * 
     * @return int Returns number of ships.
     */
    public int getNumberOfShips() {
        return Fleet.NO_OF_SHIPS;
    }

    /**
     * This method is used to d if given coordinates overlap or touch the
     * current ship. For this it delegates calls to other ship objects for
     * check.
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
    public boolean checkOverlap(int x1, int y1, int x2, int y2) {
        Ship curr = null;

        // check with other arranged ships in case of overlap
        for (int i = 0; i < NO_OF_SHIPS; i++) {
            curr = ships[i];

            // found overlap
            if (curr.isArranged() && curr.isOverlap(x1, y1, x2, y2)) {
                return true;
            }
        }

        // no overlap found
        return false;
    }

    /**
     * This method is used to arrange i'th ship in the ocean.
     * 
     * @param i
     *            i'th ship in the fleet
     * @param x1
     *            x-coordinate of starting point
     * @param y1
     *            y-coordinate of starting point
     * @param x2
     *            x-coordinate of end point
     * @param y2
     *            y-coordinate of end point
     */
    public void arrange(int i, int x1, int y1, int x2, int y2) {
        ships[i].arrange(x1, y1, x2, y2);
    }

    /**
     * This method is used to determine if all ships sunk.
     * 
     * @return boolean True if all sunk else false.
     */
    public boolean isAllShipsSunk() {

        for (int i = 0; i < NO_OF_SHIPS; i++) {
            if (!ships[i].isSunk()) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method is used to determine if any ship is hit.
     * 
     * @param x
     *            x-coordinate of target point
     * @param y
     *            y-coordinate of target point
     * @return int Uses TheGame constants for miss, hit, sunk.
     */
    public int reportStatusOfAttack(int x, int y) {
        Ship curr = null;
        int result = BattleshipGame.MISS;

        for (int i = 0; i < NO_OF_SHIPS; i++) {
            curr = ships[i];

            result = curr.reportStatusOfAttack(x, y);
            if (result != BattleshipGame.MISS) {
                return result;
            }

        }

        return BattleshipGame.MISS;
    }

    /**
     * This method is used to return name of the i'th ship.
     * 
     * @return String Returns name.
     */
    public String getNameOfShip(int i) {
        return ships[i].getName();
    }

    /**
     * This method is used to return length of the i'th ship.
     * 
     * @return int Returns length.
     */
    public int getLengthOfShip(int i) {
        return ships[i].getLength();
    }

} // Fleet
