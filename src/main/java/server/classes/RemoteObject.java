package server.classes;

import server.interfaces.RemoteInterface;
import server.mvc.Controller;
import server.mvc.Model;
import client.mvc.View;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {

    Controller contr;

    @Override
    public void link(Model model, View view) throws RemoteException {
        contr.link(model, view);
    }

    @Override
    public void loadbtn(View view) throws RemoteException {
        contr.loadbtn(view);
    }

    @Override
    public void savebtn(View view) throws RemoteException {
        contr.savebtn(view);
    }

    @Override
    public void addallbtn(View view) throws RemoteException {
        contr.addallbtn(view);
    }

    @Override
    public void addtoplaylistbtn(View view) throws RemoteException {
        contr.addtoplaylistbtn(view);
    }

    @Override
    public void nextbtn(View view) throws RemoteException {
        contr.nextbtn(view);
    }

    @Override
    public void backbtn(View view) throws RemoteException {
        contr.backbtn(view);
    }

    @Override
    public void deleteplaylist(View view) throws RemoteException {
        contr.deleteplaylist(view);
    }

    @Override
    public void commitbtn(View view) throws RemoteException {
        contr.commitbtn(view);
    }

    @Override
    public void loadlib(String path, Playlist allsongs, View view) throws RemoteException, IDOverFlowException {
        contr.loadlib(path, allsongs, view);
    }

    @Override
    public void loadPlaylist(Playlist playlist) throws RemoteException {
        contr.loadPlaylist(playlist);
    }

    @Override
    public void playpauseSong(View view) throws RemoteException {
        contr.playpauseSong(view);
    }

    @Override
    public void playSong(Song s, View view) throws RemoteException {
        contr.playSong(s, view);
    }

    @Override
    public void stopsong(View view) throws RemoteException {
        contr.stopsong(view);
    }

    @Override
    public void pauseSong(Song s) throws RemoteException {
        contr.pauseSong(s);
    }

    public RemoteObject(Controller contr) throws RemoteException {
        this.contr = contr;
    }
}
