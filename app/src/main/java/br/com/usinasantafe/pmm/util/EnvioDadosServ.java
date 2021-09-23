package br.com.usinasantafe.pmm.util;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import br.com.usinasantafe.pmm.control.CECCTR;
import br.com.usinasantafe.pmm.control.CompostoCTR;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.pmm.control.MotoMecFertCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
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

        LogProcessoDAO.getInstance().insert("checkListCTR.dadosEnvio()", activity);
        envio(urlsConexaoHttp.getsInserirCheckList(), checkListCTR.dadosEnvio(), activity);

    }

    public void envioCarregInsumo(String activity) {

        CompostoCTR compostoCTR = new CompostoCTR();

        LogProcessoDAO.getInstance().insert("compostoCTR.dadosEnvioCarreg()", activity);
        envio(urlsConexaoHttp.getsInsertCarreg(), compostoCTR.dadosEnvioCarreg(), activity);

    }

    public void envioLeiraDescarreg(String activity) {

        CompostoCTR compostoCTR = new CompostoCTR();

        LogProcessoDAO.getInstance().insert("compostoCTR.dadosEnvioLeiraDescarreg()", activity);
        envio(urlsConexaoHttp.getsInsertLeiraDescarreg(), compostoCTR.dadosEnvioLeiraDescarreg(), activity);

    }

    public void envioPreCEC(String activity) {

        CECCTR cecCTR = new CECCTR();

        LogProcessoDAO.getInstance().insert("cecCTR.dadosEnvioPreCEC()", activity);
        envio(urlsConexaoHttp.getsInsertPreCEC(), cecCTR.dadosEnvioPreCEC(), activity);

    }

    public void enviarBolFechadoMMFert(String activity) {

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();

        LogProcessoDAO.getInstance().insert("motoMecFertCTR.dadosEnvioBolFechadoMMFert()", activity);
        envio(urlsConexaoHttp.getsInsertBolFechadoMMFert(), motoMecFertCTR.dadosEnvioBolFechadoMMFert(), activity);

    }

    public void enviarBolAbertoMMFert(String activity) {

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();

        LogProcessoDAO.getInstance().insert("motoMecFertCTR.dadosEnvioBolAbertoMMFert()", activity);
        envio(urlsConexaoHttp.getsInsertBolAbertoMMFert(), motoMecFertCTR.dadosEnvioBolAbertoMMFert(), activity);

    }

    public void envio(String url, String dados, String activity){

        String[] strings = {url, activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        LogProcessoDAO.getInstance().insert("postCadGenerico.execute('" + url + "'); - Dados de Envio = " + dados, activity);
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

    public boolean verifEnvioCarregComposto() {
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
            LogProcessoDAO.getInstance().insert("ActivityGeneric.connectNetwork", activity);
            status = 2;
            if (verifChecklist()) {
                LogProcessoDAO.getInstance().insert("verifChecklist()", activity);
                LogProcessoDAO.getInstance().insert("enviarChecklist()", activity);
                enviarChecklist(activity);
            } else {
                if (verifEnvioCarregInsumo()) {
                    LogProcessoDAO.getInstance().insert("verifEnvioCarregInsumo()", activity);
                    LogProcessoDAO.getInstance().insert("envioCarregInsumo()", activity);
                    envioCarregInsumo(activity);
                } else {
                    if (verifEnvioCarregComposto()) {
                        LogProcessoDAO.getInstance().insert("verifEnvioCarregComposto()", activity);
                        LogProcessoDAO.getInstance().insert("envioLeiraDescarreg()", activity);
                        envioLeiraDescarreg(activity);
                    } else {
                        if (verifPreCEC()) {
                            LogProcessoDAO.getInstance().insert("verifPreCEC()", activity);
                            LogProcessoDAO.getInstance().insert("envioPreCEC()", activity);
                            envioPreCEC(activity);
                        } else {
                            if (verifBolFechadoMMFert()) {
                                LogProcessoDAO.getInstance().insert("verifBolFechadoMMFert()", activity);
                                LogProcessoDAO.getInstance().insert("enviarBolFechadoMMFert()", activity);
                                enviarBolFechadoMMFert(activity);
                            } else {
                                if (verifApontMMMovLeiraFert()) {
                                    LogProcessoDAO.getInstance().insert("verifApontMMMovLeiraFert()", activity);
                                    LogProcessoDAO.getInstance().insert("enviarBolAbertoMMFert()", activity);
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
                && (!verifEnvioCarregComposto())
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
        LogProcessoDAO.getInstance().insert("public void recDados(String " + result + ", String activity){", activity);
        if(result.trim().startsWith("GRAVOU-CHECKLIST")){
            CheckListCTR checkListCTR = new CheckListCTR();
            LogProcessoDAO.getInstance().insert("if(result.trim().startsWith(\"GRAVOU-CHECKLIST\")){\n" +
                    "            CheckListCTR checkListCTR = new CheckListCTR();\n" +
                    "checkListCTR.delChecklist(activity)", activity);
            checkListCTR.delChecklist(activity);
        }
        else if (result.trim().startsWith("BOLABERTOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            LogProcessoDAO.getInstance().insert("else if (result.trim().startsWith(\"BOLABERTOMM\")) {\n" +
                    "            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                    "motoMecFertCTR.updBolAberto(result)", activity);
            motoMecFertCTR.updBolAberto(result, activity);
        }
        else if (result.trim().startsWith("BOLFECHADOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            LogProcessoDAO.getInstance().insert("else if (result.trim().startsWith(\"BOLFECHADOMM\")) {\n" +
                    "            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                    "motoMecFertCTR.delBolFechado(result)", activity);
            motoMecFertCTR.delBolFechado(result, activity);
        }
        else if (result.trim().startsWith("GRAVOU-CARREGINSUMO")) {
            CompostoCTR compostoCTR = new CompostoCTR();
            LogProcessoDAO.getInstance().insert("else if (result.trim().startsWith(\"GRAVOU-CARREGINSUMO\")) {\n" +
                    "            CompostoCTR compostoCTR = new CompostoCTR();\n" +
                    "compostoCTR.updCarregInsumo(result)", activity);
            compostoCTR.updCarregInsumo(result, activity);
        }
        else if (result.trim().startsWith("GRAVOU-CARREGCOMPOSTO")) {
            CompostoCTR compostoCTR = new CompostoCTR();
            LogProcessoDAO.getInstance().insert("else if (result.trim().startsWith(\"GRAVOU-CARREGCOMPOSTO\")) {\n" +
                    "            CompostoCTR compostoCTR = new CompostoCTR();\n" +
                    "compostoCTR.updCarregComposto(result)", activity);
            compostoCTR.updCarregComposto(result, activity);
        }
        else if(result.trim().startsWith("PRECEC")){
            CECCTR cecCTR = new CECCTR();
            LogProcessoDAO.getInstance().insert("else if(result.trim().startsWith(\"PRECEC\")){\n" +
                    "            CECCTR cecCTR = new CECCTR();\n" +
                    "cecCTR.updPreCEC(result)", activity);
            cecCTR.updPreCEC(result, activity);
        }
        else {
            status = 1;
            LogProcessoDAO.getInstance().insert("else {\n" +
                    "            status = 1;", activity);
            LogErroDAO.getInstance().insert(result);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}