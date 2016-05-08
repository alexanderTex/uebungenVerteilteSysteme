/**
 * Created by Kuro on 07.05.2016.
 *
 * represents a Car, which can park
 */
public class Car {
    private static int idCounter = 0;    // ID-Counter
    private int id;                      // ID of the car
    private boolean isParking = false;   // If the car is parking

    /* Setter */

    public void setParking(boolean parking) {
        isParking = parking;
    }

    /* Constructors */

    public Car() {
        this.id = idCounter++;
    }

    /**
     * Starts the parking process.
     *
     * @param park ParkingZone for the car
     */
    public void startParking(Park park) {
        Car car = this;
        /**
         * Runnable for the parking process
         */
        class ParkTask implements Runnable {
            /**
             * Method for the thread
             */
            @Override
            public synchronized void run() {
                // Enter the parking-zone
                park.enter(car);
                // Wait a random amount of time
                try {
                    Thread.sleep((long)(Math.random() * 15000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // If the car is still in the waiting-queue wait
                while (!isParking) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Leave the parking zone
                park.leave(car);
            }
        }

        Thread thread = new Thread(new ParkTask());
        thread.start();
    }

    /**
     * Overrides {@link Object#toString()}.
     *
     * @return Representation of the car
     */
    @Override
    public String toString() {
        return "Car [" + id + "]";
    }
}
