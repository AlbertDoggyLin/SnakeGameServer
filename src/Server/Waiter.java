package Server;

import java.io.*;
import java.net.Socket;

public class Waiter extends Thread {
    private SocketIOPackage player;

    public Waiter(SocketIOPackage player) {
        this.player = player;
        start();
    }

    @Override
    public void run() {
        try {
            if (player.read().toString().equals("disconnect")) {
                player.socket.close();
            }
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
}
