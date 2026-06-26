
package rede.social;

import java.time.LocalDate;
import java.util.ArrayList;


public class REDESOCIAL {

    
    
    public static void main(String[] args) {
        int opcao;
        
       // Carregar utilizadores do ficheiro ao iniciar o programa
        // Carregar relações de seguidores
        GestorSeguidores.carregarFollows(Menu.utilizadores);
       
      GestorUtilizadores.carregarUtilizadores();
      Menu.mostrarMenuPrincipal();
        
   
    
    
        
                   
        
        }
    
    }

