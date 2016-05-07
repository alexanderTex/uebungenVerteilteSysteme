/**
 * Created by Kuro on 07.05.2016.
 */
public class Car {
    private static int idCounter = 0;
    private int id;
    private boolean isParking = false;

    public void setParking(boolean parking) {
        isParking = parking;
    }

    public Car() {
        this.id = idCounter++;
    }

    public void startParking(Park park) {
        Car car = this;
        class ParkTask implements Runnable {
            @Override
            public synchronized void run() {
                park.enter(car);
                try {
                    Thread.sleep((long)(Math.random() * 15000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!isParking) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                park.leave(car);
            }
        }

        Thread thread = new Thread(new ParkTask());
        thread.start();
    }

    @Override
    public String toString() {
        return "Car [" + id + "]";
    }
}
