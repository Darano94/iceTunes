package server.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Entity()
@Table(name = "song")
public class Song implements server.interfaces.Song, Externalizable {

    private transient SimpleStringProperty path = new SimpleStringProperty();
    private transient SimpleStringProperty title = new SimpleStringProperty();
    private transient SimpleStringProperty album = new SimpleStringProperty();
    private transient SimpleStringProperty interpret = new SimpleStringProperty();
    @GeneratedValue
    private long id;



    public Song() throws IDOverFlowException {
        IdGenerator idgen = new IdGenerator();
        long tmp = idgen.getNextID();

        if(tmp >= 0 && tmp <10000) {
            this.id = tmp;
        }
        else {
            throw new IDOverFlowException();
        }
    }


    @Override
    public String getAlbum() {
        return this.album.get();
    }

    @Override
    public void setAlbum(String album) {
        this.album.setValue(album);
    }


    @Override
    public String getInterpret() {
        return this.interpret.get();
    }

    @Override
    public void setInterpret(String interpret) {
        this.interpret.setValue(interpret);
    }


    @Override
    public String getPath() {
        return this.path.toString();
    }

    @Override
    public void setPath(String path) {
        this.path.setValue(path);
    }


    @Override
    public String getTitle() {
        return this.title.get();
    }

    @Override
    public void setTitle(String title) {
        this.title.setValue(title);
    }

    @Id
    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public ObservableValue<String> pathProperty() {
        return this.path;
    }

    @Override
    public ObservableValue<String> albumProperty() {
        return this.album;
    }

    @Override
    public ObservableValue<String> interpretProperty() {
        return this.interpret;
    }

    @Override
    public ObservableValue<String> titleProperty() {
        return this.title;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(pathProperty().getValue());
        out.writeObject(albumProperty().getValue());
        out.writeObject(interpretProperty().getValue());
        out.writeObject(titleProperty().getValue());
        out.writeLong(getId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.path.setValue((String) in.readObject());
        this.album.setValue((String) in.readObject());
        this.interpret.setValue((String) in.readObject());
        this.title.setValue((String) in.readObject());
        this.id = in.readLong();
    }
}
