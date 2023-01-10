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
            String lettere = "A;B;C;D;E;F;G;H;I;";
            String[] l = lettere.split(";");
            String numeri = "1;2;3;4;5;6;7;8;";

            //controllo e sistemo le due tabelle con le barche
            for(int i;i<10;i++)
            {
                if(barcheGiocatore1[0].startsWith(l[i]) && barcheGiocatore1[1].startsWith(l[i]))
                {
                    //inserisco i parametri di mezzo
                }
            }
            String barca11 = barcheGiocatore1[0] + barcheGiocatore1[1];
            String barca12 = barcheGiocatore1[2] + barcheGiocatore1[3];
            String barca13 = barcheGiocatore1[4] + barcheGiocatore1[5];

            String barca21 = barcheGiocatore2[0] + barcheGiocatore2[1];
            String barca22 = barcheGiocatore2[2] + barcheGiocatore2[3];
            String barca23 = barcheGiocatore2[4] + barcheGiocatore2[5];






            //-------------------------------------------------------------//
            //instauro la connessione e leggo i messaggi degli attacchi 
            //while finchè non ha finito le barche
            //attacco del giocatore 1 per primo
            byte[] bufferGiocatore1attacco=new byte[1500];
            DatagramPacket packetGiocatore1attacco=new DatagramPacket(bufferGiocatore1attacco,bufferGiocatore1attacco.length);
            packetGiocatore1.setAddress(InetAddress.getByName(/*IP 1*/));
            socketGiocatore1.receive(packetGiocatore1);
            String messGiocatore1attacco=new String(packetGiocatore1.getData());

            String[] attaccoG1 = messGiocatore1attacco.split(";");
            int rispostaattacco=0;  //0=acqua 1=colpito 2=affondato
            for(Integer i=0; i<50; i++)
            {
                if(barcheGiocatore2[i] == attaccoG1[0])        
                {
                  rispostaattacco=1;
                }
                else{
                   rispostaattacco=0;
                }
                //aggiungere controllo affondata
            }

            //attacco del giocatore 2
            byte[] bufferGiocatore2attacco=new byte[1500];
            DatagramPacket packetGiocatore2attacco=new DatagramPacket(bufferGiocatore2attacco,bufferGiocatore2attacco.length); 
            packetGiocatore2.setAddress(InetAddress.getByName(/* IP 2*/));
            socketGiocatore2.receive(packetGiocatore2);
            String messGiocatore2attacco=new String(packetGiocatore2.getData());

            //metto i messaggi in due variabili per il controllo
            
            String[] attaccoG2 = messGiocatore2attacco.split(";");
            

            for(Integer i=0; i<50; i++)
            {
                if(barcheGiocatore1[i] == attaccoG2[0])        
                {
                  rispostaattacco=1;
                }
                else{
                   rispostaattacco=0;
                }
                //aggiungere controllo affondata
            }

            




            // ----------------------------------------------------------------------//
            
                // Risposta finale
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