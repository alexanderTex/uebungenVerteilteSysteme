package Server;

import java.io.*;
import java.net.*;


public class XmlTcpServer {
    private static final int PORT = 5555;

    public static void main(String[] args) throws IOException {
        try(ServerSocket lSocket = new ServerSocket(PORT)) {
            while(true) {
                Socket cSocket = lSocket.accept();
                new Connection(cSocket);
            }
        }
    }
}
