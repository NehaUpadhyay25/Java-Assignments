
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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
    private DatagramSocket sock;
    
    static Object o = new Object();
    static Object o1 = new Object();
    
    int count_nxt_ip = 0;
    int count_res_nxt_ip = 0;
    
    String gridErrorRes = "";
    public BattleshipPlayer(Ocean ocean, String name, int id,
            BattleshipController controller, DatagramSocket sock) {
        this.grid = ocean;
        this.name = name;
        this.playerId = id;
        this.controller = controller;
        this.sock = sock;
        this.trackingGrid = new int[(ocean.getMaxRow() - ocean.getMinRow())
                + 1][(ocean.getMaxCol() - ocean.getMinCol()) + 1];
    }

    /**
     * This method is used to get coordinates for creating the fleet for this
     * player.
     * 
     * @return boolean True if feet was created successfully else false.
     */
    private boolean getCoordinatesFromThePlayerAndArrange(int i, int len, String gridRes, int port, InetAddress addr) {

        int coords[] = new int[3];
        int j = 0, k = 0, x1 = 0, y1 = 0, dir = 0, max_x = 0, max_y = 0;

        /*Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\\s");
        while (sc.hasNextInt()) {
            coords[k++] = sc.nextInt();
        }*/

        gridRes += "Enter starting coordinates and direction(0-x/1-y)"
                    + " separated by space: \n";
        byte[] buf = new byte[1024];
        buf = gridRes.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length,addr,port);
        
        
        
        
        String req = null;

        
        byte[] buf2 = new byte[256];
        DatagramPacket packet2 = new DatagramPacket(buf2, buf2.length);
        
        synchronized(o1){
        //receive first packet for sending back rules
        while( true ) {
            try {
                //till request for next input not received keep sending this packet RES_GRID

                sock.send(packet);
                try{
                    Thread.currentThread().sleep(10000);
                }catch(Exception e){
                    e.printStackTrace();
                }
                
                System.out.println(this.name+" waiting for REQ_nxt_IP"+count_nxt_ip);
                buf2 = new byte[512];
                packet2 = new DatagramPacket(buf2, buf2.length);
                sock.receive(packet2);
                req = new String(packet2.getData());
                System.out.println("recvd:- "+req);
                if(req.contains(this.name) && req.contains(BattleshipGame.REQ_NXT_IP+count_nxt_ip)){
                    //received a valid request from current player so send him his grid
                    System.out.println("recvd  req_ip"+this.name);
                    count_nxt_ip++;
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
        
        //from the req_next_input, extract coords
        String pktData2 = new String(packet2.getData());
        pktData2 = pktData2.substring(pktData2.indexOf(".")+1);
        System.out.println(pktData2);
        int s1 = pktData2.indexOf(" ");
        x1 = new Integer(pktData2.substring(0, s1));
        int s2 = pktData2.indexOf(" ",s1+1);
        y1 = new Integer(pktData2.substring(s1+1, s2));
        int s3 = pktData2.indexOf(".",s2+1);
        dir =  new Integer(pktData2.substring(s2+1,s3));
        System.out.println("recvd: "+x1+" "+y1+" "+dir);
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
            gridErrorRes += "Please enter coordinates again! "
                    + "Your coordinates should be within ocean...";
           
            return false;
        }

        if (controller.checkOverlap(playerId, x1, y1, max_x, max_y)) {
            gridErrorRes += "There is overlap! "
                    + "Please enter coordinates again." ;
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
        String req = null;

        // wait for get grid request
        //receive first packet for sending back rules
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        synchronized (o) {
            // receive first packet for sending back rules
            while (true) {
                try {
                    System.out.println(this.name + " waiting for grid_req");
                    buf = new byte[256];
                    packet = new DatagramPacket(buf, buf.length);
                    sock.receive(packet);
                    req = new String(packet.getData());
                    System.out.println("recvd:- " + req);
                    if (req.contains(this.name) && req.contains(
                            BattleshipGame.REQ_GRID)) {
                        // received a valid request from current player so send
                        // him his grid
                        System.out.println("recvdgrid_req " + this.name);
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        int port = packet.getPort();
        InetAddress addr = packet.getAddress();
        String gridRes = this.name+" RES: "+BattleshipGame.RES_GRID+"."+this.name + ". Here is your grid...\n";
        gridRes += grid.showGrid();
        
        
        while (i < noOfShips) {

            // send player the name of i'th ship so that player could provide
            // its coordinates
            str = fleetInfo.getNameOfShip(i);
            len = fleetInfo.getLengthOfShip(i);

            gridRes += this.name + "... Enter coordinates for your "
                        + str + "(length:" + len + ").\n";
            while (!this.getCoordinatesFromThePlayerAndArrange(i, len, gridRes, port, addr)) {
                // keep requesting coordinates till player enters correctly
                gridRes = this.name+" RES: "+BattleshipGame.RES_NXT_IP+this.count_res_nxt_ip+".";
                gridRes = this.gridErrorRes;
                count_res_nxt_ip++;

            }
           
            gridRes = this.name+" RES: "+BattleshipGame.RES_NXT_IP+this.count_res_nxt_ip+".";
            count_res_nxt_ip++;

            i++;
        }
        //server here after arranging all the ships, now safe to send end at the end of next response message
        gridRes += BattleshipGame.END_REQ_NXT_IP+".";
        buf = gridRes.getBytes();
        packet = new DatagramPacket(buf, buf.length,addr,port);
        
                try {
                    //till request for next input not received keep sending this packet RES_GRID

                    sock.send(packet);
                    
                } catch (IOException e) {
                    e.printStackTrace();
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
     * @return int Uses TheGame constants for miss, hit, already marked, sunk,
     *         game over.
     */
    public int reportStatusOfAttack(int x, int y) {

        System.out.print(this.name + " says... ");

        if (trackingGrid[x][y] != 0) {
            switch (trackingGrid[x][y]) {

            case BattleshipGame.MISS + 1:
                System.out.println(
                        "You already missed the spot. Try again with new coordinates!");
                break;

            case BattleshipGame.HIT + 1:
                System.out.println(
                        "You already hit here. Try again with new coordinates!");
                break;

            case BattleshipGame.SUNK + 1:
                System.out.println(
                        "You already hit here. Try again with new coordinates!");
                break;

            }

            return BattleshipGame.ALREADY_MARKED;
        }

        int result = controller.reportStatusOfAttack(playerId, x, y);

        switch (result) {

        case BattleshipGame.MISS:
            trackingGrid[x][y] = BattleshipGame.MISS + 1;
            System.out.println("It\'s a miss!");
            break;
        case BattleshipGame.HIT:
            trackingGrid[x][y] = BattleshipGame.HIT + 1;
            System.out.println("My ship has been hit!");
            break;
        case BattleshipGame.SUNK:
            trackingGrid[x][y] = BattleshipGame.SUNK + 1;
            System.out.println("My ship has been sunk!");
            if (controller.isAllShipsSunk(playerId)) {
                result = BattleshipGame.OVER;
            }
            break;

        }

        return result;

    }

} // BattleshipPlayer
