package br.com.usinasantafe.pmm.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pmm.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.pmm.control.ApontCTR;
import br.com.usinasantafe.pmm.control.BoletimCTR;
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

    public void enviarBolFechadosMM() {

        BoletimCTR boletimCTR = new BoletimCTR();
        String dados = boletimCTR.dadosEnvioBolFechadoMM();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechadoMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAbertosMM() {

        BoletimCTR boletimCTR = new BoletimCTR();
        String dados = boletimCTR.dadosEnvioBolAbertoMM();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAbertoMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioApontMM() {

        ApontCTR apontCTR = new ApontCTR();
        String dados = apontCTR.dadosEnvioApontMM();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApontaMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    public void enviarBolFechadosFert() {

        BoletimCTR boletimCTR = new BoletimCTR();
        String dados = boletimCTR.dadosEnvioBolFechadoFert();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechadoFert()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAbertosFert() {

        BoletimCTR boletimCTR = new BoletimCTR();
        String dados = boletimCTR.dadosEnvioBolAbertoFert();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAbertoFert()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioApontaFert() {

        ApontCTR apontCTR = new ApontCTR();
        String dados = apontCTR.dadosEnvioApontFert();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApontaFert()};
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

    public Boolean verifBolFechadoMM() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.verEnvioBolFechMM();
    }

    public Boolean verifBolAbertoSemEnvioMM() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.verEnvioBolAbertoMM();
    }

    public Boolean verifApontMovLeiraMM() {
        ApontCTR apontCTR = new ApontCTR();
        return apontCTR.verEnvioDadosApontMM();
    }

    public Boolean verifBolFechadoFert() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.verEnvioBolFechFert();
    }

    public Boolean verifBolAbertoSemEnvioFert() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.verEnvioBolAbertoFert();
    }

    public Boolean verifApontaFert() {
        ApontCTR apontCTR = new ApontCTR();
        return apontCTR.verEnvioDadosApontFert();
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

        if(verifInfor()){
            VerifDadosServ.getInstance().verDadosInfor();
        }
        else {
            if (verifChecklist()) {
                enviarChecklist();
            }
            else {
                if (verifBolFechadoMM()) {
                    enviarBolFechadosMM();
                }
                else {
                    if (verifBolAbertoSemEnvioMM()) {
                        enviarBolAbertosMM();
                    }
                    else {
                        if (verifApontMovLeiraMM()) {
                            envioApontMM();
                        }
                        else{
                            if (verifBolFechadoFert()) {
                                enviarBolFechadosFert();
                            }
                            else {
                                if (verifBolAbertoSemEnvioFert()) {
                                    enviarBolAbertosFert();
                                }
                                else {
                                    if (verifApontaFert()) {
                                        envioApontaFert();
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
                && (!verifBolFechadoMM())
                && (!verifBolAbertoSemEnvioMM())
                && (!verifApontMovLeiraMM())
                && (!verifChecklist())
                && (!verifBolFechadoFert())
                && (!verifBolAbertoSemEnvioFert())
                && (!verifApontaFert())){
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
            BoletimCTR boletimCTR = new BoletimCTR();
            boletimCTR.updBolAbertoMM(result);
        } else if (result.trim().contains("BOLFECHADOMM")) {
            BoletimCTR boletimCTR = new BoletimCTR();
            boletimCTR.delBolFechadoMM(result);
        } else if (result.trim().contains("APONTMM")) {
            ApontCTR apontCTR = new ApontCTR();
            apontCTR.updateApontMM(result);
        } else if (result.trim().contains("BOLABERTOFERT")) {
            BoletimCTR boletimCTR = new BoletimCTR();
            boletimCTR.updBolAbertoFert(result);
        } else if (result.trim().contains("BOLFECHADOFERT")) {
            BoletimCTR boletimCTR = new BoletimCTR();
            boletimCTR.delBolFechadoFert(result);
        } else if (result.trim().contains("APONTFERT")) {
            ApontCTR apontCTR = new ApontCTR();
            apontCTR.updateApontaFert(result);
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