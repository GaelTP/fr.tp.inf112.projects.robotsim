package fr.tp.inf112.projects.robotsim.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Logger;
import java.net.InetSocketAddress;

public class HelloWorldClient {

    private static final Logger LOGGER = Logger.getLogger(HelloWorldClient.class.getName());

    private Socket socket;

    public HelloWorldClient() {

        try {
            InetAddress netAddr = InetAddress.getByName("127.0.0.1"); // Remplacer l'adresse par l'adresse du serveur (127.0.0.1 = localhost)
            InetSocketAddress sockAddr = new InetSocketAddress(netAddr, 80);
            this.socket = new Socket();
            this.socket.connect(sockAddr, 1000);
            LOGGER.info("Connected to server");
        }
        catch (Exception ex) {
        }

    }

    public void sendMessage(String message) {
        try {
            PrintWriter oStream = new PrintWriter(this.socket.getOutputStream());
            BufferedReader iStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            oStream.write(message);
            oStream.flush();  // Flush the output stream manually, autoflush does not work and sends null object
            LOGGER.info("message sent");
        } catch (IOException ex) {
        }
    }

    public void close() {
        try {
            this.socket.close();
        } catch (IOException ex) {
        }
    }

    public static void main(String[] args) {
        HelloWorldClient client = new HelloWorldClient();
        client.sendMessage("Hello World");
        client.close();
    }

}
