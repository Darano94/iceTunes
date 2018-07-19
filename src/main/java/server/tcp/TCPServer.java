package server.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer {

    public static void main(String args[]) throws IOException {
        String Sentence;
        ServerSocket server = new ServerSocket(5020);
        Socket client = null;

        while (true){
                client = server.accept();
                Thread t = new ClientThread(client, "mypassword");
                t.start();
        }
    }
}
