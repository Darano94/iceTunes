package client;

import java.io.IOException;
import java.net.Socket;

public class TCPClient {

    public static void main (String args[]) throws IOException {

        String sentence;

        Socket clientsocket = new Socket("localhost", 5020);

        clientsocket.getInputStream();
        clientsocket.getOutputStream();

    }
}
