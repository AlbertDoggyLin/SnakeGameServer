package SnakeGame.SingletonAndTemplate;

import SnakeGame.Enum.Direction;
import SnakeGame.Enum.Point;
import SnakeGame.Enum.SnakePart;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SnakeBody {
    private int speed;
    private double effectRate;
    private double growRate;
    private Direction currentDirection;
    private final LinkedList<Point> body;
    private Point Head;

    public SnakeBody(Point startPosition, Direction initialDirection, int initialSpeed) {
        speed = initialSpeed;
        growRate = effectRate = 1;
        currentDirection = initialDirection;
        body = new LinkedList<>();
        Head = startPosition;
        for (int i = 0; i < 3; i++) addBody();
    }

    public void addBody() {
        synchronized (body) {
            growRate = 0.12 + growRate * 0.97;
            if (body.size() == 0) body.add(Head);
            else {
                HeadMove();
                body.addFirst(Head);
            }
        }
    }

    public void removeBody() {
        synchronized (body) {
            growRate = (growRate - 0.12) / 0.97;
            body.pollLast();
        }
    }

    public void Move(Direction direction) {
        if (direction != null) currentDirection = direction;
        synchronized (body) {
            HeadMove();
            body.addFirst(Head);
            body.pollLast();
        }
    }

    public int getSpeed() {
        return (int) (speed * growRate / effectRate);
    }

    public List<SnakePart> whatPart(Point p) {
        List<SnakePart> returnedList = new ArrayList<>();
        List<Point> copy;
        synchronized (body) {
            copy = new ArrayList<>(body);
        }
        for (int i = 0; i < copy.size(); i++)
            if (p.getX() == copy.get(i).getX() && p.getY() == copy.get(i).getY()) {
                if (i == 0) returnedList.add(SnakePart.HEAD);
                else {
                    returnedList.add(SnakePart.BODY);
                    break;
                }
            }
        return returnedList;
    }

    public ArrayList<Point> getBodyList() {
        synchronized (body) {
            return new ArrayList<>(body);
        }
    }

    private void HeadMove() {
        switch (currentDirection) {
            case UP:
                Head = new Point(Head.getX(), (Head.getY() + 600 - Snake.SnakeWidth) % 600);
                break;
            case DOWN:
                Head = new Point(Head.getX(), (Head.getY() + Snake.SnakeWidth) % 600);
                break;
            case RIGHT:
                Head = new Point((Head.getX() + Snake.SnakeWidth) % 600, Head.getY());
                break;
            case LEFT:
                Head = new Point((Head.getX() + 600 - Snake.SnakeWidth) % 600, Head.getY());
                break;
        }
    }
}
