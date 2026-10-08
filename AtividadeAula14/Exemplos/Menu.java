package AtividadeAula14.Exemplos;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Menu {
    public static void main(String[] args) {
        ArrayList<String> produtos = new ArrayList<>();

        boolean executando =true;

        while (executando) {
            String opcao = JOptionPane.showInputDialog(
                null,
                "Escolher uma opção:\n"+
                "1-Cadastrar produtos\n"+
                "2-Listar produtos\n"+
                "3-Sair",
                "Menu Principal",
                JOptionPane.QUESTION_MESSAGE
            );
            if (opcao ==null) {
                JOptionPane.showConfirmDialog(null, "operação cancelada");
                break;
            }

            switch (opcao) {
                case "1":
                    String produto = JOptionPane.showInputDialog(null,
                        "Digite o nome do produto",
                        "Cadastro do produto",
                        JOptionPane.QUESTION_MESSAGE
                    );
                    if (produto==null || produto.trim().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "produto nao cadastrado!");

                    }else {
                        produtos.add(produto);
                        JOptionPane.showConfirmDialog(null,
                             "produto cadastrado com sucesso!");
                    }
                    break;
                    case "2":
                    if (produtos.isEmpty()) {
                        JOptionPane.showConfirmDialog(null,
                             "produto cadastrado com sucesso!");
                    }else{
                        String lista = "Produtos cadastrados\n\n";

                        for(int i=0;i<produtos.size();i++){
                            lista+=(i+1)+" - "+produtos.get(i) +
                            "\n";
                        }
                        JOptionPane.showConfirmDialog(null,
                            lista,
                            "Lista de produtoss",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                    case "3":
                        JOptionPane.showConfirmDialog(null,
                            "Saindo...");
                            executando=false;
                            break;
                    
                default:
                    JOptionPane.showMessageDialog(null,
                        "opção invalidade!");
                    break;
            }

        }
    }
}