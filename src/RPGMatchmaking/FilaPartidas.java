package RPGMatchmaking;

public class FilaPartidas {
    private static class No {
        Personagem jogador;
        No proximo;

        No(Personagem jogador) {
            this.jogador = jogador;
        }
    }

    private No inicio;
    private No fim;
    private int quantidade;

    public boolean adicionar(Personagem jogador) {
        if (buscar(jogador.getId()) != null) return false;

        No novo = new No(jogador);
        if (fim == null) inicio = novo;
        else fim.proximo = novo;

        fim = novo;
        quantidade++;
        return true;
    }

    public Personagem buscar(int id) {
        No atual = inicio;
        while (atual != null) {
            if (atual.jogador.getId() == id) return atual.jogador;
            atual = atual.proximo;
        }
        return null;
    }

    public Personagem retirarPrimeiro() {
        if (inicio == null) return null;

        Personagem jogador = inicio.jogador;
        inicio = inicio.proximo;
        if (inicio == null) fim = null;

        quantidade--;
        return jogador;
    }

    public boolean remover(int id) {
        No atual = inicio;
        No anterior = null;

        while (atual != null) {
            if (atual.jogador.getId() == id) {
                if (anterior == null) inicio = atual.proximo;
                else anterior.proximo = atual.proximo;

                if (atual == fim) fim = anterior;
                quantidade--;
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }

    public int tamanho() {
        return quantidade;
    }

    public void listar() {
        if (inicio == null) System.out.println("Fila vazia.");

        No atual = inicio;
        while (atual != null) {
            System.out.println(atual.jogador);
            atual = atual.proximo;
        }
    }
}