package server.interfaces;

import server.classes.IDOverFlowException;
import server.classes.Playlist;
import server.classes.Playlist;
import server.mvc.Model;
import server.classes.Song;
import server.mvc.View;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    void link(Model model, View view) throws RemoteException;

    void loadbtn(View view)throws RemoteException;

    void savebtn(View view) throws RemoteException;

    void addallbtn(View view) throws RemoteException;

    void addtoplaylistbtn(View view) throws RemoteException;

    void nextbtn(View view) throws RemoteException;

    void backbtn(View view) throws RemoteException;

    void deleteplaylist(View view) throws RemoteException;

    void commitbtn(View view) throws RemoteException;

    void loadlib(String path, Playlist allsongs, View view) throws RemoteException, IDOverFlowException;

    void loadPlaylist(Playlist playlist) throws RemoteException;

    void playpauseSong(View view) throws RemoteException;

    void playSong(Song s, View view) throws RemoteException;

    void stopsong(View view) throws RemoteException;

    void pauseSong(server.classes.Song s) throws RemoteException;
}
