package org.example;

import java.io.IOException;

public class Client {

    public void start() {
        ServerHandler serverHandler = null;
        try {
            serverHandler = new ServerHandler("localhost", 2345);
            Thread receiver = new Thread(new Runnable() {
                @Override
                public void run() {
                    String message;
                    while(true){
                        message = serverHandler.read();
                        if(message == null){
                            break;
                        }
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
