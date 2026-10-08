package AtividadeAula14.Atividade;

import javax.swing.JOptionPane;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Carro> carros = new ArrayList<>();

    public static void main(String[] args) {
        int opcao = 0;

        do {
            String menu = "===== CADASTRO DE CARROS =====\n\n"
                    + "1 - Cadastrar Carro\n"
                    + "2 - Listar Carros\n"
                    + "3 - Detalhar Carro\n"
                    + "4 - Alterar Carro\n"
                    + "5 - Remover Carro\n"
                    + "6 - Gravar Informações em Arquivo\n"
                    + "7 - Sair\n\n"
                    + "Escolha uma opção:";

            String entrada = JOptionPane.showInputDialog(null, menu, "Menu Principal",
                    JOptionPane.QUESTION_MESSAGE);

            if (entrada == null) {
                opcao = 7;
            } else {
                try {
                    opcao = Integer.parseInt(entrada.trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um número válido!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
            }

            switch (opcao) {
                case 1:
                    cadastrarCarro();
                    break;
                case 2:
                    listarCarros();
                    break;
                case 3:
                    detalharCarro();
                    break;
                case 4:
                    alterarCarro();
                    break;
                case 5:
                    removerCarro();
                    break;
                case 6:
                    gravarArquivo();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Sistema encerrado. Até logo!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } while (opcao != 7);
    }

    private static void cadastrarCarro() {
        String marca = lerTexto("Informe a marca:");
        if (marca == null) return;

        String modelo = lerTexto("Informe o modelo:");
        if (modelo == null) return;

        Integer ano = lerAno("Informe o ano:");
        if (ano == null) return;

        carros.add(new Carro(marca, modelo, ano));
        JOptionPane.showMessageDialog(null, "Carro cadastrado com sucesso!");
    }

    private static void listarCarros() {
        if (carros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum carro cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder("===== CARROS CADASTRADOS =====\n\n");
        for (int i = 0; i < carros.size(); i++) {
            Carro c = carros.get(i);
            sb.append(i + 1).append(" - ")
              .append(c.getMarca()).append(" ")
              .append(c.getModelo()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Carros",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void detalharCarro() {
        int indice = escolherCarro("Informe o número do carro para detalhar:");
        if (indice == -1) return;

        JOptionPane.showMessageDialog(null, carros.get(indice).exibirDetalhes(),
                "Detalhes", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void alterarCarro() {
        int indice = escolherCarro("Informe o número do carro a alterar:");
        if (indice == -1) return;

        String marca = lerTexto("Nova marca:");
        if (marca == null) return;

        String modelo = lerTexto("Novo modelo:");
        if (modelo == null) return;

        Integer ano = lerAno("Novo ano:");
        if (ano == null) return;

        carros.set(indice, new Carro(marca, modelo, ano));
        JOptionPane.showMessageDialog(null, "Carro alterado com sucesso!");
    }

    private static void removerCarro() {
        int indice = escolherCarro("Informe o número do carro a remover:");
        if (indice == -1) return;

        int confirma = JOptionPane.showConfirmDialog(null,
                "Deseja realmente remover este carro?\n\n" + carros.get(indice).exibirDetalhes(),
                "Confirmar remoção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            carros.remove(indice);
            JOptionPane.showMessageDialog(null, "Carro removido com sucesso!");
        }
    }

    private static void gravarArquivo() {
        if (carros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há carros para gravar.");
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("carros.txt"))) {
            for (Carro c : carros) {
                pw.println("Marca: " + c.getMarca());
                pw.println("Modelo: " + c.getModelo());
                pw.println("Ano: " + c.getAno());
                pw.println("-----------------------");
            }
            JOptionPane.showMessageDialog(null, "Dados gravados em carros.txt com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String lerTexto(String mensagem) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensagem);
            if (texto == null) return null;
            texto = texto.trim();
            if (!texto.isEmpty()) return texto;
            JOptionPane.showMessageDialog(null, "O campo não pode ficar vazio!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Integer lerAno(String mensagem) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensagem);
            if (texto == null) return null;
            try {
                int ano = Integer.parseInt(texto.trim());
                if (ano >= 1886 && ano <= 2100) return ano;
                JOptionPane.showMessageDialog(null, "Informe um ano entre 1886 e 2100.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um número válido para o ano!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static int escolherCarro(String mensagem) {
        if (carros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum carro cadastrado.");
            return -1;
        }

        StringBuilder sb = new StringBuilder(mensagem + "\n\n");
        for (int i = 0; i < carros.size(); i++) {
            sb.append(i + 1).append(" - ")
              .append(carros.get(i).getMarca()).append(" ")
              .append(carros.get(i).getModelo()).append("\n");
        }

        String entrada = JOptionPane.showInputDialog(null, sb.toString());
        if (entrada == null) return -1;

        try {
            int numero = Integer.parseInt(entrada.trim());
            if (numero >= 1 && numero <= carros.size()) {
                return numero - 1;
            }
            JOptionPane.showMessageDialog(null, "Número fora da lista!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um número válido!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
}