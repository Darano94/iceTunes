package model;

import classes.Playlist;
import classes.SerializableStrategy;
import classes.Song;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ModifiableObservableListBase;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;


public class Model {

    //Variablen

    private Playlist allSongs;
    private Playlist playlist;


    // Getter Setter
    public Playlist getAllSongs() {
        return this.allSongs;
    }

    public void setAllSongs(Playlist allSongs) {
        this.allSongs = allSongs;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }



}
