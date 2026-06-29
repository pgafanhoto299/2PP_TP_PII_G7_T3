package rede.social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorUtilizadores {

    private static final String FICHEIRO = "utilizadores.txt";

    public static void guardarUtilizador(Utilizador u) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO, true))) {
            bw.write(
                u.getId()        + ";" +
                u.getUsername()  + ";" +
                u.getEmail()     + ";" +
                u.getSenha()     + ";" +
                u.getDataNasc()  + ";" +
                u.getDataCriac()
            );
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao guardar utilizador: " + e.getMessage());
        }
    }

    public static ArrayList<Utilizador> carregarUtilizadores() {
        ArrayList<Utilizador> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FICHEIRO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; // ignora linhas vazias

                String[] dados = linha.split(";");
                if (dados.length < 6) continue; // linha malformada — ignora

                Utilizador u = new Utilizador(
                    dados[3],                    // senha
                    dados[1],                    // username
                    dados[2],                    // email
                    Integer.parseInt(dados[0]), // id
                    dados[4],                    // dataNasc
                    LocalDate.parse(dados[5])   // dataCriac
                );
                lista.add(u);
            }
        } catch (FileNotFoundException e) {
            // ficheiro ainda não existe — normal na primeira execução
        } catch (IOException e) {
            System.out.println("Erro ao carregar utilizadores: " + e.getMessage());
        }

        return lista;
    }

    public static void reescreverFicheiro(ArrayList<Utilizador> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO))) {
            for (Utilizador u : lista) {
                bw.write(
                    u.getId()        + ";" +
                    u.getUsername()  + ";" +
                    u.getEmail()     + ";" +
                    u.getSenha()     + ";" +
                    u.getDataNasc()  + ";" +
                    u.getDataCriac()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao reescrever utilizadores: " + e.getMessage());
        }
    }
    public static Utilizador procurarPorEmail(String email) {
        for (Utilizador u : carregarUtilizadores()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }


    public static Utilizador procurarUser(String username) {
        for (Utilizador u : carregarUtilizadores()) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }
}
