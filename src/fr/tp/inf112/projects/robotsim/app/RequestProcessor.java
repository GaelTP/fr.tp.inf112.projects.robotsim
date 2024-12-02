package fr.tp.inf112.projects.robotsim.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.logging.Logger;

public class RequestProcessor implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(RequestProcessor.class.getName());

    private Socket socket;

    public RequestProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            LOGGER.info("Req running");
            InputStream inpStr = socket.getInputStream();
            Reader strReader = new InputStreamReader(inpStr);
            BufferedReader buffReader = new BufferedReader(strReader);
            
            String message = buffReader.readLine();
            OutputStream outStr = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outStr, true); // Autoflush

            LOGGER.info("Recieved" + message);
            
        }
        catch (IOException ex) {
            LOGGER.severe("Error");
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException ex) {
            }
        }
    }
}