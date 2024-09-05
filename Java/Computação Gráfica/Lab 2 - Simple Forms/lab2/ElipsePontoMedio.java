package lab2;

public class ElipsePontoMedio {

    public static void MidpointEllipse(int a, int b, Painel painel) {
        int x, y;
        float d1, d2;
    
        // Valores iniciais
        x = 0;
        y = b;
        d1 = b * b - a * a * b + a * a / 4.0f;
        EllipsePoints(x, y, painel); // Simetria de ordem 4
    
        // Região 1
        while (a * a * (y - 0.5) > b * b * (x + 1)) {
            if (d1 < 0) {
                d1 = d1 + b * b * (2 * x + 3);
                x++;
            } else {
                d1 = d1 + b * b * (2 * x + 3) + a * a * (-2 * y + 2);
                x++;
                y--;
            }
            EllipsePoints(x, y, painel);
        }
    
        // Região 2
        d2 = b * b * (x + 0.5f) * (x + 0.5f) + a * a * (y - 1) * (y - 1) - a * a * b * b;
        while (y > 0) {
            if (d2 < 0) {
                d2 = d2 + b * b * (2 * x + 2) + a * a * (-2 * y + 3);
                x++;
                y--;
            } else {
                d2 = d2 + a * a * (-2 * y + 3);
                y--;
            }
            EllipsePoints(x, y, painel);
        }
    }
    

  // Método para realizar espelhamento dos pontos da elipse
  public static void EllipsePoints(int x, int y, Painel painel) {
    painel.updatePixel(x, y);
    painel.updatePixel(-x, y);
    painel.updatePixel(x, -y);
    painel.updatePixel(-x, -y);
}
}
