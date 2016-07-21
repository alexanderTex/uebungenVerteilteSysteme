import java.util.*;
import java.io.*;

/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 *
 * User-Interface zur Eingabe von Stocks und die Ausgabe der Informationen
 */
public class StockifyMain {
    public static void main(String[] args) {
        Stockify stockify = new Stockify();
        LinkedList<String> argList = new LinkedList<String>();
        processUserArgInput(argList);
        for(Stock stock : stockify.fetchStocks(argList.toArray(new String[argList.size()])))
            System.out.println(stock);
    }

    /**
     *  Verarbeitet den User-Input
     *  @param argList  Liste, in der der User-Input gespeichert werden soll
     */
    private static void processUserArgInput(LinkedList<String> argList) {
        String input;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                System.out.print("Add to query: ");
                input = br.readLine();
                argList.add(input);
            } while(!input.equals(""));
        } catch(IOException e) {
            System.out.println("IO: " + e);
        }
    }
}
