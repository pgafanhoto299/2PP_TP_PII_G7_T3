package rede.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorComentario {

    private static final String FICHEIRO = "comentarios.txt";

    // formato de cada linha:
    

    // Guarda um novo comentário no ficheiro 
    public static void guardarComentario(Comentario c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO, true))) {
            bw.write(
                c.getConteudo()      + ";" +
                c.getAutorUsername() + ";" +
                c.getDataEnvio()     + ";" +
                c.getPubRemetente()  + ";" +
                c.getPubData()
            );
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao guardar comentário.");
        }
    }

    // Carrega todos os comentários do ficheiro 
    public static ArrayList<Comentario> carregarComentarios() {
        ArrayList<Comentario> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                // dados[0]=conteudo  dados[1]=autor  dados[2]=dataEnvio
                // dados[3]=pubRem    dados[4]=pubData
                Comentario c = new Comentario(
                    dados[1],
                    dados[0],
                    LocalDateTime.parse(dados[2]),
                    dados[3],
                    LocalDateTime.parse(dados[4])
                );
                lista.add(c);
            }
        } catch (IOException e) {
            System.out.println("Nenhum comentário encontrado.");
        }
        return lista;
    }

    /** Retorna todos os comentários de uma publicação específica */
    public static ArrayList<Comentario> procurarComentarios(String pubRemetente,
                                                             LocalDateTime pubData) {
        ArrayList<Comentario> todos    = carregarComentarios();
        ArrayList<Comentario> resultado = new ArrayList<>();
        for (Comentario c : todos) {
            if (c.getPubRemetente().equals(pubRemetente) &&
                c.getPubData().equals(pubData)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /** Retorna apenas os comentários feitos pelo utilizador actual */
    public static ArrayList<Comentario> procurarMeusComentarios(String autor) {
        ArrayList<Comentario> todos    = carregarComentarios();
        ArrayList<Comentario> resultado = new ArrayList<>();
        for (Comentario c : todos) {
            if (c.getAutorUsername().equalsIgnoreCase(autor)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /** Remove o comentário na posição indicada (entre os do próprio utilizador) */
    public static ArrayList<Comentario> deletarComentario(String autor, int pos) {
        ArrayList<Comentario> todos  = carregarComentarios();
        ArrayList<Comentario> meus   = procurarMeusComentarios(autor);
        // mesmo padrão do deletarPubli: trabalha na sub-lista e re-escreve tudo
        if (pos < 1 || pos > meus.size()) {
            System.out.println("Comentário não encontrado.");
            return todos;
        }
        Comentario aRemover = meus.get(pos - 1);
        todos.remove(aRemover);  // remove o objecto exacto da lista completa
        return todos;
    }

    /** Reescreve o ficheiro com a lista actualizada (mesmo padrão do reescreverPublic) */
    public static void reescreverComentarios(ArrayList<Comentario> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO))) {
            for (Comentario c : lista) {
                bw.write(
                    c.getConteudo()      + ";" +
                    c.getAutorUsername() + ";" +
                    c.getDataEnvio()     + ";" +
                    c.getPubRemetente()  + ";" +
                    c.getPubData()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao reescrever comentários.");
        }
    }
}
