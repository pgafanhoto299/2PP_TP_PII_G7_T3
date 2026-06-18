
package rede.social;

import java.time.LocalDate;
import java.util.ArrayList;

public class Utilizador { 
    private String senha, username, email, dataNasc;
    private int id;
    private ArrayList<Integer> seguidores, seguindo;
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

    public ArrayList<Integer> getSeguidores() {
        return seguidores;
    }

    public ArrayList<Integer> getSeguindo() {
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

    public void setSeguidores(ArrayList<Integer> seguidores) {
        this.seguidores = seguidores;
    }

    public void setSeguindo(ArrayList<Integer> seguindo) {
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
            System.out.println("Senha deve ter 4 caracteres");    
            return false;      
        }
         if(senha_ver.matches(".*[0-9].*" ) && senha_ver.matches(".*[a-z].*") && senha_ver.matches(".*[^a-zA-Z0-9 ].*")){
             System.out.println("senha valida");
             return true;
        }
      System.out.println("senha invalida deve ter numeros letras e caractere  especiais");
    return false;
  }
}
