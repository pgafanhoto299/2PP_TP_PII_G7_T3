package rede.social;

import java.time.LocalDateTime;
import java.util.ArrayList;
public class Publicacao {


    private String        remetenteID; // username do autor
    private String        conteudo;
    private LocalDateTime dataEnvio;

    public Publicacao(String remetenteID, String conteudo, LocalDateTime dataEnvio) {
        this.remetenteID = remetenteID;
        this.conteudo    = conteudo;
        this.dataEnvio   = dataEnvio;
    }

    public String        getRemetente() { return remetenteID; }
    public String        getConteudo()  { return conteudo;    }
    public LocalDateTime getDataEnvio() { return dataEnvio;   }

    public void setRemetente(String remetenteID)    { this.remetenteID = remetenteID; }
    public void setConteudo(String conteudo)        { this.conteudo    = conteudo;    }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio;  }

    public static void exibirPulicacoes() {
        System.out.print("Digite o nome do utilizador: ");
        String nomeRemetente = Menu.input.nextLine().trim();

        // Se o nome coincide com o utilizador logado, redireciona para "minhas publicações"
        if (nomeRemetente.equalsIgnoreCase(Menu.utiActual.getUsername())) {
            System.out.println("Esse é o seu utilizador. A mostrar as suas publicações...");
            exibirMinhasPublicaoes();
            return;
        }

        ArrayList<Publicacao> publicacoes = GestorPublicacao.procurarPubliC(nomeRemetente);

        if (publicacoes.isEmpty()) {
            System.out.println("Nenhuma publicação encontrada para \"" + nomeRemetente + "\".");
            return;
        }

        System.out.println("\n============================================");
        System.out.println("Publicações de " + nomeRemetente + ":");
        int i = 1;
        for (Publicacao p : publicacoes) {
            System.out.println(i + ". " + p.getConteudo()
                + "  [" + p.getDataEnvio().toLocalDate() + "]");
            i++;
        }

        // Opção de ver comentários de uma publicação específica
        System.out.println("\nDeseja ver os comentários de alguma publicação? (0 para não)");
        int escolha = Menu.lerOpcaoSegura();
        if (escolha >= 1 && escolha <= publicacoes.size()) {
            Comentario.exibirComentarios(publicacoes.get(escolha - 1));
        }
    }

    public static void exibirMinhasPublicaoes() {
        ArrayList<Publicacao> minhas =
            GestorPublicacao.procurarMinhasPubliC(Menu.utiActual.getUsername());

        if (minhas.isEmpty()) {
            System.out.println("Não tem nenhuma publicação, " + Menu.utiActual.getUsername() + ".");
            return;
        }

        System.out.println("\n==========================================");
        System.out.println("As suas publicações (" + Menu.utiActual.getUsername() + "):");
        int i = 1;
        for (Publicacao p : minhas) {
            System.out.println(i + ". " + p.getConteudo()
                + "  [" + p.getDataEnvio().toLocalDate() + "]");
            i++;
        }
    }

    public static void adicionarPubli(String nomeAutor) {
        System.out.println("--- Nova publicação ---");
        System.out.print("Escreva o texto: ");
        String conteudo  = Menu.input.nextLine();
        LocalDateTime agora = LocalDateTime.now();

        // Construtor: (remetenteID, conteudo, dataEnvio)
        Publicacao nova = new Publicacao(nomeAutor, conteudo, agora);
        GestorPublicacao.guardar_publicacao(nova);
        System.out.println("Publicação adicionada com sucesso!");
    }

    public static void eliminarPubli() {
        exibirMinhasPublicaoes();

        ArrayList<Publicacao> minhas =
            GestorPublicacao.procurarMinhasPubliC(Menu.utiActual.getUsername());

        if (minhas.isEmpty()) return; // exibirMinhasPublicaoes já mostrou mensagem

        System.out.print("Qual publicação quer eliminar? ");
        int pos = Menu.lerOpcaoSegura();

        ArrayList<Publicacao> atualizadas = GestorPublicacao.deletarPubli(pos);
        GestorPublicacao.reescreverPublic(atualizadas);
        System.out.println("Publicação eliminada com sucesso!");
        exibirMinhasPublicaoes();
    }
}
