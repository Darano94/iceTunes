package main;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view.View;
import controller.Controller;
import classes.Playlist;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        View view = new View();

        Controller controller = new Controller();
        controller.link(model, view);
        controller.loadlib("C:/mukke/", new Playlist(), view);
        controller.loadPlaylist(new Playlist());


        primaryStage.setScene(new Scene(view, 728, 728 / 16 * 9));
        primaryStage.setTitle("iceTunes - ice ice Baby!");
        primaryStage.show();
    }
}
