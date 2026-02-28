import java.io.*;
import java.net.*;

public class SocketServer {
    public static void main(String[] args) {
        int port = 5000; // Server will listen on this port
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept(); // Accept client connection
                System.out.println("New client connected");

                // Create input and output streams
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                // TODO: Server reads the greeting with name first
                String name = input.readLine(); //first message
                // TODO: server keeps reading until client is done
                // TODO: server replies include the user name; until the user enters 'exit' as message
                // Read client message
                String clientMessage;
                while((clientMessage=input.readLine())!=null) {
                    System.out.println("Received: " + clientMessage);

                    // Respond to client
                    output.println("Server: Hello, "+name+". You said - " + clientMessage);
                }
                System.out.println(name+" has left.");
                // Close connection
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}