package br.com.usinasantafe.cmm.util.conHttp;

import br.com.usinasantafe.cmm.CMMContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + CMMContext.versaoWS.replace(".", "_");

    public static String url = "https://www.usinasantafe.com.br/pmmdev/view/";
//    public static String url = "https://www.usinasantafe.com.br/pmmqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/pmmprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.cmm.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.cmm.util.conHttp.UrlsConexaoHttp";

    public static String AtividadeBean = url + "atividade.php";
    public static String BocalBean = url + "bocal.php";
    public static String ComponenteBean = url + "componente.php";
    public static String EquipSegBean = url + "equipseg.php";
    public static String EquipPneuBean = url + "equippneu.php";
    public static String FrenteBean = url + "frente.php";
    public static String FuncBean = url + "funcionario.php";
    public static String ItemCheckListBean = url + "itemchecklist.php";
    public static String ItemOSMecanBean = url + "itemosmecan.php";
    public static String LeiraBean = url + "leira.php";
    public static String MotoMecBean = url + "motomec.php";
    public static String OSBean = url + "os.php";
    public static String ParadaBean = url + "parada.php";
    public static String PressaoBocalBean = url + "pressaobocal.php";
    public static String ProdutoBean = url + "produto.php";
    public static String PropriedadeBean = url + "propriedade.php";
    public static String RAtivParadaBean = url + "rativparada.php";
    public static String REquipPneuBean = url + "requippneu.php";
    public static String RFuncaoAtivParBean = url + "rfuncaoativpar.php";
    public static String ROSAtivBean = url + "rosativ.php";
    public static String ServicoBean = url + "servico.php";
    public static String TurnoBean = url + "turno.php";

    public UrlsConexaoHttp() {
    }

    public String getsInsertCarreg() {
        return url + "inserircarreg.php";
    }

    public String getsInserirCheckList() {
        return url + "inserirchecklist.php";
    }

    public String getsInsertPreCEC() {
        return url + "inserirprecec.php";
    }

    public String getsInsertBolAbertoMMFert() {
        return url + "inserirbolabertommfert.php";
    }

    public String getsInsertBolFechadoMMFert() {
        return url + "inserirbolfechadommfert.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        switch (classe) {
            case "Equip":
                retorno = url + "equip.php";
                break;
            case "OS":
                retorno = url + "pesqos.php";
                break;
            case "Atividade":
                retorno = url + "pesqativ.php";
                break;
            case "AtividadeECM":
                retorno = url + "pesqativecm.php";
                break;
            case "OSMecan":
                retorno = url + "pesqosmecan.php";
                break;
            case "Atualiza":
                retorno = url + "atualaplic.php";
                break;
            case "CheckList":
                retorno = url + "atualchecklist.php";
                break;
            case "Pneu":
                retorno = url + "pesqpneu.php";
                break;
            case "OrdCarreg":
                retorno = url + "retcarreg.php";
                break;
            case "LocalCarreg":
                retorno = url + "retlocalcarreg.php";
                break;
            case "CEC":
                retorno = url + "retcec.php";
                break;
        }
        return retorno;
    }

}
