package rede.social;

import java.time.LocalDateTime;

public class Mensagem {

    private String        conteudo;
    private int           remetenteID;    // ID de quem envia
    private int           destinatarioID; // ID de quem recebe
    private LocalDateTime dataEnvio;

    public Mensagem(String conteudo, int remetenteID, int destinatarioID, LocalDateTime dataEnvio) {
        this.conteudo       = conteudo;
        this.remetenteID    = remetenteID;
        this.destinatarioID = destinatarioID;
        this.dataEnvio      = dataEnvio;
    }

    public String        getConteudo()      { return conteudo;       }
    public int           getRemetente()     { return remetenteID;    }
    public int           getDestinatario()  { return destinatarioID; }
    public LocalDateTime getDataEnvio()     { return dataEnvio;      }

    public void setConteudo(String conteudo)            { this.conteudo       = conteudo;       }
    public void setRemetente(int remetenteID)           { this.remetenteID    = remetenteID;    }
    public void setDestinatario(int destinatarioID)     { this.destinatarioID = destinatarioID; }
    public void setDataEnvio(LocalDateTime dataEnvio)   { this.dataEnvio      = dataEnvio;      }
}
