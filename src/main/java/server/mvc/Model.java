package server.mvc;

import server.classes.Playlist;

import java.io.Serializable;


public class Model implements Serializable {

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
