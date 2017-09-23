package application;

import java.net.*;
import java.io.*;

public class WikiConnectionReader {

    public static void URLConnection(String input) throws Exception {
        URL url = new URL(input);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (ambobay2@bsu.edu)");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
