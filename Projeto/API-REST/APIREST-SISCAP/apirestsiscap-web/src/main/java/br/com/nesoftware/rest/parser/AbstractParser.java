package br.com.nesoftware.rest.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Persistence;

public abstract class AbstractParser<O, T> implements IParser<O, T>{

	@Override
	public List<O> parseListRepresentation(Iterable<T> lstRep) {
		
		if (lstRep != null){
			List<O> lstBo = new ArrayList<>();
			
			for (T rep : lstRep){
				O o = this.getBo(rep);
				if (o != null){
					lstBo.add(o);
				}
			}
			
			return lstBo;
		}
		
		return Collections.emptyList();
	}

	@Override
	public List<T> parseListBo(Iterable<O> lstBo) {
		
		if (lstBo != null){
			List<T> lstReps = new ArrayList<>();
			
			for (O bo : lstBo){
				T t = this.getRepresentation(bo);
				if (t != null){
					lstReps.add(t);					
				}
			}
			
			return lstReps;
		}
			
		return Collections.emptyList();
	}
	
	/**
	 * Verifica se o objeto está no contexto JPA e é não nulo.
	 * @param obj
	 * @return
	 */
	public boolean isLoadedAndNotNull(Object obj){
		return Persistence.getPersistenceUtil().isLoaded(obj) && obj != null;
	}
}