package server;

/**
 * server
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class server {
    public static void main(String[] args) {
        try {
            DatagramSocket socketGiocatore1 = new DatagramSocket(/* porta del primo gicatore */);
            // insaturo la connessione con il primo giocatore
            byte[] bufferGiocatore1 = new byte[1500];
            DatagramPacket packetGiocatore1 = new DatagramPacket(bufferGiocatore1, bufferGiocatore1.length);
            packetGiocatore1.setAddress(InetAddress.getByName(/* inserire indirizzo ip primo giocatore */));
            socketGiocatore1.receive(packetFirstPlayer);
            String messGiocatore1 = new String(packetFirstPlayer.getData());

            // instauro la connessione con il secondo giocatore
            DatagramSocket socketGiocatore2 = new DatagramSocket(/* porta del secondo giocatore collegato */);

            byte[] bufferGiocatore2 = new byte[1500];
            DatagramPacket packetGiocatore2 = new DatagramPacket(bufferGiocatore2, bufferGiocatore2.length);
            packetGiocatore2.setAddress(InetAddress.getByName(/* indirizzo ip del secondo giocatore */));
            socketGiocatore2.receive(packetSecondPlayer);
            String messGiocatore2 = new String(packetGiocatore2.getData());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}