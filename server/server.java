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



            //-----------------------BARCHE E CONTROLLI---------------------------------//

            String barcheGiocatore1 = messGiocatore1.split(";");   //--> A2,A5;...
            String[] barcheG1posizioni = barcheGiocatore1.split(",");

            String barcheGiocatore2 = messGiocatore2.split(";");
            String[] barcheG2posizioni = messGiocatore2.split(";");

            String barche1 = "";  //stringe con tutte le posizioni corrette
            String barche2 = "";

            //controllo e sistemo le due tabelle con le barche
            //controlli per la prima tabella
            for(int i;i<10;i++){

                //-------------------PRIMA BARCA-------------------//
                //tutti i controlli sulle barche del primo giocatore
                if(barcheG1posizioni[i].startsWith(l[i]) && barcheG1posizioni[i+1].startsWith(l[i])){   //controllo iniziano con la stessa lettera
                    String num1 = barcheG1posizioni[i].substring(exampleString.length());   //controllo il numero iniziale
                    String num2 = barcheG1posizioni[i+1].substring(exampleString.length());   //controllo il numero finale

                    int iniznum = Integer.parseInt(num1);
                    int finnum = Integer.parseInt(num2);

                    for(int f=Integer.parseInt(s); f<finnum; f++){       //inserisco tutti i parametri che sono nel mezzo e aggiungo alla stringa finale

                         barche1 += barcheG1posizioni[i].substring(0) + f + ",";  //A2,A5,B4,D4
                    }
                    
                }
                else if(barcheG1posizioni[i].startsWith(l[i]) != barcheG1posizioni[i+1].startsWith(l[i])){
                    String lett1 = barcheG1posizioni[i].substring(0);   //controllo il lettera iniziale
                    String lett2 = barcheG1posizioni[i+1].substring(0);   //controllo il lettera finale

                    int inizLett = charCodeAt(lett1);
                    int finLett = charCodeAt(lett1);

                    for(int h= charCodeAt(lett1); h<finLett;h++){

                        barche1 += String(h) + barcheG1posizioni[i+1].substring(0) + ",";
                    }

                }




                //---------------------SECONDA BARCA--------------------------//
                //tutti i controlli sulle barche del secondo giocatore
                if(barcheG2posizioni[i].startsWith(l[i]) && barcheG2posizioni[i+1].startsWith(l[i])){   //controllo iniziano con la stessa lettera
                    String num1 = barcheG2posizioni[i].substring(exampleString.length());   //controllo il numero iniziale
                    String num2 = barcheG2posizioni[i+1].substring(exampleString.length());   //controllo il numero finale

                    int iniznum = Integer.parseInt(num1);
                    int finnum = Integer.parseInt(num2);

                    for(int f=Integer.parseInt(s); f<finnum; f++){       //inserisco tutti i parametri che sono nel mezzo e aggiungo alla stringa finale

                         barche1 += barcheG2posizioni[i].substring(0) + f + ",";  //A2,A5,B4,D4
                    }
                    
                }
                else if(barcheG2posizioni[i].startsWith(l[i]) != barcheG2posizioni[i+1].startsWith(l[i])){
                    String lett1 = barcheG2posizioni[i].substring(0);   //controllo il lettera iniziale
                    String lett2 = barcheG2posizioni[i+1].substring(0);   //controllo il lettera finale

                    int inizLett = charCodeAt(lett1);
                    int finLett = charCodeAt(lett1);

                    for(int h= charCodeAt(lett1); h<finLett;h++){

                        barche1 += String(h) + barcheG2posizioni[i+1].substring(0) + ",";
                    }

                }


            }
            



            //-------------------------ATTACCO E CONTROLLI------------------------------------//
            //instauro la connessione e leggo i messaggi degli attacchi 
            //while finchè non ha finito le barche
            //attacco del giocatore 1 per primo
            while(barcheGiocatore1 != null){

            //da rifare
            byte[] bufferGiocatore1attacco=new byte[1500];
            DatagramPacket packetGiocatore1attacco=new DatagramPacket(bufferGiocatore1attacco,bufferGiocatore1attacco.length);
            packetGiocatore1.setAddress(InetAddress.getByName(/*IP 1*/));
            socketGiocatore1.receive(packetGiocatore1);
            String messGiocatore1attacco=new String(packetGiocatore1.getData());
            //da rifare

            String attaccoG1 = messGiocatore1attacco.split(";");
            int rispostaattacco1=0;  //0=acqua 1=colpito 2=affondato
            for(Integer i=0; i<50; i++)
            {
                if(barche1.contains(attaccoG1))         //se la posizione è contenuta nella stringa delle barche
                {
                  rispostaattacco1=1;
                  //se colpita eliminare barca per poi controllare cosa manca
                }
                else{
                   rispostaattacco1=0;
                }
                //aggiungere controllo affondata
            }

            //attacco del giocatore 2
            //RIFARE
            byte[] bufferGiocatore2attacco=new byte[1500];
            DatagramPacket packetGiocatore2attacco=new DatagramPacket(bufferGiocatore2attacco,bufferGiocatore2attacco.length); 
            packetGiocatore2.setAddress(InetAddress.getByName(/* IP 2*/));
            socketGiocatore2.receive(packetGiocatore2);
            String messGiocatore2attacco=new String(packetGiocatore2.getData());

            //metto i messaggi in due variabili per il controllo
            
            String attaccoG2 = messGiocatore2attacco.split(";");
            int rispostaattacco2=0;
            

            for(Integer i=0; i<50; i++)
            {
                if(barche2.contains(attaccoG2))        
                {
                  rispostaattacco2=1;
                }
                else{
                   rispostaattacco2=0;
                }
                //aggiungere controllo affondata
            }
        }

            




            // ----------------------------RISPOSTA FINALE------------------------------------------//
            
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