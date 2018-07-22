import model.Diarios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class Main {

    // URL das fontes disponibilizadas para busca dos diários oficiais
    public final static String DIARIO_OFICIAL_PI = "http://www.diariooficial.pi.gov.br/diarios.php";
    public final static String DIARIO_OFICIAL_DOS_MUNICIPIOS = "http://www.diarioficialdosmunicipios.org/";
    //public final static String DOM_TERESINA = "http://www.dom.teresina.pi.gov.br/lista_diario.php";
    //public final static String DOM_PARNAIBA = "http://dom.parnaiba.pi.gov.br/";
    public final static String DOM_TERESINA = "http://www.dom.teresina.pi.gov.br/lista_diario.php?pagina=";
    public final static String DOM_PARNAIBA = "http://dom.parnaiba.pi.gov.br/home?d=";

    // URL Específica para buscar diarios oficiais do Diário Oficial do PI
    public final static String DIARIO_OFICIAL_PI_ESPECIFICA = "http://www.diariooficial.pi.gov.br/diario.php?dia=";

    // URL Específica para buscar diarios oficiais dos municípios
    public final static String DIARIO_OFICIAL_DOS_MUNICIPIOS_ESPECIFICA = "http://www.diarioficialdosmunicipios.org/edicao_atual.html";
    
    public final static String PALAVRA_FINAL_DO_ARQUIVO = "assets/diarios/";

    // Lista do objeto Fontes em que consta a data e o nome do pdf
    static List<Diarios> diarios = new ArrayList<Diarios>();
    

    private static Date convertDate(String date) {

        if (date.isEmpty()) {
            return null;
        }

        List<String> dateFormats = new ArrayList<String>(Arrays.asList("dd/MM/yyyy", "dd-MM-yyyy", "d' 'MMMM' de 'yyyy"));

        for (String dateFormatString : dateFormats) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
                dateFormat.setLenient(false);
                return dateFormat.parse(date.trim());
            } catch (ParseException pe) {
            }
        }
        return null;
    }

    // Busca de diário oficial em http://www.dom.teresina.pi.gov.br/lista_diario.php, http://www.diarioficialdosmunicipios.org/
    // e tambem no http://dom.parnaiba.pi.gov.br/
    public static void getDiariosDOM(String urlString) {

    	int pageDomTeresina = 1;
    	int pageDomParnaiba = 1;
    	Boolean isFinalPaginacao = Boolean.FALSE;
    	List<String> arquivoList = new ArrayList<String>();
        
        if (urlString.equals(DOM_TERESINA)) {
			for (pageDomTeresina = 1; isFinalPaginacao.equals(Boolean.FALSE); pageDomTeresina++) {
				isFinalPaginacao = getPaginasDiariosDOM(urlString, pageDomTeresina, arquivoList);
				if(isFinalPaginacao == null) {
    				System.out.println("Erro na fonte:" + urlString + " - pagina - " + pageDomTeresina);
    				isFinalPaginacao = Boolean.FALSE;
    			}
			}
    	} else if (urlString.equals(DOM_PARNAIBA)) {
    		for (pageDomParnaiba = 1; isFinalPaginacao.equals(Boolean.FALSE); pageDomParnaiba++) {
    			isFinalPaginacao = getPaginasDiariosDOM(urlString, pageDomParnaiba, arquivoList);
    			if(isFinalPaginacao == null) {
    				System.out.println("Erro na fonte:" + urlString + " - pagina - " + pageDomParnaiba);
    				isFinalPaginacao = Boolean.FALSE;
    			}
    		}
    	} else if (urlString.equals(DIARIO_OFICIAL_DOS_MUNICIPIOS)) {
    		isFinalPaginacao = getPaginasDiariosDOM(urlString, 1, arquivoList);
    	}

    }

	/**
	 * @param urlString
	 */
	private static Boolean getPaginasDiariosDOM(String urlString, int pageDom, List<String> arquivoList) {

		String htmlCompleto = "";
		Boolean isFinalPaginacao = null;

		try {
			String linhaHTML, regexForDate = null, regexForPDF = null;
			Date date = null;

			// A expressão regular das datas e PDFs estão diferentes para cada URL
			if (urlString.equals(DOM_TERESINA)) {
				regexForDate = "\\d{2}/\\d{2}/\\d{4}";
				regexForPDF = "[0-9A-Za-z]+[-]+[0-9]+[-]+[0-9A-Za-z]+[.][Pp][Dd][Ff]";
			} else if (urlString.equals(DOM_PARNAIBA)) {
				regexForDate = "\\d{2}-\\d{2}-\\d{4}";
				regexForPDF = "[0-9A-Za-z]+[.][Pp][Dd][Ff]";
			} else if (urlString.equals(DIARIO_OFICIAL_DOS_MUNICIPIOS)) {
				regexForDate = "\\d{2}\\s+((Janeiro)|(Fevereiro)|(Março)|(Abril)|(Maio)|(Junho)|(Julho)|(Agosto)|(Setembro)|(Outubro)|(Novembro)|(Dezembro))\\s+[d][e]\\s+\\d{4}";
				regexForPDF = "[D][M]\\s+[0-9]+[.][Pp][Dd][Ff]";
				// Em particular, o diário oficial dos municipios também requer a busca em uma
				// página específica
				urlString = DIARIO_OFICIAL_DOS_MUNICIPIOS_ESPECIFICA;
			}

			URL url = new URL(urlString);
			if (urlString.equals(DOM_TERESINA) || urlString.equals(DOM_PARNAIBA)) {
				url = new URL(urlString + pageDom);
			}
			BufferedReader fonteHTML = new BufferedReader(new InputStreamReader(url.openStream()));
			Pattern datePattern = Pattern.compile(regexForDate);
			Pattern pdfPattern = Pattern.compile(regexForPDF);

			while ((linhaHTML = fonteHTML.readLine()) != null) {

				htmlCompleto = htmlCompleto + linhaHTML;
				if ((!isNull(regexForDate)) && (!isNull(regexForPDF))) {

					Matcher matcher = datePattern.matcher(linhaHTML);
					while (matcher.find()) {
						date = convertDate(matcher.group());
					}

					matcher = pdfPattern.matcher(linhaHTML);
					while (matcher.find()) {
						if (!isNull(date)) {
							String arquivoStr = matcher.group();
							if (urlString.equals(DOM_PARNAIBA) || urlString.equals(DIARIO_OFICIAL_DOS_MUNICIPIOS_ESPECIFICA)) {
							diarios.add(new Diarios(urlString, date, arquivoStr));
							// Necessário nullar o date para evitar trazer PDFs que não contenham na
							// lista, e estes estão sem data.
							date = null;
							} else if (urlString.equals(DOM_TERESINA)) {
								if (!arquivoList.contains(arquivoStr)) {
									arquivoList.add(arquivoStr);
									diarios.add(new Diarios(urlString, date, arquivoStr));
									// Necessário nullar o date para evitar trazer PDFs que não contenham na
									// lista, e estes estão sem data.
									date = null;
									isFinalPaginacao = Boolean.FALSE;
								} else {
									isFinalPaginacao = Boolean.TRUE;
									return isFinalPaginacao;
								}
							}
						}
					}
				}

			}
			fonteHTML.close();
		} catch (MalformedURLException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
		
		if (urlString.equals(DOM_PARNAIBA)) {
			if (htmlCompleto.contains(PALAVRA_FINAL_DO_ARQUIVO)) {
				isFinalPaginacao = Boolean.FALSE;
			} else {
				isFinalPaginacao = Boolean.TRUE;
			}
		}
		return isFinalPaginacao;
	}

    // Busca de diário oficial em http://www.diariooficial.pi.gov.br/diarios.php
    public static void getDiariosEmDiarioOficialPI() {
        try {
            URL url = new URL(DIARIO_OFICIAL_PI);
            BufferedReader fonteHTML = new BufferedReader(new InputStreamReader(url.openStream()));
            String linhaHTML, regexForDate = null, regexForPDF = null;
            Date date = null;
            String dateString = null;
            Matcher matcher = null;

            // Encontra a data do ultimo diário publicado
            buscaData:
            while ((linhaHTML = fonteHTML.readLine()) != null) {

                regexForDate = "\\d{2}/\\d{2}/\\d{4}";

                Pattern datePattern = Pattern.compile(regexForDate);
                matcher = datePattern.matcher(linhaHTML);

                while (matcher.find()) {
                    date = convertDate(matcher.group());
                    dateString = matcher.group();
                    // Ao encontrar a data sai do loop mais externo
                    break buscaData;
                }
            }
            fonteHTML.close();

            // Constrói nova url para coletar o PDF e Busca em nova fonte
            String arrayData[] = dateString.split("/");
            url = new URL(DIARIO_OFICIAL_PI_ESPECIFICA+arrayData[2]+arrayData[1]+arrayData[0]);
            fonteHTML = new BufferedReader(new InputStreamReader(url.openStream()));

            buscaPDF:
            while ((linhaHTML = fonteHTML.readLine()) != null) {
                regexForPDF = "DIARIO+[0-9]+[_]+[0-9A-Za-z]+[.][Pp][Dd][Ff]";
                Pattern pdfPattern = Pattern.compile(regexForPDF);

                matcher = pdfPattern.matcher(linhaHTML);
                while (matcher.find()) {
                    if (!isNull(date)) {
                        diarios.add(new Diarios(DIARIO_OFICIAL_PI, date, matcher.group()));
                        // Ao encontrar o pdf sai do loop mais externo
                        break buscaPDF;
                    }
                }
            }
            fonteHTML.close();

        } catch(MalformedURLException excecao){
            excecao.printStackTrace();
        } catch(IOException excecao){
            excecao.printStackTrace();
        }

    }

    public static void main(String[] args) {

        // Vetor de url das fontes
        String[] fontes = new String[]{
                DIARIO_OFICIAL_PI,
                DIARIO_OFICIAL_DOS_MUNICIPIOS,
                DOM_TERESINA,
                DOM_PARNAIBA
        };

        // Formato de exibição da data
        SimpleDateFormat formatoDeData = new SimpleDateFormat("dd/MM/yyyy");

        for (String fonte : fontes) {
            switch (fonte) {
                case DIARIO_OFICIAL_PI:
                    getDiariosEmDiarioOficialPI();
                    break;
                case DIARIO_OFICIAL_DOS_MUNICIPIOS:
                    getDiariosDOM(DIARIO_OFICIAL_DOS_MUNICIPIOS);
                    break;
                case DOM_TERESINA:
                    getDiariosDOM(DOM_TERESINA);
                    break;
                case DOM_PARNAIBA:
                    getDiariosDOM(DOM_PARNAIBA);
                    break;
            }
        }

        for(Diarios diario : diarios) {
            System.out.println(diario.getFonte()+ " - "+ formatoDeData.format( diario.getDate()) + " - "+ diario.getPdfName());
        }
    }
    
}

