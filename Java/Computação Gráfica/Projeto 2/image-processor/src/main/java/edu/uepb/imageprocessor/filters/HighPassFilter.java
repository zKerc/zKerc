package edu.uepb.imageprocessor.filters;

import java.awt.image.BufferedImage;

//Passa alta básico
public class HighPassFilter {

    public static BufferedImage applyHighPassFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        // Aplica o filtro de passa alta básico
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int sum = 0;

                // Máscara de passa alta
                int[][] mask = {
                    {-1, -1, -1},
                    {-1, 8, -1},
                    {-1, -1, -1}
                };

                // Aplica a máscara ao redor do pixel (x, y)
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int rgb = image.getRGB(x + i, y + j) & 0xFF;
                        sum += rgb * mask[j + 1][i + 1];
                    }
                }

                // Limitar o valor para ficar entre 0 e 255
                int newRgb = Math.min(Math.max(sum, 0), 255);
                newRgb = (newRgb << 16) | (newRgb << 8) | newRgb; // Grayscale
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }
}
