package Assignment13;
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

import javax.swing.*;
import java.io.InputStream;
import java.rmi.RemoteException;

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

    JLabel resultsLabel = null;

    // tracking grid for a player
    private int[][] trackingGrid = null;

    private InputStream stream;
    private BattleshipRemote service;

    public BattleshipPlayer(int id, String name, BattleshipRemote service, JLabel resultsLabel) {
        this.playerId = id;
        this.name = name;
        this.service = service;
        this.grid = new Ocean(BattleshipGame.minRow, BattleshipGame.minColumn,
                              BattleshipGame.maxRow, BattleshipGame.maxColumn);
        this.grid.initializeGrid();
        this.trackingGrid = new int[(this.grid.getMaxRow() - this.grid
                .getMinRow()) + 1][(this.grid.getMaxCol() - this.grid
                        .getMinCol()) + 1];
        this.resultsLabel = resultsLabel;
    }

    

    
    
    /**
     * This method is used to get coordinates for creating the fleet for this
     * player.
     * 
     * @return boolean True if feet was created successfully else false.
     */
    public boolean getCoordinatesFromThePlayerAndArrange(int x1, int y1, int shipId, int dir, int len) {

        int j = 0, k = 0, max_x = 0, max_y = 0;


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
            resultsLabel.setText("Please enter coordinates again! "
                    + "Your coordinates should be within ocean...");
            return false;
        }

        try {
            if (service.checkOverlap(this.playerId, x1, y1, max_x, max_y)) {
                resultsLabel.setText("There is overlap! "
                        + "Please enter coordinates again.");
                return false;
            }

            for (j = x1; j <= max_x; j++) {
                for (k = y1; k <= max_y; k++) {
                    grid.mark(j, k, 1);
                }
            }

            service.arrange(this.playerId, shipId, x1, y1, max_x, max_y);
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
            service.createFleet(this.playerId);
        } catch (RemoteException e) {
            e.printStackTrace();
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
