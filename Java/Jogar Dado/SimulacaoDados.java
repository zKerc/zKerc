/*Para demonstrar que a simulação do lançamento de um dado de seis faces dez milhões de vezes,
usando nextInt (de java.security.SecureRandom), produz números de faces com probabilidade semelhante,
podemos realizar o seguinte experimento:

Criar um objeto SecureRandom.
Inicializar um array de seis elementos para armazenar a contagem de cada face.
Repetir o seguinte procedimento dez milhões de vezes:
a. Gerar um número inteiro aleatório utilizando o método nextInt do SecureRandom.
b. Adicionar 1 à contagem correspondente à face do dado representada pelo número gerado.
Calcular a frequência relativa de cada face, dividindo a contagem correspondente pelo número total de lançamentos.
Verificar se as frequências relativas são próximas a 1/6, que é a probabilidade teórica de cada face ser sorteada
em um lançamento justo de um dado de seis faces.*/

import java.security.SecureRandom;;

public class SimulacaoDados {
    public static void main(String [] args){
        SecureRandom random = new SecureRandom();
        int[] counts = new int[6];
        int numRolls = 10000000;

        for (int i = 0; i < numRolls; i++) {
            int face = random.nextInt(6);
            counts[face]++;
        }

        for (int i = 0; i < 6; i++){
            double freq = (double) counts[i] / numRolls;
            System.out.println("Face " + (i+1) + ": " + freq);
        }
    }
}
