import javax.swing.*;
import java.awt.*;

public class AcenderPixel extends JPanel {

    private int x, y;

    public AcenderPixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK); // Define o fundo como preto
        g.fillRect(0, 0, getWidth(), getHeight()); // Pinta o fundo
        g.setColor(Color.RED); // Define a cor do pixel como vermelho
        g.fillRect(x, y, 1, 1); // Desenha um pixel vermelho
    }

    public static void main(String[] args) {
        // Parâmetros de entrada
        double inp_x = 50, inp_y = 75;
        double inp_min = 0, inp_max = 100;
        double user_min = 0, user_max = 200;
        int dc_max_x = 800, dc_max_y = 600;

        // Arrays para armazenar os resultados das transformações
        double[] ndc = new double[2];
        double[] user = new double[2];
        int[] dc = new int[2];

        // Realiza as transformações de coordenadas para o Caso I: NDC [0,1] x [0,1]
        CoordinateTransformations.inpToNdc_CaseI(inp_x, inp_y, inp_min, inp_max, ndc);
        System.out.printf("INP to NDC (Caso I): (%.6f, %.6f) -> (%.6f, %.6f)\n", inp_x, inp_y, ndc[0], ndc[1]);

        CoordinateTransformations.ndcToUser(ndc[0], ndc[1], user_min, user_max, user);
        System.out.printf("NDC to USER: (%.6f, %.6f) -> (%.6f, %.6f)\n", ndc[0], ndc[1], user[0], user[1]);

        CoordinateTransformations.userToNdc(user[0], user[1], user_min, user_max, ndc);
        System.out.printf("USER to NDC: (%.6f, %.6f) -> (%.6f, %.6f)\n", user[0], user[1], ndc[0], ndc[1]);

        CoordinateTransformations.ndcToDc(ndc[0], ndc[1], dc_max_x, dc_max_y, dc);
        System.out.printf("NDC to DC: (%.6f, %.6f) -> (%d, %d)\n", ndc[0], ndc[1], dc[0], dc[1]);

        // Cria a janela para o Caso I
        JFrame frame1 = new JFrame("Acender Pixel - Caso I");
        AcenderPixel panel1 = new AcenderPixel(dc[0], dc[1]); // Posição do pixel transformado
        frame1.add(panel1);
        frame1.setSize(dc_max_x, dc_max_y);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        // Realiza as transformações de coordenadas para o Caso II: NDC [-1,1] x [-1,1]
        CoordinateTransformations.inpToNdc_CaseII(inp_x, inp_y, inp_min, inp_max, ndc);
        System.out.printf("INP to NDC (Caso II): (%.6f, %.6f) -> (%.6f, %.6f)\n", inp_x, inp_y, ndc[0], ndc[1]);

        CoordinateTransformations.ndcToDc(ndc[0], ndc[1], dc_max_x, dc_max_y, dc);
        System.out.printf("NDC to DC: (%.6f, %.6f) -> (%d, %d)\n", ndc[0], ndc[1], dc[0], dc[1]);

        // Cria a janela para o Caso II
        JFrame frame2 = new JFrame("Acender Pixel - Caso II");
        AcenderPixel panel2 = new AcenderPixel(dc[0], dc[1]); // Posição do pixel transformado
        frame2.add(panel2);
        frame2.setSize(dc_max_x, dc_max_y);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }
}
