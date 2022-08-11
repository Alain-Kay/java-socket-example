import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 7777);

            while (true){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg_recu = bufferedReader.readLine();
                System.out.println("Server : " + msg_recu);

                if (msg_recu.equals("Aurevoir...")) {
                    System.out.println("Serveur éteint...");
                    break;
                }

                Scanner scanner = new Scanner(System.in);
                System.out.print("Moi : ");
                String msg_envoye = scanner.nextLine();
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println(msg_envoye);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
