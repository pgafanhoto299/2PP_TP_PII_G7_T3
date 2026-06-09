/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rede.social;

import java.time.LocalDateTime;

/**
 *
 * @author guga
 */
public class Mensagem {
    private String conteudo;
    private int remetenteID, destinatarioID;
    private LocalDateTime dataEnvio;
    
    
    public void Mensagem (  String conteudo,int remetenteID, int destinatarioID, LocalDateTime dataEnvio){
        this.conteudo=conteudo;
        this. destinatarioID= destinatarioID;
        this.dataEnvio=dataEnvio;
        this.remetenteID=remetenteID;

    }

    public String getConteudo() {
        return conteudo;
    }

    public int getRemetente() {
        return remetenteID;
    }

    public int getDestinatario() {
        return destinatarioID;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setRemetente(int remetenteID) {
        this.remetenteID = remetenteID;
    }

    public void setDestinatario(int destinatarioID) {
        this.destinatarioID = destinatarioID;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
