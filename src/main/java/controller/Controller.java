package mvc;

import classes.Playlist;
import classes.SerializableStrategy;
import classes.Song;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ModifiableObservableListBase;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

public class Controller implements interfaces.Controller {

    // Variablen

    private Model model;
    private Media file;
    private MediaPlayer player;

    private Boolean isplaying = false;
    private Boolean ishalted = false;

    private long counter = -1;

    private Duration fullduration;
    private Duration currentduration;

    private Song tmp;
    private Song s;
    private Song startSong;

    private File f;
    private File startFile;
    private File[] paths;
    private File songFile;

    public String titleCell, idCell;

    //Methoden

    @Override
    public void link(Model model, View view) {
        this.model = model;
        view.addContoller(this);
    }

    @Override
    public void loadbtn() {
        System.out.println(model.getPlaylist().size());
    }

    @Override
    public void savebtn() {
        System.out.println("Speichern");
    }

    // TODO: 05.06.2018 model.updateplaylistview auslagern -> ggf unnötig 
    @Override
    public void addallbtn(View view) {
        SerializableStrategy strat = new SerializableStrategy();

        try {
            strat.openWritableLibrary();
            strat.writeLibrary(model.getAllSongs());
            strat.closeWritableLibrary();
            strat.openReadableLibrary();
            model.setPlaylist((Playlist) strat.readLibrary());
            strat.closeReadableLibrary();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
            model.updatePlaylistView(view);

    }
    // TODO: 05.06.2018 model.updateplaylistview un LibviewUpdate auslagern -> ggf unnötig
    @Override
    public void addtoplaylistbtn(View view) {

        try {
            SerializableStrategy strat = new SerializableStrategy();

            initClickedSong(view, model.getAllSongs());


            strat.writeSong(tmp);

            Song s = (Song) strat.readSong();
            s.setId(counter);

            model.getPlaylist().addSong(s);

            strat.openWritablePlaylist();
            strat.writePlaylist(model.getPlaylist()); //playlist in datei schreiben und somit abspeichern
            strat.closeWritablePlaylist();

            strat.openReadablePlaylist();
            model.setPlaylist((Playlist) strat.readPlaylist());
            strat.closeReadablePlaylist();


            System.out.println(model.getPlaylist().size());
            counter--;
            
            model.updateLibView(view);
            model.updatePlaylistView(view);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void nextbtn(View view) {
        if (tmp.getId() < 0) { //wenn tmp ids negativ ist (also in playlist geeklickzt wure
            if (tmp.getId() - 1 >= -model.getPlaylist().size()) { //solange es noch nächsten song gibt
                playSong((Song) model.getPlaylist().findSongByID(tmp.getId() - 1), view); //nächsten Song abpielen
                tmp = (Song) model.getPlaylist().findSongByID(tmp.getId() - 1); //tmp = aktueller songs setzen, damit wir immer next drücken könnten

            }
        }
        if (tmp.getId() > 0) {
            if (tmp.getId() + 1 <= model.getAllSongs().size()) {
                playSong((Song) model.getAllSongs().findSongByID(tmp.getId() + 1), view);
                tmp = (Song) model.getAllSongs().findSongByID(tmp.getId() + 1);
            }
        }
        view.setbtnplaypause("||");
        currentduration = null;
    }

    @Override
    public void playpauseSong(View view) {
        if (isplaying == false) {
            playSong(tmp, view);
            view.setbtnplaypause("||");
            isplaying = true;
        } else if (isplaying == true) {
            pauseSong(tmp);
            view.setbtnplaypause("|>");
            isplaying = false;
        }
    }

    @Override
    public void loadPlaylist(Playlist playlist) throws RemoteException {
        model.setPlaylist(playlist);
    }

    // TODO: 05.06.2018 unnötig wegen getSelectionmodel oder so 
    @Override
    public void initClickedSong(View view, Playlist playlistLib) {
        model.initClickedSong(view, playlistLib);
    }

    @Override
    public void backbtn(View view) {
        if (tmp.getId() < 0) {
            if (tmp.getId() + 1 < 0) { //solange es noch nächsten song gibt
                playSong((Song) model.getPlaylist().findSongByID(tmp.getId() + 1), view);
                tmp = (Song) model.getPlaylist().findSongByID(tmp.getId() + 1);
            }
        }
        if (tmp.getId() > 0) {
            if (tmp.getId() - 1 > 0) {
                playSong((Song)model.getAllSongs().findSongByID(tmp.getId() - 1), view);
                tmp = (Song)model.getAllSongs().findSongByID(tmp.getId() - 1);
            }
        }
        currentduration = null;
    }

    // TODO: 05.06.2018 updatePlaylistview weg 

    @Override
    public void deleteplaylist(View view) {
        model.getPlaylist().clearPlaylist();
        model.updatePlaylistView(view);
    }

    // TODO: 05.06.2018 updateviews weg 
    @Override
    public void commitbtn(View view) {
        String title = view.getTxtTitle().getText();
        String interpret = view.getTxtInterpret().getText();
        String album = view.getTxtAlbum().getText();
        tmp.setTitle(title);
        tmp.setInterpret(interpret);
        tmp.setAlbum(album);

        updateLibView(view);
        model.updatePlaylistView(view);
        model.updateLibView(view);
    }

    // TODO: 05.06.2018
    @Override
    public void updateLibView(View view) {
        model.updateLibView(view);
    }

    // TODO: 05.06.2018
    @Override
    public void loadlib(String path, Playlist allsongs, View view) throws RemoteException {
        model.setAllSongs(new Playlist());
        f = new File(path);
        int counter = 0;
        paths = f.listFiles();
        for (File file : paths) {
            if (file.getPath().endsWith(".mp3")) {
                counter++;
                s = new Song();
                s.setTitle(file.getName().replace(".mp3", ""));
                s.setAlbum("");
                s.setPath(file.getPath());
                s.setInterpret("");
                s.setId(counter);
                model.getAllSongs().addSong(s);
            }
        }
        //play sound bei programmstart
        startFile = new File("res/iceicebaby.mp3");
        startSong = new Song();
        startSong.setPath(startFile.getPath());
        playSong(startSong, view);


        model.updateLibView(view);
        model.updatePlaylistView(view);
    }

    // TODO: 05.06.2018
    @Override
    public void updatePlaylistView(View view, interfaces.Song song) {
        model.updatePlaylistView(view);
    }

    @Override
    public void stopsong(View view) {
        player.stop();
        isplaying = false;
        view.setbtnplaypause("|>");
        currentduration = null;
    }

    @Override
    public void playSong(Song s, View view) {
        songFile = new File(s.pathProperty().getValue());
        file = new Media(songFile.toURI().toString());
        player = new MediaPlayer(file);
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                nextbtn(view);
                view.setbtnplaypause("|>");
            }
        });

        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                if (ishalted == true) {
                    player.setStartTime(currentduration);
                    player.play();


                } else {
                    player.play();
                }
                player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                        fullduration = player.getMedia().getDuration();
                        view.getSlider().setValue(newValue.divide(fullduration.toMillis()).toMillis() * 100.0);
                        view.setLblcurrentduration(String.valueOf(newValue.toSeconds()));
                        view.setLblfinalduration(String.valueOf("/   " + player.getTotalDuration().toSeconds()));
                    }
                });
            }
        });


    }

    @Override
    public void pauseSong(Song s) {
        player.pause();
        currentduration = player.getCurrentTime();
        isplaying = false;
        ishalted = true;
    }
}

