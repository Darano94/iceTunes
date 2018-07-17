package client;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.classes.Playlist;
import server.mvc.Controller;
import server.mvc.Model;
import server.mvc.View;

import java.rmi.Naming;

public class Client extends Application {

    public void start(Stage primaryStage) throws Exception {

        View view = new View();
        

        primaryStage.setScene(new Scene(view, 728, 728 / 16 * 9));
        primaryStage.setTitle("iceTunes - ice ice Baby!");
        primaryStage.show();
    }
}
