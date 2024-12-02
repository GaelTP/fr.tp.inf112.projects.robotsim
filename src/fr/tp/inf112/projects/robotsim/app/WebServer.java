package fr.tp.inf112.projects.robotsim.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class WebServer {

    private static final Logger LOGGER = Logger.getLogger(WebServer.class.getName());

    public static void main(String args[]) {

        try (ServerSocket serverSocket = new ServerSocket(80);) {
            do {

                LOGGER.info("socket sur écoute");

                try {
                    Socket socket = serverSocket.accept();                     
                    Runnable reqProcessor = new RequestProcessor(socket);
                    LOGGER.info("req to be sent");
                    new Thread(reqProcessor).start();
                    LOGGER.info("Req sent");
                }
                catch (IOException ex) {
                }
            } while (true);
        }
        catch (IOException ex) {
        }
    }
}