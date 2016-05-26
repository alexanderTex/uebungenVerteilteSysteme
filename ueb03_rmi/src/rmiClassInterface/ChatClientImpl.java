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

/**
 * Define the object ChatClientImpl to built instances which are chat participant.
 */
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient
{

    // /===============================================================================================\
    //    variables
    // \===============================================================================================/

    // /----------------------------------------------------\
    //    private
    // /----------------------------------------------------/

    private String name;        // identification by name

    // /===============================================================================================\
    //    constructors
    // \===============================================================================================/

    /**
     * @param name
     *      Initialise the variable for identification
     * @throws RemoteException
     */
    public ChatClientImpl(String name) throws RemoteException
    {
        this.name = name;       // set name
    }

    // /===============================================================================================\
    //    methods
    // \===============================================================================================/

    /**
     * TGet-Method for variable name
     * @return
     *      get name
     * @throws RemoteException
     */
    public String getName() throws RemoteException
    {
        return this.name;
    }

    /**
     * Output method for sending message
     * @param msg
     *      The printing message
     * @throws RemoteException
     */
    public void print(String msg) throws RemoteException
    {
        System.out.println(msg);
    }
}
