package br.gov.pi.tce.publicacoes.controller.prototipo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PrototipoFontesController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fonte fonte;
	private List<Fonte> fontes;
	
	@PostConstruct
	public void init() {
		limpar();
		iniciaFontes();
	}
	
	public void limpar() {
		fonte = new Fonte();
	}

	public void editar(Fonte fonteEditar) {
		fonte = fonteEditar;
	}
	
	public void excluir(Fonte fonteExcluir) {
		fontes.remove(fonteExcluir);
	}
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
	private void iniciaFontes() {
		fontes = new ArrayList<>();
		
		fontes.add(new Fonte("Diário Oficial do Estado", "Estadual", "http://www.diariooficial.pi.gov.br/"));
		fontes.add(new Fonte("Diário Oficial dos Municípios", "Municipal", "http://www.diarioficialdosmunicipios.org/"));
		fontes.add(new Fonte("Diário Oficial de Teresina", "Municipal", "http://www.dom.teresina.pi.gov.br/"));
		fontes.add(new Fonte("Diário Oficial de Parnaíba", "Municipal", "http://dom.parnaiba.pi.gov.br/"));
	}

	public List<Fonte> getFontes() {
		return fontes;
	}
	
	public Fonte getFonte() {
		return fonte;
	}

	public void setFonte(Fonte fonte) {
		this.fonte = fonte;
	}

	public class Fonte {
		private String descricao;
		private String esfera;
		private String url;

		public Fonte() {
			super();
		}

		public Fonte(String descricao, String esfera, String url) {
			super();
			this.descricao = descricao;
			this.esfera = esfera;
			this.url = url;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getEsfera() {
			return esfera;
		}

		public void setEsfera(String esfera) {
			this.esfera = esfera;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}