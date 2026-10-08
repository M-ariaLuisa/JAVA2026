package AtividadeAula14.Exemplos;

import javax.swing.JOptionPane;

public class CaixaEntrada {
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite seu nome: ");
        JOptionPane.showMessageDialog(null, "Ola,"+nome+ "!",
        "Saudacao", JOptionPane.PLAIN_MESSAGE);
    
    }
}