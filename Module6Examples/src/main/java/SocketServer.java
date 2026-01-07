import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        int port = 5000;

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Waiting for connection... at port " + port);
            while(true){
                Socket socket = serverSocket.accept(); // accepting client connections
                System.out.println("Accepted new connection");

                //create output and input streams to listen to data
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                //read client messages
                String clientMessage = input.readLine();
                System.out.println(clientMessage);

                //respond to client
                output.println("Received: " + clientMessage);

                //close connection
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
