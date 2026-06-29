package rede.social;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reacao {
    private String[]            reacoes  = {"Trivial", "Dibinguile About you", "Vai dar tudo certo"};
    private LocalDateTime       dataReac;
    private ArrayList<Integer>  reagidores; // IDs dos utilizadores que reagiram

    public Reacao() {
        this.reagidores = new ArrayList<>();
    }

    public String[]           getReacoes()    { return reacoes;    }
    public LocalDateTime      getDataReac()   { return dataReac;   }
    public ArrayList<Integer> getReagidores() { return reagidores; }

    public void setReacoes(String[] reacoes)            { this.reacoes    = reacoes;    }
    public void setDataReac(LocalDateTime dataReac)     { this.dataReac   = dataReac;   }
    public void setReagidores(ArrayList<Integer> reagidores) { this.reagidores = reagidores; }
}
