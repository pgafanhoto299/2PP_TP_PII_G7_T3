
package rede.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class GestorUtilizadores {
    public  static void guardarUtilizador(Utilizador u){

    try(
        BufferedWriter bw =
        new BufferedWriter(
            new FileWriter("utilizadores.txt", true)
        )
    ){

        bw.write(
            u.getId() + ";" +
            u.getUsername() + ";" +
            u.getEmail() + ";" +
            u.getSenha() + ";" +
            u.getDataNasc() + ";" +
            u.getDataCriac()
        );

        bw.newLine();

    }catch(IOException e){
        System.out.println("Erro ao guardar utilizador.");
    }
}

    
    public static ArrayList<Utilizador> carregarUtilizadores(){

    ArrayList<Utilizador> lista = new ArrayList<>();

    try(
        BufferedReader br =
        new BufferedReader(
            new FileReader("utilizadores.txt")
        )
    ){

        String linha;

        while((linha = br.readLine()) != null){

              /*if(linha.trim().isEmpty()){
                  continue;
              }*/

            
            String[] dados = linha.split(";");

            Utilizador u = new Utilizador(
                dados[3],                    // senha
                dados[1],                    // username
                dados[2],                    // email
                Integer.parseInt(dados[0]), // id
                dados[4],                    // data nasc
                LocalDate.parse(dados[5])   // data criação
            );

            lista.add(u);
        }

    }catch(IOException e){
        System.out.println("Nenhum utilizador encontrado.");
   
    }

    return lista;
}
    
    public static void reescreverFicheiro(ArrayList<Utilizador> lista){

    try(
        BufferedWriter bw =
        new BufferedWriter(
            new FileWriter("utilizadores.txt")
        )
    ){

        for(Utilizador u : lista){

            bw.write(
                u.getId() + ";" +
                u.getUsername() + ";" +
                u.getEmail() + ";" +
                u.getSenha() + ";" +
                u.getDataNasc() + ";" +
                u.getDataCriac()
            );

            bw.newLine();
        }

    }catch(IOException e){
        System.out.println("Erro ao reescrever ficheiro.");
    }

}
    
    public static Utilizador procurarPorEmail(String email){

    ArrayList<Utilizador> lista = carregarUtilizadores();

    for(Utilizador u : lista){

        if(u.getEmail().equals(email)){
            
            return u;
        }

    }

    return null;
}
    
         public static Utilizador procurarUser(String nome){
    
            ArrayList<Utilizador> lista = carregarUtilizadores();

                 for(Utilizador u : lista){

                         if(u.getUsername().equals(nome)){
                         return u;
                      
                         }
                 }
                return null;
        }
        
    
}