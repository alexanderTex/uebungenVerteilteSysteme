/**
 * Created by Kuro on 07.05.2016.
 */
public class Main {
    public static void main(String[] args) {
        Park park = new Park();
        Car[] car = new Car[10];

        for (int i = 0; i < 10; i++)
            car[i] = new Car();

        for (int i = 0; i < 10; i++)
            car[i].startParking(park);
    }
}
