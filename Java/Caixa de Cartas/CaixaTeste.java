// Classe CaixaTeste

public class CaixaTeste{
    public static void main(String[] args) {
        CaixaDeCartas objeto = new CaixaDeCartas();
        objeto.embaralhar();
        for(int i = 1; i <= 52; i++){
            System.out.printf("%-20s", objeto.distribuir());
            if(i % 4 == 0) System.out.println();
        } // Fim do for
        System.out.println();
    }
}