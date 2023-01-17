import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class condivisa {
    private static condivisa _instanza = null;
    public static condivisa getInstance(){
        if(_instanza==null)
            _instanza=new condivisa();

        return _instanza;
    }
    private condivisa(){}


    List<mySocket> sockets= new ArrayList<mySocket>();


    public mySocket findSocketById(long id)
    {
        for (mySocket mySocket : sockets) {
            if( mySocket.id == id)
                return mySocket;
        }
        return null;
    }

    public boolean addSocket(mySocket socket) throws IOException
    {
        if(sockets.size()<2)      {
            sockets.add(socket);
            return true;
        }       
        return false;
    }

    public long getId(Socket socket){
        for (mySocket mySocket : sockets) {
            if( mySocket.socket == socket)
            {
                return mySocket.id;
            }
        }
        return -1;
    }

   
    public mySocket findDifferentSocketById(long id)
    {
        for (mySocket mySocket : sockets) {
            if( mySocket.id != id)
                return mySocket;
        }
        return null;
    }


  
    public void removeSocket(Socket socket)
    {
       long id=getId(socket);
       if(id!=-1)
        removeSocket(id);
    }
    public void removeSocket(long id)
    {
        mySocket cancella=null;
        for (mySocket mySocket : sockets) {    //assegno a una variabile la socket da cancellare e la rimuovo
            if( mySocket.id == id)
            {
                cancella = mySocket;
            }
        }
        
        if(cancella!=null)
        {
            removeSocket(cancella);
        }
           
    }
    public void removeSocket(mySocket mysocket)
    {
        try {
            mysocket.socket.close();    //provo a chiudere la socket
        } catch (Exception e) {
        }
        sockets.remove(mysocket);
    }

    

    public long[] getAllIds()
    {
        int size=sockets.size();
        long[] ids= new long[size];
        for (int i=0;i<size;i++)
        {
            ids[i]=sockets.get(i).id;
        }
        return ids;
    }
}
