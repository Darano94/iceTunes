package server.classes;

import javafx.scene.control.ListView;
import server.interfaces.RemoteInterface;
import server.mvc.Controller;
import server.mvc.Model;
import server.mvc.View;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {

    Controller contr;
    View view;

    @Override
    public void link(Model model) throws RemoteException {
        contr.link(model, view);
    }

    @Override
    public Playlist getAllSongs() throws RemoteException {
        return contr.getModel().getAllSongs();
    }

    @Override
    public void loadbtn() throws RemoteException {
        contr.loadbtn(view);
    }

    @Override
    public void savebtn() throws RemoteException {
        contr.savebtn(view);
    }

    @Override
    public void addallbtn() throws RemoteException {
        contr.addallbtn(view);
    }

    @Override
    public void addtoplaylistbtn() throws RemoteException {
        contr.addtoplaylistbtn(view);
    }

    @Override
    public void nextbtn() throws RemoteException {
        contr.nextbtn(view);
    }

    @Override
    public void backbtn() throws RemoteException {
        contr.backbtn(view);
    }

    @Override
    public void deleteplaylist() throws RemoteException {
        contr.deleteplaylist(view);
    }

    @Override
    public void commitbtn() throws RemoteException {
        contr.commitbtn(view);
    }

    @Override
    public void loadlib(String path, Playlist allsongs) throws RemoteException, IDOverFlowException {
    }

    @Override
    public void loadPlaylist(Playlist playlist) throws RemoteException {
    }

    @Override
    public void playpauseSong() throws RemoteException {
        contr.playpauseSong(view);
    }

    @Override
    public void playSong(Song s) throws RemoteException {
        contr.playSong(s.getId(), view);
    }

    @Override
    public void stopsong() throws RemoteException {
        contr.stopsong(view);
    }

    @Override
    public void pauseSong(Song s) throws RemoteException {
        contr.pauseSong(s);

    }

    @Override
    public void setS(Song s) throws RemoteException {
        contr.setS(s);
    }

    public RemoteObject(Controller contr, View view) throws RemoteException {
        this.contr = contr;
        this.view = view;
    }
}
