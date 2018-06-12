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

    private mvc.Model model;
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
    public void link( mvc.Model model, mvc.View view) {
        this.model = model;
        view.addController(this);
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

    public void addallbtn(mvc.View view) {
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
            updatePlaylistView(view);

    }




    // TODO: 05.06.2018 model.updateplaylistview un LibviewUpdate auslagern -> ggf unnötig
    @Override
    public void addtoplaylistbtn(mvc.View view) {

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
            
            updateLibView(view);
            updatePlaylistView(view);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void nextbtn(mvc.View view) {
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
    public void playpauseSong(mvc.View view) {
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
    public void backbtn(mvc.View view) {
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
    public void deleteplaylist(mvc.View view) {
        model.getPlaylist().clearPlaylist();
        updatePlaylistView(view);
    }

    // TODO: 05.06.2018 updateviews weg 
    @Override
    public void commitbtn(mvc.View view) {
        String title = view.getTxtTitle().getText();
        String interpret = view.getTxtInterpret().getText();
        String album = view.getTxtAlbum().getText();
        tmp.setTitle(title);
        tmp.setInterpret(interpret);
        tmp.setAlbum(album);

        updateLibView(view);
        updatePlaylistView(view);
        updateLibView(view);
    }

    // TODO: 05.06.2018
    @Override
    public void updateLibView(mvc.View view) {
        updateLibView(view);
    }

    // TODO: 05.06.2018
    @Override
    public void loadlib(String path, Playlist allsongs, mvc.View view) throws RemoteException {
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
    public void updatePlaylistView(mvc.View view, interfaces.Song song) {
        updatePlaylistView(view);
    }

    @Override
    public void stopsong(mvc.View view) {
        player.stop();
        isplaying = false;
        view.setbtnplaypause("|>");
        currentduration = null;
    }

    @Override
    public void playSong(Song s, mvc.View view) {
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
                    player.stop();
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
    public void updatePlaylistView(mvc.View view) {

        // TODO: 20.05.18 nur wenn song noch nicht vorhanden ist!
        view.getPlaylistView().setCellFactory(new javafx.util.Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> param) {
                ListCell<Song> cell = new ListCell<Song>() {
                    @Override
                    protected void updateItem(Song tmp, boolean bln) {
                        super.updateItem(tmp, bln);
                        if (tmp != null) {
                            String tmps = tmp.titleProperty().getValue();
                            tmps.replace(".mp3", "");
                            setText(tmps);
                            setId(tmp.getTitle());
                        }
                    }
                };
                cell.setOnMouseClicked((MouseEvent event) -> {
                    if (cell.isEmpty()) {
                        event.consume();
                    } else {
                        titleCell = cell.getText();
                        idCell = cell.getId();
                        initClickedSong(view, model.getPlaylist());
                        view.setTxtTitle(tmp.getTitle());

                    }
                });
                return cell;

            }
        });


        view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());

        updateLibView(view);

    }
    @Override
    public void pauseSong(Song s) {
        player.pause();
        currentduration = player.getCurrentTime();
        isplaying = false;
        ishalted = true;
    }




    public void addAllSongs(mvc.View view) throws Exception {

    }

    public void addtoplaylist(mvc.View view) throws IOException, ClassNotFoundException {


    }




    public void updateLibView(mvc.View view) {
        tmp = new Song();
        view.getSongListView().setCellFactory(new javafx.util.Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> param) {
                ListCell<Song> cell = new ListCell<Song>() {
                    @Override
                    protected void updateItem(Song s, boolean bln) {
                        super.updateItem(s, bln);
                        if (s != null) {
                            String tmps = s.getTitle();
                            tmps.replace(".mp3", "");
                            setText(tmps);
                            setId(s.getTitle());
                        }
                    }
                };
                //set for each sell a MouseEvent
                cell.setOnMouseClicked((MouseEvent event) -> {
                    if (cell.isEmpty()) {
                        event.consume();
                    } else {
                        titleCell = cell.getText();
                        idCell = cell.getId();
                        initClickedSong(view, model.getAllSongs());
                        view.setTxtTitle(tmp.getTitle());
                    }
                });
                return cell;
            }
        });
        view.getSongListView().setItems((ModifiableObservableListBase) model.getAllSongs());

    }


    public void initClickedSong(mvc.View view, Playlist playlistLib) {
        //get the Song from allSongs which is represented by the clicked cell
        for (int i = 0; i < playlistLib.size(); i++) {
            tmp = (Song) playlistLib.get(i);
            if (tmp.getTitle().contains(idCell)) {
                view.setTxtAlbum(tmp.getAlbum());
                view.setTxtInterpret(tmp.getInterpret());
                return;
            }
        }
    }

}




