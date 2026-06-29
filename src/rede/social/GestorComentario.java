package rede.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Responsável por toda a persistência de comentários no ficheiro "comentarios.txt".
 * Formato de cada linha: conteudo;autor;dataEnvio;pubRemetente;pubData
 */
public class GestorComentario {

    private static final String FICHEIRO = "comentarios.txt";

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
            System.out.println("Erro ao guardar comentário: " + e.getMessage());
        }
    }

    public static ArrayList<Comentario> carregarComentarios() {
        ArrayList<Comentario> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length < 5) continue; // linha malformada — ignora

                // dados[0]=conteudo  dados[1]=autor   dados[2]=dataEnvio
                // dados[3]=pubRem    dados[4]=pubData
                Comentario c = new Comentario(
                    dados[1],                       // autor
                    dados[0],                       // conteudo
                    LocalDateTime.parse(dados[2]),  // dataEnvio
                    dados[3],                       // pubRemetente
                    LocalDateTime.parse(dados[4])   // pubData
                );
                lista.add(c);
            }
        } catch (FileNotFoundException e) {
            // ficheiro ainda não existe — normal na primeira execução
        } catch (IOException e) {
            System.out.println("Erro ao carregar comentários: " + e.getMessage());
        }

        return lista;
    }

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
            System.out.println("Erro ao reescrever comentários: " + e.getMessage());
        }
    }

 
    // Pesquisa

    /**
     * Devolve todos os comentários de uma publicação específica,
     * identificada pelo par (pubRemetente, pubData).
     */
    public static ArrayList<Comentario> procurarComentarios(String pubRemetente,
                                                             LocalDateTime pubData) {
        ArrayList<Comentario> resultado = new ArrayList<>();
        for (Comentario c : carregarComentarios()) {
            if (c.getPubRemetente().equalsIgnoreCase(pubRemetente)
                    && c.getPubData().equals(pubData)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public static ArrayList<Comentario> procurarMeusComentarios(String autor) {
        ArrayList<Comentario> resultado = new ArrayList<>();
        for (Comentario c : carregarComentarios()) {
            if (c.getAutorUsername().equalsIgnoreCase(autor)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public static ArrayList<Comentario> deletarComentario(String autor, int pos) {
        ArrayList<Comentario> todos = carregarComentarios();
        ArrayList<Comentario> meus  = procurarMeusComentarios(autor);

        if (pos < 1 || pos > meus.size()) {
            System.out.println("Comentário não encontrado.");
            return todos;
        }

        Comentario aRemover = meus.get(pos - 1);
        todos.remove(aRemover); // remove o objeto exato da lista global
        return todos;
    }
}
