package rede.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorPublicacao {
    
    public static void  guardar_publicacao(Publicacao P){
    
        try( BufferedWriter pw =
          new BufferedWriter (
          new FileWriter("publicacao.txt",true)
          )
                
         ){
             pw.write(P.getConteudo() + ";" + P.getRemetente() + ";" + P.getDataEnvio() + ";" );
              pw.newLine();
           
              System.out.println("Publicacao adicionada com sucesso");
          
          }catch(IOException e){
                System.out.println("Erro ao guardar publicacao");
          }
                    
    }
            
    public static  ArrayList <Publicacao> Carregar_publicacao(){
                       
          ArrayList<Publicacao> lista = new ArrayList<>();
         try(
            BufferedReader pr =
            new BufferedReader(
            new FileReader("Publicacao.txt")
          )
      
        ){
    
                 String linha;
                 while((linha=pr.readLine()) != null){
                     if(linha.trim().isEmpty()){
                         continue;
                     }
                    String [] data  = linha.split(";");
                    LocalDateTime dataEnvio = LocalDateTime.parse(data[2]);
                    
                    Publicacao pub = new Publicacao(data[0], data[1] ,dataEnvio);
                    lista.add(pub);
              
                 } 
    
          }catch(IOException e){
              System.out.println("Nenhuma publição encontrada.");
          }  

         return lista;
    } 
    
        public static void reescreverPublic(ArrayList<Publicacao> P){
        
             try(BufferedWriter pw =new BufferedWriter(new FileWriter("Publicacao.txt"))){
            
                 for(Publicacao i:P){
                    
                    pw.write( i.getConteudo() + ";" + i.getRemetente() + ";" + i.getDataEnvio() + ";" );
                    pw.newLine();
                   
                }
        
        
            }catch(IOException e){
              System.out.println("Erro ao reescrever ficheiro.");
                   
            }
    
        }
                    // funcao procurar as publicaoes de determinado usuario 
            public static  ArrayList<Publicacao> procurarPubliC (String remetente){ 
            
                 ArrayList<Publicacao> todaspublicacoess = Carregar_publicacao();
                 ArrayList<Publicacao> publifound = new ArrayList<>(); // Array para guardar as publicacoes encontradas desse remetente e retornar que o usuario procura
                 
                  if(Menu.utiActual.getUsername().equalsIgnoreCase(remetente)){
                          
                          System.out.println("O usuário digitado corresponde ao seu  deseja ver suas publicações ");
                          System.out.println("1.Sim");
                          System.out.println("2.Não");     
                         int op=Menu.input.nextInt();
                        if(op==1)
                            procurarMinhasPubliC(remetente);                  // ir para minhas publicacoes
                           else if(op==2){
                                System.out.println("Voltando...");
                                Menu.publicacao();
                            } else{
                                System.out.println("Opcao inválida");
                                  Menu.publicacao();
                             }
                }
                      for( Publicacao i : todaspublicacoess){

                           if(i.getRemetente().equals(remetente)){     
                                publifound.add(i);
                              }
                            
                        }
                 return publifound; 
               }   
                      
                            // funcao exclusiva para mostrar as publicaoes do user atual
            
            public static  ArrayList<Publicacao> procurarMinhasPubliC (String remetente){ 
            
                 ArrayList<Publicacao> todaspublicacoess = Carregar_publicacao();
                 ArrayList<Publicacao> publicMinhas = new ArrayList<>(); // Array para guardar as publicacoes encontradas dele mesmo, miinhas publicaoes
                 
                for( Publicacao i : todaspublicacoess){
                          if(i.getRemetente().equalsIgnoreCase(remetente))
                          publicMinhas.add(i);
                }
             
            
                  return publicMinhas;// retorna as publicaoes do proprio usuario
              
          }
         
            public static ArrayList<Publicacao> deletarPubli(int i){ // deletar publicacao com base na sua posicao no arrayList
                ArrayList<Publicacao> todasPublicacoes=Carregar_publicacao();
                ArrayList<Publicacao> todasMinhasPublicNovas = procurarMinhasPubliC(Menu.utiActual.getUsername()); 
               if(i>=todasMinhasPublicNovas.size()+1){
                   System.out.println("Publicacao não encontrada");
                   return todasPublicacoes;
               }
                todasMinhasPublicNovas.remove(i-1);
                System.out.println("Publicação eliminada com sucesso!");
                return todasMinhasPublicNovas;
                   
           
                }
 
        }
     




   




