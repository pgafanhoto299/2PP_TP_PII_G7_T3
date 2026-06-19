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
        Menu.mostrarMenuPrincipal();
    }
}
