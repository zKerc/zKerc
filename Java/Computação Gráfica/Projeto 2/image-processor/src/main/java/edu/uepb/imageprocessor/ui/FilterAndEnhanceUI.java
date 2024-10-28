package edu.uepb.imageprocessor.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import edu.uepb.imageprocessor.models.ImageLoader;
import edu.uepb.imageprocessor.models.ImageViewer;
import edu.uepb.imageprocessor.filters.*;
import java.io.File;

public class FilterAndEnhanceUI extends JFrame {
    
    private JComboBox<String> filterComboBox;
    private JTextField[][] filterMatrixFields;
    private JPanel matrixPanel; // Declara matrixPanel como uma variável de instância
    private BufferedImage selectedImage;
    private JLabel imageLabel;
    private String imageName;

    public FilterAndEnhanceUI() {
        setTitle("Filtros e Realces");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de navegação com botão de retorno
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("← Voltar");
        backButton.addActionListener(e -> dispose()); // Fecha esta tela e volta para a anterior
        navigationPanel.add(backButton);
        add(navigationPanel, BorderLayout.NORTH);

        // Seção de seleção de imagem
        JPanel imagePanel = new JPanel(new BorderLayout());
        JButton loadImageButton = new JButton("Carregar Imagem");
        imageLabel = new JLabel("Imagem não carregada", SwingConstants.CENTER);
        loadImageButton.addActionListener(e -> loadImage());
        imagePanel.add(loadImageButton, BorderLayout.NORTH);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);

        // Menu de filtros
        JPanel filterPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        filterComboBox = new JComboBox<>(new String[] {"Média", "Mediana", "Sobel", "Prewitt", "Roberts", "Roberts Cruzado", "High-Boost", "Passa-Alta"});
        filterComboBox.addActionListener(e -> updateFilterMatrix());
        filterPanel.add(new JLabel("Selecione o Filtro:", SwingConstants.CENTER));
        filterPanel.add(filterComboBox);

        // Matriz de personalização do filtro
        matrixPanel = new JPanel(new GridLayout(3, 3)); // Inicialize matrixPanel
        filterMatrixFields = new JTextField[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                filterMatrixFields[i][j] = new JTextField("0", 3);
                matrixPanel.add(filterMatrixFields[i][j]);
            }
        }
        filterPanel.add(matrixPanel);
        
        add(filterPanel, BorderLayout.CENTER);

        // Botão de aplicar filtro
        JButton applyFilterButton = new JButton("Aplicar Filtro");
        applyFilterButton.addActionListener(e -> applyFilter());
        add(applyFilterButton, BorderLayout.SOUTH);
    }

    private void loadImage() {
        JFileChooser fileChooser = new JFileChooser("images");
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();
            imageName = selectedFile.getName();
            try {
                selectedImage = ImageLoader.loadPGM(imagePath);
                displayImage(selectedImage);  // Exibe a imagem carregada com o nome
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem.");
            }
        }
    }

    private void displayImage(BufferedImage image) {
        // Exibe a imagem no label, redimensionando conforme necessário
        ImageIcon icon = new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        imageLabel.setIcon(icon);
        imageLabel.setText("Imagem carregada: " + imageName);
        imageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        imageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    private void updateFilterMatrix() {
        String selectedFilter = (String) filterComboBox.getSelectedItem();
        int[][] defaultMatrix = getDefaultMatrix(selectedFilter);
        
        matrixPanel.removeAll();  // Limpa o painel da matriz
        filterMatrixFields = new JTextField[defaultMatrix.length][defaultMatrix[0].length];
        
        // Redefine o layout do painel para corresponder ao tamanho da matriz
        matrixPanel.setLayout(new GridLayout(defaultMatrix.length, defaultMatrix[0].length));

        // Preenche os campos de texto da matriz com os valores padrão
        for (int i = 0; i < defaultMatrix.length; i++) {
            for (int j = 0; j < defaultMatrix[i].length; j++) {
                filterMatrixFields[i][j] = new JTextField(String.valueOf(defaultMatrix[i][j]), 3);
                matrixPanel.add(filterMatrixFields[i][j]);
            }
        }

        matrixPanel.revalidate();
        matrixPanel.repaint();
    }

    private int[][] getDefaultMatrix(String filterName) {
        switch (filterName) {
            case "Média":
                return new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
            case "Mediana":
                return new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
            case "Sobel":
                return new int[][] {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
            case "Prewitt":
                return new int[][] {{-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1}};
            case "Roberts":
                return new int[][] {{1, 0}, {0, -1}};
            case "Roberts Cruzado":
                return new int[][] {{0, 1}, {-1, 0}};
            case "High-Boost":
                return new int[][] {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};
            case "Passa-Alta":
                return new int[][] {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
            default:
                return new int[][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        }
    }

    private void applyFilter() {
        if (selectedImage == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma imagem carregada!");
            return;
        }

        BufferedImage processedImage = applySelectedFilter();
        if (processedImage != null) {
            // Cria a janela de visualização sem fechar o programa
            ImageViewer viewer = new ImageViewer(selectedImage, processedImage, "Imagem Original", "Imagem Filtrada");
            viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Evita o encerramento completo
            viewer.setVisible(true);
        }
    }

    private BufferedImage applySelectedFilter() {
        String selectedFilter = (String) filterComboBox.getSelectedItem();
        switch (selectedFilter) {
            case "Média":
                return MeanFilter.applyMeanFilter(selectedImage);
            case "Mediana":
                return MedianFilter.applyMedianFilter(selectedImage);
            case "Sobel":
                return SobelFilter.applySobelFilter(selectedImage);
            case "Prewitt":
                return PrewittFilter.applyPrewittFilter(selectedImage);
            case "Roberts":
                return RobertsGradientFilter.applyRobertsGradientFilter(selectedImage);
            case "Roberts Cruzado":
                return RobertsCrossGradientFilter.applyRobertsCrossGradientFilter(selectedImage);
            case "High-Boost":
                return HighBoostFilter.applyHighBoostFilter(selectedImage, 1.2);
            case "Passa-Alta":
                return HighPassFilter.applyHighPassFilter(selectedImage);
            default:
                JOptionPane.showMessageDialog(this, "Filtro não implementado.");
                return null;
        }
    }
}
