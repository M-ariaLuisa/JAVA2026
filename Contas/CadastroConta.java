package Contas;

import java.util.ArrayList;

public class cadastroConta {
    public static final int LIMITE_CONTAS = 100;

    private final ArrayList<Conta> contas = new ArrayList<>();

    public void inserir(Conta conta) throws ExcecaoRepositorio, ExcecaoElementoJaExistente {
        if (contas.size() >= LIMITE_CONTAS) {
            throw new ExcecaoRepositorio("Limite máximo de " + LIMITE_CONTAS + " contas atingido.");
        }
        for (Conta c : contas) {
            if (c.getNumero().equals(conta.getNumero())) {
                throw new ExcecaoElementoJaExistente(
                        "Já existe uma conta cadastrada com o número " + conta.getNumero() + ".");
            }
        }
        contas.add(conta);
    }

    public Conta buscar(String numero) throws ExcecaoElementoInexistente {
        for (Conta c : contas) {
            if (c.getNumero().equals(numero.trim())) {
                return c;
            }
        }
        throw new ExcecaoElementoInexistente("Conta " + numero.trim() + " não encontrada.");
    }

    public void remover(String numero) throws ExcecaoElementoInexistente {
        Conta conta = buscar(numero);
        contas.remove(conta);
    }
}