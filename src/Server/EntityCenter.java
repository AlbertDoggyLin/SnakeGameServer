package Server;

import java.util.ArrayList;
import java.util.List;

public class EntityCenter {
    private List<SnakeBody> SnakeList;
    private List<Food> FoodList;
    public EntityCenter(){
        SnakeList=new ArrayList<>();
        FoodList=new ArrayList<>();
    }
}
