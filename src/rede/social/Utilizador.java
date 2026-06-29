package rede.social;

import java.time.LocalDate;
import java.util.ArrayList;

public class Utilizador {


    private String senha;
    private String username;
    private String email;
    private String dataNasc;
    private int id;
    private ArrayList<Utilizador> seguidores; // quem me segue
    private ArrayList<Utilizador> seguindo;   // quem eu sigo
    private LocalDate dataCriac;

    public Utilizador(String senha, String username, String email,
                      int id, String dataNasc, LocalDate dataCriac) {
        this.senha      = senha;
        this.username   = username;
        this.email      = email;
        this.id         = id;
        this.dataNasc   = dataNasc;
        this.dataCriac  = dataCriac;
        this.seguidores = new ArrayList<>();
        this.seguindo   = new ArrayList<>();
    }

    public String    getSenha()                     { return senha;      }
    public String    getUsername()                  { return username;   }
    public String    getEmail()                     { return email;      }
    public String    getDataNasc()                  { return dataNasc;   }
    public int       getId()                        { return id;         }
    public LocalDate getDataCriac()                 { return dataCriac;  }
    public ArrayList<Utilizador> getSeguidores()    { return seguidores; }
    public ArrayList<Utilizador> getSeguindo()      { return seguindo;   }

    public void setSenha(String senha)                          { this.senha      = senha;      }
    public void setUsername(String username)                    { this.username   = username;   }
    public void setDataNasc(String dataNasc)                    { this.dataNasc   = dataNasc;   }
    public void setId(int id)                                   { this.id         = id;         }
    public void setDataCriac(LocalDate dataCriac)               { this.dataCriac  = dataCriac;  }
    public void setSeguidores(ArrayList<Utilizador> seguidores) { this.seguidores = seguidores; }
    public void setSeguindo(ArrayList<Utilizador> seguindo)     { this.seguindo   = seguindo;   }

    /** Setter de email com validação embutida */
    public void setEmail(String email) {
        if (verificar_email(email)) {
            this.email = email;
        }
    }

    public static boolean verificar_email(String emailver) {
        return emailver != null && emailver.matches(".+@.+\\.com");
    }

    public static boolean verificar_senha(String senha_ver) {
        if (senha_ver == null || senha_ver.length() <= 4) {
            System.out.println("  A senha deve ter pelo menos 5 caracteres.");
            return false;
        }
        boolean temNumero   = senha_ver.matches(".*[0-9].*");
        boolean temLetra    = senha_ver.matches(".*[a-z].*");
        boolean temEspecial = senha_ver.matches(".*[^a-zA-Z0-9 ].*");

        if (temNumero && temLetra && temEspecial) {
            return true;
        }
        System.out.println("  A senha deve conter letras, números e pelo menos um caracter especial.");
        return false;
    }

    public boolean follow(Utilizador utilizador) {
        // Impedir autpo seguir se
        if (utilizador.getId() == this.id) {
            System.out.println("Não pode seguir a si mesmo.");
            return false;
        }
        // Impede seguir a mesma pessoa mais de uma vez
        if (seguindo.contains(utilizador)) {
            System.out.println("Já segue este utilizador.");
            return false;
        }

        seguindo.add(utilizador);                   // adiciona à minha lista de seguindo
        utilizador.getSeguidores().add(this);       // adiciona-me aos seguidores dele
        GestorSeguidores.guardarFollow(this.id, utilizador.getId()); // persiste

        System.out.println("Agora segue " + utilizador.getUsername() + "!");
        return true;
    }

    /**
     * Deixa de seguir outro utilizador.
     * Verifica se realmente está a seguir antes de remover.
     */
    public boolean unfollow(Utilizador utilizador) {
        if (!seguindo.contains(utilizador)) {
            System.out.println("Não segue este utilizador.");
            return false;
        }

        seguindo.remove(utilizador);                        // remove da minha lista
        utilizador.getSeguidores().remove(this);            // remove-me dos seguidores dele
        GestorSeguidores.removerFollow(this.id, utilizador.getId()); // persiste

        System.out.println("Deixou de seguir " + utilizador.getUsername() + ".");
        return true;
    }

    // ----------------------------------------------------------------
    // Métodos de visualização
    // ----------------------------------------------------------------

    /** Mostra a lista de seguidores do utilizador atual */
    public void showfollow() {
        if (seguidores.isEmpty()) {
            System.out.println("Não tem nenhum seguidor.");
            return;
        }
        System.out.println("\n--- Seguidores ---");
        for (Utilizador u : seguidores) {
            System.out.println("  " + u.getUsername());
        }
    }

    /** Mostra a lista de utilizadores que o utilizador atual segue */
    public void showfollowing() {
        if (seguindo.isEmpty()) {
            System.out.println("Não segue ninguém.");
            return;
        }
        System.out.println("\n--- A seguir ---");
        for (Utilizador u : seguindo) {
            // BUG CORRIGIDO: removida a string "vvvVVVV" que aparecia por engano
            System.out.println("  " + u.getUsername());
        }
    }
}
