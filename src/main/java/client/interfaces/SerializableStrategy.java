package client.interfaces;

import client.classes.IDOverFlowException;
import client.classes.Playlist;
import client.classes.Song;

import java.io.IOException;
import java.sql.SQLException;

public interface SerializableStrategy {


    /*
      Setup Serializing the library
     */
    void openWritableLibrary() throws SQLException, IOException;

    /*
      Setup Deserializing the library
     */
    void openReadableLibrary() throws SQLException, IOException;

    /*
      Setup Serializing the server.classes.Playlist
     */
    void openWritablePlaylist() throws  SQLException, IOException;

    /*
      Setup Deserializing the server.classes.Playlist
     */
    void openReadablePlaylist() throws IOException, SQLException;


    /*
     Write a server.classes.Song to the recently opened Medium
     */
    void writeSong(Song s) throws IOException;

    /*
     Read a song from the recently opened medium
     */
    Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException;

    /*
     Write songs from the library by calling writeSong for each server.classes.Song in library
    */
    void writeLibrary(Playlist p) throws IOException;

    /*
     Read songs into the library by calling readSong until null is returned
     */
    Playlist readLibrary() throws IOException, ClassNotFoundException;

    /*
     Write a PlayList by calling writeSong for each server.classes.Song
    */
    void writePlaylist(Playlist p) throws IOException;

    /*
     Read a playlist by calling readSong until null is returned
     */
    Playlist readPlaylist() throws IOException, ClassNotFoundException;


    /*
     Finish writing/reading by closing all Streams
     */
    void closeWritableLibrary();

    void closeReadableLibrary();

    void closeWritablePlaylist();

    void closeReadablePlaylist();
}


