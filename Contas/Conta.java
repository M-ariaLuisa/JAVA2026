package Contas;

public class Conta {

    private int numero;
    private String titular;
    private double saldo;

    public Conta(int numero, String titular, double saldo) throws ExcecaoDadoInvalido {
        if (numero <= 0) {
            throw new ExcecaoDadoInvalido("O número da conta deve ser maior que zero.");
        }
        if (titular == null || titular.trim().isEmpty()) {
            throw new ExcecaoDadoInvalido("O nome do titular não pode ficar vazio.");
        }
        if (saldo < 0) {
            throw new ExcecaoDadoInvalido("O saldo inicial não pode ser negativo.");
        }
        this.numero = numero;
        this.titular = titular.trim();
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}