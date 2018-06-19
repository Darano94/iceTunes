package classes;

import interfaces.Playlist;
import interfaces.SerializableStrategy;
import interfaces.Song;

import java.io.IOException;
import java.sql.*;

public class JDBCStrategy implements SerializableStrategy{
    Connection conlib;
    Connection conplaylist;
    PreparedStatement pst;
    ResultSet resultSet;

    @Override
    public void openWritableLibrary() throws  SQLException {
        conlib = DriverManager.getConnection("jdbc:sqlite:lib.db");
        pst = conlib.prepareStatement("CREATE TABLE IF NOT EXISTS  Lib (id long, title text, album text);");
        pst.execute();
    }

    @Override
    public void openReadableLibrary() throws SQLException {
        pst = conlib.prepareStatement("SELECT * FROM LIB;");
        resultSet = pst.executeQuery();
        //mit resultset können wir lesen : while(resulSet.next()) - sout(resultSet.getText("album");
    }

    @Override
    public void openWritablePlaylist() throws SQLException {
        conplaylist = DriverManager.getConnection("jdbc:sqlite:playlist.db");
        pst = conplaylist.prepareStatement("CREATE TABLE IF NOT EXISTS  Lib (id long, title text, album text);");
        pst.execute();
    }

    @Override
    public void openReadablePlaylist() throws IOException {

    }

    @Override
    public void writeSong(Song s) throws IOException {

    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException {
        return null;
    }

    @Override
    public void writeLibrary(Playlist p) throws IOException {

    }

    @Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void writePlaylist(Playlist p) throws IOException {

    }

    @Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void closeWritableLibrary() {

    }

    @Override
    public void closeReadableLibrary() {

    }

    @Override
    public void closeWritablePlaylist() {

    }

    @Override
    public void closeReadablePlaylist() {

    }
}