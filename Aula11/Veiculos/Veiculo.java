package Veiculos;

public abstract class Veiculo {

    private String placa;
    private String modelo;
    private int ano;
    private double valor_diaria;

    public Veiculo(String placa, String modelo, int ano, double valor_diaria) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.valor_diaria = valor_diaria;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValorDiaria() {
        return valor_diaria;
    }

    public void setValorDiaria(double valor_diaria) {
        this.valor_diaria = valor_diaria;
    }

    public void exibirDados() {
        System.out.println("----------------------------------------");
        System.out.println("Placa........: " + placa);
        System.out.println("Modelo.......: " + modelo);
        System.out.println("Ano..........: " + ano);
        System.out.printf("Valor diária.: R$ %.2f%n", valor_diaria);
    }
}