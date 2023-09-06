import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class Banco {
	private static String jdbc = "jdbc:sqlite:./src/bancoDeDados/bancoEstoque.db";
	private static Connection connection = null;
	
	public static Statement abrirBanco() throws SQLException {
		connection = DriverManager.getConnection(jdbc);
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30);
		return statement;
	}

	public static void criarBanco() throws SQLException {
		Statement statement = abrirBanco();
		statement.executeUpdate("DROP TABLE IF EXISTS produtos");
		statement.executeUpdate("DROP TABLE IF EXISTS vendas");
		statement.executeUpdate("DROP TABLE IF EXISTS cliente");

		statement.executeUpdate(
				"CREATE TABLE produtos"
						+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "nome STRING NOT NULL,"
						+ "descricao VARCHAR(400) NOT NULL,"
						+ "estoque INTEGER NOT NULL,"
						+ "preco DOUBLE NOT NULL)");

		statement.executeUpdate(
				"CREATE TABLE vendas"
				+ "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "produto STRING NOT NULL,"
				+ "autor VARCHAR(15) NOT NULL,"
				+ "CPF VARCHAR(11) NOT NULL,"
				+ "data DATE NOT NULL)");

		statement.executeUpdate(
				"CREATE TABLE cliente"
				+ "(nome STRING NOT NULL,"
				+ "CPF VARCHAR(11) NOT NULL PRIMARY KEY,"
				+ "nascimento DATE NOT NULL)");
		connection.close();
	}

	public void popularBanco() throws SQLException {
		Statement statement = abrirBanco();
		statement.executeUpdate("INSERT INTO produtos (nome, descricao, estoque, preco) VALUES ('Acer Nitro 5', 'Notebook poderoso', 10, 2500) ");
		statement.executeUpdate("INSERT INTO produtos (nome, descricao, estoque, preco) VALUES ('Kindle Oasis', 'Leitor de livros digital', 10, 1300) ");
		statement.executeUpdate("INSERT INTO produtos (nome, descricao, estoque, preco) VALUES ('Acer Nitro 5', 'Motorola Edge 30', 10, 1800) ");
		statement.executeUpdate("INSERT INTO produtos (nome, descricao, estoque, preco) VALUES ('Nitendo Switch', 'Console', 10, 2199) ");
		statement.executeUpdate("INSERT INTO produtos (nome, descricao, estoque, preco) VALUES ('Playstation 5', 'Console Ultima geração', 10, 4000) ");
		statement.executeUpdate("INSERT INTO produtos (nome, descricao, estoque, preco) VALUES ('Redmi Note 11', 'Celular > Apple IPHONE', 10, 3000) ");
		statement.executeUpdate("INSERT INTO produtos (nome, descricao, estoque, preco) VALUES ('Steam Deck', 'Console Mobile da Steam', 10, 3000) ");
		connection.close();
		
	}
	public void listarProdutos() throws SQLException {
		Statement statement = abrirBanco();
		ResultSet rs = statement.executeQuery("SELECT * FROM produtos");
        while (rs.next()) {
            // Ler os dados inseridos
        	System.out.println("=================================");
        	System.out.println("ID: " + rs.getInt("id"));
        	System.out.println("Produto: " + rs.getString("nome"));
        	System.out.printf("Preço: R$ %.2f%n", rs.getDouble("preco"));
        	System.out.printf("Estoque: %d\n", rs.getInt("estoque"));
        	System.out.println("=================================");
        }
        connection.close();
	}
	public void comprarProduto(int id) throws SQLException, IOException {
		Scanner scanner = new Scanner(System.in);
		Statement statement = abrirBanco();
		ResultSet rs = statement.executeQuery("SELECT * FROM produtos WHERE id = " + id);
		String nome = rs.getString("nome");
		double preco = rs.getDouble("preco");
		int quantidade = rs.getInt("estoque");
		
		String autor = "Ian Diniz";
		String CPF = "123";

		LocalDate dateNow = LocalDate.now();
		System.out.println("Olá! Realmente deseja comprar: " + nome + ", por: R$" + preco + "?");
		System.out.print("Digite Y ou N: ");
		char input = (char) System.in.read();
		if (input == 'Y' || input == 'y') {
            System.out.println("Opção Y selecionada.");
            statement.executeUpdate("UPDATE produtos SET estoque = " + (quantidade - 1) + " WHERE id = " + id);
            String sql = "INSERT INTO vendas (produto, autor, CPF, data) VALUES ('" + nome + "', '" + autor + "', '" + CPF + "', '" + dateNow + "')";
            statement.executeUpdate(sql);
        } else if (input == 'N' || input == 'n') {
            System.out.println("Opção N selecionada.");
        } else {
            System.out.println("Opção inválida.");
            // Lida com a opção inválida
        }
		
		connection.close();
	}
	public static void apagarBanco() {

	}

}
