package Veiculos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Veiculo> veiculos = new ArrayList<>();

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        while (true) {

            exibirMenu();

            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    cadastrarVeiculo();
                    break;

                case 2:
                    escolherTipo();
                    break;

                case 3:
                    mostrarDados();
                    break;

                case 4:
                    informarDias();
                    break;

                case 5:
                    calcularAluguel(false);
                    break;

                case 6:
                    calcularAluguel(true);
                    break;

                case 7:
                    System.out.println("Programa encerrado. Até logo!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirMenu() {

        System.out.println("\n=========== SISTEMA DE LOCAÇÃO ===========");
        System.out.println("1 - Cadastrar veículo");
        System.out.println("2 - Escolher tipo do veículo");
        System.out.println("3 - Mostrar dados do veículo");
        System.out.println("4 - Informar quantidade de dias");
        System.out.println("5 - Calcular valor do aluguel");
        System.out.println("6 - Calcular aluguel com desconto");
        System.out.println("7 - Encerrar programa");
        System.out.println("===========================================");
    }

    private static void cadastrarVeiculo() {

        System.out.println("\n--- Cadastro de Veículo ---");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");

        int tipo = lerInteiro("Escolha o tipo: ");

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        int ano = lerInteiro("Ano: ");

        double diaria = lerDouble("Valor da diária: ");

        if (tipo == 1) {

            veiculos.add(
                new Carro(placa, modelo, ano, diaria)
            );

            System.out.println("Carro cadastrado com sucesso!");

        } else if (tipo == 2) {

            veiculos.add(
                new Moto(placa, modelo, ano, diaria)
            );

            System.out.println("Moto cadastrada com sucesso!");

        } else {

            System.out.println("Tipo inválido. Cadastro cancelado.");
        }
    }

    private static void escolherTipo() {

        System.out.println("\n--- Tipos de Veículo ---");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");

        int tipo = lerInteiro("Escolha: ");

        if (tipo == 1) {

            System.out.println("Tipo escolhido: Carro");

        } else if (tipo == 2) {

            System.out.println("Tipo escolhido: Moto");

        } else {

            System.out.println("Tipo inválido.");
        }
    }

    private static void mostrarDados() {

        if (veiculos.isEmpty()) {

            System.out.println("\nNenhum veículo cadastrado.");
            return;
        }

        System.out.println("\n--- Veículos Cadastrados ---");

        for (Veiculo v : veiculos) {
            v.exibirDados();
        }
    }

    private static int informarDias() {

        int dias = lerInteiro("Informe a quantidade de dias: ");

        if (dias <= 0) {

            System.out.println(
                "A quantidade de dias deve ser maior que zero."
            );
        }

        return dias;
    }

    private static void calcularAluguel(boolean comDesconto) {

        if (veiculos.isEmpty()) {

            System.out.println("\nNenhum veículo cadastrado.");
            return;
        }

        Veiculo veiculo = buscarPorPlaca();

        if (veiculo == null) {
            return;
        }

        int dias = informarDias();

        if (dias <= 0) {
            return;
        }

        Aluguel aluguel = (Aluguel) veiculo;

        if (comDesconto) {

            double desconto = lerDouble("Informe o desconto: ");

            double total = aluguel.calcularAluguel(
                dias,
                desconto
            );

            System.out.printf(
                "Valor final do aluguel: R$ %.2f%n",
                total
            );

        } else {

            double total = aluguel.calcularAluguel(dias);

            System.out.printf(
                "Valor do aluguel: R$ %.2f%n",
                total
            );
        }
    }

    private static Veiculo buscarPorPlaca() {

        System.out.print("Digite a placa do veículo: ");

        String placa = scanner.nextLine();

        for (Veiculo v : veiculos) {

            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }

        System.out.println("Veículo não encontrado.");

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

            System.out.print("Digite um valor válido: ");
            scanner.next();
        }

        double valor = scanner.nextDouble();

        scanner.nextLine();

        return valor;
    }
}
