package interfaces;


import classes.IDOverFlowException;
import classes.Playlist;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Model;
import view.View;

import java.io.File;
import java.rmi.RemoteException;

public interface Controller {
    void link(Model model, View view);

    void loadbtn(View view);

    void savebtn(View view);

    void addallbtn(View view);

    void addtoplaylistbtn(View view);

    void nextbtn(View view);

    void backbtn(View view);

    void deleteplaylist(View view);

    void commitbtn(View view);

    void loadlib(String path, Playlist allsongs, View view) throws RemoteException, IDOverFlowException;

    void loadPlaylist(Playlist playlist) throws RemoteException;

    void playpauseSong(View view);

    void playSong(classes.Song s, View view);

    void stopsong(View view);

    void pauseSong(classes.Song s);
}
