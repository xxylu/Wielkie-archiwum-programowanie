package org.example;

public class Main {
    public static void main(String[] args) {
        String host = "localhost";
        ServerHandler s = new ServerHandler(host, 2345);
    }
}