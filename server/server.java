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

            String[] barcheGiocatore1 = messGiocatore1.split(";");
            String[] barcheGiocatore2 = messGiocatore2.split(";");

            //controllo e sistemo le due tabelle con le barche

  


            Integer attacco1=0;
            Integer attacco2=0;


            

            




            // ----------------------------------------------------------------------//
            
                // Risposta
                //indirizzi e porte giocatori
            InetAddress responseAddressGiocatore1=packetGiocatore1.getAddress(); //Giocatore 1
            int responsePortGiocatore1=packetGiocatore1.getPort(); //Giocatore 1
            InetAddress responseAddressGiocatore2=packetGiocatore2.getAddress(); //Giocatore 2
            int responsePortGiocatore2=packetGiocatore2.getPort(); //Giocatore 2
            
            //invio risposta al gicoatore 1
            bufferGiocatore1=rispostaGiocatore1.getBytes();

            packetGiocatore1=new DatagramPacket(bufferGiocatore1,bufferGiocatore1.length);
            packetGiocatore1.setAddress(responseAddressGiocatore1);
            packetGiocatore1.setPort(responsePortGiocatore1);
            socketGiocatore1.send(packetGiocatore1); //invio della risposta
            socketGiocatore1.close(); //chiusura socket

            //invio risposta al giocatore 2
            bufferGiocatore2=rispostaGiocatore2.getBytes();

            packetGiocatore2=new DatagramPacket(bufferGiocatore2,bufferGiocatore2.length);
            packetGiocatore2.setAddress(responseAddressGiocatore2);
            packetGiocatore2.setPort(responsePortGiocatore2);
            socketGiocatore2.send(packetGiocatore2); //invio della risposta
            socketGiocatore2.close(); //chiusura socket

    
      }
catch(Exception e){
    e.printStackTrace();
}
}
}