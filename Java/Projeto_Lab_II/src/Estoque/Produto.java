package Estoque;

//Classe Produto.java
public class Produto {

    // Definindo os parâmetros
    private int id;
    private int quantidade;
    private String nomeProduto;
    private String descricao;
    private double preco;

    public Produto(int id, int quantidade, String nomeProduto, String descricao, double preco) {
        this.id = id;
        this.quantidade = quantidade;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.preco = preco;
    } // Fim do método Produto

    public int getId() {
        return id;
    } // Fim do getId

    public void setId(int id) {
        this.id = id;
    } // Fim do set Id

    public int getQuantidade() {
        return quantidade;
    } // Fim do getQuantidade

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser menor que zero.");
        }
        this.quantidade = quantidade;
    } // Fim do setQuantidade

    public String getNomeProduto() {
        return nomeProduto;
    } // Fim do getNomeProduto

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    } // Fim do setNomeProduto

    public String getDescricao() {
        return descricao;
    } // Fim do getDescricao

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } // Fim do setDescricao

    public double getPreco() {
        return preco;
    } // Fim do getPreco

    public void setPreco(double preco) {
        this.preco = preco;
    } // Fim do setPreco

    public void atualizarQuantidadeProd(int quantidadeVendida) {
        if (quantidadeVendida < 0) {
            throw new IllegalArgumentException("A quantidade vendida não pode ser menor que zero.");
        }
        if (quantidadeVendida > quantidade) {
            throw new IllegalArgumentException("A quantidade vendida não pode ser maior que a quantidade em estoque.");
        }
        quantidade -= quantidadeVendida;
    } // Fim do método

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Nome: " + nomeProduto + "\n" +
                "Descrição: " + descricao + "\n" +
                "Preço: " + preco + "\n" +
                "Quantidade: " + quantidade;
    } // Fim do método toString
} // Fim da Classe