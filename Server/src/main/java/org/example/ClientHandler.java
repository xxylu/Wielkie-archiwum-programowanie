package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    private String login;
    private Socket socket;
    private Server server;
    private Scanner input;
    private PrintWriter output;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.input = new Scanner(this.socket.getInputStream());
        this.output = new PrintWriter(this.socket.getOutputStream(), true);
    }

    public void send(String message) {
        output.println(message);
    }

    @Override
    public void run() {
        login = input.nextLine();
        server.addClient(login, this);
        server.broadcast(String.format("Server: %s joined the server", login));
        String message;
        do{
            message = input.nextLine();
            server.broadcast(String.format(login + " " + message));
        }while(!message.equals("bye"));

        server.removeClient(login);
        server.broadcast(String.format("Server: %s left the server", login));

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
