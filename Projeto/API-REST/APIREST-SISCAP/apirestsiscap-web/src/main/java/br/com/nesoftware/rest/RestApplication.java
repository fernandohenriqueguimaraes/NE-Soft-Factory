package br.com.nesoftware.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import br.com.nesoftware.rest.publicacao.service.PublicacaoResource;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * Esta classe inclui todos os servicos REST que a aplicacao fornece.
 * 
 */
@ApplicationPath("/rest")
@SwaggerDefinition(
        info = @Info(
                description = "API dos serviçõs para o sistema.",
                version = "V1.0.0",
                title = "SISCAP API REST",
                contact = @Contact(
                   name = "Equipe de desenvolvimento", 
                   email = "", 
                   url = ""
                )
        ),
        schemes = { SwaggerDefinition.Scheme.HTTP },
        tags = {
                @Tag(name = "tarefa", description = "Recurso para gerenciamento de tarefas"),
                @Tag(name = "exemplo", description = "Recurso de exemplo que retorna dados em memória")
        }
)
public class RestApplication extends Application {

	/* Constantes da aplicaçãoo */
	public static final String BASE_PATH = "/apirestsiscap/rest";

	/* Habilita o Swagger */
	public RestApplication() {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setBasePath(RestApplication.BASE_PATH);
		beanConfig.setResourcePackage("br.com.nesoftware");
		beanConfig.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<>();
		/* Classes necessárias para geração da documentação do Swagger */
		resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);

		/*
		 * O registro do JacksonJaxbJsonProvider abaixo garante que o mesmo
		 * formato sera utilizado para a serializacao de objetos JSON
		 * independente do container (JBoss, Weblogic) utilizado.
		 */
		resources.add(JacksonJaxbJsonProvider.class);
		

        /* Adicionar Servicos Aqui */
		resources.add(PublicacaoResource.class);
		
		return resources;
	}
}
