package edu.uepb.imageprocessor.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ImageLoader {

    // Método para carregar uma imagem .pgm
    public static BufferedImage loadPGM(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Scanner scanner = new Scanner(fileInputStream);
        
        try {
            // Ler cabeçalho
            if (!scanner.nextLine().equals("P2")) {
                throw new IOException("Arquivo PGM inválido (falta 'P2' no cabeçalho)");
            }

            // Ignorar linhas de comentário
            while (scanner.hasNext("#")) {
                scanner.nextLine();
            }

            // Ler largura, altura e o valor máximo de cinza
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            int maxGray = scanner.nextInt();

            // Inicializar a imagem em escala de cinza
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

            // Ler os valores dos pixels
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int gray = scanner.nextInt();
                    int rgb = (gray << 16) | (gray << 8) | gray;
                    image.setRGB(x, y, rgb);
                }
            }

            return image;

        } finally {
            scanner.close();
            fileInputStream.close();
        }
    }

    // Método para salvar uma BufferedImage como .pgm
    public static void savePGM(BufferedImage image, String outputPath) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(outputPath))) {
            // Escrever cabeçalho do PGM
            writer.println("P2");
            writer.println("# Arquivo gerado pelo ImageProcessor");
            writer.println(width + " " + height);
            writer.println(255);

            // Escrever valores de pixels
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int gray = image.getRGB(x, y) & 0xFF;
                    writer.print(gray + " ");
                }
                writer.println();
            }
        }
    }
}
