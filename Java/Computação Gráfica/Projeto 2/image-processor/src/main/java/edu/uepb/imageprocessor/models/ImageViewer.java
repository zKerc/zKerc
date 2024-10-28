package edu.uepb.imageprocessor.models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ImageViewer extends JFrame {
    private BufferedImage originalImage;
    private BufferedImage transformedImage;
    private BufferedImage resultImage;
    private int mouseX = -1;
    private int mouseY = -1;
    private String originalLabel;
    private String transformedLabel;
    private String resultLabel;

    public ImageViewer(BufferedImage originalImage, BufferedImage transformedImage, String originalLabel, String transformedLabel) {
        this(originalImage, transformedImage, null, originalLabel, transformedLabel, "Imagem Resultado");
    }

    public ImageViewer(BufferedImage originalImage, BufferedImage transformedImage, BufferedImage resultImage, String originalLabel, String transformedLabel, String resultLabel) {
        this.originalImage = originalImage;
        this.transformedImage = transformedImage;
        this.resultImage = resultImage;
        this.originalLabel = originalLabel;
        this.transformedLabel = transformedLabel;
        this.resultLabel = resultLabel;

        initViewer("Visualizador de Imagens - Original, Transformada e Resultado");
    }

    private void initViewer(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1500, 700);

        ImagePanel imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.CENTER);

        imagePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Identifica em qual imagem o mouse está
                int offset = 50;
                int originalX = 100;
                int transformedX = 600;
                int resultX = 1100;

                // Define a posição do mouse relativa à imagem onde está
                if (e.getX() >= originalX && e.getX() < originalX + (originalImage != null ? originalImage.getWidth() : 0)) {
                    mouseX = e.getX() - originalX;
                    mouseY = e.getY() - offset;
                } else if (e.getX() >= transformedX && e.getX() < transformedX + (transformedImage != null ? transformedImage.getWidth() : 0)) {
                    mouseX = e.getX() - transformedX;
                    mouseY = e.getY() - offset;
                } else if (resultImage != null && e.getX() >= resultX && e.getX() < resultX + resultImage.getWidth()) {
                    mouseX = e.getX() - resultX;
                    mouseY = e.getY() - offset;
                } else {
                    mouseX = -1;
                    mouseY = -1;
                }

                imagePanel.repaint(); // Atualiza as matrizes de intensidade em todas as imagens
            }
        });
    }

    private class ImagePanel extends JPanel {
        public ImagePanel() {
            setPreferredSize(new Dimension(1500, 700));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int offset = 50;
            int originalX = 100;
            int transformedX = 600;
            int resultX = 1100;

            // Desenha imagens e rótulos
            drawImageWithLabel(g, originalImage, originalLabel, originalX, offset);
            drawImageWithLabel(g, transformedImage, transformedLabel, transformedX, offset);
            if (resultImage != null) {
                drawImageWithLabel(g, resultImage, resultLabel, resultX, offset);
            }

            // Exibe a matriz de intensidade para todas as imagens com o pixel destacado
            if (mouseX >= 0 && mouseY >= 0) {
                drawIntensityMatrix(g, originalImage, mouseX, mouseY, originalX, 400);
                drawIntensityMatrix(g, transformedImage, mouseX, mouseY, transformedX, 400);
                if (resultImage != null) {
                    drawIntensityMatrix(g, resultImage, mouseX, mouseY, resultX, 400);
                }
            }
        }

        private void drawImageWithLabel(Graphics g, BufferedImage image, String label, int x, int y) {
            if (image != null) {
                g.drawImage(image, x, y, null);
                g.drawString(label, x, y - 10);
            }
        }

        private void drawIntensityMatrix(Graphics g, BufferedImage image, int centerX, int centerY, int matrixStartX, int matrixStartY) {
            if (image == null) return;

            int matrixSize = 10;
            int cellSize = 25;
            int startX = Math.max(0, centerX - matrixSize / 2);
            int startY = Math.max(0, centerY - matrixSize / 2);

            g.setFont(new Font("Arial", Font.PLAIN, 12));

            for (int y = 0; y < matrixSize; y++) {
                for (int x = 0; x < matrixSize; x++) {
                    int imgX = startX + x;
                    int imgY = startY + y;

                    if (imgX < image.getWidth() && imgY < image.getHeight()) {
                        int intensity = image.getRGB(imgX, imgY) & 0xFF;
                        int displayX = matrixStartX + x * cellSize;
                        int displayY = matrixStartY + y * cellSize;

                        g.setColor(Color.WHITE);
                        g.fillRect(displayX, displayY, cellSize, cellSize);
                        g.setColor(Color.BLACK);
                        g.drawRect(displayX, displayY, cellSize, cellSize);

                        if (imgX == centerX && imgY == centerY) {
                            g.setColor(Color.RED);
                        } else {
                            g.setColor(Color.BLACK);
                        }

                        String text = String.valueOf(intensity);
                        FontMetrics metrics = g.getFontMetrics();
                        int textX = displayX + (cellSize - metrics.stringWidth(text)) / 2;
                        int textY = displayY + ((cellSize - metrics.getHeight()) / 2) + metrics.getAscent();
                        g.drawString(text, textX, textY);
                    }
                }
            }
        }
    }
}
