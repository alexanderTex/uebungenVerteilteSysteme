import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Alexander Luedke, 548965
 * @version 1.0
 * filename:    Parkade.java
 * created:     03.05.2016
 */
public class Parkade
{

    // /===============================================================================================\
    //
    //    variables
    //
    // \===============================================================================================/

    // /----------------------------------------------------\
    //
    //    private
    //
    // /----------------------------------------------------/

    private Queue parkSpace;

    private boolean parking = true;



    // /===============================================================================================\
    //
    //    constructors
    //
    // \===============================================================================================/

    /**
     * standard constructor
     *      for 100 parking spaces
     */
    Parkade()
    {
        this.parkSpace = new LinkedList();
    }



    // /===============================================================================================\
    //
    //    methods
    //
    // \===============================================================================================/

    /**
     * The possibility to get into the parkade
     */
    public synchronized void enter(Car car)
    {
        while (!parking)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e) {}
        }
        this.parkSpace.add(car);
        this.parking = true;

        notifyAll();
    }

    /**
     * The possibility to get out of the parkade
     */
    public synchronized void leave(Car car)
    {
        while (!parking)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e) {}
        }

        // remove from the head
        this.parkSpace.remove(car);

        this.parking = true;

        notifyAll();
    }
}
