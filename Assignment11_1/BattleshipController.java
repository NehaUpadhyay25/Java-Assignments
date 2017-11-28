public class BattleshipController {

    BattleshipModel model = null;
    BattleshipView view = null;

    int count = 0;

    public BattleshipController(BattleshipModel model) {
        this.model = model;
        model.initialize();
        view = new BattleshipView(this, model);
        view.createView();
    }

    /**
     * This method is used to create the fleet for this player.
     * 
     * @param id
     *            id of player
     */
    public void createFleet(int i) {
        model.createFleet(i);
    }

    /**
     * This method is used to d if given coordinates overlap or touch the
     * current ship. For this it delegates calls to other ship objects for
     * check.
     * 
     * @param id
     *            id of player
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
    public boolean checkOverlap(int id, int x1, int y1, int x2, int y2) {

        return model.checkOverlap(id, x1, y1, x2, y2);
    }

    /**
     * This method is used to create the fleet for this player.
     * 
     * @param id
     *            id of player
     * @param x1
     *            x-coordinate of starting point
     * @param y1
     *            y-coordinate of starting point
     * @param x2
     *            x-coordinate of end point
     * @param y2
     *            y-coordinate of end point
     */
    public void arrange(int id, int i, int x1, int y1, int x2, int y2) {
        model.arrange(id, i, x1, y1, x2, y2);
    }

    /**
     * This method is used to determine if any ship of the player is hit.
     * 
     * @param id
     *            id of player
     * @param x
     *            x-coordinate of target point
     * @param y
     *            y-coordinate of target point
     * @return int Uses TheGame constants for miss, hit, already marked, sunk,
     *         game over.
     */
    public int reportStatusOfAttack(int id, int x, int y) {
        return model.reportStatusOfAttack(id, x, y);
    }

    /**
     * This method is used to determine if all ships sunk.
     * 
     * @param id
     *            id of player
     * @return boolean True if all sunk else false.
     */
    public boolean isAllShipsSunk(int id) {

        return model.isAllShipsSunk(id);
    }

}
