package Server;

public class GameCenter {
    private EventCenter eventCenter;
    private EntityCenter entityCenter;
    public GameCenter(){
        eventCenter=new EventCenter();
        entityCenter=new EntityCenter();
    }
}
