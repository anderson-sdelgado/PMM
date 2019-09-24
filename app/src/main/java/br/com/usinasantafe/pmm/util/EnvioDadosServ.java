package br.com.usinasantafe.pmm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.control.ApontCTR;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.estaticas.ImpleMMTO;
import br.com.usinasantafe.pmm.to.variaveis.RecolhFertTO;
import br.com.usinasantafe.pmm.to.variaveis.RendMMTO;
import br.com.usinasantafe.pmm.to.variaveis.RespItemCLTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;

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

        ApontCTR apontCTR = new ApontCTR();
        String dados = apontCTR.dadosEnvioApontMM();

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

        ApontCTR apontCTR = new ApontCTR();
        String dados = apontCTR.dadosEnvioApontFert();

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
        return checkListCTR.bolFechList().size() > 0;
    }

    public Boolean verifBolFechadoMM() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.bolFechMMList().size() > 0;
    }

    public Boolean verifBolAbertoSemEnvioMM() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.bolAbertoSemEnvioMMList().size() > 0;
    }

    public Boolean verifApontMM() {
        ApontCTR apontCTR = new ApontCTR();
        return apontCTR.apontAbertoMMList().size() > 0; }

    public Boolean verifBolFechadoFert() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.bolFechFertList().size() > 0;
    }

    public Boolean verifBolAbertoSemEnvioFert() {
        BoletimCTR boletimCTR = new BoletimCTR();
        return boletimCTR.bolAbertoSemEnvioFertList().size() > 0;
    }

    public Boolean verifApontaFert() {
        ApontCTR apontCTR = new ApontCTR();
        return apontCTR.apontAbertoFertList().size() > 0;
    }

    public Boolean verifPerda() {
        ConfigCTR configCTR = new ConfigCTR();
        return configCTR.getVisDadosColhConfig() == 1;
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
                        if (verifApontMM()) {
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
                && (!verifApontMM())
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