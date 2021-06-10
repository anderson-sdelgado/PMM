package br.com.usinasantafe.pmm.util.conHttp;

import br.com.usinasantafe.pmm.PMMContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pmmdev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pmmdev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pmm.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pmm.util.conHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PMMContext.versaoAplic.replace(".", "_");

    public static String AtividadeBean = urlPrincipal + "atividade.php" + put;
    public static String BocalBean = urlPrincipal + "bocal.php" + put;
    public static String EquipSegBean = urlPrincipal + "equipseg.php" + put;
    public static String ItemCheckListBean = urlPrincipal + "itemchecklist.php" + put;
    public static String FuncBean = urlPrincipal + "funcionario.php" + put;
    public static String ParadaBean = urlPrincipal + "parada.php" + put;
    public static String PressaoBocalBean = urlPrincipal + "pressaobocal.php" + put;
    public static String RAtivParadaBean = urlPrincipal + "rativparada.php" + put;
    public static String RFuncaoAtivParBean = urlPrincipal + "rfuncaoativpar.php" + put;
    public static String TurnoBean = urlPrincipal + "turno.php" + put;
    public static String LeiraBean = urlPrincipal + "leira.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInserirCheckList() {
        return urlPrincEnvio + "inserirchecklist.php" + put;
    }

    public String getsInsertBolAbertoMM() {
        return urlPrincEnvio + "inserirbolabertommfert.php" + put;
    }

    public String getsInsertBolFechadoMM() {
        return urlPrincEnvio + "inserirbolfechadommfert.php" + put;
    }

    public String getsInsertLogErro() {
        return urlPrincEnvio + "inserirlogerro.php" + put;
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
        } else if (classe.equals("Informativo")) {
            retorno = urlPrincipal + "informativo.php" + put;
        }
        return retorno;
    }

}
