import java.io.IOException;

public class server {

    public static void main(String[] args) throws IOException {
        serverThread server1 = new serverThread();
        server1.connection();
    }

}
