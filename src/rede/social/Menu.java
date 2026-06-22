
package rede.social;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        System.out.println("4. Seguidores");

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
    
    public static void paginaInicial() {
        
        System.out.println("===== Página Inicial =====");
        System.out.println("     1.Ver Feed  ");
        System.out.println("     2.Procurar   ");
        System.out.println("     3.chat       ");
        System.out.println("     4.Voltar    ");
     int op=lerOpcao();
    
        do{
        switch(op){
            case 1 -> publicacao(); // funcao publicar !feita???
             case 2-> procuraruser();  // procurar por perfil e exibir   !feita ; Bug, é necessário procurar 3x para mostrar resultado
             case 3-> chat(); //!feita
             case 4-> menuRedeSocial();
             default -> System.out.println("Opção inválida!");
        }
        }while(op!=4);
    }// chat, ver feed, publicacao não implementada, 
    
      
    public static void chat() {
        int opcao;
            
        do{
            System.out.println("\n===== CHAT =====");
            System.out.println("1. Enviar mensagem");
            System.out.println("2. Ver conversa");
            System.out.println("3. Caixa de entrada");
            System.out.println("4. Voltar");
            opcao = lerOpcao();
            
            switch(opcao) {
                case 1 -> enviarMensagem();
                case 2 -> verConversa();
                case 3 -> caixaDeEntrada();
                case 4 -> {
                    System.out.println("A voltar...");
                    menuRedeSocial();
                }
                default -> System.out.println("Opção inválida!");
            }
        } while(opcao != 4);
    } // Não implementado

    public static void enviarMensagem() {
        System.out.println("Nome do destinatário: ");
        String nome = input.nextLine();
        
        Utilizador destinatario = null;
        for( Utilizador u : utilizadores) {
            if (u.getUsername().equalsIgnoreCase(nome)){
                destinatario = u;
                break;
            }
        }
        
        if (destinatario == null) {
            System.out.println("utilizador não encontrado");
            return ;
        }
        
        if (destinatario.getId() == utiActual.getId()){
            System.out.println("Não podes enviar mensagem a ti mesmo!");
            return ;
        }
        
        System.out.print("Mensagem: ");
        String conteudo = input.nextLine();
        
        Mensagem m = new Mensagem(conteudo, utiActual.getId(), destinatario.getId(), LocalDateTime.now());
        GestorMensagens.guardarMensagem(m);
        System.out.println("Mensagem enviada para " + destinatario.getUsername() + "!");
    }
    
    public static void verConversa() {
        System.out.println("Nome do utilizador: ");
        String nome = input.nextLine();
        
        Utilizador outro = null;
        for(Utilizador u : utilizadores) {
            if(u.getUsername().equalsIgnoreCase(nome)) {
                outro = u;
                break;
            }
        }
        
        if (outro == null){
            System.out.println("Utilizado não encontrado.");
            return ;
        }
        
        ArrayList<Mensagem> conversa = GestorMensagens.verConversa(utiActual.getId(), outro.getId());
        
        if(conversa.isEmpty()) {
            System.out.println("Sem mensagens com " + outro.getUsername() + ".");
            return ;
        }
        
        System.out.println("\n==== CONVERSA COM " + outro.getUsername().toUpperCase()+ " =====");
        for(Mensagem m : conversa) {
            String quem = m.getRemetente() == utiActual.getId() ? "Tu" : outro.getUsername();
            System.out.println("[" + m.getDataEnvio().toLocalDate() +"]" + quem + ": " + m.getConteudo());
        }
    }
    
    public static void caixaDeEntrada() {
        ArrayList<Integer> remetentes = GestorMensagens.caixaDeEntrada(utiActual.getId());
        
        if( remetentes.isEmpty()){
            System.out.println("Caixa de entrada vazia.");
        }
        
        System.out.println("\n==== CAIXA DE ENTRADA ====");
        for(int idRem : remetentes) {
            for(Utilizador u : utilizadores){
                if(u.getId() == idRem) {
                    System.out.println("- " + u.getUsername());
                    break;
                }
            }
        }
        
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
  
    } //Feito
    
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
} // Feita
    //Mudar email
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
} // Feita
    
    public static void criarConta(ArrayList<Utilizador> utilizadores) { // Feita
        Scanner input = new Scanner(System.in);
        
        
        //Senha
        String senha;
        //System.out.println("A senha tem que ter 12 caracteres");
        System.out.println("Insira a sua senha: ");GestorUtilizadores.carregarUtilizadores();
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
        utilizadores = GestorUtilizadores.carregarUtilizadores();
        int id = 0;
        if(utilizadores.isEmpty()){
               id = 1;
        }else{
               id = utilizadores.getLast().getId() + 1;
        }
        
            Utilizador u = new Utilizador(senha, username, email,id, dataNasc, LocalDate.now());
            GestorUtilizadores.guardarUtilizador(u);
            utilizadores.add(u);
        
            System.out.println("Conta criada com sucesso! Bem vindo ao Tech Leeks " + utilizadores.get(id-1).getUsername() + "\n O próximo passo é iniciar sessão!");
            System.out.println("");
            iniciarSessao();
        
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
             mostrarMenuPrincipal();
}
    
    //Menu Redes Sociais Opcoes
 
  public static void procuraruser(){
      
  }
 

             public static void publicacao(){ 


            System.out.println("");
            
             }
            
            
public static void menuSeguidores(){

    if(utiActual == null){
        System.out.println("Ninguém iniciou sessão");
        return;
    }

    int opcao;

    do{

        System.out.println("\n ===== MENU SEGUIDORES =====");
        System.out.println("1. Mostrar seguidores");
        System.out.println("2. Mostrar seguindo");
        System.out.println("3. Seguir");
        System.out.println("4. Deixar de seguir");
        System.out.println("5. Voltar");

        opcao = lerOpcao();

        switch(opcao){

            case 1:
                //O utilizador que está logado mostra quem está a seguir.
                utiActual.showfollow();
                break;

            case 2:
                 //O utilizador que está logado mostra quem está  seguindo.
                utiActual.showfollowing();
                break;

            case 3:
              
             seguir();
               break; 
             

            case 4:
                deixarDeSeguir();
                break;

            case 5:
                System.out.println(" voltar");
                break;

            default:
                System.out.println("Opção inválida!");
        }

    }while(opcao != 5);
}// feito
    public static void seguir(){

    System.out.print("Digite o nome de quem desejas seguir: ");
    String nome = input.nextLine();

    for(Utilizador u : utilizadores){

        if(u.getUsername().equalsIgnoreCase(nome)){

            utiActual.follow(u);

            GestorUtilizadores.reescreverFicheiro(utilizadores);

            return;
        }
    }

    System.out.println("Utilizador não encontrado.");
}  //feito
public static void deixarDeSeguir(){

    System.out.print("Digite o nome de quem queres deixar de seguir: ");
    String nome = input.nextLine();

    for(Utilizador u : utilizadores){

        if(u.getUsername().equalsIgnoreCase(nome)){

            utiActual.unfollow(u);

            GestorUtilizadores.reescreverFicheiro(utilizadores);

            return;
        }
    }

    System.out.println("Utilizador não encontrado.");
}//feito
}
