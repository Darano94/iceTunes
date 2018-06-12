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
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;


public class Model {

    //Variablen

    private Playlist allSongs;
    private Playlist playlist;

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

    public void initClickedSong(View view, Playlist playlistLib) {
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

    public void loadlib(String path, Playlist allSongs, View view) {

    }

    public void commitbtn(View view) {



    }

    public void updateLibView(View view) {
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
                        initClickedSong(view, getAllSongs());
                        view.setTxtTitle(tmp.getTitle());
                    }
                });
                return cell;
            }
        });
        view.getSongListView().setItems((ModifiableObservableListBase) getAllSongs());

    }

    public void updatePlaylistView(View view) {

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
                        initClickedSong(view, getPlaylist());
                        view.setTxtTitle(tmp.getTitle());

                    }
                });
                return cell;

            }
        });


        view.getPlaylistView().setItems((ModifiableObservableListBase) getPlaylist());

        updateLibView(view);

    }

    public void addAllSongs(View view) throws Exception {

    }

    public void addtoplaylist(View view) throws IOException, ClassNotFoundException {


    }



    public void playpauseSong(View view) {

    }

    public void deleteplaylist(View view) {

    }

    public void nextbtn(View view) {

    }

    public void stopsong(View view) {

    }

    public void backbtn(View view) {

    }

    // Getter Setter

    public Playlist getAllSongs() {
        return this.allSongs;
    }

    public void setAllSongs(Playlist allSongs) {
        this.allSongs = allSongs;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}


