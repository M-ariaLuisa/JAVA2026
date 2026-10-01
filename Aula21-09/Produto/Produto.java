package Produto;

public abstract class Produto {

    private int codigo;
    private String nome;
    private double preco;

    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public abstract String getTipo();

    public void exibirDados() {
        System.out.println("----------------------------------------");
        System.out.println("Tipo.....: " + getTipo());
        System.out.println("Código...: " + codigo);
        System.out.println("Nome.....: " + nome);
        System.out.printf("Preço....: R$ %.2f%n", preco);
    }
}