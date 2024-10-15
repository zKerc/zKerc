package ui;

import javax.swing.*;
import java.awt.*;

// Painel para exibir as equações matemáticas
public class MathDisplayPanel extends JPanel {

    private JTextArea mathTextArea;

    public MathDisplayPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(51, 51, 51));  // Fundo cinza escuro
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Margens internas

        mathTextArea = new JTextArea();
        mathTextArea.setEditable(false);
        mathTextArea.setBackground(new Color(51, 51, 51));  // Fundo cinza escuro
        mathTextArea.setForeground(new Color(238, 238, 238));  // Texto cinza claro
        mathTextArea.setFont(new Font("Sans-Serif", Font.PLAIN, 14));  // Fonte moderna
        add(new JScrollPane(mathTextArea), BorderLayout.CENTER);
        setPreferredSize(new Dimension(250, 0));  // Largura fixa para o painel
    }

    public void atualizarEquacoes(String equacao) {
        mathTextArea.setText(equacao);
    }
}