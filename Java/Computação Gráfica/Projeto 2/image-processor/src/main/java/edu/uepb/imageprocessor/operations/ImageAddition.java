package edu.uepb.imageprocessor.operations;

import java.awt.image.BufferedImage;

// Operação Soma
public class ImageAddition {

    public static BufferedImage addImages(BufferedImage image1, BufferedImage image2) {
        int width = Math.min(image1.getWidth(), image2.getWidth());
        int height = Math.min(image1.getHeight(), image2.getHeight());
        BufferedImage result = new BufferedImage(width, height, image1.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y) & 0xFF;
                int rgb2 = image2.getRGB(x, y) & 0xFF;
                
                // Soma dos valores de intensidade, limitando a 255
                int sum = Math.min(rgb1 + rgb2, 255);
                
                // Formata o pixel para escala de cinza
                int newRgb = (sum << 16) | (sum << 8) | sum;
                result.setRGB(x, y, newRgb);
            }
        }
        
        return result;
    }
}
