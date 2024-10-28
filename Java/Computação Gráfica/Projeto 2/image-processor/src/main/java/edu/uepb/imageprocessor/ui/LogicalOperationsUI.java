package edu.uepb.imageprocessor.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import edu.uepb.imageprocessor.models.ImageLoader;
import edu.uepb.imageprocessor.models.ImageViewer;
import edu.uepb.imageprocessor.operations.ImageLogicalOperations;
import java.io.File;

public class LogicalOperationsUI extends JFrame {

    private JComboBox<String> operationComboBox;
    private BufferedImage image1, image2;
    private JLabel imageLabel1, imageLabel2;
    private String imageName1, imageName2;

    public LogicalOperationsUI() {
        setTitle("Operadores Lógicos com Imagens");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para o botão de retorno
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("← Voltar");
        backButton.addActionListener(e -> dispose()); // Fecha esta tela e volta para a anterior
        navigationPanel.add(backButton);
        add(navigationPanel, BorderLayout.NORTH);

        // Painel para seleção de imagens
        JPanel imagePanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JButton loadImageButton1 = new JButton("Carregar Imagem 1");
        imageLabel1 = new JLabel("Imagem 1 não carregada", SwingConstants.CENTER);
        loadImageButton1.addActionListener(e -> loadImage(1));

        JButton loadImageButton2 = new JButton("Carregar Imagem 2");
        imageLabel2 = new JLabel("Imagem 2 não carregada", SwingConstants.CENTER);
        loadImageButton2.addActionListener(e -> loadImage(2));

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(loadImageButton1, BorderLayout.NORTH);
        panel1.add(imageLabel1, BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(loadImageButton2, BorderLayout.NORTH);
        panel2.add(imageLabel2, BorderLayout.CENTER);

        imagePanel.add(panel1);
        imagePanel.add(panel2);
        add(imagePanel, BorderLayout.CENTER);

        // Seletor de operações lógicas e botão de aplicação
        JPanel operationPanel = new JPanel();
        operationComboBox = new JComboBox<>(new String[] {"AND", "OR", "XOR"});
        operationPanel.add(new JLabel("Selecione o Operador:"));
        operationPanel.add(operationComboBox);

        JButton applyOperationButton = new JButton("Aplicar Operador Lógico");
        applyOperationButton.addActionListener(e -> applyLogicalOperation());
        operationPanel.add(applyOperationButton);

        add(operationPanel, BorderLayout.SOUTH);
    }

    private void loadImage(int imageNumber) {
        JFileChooser fileChooser = new JFileChooser("images");
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();
            try {
                BufferedImage loadedImage = ImageLoader.loadPGM(imagePath);
                if (imageNumber == 1) {
                    image1 = loadedImage;
                    imageName1 = selectedFile.getName();
                    displayImage(imageLabel1, image1, imageName1);
                } else {
                    image2 = loadedImage;
                    imageName2 = selectedFile.getName();
                    displayImage(imageLabel2, image2, imageName2);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem.");
            }
        }
    }

    private void displayImage(JLabel label, BufferedImage image, String imageName) {
        // Exibe a imagem no label, redimensionando conforme necessário
        ImageIcon icon = new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        label.setIcon(icon);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setText("Imagem carregada: " + imageName);
    }

    private void applyLogicalOperation() {
        if (image1 == null || image2 == null) {
            JOptionPane.showMessageDialog(this, "Ambas as imagens devem estar carregadas!");
            return;
        }

        BufferedImage resultImage = performSelectedLogicalOperation();
        if (resultImage != null) {
            ImageViewer viewer = new ImageViewer(image1, image2, resultImage, "Primeira Imagem", "Segunda Imagem", "Imagem Final");
            viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Evita o encerramento completo do programa
            viewer.setVisible(true);
        }
    }

    private BufferedImage performSelectedLogicalOperation() {
        String selectedOperation = (String) operationComboBox.getSelectedItem();
        switch (selectedOperation) {
            case "AND":
                return ImageLogicalOperations.andImages(image1, image2);
            case "OR":
                return ImageLogicalOperations.orImages(image1, image2);
            case "XOR":
                return ImageLogicalOperations.xorImages(image1, image2);
            default:
                JOptionPane.showMessageDialog(this, "Operador não implementado.");
                return null;
        }
    }
}
