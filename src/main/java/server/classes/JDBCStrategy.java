package server.classes;

import server.interfaces.Playlist;
import server.interfaces.SerializableStrategy;
import server.interfaces.Song;
import java.sql.*;

import java.io.IOException;
import java.sql.SQLException;

public class JDBCStrategy implements server.interfaces.SerializableStrategy{
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet rs;


    @Override
    public void openWritableLibrary() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:lib.db");
        pst = connection.prepareStatement("CREATE TABLE IF NOT EXISTS lib (id long, title text, interpret text, album text, path text)");
        pst.executeUpdate();
    }

    @Override
    public void openReadableLibrary() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:lib.db");
    }

    @Override
    public void openWritablePlaylist() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:playlist.db");
        pst = connection.prepareStatement("CREATE TABLE IF NOT EXISTS playlist (id long, title text, interpret text, album text, path text)");
        pst.executeUpdate();
    }

    @Override
    public void openReadablePlaylist() throws IOException, SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:playlist.db");
    }

    @Override
    public void writeSong(server.classes.Song s) throws IOException {
        try {
            pst = connection.prepareStatement("INSERT INTO playlist (id, title, interpret, album, path) VALUES (?,?,?,?,?)");
            pst.setLong(1, s.getId());
            pst.setString(2, s.titleProperty().getValue());
            pst.setString(3, s.interpretProperty().getValue());
            pst.setString(4, s.albumProperty().getValue());
            pst.setString(5, s.pathProperty().getValue());
            pst.executeUpdate(); //executeUpdate bei UPDATE, INSERT oder DELETE ; executeQuery bei Rückgabe => SELECT
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public server.classes.Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException {
        server.classes.Song s = new server.classes.Song();
        try {
            pst = connection.prepareStatement("SELECT id, title, interpret, album, path FROM playlist");
            rs = pst.executeQuery();
            while(rs.next()){
                s.setId(rs.getLong("d"));
                s.setTitle(rs.getString("title"));
                s.setInterpret(rs.getString("interpret"));
                s.setAlbum(rs.getString("album"));
                s.setPath(rs.getString("path"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public void writeLibrary(server.classes.Playlist p) throws IOException {
        for(server.classes.Song s : p){
            try {
                pst = connection.prepareStatement("INSERT INTO lib (id, title, interpret, album, path) VALUES (?,?,?,?,?)");
                pst.setLong(1, s.getId());
                pst.setString(2, s.titleProperty().getValue());
                pst.setString(3, s.interpretProperty().getValue());
                pst.setString(4, s.albumProperty().getValue());
                pst.setString(5, s.pathProperty().getValue());
                pst.executeUpdate(); //executeUpdate bei UPDATE, INSERT oder DELETE ; executeQuery bei Rückgabe => SELECT

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public server.classes.Playlist readLibrary() throws IOException, ClassNotFoundException {
        server.classes.Playlist p1 = new server.classes.Playlist();
        try {
            pst = connection.prepareStatement("SELECT * FROM lib");
            rs = pst.executeQuery();

            while(rs.next()){
                server.classes.Song s = new server.classes.Song();
                s.setId(rs.getLong("id"));
                s.setAlbum(rs.getString("album"));
                s.setInterpret(rs.getString("interpret"));
                s.setTitle(rs.getString("title"));
                s.setPath(rs.getString("path"));
                p1.addSong(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IDOverFlowException e) {
            e.printStackTrace();
        }
        return p1;
    }

    @Override
    public void writePlaylist(server.classes.Playlist p) throws IOException {
        for(Song s : p){
            try {
                pst = connection.prepareStatement("INSERT INTO playlist (id, title, interpret, album, path) VALUES (?,?,?,?,?)");
                pst.setLong(1, s.getId());
                pst.setString(2, s.titleProperty().getValue());
                pst.setString(3, s.interpretProperty().getValue());
                pst.setString(4, s.albumProperty().getValue());
                pst.setString(5, s.pathProperty().getValue());
                pst.executeUpdate(); //executeUpdate bei UPDATE, INSERT oder DELETE ; executeQuery bei Rückgabe => SELECT

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public server.classes.Playlist readPlaylist() throws IOException, ClassNotFoundException {
        server.classes.Playlist p1 = new server.classes.Playlist();
        try {
            pst = connection.prepareStatement("SELECT * FROM playlist");
            rs = pst.executeQuery();

            while (rs.next()) {
                server.classes.Song s = new server.classes.Song();
                s.setId(rs.getLong("id"));
                s.setAlbum(rs.getString("album"));
                s.setInterpret(rs.getString("interpret"));
                s.setTitle(rs.getString("title"));
                s.setPath(rs.getString("path"));
                p1.addSong(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IDOverFlowException e) {
            e.printStackTrace();
        }
        return p1;
    }

    @Override
    public void closeWritableLibrary() {
        if(connection != null){
            try {
                rs.close();
                pst.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeReadableLibrary() {
        if(connection != null){
            try {
                rs.close();
                pst.close();
                rs.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeWritablePlaylist() {
        if(connection != null){
            try {

                pst.close();
                rs.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeReadablePlaylist() {
        if(connection != null){
            try {
                pst.close();
                connection.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}