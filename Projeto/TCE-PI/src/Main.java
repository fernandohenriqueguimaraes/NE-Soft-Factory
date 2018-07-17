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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class Main {

    // URL das fontes disponibilizadas para busca dos diários oficiais
    public final static String DIARIO_OFICIAL_PI = "http://www.diariooficial.pi.gov.br/diarios.php";
    public final static String DIARIO_OFICIAL_DOS_MUNICIPIOS = "http://www.diarioficialdosmunicipios.org/";
    public final static String DOM_TERESINA = "http://www.dom.teresina.pi.gov.br/lista_diario.php";
    public final static String DOM_PARNAIBA = "http://dom.parnaiba.pi.gov.br/";

    // Lista do objeto Fontes em que consta a data e o nome do pdf
    static ArrayList<Diarios> diarios = new ArrayList<Diarios>();

    public static Date convertDate(String date) {

        if (date.isEmpty()) {
            return null;
        }

        ArrayList<String> dateFormats = new ArrayList<String>(Arrays.asList("dd/MM/yyyy", "dd-MM-yyyy"));

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

    // Busca de diário oficial em http://www.dom.teresina.pi.gov.br/lista_diario.php
    // e tambem no http://dom.parnaiba.pi.gov.br/
    public static void getDiariosDOM(String urlString) {

        try {
            URL url = new URL(urlString);
            BufferedReader fonteHTML = new BufferedReader(new InputStreamReader(url.openStream()));
            String linhaHTML, regexForDate = null, regexForPDF = null;
            Date date = new Date();

            while ((linhaHTML = fonteHTML.readLine()) != null) {

                // A expressão regular das datas e PDFs estão diferentes para cada URL
                if (urlString.equals(DOM_TERESINA)) {
                    regexForDate = "\\d{2}/\\d{2}/\\d{4}";
                    regexForPDF = "[0-9A-Za-z]+[-]+[0-9]+[-]+[0-9A-Za-z]+[.][Pp][Dd][Ff]";
                } else if (urlString.equals(DOM_PARNAIBA)) {
                    regexForDate = "\\d{2}-\\d{2}-\\d{4}";
                    regexForPDF = "[0-9A-Za-z]+[.][Pp][Dd][Ff]";
                }

                if ((!isNull(regexForDate)) && (!isNull(regexForPDF))) {

                    Pattern datePattern = Pattern.compile(regexForDate);
                    Pattern pdfPattern = Pattern.compile(regexForPDF);

                    Matcher matcher = datePattern.matcher(linhaHTML);
                    while (matcher.find()) {
                        date = convertDate(matcher.group());
                    }

                    matcher = pdfPattern.matcher(linhaHTML);
                    while (matcher.find()) {
                        if (!isNull(date)) {
                            diarios.add(new Diarios(date, matcher.group()));
                            // Necessário nullar o date para evitar trazer PDFs que não contenham na lista, e estes estão sem data.
                            date = null;
                        }
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

    // Busca de diário oficial em http://www.diariooficial.pi.gov.br/diarios.php
    public static void getDiariosEmDiarioOficialPI() {

    }

    // Busca de diário oficial em http://www.diarioficialdosmunicipios.org/
    public static void getDiariosEmDiarioOficialDosMunicipios() {

    }

    public static void main(String[] args) throws IOException {

        // Vetor de url das fontes
        String[] fontes = new String[]{
         //       DIARIO_OFICIAL_PI,
         //       DIARIO_OFICIAL_DOS_MUNICIPIOS,
                DOM_TERESINA,
                DOM_PARNAIBA
        };

        for (String fonte : fontes) {
            switch (fonte) {
                case DIARIO_OFICIAL_PI:
                    getDiariosEmDiarioOficialPI();
                    break;
                case DIARIO_OFICIAL_DOS_MUNICIPIOS:
                    getDiariosEmDiarioOficialDosMunicipios();
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
            System.out.println(diario.getDate() + " - "+ diario.getPdfName());
        }

    }


}

