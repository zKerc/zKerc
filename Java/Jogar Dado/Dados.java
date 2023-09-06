// Simular lançamento de dado 10.000.000 vezes.
// import java.lang.*;

import java.util.Random;
// import java.security.SecureRandom;

public class Dados{
    private final int TAM = 10_000_000;
    private int l1, l2, l3, l4, l5, l6;
    
    public void lancarDados(){
        Random sorteio = new Random(10); // Substituir por new Random();
        int soma = 0, valor;
        for(int i = 0; i < TAM; i++){
            valor = sorteio.nextInt(6) + 1;
            //soma += valor;
            switch(valor){
                case 1: l1++; break;
                case 2: l2++; break;
                case 3: l3++; break;
                case 4: l4++; break;
                case 5: l5++; break;
                case 6: l6++; break;
                default: System.out.println("Erro");
            } // Fim do switch-case
        } // Fim do for
        System.out.printf("Total: %d%n", soma);
        System.out.printf("L1: %d%nL2: %d%nL3: %d%nL4: %d%nL5: %d%nL6: %d%n",
                           l1, l2, l3, l4, l5, l6);
        soma = l1 + l2 + l3 + l4 + l5 + l6;
        System.out.printf("Soma: %.2f Prob.: %.0f%n", (double) soma, (double) soma / 6.0);
    } // Fim do método

    public static void main(String[] args) {
        Dados obj1 = new Dados();
        obj1.lancarDados();
    }

} // Fim da classe