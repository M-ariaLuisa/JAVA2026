package Contas;

import java.util.ArrayList;

public class CadastroConta {
    public static final int LIMITE_CONTAS = 100;

    private final ArrayList<Conta> contas = new ArrayList<>();

    public void inserir(Conta conta) throws ExcecaoRepositorio, ExcecaoElementoJaExistente {
        if (contas.size() >= LIMITE_CONTAS) {
            throw new ExcecaoRepositorio("Limite máximo de " + LIMITE_CONTAS + " contas atingido.");
        }
        for (Conta c : contas) {
            if (c.getNumero() == conta.getNumero()) {
                throw new ExcecaoElementoJaExistente(
                        "Já existe uma conta cadastrada com o número " + conta.getNumero() + ".");
            }
        }
        contas.add(conta);
    }

    public Conta buscar(int numero) throws ExcecaoElementoInexistente {
        for (Conta c : contas) {
            if (c.getNumero() == numero) {
                return c;
            }
        }
        throw new ExcecaoElementoInexistente("Conta " + numero + " não encontrada.");
    }

    public void remover(int numero) throws ExcecaoElementoInexistente {
        Conta conta = buscar(numero);
        contas.remove(conta);
    }
}