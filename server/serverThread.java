import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class serverThread extends Thread{
    
    public boolean connected = false;

    public void Connessione() throws IOException   {
        shared istance=shared.getInstance(); 
        ServerSocket serversocket = new ServerSocket(8080); 
        Socket socket;
        while(true){
            try {
                    
                System.out.println("Il server è pronto per connettersi");
                socket = serversocket.accept();

                mySocket mysocket=new mySocket(sock);
                if(istance.addSocket(mysocket)){
                    clientThread clientth=new clientThread(mysocket);
                    clientth.start();
                }

                connected = true;
                serversocket.close();
            }
            catch (SocketTimeoutException ex) {
                serversocket.close();
                connected = false;
            }
        }  
    }
    
 
}

