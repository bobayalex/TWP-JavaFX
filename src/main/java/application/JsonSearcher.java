package application;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.print.DocFlavor;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class JsonSearcher {
    public JsonArray JsonSearch(){
        com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
        JsonArray outputfile = null;// holds the revision
        ArrayList<String> exitEntries= new ArrayList<String>();//final array with all the revisions in it
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json") ;
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        for(Map.Entry<String,JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            outputfile = entryObject.getAsJsonArray("revisions");//gets us to revsions level
            for (JsonElement listOfRevisions : outputfile){
                for(Map.Entry<String, JsonElement> oneRevision: listOfRevisions.getAsJsonObject().entrySet()) {//gets us a list of all revision information.
                    String testObject = oneRevision.getValue().getAsString();
                    exitEntries.add(testObject);
                }
            }
        }


        System.out.println(exitEntries);
        ArrayList<String> names = ArraySeparator(exitEntries,1);

        ArrayList<String> timestamps = ArraySeparator(exitEntries, 2);
        System.out.println(names);
        System.out.println(timestamps);
        return outputfile;
    }


    public static ArrayList<String> ArraySeparator(ArrayList<String> array, int whicharray){
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> timestamps = new ArrayList<String>();
        for (int counter = 0;counter >= array.size(); counter++) {

            if (counter % 2==0) {
                timestamps.add(array.get(counter));
            }
            else{
                names.add(array.get(counter));
            }
        }
        if (whicharray==1){
            return names;
        }
        else {
            return timestamps;
        }
    }

}//main end
