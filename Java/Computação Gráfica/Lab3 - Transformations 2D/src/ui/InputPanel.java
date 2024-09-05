package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Ponto2D;
import java.util.List;
import java.util.ArrayList;

// Painel de Inserção de Pontos
public class InputPanel extends JPanel {

    private JTextField inputXField;
    private JTextField inputYField;
    private JButton addButton;
    private List<Ponto2D> pontos;
    private Canvas2D canvas;
    private PontoDisplayPanel pontoDisplayPanel;

    public InputPanel(Canvas2D canvas, PontoDisplayPanel pontoDisplayPanel) {
        this.canvas = canvas;
        this.pontoDisplayPanel = pontoDisplayPanel;
        this.pontos = new ArrayList<>();

        setLayout(new FlowLayout());

        JLabel labelX = new JLabel("Coordenada X: ");
        add(labelX);

        inputXField = new JTextField(5);  // Campo para coordenada X
        add(inputXField);

        JLabel labelY = new JLabel("Coordenada Y: ");
        add(labelY);

        inputYField = new JTextField(5);  // Campo para coordenada Y
        add(inputYField);

        addButton = new JButton("Adicionar Ponto");
        add(addButton);

        // Action listener para adicionar o ponto ao canvas
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = Double.parseDouble(inputXField.getText().trim());
                    double y = Double.parseDouble(inputYField.getText().trim());
                    Ponto2D ponto = new Ponto2D(x, y);
                    pontos.add(ponto);
                    canvas.setPontos(pontos);  // Atualiza os pontos no canvas
                    pontoDisplayPanel.atualizarPontos(pontos);  // Atualiza o painel de pontos
                    inputXField.setText("");  // Limpa o campo X
                    inputYField.setText("");  // Limpa o campo Y
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.");
                }
            }
        });
    }
}