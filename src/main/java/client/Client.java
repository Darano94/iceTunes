package client;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.mvc.Model;
import server.mvc.View;

import java.rmi.Naming;

public class Client extends Application {
    private static server.interfaces.RemoteInterface remote;

    public void start(Stage primaryStage) throws Exception {
        //get ref from stub object in registry
        remote = (server.interfaces.RemoteInterface) Naming.lookup("//127.0.0.1:1099/client");

        View view = remote.getView();
        Model model = remote.getModel();


        primaryStage.setScene(new Scene(view, 728, 728 / 16 * 9));
        primaryStage.setTitle("iceTunes - ice ice Baby!");
        primaryStage.show();
    }
}
