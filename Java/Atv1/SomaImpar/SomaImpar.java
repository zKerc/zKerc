package Atv1.SomaImpar;

// SomaImpar.java

public class SomaImpar {
    public static int somaImpar(int a, int b) {
        int soma = 0;
        for (int i = a; i <= b; i++) {
            if (i % 2 != 0) {
                soma += i;
            } // Fim do if
        } // Fim do for
        return soma;
    } // Fim do método int
} // Fim da Classe
