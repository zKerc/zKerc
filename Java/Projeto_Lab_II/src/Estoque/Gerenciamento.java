package Estoque;

// Gerenciamento.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gerenciamento {
    private List<Produto> estoque;
    private List<Vendas> vendas;

    public Gerenciamento() {
        estoque = new ArrayList<>();
        vendas = new ArrayList<>();
    } // Fim do método Gerenciamento

    public void adicionarProduto(Produto produto) {
        estoque.add(produto);
    } // Fim do método void adicionarProduto

    public void removerProduto(Produto produto) {
        estoque.remove(produto);
    } // Fim do método void removerProduto

    public void registrarVenda(Produto produto, int quantidadeVendida, String cliente, LocalDate data) {
        if (!estoque.contains(produto)) {
            throw new IllegalArgumentException("O produto não está no estoque.");
        } // Fim da Exceção

        if (quantidadeVendida <= 0) {
            throw new IllegalArgumentException("A quantidade vendida deve ser maior que zero.");
        } // Fim da Exceção

        if (quantidadeVendida > produto.getQuantidade()) {
            throw new IllegalArgumentException("A quantidade vendida é maior do que a quantidade em estoque.");
        } // Fim da Exceção

        produto.atualizarQuantidadeProd(quantidadeVendida);

        Vendas venda = new Vendas(vendas.size() + 1, quantidadeVendida, cliente, produto, data);
        vendas.add(venda);
    } // Fim do método void registrarVenda

    public void exibirEstoque() {
        System.out.println("Estoque:");
        for (Produto produto : estoque) {
            System.out.println(produto);
            System.out.println("--------------------");
        } // Fim do for
    } // Fim do método void exibirEstoque

    public void exibirVendas() {
        System.out.println("Vendas:");
        for (Vendas venda : vendas) {
            System.out.println(venda);
            System.out.println("--------------------");
        } // Fim do for
    } // Fim do método void exibirVendas
} // Fim da classe
