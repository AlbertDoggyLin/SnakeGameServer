package Server;

import SnakeGame.Enum.Point;
import SnakeGame.SingletonAndTemplate.SnakeBody;
import SnakeGame.SingletonAndTemplate.SnakePlayer;
import javafx.scene.input.KeyCode;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.FutureTask;

public class Game extends Thread {
    private final SocketIOPackage player1IO;
    private final SocketIOPackage player2IO;
    private InputController player1InputController;
    private InputController player2InputController;
    private SnakePlayer player1;
    private SnakePlayer player2;

    Timer GameTimer;
    GameCenter gameCenter;

    public Game(SocketIOPackage player1, SocketIOPackage player2) {
        this.player1IO=player1;
        this.player2IO=player2;
        start();
    }

    public void run(){
        try {
            //notify players
            player1IO.write("StartGameAsPlayer1");
            player2IO.write("StartGameAsPlayer2");
            //getSnake
            Object snake1=player1IO.read();
            Object snake2=player2IO.read();
            if(snake1.getClass().equals(snake2.getClass())){
                player1IO.write("sameSnake");
                player2IO.write("sameSnake");
            }
            else {
                player1IO.write("differentSnake");
                player2IO.write("differentSnake");
                player1IO.write(snake2);
                player2IO.write(snake1);
            }
            gameCenter=new GameCenter();
            player1InputController=new InputController(player1IO, KeyCode.DOWN);
            player2InputController=new InputController(player2IO, KeyCode.UP);
            player1=new SnakePlayer(new Point(200,200),player1InputController);
            player2=new SnakePlayer(new Point(400,400),player2InputController);
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
                player1IO.socket.close();
                player2IO.socket.close();
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
