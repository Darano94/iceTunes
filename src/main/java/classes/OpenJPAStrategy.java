package classes;

import interfaces.Playlist;
import interfaces.SerializableStrategy;
import interfaces.Song;
import org.apache.openjpa.util.Serialization;

import javax.persistence.*;
import java.io.IOException;
import java.sql.SQLException;

public class OpenJPAStrategy implements SerializableStrategy {
    EntityManagerFactory fac;
    //EntityManager erstellen
    EntityManager e;
    //Entity Transaction bekommen
    EntityTransaction t ;


    //Transaktion durchführen, p in Datenbank speichern

    @java.lang.Override
    public void openWritableLibrary() throws SQLException, IOException {
    fac = Persistence.createEntityManagerFactory("openjpa");
    e = fac.createEntityManager();
    }

    @java.lang.Override
    public void openReadableLibrary() throws SQLException, IOException {
    fac = Persistence.createEntityManagerFactory("openjpa");
    e = fac.createEntityManager();
    }

    @java.lang.Override
    public void openWritablePlaylist() throws SQLException, IOException {

    }

    @java.lang.Override
    public void openReadablePlaylist() throws IOException, SQLException {

    }

    @java.lang.Override
    public void writeSong(Song s) throws IOException {
    fac = Persistence.createEntityManagerFactory("openjpa");
    e = fac.createEntityManager();
    t = e.getTransaction();
    t.begin();
    e.persist(s);
    t.commit();
    }

    @java.lang.Override
    public Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException {
        t = e.getTransaction();
        t.begin();
        String aa = "SELECT id, title, interpret, album, path FROM lib";
        Song a = null;
        for (Object s : e.createQuery(aa.toString()).getResultList()) {
            a = (Song) s;

        }

        t.commit();
        return a;
    }

    @java.lang.Override
    public void writeLibrary(Playlist p) throws IOException {
    fac = Persistence.createEntityManagerFactory("openjpa");
    e = fac.createEntityManager();
    t = e.getTransaction();
    t.begin();
    e.persist(p);
    t.commit();
    }

    @java.lang.Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {
        t= e.getTransaction();
        t.begin();
        String aa = "SELECT * FROM lib";
        classes.Playlist a = null;
        for (Object s : e.createQuery(aa.toString()).getResultList()) {
            a = (classes.Playlist)s;
        }

        t.commit();
        return a;
    }

    @java.lang.Override
    public void writePlaylist(Playlist p) throws IOException {
        writeLibrary(p);
    }

    @java.lang.Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {
        return readLibrary();
    }

    @java.lang.Override
    public void closeWritableLibrary() {
    e.close();
    fac.close();
    }

    @java.lang.Override
    public void closeReadableLibrary() {
    e.close();
    fac.close();
    }

    @java.lang.Override
    public void closeWritablePlaylist() {

    }

    @java.lang.Override
    public void closeReadablePlaylist() {

    }
}
