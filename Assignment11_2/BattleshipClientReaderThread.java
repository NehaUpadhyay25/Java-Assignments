/*
 * BattleshipClientReaderThread.java
 * 
 * @version: 1.0
 * 
 * author : Kritka Sahni
 * author : Neha Upadhyay 
 * 
 * Revisions:
 *     Initial revision
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class starts and maintains the server-i/o on the client side the Battleship game.
 * 
 * @author Kritka Sahni
 * @author Neha Upadhyay
 */
public class BattleshipClientReaderThread extends Thread {

    // read from server
    BufferedReader reader;

    PrintWriter writer;

    public BattleshipClientReaderThread(BufferedReader reader,
            PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * This method is used to do i/o with server.
     * 
     */
    public void run() {
        String line = null;
        try {
            // reader.
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                while ((line = reader.readLine()) != null) {

                    System.out.println(line);

                    if (line.contains("Enter now:-")) {
                        break;
                    }
                }

                // read from client and output to server
                BattleshipClientWriterThread writerThread = new BattleshipClientWriterThread(
                        writer);
                writerThread.start();
                try {
                    writerThread.join();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.out.println("Error in reading from server");
            e.printStackTrace();
        }

    }
}
