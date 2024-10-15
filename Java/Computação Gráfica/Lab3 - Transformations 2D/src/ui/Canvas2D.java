package ui;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Ponto2D;

// Painel de Desenho (UI)
public class Canvas2D extends JPanel {

    private List<Ponto2D> pontos;
    private MathDisplayPanel mathDisplayPanel;
    private PontoDisplayPanel pontoDisplayPanel;
    private List<String> historicoEquacoes;  // Histórico das equações matemáticas

    public Canvas2D(MathDisplayPanel mathDisplayPanel, PontoDisplayPanel pontoDisplayPanel) {
        this.mathDisplayPanel = mathDisplayPanel;
        this.pontoDisplayPanel = pontoDisplayPanel;
        this.historicoEquacoes = new ArrayList<>();  // Inicializa o histórico das equações
        this.pontos = new ArrayList<>();  // Inicializa a lista de pontos
        setBackground(Color.WHITE);  // Fundo branco liso
        setBorder(BorderFactory.createEmptyBorder());  // Remover bordas
    }

    public void setPontos(List<Ponto2D> pontos) {
        this.pontos = pontos;
        atualizarPontoDisplayPanel();  // Atualiza as posições dos pontos no painel
        repaint();
    }

    public void adicionarPonto(double x, double y) {
        pontos.add(new Ponto2D(x, y));
        atualizarPontoDisplayPanel();  // Atualiza as posições dos pontos no painel
        repaint();
    }

    public List<Ponto2D> getPontos() {  // Adicionado o método getPontos()
        return pontos;
    }

    public void aplicarTransformacao(double[][] matriz, String descricao) {
        if (pontos != null && !pontos.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(descricao).append(":\n");

            // Armazena a matriz original (identidade)
            double[][] matrizOriginal = criarMatrizIdentidade();
            sb.append("Matriz Original:\n");
            sb.append(formatarMatriz(matrizOriginal)).append("\n");

            // Mostra o cálculo da multiplicação de matrizes
            sb.append("Matriz de Transformação:\n");
            sb.append(formatarMatriz(matriz)).append("\n");

            // Executa a multiplicação das matrizes passo a passo
            double[][] resultado = multiplicarMatrizes(matrizOriginal, matriz);
            sb.append("Resultado da Multiplicação:\n");
            sb.append(formatarMatriz(resultado)).append("\n");

            // Aplica a transformação aos pontos
            for (Ponto2D ponto : pontos) {
                ponto.transformar(resultado);
            }

            // Adiciona ao histórico e atualiza o painel
            historicoEquacoes.add(sb.toString());
            atualizarHistoricoEquacoes();
            atualizarPontoDisplayPanel();
            repaint();
        }
    }

    private double[][] criarMatrizIdentidade() {
        return new double[][]{
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
    }

    private double[][] multiplicarMatrizes(double[][] a, double[][] b) {
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

    private String formatarMatriz(double[][] matriz) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            sb.append("| ");
            for (int j = 0; j < matriz[0].length; j++) {
                sb.append(String.format("%.2f", matriz[i][j])).append(" ");
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

    public void limpar() {
        pontos.clear();
        historicoEquacoes.clear();  // Limpa o histórico das equações
        mathDisplayPanel.atualizarEquacoes("");
        pontoDisplayPanel.atualizarPontos(pontos);  // Limpa as posições exibidas no painel
        repaint();
    }

    private void atualizarHistoricoEquacoes() {
        StringBuilder sb = new StringBuilder();
        for (String eq : historicoEquacoes) {
            sb.append(eq).append("\n");
        }
        mathDisplayPanel.atualizarEquacoes(sb.toString());
    }

    private void atualizarPontoDisplayPanel() {
        pontoDisplayPanel.atualizarPontos(pontos);
    }

    public Ponto2D calcularCentro() {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Desenha os eixos cartesianos
        desenharEixosCartesianos(g2d);

        // Desenho dos pontos e polígono
        if (pontos == null || pontos.isEmpty()) return;

        g2d.setColor(Color.BLACK);
        double scale = 20; // Fator de escala para visualizar os pontos

        // Desenha as linhas entre os pontos
        for (int i = 0; i < pontos.size(); i++) {
            Ponto2D p = pontos.get(i);
            int x = (int) (p.x * scale + getWidth() / 2);  // Ajusta o ponto para o centro da tela
            int y = (int) (getHeight() / 2 - p.y * scale); // Inverte o eixo Y

            // Desenha a linha para o próximo ponto
            if (i > 0) {
                Ponto2D pAnterior = pontos.get(i - 1);
                int xAnterior = (int) (pAnterior.x * scale + getWidth() / 2);
                int yAnterior = (int) (getHeight() / 2 - pAnterior.y * scale);
                g2d.drawLine(xAnterior, yAnterior, x, y);
            }

            // Fechar o polígono, se houver mais de dois pontos
            if (i == pontos.size() - 1 && pontos.size() > 2) {
                Ponto2D primeiroPonto = pontos.get(0);
                int xPrimeiro = (int) (primeiroPonto.x * scale + getWidth() / 2);
                int yPrimeiro = (int) (getHeight() / 2 - primeiroPonto.y * scale);
                g2d.drawLine(x, y, xPrimeiro, yPrimeiro);
            }
        }
    }


    private void desenharEixosCartesianos(Graphics2D g2d) {
        // Eixo X
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);  // Linha horizontal no meio

        // Eixo Y
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());  // Linha vertical no meio
    }
}
