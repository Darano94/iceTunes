package main;


import classes.Playlist;
import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import mvc.Controller;
import mvc.Model;
import mvc.View;
import view.View;

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
        controller.loadlib("/Users/darano/mukke/", new Playlist(), view);
        controller.loadPlaylist(new Playlist());


        primaryStage.setScene(new Scene(view, 728, 728 / 16 * 9));
        primaryStage.setTitle("iceTunes - ice ice Baby!");
        primaryStage.show();
    }
}
