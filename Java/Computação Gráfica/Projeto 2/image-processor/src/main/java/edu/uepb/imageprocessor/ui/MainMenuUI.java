package edu.uepb.imageprocessor.ui;

import javax.swing.*;
import java.awt.*;
import edu.uepb.imageprocessor.ui.FilterAndEnhanceUI;
import edu.uepb.imageprocessor.ui.ArithmeticOperationsUI;
import edu.uepb.imageprocessor.ui.LogicalOperationsUI;

public class MainMenuUI extends JFrame {

    public MainMenuUI() {
        setTitle("Menu Principal - Editor de Processamento de Imagem");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Botão para Filtros e Realces
        JButton filterEnhancementButton = new JButton("Filtros e Realces");
        filterEnhancementButton.addActionListener(e -> openFilterEnhancementUI());
        add(filterEnhancementButton);

        // Botão para Operações Aritméticas
        JButton arithmeticOperationsButton = new JButton("Operações Aritméticas");
        arithmeticOperationsButton.addActionListener(e -> openArithmeticOperationsUI());
        add(arithmeticOperationsButton);

        // Botão para Operações Lógicas
        JButton logicalOperationsButton = new JButton("Operações Lógicas");
        logicalOperationsButton.addActionListener(e -> openLogicalOperationsUI());
        add(logicalOperationsButton);
    }

    private void openFilterEnhancementUI() {
        FilterAndEnhanceUI filterUI = new FilterAndEnhanceUI();
        filterUI.setVisible(true);
    }

    private void openArithmeticOperationsUI() {
        ArithmeticOperationsUI arithmeticUI = new ArithmeticOperationsUI();
        arithmeticUI.setVisible(true);
    }

    private void openLogicalOperationsUI() {
        LogicalOperationsUI logicalUI = new LogicalOperationsUI();
        logicalUI.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuUI mainMenu = new MainMenuUI();
            mainMenu.setVisible(true);
        });
    }
}
