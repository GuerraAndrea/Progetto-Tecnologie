import java.io.IOException;

public class servTcp {
    public static void main(String[] args) throws IOException{
        serverThread server = new serverThread();
        server.connection();;  
    }
}
