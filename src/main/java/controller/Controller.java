package controller;

import classes.*;
import javafx.collections.ModifiableObservableListBase;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Model;
import view.View;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

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

    private Song s;
    private Song startSong;

    private File f;
    private File startFile;
    private File[] paths;
    private File songFile;

    private String strat;


    //Methoden

    @Override
    public void link(Model model, View view) {
        this.model = model;
        view.addController(this);
    }

    @Override
    public void loadbtn(View view) {
        strat = view.getCboxstrat();
        switch (strat){
            case "binary":
                BinaryStrategy strategy = new BinaryStrategy();
                try {
                    strategy.openReadablePlaylist();
                    model.setPlaylist((Playlist) strategy.readPlaylist());
                    strategy.closeReadablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());
                view.getPlaylistView().setCellFactory(new javafx.util.Callback<>() {
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
                        cell.setOnMouseClicked((MouseEvent event) -> {
                            if (cell.isEmpty()) {
                                event.consume();
                            } else {
                                s = cell.getItem();
                                setS(s);
                                view.setTxtTitle(s.getTitle());
                                view.setTxtAlbum(s.getAlbum());
                                view.setTxtInterpret(s.getInterpret());
                            }
                        });
                        return cell;

                    }
                });

                break;

            case "XML":
                XMLStrategy strategy1 = new XMLStrategy();
                try {
                    strategy1.openReadablePlaylist();
                    model.setPlaylist((Playlist) strategy1.readPlaylist());
                    strategy1.closeReadablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());
                view.getPlaylistView().setCellFactory(new javafx.util.Callback<>() {
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
                        cell.setOnMouseClicked((MouseEvent event) -> {
                            if (cell.isEmpty()) {
                                event.consume();
                            } else {
                                s = cell.getItem();
                                setS(s);
                                view.setTxtTitle(s.getTitle());
                                view.setTxtAlbum(s.getAlbum());
                                view.setTxtInterpret(s.getInterpret());
                            }
                        });
                        return cell;

                    }
                });
                break;

            case "JDBC/SQL":
                JDBCStrategy strategy2 = new JDBCStrategy();
                try {
                    strategy2.openReadablePlaylist();
                    model.setPlaylist((Playlist) strategy2.readPlaylist());
                    strategy2.closeReadablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());
                view.getPlaylistView().setCellFactory(new javafx.util.Callback<>() {
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
                        cell.setOnMouseClicked((MouseEvent event) -> {
                            if (cell.isEmpty()) {
                                event.consume();
                            } else {
                                s = cell.getItem();
                                setS(s);
                                view.setTxtTitle(s.getTitle());
                                view.setTxtAlbum(s.getAlbum());
                                view.setTxtInterpret(s.getInterpret());
                            }
                        });
                        return cell;

                    }
                });
                break;

            case "OpenJPA":
                OpenJPAStrategy strategy3 = new OpenJPAStrategy();
                try {
                    strategy3.openReadablePlaylist();
                    model.setPlaylist((Playlist) strategy3.readPlaylist());
                    strategy3.closeReadablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());
                view.getPlaylistView().setCellFactory(new javafx.util.Callback<>() {
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
                        cell.setOnMouseClicked((MouseEvent event) -> {
                            if (cell.isEmpty()) {
                                event.consume();
                            } else {
                                s = cell.getItem();
                                setS(s);
                                view.setTxtTitle(s.getTitle());
                                view.setTxtAlbum(s.getAlbum());
                                view.setTxtInterpret(s.getInterpret());
                            }
                        });
                        return cell;

                    }
                });
                break;
        }

    }

    @Override
    public void savebtn(View view) {
        strat = view.getCboxstrat();
        switch (strat){
            case "binary":
               BinaryStrategy strategy = new BinaryStrategy();
                try {
                    strategy.openWritablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    strategy.writePlaylist(model.getPlaylist());
                    strategy.closeWritablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "XML":
                XMLStrategy strategy1 = new XMLStrategy();
                try {
                    strategy1.openWritablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    strategy1.writePlaylist(model.getPlaylist());
                    strategy1.closeWritablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case "JDBC/SQL":
                JDBCStrategy strategy2 = new JDBCStrategy();
                try {
                    strategy2.openWritablePlaylist();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    strategy2.writePlaylist(model.getPlaylist());
                    strategy2.closeWritablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "OpenJPA":
                OpenJPAStrategy strategy3 = new OpenJPAStrategy();
                try {
                    strategy3.openWritablePlaylist();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    strategy3.writePlaylist(model.getPlaylist());
                    strategy3.closeWritablePlaylist();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }


//        JDBCStrategy strat = new JDBCStrategy();
//        try {
//            strat.openWritablePlaylist();
//            strat.writePlaylist(model.getPlaylist());
//            strat.closeWritablePlaylist();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


    public void addallbtn(View view) {
        model.setPlaylist(model.getAllSongs());
        view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());
        view.getPlaylistView().setCellFactory(new javafx.util.Callback<>() {
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
                cell.setOnMouseClicked((MouseEvent event) -> {
                    if (cell.isEmpty()) {
                        event.consume();
                    } else {
                        s = cell.getItem();
                        setS(s);
                        view.setTxtTitle(s.getTitle());
                        view.setTxtAlbum(s.getAlbum());
                        view.setTxtInterpret(s.getInterpret());
                    }
                });
                return cell;

            }
        });
    }


    @Override
    public void addtoplaylistbtn(View view) {

        try {

            s.setId(counter);
            long id = s.getId();
            Song result = (Song)model.getAllSongs().findSongByID(id);


            model.getPlaylist().addSong(result);



            counter--;

        } catch (Exception e) {
            e.printStackTrace();
        }
        view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());
        view.getPlaylistView().setCellFactory(new javafx.util.Callback<>() {
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
                cell.setOnMouseClicked((MouseEvent event) -> {
                    if (cell.isEmpty()) {
                        event.consume();
                    } else {
                        s = cell.getItem();
                        setS(s);
                        view.setTxtTitle(s.getTitle());
                        view.setTxtAlbum(s.getAlbum());
                        view.setTxtInterpret(s.getInterpret());
                    }
                });
                return cell;

            }
        });
    }

    @Override
    public void nextbtn(View view) {
        if (s.getId() < 0) { //wenn s ids negativ ist (also in playlist geeklickzt wure
            if (s.getId() - 1 >= -model.getPlaylist().size()) { //solange es noch nächsten song gibt
                playSong((Song) model.getPlaylist().findSongByID(s.getId() - 1), view); //nächsten Song abpielen
                s = (Song) model.getPlaylist().findSongByID(s.getId() - 1); //s = aktueller songs setzen, damit wir immer next drücken könnten

            }
        }
        if (s.getId() > 0) {
            if (s.getId() + 1 <= model.getAllSongs().size()) {
                playSong((Song) model.getAllSongs().findSongByID(s.getId() + 1), view);
                s = (Song) model.getAllSongs().findSongByID(s.getId() + 1);
            }
        }
        view.setbtnplaypause("||");
        currentduration = null;
        view.setTxtAlbum(s.getAlbum());
        view.setTxtInterpret(s.getInterpret());
        view.setTxtTitle(s.getTitle());
    }

    @Override
    public void playpauseSong(View view) {
        if (isplaying == false) {
            playSong(s, view);
            view.setbtnplaypause("||");
            isplaying = true;
        } else if (isplaying == true) {
            pauseSong(s);
            view.setbtnplaypause("|>");
            isplaying = false;
        }
    }

    @Override
    public void loadPlaylist(Playlist playlist) throws RemoteException {
        model.setPlaylist(playlist);
    }

    @Override
    public void backbtn(View view) {
        if (s.getId() < 0) {
            if (s.getId() + 1 < 0) { //solange es noch nächsten song gibt
                playSong((Song) model.getPlaylist().findSongByID(s.getId() + 1), view);
                s = (Song) model.getPlaylist().findSongByID(s.getId() + 1);
            }
        }
        if (s.getId() > 0) {
            if (s.getId() - 1 > 0) {
                playSong((Song) model.getAllSongs().findSongByID(s.getId() - 1), view);
                s = (Song) model.getAllSongs().findSongByID(s.getId() - 1);
            }
        }
        currentduration = null;
        view.setTxtAlbum(s.getAlbum());
        view.setTxtInterpret(s.getInterpret());
        view.setTxtTitle(s.getTitle());
    }

    @Override
    public void deleteplaylist(View view) {
        model.getPlaylist().clearPlaylist();
        view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());
        view.getPlaylistView().setCellFactory(new javafx.util.Callback<>() {
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
                cell.setOnMouseClicked((MouseEvent event) -> {
                    if (cell.isEmpty()) {
                        event.consume();
                    } else {
                        s = cell.getItem();
                        setS(s);
                        view.setTxtTitle(s.getTitle());
                        view.setTxtAlbum(s.getAlbum());
                        view.setTxtInterpret(s.getInterpret());
                    }
                });
                return cell;

            }
        });
    }

    @Override
    public void commitbtn(View view) {
        String title = view.getTxtTitle().getText();
        String interpret = view.getTxtInterpret().getText();
        String album = view.getTxtAlbum().getText();
        s.setTitle(title);
        s.setInterpret(interpret);
        s.setAlbum(album);
        view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());
        view.getPlaylistView().refresh();
        view.getPlaylistView().setCellFactory(new javafx.util.Callback<>() {
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
                cell.setOnMouseClicked((MouseEvent event) -> {
                    if (cell.isEmpty()) {
                        event.consume();
                    } else {
                        s = cell.getItem();
                        setS(s);
                        view.setTxtTitle(s.getTitle());
                        view.setTxtAlbum(s.getAlbum());
                        view.setTxtInterpret(s.getInterpret());
                    }
                });
                return cell;

            }
        });

    }

    @Override
    public void loadlib(String path, Playlist allsongs, View view) throws RemoteException, IDOverFlowException {
        model.setAllSongs(new Playlist());
        f = new File(path);
        paths = f.listFiles();
        for (File file : paths) {
            if (file.getPath().endsWith(".mp3")) {
                s = new Song();
                s.setTitle(file.getName().replace(".mp3", ""));
                s.setAlbum("");
                s.setPath(file.getPath());
                s.setInterpret("");
                model.getAllSongs().addSong(s);
            }
        }
        //play sound bei programmstart
//        startFile = new File("res/iceicebaby.mp3");
//        startSong = new Song();
//        startSong.setPath(startFile.getPath());
//        playSong(startSong, view);
        view.getSongListView().setItems((ModifiableObservableListBase) model.getAllSongs());
        view.getPlaylistView().setItems((ModifiableObservableListBase) model.getPlaylist());

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
        player.setOnEndOfMedia(() -> {
            nextbtn(view);
            view.setbtnplaypause("|>");
        });

        player.setOnReady(() -> {
            if (ishalted == true) {
                player.setStartTime(currentduration);
                player.play();


            } else {
                player.stop();
                player.play();
            }
            player.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                fullduration = player.getMedia().getDuration();
                view.getSlider().setValue(newValue.divide(fullduration.toMillis()).toMillis() * 100.0);
                view.setLblcurrentduration(String.valueOf(newValue.toSeconds()));
                view.setLblfinalduration(String.valueOf("/   " + player.getTotalDuration().toSeconds()));
            });
        });


    }

    @Override
    public void pauseSong(Song s) {
        player.pause();
        currentduration = player.getCurrentTime();
        isplaying = false;
        ishalted = true;
    }

    public void setS(Song s) {
        this.s = s;
    }

}