package rede.social;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static Utilizador           utiActual   = null;
    public static ArrayList<Utilizador> utilizadores = new ArrayList<>();
    public static Scanner              input        = new Scanner(System.in);

    public static int lerOpcaoSegura() {
        try {
            int opcao = Integer.parseInt(input.nextLine().trim());
            return opcao;
        } catch (NumberFormatException e) {
            return -1; // sinaliza entrada inválida
        }
    }

    public static int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        return lerOpcaoSegura();
    }

//Menu Principal
    public static void mostrarMenuPrincipal() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Criar conta");
        System.out.println("2. Iniciar sessão");
        System.out.println("3. Fechar programa");

        int opcao = lerOpcao();
        switch (opcao) {
            case 1 -> criarConta();
            case 2 -> iniciarSessao();
            case 3 -> {
                System.out.println("Programa encerrado.");
                System.exit(0);
            }
            default -> {
                System.out.println("Opção inválida!");
                mostrarMenuPrincipal(); // repete o menu
            }
        }
    }

    public static void menuRedeSocial() {
        int opcao;
        do {
            System.out.println("\n===== MENU REDE SOCIAL =====");
            System.out.println("1. Página inicial");
            System.out.println("2. Chat");
            System.out.println("3. Definições");
            System.out.println("4. Seguidores");
            System.out.println("5. Terminar sessão");

            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> paginaInicial();
                case 2 -> chat();
                case 3 -> menuDefinicoes();
                case 4 -> menuSeguidores();
                case 5 -> {
                    utiActual = null;
                    System.out.println("Sessão terminada.");
                    mostrarMenuPrincipal();
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    //Pagina inicial
    public static void paginaInicial() {
        int op;
        do {
            System.out.println("\n===== PÁGINA INICIAL =====");
            System.out.println("1. Ver Feed (Publicações)");
            System.out.println("2. Procurar utilizador");
            System.out.println("3. Chat");
            System.out.println("4. Voltar");

            op = lerOpcao();
            switch (op) {
                case 1 -> publicacao();
                case 2 -> procurarUser();
                case 3 -> chat();
                case 4 -> { /* volta ao loop superior */ }
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 4);
    }

    // Menu de Publicações
    public static void publicacao() {
        int opcao;
        do {
            System.out.println("\n===== MENU PUBLICAÇÕES =====");
            System.out.println("1. Ver publicações de alguém");
            System.out.println("2. Ver as minhas publicações");
            System.out.println("3. Adicionar publicação");
            System.out.println("4. Eliminar publicação");
            System.out.println("5. Comentários");
            System.out.println("6. Voltar");

            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> Publicacao.exibirPulicacoes();
                case 2 -> Publicacao.exibirMinhasPublicaoes();
                case 3 -> Publicacao.adicionarPubli(utiActual.getUsername());
                case 4 -> Publicacao.eliminarPubli();
                case 5 -> menuComentarios();
                case 6 -> { /* volta */ }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
    }

    // Menu de Comentários

    public static void menuComentarios() {
        int opcao;
        do {
            System.out.println("\n===== MENU COMENTÁRIOS =====");
            System.out.println("1. Eliminar um meu comentário");
            System.out.println("2. Voltar");

            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> Comentario.eliminarComentario();
                case 2 -> { /* volta */ }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 2);
    }

    // Chat / Mensagens
    public static void chat() {
        int opcao;
        do {
            System.out.println("\n===== CHAT =====");
            System.out.println("1. Enviar mensagem");
            System.out.println("2. Ver conversa");
            System.out.println("3. Caixa de entrada");
            System.out.println("4. Voltar");

            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> enviarMensagem();
                case 2 -> verConversa();
                case 3 -> caixaDeEntrada();
                case 4 -> { /* volta */ }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    //EnViar mensagem
    public static void enviarMensagem() {
        System.out.print("Nome do destinatário: ");
        String nome = input.nextLine().trim();

        // Procurar
        Utilizador destinatario = null;
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equalsIgnoreCase(nome)) {
                destinatario = u;
                break;
            }
        }

        if (destinatario == null) {
            System.out.println("Utilizador não encontrado.");
            return;
        }
        if (destinatario.getId() == utiActual.getId()) {
            System.out.println("Não podes enviar mensagem a ti mesmo!");
            return;
        }

        System.out.print("Mensagem: ");
        String conteudo = input.nextLine();

        Mensagem m = new Mensagem(conteudo, utiActual.getId(),
                                  destinatario.getId(), LocalDateTime.now());
        GestorMensagens.guardarMensagem(m);
        System.out.println("Mensagem enviada para " + destinatario.getUsername() + "!");
    }

    /** Mostra a conversa completa com outro utilizador */
    public static void verConversa() {
        System.out.print("Nome do utilizador: ");
        String nome = input.nextLine().trim();

        Utilizador outro = null;
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equalsIgnoreCase(nome)) {
                outro = u;
                break;
            }
        }

        if (outro == null) {
            System.out.println("Utilizador não encontrado.");
            return;
        }

        ArrayList<Mensagem> conversa = GestorMensagens.verConversa(utiActual.getId(), outro.getId());

        if (conversa.isEmpty()) {
            System.out.println("Sem mensagens com " + outro.getUsername() + ".");
            return;
        }

        System.out.println("\n==== CONVERSA COM " + outro.getUsername().toUpperCase() + " ====");
        for (Mensagem msg : conversa) {
            String quem = (msg.getRemetente() == utiActual.getId()) ? "Tu" : outro.getUsername();
            System.out.println("[" + msg.getDataEnvio().toLocalDate() + "] "
                + quem + ": " + msg.getConteudo());
        }
    }

    /** Mostra a caixa de entrada (quem enviou mensagens ao utilizador atual) */
    public static void caixaDeEntrada() {
        ArrayList<Integer> remetentes = GestorMensagens.caixaDeEntrada(utiActual.getId());

        if (remetentes.isEmpty()) {
            System.out.println("Caixa de entrada vazia.");
            return;
        }

        System.out.println("\n==== CAIXA DE ENTRADA ====");
        for (int idRem : remetentes) {
            for (Utilizador u : utilizadores) {
                if (u.getId() == idRem) {
                    System.out.println("  - " + u.getUsername());
                    break;
                }
            }
        }
    }

    // Definições
    public static void menuDefinicoes() {
        int opcao;
        do {
            System.out.println("\n===== DEFINIÇÕES =====");
            System.out.println("1. Ver perfil");
            System.out.println("2. Mudar senha");
            System.out.println("3. Mudar email");
            System.out.println("4. Voltar");

            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> perfil();
                case 2 -> mudarSenha();
                case 3 -> mudarEmail();
                case 4 -> { /* volta */ }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    // Exibe o perfil do utilizador com sessão iniciada
    public static void perfil() {
        if (utiActual == null) {
            System.out.println("Ninguém iniciou sessão!");
            return;
        }
        System.out.println("\n===== MEU PERFIL =====");
        System.out.println("ID:               " + utiActual.getId());
        System.out.println("Username:         " + utiActual.getUsername());
        System.out.println("Email:            " + utiActual.getEmail());
        System.out.println("Data nascimento:  " + utiActual.getDataNasc());
        System.out.println("Membro desde:     " + utiActual.getDataCriac());
        System.out.println("Seguidores:       " + utiActual.getSeguidores().size());
        System.out.println("Seguindo:         " + utiActual.getSeguindo().size());
    }

    public static void mudarSenha() {
        if (utiActual == null) {
            System.out.println("Ninguém iniciou sessão!");
            return;
        }

        System.out.print("Senha atual: ");
        String senhaAtual = input.nextLine();

        if (!utiActual.getSenha().equals(senhaAtual)) {
            System.out.println("Senha nova incorreta!");
            return;
        }

        System.out.print("Nova senha: ");
        String novaSenha = input.nextLine();
        while (!Utilizador.verificar_senha(novaSenha)) {
            System.out.print("Tente novamente: ");
            novaSenha = input.nextLine();
        }

        utiActual.setSenha(novaSenha);
        GestorUtilizadores.reescreverFicheiro(utilizadores);
        System.out.println("Senha alterada com sucesso!");
    }

        public static void mudarEmail() {
        if (utiActual == null) {
            System.out.println("Ninguém iniciou sessão!");
            return;
        }

        System.out.print("Novo email: ");
        String novoEmail = input.nextLine();
        while (!Utilizador.verificar_email(novoEmail)) {
            System.out.println("Email inválido!");
            System.out.print("Tente novamente: ");
            novoEmail = input.nextLine();
        }

        utiActual.setEmail(novoEmail);
        GestorUtilizadores.reescreverFicheiro(utilizadores);
        System.out.println("Email alte1rado com sucesso!");
    }

    // Criar conta
    public static void criarConta() {
        System.out.println("\n--- Criar conta ---");

        // Senha
        System.out.print("Senha: ");
        String senha = input.nextLine();
        while (!Utilizador.verificar_senha(senha)) {
            System.out.print("Tente novamente: ");
            senha = input.nextLine();
        }

        // Username
        System.out.print("Username: ");
        String username = input.nextLine().trim();

        // Email
        System.out.print("Email: ");
        String email = input.nextLine();
        while (!Utilizador.verificar_email(email)) {
            System.out.println("Email inválido!");
            System.out.print("Tente novamente: ");
            email = input.nextLine();
        }

        // Data de nascimento
        System.out.print("Data de nascimento (ex: 2000-05-15): ");
        String dataNasc = input.nextLine();

        // Definir ID com base no último utilizador registado
        utilizadores = GestorUtilizadores.carregarUtilizadores();
        int id = utilizadores.isEmpty() ? 1 : utilizadores.getLast().getId() + 1;

        Utilizador novo = new Utilizador(senha, username, email, id, dataNasc, LocalDate.now());
        GestorUtilizadores.guardarUtilizador(novo);
        utilizadores.add(novo);

        System.out.println("Conta criada com sucesso! Bem-vindo(a) ao Tech Leeks, " + username + "!");
        System.out.println("Agora inicia sessão...\n");
        iniciarSessao();
    }
    
    // Iniciar sessão
    public static void iniciarSessao() {
        System.out.print("Email: ");
        String email = input.nextLine().trim();

        System.out.print("Senha: ");
        String senha = input.nextLine();

        // Carrega utilizadores, depois aplica os follows na mesma lista
        utilizadores = GestorUtilizadores.carregarUtilizadores();
        GestorSeguidores.carregarFollows(utilizadores); // associa seguidores na lista já carregada

        for (Utilizador u : utilizadores) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                if (u.getSenha().equals(senha)) {
                    utiActual = u;
                    System.out.println("Sessão iniciada com sucesso! Olá, " + u.getUsername() + "!");
                    menuRedeSocial();
                    return;
                } else {
                    System.out.println("Senha incorreta!");
                    return;
                }
            }
        }

        System.out.println("Utilizador não encontrado. Por favor, crie uma conta.");
        mostrarMenuPrincipal();
    }

    // Seguidores
    public static void menuSeguidores() {
        if (utiActual == null) {
            System.out.println("Ninguém iniciou sessão.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n===== MENU SEGUIDORES =====");
            System.out.println("1. Ver os meus seguidores");
            System.out.println("2. Ver quem estou a seguir");
            System.out.println("3. Seguir utilizador");
            System.out.println("4. Deixar de seguir");
            System.out.println("5. Voltar");

            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> utiActual.showfollow();
                case 2 -> utiActual.showfollowing();
                case 3 -> seguir();
                case 4 -> deixarDeSeguir();
                case 5 -> { /* volta */ }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    // Segue outro utilizador pelo username
    public static void seguir() {
        System.out.print("Username de quem deseja seguir: ");
        String nome = input.nextLine().trim();

        for (Utilizador u : utilizadores) {
            if (u.getUsername().equalsIgnoreCase(nome)) {
                utiActual.follow(u);
                GestorUtilizadores.reescreverFicheiro(utilizadores);
                return;
            }
        }
        System.out.println("Utilizador não encontrado.");
    }

    // Deixa de seguir outro utilizador pelo username
    public static void deixarDeSeguir() {
        System.out.print("Username de quem quer deixar de seguir: ");
        String nome = input.nextLine().trim();

        for (Utilizador u : utilizadores) {
            if (u.getUsername().equalsIgnoreCase(nome)) {
                utiActual.unfollow(u);
                GestorUtilizadores.reescreverFicheiro(utilizadores);
                return;
            }
        }
        System.out.println("Utilizador não encontrado.");
    }
    
    //Procurar utilizador
    public static void procurarUser() {
        System.out.print("Username a procurar: ");
        String nome = input.nextLine().trim();

        for (Utilizador u : utilizadores) {
            if (u.getUsername().equalsIgnoreCase(nome)) {
                System.out.println("\n===== PERFIL DE " + u.getUsername().toUpperCase() + " =====");
                System.out.println("Username:    " + u.getUsername());
                System.out.println("Seguidores:  " + u.getSeguidores().size());
                System.out.println("Seguindo:    " + u.getSeguindo().size());
                return;
            }
        }
        System.out.println("Utilizador \"" + nome + "\" não encontrado.");
    }
}
