/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rede.social;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author guga
 */
public class Utilizador { 
    private String senha, username, email, dataNasc;
    private int id;
    private ArrayList<Integer> seguidores, seguindo;
    private LocalDate dataCriac;

    
    
      public Utilizador(String senha, String username, String email, int id, String dataNasc) {
        this.senha = senha;
        this.username = username;
        this.email = email;
        this.id = id;
        this.seguidores = new ArrayList<>();
        this.seguindo = new ArrayList<>();
        this.dataNasc = dataNasc;
        this.dataCriac = LocalDate.now();
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
   if(verificar_senha(senha))
       this.senha=senha;
   
   }
    
  public void setDataCriac(LocalDate dataCriac) {
        this.dataCriac = dataCriac;
    }
    public void setUsername(String username) {
        
        this.username = username;
    }

    public void setEmail(String email) {
        if(verificar_email(emil))
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
    
    public static verificar_email(String emailver){
        if(emailver.matches(".+@.+\\.com"){
      System.out.println("email valido");
            return true;
        }
 System.out.println("email invalidp");
        return false;
    }
   
 
  public static boolean verificar_senha(String senha_ver){
        if(senha_ver.length()!=12){
            System.out.println("Senha deve ter 12 caracteres");    
            return false;      
        }
         if(senha_ver.matches(".*[0-9].*" ) && senha_ver.matches(".*[a-z].*") && senha_ver.matches(".*[*#&%].*")){
             System.out.println("username valido");
             return true;
        }
      System.out.println("usuaro invalido deve ter numeros letras e caractere  especiais");
    return false;
  }


    

   
    
    
    }




    
}
