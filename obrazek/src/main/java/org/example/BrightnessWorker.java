package org.example;

import java.awt.image.BufferedImage;

public class BrightnessWorker implements Runnable {

    public int start;
    public int end;
    public BufferedImage image;
    public int value;

    public BrightnessWorker(int start, int end, BufferedImage image, int value) {
        this.start = start;
        this.end = end;
        this.image=image;
        this.value = value;
    }
    @Override
    public void run() {
        int red;
        int green;
        int blue;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int rgb = image.getRGB(j, i);
                blue = rgb & 0xFF;
                green = rgb >> 8 & 0xFF;
                red = rgb >> 16 & 0xFF;
                blue = Clamp.clamp(blue + value, 0, 255);
                green = Clamp.clamp(green + value, 0, 255);
                red = Clamp.clamp(red + value, 0, 255);
                rgb = (red << 16 ) | (green << 8 ) | blue;
                image.setRGB(j, i, rgb);
            }
        }
    }
}
