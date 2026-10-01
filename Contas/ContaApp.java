package Contas;

import java.util.Scanner;

public class ContaApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        cadastroConta cadastro = new cadastroConta();
        int opcao = 0;

        do {
            System.out.println("\n===== CADASTRO DE CONTAS BANCÁRIAS =====");
            System.out.println("1. Cadastrar Conta");
            System.out.println("2. Buscar Conta");
            System.out.println("3. Remover Conta");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Erro: digite um número de 1 a 4.");
                opcao = 0;
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrar(sc, cadastro);
                    break;
                case 2:
                    buscar(sc, cadastro);
                    break;
                case 3:
                    remover(sc, cadastro);
                    break;
                case 4:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha de 1 a 4.");
            }
        } while (opcao != 4);

        sc.close();
    }

    private static void cadastrar(Scanner sc, cadastroConta cadastro) {
        try {
            System.out.print("Número da conta: ");
            String numero = sc.nextLine();
            System.out.print("Nome do titular: ");
            String titular = sc.nextLine();
            System.out.print("Saldo inicial: ");
            String saldoTexto = sc.nextLine().trim().replace(',', '.');

            double saldo;
            try {
                saldo = Double.parseDouble(saldoTexto);
            } catch (NumberFormatException e) {
                throw new ExcecaoDadoInvalido("Saldo inválido: informe um valor numérico.");
            }

            Conta conta = new Conta(numero, titular, saldo);
            cadastro.inserir(conta);
            System.out.println("Conta cadastrada com sucesso!");
        } catch (ExcecaoDadoInvalido e) {
            System.out.println("Dado inválido: " + e.getMessage());
        } catch (ExcecaoElementoJaExistente e) {
            System.out.println("Conta duplicada: " + e.getMessage());
        } catch (ExcecaoRepositorio e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    private static void buscar(Scanner sc, cadastroConta cadastro) {
        try {
            System.out.print("Número da conta: ");
            Conta conta = cadastro.buscar(sc.nextLine());
            System.out.println("Titular: " + conta.getTitular());
            System.out.printf("Saldo: R$ %.2f%n", conta.getSaldo());
        } catch (ExcecaoElementoInexistente e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void remover(Scanner sc, cadastroConta cadastro) {
        try {
            System.out.print("Número da conta: ");
            cadastro.remover(sc.nextLine());
            System.out.println("Conta removida com sucesso!");
        } catch (ExcecaoElementoInexistente e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}