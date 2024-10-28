package edu.uepb.imageprocessor.operations;

import java.awt.image.BufferedImage;

// Operadores Lógicos (OR, AND e XOR)
public class ImageLogicalOperations {

    // Operação OR
    public static BufferedImage orImages(BufferedImage image1, BufferedImage image2) {
        int width = Math.min(image1.getWidth(), image2.getWidth());
        int height = Math.min(image1.getHeight(), image2.getHeight());
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y) & 0xFF;
                int rgb2 = image2.getRGB(x, y) & 0xFF;

                int orResult = rgb1 | rgb2;
                int newRgb = (orResult << 16) | (orResult << 8) | orResult; // Grayscale
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }

    // Operação AND
    public static BufferedImage andImages(BufferedImage image1, BufferedImage image2) {
        int width = Math.min(image1.getWidth(), image2.getWidth());
        int height = Math.min(image1.getHeight(), image2.getHeight());
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y) & 0xFF;
                int rgb2 = image2.getRGB(x, y) & 0xFF;

                int andResult = rgb1 & rgb2;
                int newRgb = (andResult << 16) | (andResult << 8) | andResult; // Grayscale
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }

    // Operação XOR
    public static BufferedImage xorImages(BufferedImage image1, BufferedImage image2) {
        int width = Math.min(image1.getWidth(), image2.getWidth());
        int height = Math.min(image1.getHeight(), image2.getHeight());
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y) & 0xFF;
                int rgb2 = image2.getRGB(x, y) & 0xFF;

                int xorResult = rgb1 ^ rgb2;
                int newRgb = (xorResult << 16) | (xorResult << 8) | xorResult; // Grayscale
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }
}
