/*
 * BattleshipView.java
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
import java.util.Observable;
import java.util.Observer;

/**
 * This class represents the view in the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipView implements Observer {

    BattleshipController controller;
    BattleshipModel model;
    BattleshipGame game;

    public BattleshipView(BattleshipController controller,
            BattleshipModel model, PrintWriter[] clientOutputStreams,
            InputStream[] streams) {
        this.model = model;
        this.controller = controller;
        model.addObserver(this);
        game = new BattleshipGame(controller, model, clientOutputStreams,
                streams);

    }

    public BattleshipView(BattleshipController controller,
            BattleshipModel model) {
        this.model = model;
        this.controller = controller;
        model.addObserver(this);
        game = new BattleshipGame(controller, model);

    }

    /**
     * This method is used to receive update from Model.
     * 
     */
    public void update(Observable observable, Object obj) {
        game.update(observable, obj);

    }

    /**
     * This method is used to create view of the game.
     * 
     */
    public void createView() {
        game.createView();
    }

}
