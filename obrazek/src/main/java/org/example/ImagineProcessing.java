package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

public class ImagineProcessing {
    public BufferedImage image;
    public void read(String path) throws IOException {
        image = ImageIO.read(new File(path));
    }
    public void write(String path) throws IOException {
        String format = path.lastIndexOf('.') != -1 ? "jpg" : path.substring(path.lastIndexOf('.') + 1);
        ImageIO.write(image, format, new File(path));
    }
    public void adjustBrightness(int value) {
        int red;
        int green;
        int blue;
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int rgb = image.getRGB(j, i);
                blue = rgb & 0xFF;
                green = rgb >> 8 & 0xFF;
                red = rgb >> 16 & 0xFF;
                //wyswietlanie koloru w formacie rgb
                //System.out.println(blue + " " + green + " " + red);
                /*System.out.println(
                        "RGB signed 32 bit = "+rgb+" " +
                        "hex: "+Integer.toHexString(rgb)+" " +
                        "Binary: " +Integer.toBinaryString(rgb)
                );*/

                //Alternatywa w nowszej javie math.clamp
                blue = Clamp.clamp(blue + value, 0, 255);
                green = Clamp.clamp(green + value, 0, 255);
                red = Clamp.clamp(red + value, 0, 255);
                rgb = (red << 16 ) | (green << 8 ) | blue;
                image.setRGB(j, i, rgb);
            }
        }
    }
    public void adjustBrightnessWithThreads(int value) {
        int threadNum = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[threadNum];
        int chunk = image.getHeight() / threadNum;

        for (int i = 0; i < threadNum; i++) {
           int start = i*chunk;
           int end = (i == threadNum-1) ? image.getHeight() : (i+1)*chunk;
               threads[i] = new Thread((new BrightnessWorker(start,end,image,value)));
               threads[i].start();

        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
