package SnakeGame.Enum;

import java.io.Serializable;
import java.util.Random;

public class Point implements Serializable {
  private static final long serialVersionUID = -1092863509347829L;
  private final int x;
  private final int y;
  private static int windowWidth = 600;
  public static int GridWidth = 20;
  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  public static Point getRandomPointGrid(){
    Random RandomPoint = new Random();
    int x = RandomPoint.nextInt(windowWidth / GridWidth);
    int y = RandomPoint.nextInt(windowWidth / GridWidth);
    return new Point(x*GridWidth,y*GridWidth);
  }
  public String toString() {
    return "( "+  x + " , " + y +" )"; 
  }
}
