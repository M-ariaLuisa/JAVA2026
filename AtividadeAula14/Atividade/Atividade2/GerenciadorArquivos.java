package AtividadeAula14.Atividade.Atividade2;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class GerenciadorArquivos {

    private static final String PASTA_LABORATORIO = "LABORATORIO";

    public static void main(String[] args) {
        GarantirPastaLaboratorio();

        boolean executando = true;

        while (executando) {
            String opcaoStr = JOptionPane.showInputDialog(
                null,
                "=== GERENCIADOR DE ARQUIVOS ===\n" +
                "1 - Criar arquivo\n" +
                "2 - Escrever no arquivo\n" +
                "3 - Ler arquivo\n" +
                "4 - Renomear arquivo\n" +
                "5 - Excluir arquivo\n" +
                "6 - Mostrar informações do arquivo\n" +
                "7 - Tornar arquivo Somente Leitura (Desafio)\n" +
                "8 - Sair\n\n" +
                "Escolha uma opção:",
                "Gerenciador de Arquivos",
                JOptionPane.QUESTION_MESSAGE
            );

            if (opcaoStr == null || opcaoStr.equals("8")) {
                executando = false;
                JOptionPane.showMessageDialog(null, "Sistema encerrado.", "Encerramento", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            switch (opcaoStr) {
                case "1":
                    criarArquivo();
                    break;
                case "2":
                    escreverNoArquivo();
                    break;
                case "3":
                    lerArquivo();
                    break;
                case "4":
                    renomearArquivo();
                    break;
                case "5":
                    excluirArquivo();
                    break;
                case "6":
                    mostrarInformacoes();
                    break;
                case "7":
                    protegerSomenteLeitura();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void GarantirPastaLaboratorio() {
        File pasta = new File(PASTA_LABORATORIO);
        if (!pasta.exists()) {
            pasta.mkdir();
        }
    }

    private static void criarArquivo() {
        String nomeArquivo = pedirNomeArquivo("Digite o nome do arquivo com extensão (ex: atividade.txt):");
        if (nomeArquivo == null) return;

        File arquivo = new File(PASTA_LABORATORIO, nomeArquivo);

        try {
            if (arquivo.exists()) {
                JOptionPane.showMessageDialog(null, "O arquivo já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                if (arquivo.createNewFile()) {
                    JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso no diretório LABORATORIO!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível criar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro de I/O ao criar o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void escreverNoArquivo() {
        String nomeArquivo = pedirNomeArquivo("Digite o nome do arquivo para escrever:");
        if (nomeArquivo == null) return;

        File arquivo = new File(PASTA_LABORATORIO, nomeArquivo);

        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado no diretório LABORATORIO!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!arquivo.canWrite()) {
            JOptionPane.showMessageDialog(null, "O arquivo é somente leitura e não pode ser alterado!", "Erro de Permissão", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String conteudo = JOptionPane.showInputDialog(null, "Digite o texto a ser gravado no arquivo:", "Escrever no Arquivo", JOptionPane.QUESTION_MESSAGE);
        if (conteudo == null) return;

        try (FileWriter fw = new FileWriter(arquivo, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(conteudo);
            JOptionPane.showMessageDialog(null, "Conteúdo gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void lerArquivo() {
        String nomeArquivo = pedirNomeArquivo("Digite o nome do arquivo para leitura:");
        if (nomeArquivo == null) return;

        File arquivo = new File(PASTA_LABORATORIO, nomeArquivo);

        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado no diretório LABORATORIO!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (FileReader fr = new FileReader(arquivo);
             BufferedReader br = new BufferedReader(fr)) {

            StringBuilder conteudo = new StringBuilder();
            String linha;

            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }

            if (conteudo.length() == 0) {
                JOptionPane.showMessageDialog(null, "O arquivo está vazio.", "Conteúdo do Arquivo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, conteudo.toString(), "Conteúdo de " + nomeArquivo, JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void renomearArquivo() {
        String nomeAtual = pedirNomeArquivo("Digite o nome atual do arquivo:");
        if (nomeAtual == null) return;

        File arquivoAtual = new File(PASTA_LABORATORIO, nomeAtual);

        if (!arquivoAtual.exists()) {
            JOptionPane.showMessageDialog(null, "O arquivo especificado não existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String novoNome = pedirNomeArquivo("Digite o novo nome do arquivo com extensão:");
        if (novoNome == null) return;

        File novoArquivo = new File(PASTA_LABORATORIO, novoNome);

        if (novoArquivo.exists()) {
            JOptionPane.showMessageDialog(null, "Já existe um arquivo com esse novo nome!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (arquivoAtual.renameTo(novoArquivo)) {
            JOptionPane.showMessageDialog(null, "Arquivo renomeado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao renomear o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void excluirArquivo() {
        String nomeArquivo = pedirNomeArquivo("Digite o nome do arquivo a ser excluído:");
        if (nomeArquivo == null) return;

        File arquivo = new File(PASTA_LABORATORIO, nomeArquivo);

        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado no diretório LABORATORIO!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
            null,
            "Tem certeza que deseja excluir o arquivo '" + nomeArquivo + "'?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (arquivo.delete()) {
                JOptionPane.showMessageDialog(null, "Arquivo excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao excluir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação de exclusão cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void mostrarInformacoes() {
        String nomeArquivo = pedirNomeArquivo("Digite o nome do arquivo para ver as informações:");
        if (nomeArquivo == null) return;

        File arquivo = new File(PASTA_LABORATORIO, nomeArquivo);

        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado no diretório LABORATORIO!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String extensao = "";
        int i = nomeArquivo.lastIndexOf('.');
        if (i > 0) {
            extensao = nomeArquivo.substring(i);
        } else {
            extensao = "Sem extensão";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataModificacao = sdf.format(arquivo.lastModified());

        String info = "=== INFORMAÇÕES DO ARQUIVO ===\n\n" +
                "Nome: " + arquivo.getName() + "\n" +
                "Extensão: " + extensao + "\n" +
                "Tamanho: " + arquivo.length() + " bytes\n" +
                "Caminho Completo: " + arquivo.getAbsolutePath() + "\n" +
                "Permissão de Leitura: " + (arquivo.canRead() ? "Sim" : "Não") + "\n" +
                "Permissão de Gravação: " + (arquivo.canWrite() ? "Sim" : "Não") + "\n" +
                "Permissão de Execução: " + (arquivo.canExecute() ? "Sim" : "Não") + "\n" +
                "Última Modificação: " + dataModificacao;

        JOptionPane.showMessageDialog(null, info, "Informações do Arquivo", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void protegerSomenteLeitura() {
        String nomeArquivo = pedirNomeArquivo("Digite o nome do arquivo para tornar Somente Leitura:");
        if (nomeArquivo == null) return;

        File arquivo = new File(PASTA_LABORATORIO, nomeArquivo);

        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado no diretório LABORATORIO!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (arquivo.setReadOnly()) {
            JOptionPane.showMessageDialog(null, "Arquivo '" + nomeArquivo + "' alterado para Somente Leitura com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível alterar a permissão do arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String pedirNomeArquivo(String mensagem) {
        String nome = JOptionPane.showInputDialog(null, mensagem, "Nome do Arquivo", JOptionPane.QUESTION_MESSAGE);

        if (nome == null) {
            return null;
        }

        if (nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome do arquivo não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return nome.trim();
    }
}