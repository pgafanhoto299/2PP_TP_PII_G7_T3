package rede.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorSeguidores {

    private static final String FICHEIRO = "seguidores.txt";

    public static void guardarFollow(int idSeguidor, int idSeguido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO, true))) {
            bw.write(idSeguidor + ";" + idSeguido);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao guardar follow: " + e.getMessage());
        }
    }

    public static void removerFollow(int idSeguidor, int idSeguido) {
        ArrayList<String> linhasAManter = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length < 2) continue;

                int seg   = Integer.parseInt(dados[0]);
                int segdo = Integer.parseInt(dados[1]);

                // mantém todas as linhas EXCEPTO a que corresponde ao unfollow
                if (!(seg == idSeguidor && segdo == idSeguido)) {
                    linhasAManter.add(linha);
                }
            }
        } catch (FileNotFoundException e) {
            return; // nada a remover se o ficheiro não existe
        } catch (IOException e) {
            System.out.println("Erro ao ler seguidores: " + e.getMessage());
            return;
        }

        // Reescreve o ficheiro sem a relação removida
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO))) {
            for (String l : linhasAManter) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao reescrever seguidores: " + e.getMessage());
        }
    }

    public static void carregarFollows(ArrayList<Utilizador> lista) {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length < 2) continue;

                int idSeguidor = Integer.parseInt(dados[0]);
                int idSeguido  = Integer.parseInt(dados[1]);

                Utilizador seguidor = procurarPorId(lista, idSeguidor);
                Utilizador seguido  = procurarPorId(lista, idSeguido);

                if (seguidor != null && seguido != null) {
                    // Adiciona diretamente para evitar dupla escrita no ficheiro
                    if (!seguidor.getSeguindo().contains(seguido)) {
                        seguidor.getSeguindo().add(seguido);
                    }
                    if (!seguido.getSeguidores().contains(seguidor)) {
                        seguido.getSeguidores().add(seguidor);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // ficheiro ainda não existe — normal na primeira execução
        } catch (IOException e) {
            System.out.println("Erro ao carregar seguidores: " + e.getMessage());
        }
    }
    private static Utilizador procurarPorId(ArrayList<Utilizador> lista, int id) {
        for (Utilizador u : lista) {
            if (u.getId() == id) return u;
        }
        return null;
    }
}
