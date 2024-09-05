package lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesenhoPrimitivas extends JFrame {
    private JTextField x0Field, y0Field, x1Field, y1Field, rField, aField, bField;
    private JLabel x0Label, y0Label, x1Label, y1Label, rLabel, aLabel, bLabel; // Labels para os campos
    private JComboBox<String> algoritmoComboBox;
    private JPanel inputPanel;
    private Painel centralPanel;

    public DesenhoPrimitivas() {
        setTitle("Desenho Primitivas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1024, 780);

        // Painel de entrada
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2)); // Ajuste para incluir os campos de entrada

        x0Field = new JTextField();
        y0Field = new JTextField();
        x1Field = new JTextField();
        y1Field = new JTextField();
        rField = new JTextField();
        aField = new JTextField();
        bField = new JTextField();

        // Labels para os campos
        x0Label = new JLabel("x0:");
        y0Label = new JLabel("y0:");
        x1Label = new JLabel("x1:");
        y1Label = new JLabel("y1:");
        rLabel = new JLabel("R:");
        aLabel = new JLabel("a: ");
        bLabel = new JLabel("b: ");

        // Adiciona os campos e labels de entrada
        inputPanel.add(x0Label);
        inputPanel.add(x0Field);
        inputPanel.add(y0Label);
        inputPanel.add(y0Field);
        inputPanel.add(x1Label);
        inputPanel.add(x1Field);
        inputPanel.add(y1Label);
        inputPanel.add(y1Field);
        inputPanel.add(rLabel);
        inputPanel.add(rField);
        inputPanel.add(aLabel);
        inputPanel.add(aField);
        inputPanel.add(bLabel);
        inputPanel.add(bField);

        // Adiciona a caixa de seleção dos algoritmos
        String[] algoritmos = {"DDA", "Bresenham", "Ponto Médio Circunferência", "Equação Circunferência", "Método Trigonométrico Circunferência", "Ponto Médio Elipse"};
        algoritmoComboBox = new JComboBox<>(algoritmos);
        inputPanel.add(new JLabel("Algoritmo:"));
        inputPanel.add(algoritmoComboBox);

        add(inputPanel, BorderLayout.NORTH);

        centralPanel = new Painel(700, 500);

        // Painel centralizado
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(centralPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.insets = new Insets(5, 5, 5, 5);

        // Botão de desenho
        JButton updateButton = new JButton("Desenhar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x0 = 0, y0 = 0, x1 = 0, y1 = 0,r = 0, a=0,b=0;
                   
                String algoritmoSelecionado = (String) algoritmoComboBox.getSelectedItem();
                if (algoritmoSelecionado.contains("DDA") || algoritmoSelecionado.contains("Bresenham")) {
                        x0 = Integer.parseInt(x0Field.getText());
                        y0 = Integer.parseInt(y0Field.getText());
                        x1 = Integer.parseInt(x1Field.getText());
                        y1 = Integer.parseInt(y1Field.getText());
                }
                else if(algoritmoSelecionado.contains("Elipse")){
                    a = Integer.parseInt(aField.getText());
                    b = Integer.parseInt(bField.getText());
                }
                else {r = Integer.parseInt(rField.getText());}
                switch (algoritmoSelecionado) {
                    case "DDA":
                        Dda.DDALine(x0, y0, x1, y1, centralPanel);
                        break;
                    case "Bresenham":
                        Bresenham.BresenhamLine(x0, y0, x1, y1, centralPanel);
                    break;
                    case "Ponto Médio Circunferência":
                        if (r > 0) Circulos.midPointCircle(r, centralPanel);
                        else JOptionPane.showMessageDialog(null, "Por favor, insira um valor de raio válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                    break;
                    case "Equação Circunferência":
                        if (r > 0) Circulos.circleEquation(r,centralPanel);
                        else JOptionPane.showMessageDialog(null, "Por favor, insira um valor de raio válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                    break;
                    case "Método Trigonométrico Circunferência":
                        if (r > 0) Circulos.trigonometricCircle(r, centralPanel);
                        else JOptionPane.showMessageDialog(null, "Por favor, insira um valor de raio válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                    break;
                    case "Ponto Médio Elipse":
                       ElipsePontoMedio.MidpointEllipse(a,b, centralPanel);
                    break;
                    default:
                    break;
                    }
                
            }
        });

        // Botão de limpeza
        JButton clearButton = new JButton("Limpar");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralPanel.clearPanel();
            }
        });

        gbcButtons.gridx = 0;
        gbcButtons.gridy = 0;
        buttonPanel.add(updateButton, gbcButtons);

        gbcButtons.gridx = 1;
        buttonPanel.add(clearButton, gbcButtons);

        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);

        // Adiciona ActionListener para mudar os campos de entrada com base no algoritmo selecionado
        algoritmoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String algoritmoSelecionado = (String) algoritmoComboBox.getSelectedItem();
                if (algoritmoSelecionado.equals("DDA") || algoritmoSelecionado.equals("Bresenham")) {
                    x0Label.setVisible(true);
                    y0Label.setVisible(true);
                    x1Label.setVisible(true);
                    y1Label.setVisible(true);
                    x0Field.setVisible(true);
                    y0Field.setVisible(true);
                    x1Field.setVisible(true);
                    y1Field.setVisible(true);
                    rLabel.setVisible(false);
                    rField.setVisible(false);
                    aField.setVisible(false);
                    aLabel.setVisible(false);
                    bField.setVisible(false);
                    bLabel.setVisible(false);
                } 
                else if (algoritmoSelecionado.contains("Elipse")){
                    x0Label.setVisible(false);
                    y0Label.setVisible(false);
                    x1Label.setVisible(false);
                    y1Label.setVisible(false);
                    x0Field.setVisible(false);
                    y0Field.setVisible(false);
                    x1Field.setVisible(false);
                    y1Field.setVisible(false);
                    rLabel.setVisible(false);
                    rField.setVisible(false);
                    aField.setVisible(true);
                    aLabel.setVisible(true);
                    bField.setVisible(true);
                    bLabel.setVisible(true);
                } 
                
                else {
                    x0Label.setVisible(false);
                    y0Label.setVisible(false);
                    x1Label.setVisible(false);
                    y1Label.setVisible(false);
                    x0Field.setVisible(false);
                    y0Field.setVisible(false);
                    x1Field.setVisible(false);
                    y1Field.setVisible(false);
                    rLabel.setVisible(true);
                    rField.setVisible(true);
                    aField.setVisible(false);
                    aLabel.setVisible(false);
                    bField.setVisible(false);
                    bLabel.setVisible(false);
                }
                inputPanel.revalidate();
                inputPanel.repaint();
            }
        });

        // Configura os campos de entrada inicialmente
        algoritmoComboBox.setSelectedIndex(0); // Para garantir que os campos sejam configurados no início
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DesenhoPrimitivas::new);
    }
}
