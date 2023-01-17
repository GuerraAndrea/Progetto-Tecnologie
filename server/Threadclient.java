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
                    String num1 = TutteLePosizini.get(i).substring(exampleString.length()); // controllo il numero
                                                                                          // iniziale
                    String num2 = TutteLePosizini.get(i+1).substring(exampleString.length()); // controllo il numero
                                                                                              // finale

                    int iniznum = Integer.parseInt(num1);
                    int finnum = Integer.parseInt(num2);

                    for (int f = Integer.parseInt(s); f < finnum; f++) { // inserisco tutti i parametri che sono nel
                                                                         // mezzo e aggiungo alla stringa finale

                        barche1 += TutteLePosizini.get(i).substring(0) + f + ","; // A2,A5,B4,D4
                    }

                } else if (TutteLePosizini.get(i).startsWith(l[i]) != TutteLePosizini.get(i+1).startsWith(l[i])) {
                    String lett1 = TutteLePosizini.get(i).substring(0); // controllo il lettera iniziale
                    String lett2 = TutteLePosizini.get(i+1).substring(0); // controllo il lettera finale

                    int inizLett = charCodeAt(lett1);
                    int finLett = charCodeAt(lett1);

                    for (int h = charCodeAt(lett1); h < finLett; h++) {

                        barche1 += String(h) + barcheG1posizioni[i + 1].substring(0) + ",";
                    }

                }

                    

                    
                    if(count==2){
                             String barca1;
                             String barca2;
                            barca1=condivisa.getInstance().sockets.get(1).barche;

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