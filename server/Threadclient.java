import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Threadclient extends Thread {
    mySocket _socket = null;
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
            while (condivisa.getInstance().sockets.get(1).barche != null || condivisa.getInstance().sockets.get(2).barche != null) {

            String attacco1="";
            boolean loop2=true;
            while (loop2) {
            try {
                messaggio = in.readLine();
                if(messaggio != null){ 
                    System.out.println(_socket.id+" ha ricevuto : " + attacco1);
                    if (messaggio.equals("END")){ 
                        _socket.close();
                        loop2=false;
                        break;
                    }

                String[] attaccoGiocatore = attacco1.split(";");
                attaccoGiocatore[0] = attaccoGiocatore[0] + ",";
                int rispostaattacco1 = 0; // 0=acqua 1=colpito 2=affondato
                for (Integer i = 0; i < 50; i++) {
                    if (condivisa.getInstance().sockets.get(1).barche.contains(attaccoGiocatore[0])) // se la posizione è contenuta nella stringa delle barche
                    {
                        condivisa.getInstance().sockets.get(1).barche = condivisa.getInstance().sockets.get(1).barche.replaceAll(attaccoGiocatore[0], "");
                        rispostaattacco1 = 1;
                        // mandare la risposta al client
                        condivisa.getInstance().sockets.get(1).out.println(1);
                    } else {
                        rispostaattacco1 = 0;
                        condivisa.getInstance().sockets.get(1).out.println(0);
                    }
                    // aggiungere controllo affondata
                }

               //secondo giocatore
            String attacco2="";
            boolean loop3=true;
            while (loop3) {
            try {
                messaggio = in.readLine();
                if(messaggio != null){ 
                    System.out.println(_socket.id+" ha ricevuto : " + attacco2);
                    if (messaggio.equals("END")){ 
                        _socket.close();
                        loop3=false;
                        break;
                    }

                String[] attaccoGiocatore2 = attacco1.split(";");
                attaccoGiocatore2[0] = attaccoGiocatore[0] + ",";
                int rispostaattacco2 = 0; // 0=acqua 1=colpito 2=affondato
                for (Integer i = 0; i < 50; i++) {
                    if (condivisa.getInstance().sockets.get(2).barche.contains(attaccoGiocatore2[0])) // se la posizione è contenuta nella stringa delle barche
                    {
                        condivisa.getInstance().sockets.get(2).barche = condivisa.getInstance().sockets.get(2).barche.replaceAll(attaccoGiocatore2[0], "");
                        rispostaattacco2 = 1;
                        // mandare la risposta al client
                        condivisa.getInstance().sockets.get(2).out.println(1);
                    } else {
                        rispostaattacco2 = 0;
                        condivisa.getInstance().sockets.get(2).out.println(0);
                    }
                    // aggiungere controllo affondata
                }





            //----------------------FINALE-----------------------//
                             String barca1;
                             String barca2;
                             barca1=condivisa.getInstance().sockets.get(1).barche;
                             barca2=condivisa.getInstance().sockets.get(2).barche;

                            if(barca1==null){
                                condivisa.getInstance().sockets.get(1).out.println("hai vinto");
                                condivisa.getInstance().sockets.get(2).out.println("hai perso");
                            }
                            else if(barca2 ==null){
                                condivisa.getInstance().sockets.get(1).out.println();  //vince 2 perde 1
                                condivisa.getInstance().sockets.get(2).out.println();
                            }
                    }
                
            } catch (IOException e) {
               //connessione chiusa
               break;   //e vado a rimuovere la socket
            }finally{
            inst.removeSocket(_socket);
        }

                    

    }
}

            
            
        
