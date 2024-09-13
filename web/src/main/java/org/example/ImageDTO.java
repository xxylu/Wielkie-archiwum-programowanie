package org.example;

public class ImageDTO {
    private String imageBase64;
    private int brightness;

    public String getImageBase64() {
        return imageBase64;
    }
    public int getBrightness() {
        return brightness;
    }
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
    public void setImage(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
