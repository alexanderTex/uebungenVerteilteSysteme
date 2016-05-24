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


public class startRmiServer
{
    static Registry reg;

    public static void main(String[] args)
    {
        try
        {
            reg = LocateRegistry.createRegistry(1099);
            ChatServerImpl csi = new ChatServerImpl();
            Naming.rebind("rmiServer", csi);
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
