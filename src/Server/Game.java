package Server;

import javafx.scene.input.KeyCode;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Thread {
    Socket player1;
    Socket player2;
    ObjectOutputStream player1Output;
    ObjectOutputStream player2Output;
    ObjectInputStream player1Input;
    ObjectInputStream player2Input;
    InputController player1InputController;
    InputController player2InputController;
    Timer GameTimer;
    GameCenter gameCenter;

    public Game(Socket player1, Socket player2) {
        this.player1=player1;
        this.player2=player2;
        start();
    }

    public void run(){
        try {
            //IO complete
            player1Output=new ObjectOutputStream(player1.getOutputStream());
            player2Output=new ObjectOutputStream(player2.getOutputStream());
            player1Input=new ObjectInputStream(player1.getInputStream());
            player2Input=new ObjectInputStream(player2.getInputStream());
            //notify players
            player1Output.writeObject("StartGameAsPlayer1");
            player2Output.writeObject("StartGameAsPlayer2");
            //getSnake
            Object snake1=player1Input.readObject();
            Object snake2=player2Input.readObject();
            if(snake1.getClass().equals(snake2.getClass())){
                player1Output.writeObject("sameSnake");
                player2Output.writeObject("sameSnake");
            }
            else {
                player1Output.writeObject("differentSnake");
                player2Output.writeObject("differentSnake");
                player1Output.writeObject(snake2);
                player2Output.writeObject(snake1);
            }
            gameCenter=new GameCenter();
            player1InputController=new InputController(player1Input, KeyCode.DOWN);
            player2InputController=new InputController(player2Input, KeyCode.UP);
            GameTimer=new Timer(true);
            GameTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    GameUpdate();
                }
            },0,1);
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
            GameTimer.cancel();
            try {
                player1.close();
                player2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void GameUpdate(){
        //Snakes Move


        //Event Listen


        //Render


    }
}
