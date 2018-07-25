package br.com.nesoftware.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="PUBLICACOES")
public class Publicacao {
    
    
    
//	@NotNull
//	@Id
//	@GenericGenerator(name="PUBLICACAO_SEQ", sequenceName = "SQ_PUBLICACOES", initialValue=1, allocationSize=1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PUBLICACAO_SEQ")
//	
	
	
//	@GenericGenerator(
//	        name = "PUBLICACAO_SEQ_GENERATOR",
//	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//	        parameters = {
//	                @Parameter(name = "sequence_name", value = "SQ_PUBLICACOES"),
//	                @Parameter(name = "initial_value", value = "1"),
//	                @Parameter(name = "increment_size", value = "1")
//	        }
//	)
//	@GeneratedValue(generator = "PUBLICACAO_SEQ_GENERATOR")
	
	
	@Id
	@Column(name="ID", nullable=false)
	private Long id;
		
	
	@Column(name="DT_CADASTRO", nullable=true)
	private LocalDate dataCadastro;
	
	@Column(name="DT_PUBLICACAO", nullable=true)
	private LocalDate dataPublicacao;
	
	@Column(name="FONTE", nullable=true)
	private String fonte;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	
	
	
	
}