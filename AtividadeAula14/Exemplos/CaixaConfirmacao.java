package AtividadeAula14.Exemplos;


import javax.swing.JOptionPane;

public class CaixaConfirmacao {
    public static void main(String[] args) {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja continuar?",
            "Confirmação",JOptionPane.YES_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Voce escolheu sim",
                "Resultado",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showConfirmDialog(null, "Voce escolheu não!", 
                "Resultado", JOptionPane.WARNING_MESSAGE);
            }
    }
}