package app;

import javax.swing.*;
import java.awt.*;
import ui.Canvas2D;
import ui.InputPanel;
import ui.MathDisplayPanel;
import ui.PontoDisplayPanel;
import ui.TransformPanel;

// Classe Principal
public class Transformacao2DApp {

    private JFrame frame;
    private Canvas2D canvas;
    private MathDisplayPanel mathPanel;
    private PontoDisplayPanel pontoPanel;
    private InputPanel inputPanel;
    private TransformPanel transformPanel;

    public Transformacao2DApp() {
        initUI();
    }

    private void initUI() {
        frame = new JFrame("Transformações 2D - Editor Gráfico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);  // Aumentar a janela para melhor visualização
        frame.setLayout(new BorderLayout());

        // Inicializa os painéis
        mathPanel = new MathDisplayPanel();
        pontoPanel = new PontoDisplayPanel();
        canvas = new Canvas2D(mathPanel, pontoPanel);
        inputPanel = new InputPanel(canvas, pontoPanel);
        transformPanel = new TransformPanel(canvas);

        // Adiciona os painéis à interface
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(transformPanel, BorderLayout.WEST);
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(mathPanel, BorderLayout.EAST);
        frame.add(pontoPanel, BorderLayout.SOUTH);

        // Exibe a janela
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Mantém o LookAndFeel padrão do sistema, sem bibliotecas externas
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Inicia a aplicação
        SwingUtilities.invokeLater(() -> new Transformacao2DApp());
    }
}