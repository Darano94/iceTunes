package server.classes;

import javafx.scene.control.ListView;
import server.interfaces.Controller;
import server.interfaces.RemoteInterface;
import server.mvc.Model;
import server.mvc.View;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {
    Model model;
    String path;
    private View view;
    private server.mvc.Controller controller;

    @Override
    public void link(Model model, View view, server.mvc.Controller contr) throws RemoteException {
        this.model = model;
        this.controller = contr;
        this.view = view;
        view.addController(controller);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public Controller getController() throws RemoteException {
        return this.controller;
    }

    @Override
    public void loadbtn(View view) throws RemoteException {
        controller.loadbtn(view);
    }

    @Override
    public Playlist getPlaylist(Model model) throws RemoteException {
        return model.getPlaylist();
    }

    @Override
    public void savebtn(View view) throws RemoteException {
        controller.savebtn(view);
    }

    @Override
    public void addallbtn(View view) throws RemoteException {
        controller.addallbtn(view);
    }

    @Override
    public void addtoplaylistbtn(View view) throws RemoteException {
        controller.addtoplaylistbtn(view);
    }

    @Override
    public void nextbtn(View view) throws RemoteException {
        controller.nextbtn(view);
    }

    @Override
    public void backbtn(View view) throws RemoteException {
        controller.backbtn(view);
    }

    @Override
    public void deleteplaylist(View view) throws RemoteException {
        controller.deleteplaylist(view);
    }

    @Override
    public void commitbtn(View view) throws RemoteException {
        controller.commitbtn(view);
    }

    @Override
    public void loadlib(String path, Playlist allsongs, View view) throws RemoteException, IDOverFlowException {
        this.path = path;
        controller.loadlib(path, allsongs, view);
    }


    @Override
    public void playpauseSong(View view) throws RemoteException {
        controller.playpauseSong(view);
    }

    @Override
    public void playSong(Song s, View view) throws RemoteException {
        controller.playSong(s, view);
    }

    @Override
    public void stopsong(View view) throws RemoteException {
        controller.stopsong(view);
    }

    @Override
    public void pauseSong(Song s) throws RemoteException {
        controller.pauseSong(s);
    }

    @Override
    public ListView<Song> loadSonglist() {
        return view.getSongListView();
    }

    @Override
    public ListView<Song> loadPlaylist() {
        return view.getPlaylistView();
    }


    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Model getModel() {
        return this.model;
    }

    public RemoteObject() throws RemoteException {
        super();
    }
}
