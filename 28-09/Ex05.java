

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex05 {
    public static void main(String[] args) {
        ArrayList<String> lista = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            try {
                System.out.println("\n====MENU====");
                System.out.println("1-Adicionar");
                System.out.println("2-Listar");
                System.out.println("3-Remover");
                System.out.println("0-Sair");
                System.out.print("Informe a opção: ");
                
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        String nome = scanner.nextLine();
                        lista.add(nome);
                        System.out.println("Adicionado com sucesso!");
                        break;

                    case 2:
                        if (lista.isEmpty()) {
                            System.out.println("Lista vazia.");
                        } else {
                            System.out.println("Lista: " + lista);
                        }
                        break;

                    case 3:
                        if (lista.isEmpty()) {
                            System.out.println("Lista vazia, nada para remover.");
                            break;
                        }

                        System.out.print("Informe o índice para remover (0 a " + (lista.size() - 1) + "): ");
                        int indice = scanner.nextInt();
                        scanner.nextLine();

                        if (indice >= 0 && indice < lista.size()) {
                            lista.remove(indice);
                            System.out.println("Removido com sucesso!");
                        } else {
                            System.out.println("Índice inválido!");
                        }
                        break;

                    case 0:
                        System.out.println("Partiu!");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite apenas números inteiros.");
                scanner.nextLine(); 
            }catch(IndexOutOfBoundsException e){
                System.out.println("Erro: indice invalido!");
            }catch(Exception e){
                System.out.println("Erro inesperado: " +e.getMessage());
            }
        }

        scanner.close();
    }
}