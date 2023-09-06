import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import Estoque.Produto;
import Estoque.Vendedor;

public class InterfaceUsuario {
	public static void tela() {
		switch (opcao) {
        case 1:
            System.out.println("Digite a senha:");
            String senhaEntrada = scanner.nextLine();

            if (senhaEntrada.equals(senhaCadastrada)) {
                System.out.println("Digite o número correspondente à ação que será tomada:");
                System.out.println("1. Incrementar nova quantidade de um produto");
                System.out.println("2. Remover um produto do estoque");
                System.out.println("3. Registrar novas vendas");
                System.out.println("4. Exibir produtos em estoque");
                System.out.println("5. Exibir vendas realizadas");

                int opcao2 = scanner.nextInt();
                scanner.nextLine();

                Vendedor vendedor = new Vendedor(); // Criação do objeto Vendedor

                switch (opcao2) {
                    case 1:
                        System.out.println("Selecione o produto que terá o aumento no estoque:");
                        System.out.println("1. Acer Nitro 5");
                        System.out.println("2. Kindle Oasis");
                        System.out.println("3. Motorola Edge 30");
                        System.out.println("4. Nintendo Switch");
                        System.out.println("5. PlayStation 5");
                        System.out.println("6. Redmi Note 11");
                        System.out.println("7. Steam Deck");

                        int produtoOpcao = scanner.nextInt();
                        scanner.nextLine();

                        // Criar um objeto Produto com base na opção selecionada
                        Produto produtoSelecionado = criarProdutoPorOpcao(produtoOpcao);

                        if (produtoSelecionado != null) {

                            // Solicitar a quantidade a ser incrementada
                            System.out.println("Digite a quantidade a ser incrementada:");
                            int quantidadeIncrementada = scanner.nextInt();
                            scanner.nextLine();
                            try {
                                FileOutputStream fileOut = new FileOutputStream("lista.ser");
                                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                                out.writeObject(produtoSelecionado);
                                out.close();
                                fileOut.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            // Atualizar a quantidade do produto
                            produtoSelecionado
                                    .setQuantidade(produtoSelecionado.getQuantidade() + quantidadeIncrementada);
                            System.out.println("Quantidade incrementada com sucesso!");
                        } else {
                            System.out.println("Opção inválida!");
                        }
                        break;

                    case 2:
                        // Representação da remoção de produto
                        break;

                    case 3:
                        System.out.println("Selecione o produto:");
                        System.out.println("1. Acer Nitro 5");
                        System.out.println("2. Kindle Oasis");
                        System.out.println("3. Motorola Edge 30");
                        System.out.println("4. Nintendo Switch");
                        System.out.println("5. PlayStation 5");
                        System.out.println("6. Redmi Note 11");
                        System.out.println("7. Steam Deck");

                        int vendaProdutoOpcao = scanner.nextInt();
                        scanner.nextLine();

                        // Criar um objeto Produto com base na opção selecionada
                        Produto vendaProdutoSelecionado = criarProdutoPorOpcao(vendaProdutoOpcao);

                        if (vendaProdutoSelecionado != null) {
                            System.out.println("Digite a quantidade vendida:");
                            int quantidadeVendida = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("Digite o nome do cliente:");
                            String cliente = scanner.nextLine();

                            LocalDate dataVenda = LocalDate.now(); // Data atual

                            // Registrar a venda
                            vendedor.registrarVenda(vendaProdutoSelecionado, quantidadeVendida, cliente, dataVenda);
                            System.out.println("Venda registrada com sucesso!");
                        } else {
                            System.out.println("Opção inválida!");
                        }
                        break;

                    case 4:
                        gerenciamento.exibirEstoque();
                        break;

                    case 5:
                        vendedor.exibirVendas();
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } else {
                System.out.println("Senha incorreta");
            }

            break;

        case 2:
            System.out.println("Digite o número correspondente à ação que será tomada:");
            System.out.println("1. Registrar nova venda");
            System.out.println("2. Exibir vendas realizadas");

            int opcaoVendedor = scanner.nextInt();
            scanner.nextLine();

            Vendedor vendedor = new Vendedor(); // Criação do objeto Vendedor

            switch (opcaoVendedor) {
                case 1:
                    System.out.println("Digite os detalhes da venda:");
                    System.out.println("Selecione o produto:");
                    System.out.println("1. Acer Nitro 5");
                    System.out.println("2. Kindle Oasis");
                    System.out.println("3. Motorola Edge 30");
                    System.out.println("4. Nintendo Switch");
                    System.out.println("5. PlayStation 5");
                    System.out.println("6. Redmi Note 11");
                    System.out.println("7. Steam Deck");

                    int vendaProdutoOpcao = scanner.nextInt();
                    scanner.nextLine();

                    // Criar um objeto Produto com base na opção selecionada
                    Produto vendaProdutoSelecionado = criarProdutoPorOpcao(vendaProdutoOpcao);

                    if (vendaProdutoSelecionado != null) {
                        System.out.println("Digite a quantidade vendida:");
                        int quantidadeVendida = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Digite o nome do cliente:");
                        String cliente = scanner.nextLine();

                        LocalDate dataVenda = LocalDate.now(); // Data atual

                        // Registrar a venda
                        vendedor.registrarVenda(vendaProdutoSelecionado, quantidadeVendida, cliente, dataVenda);
                        System.out.println("Venda registrada com sucesso!");
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 2:
                    vendedor.exibirVendas();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            break;
    }
}
}
