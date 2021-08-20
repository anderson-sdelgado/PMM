package br.com.usinasantafe.pmm.util;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import br.com.usinasantafe.pmm.control.CECCTR;
import br.com.usinasantafe.pmm.control.CompostoCTR;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
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

    public void enviarChecklist() {

        CheckListCTR checkListCTR = new CheckListCTR();
        String dados = checkListCTR.dadosEnvio();

        Log.i("PMM", "CHECKLIST = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInserirCheckList()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioCarreg() {

        CompostoCTR compostoCTR = new CompostoCTR();
        String dados = compostoCTR.dadosEnvioCarreg();

        String[] url = {urlsConexaoHttp.getsInsertCarreg()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        Log.i("ECM", "CARREG = " + dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    public void envioLeiraDescarreg() {

        CompostoCTR compostoCTR = new CompostoCTR();
        String dados = compostoCTR.dadosEnvioLeiraDescarreg();

        String[] url = {urlsConexaoHttp.getsInsertLeiraDescarreg()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        Log.i("ECM", "CARREG = " + dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    public void envioPreCEC() {

        CECCTR cecCTR = new CECCTR();
        String dados = cecCTR.dadosEnvioPreCEC();

        String[] url = {urlsConexaoHttp.getsInsertPreCEC()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        Log.i("ECM", "DADOS VIAGEM = " + dados);

        PostCadGenerico.getInstance().setParametrosPost(parametrosPost);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    public void enviarBolFechadoMMFert() {

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        String dados = motoMecFertCTR.dadosEnvioBolFechadoMMFert();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechadoMMFert()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAbertoMMFert() {

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        String dados = motoMecFertCTR.dadosEnvioBolAbertoMMFert();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAbertoMMFert()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioLogErro() {

        ConfigCTR configCTR = new ConfigCTR();
        String dados = configCTR.dadosEnvioLogErro();

        Log.i("PMM", "LOG ERRO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertLogErro()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

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

    public Boolean verifLogErro() {
        ConfigCTR configCTR = new ConfigCTR();
        return configCTR.verEnvioLogErro();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void envioDados(int lugar) {
        Log.i("PMM", "ENVIANDO 1 = " + lugar);
        status = 1;
        if(ActivityGeneric.connectNetwork) {
            Log.i("PMM", "ENVIANDO 2");
            status = 2;
            if (verifChecklist()) {
                enviarChecklist();
            } else {
                if (verifEnvioCarregInsumo()) {
                    envioCarreg();
                } else {
                    if (verifEnvioCarregComposto()) {
                        envioLeiraDescarreg();
                    } else {
                        if (verifPreCEC()) {
                            envioPreCEC();
                        } else {
                            if (verifBolFechadoMMFert()) {
                                enviarBolFechadoMMFert();
                            } else {
                                if (verifApontMMMovLeiraFert()) {
                                    enviarBolAbertoMMFert();
                                } else {
                                    if (verifLogErro()) {
                                        envioLogErro();
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
    }

    public boolean verifDadosEnvio() {
        if ((!verifBolFechadoMMFert())
                && (!verifEnvioCarregInsumo())
                && (!verifEnvioCarregComposto())
                && (!verifPreCEC())
                && (!verifApontMMMovLeiraFert())
                && (!verifChecklist())
                && (!verifLogErro())){
            return false;
        } else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void recDados(String result){
        if(result.trim().startsWith("GRAVOU-CHECKLIST")){
            CheckListCTR checkListCTR = new CheckListCTR();
            checkListCTR.delChecklist();
        }
        else if (result.trim().startsWith("BOLABERTOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            motoMecFertCTR.updBolAberto(result);
        }
        else if (result.trim().startsWith("BOLFECHADOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            motoMecFertCTR.delBolFechado(result);
        }
        else if (result.trim().startsWith("LOGERRO")) {
            ConfigCTR configCTR = new ConfigCTR();
            configCTR.updLogErro(result);
        }
        else if (result.trim().startsWith("GRAVOU-CARREGINSUMO")) {
            CompostoCTR compostoCTR = new CompostoCTR();
            compostoCTR.updCarregInsumo(result);
        }
        else if (result.trim().startsWith("GRAVOU-CARREGCOMPOSTO")) {
            CompostoCTR compostoCTR = new CompostoCTR();
            Log.i("ECM", "RECEBIMENTO 1 ");
            compostoCTR.updCarregComposto(result);
        }
        else if(result.trim().startsWith("PRECEC")){
            CECCTR cecCTR = new CECCTR();
            cecCTR.updPreCEC(result);
        }
        else {
            status = 1;
            Log.i("ECM", "ERRO 2");
            LogErroDAO.getInstance().insert(result);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}