package br.com.nesoftware.rest.parser;

import java.util.List;

public interface IParser<O,T> {

	public O getBo(T rep);
	
	public T getRepresentation(O bo);
	
	public List<O> parseListRepresentation(Iterable<T> lstRep);
	
	public List<T> parseListBo(Iterable<O> lstBo);
}
