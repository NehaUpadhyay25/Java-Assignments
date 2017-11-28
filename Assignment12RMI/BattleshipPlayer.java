package Assignment12RMI;
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
import java.rmi.RemoteException;
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

    private InputStream stream;
    private BattleshipRemote service;

    public BattleshipPlayer(int id, String name, BattleshipRemote service) {
        this.playerId = id;
        this.name = name;
        this.service = service;
        this.grid = new Ocean(BattleshipGame.minRow, BattleshipGame.minColumn,
                              BattleshipGame.maxRow, BattleshipGame.maxColumn);
        this.grid.initializeGrid();
        this.trackingGrid = new int[(this.grid.getMaxRow() - this.grid
                .getMinRow()) + 1][(this.grid.getMaxCol() - this.grid
                        .getMinCol()) + 1];
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

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\\s");

        System.out.println("Enter starting coordinates and direction(0-x/1-y)"
                + " separated by space: ");

        while (sc.hasNextInt()) {
            coords[k++] = sc.nextInt();
        }

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
            System.out.println("Please enter coordinates again! "
                    + "Your coordinates should be within ocean...");
            return false;
        }

        try {
            if (service.checkOverlap(this.playerId, x1, y1, max_x, max_y)) {
                System.out.println("There is overlap! "
                        + "Please enter coordinates again.");
                return false;
            }

            for (j = x1; j <= max_x; j++) {
                for (k = y1; k <= max_y; k++) {
                    grid.mark(j, k, 1);
                }
            }

            service.arrange(this.playerId, i, x1, y1, max_x, max_y);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
    public int getPlayerId() {
        return this.playerId;
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
    public boolean createTheFleet() {
        try {
            this.fleetInfo = service.createFleet(this.playerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        int noOfShips = fleetInfo.getNumberOfShips();
        String str = null;
        int len = 0;
        int i = 0;
        int count = 0;

        System.out.println(this.name + ". Here is your grid...\n");
        grid.showGrid();

        while (i < noOfShips) {

            // send player the name of i'th ship so that player could provide
            // its coordinates
            str = fleetInfo.getNameOfShip(i);
            len = fleetInfo.getLengthOfShip(i);

            System.out.println(this.name + "... Enter coordinates for your "
                    + str + "(length:" + len + ")");

            if (!this.getCoordinatesFromThePlayerAndArrange(i, len)) {
                if (count < 3) {
                    count++;
                } else {
                    System.out.println(
                            "You are unable to arrange your ship on ocean. "
                                    + "Game over!");
                    return false;
                }

            } else {
                count = 0;
                i++;
            }
        }

        return true;
    }

    /**
     * This method is used to set tracking grid for this player.
     * 
     */
    public void setTrackingGrid(int x, int y, int status) {
        trackingGrid[x][y] = status;
    }

    /**
     * This method is used to get tracking grid for this player.
     * 
     */
    public int getTrackingGrid(int x, int y) {
        return trackingGrid[x][y];
    }

} // BattleshipPlayer
