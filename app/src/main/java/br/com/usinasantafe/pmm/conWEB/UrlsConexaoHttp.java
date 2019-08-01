package br.com.usinasantafe.pmm.conWEB;

public class UrlsConexaoHttp {

    private int tipoEnvio = 1;

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pmm/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pmm/";

    //public static String localPSTVariavel = "br.com.usinasantafe.pmm.to.tb.variaveis.";
    public static String localPSTEstatica = "br.com.usinasantafe.pmm.to.tb.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pmm.conWEB.UrlsConexaoHttp";

    public static String AtividadeTO = urlPrincipal + "atividade2.php";
    public static String BocalTO = urlPrincipal + "bocal2.php";
    public static String EquipSegTO = urlPrincipal + "equipseg2.php";
    public static String ItemCheckListTO = urlPrincipal + "itemchecklist2.php";
    public static String MotoristaTO = urlPrincipal + "motorista2.php";
    public static String ParadaTO = urlPrincipal + "parada2.php";
    public static String PressaoBocalTO = urlPrincipal + "pressaobocal2.php";
    public static String RAtivParadaTO = urlPrincipal + "rativparada2.php";
    public static String TurnoTO = urlPrincipal + "turno2.php";

    public UrlsConexaoHttp() {
    }

    public String getsInserirCheckList() {
        return urlPrincEnvio + "inserirchecklist2.php";
    }

    public String getsInsertApontaMM() {
        return urlPrincEnvio + "inserirapontmm2.php";
    }

    public String getsInsertBolAbertoMM() {
        return urlPrincEnvio + "inserirbolabertomm2.php";
    }

    public String getsInsertBolFechadoMM() {
        return urlPrincEnvio + "inserirbolfechadomm2.php";
    }

    public String getsInsertApontaFert() {
        return urlPrincEnvio + "inserirapontfert2.php";
    }

    public String getsInsertBolAbertoFert() {
        return urlPrincEnvio + "inserirbolabertofert2.php";
    }

    public String getsInsertBolFechadoFert() {
        return urlPrincEnvio + "inserirbolfechadofert2.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincEnvio + "equip2.php";
        } else if (classe.equals("OS")) {
            retorno = urlPrincEnvio + "os2.php";
        } else if (classe.equals("Atividade")) {
            retorno = urlPrincEnvio + "atualativ2.php";
        } else if (classe.equals("AtualParada")) {
            retorno = urlPrincEnvio + "atualparada2.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualaplic2.php";
        } else if (classe.equals("Operador")) {
            retorno = urlPrincEnvio + "motorista2.php";
        } else if (classe.equals("Turno")) {
            retorno = urlPrincEnvio + "turno2.php";
        } else if (classe.equals("EquipSeg")) {
            retorno = urlPrincEnvio + "equipseg2.php";
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincEnvio + "atualchecklist2.php";
        } else if (classe.equals("Pneu")) {
            retorno = urlPrincEnvio + "pneu2.php";
        } else if (classe.equals("Bocal")) {
            retorno = urlPrincEnvio + "bocal2.php";
        } else if (classe.equals("PressaoBocal")) {
            retorno = urlPrincEnvio + "pressaobocal2.php";
        } else if (classe.equals("Perda")) {
            retorno = urlPrincEnvio + "perda2.php";
        }
        return retorno;
    }

}
