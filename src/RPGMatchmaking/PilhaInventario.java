package RPGMatchmaking;

public class PilhaInventario {
    private static class No {
        String item;
        No proximo;

        No(String item) {
            this.item = item;
        }
    }

    private No topo;

    public void adicionar(String item) {
        No novo = new No(item);
        novo.proximo = topo;
        topo = novo;
    }

    public boolean buscar(String item) {
        No atual = topo;
        while (atual != null) {
            if (atual.item.equalsIgnoreCase(item)) return true;
            atual = atual.proximo;
        }
        return false;
    }

    public String remover() {
        if (topo == null) return null;

        String item = topo.item;
        topo = topo.proximo;
        return item;
    }

    public void listar() {
        if (topo == null) System.out.println("Inventario vazio.");

        No atual = topo;
        while (atual != null) {
            System.out.println("- " + atual.item);
            atual = atual.proximo;
        }
    }
}