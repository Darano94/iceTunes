package client;


import client.mvc.View2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.classes.Playlist;
import client.mvc.Controller;
import server.mvc.Model;
import server.mvc.View;

import java.rmi.Naming;

public class Client extends Application {

    public void start(Stage primaryStage) throws Exception {

        View2 view = new View2();
        Model model = new Model();
        Controller controller = new Controller();

        controller.loadlib();

//        controller.link(model, view);



        primaryStage.setScene(new Scene(view, 728, 728 / 16 * 9));
        primaryStage.setTitle("iceTunes - ice ice Baby!");
        primaryStage.show();
    }
}
