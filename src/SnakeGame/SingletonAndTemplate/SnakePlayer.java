package SnakeGame.SingletonAndTemplate;

import Server.InputController;
import SnakeGame.Enum.Point;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class SnakePlayer {
    private SnakeBody m_snakeBody;
    private int counter;
    private InputController m_InputController;
    private Callable<Void> skill;
    public SnakePlayer(Point startPoint, InputController inputController){
        m_snakeBody=new SnakeBody(startPoint, inputController.NextDirection(), 170);
        m_InputController=inputController;
        skill=null;
        counter=0;
    }
    public void Update() throws Exception {
        counter++;
        if(counter >= m_snakeBody.getSpeed()){
            counter=0;
            m_snakeBody.Move(m_InputController.NextDirection());
            if(m_InputController.canFire){
                m_InputController.canFire=false;
                if(skill!=null)skill.call();
            }
        }
    }
    public ArrayList<Point> getSnakeList(){
        return m_snakeBody.getBodyList();
    }
}
