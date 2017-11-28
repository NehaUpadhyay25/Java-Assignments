import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class BattleshipUDPServer extends Thread {
    static int port = 6060;
    InetAddress addr;
    DatagramSocket sock;
    DatagramPacket packet;
    byte[] buf = new byte[512];
    int count = 0;

    public BattleshipUDPServer() {
        try {
            sock = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... strings) {

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("-port")) {
                port = new Integer(strings[++i]);
            }
        }
        new BattleshipUDPServer().start();
    }

    public void run() {
            String req = "";
            try {

                do {
                    buf = new byte[1024];
                    // receive first packet for sending back rules
                    packet = new DatagramPacket(buf, buf.length);
                    // receive first packet for sending back rules
                    sock.receive(packet);
                    req = new String(packet.getData());
                    while (!req.contains(BattleshipGame.REQ_INIT)) {
                        buf = new byte[1024];
                        // receive first packet for sending back rules
                        packet = new DatagramPacket(buf, buf.length);
                        // receive first packet for sending back rules
                        sock.receive(packet);
                        req = new String(packet.getData());
                    }
                    port = packet.getPort();
                    addr = packet.getAddress();
                    String rules = "Please understand the following rules of the game:-\n"
                            + "1. First step in the game is to arrange your fleet on your grid.\n"
                            + "2. After your grid is initialized, you should wait."
                            + "The game begins automatically once the second player has also initialized his grid.\n"
                            + "3. You should input in one line and only when you are asked to enter. Otherwise, wait for your turn.\n"
                            + "YOUR NAME IS Player" + (count == 0 ? "A" : "B")
                            + ".";
                    count++;
                    buf = rules.getBytes();
                    packet = new DatagramPacket(buf, buf.length, addr, port);
                    sock.send(packet);
                } while (count == 1);

                if (count == 2) {
                    // start a game in a separate controller thread between
                    // these two clients
                    BattleshipModel model = new BattleshipModel();
                    BattleshipController controller = new BattleshipController(
                            model, sock);
                    controller.start();
                    count = 0;
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
      
    }
}
