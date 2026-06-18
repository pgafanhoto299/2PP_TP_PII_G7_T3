/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rede.social;

import java.util.ArrayList;

/**
 *
 * @author guga
 */
public class REDESOCIAL {

    
    
    public static void main(String[] args) {
        int opcao;
        
        // Carregar utilizadores do ficheiro ao iniciar o programa
        Menu.utilizadores = GestorUtilizadores.carregarUtilizadores();

        
        /* Testar o procurar por email*/
        
        /*    Utilizador u =
            GestorUtilizadores.procurarPorEmail("anildo@gmail.com");

                if(u != null){
                    System.out.println("Utilizador encontrado");
                    System.out.println(u.getUsername());
                }
                else{
                    System.out.println("Utilizador não encontrado");
                }
        */
        /*Testar o carregar utilizadores
                ArrayList<Utilizador> lista =
                GestorUtilizadores.carregarUtilizadores();

                for(Utilizador u : lista){

                System.out.println("ID: " + u.getId());
                System.out.println("Username: " + u.getUsername());
                System.out.println("Email: " + u.getEmail());
                System.out.println("Senha: " + u.getSenha());
                System.out.println("Nascimento: " + u.getDataNasc());
                System.out.println("Criacao: " + u.getDataCriac());
                System.out.println("----------------");
            }*/
        do {
            Menu.mostrarMenuPrincipal();
            opcao = Menu.lerOpcao();
            Menu.executarOpcaoMenuPrincipal(opcao);

        } while (opcao != 4 && opcao != 3);
    }
}
