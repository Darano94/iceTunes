package client.mvc;

import server.classes.Playlist;
import server.classes.Song;
import server.interfaces.RemoteInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

    private RemoteInterface remote = (RemoteInterface) Naming.lookup("//localhost:1099/name");


    private Song s;

    public Controller() throws RemoteException, NotBoundException, MalformedURLException {
    }
    public void setS(Song s) {
        this.s = s;
    }
    public void loadlib(View view) throws RemoteException {
        Playlist p1 = new Playlist();

        for(int i = 0; i <= remote.getAllSongs().size(); i++){
            Song tmp = remote.getAllSongs().findSongByID(i);
            p1.addSong(tmp);
        }

        view.getSongListView().setItems(p1);
    }

    public void time ( View view) throws Exception{

        Date Time = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("00:mm:ss");
        System.out.println("Abspielzeit"+simpleDateFormat.format(Time));

    }


}
