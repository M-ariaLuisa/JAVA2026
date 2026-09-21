package Funcionarios;

public class FuncionarioFreelancer extends Funcionario implements Pagamento {

    private double horasTrabalhadas;
    private double valorHora;

    public FuncionarioFreelancer(String nome, String cpf, double horasTrabalhadas, double valorHora) {

        super(nome, cpf, horasTrabalhadas * valorHora);
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = valorHora;
    }

    public double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
        atualizarSalario();
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
        atualizarSalario();
    }

    private void atualizarSalario() {
        setSalario(horasTrabalhadas * valorHora);
    }

    @Override
    public String getTipo() {
        return "Freelancer";
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Horas trabalhadas: " + horasTrabalhadas);
        System.out.printf ("Valor por hora....: R$ %.2f%n", valorHora);
    }

    @Override
    public double calcularPagamento() {
        return horasTrabalhadas * valorHora;
    }

    @Override
    public double calcularPagamento(double bonus) {
        return (horasTrabalhadas * valorHora) + bonus;
    }
}

    

    

