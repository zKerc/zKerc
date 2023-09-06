package Atv1.SomaImpar;

// SomaImparTeste.java
import java.util.Scanner;

public class SomaImparTeste {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o valor de a: ");
        int a = input.nextInt();
        System.out.print("Digite o valor de b: ");
        int b = input.nextInt();
        int soma = SomaImpar.somaImpar(a, b);
        System.out.println("A soma dos números ímpares entre " + a + " e " + b + " é " + soma);
        input.close();
    } // Fim do método main
} // Fim da CLasse
