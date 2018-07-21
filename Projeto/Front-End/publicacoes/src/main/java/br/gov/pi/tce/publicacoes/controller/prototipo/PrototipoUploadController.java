package br.gov.pi.tce.publicacoes.controller.prototipo;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class PrototipoUploadController implements Serializable {

	private static final long serialVersionUID = 1L;

	private int fonte;
	private Date data;
	private String nomeArquivo;
	private UploadedFile file;
	
	@PostConstruct
	public void init() {
		limpar();
	}
	
	public void limpar() {
		setFonte(0);
		setData(null);
		setNomeArquivo("");
		file = null;
	}

	public void salvar() {
		if (file == null) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Atenção", "Arquivo de diário não informado.");
			return;
		}
		
		addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Diário salvo com sucesso.");
		limpar();
	}

	public void uploadFile(FileUploadEvent event) {
		setFile(event.getFile());
		setNomeArquivo(event.getFile().getFileName());
    }
	
	public void addMessage(Severity severityInfo, String summary, String detail) {
        FacesMessage message = new FacesMessage(severityInfo, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public int getFonte() {
		return fonte;
	}

	public void setFonte(int fonte) {
		this.fonte = fonte;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
    
}