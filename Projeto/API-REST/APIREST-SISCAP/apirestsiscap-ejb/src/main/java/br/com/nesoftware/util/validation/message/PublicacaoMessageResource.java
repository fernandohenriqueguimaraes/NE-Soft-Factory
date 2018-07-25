package br.com.nesoftware.util.validation.message;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;


/**
 * Classe que agrupa o Resource Bundle , carregando as mensagens de varios arquivos properties
 *
 * @author helton.souto
 *
 */
public class PublicacaoMessageResource extends AggregateResourceBundleLocator{

    private ResourceBundle resourceBundleDefault;
    
    
    public PublicacaoMessageResource() {
        super(Arrays.asList(
                "properties.mensagens.ValidationMessages"
        ));
        resourceBundleDefault = this.getResourceBundle(Locale.getDefault());
    }
    
    
    /**
     * Retorna a mensagem do associada a chave do Resource Bundle Default
     * @param key
     * @return
     */
    public String getMessage(String key){
        if(resourceBundleDefault.containsKey(key)){
            return resourceBundleDefault.getString(key);
        }
        return "{"+key+"}";
    }

    
    /**
    * Verifica se a chave indicada contem no Resource Bundle Default
    * @param key
    * @return
    */
    public boolean containsKey(String key){
        return resourceBundleDefault.containsKey(key);
    }

    
    /**
     * Retorna o  Resource Bundle Default
     * @return
     */
    public ResourceBundle getResourceBundleDefault() {
        return resourceBundleDefault;
    }

}