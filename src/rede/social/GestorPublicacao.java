package rede.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorPublicacao {

    private static final String FICHEIRO = "publicacoes.txt";

    public static void guardar_publicacao(Publicacao p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO, true))) {
            bw.write(p.getRemetente() + ";" + p.getConteudo() + ";" + p.getDataEnvio());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao guardar publicação: " + e.getMessage());
        }
    }

    public static ArrayList<Publicacao> Carregar_publicacao() {
        ArrayList<Publicacao> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length < 3) continue; // linha malformada — ignora

                String        remetente = dados[0];
                String        conteudo  = dados[1];
                LocalDateTime dataEnvio = LocalDateTime.parse(dados[2]);

                lista.add(new Publicacao(remetente, conteudo, dataEnvio));
            }
        } catch (IOException e) {
            // ficheiro ainda não existe — situação normal na primeira execução
        }

        return lista;
    }

    public static void reescreverPublic(ArrayList<Publicacao> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO))) {
            for (Publicacao p : lista) {
                bw.write(p.getRemetente() + ";" + p.getConteudo() + ";" + p.getDataEnvio());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao reescrever publicações: " + e.getMessage());
        }
    }

    public static ArrayList<Publicacao> procurarPubliC(String remetente) {
        ArrayList<Publicacao> todas   = Carregar_publicacao();
        ArrayList<Publicacao> encontradas = new ArrayList<>();

        for (Publicacao p : todas) {
            if (p.getRemetente().equalsIgnoreCase(remetente)) {
                encontradas.add(p);
            }
        }
        return encontradas;
    }

    public static ArrayList<Publicacao> procurarMinhasPubliC(String remetente) {
        ArrayList<Publicacao> todas = Carregar_publicacao();
        ArrayList<Publicacao> minhas = new ArrayList<>();

        for (Publicacao p : todas) {
            if (p.getRemetente().equalsIgnoreCase(remetente)) {
                minhas.add(p);
            }
        }
        return minhas;
    }

    public static ArrayList<Publicacao> deletarPubli(int pos) {
        ArrayList<Publicacao> todas  = Carregar_publicacao();
        ArrayList<Publicacao> minhas = procurarMinhasPubliC(Menu.utiActual.getUsername());

        if (pos < 1 || pos > minhas.size()) {
            System.out.println("Publicação não encontrada.");
            return todas; // devolve lista global intacta
        }

        // Identifica o objeto exato a remover na lista global
        Publicacao aRemover = minhas.get(pos - 1);
        todas.remove(aRemover);
        return todas;
    }
}
