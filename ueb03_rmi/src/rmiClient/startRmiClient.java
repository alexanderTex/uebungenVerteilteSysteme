/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 * filename:    startRmiClient.java
 * created:     23.05.2016
 */

package rmiClient;

import rmiClassInterface.*;
import java.io.*;
import java.rmi.Naming;


public class startRmiClient
{
    /**
     * The RMI-Client
     * @param args
     *          [0] servername
     *          [1] client nickname
     */
    public static void main(String[] args)
    {
        if(args.length !=2) {
            // Check arguments for two inputs
            System.out.println("The application needs two arguments: " + "<servername>" + "<nickname>");
        }
        try
        {
            ChatClientImpl chatClientImpl = new ChatClientImpl(args[1]);    // ChatClient Instance
            ChatServer chatServer = (ChatServer) Naming.lookup("rmi://" + args[0] + "/rmiServer");
            /*
                Returns a chatserver reference, a stub, for the remote
                object (chatserver) associated with the specified name.
            */

            chatServer.addClient(chatClientImpl);       // add client to server array
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            /*
                 Wrapping an InputStreamReader within a BufferedReader,
                 that is reading from the underlying byte-input stream.

                 InputStreamReader
                    is a bridge from byte streams to character streams.
                    It reads bytes and decodes them into characters.

                 BufferedReader
                    reads text from a character-input stream (buffering characters).
                    The buffer size may be specified, or the default size may be used.
             */

            String activity = "0";          // Initialise the selection
            while (!activity.equals("2")) {
                // Selection menue ("2" == exit loop)
                System.out.println("\n acitvity: \n 1) send message \n 2) exit");   // selection option
                System.out.print("choose activity: ");
                activity = bufferedReader.readLine();                               // user input

                switch (activity) {
                    // activity selection
                    case "1": {
                        // send message
                        while (!activity.equals("0")) {
                            // send messages until input == "0"
                            System.out.print("\nmsg: ");
                            activity = bufferedReader.readLine();            //Input message
                            if (!activity.equals("0")) {
                                // No output for "0"
                                chatServer.sendMessage(args[1], activity);   // Send message to server
                            }
                        }
                        break;
                    }
                    case "2": {
                        // exit application
                        chatServer.removeClient(chatClientImpl);            // remove Client from server array
                        System.out.println("exit");
                        break;
                    }
                    default: {
                        // wrong input
                        System.out.println("false activity");
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }
    }
}
