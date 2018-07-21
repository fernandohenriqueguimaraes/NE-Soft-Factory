package br.gov.pi.tce.publicacoes.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

@Named
@ApplicationScoped
public class ApplicationController {

	private String version;
	
	public String getVersion() {
		if (this.version == null) {
			this.version = initVersion();
		}
		
		return this.version;
	}

	private String initVersion() {
		Properties properties = getProperties();
		if (properties == null)
			return "";
		
		return "Versão " + properties.getProperty("version") + " - " + properties.getProperty("buildTime");
	}
	
	private Properties getProperties() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		Properties prop = new Properties();
		
		InputStream resourceAsStream = servletContext.getResourceAsStream("/META-INF/MANIFEST.MF");
		if (resourceAsStream != null) {
			try {
				prop.load(resourceAsStream);
			} catch (IOException e) {
				return null;
			}
		}
		
		return prop;
	}

	
}
