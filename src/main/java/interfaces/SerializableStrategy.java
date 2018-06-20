package interfaces;

import classes.IDOverFlowException;

import java.io.IOException;

import java.sql.*;

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
      Setup Serializing the classes.Playlist
     */
    void openWritablePlaylist() throws  SQLException, IOException;

    /*
      Setup Deserializing the classes.Playlist
     */
    void openReadablePlaylist() throws IOException, SQLException;


    /*
     Write a classes.Song to the recently opened Medium
     */
    void writeSong(Song s) throws IOException;

    /*
     Read a song from the recently opened medium
     */
    Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException;

    /*
     Write songs from the library by calling writeSong for each classes.Song in library
    */
    void writeLibrary(Playlist p) throws IOException;

    /*
     Read songs into the library by calling readSong until null is returned
     */
    Playlist readLibrary() throws IOException, ClassNotFoundException;

    /*
     Write a PlayList by calling writeSong for each classes.Song
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


