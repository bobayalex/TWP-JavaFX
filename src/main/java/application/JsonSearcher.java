package application;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings("WeakerAccess")//to emliminate the warnings that they could be private because I do not want them to be
public class JsonSearcher {

    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> timestamps = new ArrayList<String>();
    private ArrayList<String> unsortedNames = new ArrayList<String>();
    private ArrayList<String> unsortedTimestamps = new ArrayList<String>();
    private ArrayList<String> sortedNames = new ArrayList<String>();
    private ArrayList<String> sortedTimestamps = new ArrayList<String>();


    public String JsonSearch(String jsonDataInput){ // pulls data from Json file, has to be long to be easy to follow
        try{
            JsonArray revisionsArray;
            ArrayList<String> userAndTimeList= new ArrayList<String>();//final array with all the revisions in it

            com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
            InputStream inputStream = new ByteArrayInputStream(jsonDataInput.getBytes());
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
            return MakeOutputString(names, timestamps);


        }
        catch (NullPointerException e){
           return ("Page Not Found");

        }
    }//JsonSearch end

    public void ArraySeparator(ArrayList<String> inputArray){//takes the long arraylist of all the values and separates them to names and timestamps
        int counter=0;
        while(counter < inputArray.size()) {
            if (inputArray.get(counter).equals(""))
            {
                inputArray.remove(counter);
            }

            if (counter % 2 == 0) {
                names.add(inputArray.get(counter));

            } else {
                timestamps.add(inputArray.get(counter));
            }

            counter++;
        }
    }//arrayseparator end


    public String MakeOutputString(ArrayList<String> firstTerm, ArrayList<String>secondTerm){
        String output= "";
        int counter = 0;
        while(counter < firstTerm.size()){
            output=output + (counter+1)+". " +firstTerm.get(counter)+"\n"+secondTerm.get(counter)+"\n";
            counter++;
        }
    return output;
    }//makeOutputString end

    public String SortingByMostEdits(){
        unsortedNames =names;
        unsortedTimestamps =timestamps;
        int counter = 0;
        while(counter < names.size()){
            AddString( Collections.frequency(unsortedNames, unsortedNames.get(0)), unsortedNames.get(0));
        }
       return MakeOutputString(sortedNames,sortedTimestamps);
    }

    private void AddString(int amount, String object) {
        SearchThrough(unsortedNames, amount, object);

    }//SortingByMostEdits end

    private void SearchThrough(ArrayList<String> list, int amount, String object) {
        while (amount>0){
            for (int counter = 0; counter < list.size(); counter++)
            {
                if ( unsortedNames.get(counter).equals(object))     /* Searching element is present */
                {
                    sortedNames.add(unsortedNames.get(counter));
                    sortedTimestamps.add(unsortedTimestamps.get(counter));
                    unsortedNames.remove(counter);
                    unsortedTimestamps.remove(counter);

                    break;
                }
            }


        amount--;
        }
    }//SearchThrough end
}//main end

