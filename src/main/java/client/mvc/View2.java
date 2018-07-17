package client.mvc;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import server.interfaces.RemoteInterface;

import java.awt.*;
import java.io.Externalizable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class View2 extends BorderPane {

    private RemoteInterface remote = (RemoteInterface) Naming.lookup("//localhost:1099/name");

    private Button button = new Button("test");


    public View2 () throws RemoteException, NotBoundException, MalformedURLException {
        setCenter(button);

        button.setOnAction(e -> {
            try {
                remote.deleteplaylist();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
    }


}
