import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try(Socket socket = new Socket(serverAddress, port)){

            // getting input and output streams
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            // read input from user
            System.out.println("Enter message: ");
            String message = console.readLine();

            // send user's input to server
            output.println(message);

            //get server response
            String response = input.readLine();
            System.out.println("Server replied with : "+response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
