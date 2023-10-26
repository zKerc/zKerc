package tad.conjuntoDinamico;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

    private Integer[] meusDados;
    private int posInsercao;
    private static final int INITIAL_SIZE = 10;
    private static final double INCREASE_RATE = 1.5;

    public MeuConjuntoDinamico() {
        meusDados = new Integer[INITIAL_SIZE];
        posInsercao = 0;
    }

    @Override
    public void inserir(Integer item) {
        if (posInsercao == meusDados.length) {
            meusDados = aumentarArray();
        }
        meusDados[posInsercao++] = item;
    }

    private Integer[] aumentarArray() {
        int newSize = (int) (meusDados.length * INCREASE_RATE);
        Integer[] newArray = new Integer[newSize];
        System.arraycopy(meusDados, 0, newArray, 0, meusDados.length);
        return newArray;
    }

    @Override
    public Integer remover(Integer item) {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                Integer removedItem = meusDados[i];
                System.arraycopy(meusDados, i + 1, meusDados, i, posInsercao - i - 1);
                posInsercao--;
                return removedItem;
            }
        }
        throw new RuntimeException("Item not found");
    }

    @Override
    public Integer predecessor(Integer item) {
        int pos = -1;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                pos = i;
                break;
            }
        }
        if (pos == -1) throw new RuntimeException("Item not found");
        return pos > 0 ? meusDados[pos - 1] : null;
    }

    @Override
    public Integer sucessor(Integer item) {
        int pos = -1;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                pos = i;
                break;
            }
        }
        if (pos == -1) throw new RuntimeException("Item not found");
        return pos < posInsercao - 1 ? meusDados[pos + 1] : null;
    }

    @Override
    public int tamanho() {
        return posInsercao;
    }

    @Override
    public Integer buscar(Integer item) {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) return item;
        }
        throw new RuntimeException("Item not found");
    }

    @Override
    public Integer minimum() {
        if (posInsercao == 0) throw new RuntimeException("Set is empty");
        Integer min = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] < min) {
                min = meusDados[i];
            }
        }
        return min;
    }

    @Override
    public Integer maximum() {
        if (posInsercao == 0) throw new RuntimeException("Set is empty");
        Integer max = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] > max) {
                max = meusDados[i];
            }
        }
        return max;
    }
}
