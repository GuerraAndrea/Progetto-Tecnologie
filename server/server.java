package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class server {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

        try {

            // ----------------CONNESSIONI CON I CLIENT E LETTURA MESSAGGI----------------//
            ServerSocket s1 = new ServerSocket(PORT);
            System.out.println("Started: " + s1);
            try {
                Socket socket1 = s1.accept();
                System.out.println("Connection accepted: " + socket1);
                BufferedReader messGiocatore1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } finally {
                System.out.println("closing...");
                socket1.close();
            }

            ServerSocket s2 = new ServerSocket(PORT);
            System.out.println("Started: " + s2);
            try {
                Socket socket2 = s2.accept();
                System.out.println("Connection accepted: " + socket2);
                BufferedReader messGiocatore2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } finally {
                System.out.println("closing...");
                socket2.close();
            }

            // -----------------------BARCHE E CONTROLLI---------------------------------//

            String barcheGiocatore1 = messGiocatore1.split(";"); // --> A2,A5;...
            String[] barcheG1posizioni = barcheGiocatore1.split(",");

            String barcheGiocatore2 = messGiocatore2.split(";");
            String[] barcheG2posizioni = messGiocatore2.split(";");

            String barche1 = ""; // stringe con tutte le posizioni corrette
            String barche2 = "";

            // controllo e sistemo le due tabelle con le barche
            // controlli per la prima tabella
            for (int i; i < 10; i++) {

                // -------------------PRIMA BARCA-------------------//
                // tutti i controlli sulle barche del primo giocatore
                if (barcheG1posizioni[i].startsWith(l[i]) && barcheG1posizioni[i + 1].startsWith(l[i])) { // controllo
                                                                                                          // iniziano
                                                                                                          // con la
                                                                                                          // stessa
                                                                                                          // lettera
                    String num1 = barcheG1posizioni[i].substring(exampleString.length()); // controllo il numero
                                                                                          // iniziale
                    String num2 = barcheG1posizioni[i + 1].substring(exampleString.length()); // controllo il numero
                                                                                              // finale

                    int iniznum = Integer.parseInt(num1);
                    int finnum = Integer.parseInt(num2);

                    for (int f = Integer.parseInt(s); f < finnum; f++) { // inserisco tutti i parametri che sono nel
                                                                         // mezzo e aggiungo alla stringa finale

                        barche1 += barcheG1posizioni[i].substring(0) + f + ","; // A2,A5,B4,D4
                    }

                } else if (barcheG1posizioni[i].startsWith(l[i]) != barcheG1posizioni[i + 1].startsWith(l[i])) {
                    String lett1 = barcheG1posizioni[i].substring(0); // controllo il lettera iniziale
                    String lett2 = barcheG1posizioni[i + 1].substring(0); // controllo il lettera finale

                    int inizLett = charCodeAt(lett1);
                    int finLett = charCodeAt(lett1);

                    for (int h = charCodeAt(lett1); h < finLett; h++) {

                        barche1 += String(h) + barcheG1posizioni[i + 1].substring(0) + ",";
                    }

                }

                // ---------------------SECONDA BARCA--------------------------//
                // tutti i controlli sulle barche del secondo giocatore
                if (barcheG2posizioni[i].startsWith(l[i]) && barcheG2posizioni[i + 1].startsWith(l[i])) { // controllo
                                                                                                          // iniziano
                                                                                                          // con la
                                                                                                          // stessa
                                                                                                          // lettera
                    String num1 = barcheG2posizioni[i].substring(exampleString.length()); // controllo il numero
                                                                                          // iniziale
                    String num2 = barcheG2posizioni[i + 1].substring(exampleString.length()); // controllo il numero
                                                                                              // finale

                    int iniznum = Integer.parseInt(num1);
                    int finnum = Integer.parseInt(num2);

                    for (int f = Integer.parseInt(s); f < finnum; f++) { // inserisco tutti i parametri che sono nel
                                                                         // mezzo e aggiungo alla stringa finale

                        barche1 += barcheG2posizioni[i].substring(0) + f + ","; // A2,A5,B4,D4
                    }

                } else if (barcheG2posizioni[i].startsWith(l[i]) != barcheG2posizioni[i + 1].startsWith(l[i])) {
                    String lett1 = barcheG2posizioni[i].substring(0); // controllo il lettera iniziale
                    String lett2 = barcheG2posizioni[i + 1].substring(0); // controllo il lettera finale

                    int inizLett = charCodeAt(lett1);
                    int finLett = charCodeAt(lett1);

                    for (int h = charCodeAt(lett1); h < finLett; h++) {

                        barche1 += String(h) + barcheG2posizioni[i + 1].substring(0) + ",";
                    }

                }

            }

            // -------------------------ATTACCO E
            // CONTROLLI------------------------------------//
            // instauro la connessione e leggo i messaggi degli attacchi
            // while finchè non ha finito le barche
            // attacco del giocatore 1 per primo
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

                // attacco del giocatore 2
                // RIFARE
                byte[] bufferGiocatore2attacco = new byte[1500];
                DatagramPacket packetGiocatore2attacco = new DatagramPacket(bufferGiocatore2attacco,
                        bufferGiocatore2attacco.length);
                packetGiocatore2.setAddress(InetAddress.getByName(/* IP 2 */));
                socketGiocatore2.receive(packetGiocatore2);
                String messGiocatore2attacco = new String(packetGiocatore2.getData());

                // metto i messaggi in due variabili per il controllo

                String attaccoG2 = messGiocatore2attacco.split(";");
                attaccoG2 = attaccoG2 + ",";
                int rispostaattacco2 = 0;

                for (Integer i = 0; i < 50; i++) {
                    if (barche2.contains(attaccoG2)) {
                        barche2 = barche2.replaceAll(attaccoG2, "");
                        rispostaattacco2 = 1;
                        // mandare la risposta al client

                        ServerSocket satt1 = new ServerSocket(PORT);
                        System.out.println("Started: " + satt1);
                        try {
                            Socket socketatt1 = satt1.accept();
                            try {
                                System.out.println("Connection accepted: " + socketatt1);
                                PrintWriter out = new PrintWriter(
                                        new BufferedWriter(
                                                new OutputStreamWriter(socketatt1.getOutputStream(rispostaattacco1))));
                            } finally {
                                System.out.println("closing...");
                                socketatt1.close();
                            }
                        } finally {
                            satt1.close();
                        }

                        // altrimenti
                    } else {
                        rispostaattacco2 = 0;
                        // mandare risposta al client
                        ServerSocket satt2 = new ServerSocket(PORT);
                        System.out.println("Started: " + satt2);
                        try {
                            Socket socketatt2 = satt2.accept();
                            try {
                                System.out.println("Connection accepted: " + socketatt2);
                                PrintWriter out = new PrintWriter(
                                        new BufferedWriter(
                                                new OutputStreamWriter(socketatt2.getOutputStream(rispostaattacco2))));
                            } finally {
                                System.out.println("closing...");
                                socketatt1.close();
                            }
                        } finally {
                            satt2.close();
                        }

                    }
                    // aggiungere controllo affondata
                }
            }

            // -------------------FINALE E RISULTATI-----------------------//

            // indirizzi e porte giocatori

            if (barche1 == null) {
                // invio risposta al gicoatore 1

                ServerSocket sv = new ServerSocket(PORT);
                System.out.println("Started: " + sv);
                try {
                    Socket socketv = sv.accept();
                    try {
                        System.out.println("Connection accepted: " + socketv);
                        PrintWriter out = new PrintWriter(
                                new BufferedWriter(new OutputStreamWriter(socketv.getOutputStream("Hai vinto"))));
                    } finally {
                        System.out.println("closing...");
                        socketv.close();
                    }
                } finally {
                    sv.close();
                }

            } else {

                ServerSocket ss = new ServerSocket(PORT);
                System.out.println("Started: " + ss);
                try {
                    Socket sockets = ss.accept();
                    try {
                        System.out.println("Connection accepted: " + socket3);
                        PrintWriter out = new PrintWriter(
                                new BufferedWriter(new OutputStreamWriter(sockets.getOutputStream("Hai vinto"))));
                    } finally {
                        System.out.println("closing...");
                        sockets.close();
                    }
                } finally {
                    ss.close();
                }
            }

        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }
}