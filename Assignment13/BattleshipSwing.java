package Assignment13;/*
 * BattleshipSwing.java
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * This class starts and maintains the client in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */

public class BattleshipSwing {

    static BattleshipSwing client = null;
    BattleshipPlayer player = null;
    BattleshipRemote service = null;
    int dir = 0;
    String currShipLabel = "C";
    JLabel resultsLabel = new JLabel("");
    JTextArea name = null;
    JPanel gridPane = null;
    int shipId = 1;
    int currShipCount = 0;
    int currShipLen = 0;
    boolean arranging = false;
    boolean first = true;
    int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
    JButton[][] trackingButtons = new JButton[10][10];
    JButton[][] primaryButtons = new JButton[10][10];
    int[][] marker = new int[10][10];
    boolean disconnect = false;
    
    /**
     * This method connects the client with server
     */
    
    public void connect() {
        Registry reg;
        try {
            reg = LocateRegistry.getRegistry(1078);
            service = (BattleshipRemote) reg.lookup(
                    "BattleshipRemoteImplObject");
            service.connect();
            String playerName = name.getText();
            int id = service.getPlayerId();
            player = new BattleshipPlayer(id, playerName, service, resultsLabel);
            player.createTheFleet();
            service.setPlayerName(id, playerName);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates the player grid
     * @return pane created pane 
     */
    public JPanel createPlayerGrid() {
        JPanel pane = new JPanel();

        pane.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int i1 = i;
                final int j1 = j;
                JButton button = new JButton("     0     ");
                button.setName(i1 + ":" + j1);// this is to retrieve coords
                primaryButtons[i][j] = button;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        button.setText(currShipLabel);
                        button.setEnabled(false);
                        // button.set
                        if (arranging && currShipCount == 1) {
                            String name = button.getName();
                            int in = name.indexOf(':');
                            x1 = new Integer(name.substring(0, in));
                            y1 = new Integer(name.substring(in + 1));

                        }

                        if (arranging && currShipCount == currShipLen) {
                            String name = button.getName();
                            int in = name.indexOf(':');
                            x2 = new Integer(name.substring(0, in));
                            y2 = new Integer(name.substring(in + 1));
                            if (x1 <= x2 && y1 <= y2) {
                                if( player.getCoordinatesFromThePlayerAndArrange(x1,
                                        y1, shipId, dir, currShipLen) == false){
                                    for(int i = x1;i<=x2;i++){
                                        for(int j=y1;j<=y2;j++){
                                            System.out.println(i+" "+j);
                                            primaryButtons[i][j].setEnabled(true);
                                            primaryButtons[i][j].setText("     0     ");
                                        }
                                    }
                                }
                            } else {
                                if(player.getCoordinatesFromThePlayerAndArrange(x2,
                                        y2, shipId, dir, currShipLen) == false){
                                    for(int i = x2;i<=x1;i++){
                                        for(int j=y2;j<=y1;j++){
                                            System.out.println(i+" "+j);
                                            primaryButtons[i][j].setEnabled(true);
                                            primaryButtons[i][j].setText("     0     ");
                                        }
                                    }
                                    
                                }

                            }
                            currShipCount = 0;
                            arranging = false;
                        }
                        currShipCount++;
                       
                    }

                });

