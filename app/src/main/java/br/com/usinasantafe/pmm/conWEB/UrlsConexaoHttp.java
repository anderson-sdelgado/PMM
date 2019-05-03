package br.com.usinasantafe.pmm.conWEB;

public class UrlsConexaoHttp {

    private int tipoEnvio = 1;

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pmmdev/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pmmdev/";

    //public static String localPSTVariavel = "br.com.usinasantafe.pmm.to.tb.variaveis.";
    public static String localPSTEstatica = "br.com.usinasantafe.pmm.to.tb.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pmm.conWEB.UrlsConexaoHttp";

    public static String AtividadeTO = urlPrincipal + "atividade.php";
    public static String ParadaTO = urlPrincipal + "parada.php";
    public static String TurnoTO = urlPrincipal + "turno.php";
    public static String MotoristaTO = urlPrincipal + "motorista.php";
    public static String ItemCheckListTO = urlPrincipal + "itemchecklist.php";
    public static String EquipSegTO = urlPrincipal + "equipseg.php";

    public UrlsConexaoHttp() {
        // TODO Auto-generated constructor stub
    }

    public String getsInsertApontaMM() {
        return urlPrincEnvio + "inserirapont2.php";
    }

    public String getsApontChecklist() {
        return urlPrincEnvio + "apontchecklist2.php";
    }

    public String getsInsertBolAbertoMM() {
        return urlPrincEnvio + "inserirbolaberto2.php";
    }

    public String getsInsertBolFechadoMM() {
        return urlPrincEnvio + "inserirbolfechado2.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincEnvio + "verifequip2.php";
        } else if (classe.equals("OS")) {
            retorno = urlPrincEnvio + "verifos2.php";
        } else if (classe.equals("Atividade")) {
            retorno = urlPrincEnvio + "verifativ2.php";
        } else if (classe.equals("Parada")) {
            retorno = urlPrincEnvio + "verifativpar2.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualizaaplic.php";
        } else if (classe.equals("Operador")) {
            retorno = urlPrincEnvio + "motorista.php";
        } else if (classe.equals("Turno")) {
            retorno = urlPrincEnvio + "turno.php";
        } else if (classe.equals("EquipSeg")) {
            retorno = urlPrincEnvio + "equipseg.php";
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincEnvio + "verifchecklist2.php";
        } else if (classe.equals("GrafPlantio")) {
            retorno = urlPrincEnvio + "grafplantio.php";
        } else if (classe.equals("Pneu")) {
            retorno = urlPrincEnvio + "verifpneu2.php";
        }
        return retorno;
    }

}
