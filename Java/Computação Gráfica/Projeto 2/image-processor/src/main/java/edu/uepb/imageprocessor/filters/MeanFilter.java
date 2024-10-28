package edu.uepb.imageprocessor.filters;

import java.awt.image.BufferedImage;

// Filtro de Média
public class MeanFilter {

    public static BufferedImage applyMeanFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, image.getType());

        int maskSize = 3;
        int maskArea = maskSize * maskSize;

        // Aplica o filtro da média
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int sum = 0;

                // Percorre a máscara 3x3 ao redor do pixel (x, y)
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int rgb = image.getRGB(x + i, y + j) & 0xFF; // Captura o valor de intensidade (cinza)
                        sum += rgb;
                    }
                }

                // Calcula a média e aplica ao pixel central
                int average = sum / maskArea;
                int newRgb = (average << 16) | (average << 8) | average; // Grayscale
                result.setRGB(x, y, newRgb);
            }
        }

        return result;
    }
}