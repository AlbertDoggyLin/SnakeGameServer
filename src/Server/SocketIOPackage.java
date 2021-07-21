package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketIOPackage {
    public Socket socket;
    private final ObjectInputStream i;
    private final ObjectOutputStream o;
    public SocketIOPackage(Socket socket) throws IOException {
        this.socket=socket;
        o=new ObjectOutputStream(socket.getOutputStream());
        i=new ObjectInputStream(socket.getInputStream());
    }
    public void write(Object obj) throws IOException {
        synchronized (o){
            o.writeObject(obj);
        }
    }
    public synchronized Object read() throws IOException, ClassNotFoundException {
        synchronized (i){
            return i.readObject();
        }
    }
}
