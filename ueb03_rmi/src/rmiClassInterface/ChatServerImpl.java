/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 * filename:    ChatServerImpl.java
 * created:     23.05.2016
 */

package rmiClassInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;


public class ChatServerImpl extends UnicastRemoteObject implements ChatServer
{
    // /===============================================================================================\
    //    variables
    // \===============================================================================================/

    // /----------------------------------------------------\
    //    private
    // /----------------------------------------------------/

    private ArrayList<ChatClient> allClients;

    // /===============================================================================================\
    //    constructors
    // \===============================================================================================/

    public ChatServerImpl() throws RemoteException
    {
        allClients = new ArrayList<ChatClient>();
    }

    // /===============================================================================================\
    //    methods
    // \===============================================================================================/

    public synchronized boolean addClient(ChatClient chatClient) throws RemoteException
    {
        String name = chatClient.getName();

        for (Iterator<ChatClient> iter = allClients.iterator(); iter.hasNext(); )
        {
            ChatClient cc = iter.next();
            try {
                if (cc.getName().equals(name))
                {
                    cc.print("The client name already exist.");
                    return false;
                }
            } catch (RemoteException exc)
            {
                iter.remove();
            }
        }
        allClients.add(chatClient);
        chatClient.print("added client");
        return true;
    }

    public synchronized void removeClient(ChatClient objRef) throws RemoteException
    {
        objRef.print("client remove successful");
        allClients.remove(objRef);
    }

    public synchronized void sendMessage(String name, String msg) throws RemoteException
    {
        for (Iterator<ChatClient> iter = allClients.iterator(); iter.hasNext();)
        {
            ChatClient cc = iter.next();
            try
            {
                cc.print(name + ": " + msg);
            }
            catch (RemoteException exc)
            {
                iter.remove();
            }
        }
    }
}

