package RPGMatchmaking;

public class Personagem {
    private int id;
    private String nome;
    private int nivel;
    private PilhaInventario inventario;

    public Personagem(int id, String nome, int nivel) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.inventario = new PilhaInventario();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public PilhaInventario getInventario() {
        return inventario;
    }

    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Nivel: " + nivel;
    }
}