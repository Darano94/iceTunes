package server.interfaces;

import javafx.scene.control.ListView;
import server.classes.IDOverFlowException;
import server.classes.Playlist;
import server.classes.Song;
import server.mvc.Model;
import server.mvc.View;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    void link(Model model, View view, server.mvc.Controller contr) throws RemoteException;
    
    View getView();
    

    Controller getController() throws RemoteException;

    void loadbtn(View view) throws RemoteException;

    server.classes.Playlist getPlaylist(Model model) throws RemoteException;

    void savebtn(View view) throws RemoteException;

    void addallbtn(View view) throws RemoteException;

    void addtoplaylistbtn(View view) throws RemoteException;

    void nextbtn(View view) throws RemoteException;

    void backbtn(View view) throws RemoteException;

    void deleteplaylist(View view) throws RemoteException;

    void commitbtn(View view) throws RemoteException;

    void loadlib(String path, server.classes.Playlist allsongs, View view) throws RemoteException, IDOverFlowException;
    

    void playpauseSong(View view) throws RemoteException;

    void playSong(server.classes.Song s, View view) throws RemoteException;

    void stopsong(View view) throws RemoteException;

    void pauseSong(server.classes.Song s) throws RemoteException;

    ListView<server.classes.Song> loadSonglist() throws RemoteException;

    ListView<Song> loadPlaylist()throws RemoteException;

    String getPath() throws RemoteException;

    Model getModel();
}
