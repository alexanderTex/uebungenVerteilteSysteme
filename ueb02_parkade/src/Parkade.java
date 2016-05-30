import java.util.LinkedList;
import java.util.Queue;

/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 * filename:    Parkade.java
 * created:     03.05.2016
 */
public class Parkade
{

    // /===============================================================================================\
    //    variables
    // \===============================================================================================/

    // /----------------------------------------------------\
    //    private
    // /----------------------------------------------------/

    private Queue parkSpace;            // Parkade
    private Queue watingQueue;
    private int parkPlaces;             // parkplaces overall
    private boolean parking = true;     // status
    private int count = 0;              // check for free places
    private int inOutCount = 0;         // control variable for in- and outpus overall

    // /===============================================================================================\
    //    constructors
    // \===============================================================================================/

    /**
     * standard constructor
     *      for 100 parking spaces
     */
    Parkade()
    {
        this.parkSpace = new LinkedList();
        this.watingQueue = new LinkedList<>();
        this.parkPlaces = 10;
    }

    /**
     * to create a parkade with numbers of places
     * @param parkPlaces
     *      the all parkplaces
     */
    Parkade(int parkPlaces)
    {
        this.parkSpace = new LinkedList();
        this.watingQueue = new LinkedList<>();
        this.parkPlaces = parkPlaces;
    }

    // /===============================================================================================\
    //    methods
    // \===============================================================================================/

    /**
     * The possibility to get into the parkade
     * @param car
     *      Add car to queue/parkade
     */
    public synchronized void enter(Car car) throws NullPointerException
    {
        this.watingQueue.add(car);
        System.out.println(car.nr + "bin da");

        // when false(no free parking space) -> wait
        while (!this.watingQueue.peek().equals(car) && !parking)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e) {}
        }

        // add car to parkade(into the Queue)
        this.parkSpace.add(car);

        this.watingQueue.remove(car);

        // to observe the free parking spaces
        this.count++;

        // control variable to check overall in- and  outputs at the end of main().
        this.inOutCount++;

        // check the limited parking spaces
        if (this.count == this.parkPlaces)
        {
            parking = false;
        }

        notifyAll();
    }

    /**
     * The possibility to get out of the parkade
     * @param car
     *      Add car to queue/parkade
     */
    public synchronized void leave(Car car)
    {
        // check for cars in the parkade.
        // If there at least one car, it can be leaved.
        if (this.count >= 0)
            this.parking = false;

        // when true(no cars) -> wait
        while (parking)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e) {}
        }


        // remove car
        this.parkSpace.remove(car);
        this.count--;

        // control variable to check overall in- and  outputs at the end of main().
        this.inOutCount++;

        // check for cars in the parkade, to add a new one
        if (this.count >= 0)
            this.parking = true;

        notifyAll();
    }

    /**
     * To get the control variable (for In and Output)
     * @return
     *      control variable
     */
    public int getInOutCount()
    {
        return this.inOutCount;
    }
}
