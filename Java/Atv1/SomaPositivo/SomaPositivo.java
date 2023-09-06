package Atv1.SomaPositivo;

// SomaPositivo.java

public class SomaPositivo {

    public static int somaPositivos(int x) {
        int soma = 0;

        for (int i = 1; i < x; i++) {
            soma += i;
        }

        return soma;
    }
}
