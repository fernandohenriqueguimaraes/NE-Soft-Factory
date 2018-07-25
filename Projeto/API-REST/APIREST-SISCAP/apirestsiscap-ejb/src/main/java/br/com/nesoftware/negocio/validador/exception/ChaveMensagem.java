package br.com.nesoftware.negocio.validador.exception;

import java.io.Serializable;

public class ChaveMensagem implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String chave;
    private Serializable[] args;
    
    
    public ChaveMensagem(String chaveMsg) {
        this.chave=chaveMsg;
    }
    
    public ChaveMensagem(String chaveMsg, Serializable... args) {
        this.chave = chaveMsg;
        this.args = args;
    }


    
    public Serializable[] getArgs() {
        return args;
    }
    
    public String getChave() {
        return chave;
    }

}
