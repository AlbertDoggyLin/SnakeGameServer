package Server;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Thread {
    Socket player1;
    Socket player2;
    PrintStream player1Output;
    PrintStream player2Output;
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
            player1Output=new PrintStream(player1.getOutputStream());
            player2Output=new PrintStream(player2.getOutputStream());
            player1Input=new ObjectInputStream(player1.getInputStream());
            player2Input=new ObjectInputStream(player2.getInputStream());
            gameCenter=new GameCenter();
            player1Output.println("StartGameAsPlayer1");
            player2Output.println("StartGameAsPlayer2");
            player1Output.flush();
            player2Output.flush();
            player1InputController=new InputController(player1Input);
            player2InputController=new InputController(player2Input);
            GameTimer=new Timer(true);
            GameTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    GameUpdate();
                }
            },0,1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void GameUpdate(){

    }
}
