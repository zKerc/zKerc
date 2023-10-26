package tad.fila;

public class MinhaFila implements FilaIF<Integer> {
    
    private int tamanho;
    private int cauda = 0;
    private int cabeca = 0;
    private int elementos = 0;  // Contador de elementos na fila
    
    private Integer[] meusDados;
    
    public MinhaFila(int tamanhoInicial) {
        this.tamanho = tamanhoInicial;
        meusDados = new Integer[tamanho];
    }
    
    public MinhaFila() {
        this.tamanho = 10;  // Tamanho padrão
        meusDados = new Integer[tamanho];
    }
    
    @Override
    public void enfileirar(Integer item) throws FilaCheiaException{
        if (isFull()) {
            throw new FilaCheiaException();
        }
        meusDados[cauda] = item;
        cauda = (cauda + 1) % tamanho;
        elementos++;
    }
    
    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException();
        }
        Integer itemRemovido = meusDados[cabeca];
        meusDados[cabeca] = null;
        cabeca = (cabeca + 1) % tamanho;
        elementos--;
        return itemRemovido;
    }
    
    @Override
    public Integer verificarCauda() {
        if (isEmpty()) {
            return null;
        }
        return cauda == 0 ? meusDados[tamanho - 1] : meusDados[cauda - 1];
    }
    
    @Override
    public Integer verificarCabeca() {
        if (isEmpty()) {
            return null;
        }
        return meusDados[cabeca];
    }
    
    @Override
    public boolean isEmpty() {
        return elementos == 0;
    }
    
    @Override
    public boolean isFull() {
        return elementos == tamanho;
    }
}
