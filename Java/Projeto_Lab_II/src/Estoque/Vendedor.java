package Estoque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    private List<Vendas> vendas;

    public Vendedor() {
        vendas = new ArrayList<>();
    } // Fim do método vendedor

    public void registrarVenda(Produto produto, int quantidadeVendida, String cliente, LocalDate data) {
        if (quantidadeVendida <= 0) {
            throw new IllegalArgumentException("A quantidade vendida deve ser maior que zero.");
        } // Fim da exceção

        if (quantidadeVendida > produto.getQuantidade()) {
            throw new IllegalArgumentException("A quantidade vendida é maior do que a quantidade em estoque.");
        } // Fim da exceção

        produto.atualizarQuantidadeProd(quantidadeVendida);

        Vendas venda = new Vendas(vendas.size() + 1, quantidadeVendida, cliente, produto, data);
        vendas.add(venda);
    } // Fim do método void registrarVenda

    public void exibirVendas() {
        System.out.println("Vendas:");
        for (Vendas venda : vendas) {
            System.out.println(venda);
            System.out.println("--------------------");
        }
    } // Fim do método void exibirVendas
} // Fim da classe
