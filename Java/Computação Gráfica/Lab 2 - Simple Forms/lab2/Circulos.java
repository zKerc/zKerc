package lab2;

public class Circulos {

    public static void midPointCircle(int r, Painel p) {
        int x, y, d;
    
        // Valores iniciais
        x = 0;
        y = r;
        d = 1 - r;
        CirclePoints(x, y, p);
    
        while (y > x) {
            if (d < 0) {
                // Selecione E
                d = d + 2 * x + 3;
                x++;
            } else {
                // Selecione SE
                d = d + 2 * (x - y) + 5;
                x++;
                y--;
            }
            CirclePoints(x, y, p);
        }
    }
    

    // Método para desenhar a circunferência usando o método trigonométrico
    public static void trigonometricCircle(int raio, Painel p) {
        double theta = 0;
        boolean converted = false;
        final double passo = 1.0;

        // Loop enquanto o círculo não for completamente desenhado
        while (!converted) {
            // Converter o ângulo de graus para radianos
            double radianos = Math.toRadians(theta);

            // Calcular a coordenada x usando a fórmula do círculo
            int x = (int) Math.round(raio * Math.cos(radianos));

            // Calcular a coordenada y usando a fórmula do círculo
            int y = (int) Math.round(raio * Math.sin(radianos));

            // Plotar os pontos 
           CirclePoints(x, y, p);
            theta += passo;

            // Condição de parada: círculo completo quando o ângulo alcança 360 graus
            if (theta >= 360) {
                converted = true;
            }
        }
    }

    // Método para desenhar a circunferência usando o método Polinomial
    public static void circleEquation(int raio, Painel p) {
        // Inicialização das variáveis
        int x = 0;
        int i = 1;
        int xend = raio;

        // Loop para desenhar o círculo
        while (x <= xend) {
            // Cálculo da coordenada y usando a equação do círculo
            int y = (int) Math.round(Math.sqrt(raio * raio - x * x));

            // Plotar os pontos determinados pela simetria
            CirclePoints(x , y, p);

            // Incrementar x
            x += i;
        }
    }


    // Método para realizar espelhamento dos pontos da circunferência
    private static void CirclePoints(int x, int y, Painel p) {
        p.updatePixel( x, y);
        p.updatePixel(y, x);
        p.updatePixel(y, -x);;
        p.updatePixel(x, -y);
        p.updatePixel(-x, -y);
        p.updatePixel(-y, -x);
        p.updatePixel(-y, x);
        p.updatePixel(-x, y); 
      }
}