import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class mySocket {
    static long idCounter=0;

    long id;
    Socket socket;
    PrintWriter out;
    String barche;
    
    public mySocket(Socket socket) throws IOException
    {
        this.socket=socket;
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj==null) return false;

        if(! (obj instanceof mySocket)) return false;

        mySocket tmp =(mySocket)obj;
        return tmp.id == id;
    }

    public void close(){
        try{
            socket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
