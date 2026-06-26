
package rede.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorMensagens {
    private static final String FICHEIRO = "mensagens.txt";
    
    public static void guardarMensagem(Mensagem m) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO, true))){
            bw.write(m.getRemetente() + ";" + m.getDestinatario() + ";" + 
                      m.getConteudo() + ";" + m.getDataEnvio());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao guardar mensagem: " + e.getMessage());
        }
    }
    
    public static ArrayList<Mensagem> carregarMensagens() {
        ArrayList<Mensagem> mensagens = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))){
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int remetente = Integer.parseInt(partes[0]);
                int destinatario = Integer.parseInt(partes[1]);
                String conteudo = partes[2];
                LocalDateTime data = LocalDateTime.parse(partes[3]);
                
                //adcionar as listas
                mensagens.add(new Mensagem(conteudo, remetente, destinatario, data));
            }
        } catch (FileNotFoundException e){
            //ficheiro ainda não existe
        } catch (IOException e) {
            System.out.println("Erro ao carregar mensagem: " + e.getMessage());
        }
    return mensagens;
    }   
    
    // Retorna mensagem entre dois utilizadores
    public static ArrayList<Mensagem> verConversa(int idA, int idB){
        ArrayList<Mensagem> conversa = new ArrayList<>();
        for( Mensagem m : carregarMensagens()){
            boolean AtoB = m.getRemetente() == idA && m.getDestinatario() == idB;
            boolean BtoA = m.getRemetente() == idB && m.getDestinatario() == idA;
            
            if(AtoB || BtoA) conversa.add(m);
        }
        return conversa;
    }
    
    // Retorna conversas únicas onde o utilizador é o destinatario
    public static ArrayList<Integer> caixaDeEntrada(int idUtilizador) {
        ArrayList<Integer> remetentes = new ArrayList<>();
        for(Mensagem m : carregarMensagens()) {
            if (m.getDestinatario() == idUtilizador && !remetentes.contains(m.getRemetente())){
                remetentes.add(m.getRemetente());
            }
        }
        return remetentes;
    }
    
    
}

