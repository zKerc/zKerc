package model;

// Representação de um ponto 2D
public class Ponto2D {

    public double x, y;

    public Ponto2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Método para aplicar uma transformação usando uma matriz
    public void transformar(double[][] matriz) {
        double novoX = matriz[0][0] * x + matriz[0][1] * y + matriz[0][2];
        double novoY = matriz[1][0] * x + matriz[1][1] * y + matriz[1][2];
        this.x = novoX;
        this.y = novoY;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
}