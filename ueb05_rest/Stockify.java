import java.net.*;
import java.io.*;
import java.util.*;

import org.json.*;

public class Stockify {
    public Stockify() {}

    public LinkedList<Stock> fetchStocks(String... stocks) {
        String baseURL = "http://finance.google.com/finance/info?client=ig&q=";
        for (String stock : stocks)
            baseURL += stock + ",";
        baseURL.substring(0, baseURL.length()-1);

        LinkedList<Stock> fetchedStocks = new LinkedList<Stock>();

        try {
            URL url = new URL(baseURL);

            try (BufferedInputStream is = new BufferedInputStream(url.openStream());
                Scanner sc = new Scanner(is)) {
                String jsonString = "";
                while (sc.hasNextLine())
                    jsonString += sc.nextLine();
                jsonString = jsonString.substring(3, jsonString.length());

                JSONArray json = new JSONArray(jsonString);

                Iterator it = json.iterator();
                while (it.hasNext()) {
                    JSONObject obj = (JSONObject)it.next();
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
