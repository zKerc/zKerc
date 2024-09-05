package lab2;
import javax.swing.*;
import java.awt.*;

public class Painel extends JPanel {

    private int panelWidth;
    private int panelHeight;

    public Painel(int width, int height) {
        this.panelWidth = width;
        this.panelHeight = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.WHITE);
    }

    // Método para acender um pixel no painel com a origem no centro
    public void updatePixel(int x, int y) {
        Graphics g = this.getGraphics();
        g.setColor(Color.RED);

        // Traduzir as coordenadas para que a origem seja no centro do painel
        int centerX = panelWidth / 2;
        int centerY = panelHeight / 2;

        // Ajusta as coordenadas
        int adjustedX = centerX + x;
        int adjustedY = centerY - y;

        g.fillRect(adjustedX, adjustedY, 1, 1);
    }

    // Método para limpar o painel pintando tudo de branco
    public void clearPanel() {
        Graphics g = this.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, panelWidth, panelHeight);
    }

}
