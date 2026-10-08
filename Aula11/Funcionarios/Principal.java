package Funcionarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Funcionario> funcionarios = new ArrayList<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        int opcao;

        while (true) {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    mostrarDadosCadastrados();
                    break;
                case 3:
                    calcularPagamento(false);
                    break;
                case 4:
                    calcularPagamento(true);
                    break;
                case 5:
                    consultarFuncionario();
                    break;
                case 6:
                    System.out.println("Encerrando o programa. Até logo!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=========== SISTEMA DE FUNCIONÁRIOS ===========");
        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Mostrar dados cadastrados");
        System.out.println("3 - Calcular pagamento");
        System.out.println("4 - Calcular pagamento com bônus");
        System.out.println("5 - Consultar dados de um funcionário");
        System.out.println("6 - Encerrar programa");
        System.out.println("=================================================");
    }


    private static void cadastrarFuncionario() {
        System.out.println("\n--- Cadastro de Funcionário ---");
        System.out.println("Escolha o tipo de funcionário:");
        System.out.println("1 - CLT");
        System.out.println("2 - Freelancer");
        int tipo = lerInteiro("Tipo: ");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if (tipo == 1) {
            double salario = lerDouble("Salário mensal: ");
            funcionarios.add(new Clt(nome, cpf, salario));
            System.out.println("Funcionário CLT cadastrado com sucesso!");

        } else if (tipo == 2) {
            double horas = lerDouble("Quantidade de horas trabalhadas: ");
            double valorHora = lerDouble("Valor por hora (R$): ");
            funcionarios.add(new FuncionarioFreelancer(nome, cpf, horas, valorHora));
            System.out.println("Funcionário Freelancer cadastrado com sucesso!");

        } else {
            System.out.println("Tipo inválido! Cadastro cancelado.");
        }
    }


    private static void mostrarDadosCadastrados() {
        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n--- Funcionários Cadastrados ---");
        for (Funcionario f : funcionarios) {
            f.exibirDados();
        }
    }


    private static void calcularPagamento(boolean comBonus) {
        Funcionario f = buscarPorCpf();
        if (f == null) {
            return;
        }

        
        Pagamento p = (Pagamento) f;

        if (comBonus) {
            double bonus = lerDouble("Valor do bônus: ");
            double total = p.calcularPagamento(bonus);
            System.out.printf("Pagamento com bônus de %s: R$ %.2f%n", f.getNome(), total);
        } else {
            double total = p.calcularPagamento();
            System.out.printf("Pagamento de %s: R$ %.2f%n", f.getNome(), total);
        }
    }

    
    private static void consultarFuncionario() {
        Funcionario f = buscarPorCpf();
        if (f != null) {
            System.out.println("\n--- Dados do Funcionário ---");
            f.exibirDados();
        }
    }


    private static Funcionario buscarPorCpf() {
        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return null;
        }

        System.out.print("Digite o CPF do funcionário: ");
        String cpf = scanner.nextLine();

        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }

        System.out.println("Funcionário não encontrado.");
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