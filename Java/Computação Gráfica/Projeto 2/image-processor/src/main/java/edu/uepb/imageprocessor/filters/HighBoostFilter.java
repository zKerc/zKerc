package edu.uepb.imageprocessor.filters;

import java.awt.image.BufferedImage;

// Gradiente Alto Reforço
public class HighBoostFilter {

    public static BufferedImage applyHighBoostFilter(BufferedImage image, double A) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        // Máscara de alta nitidez (passa-alta)
        int[][] mask = {
            {-1, -1, -1},
            {-1, 8, -1},
            {-1, -1, -1}
        };

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int originalPixel = image.getRGB(x, y) & 0xFF;
                int sum = 0;

                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int rgb = image.getRGB(x + i, y + j) & 0xFF;
                        sum += rgb * mask[j + 1][i + 1];
                    }
                }

                int highBoost = (int) (originalPixel + A * sum);
                int newValue = Math.min(Math.max(highBoost, 0), 255);
                int newRgb = (newValue << 16) | (newValue << 8) | newValue;
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }
}
