package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverStart{
    public static void main(String[] args) throws IOException {
        ServerSocket m_serverSocket=new ServerSocket(8787);
        while(true){
            Socket player1 = m_serverSocket.accept();
            new Waiter(player1);
            Socket player2 = m_serverSocket.accept();
            new Waiter(player2);
            while(player1.isClosed()){
                player1=m_serverSocket.accept();
                new Waiter(player1);
                if(player2.isClosed()){
                    player2=m_serverSocket.accept();
                }
                new Waiter(player2);
            }
            new Game(player1,player2);
        }
    }
}
