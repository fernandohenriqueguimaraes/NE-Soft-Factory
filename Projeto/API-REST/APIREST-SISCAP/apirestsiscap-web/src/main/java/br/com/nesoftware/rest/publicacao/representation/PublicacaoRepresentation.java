package br.com.nesoftware.rest.publicacao.representation;

import java.time.LocalDate;

public class PublicacaoRepresentation {

	private Long id;
	private String fonte;
	private LocalDate dataCadastro;
	private LocalDate dataPublicacao;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((fonte == null) ? 0 : fonte.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result
				+ ((dataPublicacao == null) ? 0 : dataPublicacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublicacaoRepresentation other = (PublicacaoRepresentation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!fonte.equals(other.fonte))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataPublicacao.equals(other.dataPublicacao))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	
	
	
}