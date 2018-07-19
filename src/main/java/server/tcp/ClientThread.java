package server.tcp;

import java.net.Socket;

public class ClientThread extends Thread {
    private Socket client;
    private String name;
    private String password;

    public ClientThread(Socket client, String pw){
        this.client = client;
        this.name = client.getInetAddress().getHostName();
        this.password = pw;
    }
}
