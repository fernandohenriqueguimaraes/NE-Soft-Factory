package br.com.nesoftware.rest.publicacao.parser;

import java.time.LocalDate;

import javax.enterprise.context.Dependent;

import br.com.nesoftware.modelo.Publicacao;
import br.com.nesoftware.rest.parser.AbstractParser;
import br.com.nesoftware.rest.publicacao.representation.PublicacaoRepresentation;

@Dependent
public class PublicacaoParser extends AbstractParser<Publicacao, PublicacaoRepresentation>{
	
	
	@Override
	public Publicacao getBo(PublicacaoRepresentation rep) {
		
		if (rep != null){
			Publicacao bo = new Publicacao();
			parseAndMerge(bo, rep);
            return bo;
		}
		
		return null;
	}
	
	
	/**
	 * Realiza o merge de dados do trabalhador salvo com os dados do representation
	 * @param bo
	 * @param rep
	 */
	public void parseAndMerge(Publicacao bo, PublicacaoRepresentation rep){
	    
        if (rep.getId() != null){
            bo.setId(rep.getId());
        } else {
            bo.setId(null);
        }
        
        if(rep.getDataCadastro()==null){
            bo.setDataCadastro(LocalDate.now());
        }
        
        if(rep.getDataPublicacao()!=null){
            bo.setDataPublicacao(rep.getDataPublicacao());
        }
        
        if(rep.getFonte()!=null){
            bo.setFonte(rep.getFonte());
        }
	}
	

	@Override
	public PublicacaoRepresentation getRepresentation(Publicacao bo) {
		
		if (bo != null){
			PublicacaoRepresentation rep = new PublicacaoRepresentation();
			rep.setId(bo.getId());
			rep.setDataCadastro(bo.getDataCadastro());
			rep.setDataPublicacao(bo.getDataPublicacao());
			rep.setFonte(bo.getFonte());
			return rep;	
		} else {
			return null;
		}
	}
	
	}