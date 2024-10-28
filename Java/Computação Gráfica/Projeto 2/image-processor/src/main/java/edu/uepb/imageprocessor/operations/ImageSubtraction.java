package edu.uepb.imageprocessor.operations;

import java.awt.image.BufferedImage;

// Operação de Subtração
public class ImageSubtraction {

    public static BufferedImage subtractImages(BufferedImage image1, BufferedImage image2) {
        int width = Math.min(image1.getWidth(), image2.getWidth());
        int height = Math.min(image1.getHeight(), image2.getHeight());
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y) & 0xFF;
                int rgb2 = image2.getRGB(x, y) & 0xFF;
                
                int diff = Math.abs(rgb1 - rgb2); // Subtração absoluta
                int newRgb = (diff << 16) | (diff << 8) | diff; // Grayscale
                
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }
}
