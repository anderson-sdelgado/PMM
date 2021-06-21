package br.com.usinasantafe.pmm.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pmm.control.CECCTR;
import br.com.usinasantafe.pmm.control.CompostoCTR;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.pmm.control.MotoMecFertCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.util.conHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

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

    public void envioCarregInsumo() {

        CompostoCTR compostoCTR = new CompostoCTR();
        String dados = compostoCTR.dadosEnvioCarregInsumo();

        String[] url = {urlsConexaoHttp.getsInsertCarregInsumo()};
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

    public boolean verifEnviaCarregInsumo() {
        CompostoCTR compostoCTR = new CompostoCTR();
        return compostoCTR.verEnviaCarregInsumo();
    }

    public boolean verifEnvioLeiraDescarreg() {
        CompostoCTR compostoCTR = new CompostoCTR();
        return compostoCTR.verEnvioLeiraDescarreg();
    }

    public boolean verifPreCEC() {
        CECCTR cecCTR = new CECCTR();
        return cecCTR.verPreCECFechado();
    }

    public Boolean verifBolFechadoMMFert() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.verEnvioBolFech();
    }

    public Boolean verifApontMMFert() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.verEnvioApont();
    }

    public Boolean verifLogErro() {
        ConfigCTR configCTR = new ConfigCTR();
        return configCTR.verEnvioLogErro();
    }

    public Boolean verifInfor() {
        boolean ret = false;
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElements()){
            if(configCTR.getVerInforConfig() == 1){
                ret = true;
            }
        }
        return ret;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc() {

        if (verifLogErro()) {
            envioLogErro();
        }
        else {
            if(verifInfor()){
                VerifDadosServ.getInstance().verDadosInfor();
            } else {
                if (verifChecklist()) {
                    enviarChecklist();
                } else {
                    if (verifEnviaCarregInsumo()) {
                        envioCarregInsumo();
                    } else {
                        if (verifEnvioLeiraDescarreg()) {
                            envioLeiraDescarreg();
                        } else {
                            if (verifPreCEC()) {
                                envioPreCEC();
                            }
                            else {
                                if (verifBolFechadoMMFert()) {
                                    enviarBolFechadoMMFert();
                                } else {
                                    if (verifApontMMFert()) {
                                        enviarBolAbertoMMFert();
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
        if ((!verifInfor())
                && (!verifBolFechadoMMFert())
                && (!verifEnviaCarregInsumo())
                && (!verifEnvioLeiraDescarreg())
                && (!verifPreCEC())
                && (!verifApontMMFert())
                && (!verifChecklist())
                && (!verifLogErro())){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void recDados(String result){
        if(result.trim().startsWith("GRAVOU-CHECKLIST")){
            CheckListCTR checkListCTR = new CheckListCTR();
            checkListCTR.delChecklist();
        } else if (result.trim().startsWith("BOLABERTOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            motoMecFertCTR.updateBolAberto(result);
        } else if (result.trim().startsWith("BOLFECHADOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            motoMecFertCTR.deleteBolFechado(result);
        } else if (result.trim().startsWith("LOGERRO")) {
            ConfigCTR configCTR = new ConfigCTR();
            configCTR.updLogErro(result);
        } else if (result.trim().startsWith("GRAVOU-CARREGINSUMO")) {
            CompostoCTR compostoCTR = new CompostoCTR();
            compostoCTR.updateCarregInsumo(result);
        } else if (result.trim().startsWith("GRAVOU-LEIRADESCARREG")) {
            CompostoCTR compostoCTR = new CompostoCTR();
            compostoCTR.updCarregLeiraDescarreg(result);
        } else if(result.trim().startsWith("PRECEC")){
            CECCTR cecCTR = new CECCTR();
            cecCTR.atualPreCEC(result);
        } else{
            LogErroDAO.getInstance().insert(result);
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }
}