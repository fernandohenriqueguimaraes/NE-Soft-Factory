package br.com.nesoftware.negocio.validador.exception;

public class NegocioException extends Exception{

    private static final long serialVersionUID = 1L;
    
    private final ChaveMensagem chaveMensagem;

    protected NegocioException() {
        super();
        chaveMensagem=null;
    }

    public NegocioException(ChaveMensagem chaveMensagem, Throwable cause) {
        super(chaveMensagem.getChave(), cause);
        this.chaveMensagem=chaveMensagem;
    }

    public NegocioException(ChaveMensagem chaveMensagem) {
        super(chaveMensagem.getChave());
        this.chaveMensagem=chaveMensagem;
    }


    public ChaveMensagem getChaveMensagem() {
        return chaveMensagem;
    }
}
