
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rede.social;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
//String caminho = "publicacao.txt";
/**
 *
 * @author guga
 */
public class Publicacao {
    private String remetenteID;
    private String conteudo;
    private LocalDateTime dataEnvio;

    
   public Publicacao(String conteudo,String remetenteID,LocalDateTime dataEnvio){
       this.conteudo=conteudo;
       this.remetenteID=remetenteID;
       this.dataEnvio=dataEnvio;
   }
   
    public String getRemetente() {
        return remetenteID;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setRemetente(String remetenteID) {
        this.remetenteID = remetenteID;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
    
       public static void exibirPulicacoes (){         
            
             ArrayList<Publicacao> publicacoes = new ArrayList<>();
        
             System.out.println("Digite o nome do usuario do dono da publicação:");
             String nomeRemetente=Menu.input.nextLine(); // remetente a ser procurado
             publicacoes = GestorPublicacao.procurarPubliC(nomeRemetente); //chama o procurar publicao que retorna uma lista de publicacoes desse remetente
              
                 if(publicacoes.isEmpty()){
                     System.out.println("Publicações do(a) utilizador(a) " +nomeRemetente+ " não enconttrada"); 
                     System.out.println("Tente denovo");
                    
                    Menu.publicacao();
             
                 }else{
                     System.out.println("\n============================================");
                     System.out.println("Todas publicacoes do(a) " +nomeRemetente+ ":");
                     int i=1;
                     for(Publicacao j:publicacoes){
                     System.out.println(i+"."+j.getConteudo()+". publicado em: "+j.getDataEnvio()); // exibe todas publicacoes do remetente desejado
                     i++;
                     }

                  
                 }
        
                 Menu.publicacao(); // chamar reagir nao o publicacao talvez
       }
        
      public static void adicionarPubli(String nome){
           
           System.out.println("Adicionar publicacao");
           System.out.println("Insira o texto a Publicar");
           
           String content =Menu.input.nextLine();
           LocalDateTime dataEnvio=LocalDateTime.now();
           
          
           Publicacao novaPublicacao= new Publicacao(content,nome,dataEnvio);
           GestorPublicacao.guardar_publicacao(novaPublicacao);
           Menu.publicacao();
           
     
      }
            public static void exibirMinhasPublicaoes(){
                      ArrayList<Publicacao> Minhaspublicacoes = new ArrayList<>();
               Minhaspublicacoes = GestorPublicacao. procurarMinhasPubliC(Menu.utiActual.getUsername());  //procurar publicaoes do utilizador logado no momento
              
                 if(Minhaspublicacoes.isEmpty()){
                     System.out.println("Não tem nehuma publicacao "+Menu.utiActual.getUsername()); 
                       Menu.publicacao();  
             
                 }else{
                         System.out.println("\n==========================================");
                        System.out.println("Todas suas publicacoes " +Menu.utiActual.getUsername()+ ":");
                         int i=1;
                         for(Publicacao j:Minhaspublicacoes){
                          System.out.println(i+"."+j.getConteudo()); // exibe todas publicacoes do proprio usuario
                          i++;
                        }
                           
                      
              
               }
                     //   Menu.publicacao;
         }
          
            
            public static void eliminarPubli(){ // funcao eliminar chama o metodo  deletar no gestor para apgar uma publicacao com base na posicao do array
                
                exibirMinhasPublicaoes();
                System.out.println("Que publicacao quer eliminar?");
                int i=Menu.input.nextInt();
                ArrayList<Publicacao> NovasPublicacoes =GestorPublicacao.deletarPubli(i);
                
                GestorPublicacao.reescreverPublic(NovasPublicacoes);
                
                
                 exibirMinhasPublicaoes();
               
                
            }
         
    }





  
    
    

