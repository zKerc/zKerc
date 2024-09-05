package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Ponto2D;

// Painel para exibir as posições dos pontos
public class PontoDisplayPanel extends JPanel {

    private JTextArea pontoTextArea;

    public PontoDisplayPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1024, 150));  // Aumenta a altura da janela para 150px
        setBackground(new Color(51, 51, 51));  // Fundo cinza escuro
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Margens internas

        pontoTextArea = new JTextArea();
        pontoTextArea.setEditable(false);
        pontoTextArea.setBackground(new Color(51, 51, 51));  // Fundo cinza escuro
        pontoTextArea.setForeground(new Color(238, 238, 238));  // Texto cinza claro
        pontoTextArea.setFont(new Font("Sans-Serif", Font.PLAIN, 14));  // Fonte moderna
        add(new JScrollPane(pontoTextArea), BorderLayout.CENTER);
    }

    public void atualizarPontos(List<Ponto2D> pontos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pontos.size(); i++) {
            Ponto2D ponto = pontos.get(i);
            sb.append("Ponto ").append(i + 1).append(": (")
                .append(String.format("%.2f", ponto.x)).append(", ")
                .append(String.format("%.2f", ponto.y)).append(")\n");
        }
        pontoTextArea.setText(sb.toString());
    }
}