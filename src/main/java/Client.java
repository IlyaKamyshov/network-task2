
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try (Socket clientSocket = new Socket("localhost", Server.SRVPORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String srvAnswer = reader.readLine();
            System.out.println(srvAnswer);
            writer.println("Kot Matroskin");
            writer.flush();

            srvAnswer = reader.readLine();
            System.out.println(srvAnswer);
            writer.println("NO");
            writer.flush();

            srvAnswer = reader.readLine();
            System.out.println(srvAnswer);

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
