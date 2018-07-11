package udp;

import javafx.application.Platform;

import java.io.IOException;
import java.net.*;

// der slider, timer muss noch reinkommen

public class UDPClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress ia = null;
        ia = InetAddress.getByName("localhost");
        DatagramSocket socket = new DatagramSocket(5000);
        int i = 0;
        while (i < 10) {
            String msg = "TIME";
            byte buffer[] = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ia, 5000);
            socket.send(packet);
            byte answer[] = new byte[1024];
            packet = new DatagramPacket(answer, answer.length);
            socket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength())); // in der View anzeigen
            Thread.sleep(1000);

            i++;
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    //UI Komponenten updaten
//                    // list.getItems().add(0, getName() + " sagt Hallo!");
////                }
////            });

            // Runnable

        }

    }
}
