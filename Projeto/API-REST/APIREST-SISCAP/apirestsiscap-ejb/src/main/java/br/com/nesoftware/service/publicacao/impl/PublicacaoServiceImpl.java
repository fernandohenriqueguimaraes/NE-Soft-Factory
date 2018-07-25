package br.com.nesoftware.service.publicacao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.nesoftware.integracao.dao.publicacao.IPublicacaoDao;
import br.com.nesoftware.modelo.Publicacao;
import br.com.nesoftware.negocio.validador.exception.ChaveMensagem;
import br.com.nesoftware.negocio.validador.exception.NegocioException;
import br.com.nesoftware.service.publicacao.IPublicacaoService;


/**
 * @author helton.souto
 *
 */
@Local
@Stateless(name="PublicacaoService")
public class PublicacaoServiceImpl implements IPublicacaoService {
    
    private static final ChaveMensagem ERRO_CADASTRO_PUBLICACAO = new ChaveMensagem("PublicacaoService.cadastro.erro");
	
    @Inject
	private IPublicacaoDao publicacaoDao;
    
    
	@Override
    public void inserir(Publicacao publicacao) throws NegocioException {
        
        if (publicacao != null){
        	
        	LocalDateTime dataHoje = LocalDateTime.now();
        	publicacao.setDataCadastro(dataHoje.toLocalDate());
        	publicacao.setDataPublicacao(dataHoje.toLocalDate());
        	
        	//TODO chama negócio para validar regras. Algo tipo
        	//publicacaoNegocio.validarRegras(publicacao)
        	//publicacao.setId(null);
        	publicacaoDao.inserir(publicacao);
        }
        else {
			throw new NegocioException(ERRO_CADASTRO_PUBLICACAO);
		}
	}
	
	
	
	@Override
	public Publicacao obter(Long id)  throws NegocioException {
		if (id != null){
			return publicacaoDao.obter(id);
		}
		return null;
	}



	@Override
	public List<Publicacao> obtemPublicacoes() throws NegocioException {
		return publicacaoDao.obtemPublicacoes();
	}
	
	
}