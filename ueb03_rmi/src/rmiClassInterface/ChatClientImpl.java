/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 * filename:    ChatClientImpl.java
 * created:     23.05.2016
 */

package rmiClassInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ChatClientImpl extends UnicastRemoteObject implements ChatClient
{

    // /===============================================================================================\
    //    variables
    // \===============================================================================================/

    // /----------------------------------------------------\
    //    private
    // /----------------------------------------------------/

    private String name;

    // /===============================================================================================\
    //    constructors
    // \===============================================================================================/

    public ChatClientImpl(String name) throws RemoteException
    {
        this.name = name;
    }

    // /===============================================================================================\
    //    methods
    // \===============================================================================================/

    public String getName() throws RemoteException
    {
        return this.name;
    }

    public void print(String msg) throws RemoteException
    {
        System.out.println(msg);
    }
}
