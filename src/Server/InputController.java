package Server;

import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class InputController extends Thread {
    private ObjectInputStream inputStream;
    private LinkedList<Direction> directionList;
    private KeyCode lastDirection;
    public boolean canFire;
    public Direction NextDirection(){
        return directionList.poll();
    }

    public InputController(ObjectInputStream input, KeyCode firstDirection) {
        inputStream = input;
        directionList = new LinkedList<>();
        lastDirection = firstDirection;
        canFire = false;
        start();
    }

    public void run() {
        while (true) {
            try {
                Object tem = inputStream.readObject();
                if (tem.toString().equals("dieconnect"))break;
                KeyCode in = (KeyCode) inputStream.readObject();
                if (in == null) break;
                else {
                    switch (in) {
                        case SLASH:
                            canFire = true;
                            break;
                        case UP:
                            if (lastDirection != KeyCode.DOWN && lastDirection != KeyCode.UP) {
                                directionList.add(Direction.UP);
                                lastDirection = in;
                            }
                            break;
                        case DOWN:
                            if (lastDirection != KeyCode.DOWN && lastDirection != KeyCode.UP) {
                                directionList.add(Direction.DOWN);
                                lastDirection = in;
                            }
                            break;
                        case RIGHT:
                            if (lastDirection != KeyCode.RIGHT && lastDirection != KeyCode.LEFT) {
                                directionList.add(Direction.RIGHT);
                                lastDirection = in;
                            }
                            break;
                        case LEFT:
                            if (lastDirection != KeyCode.RIGHT && lastDirection != KeyCode.LEFT) {
                                directionList.add(Direction.LEFT);
                                lastDirection = in;
                            }
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

}
