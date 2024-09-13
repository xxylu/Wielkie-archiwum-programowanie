package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start(2345);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}