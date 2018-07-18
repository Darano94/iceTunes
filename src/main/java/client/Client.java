package client;


import client.mvc.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.mvc.Controller;
import server.mvc.Model;

public class Client extends Application {

    public void start(Stage primaryStage) throws Exception {

        View view = new View();
        Model model = new Model();
        Controller controller = new Controller();

        controller.loadlib(view);

//        controller.link(model, view);



        primaryStage.setScene(new Scene(view, 728, 728 / 16 * 9));
        primaryStage.setTitle("iceTunes - ice ice Baby!");
        primaryStage.show();
    }
}
