package SnakeGame.Snake;

import Server.ResourcesLoader;
import SnakeGame.Enum.Point;
import SnakeGame.SingletonAndTemplate.Snake;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.net.MalformedURLException;

public class CustomizeSnake2 extends Snake {
    private static File ImgSource;
    @Override
    public void InitialSnakeBody(Point position) {
        this.position = position;
        this.body = new Rectangle(position.getX(), position.getY(), SnakeWidth, SnakeWidth);
        if (ImgSource == null) this.image = ResourcesLoader.getImage("img/Question.png");
        this.body.setFill(new ImagePattern(this.image));
    }
    public static void SetImageSource(File file){
        ImgSource = file;
    }
}
