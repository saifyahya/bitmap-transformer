package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Bitmap {
    private int width;
    private int height;
    private byte[] pixelData;

    public Bitmap(String filePath) {
        try {
            BufferedImage img = ImageIO.read(new File(filePath));
            this.width = img.getWidth();
            this.height = img.getHeight();
            this.pixelData = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void methodName(int number) {
        switch (number) {
            case 1 :
                addBorder(5);
                break;
            case 2 :
                convertToBlackAndWhite();
                break;
                case 3 :
                    verticallyMirror();
            break;
        }
    }
    public void addBorder(int borderWidth) {
        if (borderWidth <= 0) {
            return;
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x < borderWidth || y < borderWidth || x >= width - borderWidth || y >= height - borderWidth) {
                    int index = (y * width + x) * 3;
                    pixelData[index] = (byte) 255;
                    pixelData[index + 1] = 0;
                    pixelData[index + 2] = 0;
                }
            }
        }
    }public void convertToBlackAndWhite() {
        for (int i = 0; i < pixelData.length; i += 3) {
            int r = pixelData[i] & 0xFF;
            int g = pixelData[i + 1] & 0xFF;
            int b = pixelData[i + 2] & 0xFF;

            int grayValue = (int) (0.2989 * r + 0.5870 * g + 0.1140 * b);
            byte grayPixel = (byte) grayValue;

            pixelData[i] = grayPixel;
            pixelData[i + 1] = grayPixel;
            pixelData[i + 2] = grayPixel;
        }
    }
    public void verticallyMirror() {
        byte[] newPixelData = new byte[pixelData.length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int sourcePixelIndex = (i * width + j) * 3;
                int targetPixelIndex = ((height - 1 - i) * width + j) * 3;

                newPixelData[targetPixelIndex] = pixelData[sourcePixelIndex];
                newPixelData[targetPixelIndex + 1] = pixelData[sourcePixelIndex + 1];
                newPixelData[targetPixelIndex + 2] = pixelData[sourcePixelIndex + 2];
            }
        }
        pixelData = newPixelData;
    }
    public void applyGrayscaleTransformation() {
        byte[] newPixelData = new byte[pixelData.length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixelIndex = (i * width + j) * 3;
                int r = pixelData[pixelIndex] & 0xFF;
                int g = pixelData[pixelIndex + 1] & 0xFF;
                int b = pixelData[pixelIndex + 2] & 0xFF;
                int gray = (r + g + b) / 3;

                newPixelData[pixelIndex] = (byte) gray;
                newPixelData[pixelIndex + 1] = (byte) gray;
                newPixelData[pixelIndex + 2] = (byte) gray;
            }
        }
        pixelData = newPixelData;
    }
    public void randomizeColors() {
        Random random = new Random();
        for (int i = 0; i < pixelData.length; i += 3) {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);

            pixelData[i] = (byte) r;
            pixelData[i + 1] = (byte) g;
            pixelData[i + 2] = (byte) b;
        }
    }
    public void writeToFile(String filePath) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        img.setData(Raster.createRaster(img.getSampleModel(), new DataBufferByte(pixelData, pixelData.length), null));
        try {
            ImageIO.write(img, "bmp", new File(filePath));
            System.out.println("image has written to file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public byte[] getPixelData() {
        return this.pixelData;
    }
}
