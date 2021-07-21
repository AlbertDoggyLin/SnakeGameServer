package SnakeGame.SingletonAndTemplate;
import SnakeGame.Enum.Point;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public abstract class Snake implements Serializable {
  public static final int SnakeWidth = Point.GridWidth;
  protected transient Rectangle body ;
  protected volatile Point position ;
  protected transient Image image ;
  public abstract void InitialSnakeBody(Point position);
  public void ChangPosition(Point newPosition){
  }
  public void SetColor(Color color){
    body.setFill(color);
  }
  public Point GetPosition(){
    return this.position;
  }
  public Rectangle GetBody(){
    return this.body;
  }
  public void SnakeEffect(Lighting l) {
    body.setEffect(l);
  }

}
