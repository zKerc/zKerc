package transformacao;

import model.Ponto2D;
import java.util.List;

// Classe para aplicar transformações geométricas
public class Transformacao {

    // Matriz de translação
    public static double[][] transladar(double dx, double dy) {
        return new double[][] {
            {1, 0, dx},
            {0, 1, dy},
            {0, 0, 1}
        };
    }

    // Matriz de rotação
    public static double[][] rotacionar(double angulo, double cx, double cy) {
        double rad = Math.toRadians(angulo);
        double[][] transladarParaOrigem = transladar(-cx, -cy);
        double[][] rotacao = new double[][] {
            {Math.cos(rad), -Math.sin(rad), 0},
            {Math.sin(rad), Math.cos(rad), 0},
            {0, 0, 1}
        };
        double[][] transladarDeVolta = transladar(cx, cy);
        
        return multiplicarMatrizes(multiplicarMatrizes(transladarDeVolta, rotacao), transladarParaOrigem);
    }

    // Matriz de escalonamento
    public static double[][] escalar(double sx, double sy) {
        return new double[][] {
            {sx, 0, 0},
            {0, sy, 0},
            {0, 0, 1}
        };
    }

    // Matriz de cisalhamento
    public static double[][] cisalhar(double cx, double cy) {
        return new double[][] {
            {1, cx, 0},  // Se cx != 0, X será distorcido em relação a Y
            {cy, 1, 0},  // Se cy != 0, Y será distorcido em relação a X
            {0, 0, 1}
        };
    }

    // Matriz de reflexão em torno de um eixo
    public static double[][] refletir(String eixo) {
        switch (eixo.toLowerCase()) {
            case "x":
                return new double[][] {
                    {1, 0, 0},
                    {0, -1, 0},
                    {0, 0, 1}
                };
            case "y":
                return new double[][] {
                    {-1, 0, 0},
                    {0, 1, 0},
                    {0, 0, 1}
                };
            default:
                throw new IllegalArgumentException("Eixo de reflexão inválido. Use 'x' ou 'y'.");
        }
    }

    // Método para calcular o ponto médio (centro do objeto)
    public static Ponto2D calcularCentro(java.util.List<Ponto2D> pontos) {
        double somaX = 0;
        double somaY = 0;
        for (Ponto2D ponto : pontos) {
            somaX += ponto.x;
            somaY += ponto.y;
        }
        double centroX = somaX / pontos.size();
        double centroY = somaY / pontos.size();
        return new Ponto2D(centroX, centroY);
    }

    // Multiplicação de matrizes
    public static double[][] multiplicarMatrizes(double[][] a, double[][] b) {
        double[][] resultado = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    resultado[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return resultado;
    }
}