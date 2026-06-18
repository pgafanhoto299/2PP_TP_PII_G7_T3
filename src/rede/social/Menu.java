
package rede.social;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu {
    
    //serve para guardar todos os utilizdaores que estão logados no momento
    public static Utilizador utiActual = null;
     
     public static ArrayList<Utilizador> utilizadores = new ArrayList<>(); // vamos ter que carregar todos os ficheiros a partir do menu
    
     static Scanner input = new Scanner(System.in);
    
     // Menu Principal 
     public static void mostrarMenuPrincipal() {

        System.out.println("===== MENU PRINCIPAL =====");
        System.out.println("1. Criar conta");
        System.out.println("2. Iniciar Sessão");
        System.out.println("3. Fechar programa");

        System.out.println("Escolha uma opção: ");
        int opcao = input.nextInt();
        input.nextLine();
        switch (opcao) {

            case 1 -> criarConta(utilizadores);

            case 2 -> iniciarSessao();

            case 3 -> {
                System.out.println("Programa encerrado.");
                System.exit(0);
            }

            default -> System.out.println("Opção inválida!");
        }
    }
     
    //Ler a opcao
   public static int lerOpcao(){
    System.out.println("Escolha uma opção: ");
    int opcao = input.nextInt();
    input.nextLine(); // limpa o Enter
    return opcao;
}
    //Menu Rede Social
    public static void menuRedeSocial() {
        int opcao;

        System.out.println("===== MENU REDE SOCIAL =====");
        System.out.println("1. Página inicial");
        System.out.println("2. Chat");
        System.out.println("3. Definições");
        System.out.println("4. Terminar sessão");
        opcao = lerOpcao();
        
        do {
            switch (opcao) {
                case 1 -> paginaInicial();
                case 2 -> chat();
                case 3 -> menuDefinicoes();
                case 4 -> {
                    utiActual = null;
                    System.out.println("Sessão terminada.");
                    mostrarMenuPrincipal();
                    
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }
    
    public static void paginaInicial() {
        
        System.out.println("===== Página Inicial =====");
        System.out.println("     1.Ver Feed  ");
        System.out.println("     2.Procurar   ");
        System.out.println("     3.chat       ");
        System.out.println("     4.Voltar    ");
     int op=lerOpcao();
    
        do{
        switch(op){
            case 1 -> publicacao(); // funcao publicar !feita
             case 2-> Utilizador.procurarUser();  // procurar por perfil e exibir   !feita
             case 3-> chat(); //!feita
             case 4->mostrarMenuPrincipal();
             default -> System.out.println("Opção inválida!");
        }
        }while(op!=4);
    }
        

    
    
    public static void chat() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void menuDefinicoes(){
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
                case 4 -> menuRedeSocial();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }
    
    //Perfil
    public static void perfil(){
        if (utiActual == null) {
            System.out.println("Ninguém iniciou sessão!");
            return;
        }
        System.out.println("\n===== MEU PERFIL =====");
        System.out.println("ID:               " + utiActual.getId());
        System.out.println("Nome:             " + utiActual.getUsername());
        System.out.println("Email:            " + utiActual.getEmail());
        System.out.println("Data nascimento:  " + utiActual.getDataNasc());
        System.out.println("Membro desde:     " + utiActual.getDataCriac());
        System.out.println("Seguidores:       " + utiActual.getSeguidores().size());
        System.out.println("Seguindo:         " + utiActual.getSeguindo().size());
  
    }
    
    //Mudar senha
    public static void mudarSenha() {

    if(utiActual == null){
        System.out.println("Ninguém iniciou sessão!");
        return;
    }

    System.out.print("Digite a senha atual: ");
    String senhaActual = input.nextLine();

    if(!utiActual.getSenha().equals(senhaActual)){
        System.out.println("Senha nova incorreta!");
        return;
    }

    String novaSenha;

    System.out.print("Digite a nova senha: ");

    while(!Utilizador.verificar_senha(novaSenha = input.nextLine())){
        System.out.println("Senha inválida!");
        System.out.print("Digite novamente: ");
    }

        utiActual.setSenha(novaSenha);
    
    // Gravar alteração no ficheiro
    GestorUtilizadores.reescreverFicheiro(utilizadores);;
    
    System.out.println("Senha alterada com sucesso!");
}
   public static void mudarEmail() {

    if(utiActual == null){
        System.out.println("Ninguém iniciou  sessão !");
        return;
    }

    String novoEmail;

    System.out.print("Digite o novo email: ");

    while(!Utilizador.verificar_email(novoEmail = input.nextLine())){
        System.out.println("Email inválido!");
        System.out.print("Digite novamente: ");
    }

    utiActual.setEmail(novoEmail);
    // Gravar alteração no ficheiro
    GestorUtilizadores.reescreverFicheiro(utilizadores);;
    
    System.out.println("Email alterado com sucesso!");
}
    
    public static void criarConta(ArrayList<Utilizador> utilizadores) {
        Scanner input = new Scanner(System.in);
        
        
        //Senha
        String senha;
        //System.out.println("A senha tem que ter 12 caracteres");
        System.out.println("Insira a sua senha: ");
        while(!Utilizador.verificar_senha(senha = input.nextLine())){
            System.out.println("Insira um senha válido:");
        }
        
        
        //Nome
            System.out.println("Insira o seu nome: ");
        String username = input.nextLine();// FUncao verificar nome
        
        
        //Email
            String email;
            System.out.println("Insira o seu email: ");
            while(!Utilizador.verificar_email(email = input.nextLine())){
            System.out.println("Insira um email válido:");
            }
                System.out.println("Insira a sua data de nascimento: ");
                String dataNasc = input.nextLine(); //FUncao Verificar data
        
        //Definir o ID baseado no ID do ultimo usuario cadastrado
        int id = 0;
        if(utilizadores.isEmpty()){
               id = 1;
        }else{
               id = utilizadores.getLast().getId() + 1;
        }
        
            Utilizador u = new Utilizador(senha, username, email,id, dataNasc, LocalDate.now());
            GestorUtilizadores.guardarUtilizador(u);
            utilizadores.add(u);
        
            System.out.println("Conta criada com sucesso! Bem vindo a  GVO" + utilizadores.get(id-1).getUsername() + "\n O próximo passo é iniciar sessão!");
        
        
    }
    
    
    public static void iniciarSessao(){

        System.out.println("Digite o seu email: ");
        String email = input.nextLine();

        System.out.println("Digite a sua senha: ");
        String senha = input.nextLine();
    
    //verificar as credenciais
        //Loop para pesquisar as credenciais do usuario ao iniciar sessão

    for(int i = 0; i < utilizadores.size(); i++){

        if(utilizadores.get(i).getEmail().equalsIgnoreCase(email)){

            if(utilizadores.get(i).getSenha().equals(senha)){

                utiActual = utilizadores.get(i);

                System.out.println("Sessão iniciada com sucesso!");

                menuRedeSocial();
                return;

            }else{

                System.out.println("Senha incorreta!");
                return;
            }
        }
    }

             System.out.println("Usuário inexistente!");
             System.out.println("Por favor, crie uma conta.");
}
    
    //Menu Redes Sociais Opcoes
 
  

             public static void publicacao(){ 


            System.out.println("");
    
}
}
