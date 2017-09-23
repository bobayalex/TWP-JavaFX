package application;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.Map;

public class JsonSearcher {
    public JsonArray JsonSearch(){
        com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json") ;
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for(Map.Entry<String,JsonElement> entry : pages.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        return array;
    }
    public void JsonPrint(JsonArray test){

        System.out.println(test);




    }
}
