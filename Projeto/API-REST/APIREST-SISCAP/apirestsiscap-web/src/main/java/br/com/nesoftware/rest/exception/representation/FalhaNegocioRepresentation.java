package br.com.nesoftware.rest.exception.representation;

import java.util.LinkedList;
import java.util.List;

public class FalhaNegocioRepresentation {
	
	private List<DetalheFalhaNegocioRepresentation> detalhes;

	public FalhaNegocioRepresentation() {
		detalhes = new LinkedList<>();
	}
	
	public List<DetalheFalhaNegocioRepresentation> getDetalhes(){
		return detalhes;
	}
	
	public void adicionarDetalhe(DetalheFalhaNegocioRepresentation detalhe){
		detalhes.add(detalhe);
	}
	
	public void adicionarDetalhe(String nomeAtributo, String mensagem){
		detalhes.add(new DetalheFalhaNegocioRepresentation(nomeAtributo, mensagem));
	}
	

}
