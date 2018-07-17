package client.mvc;

import javafx.scene.layout.BorderPane;
import server.classes.Song;
import javafx.scene.control.*;

public class View extends BorderPane{
    private ListView<String> songListView = new ListView<String>();


    public View(){
        setCenter(songListView);
    }
}