package classes;

import interfaces.Playlist;
import interfaces.SerializableStrategy;
import interfaces.Song;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;

public class OpenJPAStrategy implements SerializableStrategy {

    EntityManagerFactory fac;
    EntityManager e;
    EntityTransaction t;

    @Override
    public void openWritableLibrary() throws SQLException, IOException {
        fac = Persistence.createEntityManagerFactory("openjpa");
        e = fac.createEntityManager();

    }

    @Override
    public void openReadableLibrary() throws SQLException, IOException {
        fac = Persistence.createEntityManagerFactory("openjpa");
        e = fac.createEntityManager();
    }

    @Override
    public void openWritablePlaylist() throws SQLException, IOException {
}

    @Override
    public void openReadablePlaylist() throws IOException, SQLException {

    }

    @Override
    public void writeSong(Song s) throws IOException {
       t = e.getTransaction();
       t.begin();
       e.persist(s);
       t.commit();
    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException {
        Song a;
        t = e.getTransaction();
        t.begin();

        for (Object s : e.createQuery("SELECT id, title, interpret, album, path FROM lib").getResultList()){
            a = (classes.Song) s;
        }
        t.commit();
        return a;
    }

    @Override
    public void writeLibrary(Playlist p) throws IOException {
        fac = Persistence.createEntityManagerFactory("openjpa");
        e = fac.createEntityManager();

    }

    @Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {
        fac = Persistence.createEntityManagerFactory("openjpa");
        e = fac.createEntityManager();
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
