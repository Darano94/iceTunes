package classes;


import interfaces.Playlist;
import interfaces.Song;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XMLSerialization implements interfaces.SerializableStrategy {

    private FileOutputStream fos = null;
    private FileInputStream fis = null;
    private classes.Playlist playlistReader = null;
    private classes.Song songReader = null;

    @Override
    public void openWritableLibrary() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("lib.ser");
             XMLEncoder encoder = new XMLEncoder(fos)) {
            encoder.writeObject(?);
            encoder.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void openReadableLibrary() throws IOException {
    try (FileInputStream fis = new FileInputStream("lib.ser");
         XMLDecoder decoder = new XMLDecoder(fis)){
            playlistReader = (?) decoder.readObject;
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openWritablePlaylist() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("playlist.ser");
             XMLEncoder encoder = new XMLEncoder(fos)) {
            encoder.writeObject(?);
            encoder.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openReadablePlaylist() throws IOException {
        try ( FileInputStream fis  = new FileInputStream("playlist.ser");
              XMLDecoder decoder = new XMLDecoder(fis)) {
             playlistReader = (classes.Playlist) decoder.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeSong(Song s) throws IOException {
        try ( FileOutputStream fos = new FileOutputStream("song.ser");
              XMLEncoder encoder = new XMLEncoder(fos)) {
            encoder.writeObject(s);
            encoder.flush();
        }catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException {
        try ( FileInputStream fis = new FileInputStream("song.ser");
              XMLDecoder decoder = new XMLDecoder(fis)) {
            songReader = new classes.Song();
            songReader = (classes.Song) decoder.readObject();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return songReader;
}
    @Override
    public void writeLibrary(Playlist p) throws IOException {
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.writeObject(p);
        encoder.flush();
    }

    @Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {
        XMLDecoder decoder = new XMLDecoder(fis);
        playlistReader = new classes.Playlist();
        playlistReader = (classes.Playlist) decoder.readObject();
        return playlistReader;

    }

    @Override
    public void writePlaylist(Playlist p) throws IOException {
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.writeObject(p);
        encoder.flush();
    }

    @Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {
        XMLDecoder decoder = new XMLDecoder(fis);
        playlistReader = (classes.Playlist) decoder.readObject();
        return playlistReader;
    }

    @Override
    public void closeWritableLibrary() {
        XMLEncoder encoder = new XMLEncoder(fos);
        try {
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadableLibrary() {
        XMLDecoder decoder = new XMLDecoder(fis);
        try {
            decoder.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeWritablePlaylist() {
        XMLEncoder encoder = new XMLEncoder(fos);
        try {
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadablePlaylist() {
        XMLDecoder decoder = new XMLDecoder(fis);
        try {
            decoder.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

