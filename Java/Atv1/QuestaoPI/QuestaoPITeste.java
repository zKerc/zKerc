package Atv1.QuestaoPI;

// QuestaoPITeste.java
import java.util.Scanner;

public class QuestaoPITeste {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o termo da constante: ");
        int num = entrada.nextInt();
        System.out.println("Saída: " + QuestaoPI.PI(num));
        System.out.println("Saída: " + Math.PI);
    } // Fim do método main
} // Fim da classe
