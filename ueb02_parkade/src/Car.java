/**
 * @author Alexander Luedke, 548965
 * @version 1.0
 * filename:    Car.java
 * created:     03.05.2016
 */
public class Car extends Thread {

    // /===============================================================================================\
    //
    //    variables
    //
    // \===============================================================================================/

    // /----------------------------------------------------\
    //
    //    public
    //
    // /----------------------------------------------------/

    public int nr;

    // /----------------------------------------------------\
    //
    //    private
    //
    // /----------------------------------------------------/

    private Parkade parkade;




    // /===============================================================================================\
    //
    //    constructors
    //
    // \===============================================================================================/

    /**
     * The constructor to initialize the queue and number of car.
     * @param parkade
     *          The queue
     * @param nr
     *          The specific carnumber
     */
    Car(Parkade parkade, int nr)
    {
        this.parkade = parkade;
        this.nr = nr;
    }



    // /===============================================================================================\
    //
    //    methods
    //
    // \===============================================================================================/


    /**
     * start the new thread "car"
     */
    public void run()
    {
            // add car to queue
            parkade.enter(this);
            System.out.println(this.nr + ": enter");

            // The fake delay for the car to get off.
            try {
                sleep((int)(Math.random() * 3000));
            }
            catch (InterruptedException e){}

            // remove car from queue
            parkade.leave(this);
            System.out.println(this.nr + ": leave");
    }
}
