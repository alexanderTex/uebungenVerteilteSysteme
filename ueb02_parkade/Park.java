import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

/**
 * Created by Kuro on 07.05.2016.
 */
public class Park implements Runnable {
    private ConcurrentHashMap.KeySetView<Car, Boolean> parkingSlots;
    private int maxSlots;
    private Queue<Car> waitingQueue;

    public Park() {
        parkingSlots = ConcurrentHashMap.newKeySet();
        waitingQueue = new ConcurrentLinkedQueue<>();
        maxSlots = 4;
        Thread queueTask = new Thread(this);
        queueTask.start();
    }

    public synchronized void enter(Car car) {
        System.out.println(car + " attempt to enter parking slot");
        if (parkingSlots.contains(car)) {
            System.out.println(car + " - Already in parking-area");
            return;
        }
        waitingQueue.offer(car);
        System.out.println(car + " - Entered queue");
        notifyAll();
    }

    public synchronized void leave(Car car) {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(car + " attempt to leave parking slot");
        if (!parkingSlots.contains(car)) {
            System.out.println(car + " - Not in parking-area");
            return;
        }

        parkingSlots.remove(car);
        System.out.println(car + " - Left parking-slot");
        System.out.println("Slots free: " + (maxSlots - parkingSlots.size()));
        notifyAll();
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (waitingQueue.isEmpty() || parkingSlots.size() >= maxSlots) {
                try {
                    notifyAll();
                    wait(500);
                    System.out.println(waitingQueue.size() + " - " + parkingSlots.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
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
