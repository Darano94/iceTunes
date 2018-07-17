package client.interfaces;


import client.classes.IDOverFlowException;
import client.classes.Playlist;
import client.classes.Song;
import client.mvc.Model;
import client.mvc.View;

import java.rmi.RemoteException;

public interface Controller {
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

    void playSong(Song s, View view);

    void stopsong(View view);

    void pauseSong(Song s);
}
