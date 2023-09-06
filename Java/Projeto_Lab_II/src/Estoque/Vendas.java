package Estoque;

import java.time.LocalDate;

public class Vendas {

    // Definindo os parâmetros
    private int id;
    private int vendidos;
    private String cliente;
    private Produto produto;
    private LocalDate data;

    public Vendas(int id, int vendidos, String cliente, Produto produto, LocalDate data) {
        this.id = id;
        this.vendidos = vendidos;
        this.cliente = cliente;
        this.produto = produto;
        this.data = data;
    } // Fim do método vendas

    public int getId() {
        return id;
    } // Fim do getId

    public void setId(int id) {
        this.id = id;
    } // Fim do setId

    public int getVendidos() {
        return vendidos;
    } // Fim do getVendidos

    public void setVendidos(int quantVendida) {
        if (quantVendida < 0) {
            throw new IllegalArgumentException("A quantidade vendida não pode ser menor que zero.");
        }
        this.vendidos = quantVendida;
    } // Fim do setVendidos

    public String getCliente() {
        return cliente;
    } // Fim do getCliente

    public void setCliente(String cliente) {
        this.cliente = cliente;
    } // Fim do setCliente

    public Produto getProduto() {
        return produto;
    } // Fim do getProduto

    public void setProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        this.produto = produto;
    } // Fim do setProduto

    public LocalDate getData() {
        return data;
    } // Fim do getData

    public void setData(LocalDate data) {
        this.data = data;
    } // Fim do setData

    public double calcularValorTotal() {
        return vendidos * produto.getPreco();
    } // Fim do método calcularValorTotal

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Cliente: " + cliente + "\n" +
                "Produto: " + produto.getNomeProduto() + "\n" +
                "Quantidade vendida: " + vendidos + "\n" +
                "Data: " + data + "\n" +
                "Valor total: " + calcularValorTotal();
    } // Fim do toString
}
