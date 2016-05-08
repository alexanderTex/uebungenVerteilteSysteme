/**
 * @author Alexander Luedke, 548965
 * @version 1.0
 * filename:    mainParkade.java
 * created:     03.05.2016
 */
public class mainParkade
{
    public static void main(String[] ars)
    {
        Parkade parkade = new Parkade();
        Car c1 = new Car(parkade,01);
        Car c2= new Car(parkade,02);
        Car c3 = new Car(parkade,03);
        Car c4 = new Car(parkade,04);
        Car c5 = new Car(parkade,05);


        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
    }
}
