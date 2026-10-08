package Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Produto> produtos = new ArrayList<>();

    private static Produto produtoSelecionado = null;
    private static int quantidade = 0;
    private static double valorFinal = 0;

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        while (true) {

            exibirMenu();

            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    cadastrarProduto();
                    break;

                case 2:
                    escolherProduto();
                    break;

                case 3:
                    mostrarDadosProduto();
                    break;

                case 4:
                    informarQuantidade();
                    break;

                case 5:
                    realizarVenda();
                    break;

                case 6:
                    realizarVendaComDesconto();
                    break;

                case 7:
                    mostrarValorFinal();
                    break;

                case 8:
                    System.out.println("Programa encerrado. Até logo!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirMenu() {

        System.out.println("\n=========== SISTEMA DE PRODUTOS ===========");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Escolher produto físico ou digital");
        System.out.println("3 - Mostrar dados do produto");
        System.out.println("4 - Informar quantidade");
        System.out.println("5 - Realizar venda");
        System.out.println("6 - Realizar venda com desconto");
        System.out.println("7 - Mostrar valor final");
        System.out.println("8 - Encerrar o programa");
        System.out.println("============================================");
    }

    private static void cadastrarProduto() {

        System.out.println("\n--- Cadastro de Produto ---");

        System.out.println("1 - Produto Físico");
        System.out.println("2 - Produto Digital");

        int tipo = lerInteiro("Escolha o tipo: ");

        int codigo = lerInteiro("Código: ");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        double preco = lerDouble("Preço: ");

        if (tipo == 1) {

            double frete = lerDouble("Valor do frete: ");

            produtos.add(
                new ProdutoFisico(codigo, nome, preco, frete)
            );

            System.out.println("Produto físico cadastrado com sucesso!");

        } else if (tipo == 2) {

            produtos.add(
                new ProdutoDigital(codigo, nome, preco)
            );

            System.out.println("Produto digital cadastrado com sucesso!");

        } else {

            System.out.println("Tipo inválido. Cadastro cancelado.");
        }
    }

    private static void escolherProduto() {

        if (produtos.isEmpty()) {

            System.out.println("\nNenhum produto cadastrado.");
            return;
        }

        int codigo = lerInteiro("Digite o código do produto: ");

        produtoSelecionado = buscarPorCodigo(codigo);

        if (produtoSelecionado != null) {

            System.out.println(
                "Produto selecionado: " + produtoSelecionado.getNome()
            );

        } else {

            System.out.println("Produto não encontrado.");
        }
    }

    private static void mostrarDadosProduto() {

        if (produtoSelecionado == null) {

            System.out.println("\nNenhum produto foi selecionado.");
            return;
        }

        System.out.println("\n--- Dados do Produto ---");
        produtoSelecionado.exibirDados();
    }

    private static void informarQuantidade() {

        if (produtoSelecionado == null) {

            System.out.println("\nPrimeiro selecione um produto.");
            return;
        }

        quantidade = lerInteiro("Informe a quantidade: ");

        if (quantidade <= 0) {

            System.out.println("A quantidade deve ser maior que zero.");
            quantidade = 0;

        } else {

            System.out.println(
                "Quantidade informada: " + quantidade
            );
        }
    }

    private static void realizarVenda() {

        if (produtoSelecionado == null) {

            System.out.println("\nPrimeiro selecione um produto.");
            return;
        }

        if (quantidade <= 0) {

            System.out.println("\nPrimeiro informe uma quantidade válida.");
            return;
        }

        Venda venda = (Venda) produtoSelecionado;

        valorFinal = venda.realizarVenda(quantidade);

        System.out.printf(
            "Valor da venda: R$ %.2f%n",
            valorFinal
        );
    }

    private static void realizarVendaComDesconto() {

        if (produtoSelecionado == null) {

            System.out.println("\nPrimeiro selecione um produto.");
            return;
        }

        if (quantidade <= 0) {

            System.out.println("\nPrimeiro informe uma quantidade válida.");
            return;
        }

        double desconto = lerDouble("Informe o desconto (%): ");

        if (desconto < 0 || desconto > 100) {

            System.out.println(
                "O desconto deve estar entre 0% e 100%."
            );

            return;
        }

        Venda venda = (Venda) produtoSelecionado;

        valorFinal = venda.realizarVenda(
            quantidade,
            desconto
        );

        System.out.printf(
            "Valor final com %.2f%% de desconto: R$ %.2f%n",
            desconto,
            valorFinal
        );
    }

    private static void mostrarValorFinal() {

        if (valorFinal <= 0) {

            System.out.println(
                "\nNenhuma venda foi realizada ainda."
            );

            return;
        }

        System.out.printf(
            "\nValor final da compra: R$ %.2f%n",
            valorFinal
        );
    }

    private static Produto buscarPorCodigo(int codigo) {

        for (Produto p : produtos) {

            if (p.getCodigo() == codigo) {
                return p;
            }
        }

        return null;
    }

    private static int lerInteiro(String mensagem) {

        System.out.print(mensagem);

        while (!scanner.hasNextInt()) {

            System.out.print("Digite um número válido: ");
            scanner.next();
        }

        int valor = scanner.nextInt();
        scanner.nextLine();

        return valor;
    }

    private static double lerDouble(String mensagem) {

        System.out.print(mensagem);

        while (!scanner.hasNextDouble()) {

            System.out.print("Digite um valor numérico válido: ");
            scanner.next();
        }

        double valor = scanner.nextDouble();
        scanner.nextLine();

        return valor;
    }
}