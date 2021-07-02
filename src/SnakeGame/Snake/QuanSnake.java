package SnakeGame.Snake;

import Server.ResourcesLoader;
import SnakeGame.Enum.Point;
import SnakeGame.SingletonAndTemplate.Snake;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class QuanSnake extends Snake {
  @Override
  public void InitialSnakeBody(Point position) {
    this.position = position;
    this.body = new Rectangle(position.getX(), position.getY(), SnakeWidth, SnakeWidth);
    this.image = ResourcesLoader.getImage("img/Quan.JPG");
    this.body.setFill(new ImagePattern(image));
  }
}
