package br.com.nesoftware.integracao.dao.publicacao;

import java.util.List;

import javax.ejb.Local;

import br.com.nesoftware.modelo.Publicacao;


@Local
public interface IPublicacaoDao {

	
	public void inserir(Publicacao publicacao);
	
	
	public Publicacao obter(Long id);


	public List<Publicacao> obtemPublicacoes();

}