package view;

import classes.Song;
import controller.Controller;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View extends BorderPane {

    // Variablen

    private Controller controller;

    private ChoiceBox choiceBox = new ChoiceBox();

    private ListView<Song> songListView = new ListView<>();
    private ListView<Song> playlistListView = new ListView<>();

    private Label lblTitle = new Label("Title:");
    private Label lblInterpret = new Label("Interpret:");
    private Label lblAlbum = new Label("Album:");

    public Label getLblcurrentduration() {
        return lblcurrentduration;
    }

    private Label lblcurrentduration = new Label("0:00");

    public Label getLblfinalduration() {
        return lblfinalduration;
    }

    private Label lblfinalduration = new Label("/   "+"0:00");

    private TextField txtTitle = new TextField();
    private TextField txtInterpret = new TextField();
    private TextField txtAlbum = new TextField();

    private Button btnNext = new Button("->");
    private Button btnback = new Button("<-");
    private Button btnCommit = new Button("Commit");
    private Button btnAddToPlaylist = new Button("Add to playlist");
    private Button btndeleteplaylist = new Button("Playlist löschen");
    private Button btnplaypause = new Button ("|>");
    private Button btnstop = new Button("Stop");
    private Button btnLoad = new Button("Load");
    private Button btnSave = new Button("Save");
    private Button btnAddAll = new Button("Add all");

    private Slider slider = new Slider();

    private HBox hBoxRight = new HBox(10, btnback, btnplaypause, btnstop, btnNext, btnCommit);
    private HBox hboxbottom = new HBox(10, btnAddAll, btndeleteplaylist, lblcurrentduration);
    private HBox hBoxTop = new HBox(10,choiceBox, btnLoad, btnSave);
    private HBox hBoxTop2 = new HBox(10,slider, lblcurrentduration, lblfinalduration);
    private HBox hboxbox = new HBox(10, hBoxTop,hBoxTop2);
    private VBox vBoxRight = new VBox(2, lblTitle, txtTitle, lblInterpret, txtInterpret, lblAlbum, txtAlbum);
    private VBox vBoxRight2 = new VBox(5, hBoxRight, btnAddToPlaylist);
    private VBox vBoxRightFull = new VBox(10, vBoxRight, vBoxRight2);


    // Methoden

    public View() {

        // Layout BorderPane

        setTop(hboxbox);
        setLeft(songListView);
        setCenter(playlistListView);
        setBottom(hboxbottom);
        setRight(vBoxRightFull);

        // Button Events

        btnLoad.setOnAction(e -> controller.loadbtn());
        btnSave.setOnAction(e -> controller.savebtn());
        btnAddAll.setOnAction(e -> controller.addallbtn(this));
        btnAddToPlaylist.setOnAction(e -> controller.addtoplaylistbtn(this));
        btnCommit.setOnAction(e -> controller.commitbtn(this));
        btnNext.setOnAction(e -> controller.nextbtn(this));
        btnback.setOnAction(e -> controller.backbtn(this));
        btndeleteplaylist.setOnAction(e -> controller.deleteplaylist(this));
        btnplaypause.setOnAction(e -> controller.playpauseSong(this));
        btnstop.setOnAction(e -> controller.stopsong(this));
    }

    public void addController(Controller contr) {
        this.controller = contr;
    }

    // Getter Setter

    public Slider getSlider() {
        return slider;
    }

    public void setLblcurrentduration(String string){
        lblcurrentduration.setText(string);
    }
    public void setLblfinalduration(String string){
        lblfinalduration.setText(string);
    }

    public void setbtnplaypause (String string){
        btnplaypause.setText(string);
    }

    public TextField getTxtAlbum() {
        return txtAlbum;
    }
    public void setTxtAlbum(String text) {
        this.txtAlbum.setText(text);
    }

    public TextField getTxtTitle() {
        return txtTitle;
    }
    public void setTxtTitle(String text) {
        this.txtTitle.setText(text);
    }

    public TextField getTxtInterpret() {
        return txtInterpret;
    }
    public void setTxtInterpret(String text) {
        this.txtInterpret.setText(text);
    }


    public ListView<Song> getSongListView() {
        return this.songListView;
    }
    public ListView<Song> getPlaylistView() {
        return this.playlistListView;
    }

}

