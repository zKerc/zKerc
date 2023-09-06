package Atv1.ContadorDeVogais;

// ContadorDeVogaisTeste.java

import java.util.Scanner;

public class ContadorDeVogaisTeste {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um texto: ");
        String texto = scanner.nextLine();
        ContadorDeVogais contador = new ContadorDeVogais(texto);
        int numeroDeVogais = contador.contarVogais();
        System.out.println("O texto \"" + texto + "\" possui " + numeroDeVogais + " vogais.");
    } // Fim do método main

} // Fim da classe
