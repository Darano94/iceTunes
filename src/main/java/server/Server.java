package server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.classes.IDOverFlowException;
import server.classes.Playlist;
import server.classes.RemoteObject;
import server.interfaces.RemoteInterface;
import server.mvc.Controller;
import server.mvc.Model;
import server.mvc.View;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class Server extends Application {

    public void start(Stage primaryStage) throws Exception, IDOverFlowException {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();



        RemoteObject remote = new RemoteObject(controller, view);

        //start rmiregistry;
        LocateRegistry.createRegistry(1099);
        //move object in registry
        Naming.bind("name", remote);
        System.out.println("Server gestartet..");


        controller.link(model, view);
        controller.loadlib("C:/mukke/", new Playlist(), view);
        controller.loadPlaylist(new Playlist());


        primaryStage.setScene(new Scene(view, 728, 728 / 16 * 9));
        primaryStage.setTitle("iceTunes - ice ice Baby!");
        primaryStage.show();
    }
}