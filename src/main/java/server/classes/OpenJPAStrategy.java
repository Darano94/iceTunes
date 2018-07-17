package server.classes;

import server.interfaces.Playlist;
import server.interfaces.SerializableStrategy;
import server.interfaces.Song;
import org.apache.openjpa.util.Serialization;


import org.apache.openjpa.persistence.OpenJPAPersistence;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;

import javax.persistence.EntityTransaction;

import javax.persistence.Persistence;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;


import javax.persistence.*;
import java.io.IOException;
import java.sql.SQLException;

public class OpenJPAStrategy implements SerializableStrategy {
    EntityManagerFactory fac;
    //EntityManager erstellen
    EntityManager e;
    //Entity Transaction bekommen
    EntityTransaction t ;


    public static EntityManagerFactory getWithoutConfig() {
        Map<String, String> map = new HashMap<String, String>();


        map.put("openjpa.ConnectionURL","jdbc:sqlite:lib.db");

        map.put("openjpa.ConnectionDriverName", "org.sqlite.JDBC");

        map.put("openjpa.RuntimeUnenhancedClasses", "supported");

        map.put("openjpa.jdbc.SynchronizeMappings", "false");


        // find all classes to registrate them

        List<Class<?>> types = new ArrayList<Class<?>>();

        types.add(Song.class);


        if (!types.isEmpty()) {

            StringBuffer buf = new StringBuffer();

            for (Class<?> c : types) {

                if (buf.length() > 0)
                    buf.append(";");

                buf.append(c.getName());

            }


            map.put("openjpa.MetaDataFactory", "jpa(Types=" + buf.toString()+ ")");

        }


        return OpenJPAPersistence.getEntityManagerFactory(map);

    }

    @Override
    public void openWritableLibrary() throws SQLException, IOException {
        //fac = Persistence.createEntityManagerFactory("openjpa");
        fac = getWithoutConfig();
        e = fac.createEntityManager();
    }

    @Override
    public void openReadableLibrary() throws SQLException, IOException {
        //fac = Persistence.createEntityManagerFactory("openjpa");
        fac = getWithoutConfig();
        e = fac.createEntityManager();
    }

    @Override
    public void openWritablePlaylist() throws SQLException, IOException {

    }

    @Override
    public void openReadablePlaylist() throws IOException, SQLException {

    }

    @Override
    public void writeSong(server.classes.Song s) throws IOException {
        //fac = Persistence.createEntityManagerFactory("openjpa");

        t = e.getTransaction();
        t.begin();
        e.persist(s);
        t.commit();
    }

    @Override
    public server.classes.Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException {
        t = e.getTransaction();
        t.begin();
        String aa = "SELECT id, title, interpret, album, path FROM lib";
        server.classes.Song a = null;
        for (Object s : e.createQuery(aa.toString()).getResultList()) {
            a = (server.classes.Song) s;

        }

        t.commit();
        return a;
    }

    @Override
    public void writeLibrary(server.classes.Playlist p) throws IOException {
        //fac = Persistence.createEntityManagerFactory("openjpa");
        t = e.getTransaction();
        t.begin();
        e.persist(p);
        t.commit();
    }

    @Override
    public server.classes.Playlist readLibrary() throws IOException, ClassNotFoundException {

        t= e.getTransaction();
        t.begin();
        String aa = "SELECT x FROM Playlist x";
        server.classes.Playlist a = null;
        for (Object s : e.createQuery(aa.toString()).getResultList()) {
            a = (server.classes.Playlist)s;
        }

        t.commit();
        return a;
    }

    @Override
    public void writePlaylist(server.classes.Playlist p) throws IOException {
        writeLibrary(p);
    }

    @Override
    public server.classes.Playlist readPlaylist() throws IOException, ClassNotFoundException {
        return readLibrary();
    }

    @Override
    public void closeWritableLibrary() {
        e.close();
        fac.close();
    }

    @Override
    public void closeReadableLibrary() {
        e.close();
        fac.close();
    }

    @Override
    public void closeWritablePlaylist() {

    }

    @Override
    public void closeReadablePlaylist() {

    }
}
