package Veiculos;

public class Carro extends Veiculo implements Aluguel {

    public Carro(String placa, String modelo, int ano, double valor_diaria) {
        super(placa, modelo, ano, valor_diaria);
    }

    @Override
    public double calcularAluguel(int dias) {
        return getValorDiaria() * dias;
    }

    @Override
    public double calcularAluguel(int dias, double desconto) {
        return (getValorDiaria() * dias) - desconto;
    }

    @Override
    public void exibirDados() {
        System.out.println("----------------------------------------");
        System.out.println("Tipo.........: Carro");
        System.out.println("Placa........: " + getPlaca());
        System.out.println("Modelo.......: " + getModelo());
        System.out.println("Ano..........: " + getAno());
        System.out.printf("Valor diária.: R$ %.2f%n", getValorDiaria());
    }
}