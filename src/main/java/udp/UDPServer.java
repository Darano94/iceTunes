package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.Timer;

public class UDPServer {
    private static DatagramSocket socket;
    private static final int PORT = 5000;
    private static DatagramPacket packet;


    public static void main(String[] args) throws IOException {
        socket = new DatagramSocket(PORT);
        System.out.println("Erreichbar unter: "+InetAddress.getLocalHost() +":"+socket.getLocalPort());
        packet = new DatagramPacket(new byte[5], 5);
//        Platform.runLater(new Runnable() {
//        @Override
//            public void run() {
////                    //UI Komponenten updaten
//                packet.getData().add(0, getName() + " sagt Hallo!");
////                }
////            });

        // Runnable
        socket.receive(packet);

        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        int len = packet.getLength();
        byte [] data = packet.getData();
        System.out.println("Anfrage von" +address+ "vom Port" +port+ "mit der Länge" +len+ "\n" +new String(data));

        String da = new String(data);
        Scanner sc = new Scanner(da).useDelimiter(":");
            String keyword = sc.next();
        if ( keyword.equals ("TIME")) { // keyword bei Client einrichten
            Timer t = new Timer();
            byte [] myTimer = t.toString().getBytes();

            packet = new DatagramPacket(myTimer, myTimer.length, address, port);
            socket.send(packet);

        } else {
            byte [] myTimer = null;
            myTimer = new String ( "Fehlermeldung").getBytes();
            packet = new DatagramPacket(myTimer, myTimer.length, address, port);
            socket.send(packet);
        }
    }
}
