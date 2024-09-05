import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class ComputaçãoGrafica extends JPanel {

    private int x, y;

    public ComputaçãoGrafica(int x, int y) {
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite x:");
        double inp_x = scanner.nextDouble();
        System.out.println("Digite y:");
        double inp_y = scanner.nextDouble();

        double inp_min = 100.3, inp_max = 200.2;
        double user_min = 0, user_max = 200;
        int dc_max_x = 800, dc_max_y = 600;

        // Arrays para armazenar os resultados das transformações
        double[] ndc = new double[2];
        double[] user = new double[2];
        int[] dc = new int[2];

        // Realiza as transformações de coordenadas para o Caso I: NDC [0,1] x [0,1]
        CoordinateTransformations.inpToNdc_CaseI(inp_x, inp_y, inp_min, inp_max, ndc);
        System.out.println("Caso I");
        System.out.printf("INP to NDC: (%.6f, %.6f) -> (%.6f, %.6f)\n", inp_x, inp_y, ndc[0], ndc[1]);

        CoordinateTransformations.ndcToUser(ndc[0], ndc[1], user_min, user_max, user);
        System.out.printf("NDC to USER: (%.6f, %.6f) -> (%.6f, %.6f)\n", ndc[0], ndc[1], user[0], user[1]);

        CoordinateTransformations.userToNdc(user[0], user[1], user_min, user_max, ndc);
        System.out.printf("USER to NDC: (%.6f, %.6f) -> (%.6f, %.6f)\n", user[0], user[1], ndc[0], ndc[1]);

        // Corrigido: Passar os valores corretos de dc_max_x e dc_max_y
        CoordinateTransformations.ndcToDc(ndc[0], ndc[1], dc_max_x, dc_max_y, 0, 0, dc);
        System.out.printf("NDC to DC: (%.6f, %.6f) -> (%d, %d)\n", ndc[0], ndc[1], dc[0], dc[1]);
        System.out.println();

        // Cria a janela para o Caso I
        JFrame frame1 = new JFrame("Acender Pixel - Caso I");
        ComputaçãoGrafica panel1 = new ComputaçãoGrafica(dc[0], dc[1]); // Posição do pixel transformado
        frame1.add(panel1);
        frame1.setSize(dc_max_x, dc_max_y);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        // Realiza as transformações de coordenadas para o Caso II: NDC [-1,1] x [-1,1]
        CoordinateTransformations.inpToNdc_CaseII(inp_x, inp_y, inp_min, inp_max, ndc);
        System.out.println("Caso II");
        System.out.printf("INP to NDC: (%.6f, %.6f) -> (%.6f, %.6f)\n", inp_x, inp_y, ndc[0], ndc[1]);

        // Corrigido: Passar os valores corretos de dc_max_x e dc_max_y
        CoordinateTransformations.ndcToDc(ndc[0], ndc[1], dc_max_x, dc_max_y, 0, 0, dc);
        System.out.printf("NDC to DC: (%.6f, %.6f) -> (%d, %d)\n", ndc[0], ndc[1], dc[0], dc[1]);

        // Cria a janela para o Caso II
        JFrame frame2 = new JFrame("Acender Pixel - Caso II");
        ComputaçãoGrafica panel2 = new ComputaçãoGrafica(dc[0], dc[1]); // Posição do pixel transformado
        frame2.add(panel2);
        frame2.setSize(dc_max_x, dc_max_y);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }


    public class CoordinateTransformations {

        // Caso I: NDC [0,1] x [0,1]
        public static void inpToNdc_CaseI(double inp_x, double inp_y, double inp_min, double inp_max, double[] ndc) {
            ndc[0] = (inp_x - inp_min) / (inp_max - inp_min);
            ndc[1] = (inp_y - inp_min) / (inp_max - inp_min);
        }

        // Caso II: NDC [-1,1] x [-1,1]
        public static void inpToNdc_CaseII(double inp_x, double inp_y, double inp_min, double inp_max, double[] ndc) {
            ndc[0] = 2 * (inp_x - inp_min) / (inp_max - inp_min) - 1;
            ndc[1] = 2 * (inp_y - inp_min) / (inp_max - inp_min) - 1;
        }

        public static void ndcToUser(double ndc_x, double ndc_y, double user_min, double user_max, double[] user) {
            user[0] = ((ndc_x + 1) / 2) * (user_max - user_min) + user_min;
            user[1] = ((ndc_y + 1) / 2) * (user_max - user_min) + user_min;
        }

        public static void userToNdc(double user_x, double user_y, double user_min, double user_max, double[] ndc) {
            ndc[0] = 2 * (user_x - user_min) / (user_max - user_min) - 1;
            ndc[1] = 2 * (user_y - user_min) / (user_max - user_min) - 1;
        }

        public static void ndcToDc(double ndc_x, double ndc_y, int dc_max_x, int dc_max_y, int dc_min_x, int dc_min_y, int[] dc) {
            dc[0] = (int) (((ndc_x + 1) / 2) * (dc_max_x - dc_min_x) + dc_min_x);
            dc[1] = (int) (((ndc_y + 1) / 2) * (dc_max_y - dc_min_y) + dc_min_y);
        }
    }
}