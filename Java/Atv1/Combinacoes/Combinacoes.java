package Atv1.Combinacoes;

// Combinacoes.java

public class Combinacoes {

    public static void gerarCombinacoes(String prefixo, String sufixo) {
        if (sufixo.length() == 0) {
            System.out.println(prefixo);
        } // Fim do if
        else {
            for (int i = 0; i < sufixo.length(); i++) {
                gerarCombinacoes(prefixo + sufixo.charAt(i), sufixo.substring(0, i) + sufixo.substring(i + 1));
            } // Fim do for
        } // Fim do else
    } // Fim do método
} // Fim da classe
