package windowControllers;

import application.JsonSearcher;
import application.WikiConnectionReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainWindowController {

    private Main main;
    @FXML private TextArea results;
    @FXML private TextField inputArea;


    public void setMain(Main main){
        this.main=main;
    }

    public void submit(ActionEvent actionEvent) {
        String result = "No Connection To Wikipedia";
        try{
            String input = inputArea.getText();
            WikiConnectionReader fetcher = new WikiConnectionReader();
            JsonSearcher parser = new JsonSearcher();
            String url = fetcher.URLCreator(input);
            result = fetcher.URLConnection(url);
            results.setText(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
