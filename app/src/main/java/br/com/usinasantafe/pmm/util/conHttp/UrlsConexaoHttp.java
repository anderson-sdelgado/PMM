package br.com.usinasantafe.pmm.util.conHttp;

import br.com.usinasantafe.pmm.PMMContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + PMMContext.versaoWebService.replace(".", "_");

//    public static String urlPrincipal = "https://www.usinasantafe.com.br/pmmdev/view/";
//    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pmmdev/view/";

//    public static String urlPrincipal = "https://www.usinasantafe.com.br/pmmqa/view/";
//    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pmmqa/view/";

    public static String urlPrincipal = "https://www.usinasantafe.com.br/pmmprod/" + versao + "/view/";
    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pmmprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pmm.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pmm.util.conHttp.UrlsConexaoHttp";

    public static String AtividadeBean = urlPrincipal + "atividade.php";
    public static String BocalBean = urlPrincipal + "bocal.php";
    public static String ComponenteBean = urlPrincipal + "componente.php";
    public static String EquipSegBean = urlPrincipal + "equipseg.php";
    public static String FrenteBean = urlPrincipal + "frente.php";
    public static String FuncBean = urlPrincipal + "funcionario.php";
    public static String ItemCheckListBean = urlPrincipal + "itemchecklist.php";
    public static String ItemOSMecanBean = urlPrincipal + "itemosmecan.php";
    public static String LeiraBean = urlPrincipal + "leira.php";
    public static String MotoMecBean = urlPrincipal + "motomec.php";
    public static String OSBean = urlPrincipal + "os.php";
    public static String ParadaBean = urlPrincipal + "parada.php";
    public static String PneuBean = urlPrincipal + "pneu.php";
    public static String PressaoBocalBean = urlPrincipal + "pressaobocal.php";
    public static String ProdutoBean = urlPrincipal + "produto.php";
    public static String PropriedadeBean = urlPrincipal + "propriedade.php";
    public static String RAtivParadaBean = urlPrincipal + "rativparada.php";
    public static String RFuncaoAtivParBean = urlPrincipal + "rfuncaoativpar.php";
    public static String ROSAtivBean = urlPrincipal + "rosativ.php";
    public static String ServicoBean = urlPrincipal + "servico.php";
    public static String TurnoBean = urlPrincipal + "turno.php";

    public UrlsConexaoHttp() {
    }

    public String getsInsertCarreg() {
        return urlPrincEnvio + "inserircarreg.php";
    }

    public String getsInserirCheckList() {
        return urlPrincEnvio + "inserirchecklist.php";
    }

    public String getsInsertPreCEC() {
        return urlPrincEnvio + "inserirprecec.php";
    }

    public String getsInsertBolAbertoMMFert() {
        return urlPrincEnvio + "inserirbolabertommfert.php";
    }

    public String getsInsertBolFechadoMMFert() {
        return urlPrincEnvio + "inserirbolfechadommfert.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincipal + "equip.php";
        } else if (classe.equals("OS")) {
            retorno = urlPrincipal + "pesqos.php";
        } else if (classe.equals("Atividade")) {
            retorno = urlPrincipal + "pesqativ.php";
        } else if (classe.equals("AtividadeECM")) {
            retorno = urlPrincipal + "pesqativecm.php";
        } else if (classe.equals("OSMecan")) {
            retorno = urlPrincipal + "pesqosmecan.php";
        } else if (classe.equals("AtualParada")) {
            retorno = urlPrincipal + "atualparada.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php";
        } else if (classe.equals("Operador")) {
            retorno = urlPrincipal + "motorista.php";
        } else if (classe.equals("Turno")) {
            retorno = urlPrincipal + "turno.php";
        } else if (classe.equals("EquipSeg")) {
            retorno = urlPrincipal + "equipseg.php";
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincipal + "atualchecklist.php";
        } else if (classe.equals("Pneu")) {
            retorno = urlPrincipal + "pesqpneu.php";
        } else if (classe.equals("Informativo")) {
            retorno = urlPrincipal + "informativo.php";
        } else if(classe.equals("OrdCarreg")){
            retorno = urlPrincipal + "retcarreg.php";
        } else if (classe.equals("CEC")) {
            retorno = urlPrincEnvio + "retcec.php";
        }
        return retorno;
    }

}
