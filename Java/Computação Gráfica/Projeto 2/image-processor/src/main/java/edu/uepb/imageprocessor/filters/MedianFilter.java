package edu.uepb.imageprocessor.filters;

import java.awt.image.BufferedImage;
import java.util.Arrays;

// Filtro de Mediana
public class MedianFilter {

    public static BufferedImage applyMedianFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        int maskSize = 3;
        int[] mask = new int[maskSize * maskSize];

        // Aplica o filtro da mediana
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int index = 0;

                // Coleta os valores ao redor do pixel (x, y)
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int rgb = image.getRGB(x + i, y + j) & 0xFF; // Captura o valor de intensidade (cinza)
                        mask[index++] = rgb;
                    }
                }

                // Ordena e pega a mediana
                Arrays.sort(mask);
                int median = mask[mask.length / 2];
                int newRgb = (median << 16) | (median << 8) | median; // Grayscale
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }
}
