package br.com.nesoftware.rest.exception.representation;

import java.time.LocalDateTime;

public class FalhaInesperadaRepresentation {
	
	private int codigo;
	
	private String ticket;
	
	private String mensagem;
	
	private LocalDateTime data;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
}
