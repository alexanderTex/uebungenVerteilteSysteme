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
import java.util.Scanner;


public class startRmiClient
{
    public static void main(String[] args)
    {
        // Check arguments for two inputs
        if(args.length !=2)
        {
            System.out.println("The application needs two arguments: " + "<servername>" + "<nickname>");
        }

        /*
        InputStream inputStream = System.in;
        //Reader inputStreamReader = new InputStreamReader(inputStream);
        char c;
        int i;

        try {
            while((i= inputStream.read())!=-1)
            {
                // converts integer to character
                c = (char) i;
                System.out.print(c);
            }
        }
        catch (Exception e){}
        */

        try
        {
            // args[0] == localhost; args[1] == alex
            ChatClientImpl cc = new ChatClientImpl(args[1]);
            ChatServer csi = (ChatServer) Naming.lookup("rmi://" + args[0] + "/rmiServer");

            // INFO
            // System.out.println(csi.addClient(cc));
            csi.addClient(cc);

            // create scanner (input)
            // input == choose write message(1) or exit(2)
            //Scanner input = new Scanner(System.in);

            boolean exit = false;
            while (exit == false)
            {
                System.out.println("acitvity: \n 1) send message \n 2) exit");
                System.out.print("activity: ");
                Scanner input = new Scanner(System.in);
                int activity = input.nextInt();
                //input = null;
                //input.close();

                switch (activity) {

                    case 1: {
                        //InputStream inputStream = System.in;
                        //Reader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                        char c;
                        int i;
                        //String sendMsg = new String();

                        Scanner sc = new Scanner(System.in);
                        String sendMsg = "1";
                        while (!sendMsg.equals("0"))
                        {
                            try
                            {
                                InputStreamReader isr = new InputStreamReader(System.in);
                                BufferedReader br = new BufferedReader(isr);
                                System.out.print("msg: ");
                                String eingabe = br.readLine();
                                //Info
                                System.out.println("Du hast " + eingabe + " eingegeben.");
                            }
                            catch (IOException ioe){}

                        }
                            /*
                            // reset the input acitvity "1"
                            sendMsg = "";

                            try {
                                while ((i = bufferedReader.read()) != 10) {
                                    // converts integer to character
                                    c = (char) i;
                                    // System.out.print(i + " " + c + " ");
                                    sendMsg += c;
                                }
                                    System.out.print(sendMsg);
                                    csi.sendMessage("alex", sendMsg);
                                    // reset for new message
                                    sendMsg = "";

                            } catch (Exception e) {
                            }
                            */
                        break;
                    }

                    case 2: {
                        exit = true;
                        csi.removeClient(cc);
                        System.out.println("exit");

                        // close the scanner
                        input.close();

                        break;
                    }

                    default: {
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
