package br.com.nesoftware.integracao.dao;

@FunctionalInterface
public interface IDaoParser<O,T> {

	public O getBo(T serviceBo);
}