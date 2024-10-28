package edu.uepb.imageprocessor.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import edu.uepb.imageprocessor.models.ImageLoader;
import edu.uepb.imageprocessor.models.ImageViewer;
import edu.uepb.imageprocessor.operations.ImageAddition;
import edu.uepb.imageprocessor.operations.ImageDivision;
import edu.uepb.imageprocessor.operations.ImageMultiplication;
import edu.uepb.imageprocessor.operations.ImageSubtraction;
import java.io.File;

public class ArithmeticOperationsUI extends JFrame {

    private JComboBox<String> operationComboBox;
    private BufferedImage image1, image2;
    private JLabel imageLabel1, imageLabel2;
    private String imageName1, imageName2;

    public ArithmeticOperationsUI() {
        setTitle("Operações Aritméticas com Imagens");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Evita o encerramento completo do programa
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel para seleção de imagens
        JPanel imagePanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton loadImageButton1 = new JButton("Carregar Primeira Imagem");
        imageLabel1 = new JLabel("Imagem 1 não carregada", SwingConstants.CENTER);
        loadImageButton1.addActionListener(e -> loadImage(1));

        JButton loadImageButton2 = new JButton("Carregar Segunda Imagem");
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

        // Painel de operações aritméticas e botão de retorno
        JPanel controlPanel = new JPanel(new FlowLayout());
        operationComboBox = new JComboBox<>(new String[]{"Soma", "Subtração", "Multiplicação", "Divisão"});
        controlPanel.add(new JLabel("Selecione a Operação:"));
        controlPanel.add(operationComboBox);

        JButton applyOperationButton = new JButton("Aplicar Operação");
        applyOperationButton.addActionListener(e -> applyOperation());
        controlPanel.add(applyOperationButton);

        JButton backButton = new JButton("← Voltar");
        backButton.addActionListener(e -> goBack());
        controlPanel.add(backButton);

        add(controlPanel, BorderLayout.SOUTH);
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
                    displayImage(imageLabel1, image1, "Primeira Imagem: " + imageName1);
                } else {
                    image2 = loadedImage;
                    imageName2 = selectedFile.getName();
                    displayImage(imageLabel2, image2, "Segunda Imagem: " + imageName2);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem.");
            }
        }
    }

    private void displayImage(JLabel label, BufferedImage image, String imageName) {
        ImageIcon icon = new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        label.setIcon(icon);
        label.setText(imageName);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    private void applyOperation() {
        if (image1 == null || image2 == null) {
            JOptionPane.showMessageDialog(this, "Ambas as imagens devem estar carregadas!");
            return;
        }

        BufferedImage resultImage = performSelectedOperation();
        if (resultImage != null) {
            ImageViewer viewer = new ImageViewer(image1, image2, resultImage, "Primeira Imagem", "Segunda Imagem", "Imagem Final");
            viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Evita o encerramento completo do programa
            viewer.setVisible(true);
        }
    }

    private BufferedImage performSelectedOperation() {
        String selectedOperation = (String) operationComboBox.getSelectedItem();
        switch (selectedOperation) {
            case "Soma":
                return ImageAddition.addImages(image1, image2);
            case "Subtração":
                return ImageSubtraction.subtractImages(image1, image2);
            case "Multiplicação":
                return ImageMultiplication.multiplyImages(image1, image2);
            case "Divisão":
                return ImageDivision.divideImages(image1, image2);
            default:
                JOptionPane.showMessageDialog(this, "Operação não implementada.");
                return null;
        }
    }

    private void goBack() {
        dispose();
        new MainMenuUI().setVisible(true);
    }
}
