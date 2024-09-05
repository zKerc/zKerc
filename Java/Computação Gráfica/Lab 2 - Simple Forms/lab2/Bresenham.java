package lab2;
public class Bresenham {

        public static void BresenhamLine(int x0, int y0, int x1, int y1, Painel p) {
            int x, y;
            float a;
        
            // Calcula a inclinação da linha (m)
            a = (float)(y1 - y0) / (x1 - x0);
        
            // Desenha a linha pixel por pixel
            for (x = x0; x <= x1; x++) {
                // Arredonda y
                y = Math.round(y0 + a * (x - x0));
                

            p.updatePixel(x, y);
        }
    }
}  
