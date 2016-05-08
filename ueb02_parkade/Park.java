import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

/**
 * Created by Kuro on 07.05.2016.
 *
 * Represents the parking zone
 */
public class Park implements Runnable {
    // Concurrent HashSet
    private ConcurrentHashMap.KeySetView<Car, Boolean> parkingSlots;
    // Max. space in the parking zone
    private int maxSlots;
    // Waiting queue
    private Queue<Car> waitingQueue;

    /* Constructors */

    public Park() {
        parkingSlots = ConcurrentHashMap.newKeySet();
        waitingQueue = new ConcurrentLinkedQueue<>();
        maxSlots = 4;
        Thread queueTask = new Thread(this);
        queueTask.start();
    }

    /**
     * Line up the waiting queue to entering the parking zone
     *
     * @param car The car, which attempts to park
     */
    public synchronized void enter(Car car) {
        System.out.println(car + " attempt to enter parking slot");
        // If the car is already parking
        if (parkingSlots.contains(car)) {
            System.out.println(car + " - Already in parking-area");
            return;
        }
        // Line up to the waiting queue
        waitingQueue.offer(car);
        System.out.println(car + " - Entered queue");
        // Shout, that a new car has entered the waiting queue
        notifyAll();
    }

    /**
     * Leaving the parking zone
     *
     * @param car The car, which attempts to leave
     */
    public synchronized void leave(Car car) {
        // Wait for a signal to leave
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(car + " attempt to leave parking slot");
        // Is not parking in the parking zone
        if (!parkingSlots.contains(car)) {
            System.out.println(car + " - Not in parking-area");
            return;
        }

        // Leave the parking zone
        parkingSlots.remove(car);
        System.out.println(car + " - Left parking-slot");
        System.out.println("Slots free: " + (maxSlots - parkingSlots.size()));
        // Shout, that a car has left the parking zone
        notifyAll();
    }

    /**
     * Implements {@link Runnable#run()}
     *
     * Processing the waiting queue.
     */
    @Override
    public synchronized void run() {
        while (true) {
            // If there is no car in the waiting queue or the parking zone is full
            if (waitingQueue.isEmpty() || parkingSlots.size() >= maxSlots) {
                // wait until there is work todo
                try {
                    notifyAll();
                    wait(500);
                    System.out.println(waitingQueue.size() + " - " + parkingSlots.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // Remove car from the waiting queue and add it to the parking zone
                Car car = waitingQueue.poll();
                parkingSlots.add(car);
                car.setParking(true);
                System.out.println(car + " - Left queue and entered parking-slot");
                System.out.println("Slots free: " + (maxSlots - parkingSlots.size()));
            }
            notifyAll();
        }
    }
}
