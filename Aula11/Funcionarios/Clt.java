package Funcionarios;

public class Clt extends Funcionario implements Pagamento {

    public Clt(String nome, String cpf, double salario) {
        super(nome, cpf, salario);
    }

    @Override
    public String getTipo() {
        return "Clt";
    }

    @Override
    public double calcularPagamento() {
        return getSalario();
    }


    @Override
    public double calcularPagamento(double bonus) {
        return getSalario() + bonus;
    }
}
