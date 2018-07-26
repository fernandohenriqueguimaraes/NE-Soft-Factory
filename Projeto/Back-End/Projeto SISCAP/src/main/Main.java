package main;

import service.DiariosService;

import java.util.Date;

public class Main {

    // URL das fontes disponibilizadas para busca dos diários oficiais
    public final static String DIARIO_OFICIAL_PI = "http://www.diariooficial.pi.gov.br/diarios.php";
    public final static String DIARIO_OFICIAL_DOS_MUNICIPIOS = "http://www.diarioficialdosmunicipios.org/";
    //public final static String DOM_TERESINA = "http://www.dom.teresina.pi.gov.br/lista_diario.php";
    //public final static String DOM_PARNAIBA = "http://dom.parnaiba.pi.gov.br/";
    public final static String DOM_TERESINA = "http://www.dom.teresina.pi.gov.br/lista_diario.php?pagina=";
    public final static String DOM_PARNAIBA = "http://dom.parnaiba.pi.gov.br/home?d=";

    public static void main(String[] args) {

        Date dataInicial =  DiariosService.getData("23/07/2018 00:00:00");
        Date dataFinal = DiariosService.getData("26/07/2018 23:59:59");

        // Vetor de url das fontes
        String[] fontes = new String[]{
                DIARIO_OFICIAL_PI,
                DIARIO_OFICIAL_DOS_MUNICIPIOS,
                DOM_TERESINA,
                DOM_PARNAIBA
        };

        for (String fonte : fontes) {
            switch (fonte) {
                case DIARIO_OFICIAL_PI:
                    DiariosService.getDiariosEmDiarioOficialPI(dataInicial, dataFinal);
                    break;
                case DIARIO_OFICIAL_DOS_MUNICIPIOS:
                    DiariosService.getDiariosDOM(DIARIO_OFICIAL_DOS_MUNICIPIOS, dataInicial, dataFinal);
                    break;
                case DOM_TERESINA:
                    DiariosService.getDiariosDOM(DOM_TERESINA, dataInicial, dataFinal);
                    break;
                case DOM_PARNAIBA:
                    DiariosService.getDiariosDOM(DOM_PARNAIBA, dataInicial, dataFinal);
                    break;
            }
        }
    }


}