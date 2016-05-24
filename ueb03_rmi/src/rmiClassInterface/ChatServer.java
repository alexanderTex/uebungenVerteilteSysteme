/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 * filename:    ChatServer.java
 * created:     23.05.2016
 */

package rmiClassInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ChatServer extends Remote
{
    // /===============================================================================================\
    //    methods
    // \===============================================================================================/

    public boolean addClient(ChatClient objRef) throws RemoteException;
    public void removeClient(ChatClient objRef) throws RemoteException;
    public void sendMessage(String name, String msg) throws RemoteException;
}
