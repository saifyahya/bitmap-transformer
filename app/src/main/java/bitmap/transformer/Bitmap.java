package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {
    private int width;        // Width of the image in pixels
    private int height;       // Height of the image in pixels
    private int colorDepth;   // Number of bits per pixel (e.g., 24-bit)
    private int dataSize;     // Size of the pixel data in bytes
    private byte[] pixelData; // pixel values

    public Bitmap(int width, int height) {


            this.width= width;
            this.height= height;
            //this.pixelData = (byte[]) img.getRaster().getDataElements(0,0,width,height,null);


    }
    public void Transform(String transformName) {
        //grayscale
       /* BufferedImage grayScale = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        for (int i = 0 ; i < width ; i++){
            for (int j = 0 ; j < width ; j++){
            Color color =  new Color(Bitmap);
            }
        }*/
    }
    public void writeToFile(String filePath) {

    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
}
