package client.udp;

import javafx.application.Platform;
import server.mvc.Controller;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;


public class UDPClient {
    private static final int PORT = 5000;
    private static final String IP = "192.168.80.108";


    public static void main(String[] args) throws IOException {

        Controller contr = new Controller();
        SimpleDateFormat currentTime = new SimpleDateFormat("00:mm:ss");


        InetAddress address = InetAddress.getByName("localHost");
        DatagramSocket socket = new DatagramSocket();
        int i = 0;

        while (i < 10) { //Songlänge ?
            String msg = "TIME:";
            msg = currentTime.format(contr.getCurrentduration());
            byte buffer[] = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
            socket.send(packet);

            byte answer[] = new byte[1024];
            packet = new DatagramPacket(answer, answer.length);
            socket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}