package server;

import client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class TCPServer {

    public static void main(String args[]) throws IOException {

        String Sentence;

        ServerSocket welcomesocket = new ServerSocket(5020);

        while (true){
            Socket connectionsocket = null;

            try{
              connectionsocket = welcomesocket.accept();
                System.out.println("Ein Client hat sich verbunden.");

                //in out streams ?

                Thread t = new Client(connectionsocket);
            }
        }

    }
}
