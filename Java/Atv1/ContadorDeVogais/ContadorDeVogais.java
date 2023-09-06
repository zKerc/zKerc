package Atv1.ContadorDeVogais;

// ContadorDeVogais.java

public class ContadorDeVogais {

    private String texto;

    public ContadorDeVogais(String texto) {
        this.texto = texto;
    } // Fim do método

    public int contarVogais() {
        int contador = 0;

        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);

            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u' ||
                    letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U') {
                contador++;
            } // Fim do if
        } // Fim do for

        return contador;
    } // Fim do método int

} // Fim da Classe
