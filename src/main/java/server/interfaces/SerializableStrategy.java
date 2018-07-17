package server.interfaces;

import server.classes.IDOverFlowException;

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
    void writeSong(server.classes.Song s) throws IOException;

    /*
     Read a song from the recently opened medium
     */
    server.classes.Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException;

    /*
     Write songs from the library by calling writeSong for each server.classes.Song in library
    */
    void writeLibrary(server.classes.Playlist p) throws IOException;

    /*
     Read songs into the library by calling readSong until null is returned
     */
    server.classes.Playlist readLibrary() throws IOException, ClassNotFoundException;

    /*
     Write a PlayList by calling writeSong for each server.classes.Song
    */
    void writePlaylist(server.classes.Playlist p) throws IOException;

    /*
     Read a playlist by calling readSong until null is returned
     */
    server.classes.Playlist readPlaylist() throws IOException, ClassNotFoundException;


    /*
     Finish writing/reading by closing all Streams
     */
    void closeWritableLibrary();

    void closeReadableLibrary();

    void closeWritablePlaylist();

    void closeReadablePlaylist();
}


