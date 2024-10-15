package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import transformacao.Transformacao;
import model.Ponto2D;

// Painel de botões para aplicar as transformações
public class TransformPanel extends JPanel {

    private Canvas2D canvas;

    public TransformPanel(Canvas2D canvas) {
        this.canvas = canvas;
        setLayout(new GridLayout(7, 1, 10, 10));  // Mais espaçamento entre os botões

        // Configuração de fonte e cor para os botões
        Font buttonFont = new Font("Sans-Serif", Font.BOLD, 14);
        Color buttonBackground = new Color(230, 230, 230);  // Cor cinza claro para fundo
        Color buttonForeground = new Color(50, 50, 50);  // Texto mais escuro

        // Botão de Translação
        JButton translateButton = new JButton("Transladar");
        configurarBotao(translateButton, buttonFont, buttonBackground, buttonForeground);
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Criação de inputs para dx e dy na mesma janela
                JTextField dxField = new JTextField(5);
                JTextField dyField = new JTextField(5);

                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Valor de dx:"));
                panel.add(dxField);
                panel.add(new JLabel("Valor de dy:"));
                panel.add(dyField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Transladar", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        double dx = Double.parseDouble(dxField.getText());
                        double dy = Double.parseDouble(dyField.getText());
                        double[][] matrizTranslacao = Transformacao.transladar(dx, dy);
                        canvas.aplicarTransformacao(matrizTranslacao, "Translação: T(x' = x + " + dx + ", y' = y + " + dy + ")");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.");
                    }
                }
            }
        });
        add(translateButton);

        // Botão de Rotação
        JButton rotateButton = new JButton("Rotacionar");
        configurarBotao(rotateButton, buttonFont, buttonBackground, buttonForeground);
        rotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String anguloStr = JOptionPane.showInputDialog("Insira o ângulo de rotação (em graus):");
                try {
                    double angulo = Double.parseDouble(anguloStr);
                    Ponto2D pontoCentro = Transformacao.encontrarPontoMaisProximo(canvas.getPontos());
                    double[][] matrizRotacao = Transformacao.rotacionar(angulo, pontoCentro.x, pontoCentro.y);
                    canvas.aplicarTransformacao(matrizRotacao, "Rotação em torno do ponto mais próximo da origem: R(θ = " + angulo + "°)");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido.");
                }
            }
        });
        add(rotateButton);


        // Botão de Escalonamento
        JButton scaleButton = new JButton("Escalonar");
        configurarBotao(scaleButton, buttonFont, buttonBackground, buttonForeground);
        scaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Criação de inputs para sx e sy na mesma janela
                JTextField sxField = new JTextField(5);
                JTextField syField = new JTextField(5);

                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Fator de escala em x:"));
                panel.add(sxField);
                panel.add(new JLabel("Fator de escala em y:"));
                panel.add(syField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Escalonar", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        double sx = Double.parseDouble(sxField.getText());
                        double sy = Double.parseDouble(syField.getText());
                        double[][] matrizEscala = Transformacao.escalar(sx, sy);
                        canvas.aplicarTransformacao(matrizEscala, "Escalonamento: S(sx = " + sx + ", sy = " + sy + ")");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.");
                    }
                }
            }
        });
        add(scaleButton);

        // Botão de Cisalhamento
        JButton shearButton = new JButton("Cisalhar");
        configurarBotao(shearButton, buttonFont, buttonBackground, buttonForeground);
        shearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Criação de inputs para cx e cy na mesma janela
                JTextField cxField = new JTextField(5);
                JTextField cyField = new JTextField(5);

                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Valor de cisalhamento em x:"));
                panel.add(cxField);
                panel.add(new JLabel("Valor de cisalhamento em y:"));
                panel.add(cyField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Cisalhar", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        double cx = Double.parseDouble(cxField.getText());
                        double cy = Double.parseDouble(cyField.getText());
                        double[][] matrizCisalhamento = Transformacao.cisalhar(cx, cy);
                        canvas.aplicarTransformacao(matrizCisalhamento, "Cisalhamento: Sh(cx = " + cx + ", cy = " + cy + ")");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.");
                    }
                }
            }
        });
        add(shearButton);

        // Botão de Reflexão
        JButton reflectButton = new JButton("Refletir");
        configurarBotao(reflectButton, buttonFont, buttonBackground, buttonForeground);
        reflectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eixo = JOptionPane.showInputDialog("Insira o eixo de reflexão ('x' ou 'y'):");
                if (eixo.equalsIgnoreCase("x") || eixo.equalsIgnoreCase("y")) {
                    double[][] matrizReflexao = Transformacao.refletir(eixo);
                    canvas.aplicarTransformacao(matrizReflexao, "Reflexão: Eixo " + eixo.toUpperCase());
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira 'x' ou 'y' para o eixo de reflexão.");
                }
            }
        });
        add(reflectButton);

        // Botão de Limpar
        JButton clearButton = new JButton("Limpar");
        configurarBotao(clearButton, buttonFont, buttonBackground, buttonForeground);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.limpar();
            }
        });
        add(clearButton);
    }

    private void configurarBotao(JButton button, Font font, Color background, Color foreground) {
        button.setFont(font);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);  // Remove o foco visual
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));  // Borda leve
    }
}