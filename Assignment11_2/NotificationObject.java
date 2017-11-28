/*
 * NotificationObject.java
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
 * This class represent NotificationObject sent from Model to View on update.
 * 
 * @author    Kritka Sahni
 * @author    Neha Upadhyay
 */
public class NotificationObject {

    private int playerNum;
    private int status;
    private Object info;
    
    public NotificationObject(int playerNum, int status, Object info) {
        this.playerNum = playerNum;
        this.status = status;
        this.info = info;
    }
    
    /**
     * This method is used to return playerId for which notification meant.
     * 
     * @return int Returns playerId.
     */
    public int getPlayer() {
        return playerNum;
    }
    
    /**
     * This method is used to return status.
     * 
     * @return int Returns status.
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * This method is used to return info object.
     * 
     * @return Object Returns info object.
     */
    public Object getInfo() {
        return info;
    }
}
