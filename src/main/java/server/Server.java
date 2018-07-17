package server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.classes.NullSecurityManager;
import server.classes.Playlist;
import server.classes.RemoteObject;
import server.mvc.Controller;
import server.mvc.Model;
import server.mvc.View;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class Server extends Application {

    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();

        // use a non-restrictive security manager
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new NullSecurityManager());
        }
        //start rmiregistry
        LocateRegistry.createRegistry(1099);
        //create instance of the RemoteObject
        Remote remote = new RemoteObject();
        //every registry input consists of a Name and a object ref.
        Naming.rebind("//localhost:1099/client", remote);
        System.out.println("Sever startet");

        controller.link(model, view);
        controller.loadlib("/Users/darano/mukke", new Playlist(), view);
        controller.loadPlaylist(new Playlist());


        primaryStage.setScene(new Scene(view, 728, 728 / 16 * 9));
        primaryStage.setTitle("iceTunes - ice ice Baby!");
        primaryStage.show();
    }
}