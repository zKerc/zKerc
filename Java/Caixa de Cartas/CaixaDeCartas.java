// CaixaDeCartas.java

import java.util.Random;
// import java.security.SecureRandom;

public class CaixaDeCartas{
    private Random sorteio = new Random();
    private final int QUANTIDADE = 52;
    private static int referencial;
    private Carta caixa[];

    public CaixaDeCartas(){
        String faces[] = {"Ás", "2", "3", "4", "5", "6", "7",
                          "8", "9", "10", "Valete", "Rainha", "Rei"};
        String naipes[] = {"Copas", "Ouros", "Paus", "Espadas"};
        caixa = new Carta[QUANTIDADE];
        for(int i = 0; i < QUANTIDADE; i++){
            caixa[i] = new Carta(faces[i % 13], naipes[i / 13]);
        } // Fim do for
    } // Fim do construtor

    public Carta distribuir(){
        if(referencial < caixa.length){
            return caixa[referencial++];
        }
        return null;
    } // Fim do método distribuir
    public void embaralhar(){
        for(int i = 0; i < QUANTIDADE; i++){
            int temp = sorteio.nextInt(QUANTIDADE);
            Carta obj = caixa[i];
            caixa[i] = caixa[temp];
            caixa[temp] = obj;
        }
    } // Fim do método embaralhar

    @Override
    public String toString(){
        return "Classe: CaixaDeCartas - representa uma caixa de baralho.";
    } // Fim do toString

} // Fim da classe CaixaDeCartas