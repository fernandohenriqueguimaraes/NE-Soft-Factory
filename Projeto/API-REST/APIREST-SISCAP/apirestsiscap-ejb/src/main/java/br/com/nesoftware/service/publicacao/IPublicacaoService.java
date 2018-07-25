package br.com.nesoftware.service.publicacao;

import java.util.List;

import br.com.nesoftware.modelo.Publicacao;
import br.com.nesoftware.negocio.validador.exception.NegocioException;



/**
 * @author helton.souto
 *
 */
public interface IPublicacaoService {

	
	public void inserir(Publicacao publicacao) throws NegocioException;
	
	
	public Publicacao obter(Long id) throws NegocioException;


	public List<Publicacao> obtemPublicacoes()  throws NegocioException;
	
	

	
}