import java.net.*;
import java.io.*;
import java.util.*;

import org.json.*;

/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 *
 * Diese Klasse ist dazu da Stock-Informationen zu fetchen und zu verarbeiten
 */
public class Stockify {
    public Stockify() {}

    /**
     * Fetched Stock-Informationen von der Gooogle Finance API
     * @param stocks
     *          eine Kommagetrennte Liste von Stock-Names
     */
    public LinkedList<Stock> fetchStocks(String... stocks) {
        // Aufbau des URL-Strings
        String baseURL = "http://finance.google.com/finance/info?client=ig&q=";
        for (String stock : stocks)
            baseURL += stock + ",";
        baseURL.substring(0, baseURL.length()-1);

        LinkedList<Stock> fetchedStocks = new LinkedList<Stock>();

        try {
            URL url = new URL(baseURL);

            try (BufferedInputStream is = new BufferedInputStream(url.openStream());
                // Lesen der JSON-Antwort
                Scanner sc = new Scanner(is)) {
                String jsonString = "";
                while (sc.hasNextLine())
                    jsonString += sc.nextLine();
                jsonString = jsonString.substring(3, jsonString.length());

                // Lesen einzelner Wertpaare des JSON-Objektes
                JSONArray json = new JSONArray(jsonString);

                Iterator it = json.iterator();
                while (it.hasNext()) {
                    JSONObject obj = (JSONObject)it.next();
                    // Hinzufügen zur Antwortliste
                    fetchedStocks.add(
                        new Stock(obj.getString("t"),
                                  obj.getDouble("l_cur"),
                                  obj.getString("lt_dts"))
                    );
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("URL: " + e);
        } catch (IOException e) {
            System.out.println("Stream: " + e);
        }

        return fetchedStocks;
    }
}
