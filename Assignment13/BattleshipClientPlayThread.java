package Assignment13;

import javax.swing.*;
import java.rmi.RemoteException;

public class BattleshipClientPlayThread extends Thread {

    BattleshipPlayer player = null;
    BattleshipRemote service = null;
    JLabel resultsLabel = null;
    BattleshipSwing client = null;
    
    BattleshipClientPlayThread(BattleshipPlayer player,
                               BattleshipRemote service, JLabel resultsLabel, BattleshipSwing client) {
        this.player = player;
        this.service = service;
        this.resultsLabel = resultsLabel;
        this.client = client;
    }

    public void run() {
        int countOfMeAsOppo = 1;
        int  result = 1;
        do {
            
            
            try {
                result = service.reportStatusOfAttackOnMe(player.getPlayerId(),
                        countOfMeAsOppo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            countOfMeAsOppo++;
            switch (result) {

            case BattleshipGame.MISS:

                resultsLabel.setText("Attacker missed!");
                break;
            case BattleshipGame.HIT:

                resultsLabel.setText("Attacker has hit your ship.");
                break;
            case BattleshipGame.SUNK:

                resultsLabel.setText("Attacker has sunk your ship!");
                try {
                    if (service.isAllShipsSunk(player.getPlayerId())) {
                        result = BattleshipGame.OVER;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;

            }
            
        } while (result != BattleshipGame.MISS && result != BattleshipGame.OVER);

        // check if all the ships are sunk, then game over is displayed
        if (result == BattleshipGame.OVER) {

            try {
                resultsLabel.setText(service.getWinnerName()
                        + " won! Game Over!!");
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
        
        else  if(result == BattleshipGame.MISS ) {
            //show this player the tracking grid again
            client.showTrackingGrid();
        }
    }
    
    

}
