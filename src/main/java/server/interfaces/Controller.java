package server.interfaces;


import server.classes.IDOverFlowException;
import server.classes.Playlist;
import server.classes.Song;
import server.mvc.*;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Controller extends Serializable {
    void link(Model model, View view);

    void loadbtn(View view);

    void savebtn(View view);

    void addallbtn(View view);

    void addtoplaylistbtn(View view);

    void nextbtn(View view);

    void backbtn(View view);

    void deleteplaylist(View view);

    void commitbtn(View view);

    void loadlib(String path, Playlist allsongs, View view) throws RemoteException, IDOverFlowException;

    void loadPlaylist(Playlist playlist) throws RemoteException;

    void playpauseSong(View view);

    void playSong(long id, View view);

    void stopsong(View view);

    void pauseSong(server.classes.Song s);
}
