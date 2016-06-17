package Server;

import java.io.*;
import java.net.*;

/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 */
public class XmlTcpServer {
    private static final int PORT = 5555;

    public static void main(String[] args) throws IOException {
        try(ServerSocket lSocket = new ServerSocket(PORT)) {
            while(true) {
                // Waiting for work
                Socket cSocket = lSocket.accept();
                // Start the work
                new Connection(cSocket);
            }
        }
    }
}
