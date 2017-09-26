package application;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")//to emliminate the warnings that they could be private because I do not want them to be
public class JsonSearcher {
    HashMap<String, String> finalValuesHM = new HashMap<String, String>();
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> timestamps = new ArrayList<String>();


    public ArrayList<String> JsonSearch(String jsonDataInput){ // pulls data from Json file
        try{
            com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
            JsonArray revisionsArray;
            ArrayList<String> userAndTimeList= new ArrayList<String>();//final array with all the revisions in it
            InputStream inputStream = new ByteArrayInputStream(jsonDataInput.getBytes());
            //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json") ;
            Reader reader = new InputStreamReader(inputStream);
            JsonElement rootElement = parser.parse(reader);
            JsonObject rootObject = rootElement.getAsJsonObject();
            JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
            for(Map.Entry<String,JsonElement> entry : pages.entrySet()) {
                JsonObject entryObject = entry.getValue().getAsJsonObject();
                revisionsArray = entryObject.getAsJsonArray("revisions");//gets us to revisions level
                for (JsonElement listOfRevisions : revisionsArray){
                    for(Map.Entry<String, JsonElement> oneRevision: listOfRevisions.getAsJsonObject().entrySet()) {//gets us a list of all revision information.
                        String itemFromRevision = oneRevision.getValue().getAsString();
                        userAndTimeList.add(itemFromRevision);
                    }
                }
            }
            ArraySeparator(userAndTimeList);
            Hashmapafiy();
            return userAndTimeList;
        }
        catch (NullPointerException e){
            ArrayList<String> error = new ArrayList<String>();
            error.add("Page Not Found");
            return error;
        }
    }

    public void ArraySeparator(ArrayList<String> inputArray){//takes the long arraylist of all the values and separates them to names and timestamps
        int counter=0;
        while(counter < inputArray.size()) {
            if (inputArray.get(counter).equals(""))
            {
                inputArray.remove(counter);
            }

            if (counter % 2 == 0) {
                timestamps.add(inputArray.get(counter));

            } else {
                names.add(inputArray.get(counter));
            }

            counter++;
        }
    }//arrayseparator end

    public void Hashmapafiy(){// converts two lists to one hashmap
        int counter= 0;
        while(counter < names.size()){
            finalValuesHM.put(names.get(counter),timestamps.get(counter));
            counter++;
        }
    }
}//main end

