package server.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;


public class UDPServer {
    private static DatagramSocket socket;
    private static final int PORT = 5000;
    private static DatagramPacket packet;
    static String msg;

    public static void main(String[] args) throws IOException {
        socket = new DatagramSocket(PORT);
        System.out.println("Erreichbar unter: "+InetAddress.getLocalHost() +":"+socket.getLocalPort());
        packet = new DatagramPacket(new byte[5], 5);
        socket.receive(packet);


        msg = new String(packet.getData());
        System.out.println("Angekommen");
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        int len = packet.getLength();
        String newmsg = msg.getBytes().toString();//String formatieren
        if (newmsg.equals("TIME:")){
            Date time = new Date();
            byte[] myTime = time.toString().getBytes();
            packet = new DatagramPacket(myTime, myTime.length , address, port);
            socket.send(packet);
        }


    }
}
