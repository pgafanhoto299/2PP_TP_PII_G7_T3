package rede.social;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comentario {


    private String        autorUsername;
    private String        conteudo;
    private LocalDateTime dataEnvio;
    private String        pubRemetente;
    private LocalDateTime pubData;

    // --- Construtor ---
    public Comentario(String autorUsername, String conteudo, LocalDateTime dataEnvio,
                      String pubRemetente, LocalDateTime pubData) {
        this.autorUsername = autorUsername;
        this.conteudo      = conteudo;
        this.dataEnvio     = dataEnvio;
        this.pubRemetente  = pubRemetente;
        this.pubData       = pubData;
    }

    // --- Getters ---
    public String        getAutorUsername() { return autorUsername; }
    public String        getConteudo()      { return conteudo;      }
    public LocalDateTime getDataEnvio()     { return dataEnvio;     }
    public String        getPubRemetente()  { return pubRemetente;  }
    public LocalDateTime getPubData()       { return pubData;       }

    // --- Setters ---
    public void setAutorUsername(String autorUsername)  { this.autorUsername = autorUsername; }
    public void setConteudo(String conteudo)            { this.conteudo      = conteudo;      }
    public void setDataEnvio(LocalDateTime dataEnvio)   { this.dataEnvio     = dataEnvio;     }
    public void setPubRemetente(String pubRemetente)    { this.pubRemetente  = pubRemetente;  }
    public void setPubData(LocalDateTime pubData)       { this.pubData       = pubData;       }

    
    public static void adicionarComentario(Publicacao pub) {
        System.out.print("Escreva o seu comentário: ");
        String conteudo = Menu.input.nextLine();
        LocalDateTime agora = LocalDateTime.now();

        Comentario novo = new Comentario(
            Menu.utiActual.getUsername(),
            conteudo,
            agora,
            pub.getRemetente(),
            pub.getDataEnvio()
        );

        GestorComentario.guardarComentario(novo);
        System.out.println("Comentário adicionado com sucesso!");
    }

    public static void exibirComentarios(Publicacao pub) {
        ArrayList<Comentario> comentarios =
            GestorComentario.procurarComentarios(pub.getRemetente(), pub.getDataEnvio());

        if (comentarios.isEmpty()) {
            System.out.println("Esta publicação ainda não tem comentários.");
            return;
        }

        System.out.println("\n============================================");
        System.out.println("Comentários da publicação de " + pub.getRemetente() + ":");
        int i = 1;
        for (Comentario c : comentarios) {
            System.out.println(i + ". [" + c.getAutorUsername() + "]: "
                + c.getConteudo() + "  (" + c.getDataEnvio().toLocalDate() + ")");
            i++;
        }
    }

     //BUG CORRIGIDO: input.nextInt() sem nextLine() deixava o '\n' no buffer;
    public static void eliminarComentario() {
        ArrayList<Comentario> meus =
            GestorComentario.procurarMeusComentarios(Menu.utiActual.getUsername());

        if (meus.isEmpty()) {
            System.out.println("Não tem nenhum comentário para eliminar.");
            return;
        }

        System.out.println("\n============================================");
        System.out.println("Os seus comentários:");
        int i = 1;
        for (Comentario c : meus) {
            System.out.println(i + ". " + c.getConteudo()
                + "  [em publicação de " + c.getPubRemetente() + "]");
            i++;
        }

        System.out.print("Qual comentário quer eliminar? ");
        int pos = Menu.lerOpcaoSegura();

        ArrayList<Comentario> atualizados =
            GestorComentario.deletarComentario(Menu.utiActual.getUsername(), pos);
        GestorComentario.reescreverComentarios(atualizados);
        System.out.println("Comentário eliminado com sucesso!");
    }
}
