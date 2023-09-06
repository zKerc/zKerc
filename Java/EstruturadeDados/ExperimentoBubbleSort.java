package EstruturadeDados;

/**
 * Esta classe demonstra a implementação e análise do algoritmo de ordenação Bubble Sort.
 * Ela gera amostras de dados de diferentes tipos e tamanhos e mede o tempo e a memória
 * utilizados para ordenar essas amostras com o Bubble Sort.
 *
 * @author Kaio Emanuel
 * @version 1.0
 */
import java.util.Arrays;

public class ExperimentoBubbleSort {

    /**
     * O método principal que inicia o experimento de ordenação com Bubble Sort.
     * Ele gera diferentes tipos de amostras de dados e mede o tempo e a memória
     * utilizados para ordenar cada amostra.
     *
     * @param args Os argumentos da linha de comando (não utilizados neste
     *             programa).
     */
    public static void main(String[] args) {
        int[] tamanhosAmostra = { 100000, 500000, 1500000, 2000000 };

        // Testar cenários diferentes para cada tamanho de amostra
        for (int tamanho : tamanhosAmostra) {
            int[] naoOrdenado = gerarAmostraNaoOrdenada(tamanho);
            int[] crescente = gerarAmostraCrescente(tamanho);
            int[] decrescente = gerarAmostraDecrescente(tamanho);
            int[] constante = gerarAmostraConstante(tamanho);
            int[] parcialmenteOrdenado = gerarAmostraParcialmenteOrdenada(tamanho);

            // Realizar o Bubble Sort e medir tempo/memória para cada cenário
            medirEImprimirResultados(naoOrdenado, "Não Ordenado", tamanho);
            medirEImprimirResultados(crescente, "Crescente", tamanho);
            medirEImprimirResultados(decrescente, "Decrescente", tamanho);
            medirEImprimirResultados(constante, "Constante", tamanho);
            medirEImprimirResultados(parcialmenteOrdenado, "Parcialmente Ordenado", tamanho);
        }
    }

    /**
     * Gerar uma amostra não ordenada.
     *
     * @param tamanho O tamanho da amostra a ser gerada.
     * @return Um array de inteiros representando a amostra não ordenada.
     */
    private static int[] gerarAmostraNaoOrdenada(int tamanho) {
        int[] amostra = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            amostra[i] = (int) (Math.random() * tamanho);
        }
        return amostra;
    }

    /**
     * Gerar uma amostra crescente.
     *
     * @param tamanho O tamanho da amostra a ser gerada.
     * @return Um array de inteiros representando a amostra crescente.
     */
    private static int[] gerarAmostraCrescente(int tamanho) {
        int[] amostra = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            amostra[i] = i;
        }
        return amostra;
    }

    /**
     * Gerar uma amostra decrescente.
     *
     * @param tamanho O tamanho da amostra a ser gerada.
     * @return Um array de inteiros representando a amostra decrescente.
     */
    private static int[] gerarAmostraDecrescente(int tamanho) {
        int[] amostra = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            amostra[i] = tamanho - i;
        }
        return amostra;
    }

    /**
     * Gerar uma amostra constante.
     *
     * @param tamanho O tamanho da amostra a ser gerada.
     * @return Um array de inteiros representando a amostra constante.
     */
    private static int[] gerarAmostraConstante(int tamanho) {
        int[] amostra = new int[tamanho];
        Arrays.fill(amostra, 42); // Use qualquer valor constante que você desejar
        return amostra;
    }

    /**
     * Gerar uma amostra parcialmente ordenada.
     *
     * @param tamanho O tamanho da amostra a ser gerada.
     * @return Um array de inteiros representando a amostra parcialmente ordenada.
     */
    private static int[] gerarAmostraParcialmenteOrdenada(int tamanho) {
        int[] amostra = new int[tamanho];
        int quantidadeOrdenada = (int) (tamanho * 0.9);

        for (int i = 0; i < quantidadeOrdenada; i++) {
            amostra[i] = i;
        }

        for (int i = quantidadeOrdenada; i < tamanho; i++) {
            amostra[i] = (int) (Math.random() * tamanho);
        }

        return amostra;
    }

    /**
     * Medir tempo/memória para ordenar e imprimir resultados.
     *
     * @param array   O array de inteiros a ser ordenado.
     * @param cenário O cenário da amostra (por exemplo, "Não Ordenado").
     * @param tamanho O tamanho da amostra.
     */
    private static void medirEImprimirResultados(int[] array, String cenário, int tamanho) {
        long horaInicio = System.currentTimeMillis();
        // Chame o seu método bubbleSort aqui
        bubbleSort(array);
        long horaFim = System.currentTimeMillis();

        Runtime runtime = Runtime.getRuntime();
        long memóriaUsada = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Cenário: " + cenário + " | Tamanho da Amostra: " + tamanho);
        System.out.println("Tempo gasto: " + (horaFim - horaInicio) + " ms");
        System.out.println("Memória utilizada: " + memóriaUsada + " bytes\n");
    }

    /**
     * Implementação do algoritmo Bubble Sort para ordenar um array de inteiros.
     * Este método ordena o array fornecido em ordem crescente.
     *
     * @param array O array de inteiros a ser ordenado.
     */
    private static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Trocar os elementos
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
