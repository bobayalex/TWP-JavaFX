package windowControllers;

import application.JsonSearcher;
import application.WikiConnectionReader;
import com.google.gson.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.IOException;

public class MainWindowController {

    private Main main;
    @FXML private TextArea results;
    @FXML private TextField inputArea;


    public void setMain(Main main){
        this.main=main;
    }

    public void submit(ActionEvent actionEvent) {
        try{
            String input = inputArea.getText();
            if (input.contains(" ")){
                input = input.replace(" ", "%20");
            }
            WikiConnectionReader fetcher = new WikiConnectionReader();
            JsonSearcher searcher = new JsonSearcher();
            String url = fetcher.URLCreator(input);
            String jsonData = fetcher.URLConnection(url);
            results.setText((searcher.JsonSearch(jsonData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sort(ActionEvent actionEvent){
        try{
            String input = inputArea.getText();
            if (input.contains(" ")){
                input = input.replace(" ", "%20");
            }
            WikiConnectionReader fetcher = new WikiConnectionReader();
            JsonSearcher searcher = new JsonSearcher();
            String url = fetcher.URLCreator(input);
            String jsonData = fetcher.URLConnection(url);
            searcher.JsonSearch(jsonData);
            results.setText(searcher.SortingByMostEdits());
            //NOT SURE WHY THIS PRINTS THE SAME AS THE OTHER METHOD
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
