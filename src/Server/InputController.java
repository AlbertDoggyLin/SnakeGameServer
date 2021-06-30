package Server;

import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.io.ObjectInputStream;

public class InputController extends Thread {
    private ObjectInputStream inputStream;
    public InputController(ObjectInputStream input){
        inputStream=input;
        start();
    }
    public void run(){
        while(true){
            try {
                KeyCode in=(KeyCode) inputStream.readObject();
                if(in==null)break;
                else System.out.println(in);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
