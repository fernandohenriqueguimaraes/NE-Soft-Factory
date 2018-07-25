package br.com.nesoftware.rest.exception.representation;

public class DetalheFalhaNegocioRepresentation {
	
	private String atributo;	
	private String mensagem;
	private String chave;

	public DetalheFalhaNegocioRepresentation() {
	    super();
	}
	
	
	public DetalheFalhaNegocioRepresentation(String atributo, String mensagem) {
		this.atributo = atributo;
		this.mensagem = mensagem;
	}


	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }


    public String getChave() {
        return chave;
    }


    public void setChave(String chave) {
        this.chave = chave;
    }
	
}
