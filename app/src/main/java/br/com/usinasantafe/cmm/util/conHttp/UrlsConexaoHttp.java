package br.com.usinasantafe.cmm.util.conHttp;

import br.com.usinasantafe.cmm.CMMContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + CMMContext.versaoWS.replace(".", "_");

    public static String url = "https://www.usinasantafe.com.br/pmmdev/view/";
//    public static String url = "https://www.usinasantafe.com.br/pmmqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/pmmprod/" + versao + "/view/";

    public static String urlBD = "https://www.usinasantafe.com.br/pmmprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.cmm.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.cmm.util.conHttp.UrlsConexaoHttp";

    public static String AtividadeBean = urlBD + "atividade.php";
    public static String BocalBean = urlBD + "bocal.php";
    public static String ComponenteBean = urlBD + "componente.php";
    public static String EquipSegBean = urlBD + "equipseg.php";
    public static String EquipPneuBean = urlBD + "equippneu.php";
    public static String FrenteBean = urlBD + "frente.php";
    public static String FuncBean = urlBD + "funcionario.php";
    public static String ItemCheckListBean = urlBD + "itemchecklist.php";
    public static String ItemOSMecanBean = urlBD + "itemosmecan.php";
    public static String LeiraBean = urlBD + "leira.php";
    public static String MotoMecBean = urlBD + "motomec.php";
    public static String OSBean = urlBD + "os.php";
    public static String ParadaBean = urlBD + "parada.php";
    public static String PressaoBocalBean = urlBD + "pressaobocal.php";
    public static String ProdutoBean = urlBD + "produto.php";
    public static String PropriedadeBean = urlBD + "propriedade.php";
    public static String RAtivParadaBean = urlBD + "rativparada.php";
    public static String REquipPneuBean = urlBD + "requippneu.php";
    public static String RFuncaoAtivParBean = urlBD + "rfuncaoativpar.php";
    public static String ROSAtivBean = urlBD + "rosativ.php";
    public static String ServicoBean = urlBD + "servico.php";
    public static String TurnoBean = urlBD + "turno.php";

    public UrlsConexaoHttp() {}

    public String urlVerifica(String classe) {
        String retorno = "";
        switch (classe) {
            case "Equip":
                retorno = urlBD + "equip.php";
                break;
            case "OS":
                retorno = urlBD + "pesqos.php";
                break;
            case "Atividade":
                retorno = urlBD + "pesqativ.php";
                break;
            case "AtividadeECM":
                retorno = urlBD + "pesqativecm.php";
                break;
            case "OSMecan":
                retorno = urlBD + "pesqosmecan.php";
                break;
            case "Atualiza":
                retorno = urlBD + "atualaplic.php";
                break;
            case "CheckList":
                retorno = urlBD + "atualchecklist.php";
                break;
            case "Pneu":
                retorno = urlBD + "pesqpneu.php";
                break;
            case "OrdCarreg":
                retorno = urlBD + "retcarreg.php";
                break;
            case "LocalCarreg":
                retorno = urlBD + "retlocalcarreg.php";
                break;
            case "CEC":
                retorno = urlBD + "retcec.php";
                break;
        }
        return retorno;
    }

}
