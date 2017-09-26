package application;

import java.net.*;
import java.io.*;

public class WikiConnectionReader {

    public String URLConnection(String input) throws Exception {
        URL url = new URL(input);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (ambobay2@bsu.edu)");
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                return inputLine;
            in.close();
            return"";
        }
        catch (UnknownHostException e){
            return "A Connection Could Not Be Established";
        }
    }

    public String URLCreator(String searchTerm){
        String beginning = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&list=users&indexpageids=1&titles=";
        String end = "&rvprop=user%7Ctimestamp&rvlimit=30";
        String fullUrl = beginning + searchTerm + end;
        return fullUrl;
    }


}
