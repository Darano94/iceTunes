package client.mvc;

import server.interfaces.RemoteInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Controller {

    RemoteInterface remote = (RemoteInterface) Naming.lookup("//localhost:1099/name");

    public Controller() throws RemoteException, NotBoundException, MalformedURLException {
    }

    public void loadlib() throws RemoteException {
        System.out.println(remote.ids());

    }

}
