package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerHandler {

    private Socket socket;
    private Scanner input;
    private PrintWriter output;

    public ServerHandler(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        this.input = new Scanner(this.socket.getInputStream());
        this.output = new PrintWriter(this.socket.getOutputStream(), true);
    }
    public void send(String message) {
        output.println(message);
    }

    public String read() {
        if(input.hasNextLine()) {
            return input.nextLine();
        } else {
            return null;
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
