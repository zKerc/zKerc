import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjetoMain {
    public static void main(String[] args) throws SQLException, IOException {
    	Banco alterarBanco = new Banco();
    	//alterarBanco.criarBanco();
    	//alterarBanco.popularBanco();
    	alterarBanco.listarProdutos();
    	//alterarBanco.comprarProduto(6);
    }
}