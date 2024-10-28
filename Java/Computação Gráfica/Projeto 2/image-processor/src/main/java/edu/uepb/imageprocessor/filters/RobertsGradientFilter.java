package edu.uepb.imageprocessor.filters;

import java.awt.image.BufferedImage;

// Operador Gradiente de Robert
public class RobertsGradientFilter {

    public static BufferedImage applyRobertsGradientFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        // Aplica o operador de Roberts
        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width - 1; x++) {
                int Z5 = image.getRGB(x, y) & 0xFF;
                int Z6 = image.getRGB(x + 1, y) & 0xFF;
                int Z8 = image.getRGB(x, y + 1) & 0xFF;

                // Calcular o gradiente nas direções X e Y
                int gx = Math.abs(Z5 - Z8); // Diferença na direção X
                int gy = Math.abs(Z5 - Z6); // Diferença na direção Y

                // Magnitude do gradiente de Roberts
                int gradientMagnitude = gx + gy;

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
