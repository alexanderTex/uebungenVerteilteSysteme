import java.io.*;
import java.net.*;


public class XmlTcpServer {
    private static final int PORT = 5555;

    public static void main(String[] args) {
        try {
            ServerSocket lSocket = new ServerSocket(PORT);
            while(true) {
                Socket cSocket = lSocket.accept();
                Connection conn = new Connection(cSocket);
            }
        } catch(IOException e) {
            System.out.println("Listen:" + e.getMessage());
        }
    }
}
