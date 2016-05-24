/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 * filename:    ChatClient.java
 * created:     23.05.2016
 */

package rmiClassInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ChatClient extends Remote
{
    // /===============================================================================================\
    //    methods
    // \===============================================================================================/

    public String getName() throws java.rmi.RemoteException;

    public void print(String msg) throws java.rmi.RemoteException;
}
