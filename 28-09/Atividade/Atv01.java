package Atividade;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Atv01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numero = 0;
        boolean deuCerto = false;

        // O carrossel: repete ENQUANTO NÃO der certo
        while (!deuCerto) {
            try {
                System.out.print("Informe um número inteiro: ");
                numero = sc.nextInt();
                
                // Se o programa chegou nesta linha, significa que NÃO deu erro!
                deuCerto = true; // Avisa o carrossel para parar
                
            } catch (InputMismatchException e) {
                System.out.println("Erro: Você deve digitar um número inteiro.\n");
                
                // Tira o texto errado da mão do robô para não travar o programa
                sc.nextLine();
            }
        }

        System.out.println("Parabéns! Você digitou o número: " + numero);

        sc.close();
    }
}