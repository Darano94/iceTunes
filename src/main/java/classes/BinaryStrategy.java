package classes;

        import interfaces.Playlist;
        import interfaces.Song;

        import java.io.*;

public class BinaryStrategy implements interfaces.SerializableStrategy {
    public static final long serialVersionUID = 4L;

    /*
        Vars
     */
    private FileOutputStream fos = null;
    private ObjectOutputStream oos = null;
    private FileInputStream fis = null;
    private ObjectInputStream ois = null;
    private classes.Playlist playlistReader = null;
    private classes.Song songReader = null;

    /*
        Methods
     */
    @Override
    public void openWritableLibrary() throws IOException {
        fos = new FileOutputStream("lib.ser");
        oos = new ObjectOutputStream(fos);
    }

    @Override
    public void openReadableLibrary() throws IOException {
        fis = new FileInputStream("lib.ser");
        ois = new ObjectInputStream(fis);
    }

    @Override
    public void openWritablePlaylist() throws IOException {
        fos = new FileOutputStream("playlist.ser");
        oos = new ObjectOutputStream(fos);
    }

    @Override
    public void openReadablePlaylist() throws IOException {
        fis = new FileInputStream("playlist.ser");
        ois = new ObjectInputStream(fis);
    }

    @Override
    public void writeSong(Song s) throws IOException {
        fos = new FileOutputStream("song.ser");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(s);
        oos.flush();


    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException, IDOverFlowException {
        fis = new FileInputStream("song.ser");
        ois = new ObjectInputStream(fis);
        songReader = new classes.Song();
        songReader = (classes.Song)ois.readObject();

        return songReader;
    }

    @Override
    public void writeLibrary(Playlist p) throws IOException {
        oos.writeObject(p);
        oos.flush();
    }

    @Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {
        playlistReader = new classes.Playlist();
        playlistReader = (classes.Playlist) ois.readObject();
        return playlistReader;
    }

    @Override
    public void writePlaylist(Playlist p) throws IOException {
        oos.writeObject(p);
        oos.flush();
    }

    @Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {
        playlistReader = (classes.Playlist) ois.readObject();
        return playlistReader;
    }

    @Override
    public void closeWritableLibrary() {
        try {
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadableLibrary() {
        try {
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeWritablePlaylist() {
        try {
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadablePlaylist() {
        try {
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}