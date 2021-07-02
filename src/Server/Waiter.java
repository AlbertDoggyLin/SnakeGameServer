package Server;

import java.io.*;
import java.net.Socket;

public class Waiter extends Thread {
    private Socket waiter;
    public Waiter(Socket player){
        waiter=player;
        start();
    }
    @Override
    public void run(){
        ObjectInputStream inputStream;
        try {
            inputStream=new ObjectInputStream(waiter.getInputStream());
            while(true){
                try {
                    if(inputStream.readObject().toString().equals("disconnect")){
                        waiter.close();
                        break;
                    }
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
