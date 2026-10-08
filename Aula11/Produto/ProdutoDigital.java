package Produto;

public class ProdutoDigital extends Produto implements Venda {

    public ProdutoDigital(
            int codigo,
            String nome,
            double preco) {

        super(codigo, nome, preco);
    }

    @Override
    public String getTipo() {
        return "Produto Digital";
    }

    // Venda normal
    @Override
    public double realizarVenda(int quantidade) {

        return getPreco() * quantidade;
    }

    // Venda com desconto
    @Override
    public double realizarVenda(int quantidade, double desconto) {

        double subtotal = getPreco() * quantidade;

        double valorDesconto = subtotal * (desconto / 100);

        return subtotal - valorDesconto;
    }
}