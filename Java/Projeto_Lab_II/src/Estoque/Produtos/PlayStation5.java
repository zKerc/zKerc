package Estoque.Produtos;

import Estoque.Produto;

public class PlayStation5 extends Produto {

    public PlayStation5(int id, int quantidade, String nomeProduto, String descricao, double preco) {
        super(id, quantidade, nomeProduto, descricao, preco);
    }

    public void exibirInformacoes() {
        System.out.println("ID: " + getId());
        System.out.println("Quantidade: " + getQuantidade());
        System.out.println("Nome do Produto: " + getNomeProduto());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Preço: " + getPreco());
    }

}
