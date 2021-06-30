package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Waiter extends Thread {
    private Socket waiter;
    public Waiter(Socket player){
        waiter=player;
        start();
    }
    @Override
    public void run(){
    }
}
