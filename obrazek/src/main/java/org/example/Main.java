package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


       ImagineProcessing im = new ImagineProcessing();
        long start, end;

        im.read("thailand1.jpg");
        start = System.currentTimeMillis();
        im.adjustBrightness(50);
        end = System.currentTimeMillis();
        System.out.println("Regular: " + (end - start));
        im.write("thailand2.jpg");
        im.read("thailand1.jpg");
        start = System.currentTimeMillis();
        im.adjustBrightnessWithThreads(50);
        end = System.currentTimeMillis();
        System.out.println("Threads: " + (end - start));
        im.write("thailandmulti.jpg");

        /*try {
            im.read("thailand1.jpg");
            im.adjustBrightness(0);
            im.write("thailand2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}