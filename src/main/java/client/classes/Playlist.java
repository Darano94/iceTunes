package client.classes;/*
    represents a list of Songs which can be played one after another
    extends ModifiableObservableListBase<server.classes.Song> to be shown on the GUI
 */

import javafx.collections.ModifiableObservableListBase;

import java.util.ArrayList;
import java.util.Iterator;

public class Playlist extends ModifiableObservableListBase<Song> implements client.interfaces.Playlist {
    /*
      Vars
   */
    ArrayList<Song> songArrayList;


    /*
        Methods
     */
    //following Overrides are needed because we extends ModifiableObservListBase
    @Override
    protected void doAdd(int index, Song element) {
        songArrayList.add(index, element);
    }

    @Override
    protected Song doRemove(int index) {
        return songArrayList.remove(index);
    }

    @Override
    public int size() {
        return songArrayList.size();
    }


    @Override
    public Song get(int index) {
        return songArrayList.get(index);
    }

    @Override
    // TODO: 18.05.18
    protected Song doSet(int index, Song element) {
        return null;
    }
    // end overrides based on Modif.Observ.ListBase


    @Override
    public boolean addSong(Song s) {
        return songArrayList.add(s);
    }

    @Override
    public boolean deleteSong(Song s) {
        return songArrayList.remove(s);
    }

    @Override
    // TODO: 04.05.2018
    public boolean deleteSongByID(long id) {
        return false;
    }

    @Override
    public void setList(ArrayList<Song> s) {
        this.songArrayList = s;
    }

    @Override
    public ArrayList<Song> getList() {
        return songArrayList;
    }

    @Override
    public void clearPlaylist() {
        songArrayList.clear();
    }

    @Override
    public int sizeOfPlaylist() {
        return songArrayList.size();
    }

    @Override
    public Song findSongByPath(String name) {
        for (Song s:songArrayList){
            if (s.pathProperty().getValue()==name){
            return s;
            }
        }
        return null;
    }

    @Override
    public Song findSongByID(long id) {
        for (Song s:songArrayList){
            if (s.getId()==id){
            return s;
            }
        }
        return null;
    }

    @Override
    public Iterator<Song> iterator() {
       Iterator<Song> songIterator = songArrayList.iterator();
        return songIterator;
    }

    public Playlist() {
        songArrayList = new ArrayList<Song>();
    }
}
