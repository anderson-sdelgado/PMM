package br.com.usinasantafe.pmm.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.control.ApontMMMovLeiraCTR;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;

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

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void enviarChecklist() {

        CheckListCTR checkListCTR = new CheckListCTR();
        String dados = checkListCTR.dadosEnvio();

        Log.i("PMM", "CHECKLIST = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInserirCheckList()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
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

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAbertosMM() {

        BoletimCTR boletimCTR = new BoletimCTR();
        String dados = boletimCTR.dadosEnvioBolAbertoMMSemEnvio();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAbertoMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioApontMM() {

        ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
        String dados = apontMMMovLeiraCTR.dadosEnvioApontMM();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApontaMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    public void enviarBolFechadosFert() {

        BoletimCTR boletimCTR = new BoletimCTR();
        String dados = boletimCTR.dadosEnvioBolFechadoFert();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechadoFert()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAbertosFert() {

        BoletimCTR boletimCTR = new BoletimCTR();
        String dados = boletimCTR.dadosEnvioBolAbertoFertSemEnvio();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAbertoFert()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioApontaFert() {

        ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
        String dados = apontMMMovLeiraCTR.dadosEnvioApontFert();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApontaFert()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public boolean verifChecklist() {
        CheckListCTR checkListCTR = new CheckListCTR();
        return checkListCTR.verEnvioDados();
    }

    public Boolean verifBolFechadoMM() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.verEnvioDadosBolFechMM();
    }

    public Boolean verifBolAbertoSemEnvioMM() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.verEnvioDadosBolAbertoMM();
    }

    public Boolean verifApontMovLeiraMM() {
        ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
        return apontMMMovLeiraCTR.verEnvioDadosApontMM();
    }

    public Boolean verifBolFechadoFert() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.verEnvioDadosBolFechFert();
    }

    public Boolean verifBolAbertoSemEnvioFert() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.verEnvioDadosBolAbertoFert();
    }

    public Boolean verifApontaFert() {
        ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
        return apontMMMovLeiraCTR.verEnvioDadosApontFert();
    }

    public Boolean verifPerda() {
        boolean ret = false;
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElements()){
            if(configCTR.getVisDadosColhConfig() == 1){
                ret = true;
            }
        }
        return ret;
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

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

        if(verifPerda()){
            VerifDadosServ.getInstance().verDadosPerda();
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
        if ((!verifPerda())
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

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }
}