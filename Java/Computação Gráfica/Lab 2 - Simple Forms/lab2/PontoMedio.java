package lab2;
public class PontoMedio {

    public static void BresenhamLine(int x0, int y0, int x1, int y1, Painel p) {
        int dx = Math.abs(x1 - x0); // Diferença absoluta entre x1 e x0
        int dy = Math.abs(y1 - y0); // Diferença absoluta entre y1 e y0

        int sx = x0 < x1 ? 1 : -1; // Direção do passo em x
        int sy = y0 < y1 ? 1 : -1; // Direção do passo em y

        int err = dx - dy; // Erro inicial

        while (true) {
            p.updatePixel(x0, y0); // Desenha o pixel na posição atual

            // Verifica se o ponto final foi atingido
            if (x0 == x1 && y0 == y1) {
                break;
            }

            int e2 = 2 * err; // Multiplica o erro por 2

            // Ajusta o erro e o ponto x
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            // Ajusta o erro e o ponto y
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }
}
