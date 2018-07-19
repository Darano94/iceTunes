package server.interfaces;

import javafx.scene.control.ListView;
import server.classes.IDOverFlowException;
import server.classes.Playlist;
import server.classes.Playlist;
import server.mvc.Model;
import server.classes.Song;
import server.mvc.View;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteInterface extends Remote {
    void link(Model model) throws RemoteException;

    Playlist getAllSongs() throws RemoteException;

    void loadbtn()throws RemoteException;

    void savebtn() throws RemoteException;

    void addallbtn() throws RemoteException;

    void addtoplaylistbtn() throws RemoteException;

    void nextbtn() throws RemoteException;

    void backbtn() throws RemoteException;

    void deleteplaylist() throws RemoteException;

    void commitbtn() throws RemoteException;

    void loadlib(String path, Playlist allsongs) throws RemoteException, IDOverFlowException;

    void loadPlaylist(Playlist playlist) throws RemoteException;

    void playpauseSong() throws RemoteException;

    void playSong(Song s) throws RemoteException;

    void stopsong() throws RemoteException;

    void pauseSong(server.classes.Song s) throws RemoteException;

    void setS(Song s) throws RemoteException;

}
