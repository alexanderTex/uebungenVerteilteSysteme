/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 *
 * Repräsentiert ein Stock
 */
public class Stock {
    private String name;
    private double value;
    private String time;

    public Stock(String name, double value, String time) {
        this.name = name;
        this.value = value;
        this.time = time;
    }

    public String toString() {
        return "Aktie - Name: " + name +
                      " Wert: " + value +
                      " Stand: " + time;
    }
}
