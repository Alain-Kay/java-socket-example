import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            Socket socket = serverSocket.accept();
            System.out.println("Client connecté");

            while (true){
                Scanner scanner = new Scanner(System.in);
                System.out.print("Moi : ");
                String msg_envoye = scanner.nextLine();
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

                if (msg_envoye.equalsIgnoreCase("exit")) {
                    printWriter.println("Aurevoir...");
                    serverSocket.close();
                    break;
                }

                printWriter.println(msg_envoye);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg_recu = bufferedReader.readLine();
                System.out.println("Client : " + msg_recu);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
