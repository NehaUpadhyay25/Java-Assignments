/*
 * BattleshipPlayer.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class represents the Player in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipPlayer {

    // Name of the player.
    String name;
    int playerId;

    // Grid of the player
    Ocean grid;

    // Player has Fleet
    private FleetInfo fleetInfo;

    // tracking grid for a player
    private int[][] trackingGrid = null;

    // controller
    private BattleshipController controller;

    private PrintWriter clientOutputStream;
    private InputStream stream;

    public BattleshipPlayer(Ocean ocean, String name, int id,
            BattleshipController controller) {
        this.grid = ocean;
        this.name = name;
        this.playerId = id;
        this.controller = controller;
        this.trackingGrid = new int[(ocean.getMaxRow() - ocean.getMinRow())
                + 1][(ocean.getMaxCol() - ocean.getMinCol()) + 1];
    }

    public BattleshipPlayer(Ocean ocean, String name, int id,
            BattleshipController controller, PrintWriter clientOutputStream,
            InputStream stream) {
        this.grid = ocean;
        this.name = name;
        this.playerId = id;
        this.controller = controller;
        this.clientOutputStream = clientOutputStream;
        this.stream = stream;
        this.trackingGrid = new int[(ocean.getMaxRow() - ocean.getMinRow())
                + 1][(ocean.getMaxCol() - ocean.getMinCol()) + 1];
    }

    /**
     * This method is used to get coordinates for creating the fleet for this
     * player.
     * 
     * @return boolean True if feet was created successfully else false.
     */
    private boolean getCoordinatesFromThePlayerAndArrange(int i, int len) {

        int coords[] = new int[3];
        int j = 0, k = 0, x1 = 0, y1 = 0, dir = 0, max_x = 0, max_y = 0;

        /*
         * Scanner sc = new Scanner( System.in ); sc.useDelimiter( "\\s" );
         */

        Scanner sc = new Scanner(stream);
        clientOutputStream.println(
                "Enter starting coordinates and direction(0-x/1-y)"
                        + " separated by space. Enter now:- ");
        clientOutputStream.flush();

        // we receive only 1 line from client
        String line = sc.nextLine();
        int begin = 0;
        int end = line.indexOf(" ", begin);
        while (end != -1) {
            coords[k++] = new Integer(line.substring(begin, end));
            begin = end + 1;
            end = line.indexOf(" ", begin);
        }

        coords[k++] = new Integer(line.substring(begin, line.length()));

        x1 = coords[0];
        y1 = coords[1];
        dir = coords[2];

        // check ship within bounds of the ocean
        // simply check start and end coordinates
        if (dir == 0) { // arranging along x-axis
            max_x = x1;
            max_y = y1 + len - 1;
        } else { // arranging along y-axis

            max_x = x1 + len - 1;
            max_y = y1;
        }

        if (!grid.checkCoordinatesInRange(x1, y1) || !grid
                .checkCoordinatesInRange(max_x, max_y)) {
            clientOutputStream.println("Please enter coordinates again! "
                    + "Your coordinates should be within ocean...");
            clientOutputStream.flush();
            return false;
        }

        if (controller.checkOverlap(playerId, x1, y1, max_x, max_y)) {
            
            clientOutputStream.println("There is overlap! "
                    + "Please enter coordinates again.");
            clientOutputStream.flush();
            return false;
        }

        for (j = x1; j <= max_x; j++) {
            for (k = y1; k <= max_y; k++) {
                grid.mark(j, k, 1);
            }
        }

        controller.arrange(playerId, i, x1, y1, max_x, max_y);
        grid.showGrid();

        return true;
    }

    /**
     * This method is used to return name of the player.
     * 
     * @return String Returns name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method is used to return name of the player.
     * 
     * @return String Returns name.
     */
    public PrintWriter getOutputStream() {
        return this.clientOutputStream;
    }

    /**
     * This method is used to return name of the player.
     * 
     * @return String Returns name.
     */
    public InputStream getInputStream() {
        return this.stream;
    }

    /**
     * This method is used to create the fleet for this player.
     * 
     */
    public void createTheFleet() {

        controller.createFleet(playerId);
    }

    /**
     * This method is used to set the fleet info for this player.
     * 
     */
    public void setFleetInfo(FleetInfo info) {
        this.fleetInfo = info;
    }

    /**
     * This method is used to create the fleet for this player.
     * 
     * @return boolean True if feet was created successfully else false.
     */
    public boolean arrangeTheFleet() {
        int noOfShips = fleetInfo.getNumberOfShips();
        String str = null;
        int len = 0;
        int i = 0;

        clientOutputStream.println(this.name + ". Please arrange your fleet. Here is your grid...\n");

        grid.showGrid();

        while (i < noOfShips) {

            // send player the name of i'th ship so that player could provide
            // its coordinates
            str = fleetInfo.getNameOfShip(i);
            len = fleetInfo.getLengthOfShip(i);

            clientOutputStream.println(this.name
                    + "... Enter coordinates for your " + str + "(length:" + len
                    + ").");

            while (!this.getCoordinatesFromThePlayerAndArrange(i, len)) {
                // keep requesting coordinates till player enters correctly
            }
            i++;
        }

        return true;
    }

    /**
     * This method is used to determine if any ship of the player is hit.
     * 
     * @param x
     *            x-coordinate of target point
     * @param y
     *            y-coordinate of target point
     * @param attackerOutputStream
     *            output stream of attacker
     * @return int Uses TheGame constants for miss, hit, already marked, sunk,
     *         game over.
     */
    public int reportStatusOfAttack(int x, int y,
            PrintWriter attackerOutputStream) {

        attackerOutputStream.print(this.name + " says... ");

        if (trackingGrid[x][y] != 0) {
            switch (trackingGrid[x][y]) {

            case BattleshipGame.MISS + 1:
                attackerOutputStream.println(
                        "You already missed the spot. Try again with new coordinates!");
                break;

            case BattleshipGame.HIT + 1:
                attackerOutputStream.println(
                        "You already hit here. Try again with new coordinates!");
                break;

            case BattleshipGame.SUNK + 1:
                attackerOutputStream.println(
                        "You already hit here. Try again with new coordinates!");
                break;

            }
            attackerOutputStream.flush();
            return BattleshipGame.ALREADY_MARKED;
        }

        int result = controller.reportStatusOfAttack(playerId, x, y);

        switch (result) {

        case BattleshipGame.MISS:
            trackingGrid[x][y] = BattleshipGame.MISS + 1;
            attackerOutputStream.println("It\'s a miss!");
            break;
        case BattleshipGame.HIT:
            trackingGrid[x][y] = BattleshipGame.HIT + 1;
            this.clientOutputStream.println("Attacker has hit your ship.");
            attackerOutputStream.println("My ship has been hit!");
            break;
        case BattleshipGame.SUNK:
            trackingGrid[x][y] = BattleshipGame.SUNK + 1;
            this.clientOutputStream.println("Attacker has sunk your ship.");
            attackerOutputStream.println("My ship has been sunk!");
            if (controller.isAllShipsSunk(playerId)) {
                result = BattleshipGame.OVER;
            }
            break;

        }
        this.clientOutputStream.flush();
        attackerOutputStream.flush();
        return result;

    }

} // BattleshipPlayer
