package RPGMatchmaking;

import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);
    private static ArvorePersonagens personagens = new ArvorePersonagens();
    private static FilaPartidas fila = new FilaPartidas();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== RPG ARENA ===");
            System.out.println("1 - Cadastrar personagem");
            System.out.println("2 - Buscar personagem");
            System.out.println("3 - Remover personagem");
            System.out.println("4 - Listar personagens");
            System.out.println("5 - Entrar na fila de partida");
            System.out.println("6 - Buscar jogador na fila");
            System.out.println("7 - Sair da fila");
            System.out.println("8 - Ver fila");
            System.out.println("9 - Formar partida");
            System.out.println("10 - Adicionar item ao inventario");
            System.out.println("11 - Buscar item no inventario");
            System.out.println("12 - Usar ultimo item");
            System.out.println("13 - Ver inventario");
            System.out.println("0 - Sair");
            opcao = lerNumero("Opcao: ");

            switch (opcao) {
                case 1:
                    int id = lerNumero("ID: ");
                    System.out.print("Nome: ");
                    String nome = entrada.nextLine();
                    int nivel = lerNumero("Nivel: ");

                    if (id <= 0 || nivel <= 0 || nome.trim().isEmpty()) {
                        System.out.println("Dados invalidos.");
                    } else if (personagens.inserir(new Personagem(id, nome, nivel))) {
                        System.out.println("Personagem cadastrado.");
                    } else {
                        System.out.println("Ja existe personagem com esse ID.");
                    }
                    break;

                case 2:
                    Personagem encontrado = selecionarPersonagem();
                    if (encontrado != null) System.out.println(encontrado);
                    break;

                case 3:
                    int removerId = lerNumero("ID para remover: ");
                    if (personagens.buscar(removerId) == null) {
                        System.out.println("Personagem nao encontrado.");
                    } else {
                        fila.remover(removerId);
                        personagens.remover(removerId);
                        System.out.println("Personagem removido.");
                    }
                    break;

                case 4:
                    System.out.println("1 - Em ordem | 2 - Pre ordem | 3 - Pos ordem");
                    int ordem = lerNumero("Percurso: ");
                    personagens.listar(ordem);
                    break;

                case 5:
                    Personagem p = selecionarPersonagem();
                    if (p != null) {
                        System.out.println(
                            fila.adicionar(p) ? "Entrou na fila." : "Ja esta na fila."
                        );
                    }
                    break;

                case 6:
                    int buscaFila = lerNumero("ID do jogador: ");
                    Personagem naFila = fila.buscar(buscaFila);
                    System.out.println(naFila == null ? "Nao esta na fila." : naFila);
                    break;

                case 7:
                    System.out.println(
                        fila.remover(lerNumero("ID para sair: "))
                            ? "Saiu da fila." : "Nao esta na fila."
                    );
                    break;

                case 8:
                    fila.listar();
                    break;

                case 9:
                    if (fila.tamanho() < 2) {
                        System.out.println("Sao necessarios pelo menos 2 jogadores.");
                    } else {
                        Personagem primeiro = fila.retirarPrimeiro();
                        Personagem segundo = fila.retirarPrimeiro();
                        System.out.println(
                            "Partida formada: " + primeiro.getNome()
                                + " X " + segundo.getNome()
                        );
                    }
                    break;

                case 10:
                    Personagem dono = selecionarPersonagem();
                    if (dono != null) {
                        System.out.print("Nome do item: ");
                        String item = entrada.nextLine();
                        if (item.trim().isEmpty()) {
                            System.out.println("Item invalido.");
                        } else {
                            dono.getInventario().adicionar(item);
                            System.out.println("Item guardado.");
                        }
                    }
                    break;

                case 11:
                    Personagem donoBusca = selecionarPersonagem();
                    if (donoBusca != null) {
                        System.out.print("Item para buscar: ");
                        String itemBusca = entrada.nextLine();
                        System.out.println(
                            donoBusca.getInventario().buscar(itemBusca)
                                ? "Item encontrado." : "Item nao encontrado."
                        );
                    }
                    break;

                case 12:
                    Personagem donoUso = selecionarPersonagem();
                    if (donoUso != null) {
                        String usado = donoUso.getInventario().remover();
                        System.out.println(
                            usado == null ? "Inventario vazio." : "Item usado: " + usado
                        );
                    }
                    break;

                case 13:
                    Personagem donoLista = selecionarPersonagem();
                    if (donoLista != null) donoLista.getInventario().listar();
                    break;

                case 0:
                    System.out.println("Encerrando o jogo.");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        entrada.close();
    }

    private static int lerNumero(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro.");
            }
        }
    }

    private static Personagem selecionarPersonagem() {
        Personagem p = personagens.buscar(lerNumero("ID do personagem: "));
        if (p == null) System.out.println("Personagem nao encontrado.");
        return p;
    }
}