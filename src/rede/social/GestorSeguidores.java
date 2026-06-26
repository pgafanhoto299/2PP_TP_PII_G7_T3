

package rede.social;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorSeguidores {
        
    // Cada linha do ficheiro guarda uma relação idSeguidor e idSeguido
    public static void guardarFollow(int idSeguidor, int idSeguido){
        try(
            BufferedWriter bw = new BufferedWriter(new FileWriter("seguidores.txt", true))
        ){
            bw.write(idSeguidor + ";" + idSeguido);
            bw.newLine();
        }catch(IOException e){
            System.out.println("Erro ao guardar follow.");
        }
    }

    // Remove uma relação de follow específica, reescrevendo o ficheiro sem ela
    public static void removerFollow(int idSeguidor, int idSeguido){
        ArrayList<String> linhas = new ArrayList<>();

        try(
            BufferedReader br = new BufferedReader( new FileReader("seguidores.txt") )
        ){
            String linha;
            while((linha = br.readLine()) != null){

                if(linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if(dados.length < 2) continue;

                int seguidor = Integer.parseInt(dados[0]);
                int seguido  = Integer.parseInt(dados[1]);

                // mantém todas as linhas exceto a que corresponde ao unfollow
                if(!(seguidor == idSeguidor && seguido == idSeguido)){
                    linhas.add(linha);
                }
            }
        }catch(IOException e){
            System.out.println("Nenhuma relação de follow encontrada.");
            return;
        }

        try(
            BufferedWriter bw =
            new BufferedWriter(
                new FileWriter("seguidores.txt")
            )
        ){
            for(String l : linhas){
                bw.write(l);
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println("Erro ao reescrever ficheiro de seguidores.");
        }
    }

    // Lê o ficheiro e reconstrói as listas de seguidores/seguindo
    // de todos os utilizadores já carregados em "lista"
    public static void carregarFollows(ArrayList<Utilizador> lista){
        try(
            BufferedReader br =
            new BufferedReader(
                new FileReader("seguidores.txt")
            )
        ){
            String linha;
            while((linha = br.readLine()) != null){

                if(linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if(dados.length < 2) continue;

                int idSeguidor = Integer.parseInt(dados[0]);
                int idSeguido  = Integer.parseInt(dados[1]);

                Utilizador seguidor = procurarPorId(lista, idSeguidor);
                Utilizador seguido  = procurarPorId(lista, idSeguido);

                if(seguidor != null && seguido != null){
                    // adiciona diretamente às listas, sem passar pelo método follow()
                    // para evitar duplicar escrita no ficheiro durante o carregamento
                    if(!seguidor.getSeguindo().contains(seguido)){
                        seguidor.getSeguindo().add(seguido);
                    }
                    if(!seguido.getSeguidores().contains(seguidor)){
                        seguido.getSeguidores().add(seguidor);
                    }
                }
            }
        } catch(IOException e) {
    System.out.println("Erro: " + e.getMessage()); // mostra o erro real
}
    }

    // auxiliar local para procurar utilizador por id dentro de uma lista já em memória
    private static Utilizador procurarPorId(ArrayList<Utilizador> lista, int id){
        for(Utilizador u : lista){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }
}

    

