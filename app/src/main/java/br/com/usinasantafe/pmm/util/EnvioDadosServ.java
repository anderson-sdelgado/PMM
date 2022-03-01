package br.com.usinasantafe.pmm.util;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pmm.control.CECCTR;
import br.com.usinasantafe.pmm.control.CompostoCTR;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.pmm.control.MotoMecFertCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.util.conHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pmm.view.ActivityGeneric;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    public static int status; //1 - Existe Dados para Enviar; 2 - Enviado; 3 - Todos os Dados Foram Enviados;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    ////////////////////////////////// ENVIAR DADOS ///////////////////////////////////////////////

    public void enviarChecklist(String activity) {

        CheckListCTR checkListCTR = new CheckListCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("checkListCTR.dadosEnvio()", activity);
        envio(urlsConexaoHttp.getsInserirCheckList(), checkListCTR.dadosEnvio(), activity);

    }

    public void envioCarregInsumo(String activity) {

        CompostoCTR compostoCTR = new CompostoCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("compostoCTR.dadosEnvioCarreg()", activity);
        envio(urlsConexaoHttp.getsInsertCarreg(), compostoCTR.dadosEnvioCarreg(), activity);

    }

    public void envioLeiraDescarreg(String activity) {

        CompostoCTR compostoCTR = new CompostoCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("compostoCTR.dadosEnvioLeiraDescarreg()", activity);
        envio(urlsConexaoHttp.getsInsertLeiraDescarreg(), compostoCTR.dadosEnvioLeiraDescarreg(), activity);

    }

    public void envioPreCEC(String activity) {

        CECCTR cecCTR = new CECCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("cecCTR.dadosEnvioPreCEC()", activity);
        envio(urlsConexaoHttp.getsInsertPreCEC(), cecCTR.dadosEnvioPreCEC(), activity);

    }

    public void enviarBolFechadoMMFert(String activity) {

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("motoMecFertCTR.dadosEnvioBolFechadoMMFert()", activity);
        envio(urlsConexaoHttp.getsInsertBolFechadoMMFert(), motoMecFertCTR.dadosEnvioBolFechadoMMFert(), activity);

    }

    public void enviarBolAbertoMMFert(String activity) {

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("motoMecFertCTR.dadosEnvioBolAbertoMMFert()", activity);
        envio(urlsConexaoHttp.getsInsertBolAbertoMMFert(), motoMecFertCTR.dadosEnvioBolAbertoMMFert(), activity);

    }

    public void envio(String url, String dados, String activity){

        String[] strings = {url, activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        LogProcessoDAO.getInstance().insertLogProcesso("postCadGenerico.execute('" + url + "'); - Dados de Envio = " + dados, activity);
        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(strings);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////VERIFICAÇÃO DE DADOS/////////////////////////////////////////

    public boolean verifChecklist() {
        CheckListCTR checkListCTR = new CheckListCTR();
        return checkListCTR.verEnvioDados();
    }

    public boolean verifEnvioCarregInsumo() {
        CompostoCTR compostoCTR = new CompostoCTR();
        return compostoCTR.verifEnvioCarregInsumo();
    }

    public boolean verifEnvioLeiraDescarreg() {
        CompostoCTR compostoCTR = new CompostoCTR();
        return compostoCTR.verifEnvioLeiraDescarreg();
    }

    public boolean verifPreCEC() {
        CECCTR cecCTR = new CECCTR();
        return cecCTR.verPreCECFechado();
    }

    public Boolean verifBolFechadoMMFert() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.verEnvioBolFech();
    }

    public Boolean verifApontMMMovLeiraFert() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.verEnvioApont() || motoMecFertCTR.verEnvioMovLeira();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void envioDados(String activity) {
        status = 1;
        if(ActivityGeneric.connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("ActivityGeneric.connectNetwork", activity);
            status = 2;
            if (verifChecklist()) {
                LogProcessoDAO.getInstance().insertLogProcesso("verifChecklist()\n" +
                         "enviarChecklist()", activity);
                enviarChecklist(activity);
            } else {
                if (verifEnvioCarregInsumo()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("verifEnvioCarregInsumo()\n" +
                            "envioCarregInsumo()", activity);
                    envioCarregInsumo(activity);
                } else {
                    if (verifEnvioLeiraDescarreg()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("verifEnvioCarregComposto()\n" +
                                "envioLeiraDescarreg()", activity);
                        envioLeiraDescarreg(activity);
                    } else {
                        if (verifPreCEC()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("verifPreCEC()\n" +
                                    "envioPreCEC()", activity);
                            envioPreCEC(activity);
                        } else {
                            if (verifBolFechadoMMFert()) {
                                LogProcessoDAO.getInstance().insertLogProcesso("verifBolFechadoMMFert()\n" +
                                        "enviarBolFechadoMMFert()", activity);
                                enviarBolFechadoMMFert(activity);
                            } else {
                                if (verifApontMMMovLeiraFert()) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("verifApontMMMovLeiraFert()\n" +
                                            "enviarBolAbertoMMFert()", activity);
                                    enviarBolAbertoMMFert(activity);
                                } else {
                                    status = 3;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifBolFechadoMMFert())
                && (!verifEnvioCarregInsumo())
                && (!verifEnvioLeiraDescarreg())
                && (!verifPreCEC())
                && (!verifApontMMMovLeiraFert())
                && (!verifChecklist())){
            return false;
        } else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void recDados(String result, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void recDados(String " + result + ", String activity){", activity);
        if(result.trim().startsWith("GRAVOU-CHECKLIST")){
            CheckListCTR checkListCTR = new CheckListCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("if(result.trim().startsWith(\"GRAVOU-CHECKLIST\")){\n" +
                    "            CheckListCTR checkListCTR = new CheckListCTR();\n" +
                    "checkListCTR.delChecklist(activity)", activity);
            checkListCTR.updateRecebChecklist(activity);
        }
        else if (result.trim().startsWith("BOLABERTOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"BOLABERTOMM\")) {\n" +
                    "            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                    "motoMecFertCTR.updBolAberto(result)", activity);
            motoMecFertCTR.updBolAberto(result, activity);
        }
        else if (result.trim().startsWith("BOLFECHADOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"BOLFECHADOMM\")) {\n" +
                    "            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                    "motoMecFertCTR.delBolFechado(result)", activity);
            motoMecFertCTR.updateBolFechado(result, activity);
        }
        else if (result.trim().startsWith("GRAVOU-CARREGINSUMO")) {
            CompostoCTR compostoCTR = new CompostoCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"GRAVOU-CARREGINSUMO\")) {\n" +
                    "            CompostoCTR compostoCTR = new CompostoCTR();\n" +
                    "compostoCTR.updCarregInsumo(result)", activity);
            compostoCTR.updCarregInsumo(result, activity);
        }
        else if (result.trim().startsWith("GRAVOU-CARREGCOMPOSTO")) {
            CompostoCTR compostoCTR = new CompostoCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"GRAVOU-CARREGCOMPOSTO\")) {\n" +
                    "            CompostoCTR compostoCTR = new CompostoCTR();\n" +
                    "compostoCTR.updCarregComposto(result)", activity);
            compostoCTR.updCarregCompostoDescarreg(result, activity);
        }
        else if (result.trim().startsWith("GRAVOU-LEIRADESCARREG")) {
            CompostoCTR compostoCTR = new CompostoCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"GRAVOU-CARREGCOMPOSTO\")) {\n" +
                    "            CompostoCTR compostoCTR = new CompostoCTR();\n" +
                    "compostoCTR.updCarregComposto(result)", activity);
            compostoCTR.updCarregCompostoDescarreg(result, activity);
        }
        else if(result.trim().startsWith("PRECEC")){
            CECCTR cecCTR = new CECCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("else if(result.trim().startsWith(\"PRECEC\")){\n" +
                    "            CECCTR cecCTR = new CECCTR();\n" +
                    "cecCTR.updPreCEC(result)", activity);
            cecCTR.updPreCEC(result, activity);
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "            status = 1;", activity);
            status = 1;
            LogErroDAO.getInstance().insertLogErro(result);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}