/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rede.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author guga
 */
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
    
    public static Utilizador procurarPorId(int id){

    ArrayList<Utilizador> lista = carregarUtilizadores();

    for(Utilizador u : lista){

        if(u.getId() == id){
            return u;
        }

    }

    return null;
}
    
    
}

