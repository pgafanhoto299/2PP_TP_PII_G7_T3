package rede.social;

/**
 * Ponto de entrada da aplicação "Tech Leeks" — Rede Social.
 *
 * Ao iniciar:
 *   1. Carrega todos os utilizadores do ficheiro.
 *   2. Reconstrói as relações de seguidores na lista em memória.
 *   3. Abre o menu principal.
 *
*/

public class REDESOCIAL {

    public static void main(String[] args) {
        // 1. Carrega utilizadores para a lista global do Menu
        Menu.utilizadores = GestorUtilizadores.carregarUtilizadores();

        // 2. Reconstrói as relações de follow (usa a lista já populada)
        GestorSeguidores.carregarFollows(Menu.utilizadores);

        // 3. Abre o menu principal
        Menu.mostrarMenuPrincipal();
    }
}
