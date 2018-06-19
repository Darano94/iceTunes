package model;

import classes.Playlist;


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
