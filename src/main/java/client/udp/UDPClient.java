package client.udp;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    private static final int PORT = 5000;
    private static final String IP ="192.168.80.108";

    public static void main ( String [] args) throws IOException {
        InetAddress address = InetAddress.getByName("localHost");
        DatagramSocket socket = new DatagramSocket();
        String msg = "TIME:";
        byte buffer [] = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
        socket.send(packet);

        byte answer [] = new byte[1024];
        packet = new DatagramPacket(answer, answer.length);
        socket.receive(packet);
        System.out.println(new String ( packet.getData(),0,packet.getLength()));



    }
}
