package classes;

import interfaces.Playlist;
import interfaces.SerializableStrategy;
import interfaces.Song;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;

public class OpenJPAStrategy implements SerializableStrategy {

    EntityManagerFactory fac = Persistence.createEntityManagerFactory("openjpa");
    EntityManager e = fac.createEntityManager();

    @Override
    public void openWritableLibrary() throws SQLException, IOException {

    }

    @Override
    public void openReadableLibrary() throws SQLException, IOException {

    }

    @Override
    public void openWritablePlaylist() throws SQLException, IOException {

    }

    @Override
    public void openReadablePlaylist() throws IOException, SQLException {

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
