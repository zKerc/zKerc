package lab2;
public class Dda {

  public static int round(float n) { //Função de arredondamento
    if (n - (int) n < 0.5) 
      return (int) n; 
    return (int) (n + 1); 
  } 
  
  public static void DDALine(int x0, int y0, int x1, int y1, Painel painel) { 

    int dx = x1 - x0; 
    int dy = y1 - y0; 

    int step; 

    if (Math.abs(dx) > Math.abs(dy)) 
        step = Math.abs(dx); 
    else
        step = Math.abs(dy); 

    float x_incr = (float) dx / step; 
    float y_incr = (float) dy / step; 
    float x = x0;
    float y = y0;
    
    for (int i = 0; i <= step; i++) {
        painel.updatePixel(Math.round(x), Math.round(y));  
        x += x_incr;
        y += y_incr;
    }
} 
}


