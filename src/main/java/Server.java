import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public final static Integer SRVPORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SRVPORT)) {
            System.out.println("Server started");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.printf("New connection accepted. Client: %s, port:%d%n",
                            clientSocket.getInetAddress(), clientSocket.getPort());

                    out.println("Hi, this is Server. Write your name");
                    out.flush();

                    String name = in.readLine();
                    System.out.println(name);
                    out.println(String.format("%s, are you child? (yes/no)", name));
                    out.flush();

                    String readLine = in.readLine();
                    System.out.println(readLine);
                    if (readLine.equalsIgnoreCase("yes")) {
                        out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                        out.flush();
                    }
                    if (readLine.equalsIgnoreCase("no")) {
                        out.println(String.format("Welcome to the adult zone, %s! Have a good rest," +
                                " or a good working day!", name));
                        out.flush();
                    }
                    out.println(String.format("%s! Your answer is unknown! Let's bye!", name));
                    out.flush();
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
