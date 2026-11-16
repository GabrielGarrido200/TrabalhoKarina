package RPGMatchmaking;
public class ArvorePersonagens {
    private static class No {
        Personagem personagem;
        No esquerda;
        No direita;

        No(Personagem personagem) { this.personagem = personagem; }
    }

    private No raiz;

    public boolean inserir(Personagem personagem) {
        if (buscar(personagem.getId()) != null) return false;
        raiz = inserirNo(raiz, personagem);
        return true;
    }

    private No inserirNo(No atual, Personagem personagem) {
        if (atual == null) return new No(personagem);
        if (personagem.getId() < atual.personagem.getId()) atual.esquerda = inserirNo(atual.esquerda, personagem);
        else atual.direita = inserirNo(atual.direita, personagem);
        return atual;
    }

    public Personagem buscar(int id) {
        No atual = raiz;
        while (atual != null) {
            if (id == atual.personagem.getId()) return atual.personagem;
            if (id < atual.personagem.getId()) atual = atual.esquerda;
            else atual = atual.direita;
        }
        return null;
    }

    public boolean remover(int id) {
        if (buscar(id) == null) return false;
        raiz = removerNo(raiz, id);
        return true;
    }

    private No removerNo(No atual, int id) {
        if (atual == null) return null;
        if (id < atual.personagem.getId()) atual.esquerda = removerNo(atual.esquerda, id);
        else if (id > atual.personagem.getId()) atual.direita = removerNo(atual.direita, id);
        else {
            if (atual.esquerda == null) return atual.direita;
            if (atual.direita == null) return atual.esquerda;
            No sucessor = atual.direita;
            while (sucessor.esquerda != null) sucessor = sucessor.esquerda;
            atual.personagem = sucessor.personagem;
            atual.direita = removerNo(atual.direita, sucessor.personagem.getId());
        }
        return atual;
    }

    public void listar(int ordem) {
        if (raiz == null) {
            System.out.println("Nenhum personagem cadastrado.");
            return;
        }
        if (ordem == 1) emOrdem(raiz);
        else if (ordem == 2) preOrdem(raiz);
        else if (ordem == 3) posOrdem(raiz);
        else System.out.println("Percurso invalido.");
    }

    private void emOrdem(No no) {
        if (no == null) return;
        emOrdem(no.esquerda);
        System.out.println(no.personagem);
        emOrdem(no.direita);
    }

    private void preOrdem(No no) {
        if (no == null) return;
        System.out.println(no.personagem);
        preOrdem(no.esquerda);
        preOrdem(no.direita);
    }

    private void posOrdem(No no) {
        if (no == null) return;
        posOrdem(no.esquerda);
        posOrdem(no.direita);
        System.out.println(no.personagem);
    }
}
