package edu.uepb.imageprocessor.operations;

import java.awt.image.BufferedImage;

// Operação Multiplicação
public class ImageMultiplication {

    public static BufferedImage multiplyImages(BufferedImage image1, BufferedImage image2) {
        int width = Math.min(image1.getWidth(), image2.getWidth());
        int height = Math.min(image1.getHeight(), image2.getHeight());
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y) & 0xFF;
                int rgb2 = image2.getRGB(x, y) & 0xFF;
                
                int product = rgb1 * rgb2;
                int scaledProduct = Math.min(product / 255, 255); // Escala e limita a 0-255

                int newRgb = (scaledProduct << 16) | (scaledProduct << 8) | scaledProduct; // Grayscale
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }
}