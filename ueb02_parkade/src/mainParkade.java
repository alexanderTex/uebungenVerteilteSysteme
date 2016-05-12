/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 * filename:    mainParkade.java
 * created:     03.05.2016
 */
public class mainParkade
{
    public static void main(String[] ars)
    {
        // create parkade
        Parkade parkade = new Parkade(4);

        // create cars
        Car c1 = new Car(parkade,01);
        Car c2= new Car(parkade,02);
        Car c3 = new Car(parkade,03);
        Car c4 = new Car(parkade,04);
        Car c5 = new Car(parkade,05);

        // start threats
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();

        try {
            c1.join();
            c2.join();
            c3.join();
            c4.join();
            c5.join();
        }
         catch (InterruptedException i) {}
        // controloutput for overall in- and ouputs.
        System.out.print("Ein- und Ausfahrten: " + parkade.getInOutCount());

    }
}
