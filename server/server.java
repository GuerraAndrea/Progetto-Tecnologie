package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class server{
    public static void main(String[] args){
        try{
            DatagramSocket socketGiocatore1=new DatagramSocket(/*porta 1 */); 
            //apro la socket con il primo giocatore
            
            byte[] bufferGiocatore1=new byte[1500];
            DatagramPacket packetGiocatore1=new DatagramPacket(bufferGiocatore1,bufferGiocatore1.length);
            packetGiocatore1.setAddress(InetAddress.getByName(/*IP 1*/));
            socketGiocatore1.receive(packetGiocatore1);
            String messGiocatore1=new String(packetGiocatore1.getData());
            
            
            DatagramSocket socketGiocatore2=new DatagramSocket(/*porta  2 */); 
            //apero la secket con il secondo giocatore
            
            byte[] bufferGiocatore2=new byte[1500];
            DatagramPacket packetGiocatore2=new DatagramPacket(bufferGiocatore2,bufferGiocatore2.length); 
            packetGiocatore2.setAddress(InetAddress.getByName(/* IP 2*/));
            socketGiocatore2.receive(packetGiocatore2);
            String messGiocatore2=new String(packetGiocatore2.getData());

    
      }
catch(Exception e){
    e.printStackTrace();
}
}
}