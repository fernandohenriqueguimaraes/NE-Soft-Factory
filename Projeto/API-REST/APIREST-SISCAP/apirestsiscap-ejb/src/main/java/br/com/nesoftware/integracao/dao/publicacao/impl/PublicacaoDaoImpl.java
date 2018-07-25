package br.com.nesoftware.integracao.dao.publicacao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nesoftware.integracao.dao.IDao;
import br.com.nesoftware.integracao.dao.impl.GenericDao;
import br.com.nesoftware.integracao.dao.publicacao.IPublicacaoDao;
import br.com.nesoftware.modelo.Publicacao;

@Stateless(name="IPublicacaoDao")
public class PublicacaoDaoImpl implements IPublicacaoDao {
    
	@Inject
	private IDao<Publicacao> genericDao = new GenericDao<Publicacao>();
	
	
	@Override
	public void inserir(Publicacao pojo){
	    genericDao.inserir(pojo);
	}

	
	
	@Override
	public Publicacao obter(Long id) {
		return genericDao.obterPorId(Publicacao.class, id);
	}



	@Override
	public List<Publicacao> obtemPublicacoes() {
		return genericDao.obterTodos(Publicacao.class);
	}

	
	
}