package br.com.usinasantafe.pmm.util;

import br.com.usinasantafe.pmm.PMMContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pmm/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pmm/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pmm.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pmm.util.UrlsConexaoHttp";

    public static String put = "?versao=" + PMMContext.versaoAplic.replace(".", "_");

    public static String AtividadeTO = urlPrincipal + "atividade.php" + put;
    public static String BocalTO = urlPrincipal + "bocal.php" + put;
    public static String EquipSegTO = urlPrincipal + "equipseg.php" + put;
    public static String ItemCheckListTO = urlPrincipal + "itemchecklist.php" + put;
    public static String FuncionarioTO = urlPrincipal + "funcionario.php" + put;
    public static String ParadaTO = urlPrincipal + "parada.php" + put;
    public static String PressaoBocalTO = urlPrincipal + "pressaobocal.php" + put;
    public static String RAtivParadaTO = urlPrincipal + "rativparada.php" + put;
    public static String RFuncaoAtivParTO = urlPrincipal + "rfuncaoativpar.php" + put;
    public static String TurnoTO = urlPrincipal + "turno.php" + put;
    public static String LeiraTO = urlPrincipal + "leira.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInserirCheckList() {
        return urlPrincEnvio + "inserirchecklist.php" + put;
    }

    public String getsInsertApontaMM() {
        return urlPrincEnvio + "inserirapontmm.php" + put;
    }

    public String getsInsertBolAbertoMM() {
        return urlPrincEnvio + "inserirbolabertomm.php" + put;
    }

    public String getsInsertBolFechadoMM() {
        return urlPrincEnvio + "inserirbolfechadomm.php" + put;
    }

    public String getsInsertApontaFert() {
        return urlPrincEnvio + "inserirapontfert.php" + put;
    }

    public String getsInsertBolAbertoFert() {
        return urlPrincEnvio + "inserirbolabertofert.php" + put;
    }

    public String getsInsertBolFechadoFert() {
        return urlPrincEnvio + "inserirbolfechadofert.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincipal + "equip.php" + put;
        } else if (classe.equals("OS")) {
            retorno = urlPrincipal + "os.php" + put;
        } else if (classe.equals("Atividade")) {
            retorno = urlPrincipal + "atualativ.php" + put;
        } else if (classe.equals("AtualParada")) {
            retorno = urlPrincipal + "atualparada.php" + put;
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php" + put;
        } else if (classe.equals("Operador")) {
            retorno = urlPrincipal + "motorista.php" + put;
        } else if (classe.equals("Turno")) {
            retorno = urlPrincipal + "turno.php" + put;
        } else if (classe.equals("EquipSeg")) {
            retorno = urlPrincipal + "equipseg.php" + put;
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincipal + "atualchecklist.php" + put;
        } else if (classe.equals("Pneu")) {
            retorno = urlPrincipal + "pneu.php" + put;
        } else if (classe.equals("Colheita")) {
            retorno = urlPrincipal + "perda.php" + put;
        }
        return retorno;
    }

}
