import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Threadclient extends Thread{
    mySocket _socket=null;
    BufferedReader in;
    
    public Threadclient(mySocket socket) throws IOException
    {
        _socket=socket;
        in =new BufferedReader(new InputStreamReader(_socket.socket.getInputStream()));
    }

    @Override
    public void run() {
        
        condivisa inst=condivisa.getInstance();
        String messaggio="";
        boolean loop=true;
        int count=0;
        while (loop) {
            try {
                messaggio = in.readLine();
                if(messaggio != null){ 
                    System.out.println(_socket.id+" ha ricevuto : " + messaggio);
                    if (messaggio.equals("END")){ 
                        _socket.close();
                        loop=false;
                        break;
                    }
                   

                    // -----------------------BARCHE E CONTROLLI---------------------------------//

            String[] barcheGiocatore = messaggio.split(";"); // --> A2,A5;...
           // String[] elemento1 = barcheGiocatore[0].split(",");
           List<String> TutteLePosizini = new ArrayList<String>(); 
           for (String barca : barcheGiocatore) {
                String[] posBarca = barca.split(",");
                for (String posizione : posBarca) {
                    TutteLePosizini.add(posizione);
                }   
            }

            String barche1 = ""; // stringe con tutte le posizioni corrette
            String barche2 = "";
            String lettere = "A;B;C;D;E;F;H;I;";
            String[] l = lettere.split(";");


            // controllo e sistemo le due tabelle con le barche
            // controlli per la prima tabella
            for (int i; i < 10; i++) {

                // -------------------PRIMA BARCA-------------------//
                // tutti i controlli sulle barche del primo giocatore
                if (TutteLePosizini.get(i).startsWith(l[i]) && TutteLePosizini.get(i+1).startsWith(l[i])) { // controllo
                                                                                                          // iniziano
                                                                                                          // con la
                                                                                                          // stessa
                                                                                                          // lettera
                    String num1 = TutteLePosizini.get(i).substring(TutteLePosizini.get(i).length()); // controllo il numero
                                                                                          // iniziale
                    String num2 = TutteLePosizini.get(i+1).substring(TutteLePosizini.get(i+1).length()); // controllo il numero
                                                                                              // finale

                    int iniznum = Integer.parseInt(num1);
                    int finnum = Integer.parseInt(num2);

                    for (int f = iniznum; f < finnum; f++) { // inserisco tutti i parametri che sono nel
                                                                         // mezzo e aggiungo alla stringa finale

                        barche1 += TutteLePosizini.get(i).substring(0) + f + ","; // A2,A5,B4,D4
                    }

                } else if (TutteLePosizini.get(i).startsWith(l[i]) != TutteLePosizini.get(i+1).startsWith(l[i])) {
                    String lett1 = TutteLePosizini.get(i).substring(0); // controllo il lettera iniziale
                    String lett2 = TutteLePosizini.get(i+1).substring(0); // controllo il lettera finale

                    int inizlett = Integer.parseInt(lett1);
                    int finLett = Integer.parseInt(lett2);

                    for (int h = inizlett; h < finLett; h++) {

                        barche1 += Integer.toString(h) + TutteLePosizini.get(i + 1).substring(0) + ",";
                        _socket.barche=barche1;
                    }

                }      

            }

            //------------------------ATTACCHI--------------------//
            while (barcheGiocatore1 != null) {

                // da rifare
                byte[] bufferGiocatore1attacco = new byte[1500];
                DatagramPacket packetGiocatore1attacco = new DatagramPacket(bufferGiocatore1attacco,
                        bufferGiocatore1attacco.length);
                packetGiocatore1.setAddress(InetAddress.getByName(/* IP 1 */));
                socketGiocatore1.receive(packetGiocatore1);
                String messGiocatore1attacco = new String(packetGiocatore1.getData());
                // da rifare

                String attaccoG1 = messGiocatore1attacco.split(";");
                attaccoG1 = attaccoG1 + ",";
                int rispostaattacco1 = 0; // 0=acqua 1=colpito 2=affondato
                for (Integer i = 0; i < 50; i++) {
                    if (barche1.contains(attaccoG1)) // se la posizione è contenuta nella stringa delle barche
                    {
                        barche1 = barche1.replaceAll(attaccoG1, "");
                        rispostaattacco1 = 1;
                        // mandare la risposta al client
                        PrintWriter out = new PrintWriter(
                                new BufferedWriter(new OutputStreamWriter(ocket.getOutputStream(rispostaattacco1))));
                    } else {
                        rispostaattacco1 = 0;
                        PrintWriter out = new PrintWriter(
                                new BufferedWriter(new OutputStreamWriter(ocket.getOutputStream(rispostaattacco1))));
                    }
                    // aggiungere controllo affondata
                }





            //----------------------FINALE-----------------------//
                             String barca1;
                             String barca2;
                             barca1=condivisa.getInstance().sockets.get(1).barche;
                             barca2=condivisa.getInstance().sockets.get(1).barche;

                            if(barca1==null){
                                condivisa.getInstance().sockets.get(1).out.println("hai vinto");
                                condivisa.getInstance().sockets.get(2).out.println("hai perso");
                            }
                            else if(barca2 ==null){
                                condivisa.getInstance().sockets.get(1).out.println();  //vince 2 perde 1
                                condivisa.getInstance().sockets.get(2).out.println();
                            }
                    }
                }
            } catch (IOException e) {
               //connessione chiusa
               break;   //e vado a rimuovere la socket
            }
        }

        inst.removeSocket(_socket);            

    }
}