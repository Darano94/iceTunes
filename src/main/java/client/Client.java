package client;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import client.mvc.View;

public class Client extends Application {

    public void start(Stage primaryStage) throws Exception {

        View view = new View();


        primaryStage.setScene(new Scene(view, 728, 728 / 16 * 9));
        primaryStage.setTitle("iceTunes - ice ice Baby!");
        primaryStage.show();
    }
}
