/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 * filename:    startRmiServer.java
 * created:     23.05.2016
 */

package rmiServer;

import rmiClassInterface.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The chat server
 */
public class startRmiServer
{
    static Registry registry;    // Instance of the interface Registry
    public static void main(String[] args)
    {
        try
        {
            registry = LocateRegistry.createRegistry(1099);
            /*
                Returns a reference to the remote object
                Registry for the local host on the specified port.
                Create a remote object registry that accepts calls on a specific port.
            */

            ChatServerImpl csi = new ChatServerImpl();  // ChatServerImpl Instance
            Naming.rebind("rmiServer", csi);            //Rebinds the specified name to the remote object (ChatServerImpl).
            System.out.print("chat-Server started under /rmiServer");
        }
        catch (RemoteException re){
            System.out.print(re);
        }
        catch (MalformedURLException mfurle) {
            System.out.print(mfurle);
        }
    }
}