                pane.add(button);
            }

        }
        return pane;
    }
    
    /**
     * This method is used to implement attack
     * @param x coordinates x
     * @param y coordinates y
     */

    public void attack( int x, int y ) {
        int track = player.getTrackingGrid(x, y);
        int result = 0;
        String oppoName = "";
        try {
            oppoName = service.getOpponentName(player.getPlayerId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
        if (track != 0) {
            switch (track) {

            case BattleshipGame.MISS + 1:
                resultsLabel.setText
                (
                        oppoName + " says..."+"You already missed the spot. Try again with new coordinates!");
                break;

            case BattleshipGame.HIT + 1:
                resultsLabel.setText(oppoName + " says..."+
                        "You already hit here. Try again with new coordinates!");
                break;

            case BattleshipGame.SUNK + 1:
                resultsLabel.setText(oppoName + " says..."+
                        "You already hit here. Try again with new coordinates!");
                break;

            }
            result = BattleshipGame.ALREADY_MARKED;

        } else {
            try {
                result = service.reportStatusOfAttack(player
                        .getPlayerId(), x, y);//get result of attack on opponent
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            switch (result) {

            case BattleshipGame.MISS:
                player.setTrackingGrid(x, y, BattleshipGame.MISS
                                             + 1);
                resultsLabel.setText(oppoName + " says..."+"It\'s a miss!");
                // result is miss, so hide the tracking grid so that this player can't play next
                for(int i=0;i<10;i++){
                    for(int j=0;j<10;j++){
                        trackingButtons[i][j].setEnabled(false);
                    }
                }
                //start the thread where this player waits for response from server
                new BattleshipClientPlayThread(player,service,resultsLabel,client).start();
                break;
            case BattleshipGame.HIT:
                player.setTrackingGrid(x, y, BattleshipGame.HIT
                                             + 1);

                resultsLabel.setText(oppoName + " says..."+"My ship has been hit!");
                break;
            case BattleshipGame.SUNK:
                player.setTrackingGrid(x, y, BattleshipGame.SUNK
                                             + 1);
                // this.clientOutputStream.println("Attacker has
                // sunk your ship.");
                resultsLabel.setText(oppoName + " says..."+"My ship has been sunk!");
                try {
                    if (service.isAllShipsSunk(player.getPlayerId() == 0
                            ? 1 : 0)) {
                        result = BattleshipGame.OVER;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;

            }
        }
        
     // check if all the ships are sunk, then game over is displayed
        if (result == BattleshipGame.OVER) {

            try {
                resultsLabel.setText(service.getWinnerName()
                        + " won! Game Over!!");
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * This method creates the grid
     * @return pane pane of the grid
     */
    public JPanel createTrackingGrid() {
        JPanel pane = new JPanel();

        pane.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int i1 = i;
                final int j1 = j;
                JButton button = new JButton("     0     ");
                button.setName(i1 + ":" + j1);// this is to retrieve coordinates
                // add each of these buttons in a list of buttons to disable them when its not this player's turn
                
                // also create a marker for each button to indicate player already attacker here
                trackingButtons[i][j] = button;
                this.marker[i][j] = 0; 
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        button.setText("X");
                        button.setEnabled(false);

                        String name = button.getName();
                        int in = name.indexOf(':');
                        int x = new Integer(name.substring(0, in));
                        int y = new Integer(name.substring(in + 1));
                        marker[x][y] = 1;
                        attack(x, y);

                    }
                });

                pane.add(button);
            }

        }

        return pane;
    }
    /**
     * This method is used to create the buttons
     * @return gridPane the create grid
     */
    public Component createButtons() {
        gridPane = new JPanel();
        gridPane.setBorder(BorderFactory.createLoweredBevelBorder());

        gridPane.setLayout(new BoxLayout(gridPane, BoxLayout.Y_AXIS));
        gridPane.add(resultsLabel);
        gridPane.add(createPlayerGrid());
        return gridPane;
    }
    /**
     * This method creates more buttons
     * @return pane the created pane
     */
    public Component createExtraButtons() {
        JPanel pane = new JPanel();

        pane.setBorder(BorderFactory.createLoweredBevelBorder());

        pane.setLayout(new GridLayout(4, 2));
        JLabel label = new JLabel("Your Name:");
        pane.add(label);
        name = new JTextArea();
        pane.add(name);
        JButton buttonConnect = new JButton("Connect");
        pane.add(buttonConnect);

        buttonConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if( disconnect == false ) {
                    buttonConnect.setText("Disconnect");
                    if(first == true ) {
                        connect();
                        first = false;
                    } else {
                        for(int i=0;i<10;i++){
                            for(int j=0;j<10;j++){
                                if(marker[i][j] == 0 && trackingButtons[i][j]!=null) {
                                trackingButtons[i][j].setEnabled(true);
                                }
                                primaryButtons[i][j].setEnabled(true);
                            }
                        }
                        
                    }
                    disconnect = true;
                } else {
                    buttonConnect.setText("Connect");
                    for(int i=0;i<10;i++){
                        for(int j=0;j<10;j++){
                            if(trackingButtons[i][j]!=null){
                            trackingButtons[i][j].setEnabled(false);
                            }
                            primaryButtons[i][j].setEnabled(false);
                        }
                    }
                    disconnect = false;
                }
                
            }
        });

        JButton buttonPlay = new JButton("Play");
        pane.add(buttonPlay);
        buttonPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //check value of start and do accordingly
                boolean start = false;
                try {
                    //this player may or may not become attacker
                    service.setAttackerId(player.getPlayerId());
                    start = service.playGame(player.getPlayerId());
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
                
                JLabel label = new JLabel("Tracking Grid");
                gridPane.add(label);
                gridPane.add(createTrackingGrid());
                
                if( start == false ) {
                    //if its not this player's turn, disable tracking grid and start waiting for server response thread
                 // result is miss, so hide the tracking grid so that this player can't play next
                    for(int i=0;i<10;i++){
                        for(int j=0;j<10;j++){
                            trackingButtons[i][j].setEnabled(false);
                        }
                    }
                    resultsLabel.setText("You wait for your turn to attack!");
                    new BattleshipClientPlayThread(player,service,resultsLabel,client).start();
                }else {
                    resultsLabel.setText("Your turn to attack!");
                }

                
                
            }
        });
        return pane;
    }

    /**
     * This method displays the grid
     */
    public void showTrackingGrid() {
        resultsLabel.setText("Your turn to attack!");
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(marker[i][j] == 0) {
                trackingButtons[i][j].setEnabled(true);
                }
            }
        }
        
    }
    
    /**
     * This method create more buttons
     * @return pane This method returns the pane
     */
    public Component createMoreButtons() {
        JPanel pane = new JPanel();

        pane.setBorder(BorderFactory.createLoweredBevelBorder());

        pane.setLayout(new GridLayout(2, 2));
        JButton buttonH = new JButton("Horizontal");
        pane.add(buttonH);
        buttonH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dir = 0;
            }
        });

        JButton buttonV = new JButton("Vertical");
        pane.add(buttonV);
        buttonV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dir = 1;
            }
        });

        return pane;
    }

    /**
     * This method creates the buttons for the ship
     * @return pane created pane
     */
    public Component createShipButtons() {
        JPanel pane = new JPanel();

        pane.setBorder(BorderFactory.createLoweredBevelBorder());

        pane.setLayout(new GridLayout(4, 4));
        Button dest = new Button("2-Destroyer");
        pane.add(dest);
        dest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currShipLabel = "D";
                shipId = 3;
                currShipCount = 1;
                currShipLen = 2;
                arranging = true;
                dest.setEnabled(false);
            }
        });
        Button cru = new Button("3-Cruiser");
        pane.add(cru);
        cru.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currShipLabel = "c";
                shipId = 2;
                currShipCount = 1;
                currShipLen = 3;
                arranging = true;
                cru.setEnabled(false);
            }
        });
        Button b = new Button("4-Battleship");
        pane.add(b);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currShipLabel = "B";
                shipId = 0;
                currShipCount = 1;
                currShipLen = 4;
                arranging = true;
                b.setEnabled(false);
            }
        });
        JButton button = new JButton("5-Carrier");
        pane.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currShipLabel = "C";
                shipId = 1;
                currShipCount = 1;
                currShipLen = 5;
                arranging = true;
                button.setEnabled(false);
            }
        });
        return pane;
    }
    /**
     * This method creates the components
     * @return pane returns the created pane
     */
    public Component createComponents() {
        JPanel pane1 = new JPanel();
        pane1.setBorder(BorderFactory.createLoweredBevelBorder());

        pane1.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        pane1.add(createExtraButtons());
        pane1.add(createButtons());
        pane1.add(createMoreButtons());
        pane1.add(createShipButtons());
        return pane1;
    }

    /**
     * This is the method
     * @param args command line arguments(ignored) 
     */
    public static void main(String[] args) {
        String lookAndFeel;

        lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
        if (args.length == 1) {
            if (args[0].equals("motif"))
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            if (args[0].equals("metal"))
                lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
            else if (args[0].equals("system"))
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        }
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
        }

        // Create the top-level container and add contents to it.
        JFrame frame = new JFrame("SwingApplication");
        client = new BattleshipSwing();
        Component contents = client.createComponents();
        frame.getContentPane().add(contents);

        // Finish setting up the frame, and show it.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
}
