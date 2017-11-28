package Assignment12RMI;

import java.io.Serializable;

/*
 * FleetInfo.java
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
 * This class represent basic info of Fleet.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */
public class FleetInfo implements Serializable {
    
    String[] shipNames;
    int[] lengthOfShips;
    int noOfShips;
    
    
    public FleetInfo(String[] shipNames, int[] lengthOfShips, int noOfShips) {
        this.shipNames =shipNames;
        this.lengthOfShips = lengthOfShips;
        this.noOfShips = noOfShips;
    }
    
    /**
     * This method is used to return name of ships in the fleet.
     * 
     * @return String Returns name of ships.
     */
    public String getNameOfShip( int  i ) {
        return shipNames[i];
    }
    
    /**
     * This method is used to return length of ships in the fleet.
     * 
     * @return int Returns length of ships.
     */
    public int getLengthOfShip( int  i ) {
        return lengthOfShips[i];
    }
    
    /**
     * This method is used to return number of ships in the fleet.
     * 
     * @return int Returns number of ships.
     */
    public int getNumberOfShips() {
        return noOfShips;
    }
}
