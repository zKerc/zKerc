package edu.uepb.imageprocessor.filters;

import java.awt.image.BufferedImage;

// Gradiente de Sobel
public class SobelFilter {

    public static BufferedImage applySobelFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        // Aplica o operador de Sobel
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int Z1 = image.getRGB(x - 1, y - 1) & 0xFF;
                int Z2 = image.getRGB(x, y - 1) & 0xFF;
                int Z3 = image.getRGB(x + 1, y - 1) & 0xFF;
                int Z4 = image.getRGB(x - 1, y) & 0xFF;
                int Z6 = image.getRGB(x + 1, y) & 0xFF;
                int Z7 = image.getRGB(x - 1, y + 1) & 0xFF;
                int Z8 = image.getRGB(x, y + 1) & 0xFF;
                int Z9 = image.getRGB(x + 1, y + 1) & 0xFF;

                // Gradiente na direção X e Y
                int gx = (Z3 + 2 * Z6 + Z9) - (Z1 + 2 * Z4 + Z7);
                int gy = (Z7 + 2 * Z8 + Z9) - (Z1 + 2 * Z2 + Z3);

                // Calcula a magnitude do gradiente de Sobel
                int gradientMagnitude = Math.abs(gx) + Math.abs(gy);

                // Limitar o valor para ficar entre 0 e 255
                gradientMagnitude = Math.min(Math.max(gradientMagnitude, 0), 255);

                // Converter para escala de cinza
                int newRgb = (gradientMagnitude << 16) | (gradientMagnitude << 8) | gradientMagnitude;
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }
}
