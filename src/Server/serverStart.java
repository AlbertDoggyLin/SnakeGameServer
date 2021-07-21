package Server;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serverStart{
    public static void main(String[] args) throws IOException {
        ServerSocket m_serverSocket=new ServerSocket(8787);
        while(true){
            SocketIOPackage player1 = new SocketIOPackage(m_serverSocket.accept());
            Waiter waiter1 = new Waiter(player1);
            SocketIOPackage player2 = new SocketIOPackage(m_serverSocket.accept());
            Waiter waiter2 = new Waiter(player2);
            while(player1.socket.isClosed()){
                player1=new SocketIOPackage(m_serverSocket.accept());
                waiter1 = new Waiter(player1);
                if(player2.socket.isClosed()){
                    player2=new SocketIOPackage(m_serverSocket.accept());
                }
                waiter2 = new Waiter(player2);
            }
            waiter1.interrupt();
            waiter2.interrupt();
            new Game(player1,player2);
        }
    }
}
