package client.classes;


import client.interfaces.SerializableStrategy;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class XMLStrategy implements SerializableStrategy {

    private FileOutputStream fos = null;
    private XMLEncoder encoder;
    private XMLDecoder decoder;
    private FileInputStream fis = null;
    private Playlist playlistReader = null;
    private Song songReader = null;

    @Override
    public void openWritableLibrary() throws IOException {
        fos = new FileOutputStream("lib.xml");
             encoder = new XMLEncoder(fos);
        }



    @Override
    public void openReadableLibrary() throws IOException {
        fis = new FileInputStream("lib.xml");
         decoder = new XMLDecoder(fis);
    }

    @Override
    public void openWritablePlaylist() throws IOException {
        fos = new FileOutputStream("playlist.xml");
             encoder = new XMLEncoder(fos);
    }

    @Override
    public void openReadablePlaylist() throws IOException {
        fis  = new FileInputStream("playlist.xml");
              decoder = new XMLDecoder(fis);
    }

    @Override
    public void writeSong(Song s) throws IOException {
        fos = new FileOutputStream("song.xml");
        encoder = new XMLEncoder(fos);
            encoder.writeObject(s); //??
            encoder.flush();

    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException {
        fis = new FileInputStream("song.xml");
              decoder = new XMLDecoder(fis);
            songReader = new Song();
            songReader = (Song) decoder.readObject();
        return songReader;
}
    @Override
    public void writeLibrary(Playlist p) throws IOException {

        encoder.writeObject(p);
        encoder.flush();
    }

    @Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {

        playlistReader = new Playlist();
        playlistReader = (Playlist) decoder.readObject();
        return playlistReader;
    }

    @Override
    public void writePlaylist(Playlist p) throws IOException {

        encoder.writeObject(p);
        encoder.flush();
    }

    @Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {

        playlistReader = (Playlist) decoder.readObject();
        return playlistReader;
    }

    @Override
    public void closeWritableLibrary() {

        try {
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadableLibrary() {

        try {
            decoder.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeWritablePlaylist() {

        try {
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadablePlaylist() {

        try {
            decoder.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

