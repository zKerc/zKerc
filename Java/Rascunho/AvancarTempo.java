package Rascunho;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AvancarTempo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate data = null;
        boolean dataValida = false;
        while (!dataValida) {
            System.out.println("Digite uma data no formato dd-MM-yyyy:");
            String dataString = scanner.nextLine();
            try {
                data = LocalDate.parse(dataString, formatter);
                dataValida = true;
            } catch (Exception e) {
                System.out.println("Data inválida. Por favor, insira uma data no formato correto.");
            }
        }

        boolean continuar = true;
        while (continuar) {
            int escolha = 0;
            boolean escolhaValida = false;
            while (!escolhaValida) {
                System.out.println("Quanto tempo deseja avançar?");
                System.out.println("1. Dias");
                System.out.println("2. Meses");
                System.out.println("3. Anos");
                System.out.println("4. Sair");

                try {
                    escolha = scanner.nextInt();
                    escolhaValida = true;
                } catch (InputMismatchException e) {
                    System.out.println("Escolha inválida. Por favor, insira um número válido.");
                    scanner.next(); // Limpar o buffer do scanner
                }

                int quantidade = 0;
                boolean quantidadeValida = false;

                switch (escolha) {
                    case 1:
                        while (!quantidadeValida) {
                            System.out.println("Quantos dias deseja avançar?");
                            try {
                                quantidade = scanner.nextInt();
                                quantidadeValida = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Valor inválido. Por favor, insira um número válido.");
                                scanner.next(); // Limpar o buffer do scanner
                            }
                        }
                        data = data.plusDays(quantidade);
                        break;
                    case 2:
                        while (!quantidadeValida) {
                            System.out.println("Quantos meses deseja avançar?");
                            try {
                                quantidade = scanner.nextInt();
                                quantidadeValida = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Valor inválido. Por favor, insira um número válido.");
                                scanner.next(); // Limpar o buffer do scanner
                            }
                        }
                        data = data.plusMonths(quantidade);
                        break;
                    case 3:
                        while (!quantidadeValida) {
                            System.out.println("Quantos anos deseja avançar?");
                            try {
                                quantidade = scanner.nextInt();
                                quantidadeValida = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Valor inválido. Por favor, insira um número válido.");
                                scanner.next(); // Limpar o buffer do scanner
                            }
                        }
                        data = data.plusYears(quantidade);
                        break;
                    case 4:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Escolha inválida. Tente novamente.");
                }

                System.out.println("Nova data: " + data.format(formatter));
            }
        }

        scanner.close();
    }
}
