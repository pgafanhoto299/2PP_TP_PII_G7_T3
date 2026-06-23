
package rede.social;

import java.time.LocalDate;
import java.util.ArrayList;

public class Utilizador { 
    private String senha, username, email, dataNasc;
    private int id;
   private ArrayList<Utilizador> seguidores, seguindo;
    private LocalDate dataCriac;

    
    
      public Utilizador(String senha, String username, String email, int id, String dataNasc, LocalDate dataCriac) {
        this.senha = senha;
        this.username = username;
        this.email = email;
        this.id = id;
        this.seguidores = new ArrayList<>();
        this.seguindo = new ArrayList<>();
        this.dataNasc = dataNasc;
        this.dataCriac = LocalDate.now();
        this.dataCriac = dataCriac;
    }

public LocalDate getDataCriac() {
        return dataCriac;
}
    
    public String getSenha() {
        return senha;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Utilizador> getSeguidores() {
    return seguidores;
}

    public ArrayList<Utilizador> getSeguindo() {
    return seguindo;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setSenha(String senha){
       this.senha=senha;
   
   }
    
  public void setDataCriac(LocalDate dataCriac) {
        this.dataCriac = dataCriac;
    }
    public void setUsername(String username) {
        
        this.username = username;
    }

    public void setEmail(String email) {
        if(verificar_email(email))
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

   public void setSeguidores(ArrayList<Utilizador> seguidores) {
    this.seguidores = seguidores;
}

     public void setSeguindo(ArrayList<Utilizador> seguindo) {
    this.seguindo = seguindo;
    }
    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    public static boolean  verificar_email(String emailver){
        if(emailver.matches(".+@.+\\.com")){
      System.out.println("email valido");
            return true;
        }
 System.out.println("email invalido");
        return false;
    }
    public static boolean verificar_senha(String senha_ver){
        if(senha_ver.length()<=4){
            System.out.println("Senha deve ter pelo menos 5 caracteres");    
            return false;      
        }
         if(senha_ver.matches(".*[0-9].*" ) && senha_ver.matches(".*[a-z].*") && senha_ver.matches(".*[^a-zA-Z0-9 ].*")){
             System.out.println("senha valida");
             return true;
        }
      System.out.println("senha invalida deve ter numeros letras e caractere  especiais");
    return false;
  }
    
 public boolean follow(Utilizador utilizador) {
     
     //verifica se segue a si mesmo e impede
    if(utilizador.getId() == this.id){
        System.out.println("Não pode seguir a si mesmo");
        return false;
    }

    //verifica se já segue este utilizador
    if(seguindo.contains(utilizador)){
        System.out.println("Já segue este utilizador.");
        return false;
    }
    
    // Adiciona na lista dos seguidos
    seguindo.add(utilizador);
    
     //Adiciona aos seguidores dooutro utilizador
    utilizador.getSeguidores().add(this);

    System.out.println("Agora segue " + utilizador.getUsername());

    return true;
}
 
 public boolean unfollow(Utilizador utilizador){

    if(!seguindo.contains(utilizador)){
        System.out.println("Não segue este utilizador.");
        return false;
    }
    
    //remove da lista de seguidos
    
    seguindo.remove(utilizador);
    
    //remove dos seguidores do outro utilizador
    utilizador.getSeguidores().remove(this);

    System.out.println("Deixou de seguir " + utilizador.getUsername());

    return true;
}
 
 public void showfollow(){

     //Verifica se a lista de seguidores está vazia!
    if(seguidores.isEmpty()){
        System.out.println("Não tem nenhum seguidor");
        return;
    }

    System.out.println("\n Seguidores:");
     
    //perccorre a lista para mostrar os seguidores
     for(int i = 0; i < seguidores.size(); i++){

        System.out.println(" " + seguidores.get(i).getUsername());

    }
}
 
 public void showfollowing(){

     //verifica se segue um usuário!
    if(seguindo.isEmpty()){
        System.out.println("Não segue ninguém");
        return;
    }

    System.out.println("\n Seguindo:");
      
     //percorre para mostrar as pessoas que segue
      for(int i = 0; i < seguindo.size(); i++){

        System.out.println(" " + seguindo.get(i).getUsername());

    }
}
 
 
}
