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
        // Check arguments for two inputs
        if(args.length !=2)
        {
            System.out.println("The application needs two arguments: " + "<servername>" + "<nickname>");
        }

        try
        {
            // args[0] == localhost; args[1] == alex
            ChatClientImpl chatClientImpl = new ChatClientImpl(args[1]);
            ChatServer chatServer = (ChatServer) Naming.lookup("rmi://" + args[0] + "/rmiServer");

            chatServer.addClient(chatClientImpl);

            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            boolean exitApplication = false;
            while (exitApplication == false)
            {
                System.out.println("\n acitvity: \n 1) send message \n 2) exit");
                System.out.print("choose activity: ");
                String activity = bufferedReader.readLine();

                switch (activity) {

                    case "1":
                    {
                        String inputBuffer = "1";
                        while (!inputBuffer.equals("0"))
                        {
                            // Input message
                            System.out.print("\nmsg: ");
                            inputBuffer = bufferedReader.readLine();

                            // No output for "0"
                            if (!inputBuffer.equals("0"))
                            {
                                // Send message to server
                                chatServer.sendMessage(args[1], inputBuffer);
                            }
                        }
                        break;
                    }

                    case "2":
                    {
                        exitApplication = true;
                        chatServer.removeClient(chatClientImpl);
                        System.out.println("exit");
                        break;
                    }

                    default:
                    {
                        System.out.println("false activity");
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
    }
}
