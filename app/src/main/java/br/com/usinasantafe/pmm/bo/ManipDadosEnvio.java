package br.com.usinasantafe.pmm.bo;

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

import br.com.usinasantafe.pmm.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.pmm.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.estaticas.RFuncaoAtivParTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.ImpleMMTO;
import br.com.usinasantafe.pmm.to.variaveis.RecolhFertTO;
import br.com.usinasantafe.pmm.to.variaveis.RendMMTO;
import br.com.usinasantafe.pmm.to.variaveis.RespItemCLTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;

public class ManipDadosEnvio {

    private static ManipDadosEnvio instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public ManipDadosEnvio() {

        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static ManipDadosEnvio getInstance() {
        if (instance == null) {
            instance = new ManipDadosEnvio();
        }
        return instance;
    }

    //////////////////////// SALVAR DADOS ////////////////////////////////////////////

    public void salvaCheckList() {

        CabecCLTO cabecCLTO = new CabecCLTO();
        List cabecCheckListList = cabecCLTO.get("statusCabCL", 1L);
        cabecCLTO = (CabecCLTO) cabecCheckListList.get(0);
        cabecCheckListList.clear();

        cabecCLTO.setStatusCabCL(2L);
        cabecCLTO.update();

        enviarChecklist();

    }

    public void salvaBoletimFechadoMM() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List listBoletim = boletimMMTO.get("statusBolMM", 1L);
        boletimMMTO = (BoletimMMTO) listBoletim.get(0);
        listBoletim.clear();

        boletimMMTO.setDthrFinalBolMM(Tempo.getInstance().datahora());
        boletimMMTO.setStatusBolMM(2L);
        boletimMMTO.update();

    }

    public void salvaBoletimAbertoMM(BoletimMMTO boletimMMTO, boolean checklist, Double latitude, Double longitude) {

        boletimMMTO.setDthrInicialBolMM(Tempo.getInstance().datahora());


        ConfigTO configTO = new ConfigTO();
        List listConfigTO = configTO.all();
        configTO = (ConfigTO) listConfigTO.get(0);
        listConfigTO.clear();

        if(!checklist) {

            boletimMMTO.setQtdeApontBolMM(0L);
            boletimMMTO.insert();

        }else{

            boletimMMTO.setQtdeApontBolMM(1L);
            boletimMMTO.insert();

            String datahora = Tempo.getInstance().datahora();
            ApontMMTO apontMMTO = new ApontMMTO();
            apontMMTO.setDthrApontMM(datahora);
            apontMMTO.setIdBolApontMM(boletimMMTO.getIdBolMM());
            apontMMTO.setIdExtBolApontMM(boletimMMTO.getIdExtBolMM());
            apontMMTO.setOsApontMM(boletimMMTO.getOsBolMM());
            apontMMTO.setAtivApontMM(boletimMMTO.getAtivPrincBolMM());

            RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
            ArrayList pesqList = new ArrayList();

            EspecificaPesquisa pesq1 = new EspecificaPesquisa();
            pesq1.setCampo("codFuncao");
            pesq1.setValor(1L);
            pesq1.setTipo(1);
            pesqList.add(pesq1);

            EspecificaPesquisa pesq2 = new EspecificaPesquisa();
            pesq2.setCampo("tipoFuncao");
            pesq2.setValor(2L);
            pesq2.setTipo(1);
            pesqList.add(pesq2);

            List rFuncaoAtivParList = rFuncaoAtivParTO.get(pesqList);
            rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(0);
            rFuncaoAtivParList.clear();

            apontMMTO.setParadaApontMM(rFuncaoAtivParTO.getIdAtivPar());
            apontMMTO.setTransbApontMM(0L);
            apontMMTO.setLatitudeApontMM(latitude);
            apontMMTO.setLongitudeApontMM(longitude);
            apontMMTO.setStatusConApontMM(configTO.getStatusConConfig());
            apontMMTO.setStatusApontMM(1L);
            apontMMTO.insert();

            BackupApontaTO backupApontaTO = new BackupApontaTO();
            backupApontaTO.setDthrAponta(datahora);
            backupApontaTO.setOsAponta(boletimMMTO.getOsBolMM());
            backupApontaTO.setAtividadeAponta(boletimMMTO.getAtivPrincBolMM());
            backupApontaTO.setParadaAponta(rFuncaoAtivParTO.getIdAtivPar());
            backupApontaTO.setTransbAponta(0L);
            backupApontaTO.insert();

            ImpleMMTO impleMMTO = new ImpleMMTO();
            List implementoList = impleMMTO.get("idApontImpleMM", 0L);

            for (int i = 0; i < implementoList.size(); i++) {
                impleMMTO = (ImpleMMTO) implementoList.get(i);
                ImpleMMTO cadImplTO = new ImpleMMTO();
                cadImplTO.setIdApontImpleMM(apontMMTO.getIdApontMM());
                cadImplTO.setCodEquipImpleMM(impleMMTO.getCodEquipImpleMM());
                cadImplTO.setPosImpleMM(impleMMTO.getPosImpleMM());
                cadImplTO.setDthrImpleMM(datahora);
                cadImplTO.insert();
            }

            configTO.setUltTurnoCLConfig(boletimMMTO.getIdTurnoBolMM());
            configTO.setDtUltCLConfig(Tempo.getInstance().dataSHora());
            configTO.setDtUltApontConfig(datahora);
            configTO.update();

        }

    }

    public void salvaApontaMM(ApontMMTO apontMMTO) {

        String datahora = Tempo.getInstance().datahora();
        apontMMTO.setDthrApontMM(datahora);

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List bolList = boletimMMTO.get("statusBolMM", 1L);
        boletimMMTO = (BoletimMMTO) bolList.get(0);
        boletimMMTO.setQtdeApontBolMM(boletimMMTO.getQtdeApontBolMM() + 1L);
        boletimMMTO.update();
        bolList.clear();

        apontMMTO.setIdBolApontMM(boletimMMTO.getIdBolMM());
        apontMMTO.setIdExtBolApontMM(boletimMMTO.getIdExtBolMM());
        apontMMTO.setStatusApontMM(1L);
        apontMMTO.insert();

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        backupApontaTO.setDthrAponta(apontMMTO.getDthrApontMM());
        backupApontaTO.setOsAponta(apontMMTO.getOsApontMM());
        backupApontaTO.setAtividadeAponta(apontMMTO.getAtivApontMM());
        backupApontaTO.setParadaAponta(apontMMTO.getParadaApontMM());
        backupApontaTO.setTransbAponta(apontMMTO.getTransbApontMM());
        backupApontaTO.insert();

        ImpleMMTO impleMMTO = new ImpleMMTO();
        List implementoList = impleMMTO.get("idApontImpleMM", 0L);

        List apontaList = apontMMTO.get("dthrApontMM", datahora);
        apontMMTO = (ApontMMTO) apontaList.get(0);

        for (int i = 0; i < implementoList.size(); i++) {
            impleMMTO = (ImpleMMTO) implementoList.get(i);
            ImpleMMTO cadImplTO = new ImpleMMTO();
            cadImplTO.setIdApontImpleMM(apontMMTO.getIdApontMM());
            cadImplTO.setCodEquipImpleMM(impleMMTO.getCodEquipImpleMM());
            cadImplTO.setPosImpleMM(impleMMTO.getPosImpleMM());
            cadImplTO.setDthrImpleMM(datahora);
            cadImplTO.insert();
        }

        envioDadosPrinc();

    }

    public void salvaBoletimFechadoFert() {

        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.get("statusBolFert", 1L);
        boletimFertTO = (BoletimFertTO) boletimFertList.get(0);
        boletimFertList.clear();

        boletimFertTO.setDthrFinalBolFert(Tempo.getInstance().datahora());
        boletimFertTO.setStatusBolFert(2L);
        boletimFertTO.update();

    }

    public void salvaBoletimAbertoFert(BoletimFertTO boletimFertTO, boolean checklist, Double latitude, Double longitude) {

        boletimFertTO.setDthrInicialBolFert(Tempo.getInstance().datahora());
        boletimFertTO.insert();

        ConfigTO configTO = new ConfigTO();
        List listConfigTO = configTO.all();
        configTO = (ConfigTO) listConfigTO.get(0);
        listConfigTO.clear();

        if(checklist){

            String datahora = Tempo.getInstance().datahora();
            ApontFertTO apontFertTO = new ApontFertTO();
            apontFertTO.setDthrApontFert(datahora);
            apontFertTO.setIdBolApontFert(boletimFertTO.getIdBolFert());
            apontFertTO.setIdExtBolApontFert(boletimFertTO.getIdExtBolFert());
            apontFertTO.setOsApontFert(boletimFertTO.getOsBolFert());
            apontFertTO.setAtivApontFert(boletimFertTO.getAtivPrincBolFert());

            RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
            ArrayList pesqList = new ArrayList();

            EspecificaPesquisa pesq1 = new EspecificaPesquisa();
            pesq1.setCampo("codFuncao");
            pesq1.setValor(1L);
            pesq1.setTipo(1);
            pesqList.add(pesq1);

            EspecificaPesquisa pesq2 = new EspecificaPesquisa();
            pesq2.setCampo("tipoFuncao");
            pesq2.setValor(2L);
            pesq2.setTipo(1);
            pesqList.add(pesq2);

            List rFuncaoAtivParList = rFuncaoAtivParTO.get(pesqList);
            rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(0);
            rFuncaoAtivParList.clear();

            apontFertTO.setParadaApontFert(rFuncaoAtivParTO.getIdAtivPar());
            apontFertTO.setBocalApontFert(0L);
            apontFertTO.setPressaoApontFert(0D);
            apontFertTO.setVelocApontFert(0L);
            apontFertTO.setLatitudeApontFert(latitude);
            apontFertTO.setLongitudeApontFert(longitude);
            apontFertTO.setStatusConApontFert(configTO.getStatusConConfig());
            apontFertTO.setStatusApontFert(1L);
            apontFertTO.insert();

            BackupApontaTO backupApontaTO = new BackupApontaTO();
            backupApontaTO.setDthrAponta(datahora);
            backupApontaTO.setOsAponta(boletimFertTO.getOsBolFert());
            backupApontaTO.setAtividadeAponta(boletimFertTO.getAtivPrincBolFert());
            backupApontaTO.setParadaAponta(rFuncaoAtivParTO.getIdAtivPar());
            backupApontaTO.setPressaoAponta(0D);
            backupApontaTO.setVelocAponta(0L);
            backupApontaTO.setBocalAponta(0L);
            backupApontaTO.insert();

            configTO.setUltTurnoCLConfig(boletimFertTO.getIdTurnoBolFert());
            configTO.setDtUltCLConfig(Tempo.getInstance().dataSHora());
            configTO.setDtUltApontConfig(datahora);
            configTO.update();

        }

    }

    public void salvaApontaFert(ApontFertTO apontFertTO) {

        String datahora = Tempo.getInstance().datahora();
        apontFertTO.setDthrApontFert(datahora);

        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.get("statusBolFert", 1L);
        boletimFertTO = (BoletimFertTO) boletimFertList.get(0);
        boletimFertList.clear();

        apontFertTO.setIdBolApontFert(boletimFertTO.getIdBolFert());
        apontFertTO.setIdExtBolApontFert(boletimFertTO.getIdExtBolFert());
        apontFertTO.setStatusApontFert(1L);
        apontFertTO.insert();

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        backupApontaTO.setDthrAponta(apontFertTO.getDthrApontFert());
        backupApontaTO.setOsAponta(apontFertTO.getOsApontFert());
        backupApontaTO.setAtividadeAponta(apontFertTO.getAtivApontFert());
        backupApontaTO.setParadaAponta(apontFertTO.getParadaApontFert());
        backupApontaTO.setBocalAponta(apontFertTO.getBocalApontFert());
        backupApontaTO.setPressaoAponta(apontFertTO.getPressaoApontFert());
        backupApontaTO.setVelocAponta(apontFertTO.getVelocApontFert());
        backupApontaTO.insert();

        envioDadosPrinc();

    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void enviarChecklist() {

        CabecCLTO cabecCLTO = new CabecCLTO();
        List cabecCheckListLista = bolChecklist();

        JsonArray jsonArrayCabec = new JsonArray();
        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < cabecCheckListLista.size(); i++) {

            cabecCLTO = (CabecCLTO) cabecCheckListLista.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecCLTO, cabecCLTO.getClass()));

            RespItemCLTO respItemCLTO = new RespItemCLTO();
            List listaItem = respItemCLTO.get("idCabItCL", cabecCLTO.getIdCabCL());

            for (int j = 0; j < listaItem.size(); j++) {
                respItemCLTO = (RespItemCLTO) listaItem.get(j);
                Gson gsonItem = new Gson();
                jsonArrayItem.add(gsonItem.toJsonTree(respItemCLTO, respItemCLTO.getClass()));
            }

        }

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabecalho", jsonArrayCabec);

        JsonObject jsonItem = new JsonObject();
        jsonItem.add("item", jsonArrayItem);

        String dados = jsonCabec.toString() + "_" + jsonItem.toString();

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

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = bolFechadoMM();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();
        JsonArray jsonArrayRendimento = new JsonArray();

        for (int i = 0; i < boletimMMList.size(); i++) {

            boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));

            ApontMMTO apontMMTO = new ApontMMTO();

            ArrayList listaPesq = new ArrayList();
            EspecificaPesquisa pesquisa = new EspecificaPesquisa();
            pesquisa.setCampo("statusApontMM");
            pesquisa.setValor(1L);
            pesquisa.setTipo(1);
            listaPesq.add(pesquisa);

            EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
            pesquisa2.setCampo("idBolApontMM");
            pesquisa2.setValor(boletimMMTO.getIdBolMM());
            pesquisa2.setTipo(1);
            listaPesq.add(pesquisa2);

            List apontaMMList = apontMMTO.get(listaPesq);

            for (int j = 0; j < apontaMMList.size(); j++) {

                apontMMTO = (ApontMMTO) apontaMMList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontMMTO, apontMMTO.getClass()));

                ImpleMMTO impleMMTO = new ImpleMMTO();
                List implementoList = impleMMTO.get("idApontImpleMM", apontMMTO.getIdApontMM());

                for (int l = 0; l < implementoList.size(); l++) {
                    impleMMTO = (ImpleMMTO) implementoList.get(l);
                    Gson gsonItemImp = new Gson();
                    jsonArrayImplemento.add(gsonItemImp.toJsonTree(impleMMTO, impleMMTO.getClass()));
                }

                implementoList.clear();

            }

            apontaMMList.clear();

            RendMMTO rendMMTO = new RendMMTO();
            List rendList = rendMMTO.get("idBolRendMM", boletimMMTO.getIdBolMM());

            for (int j = 0; j < rendList.size(); j++) {
                rendMMTO = (RendMMTO) rendList.get(j);
                Gson gsonRend = new Gson();
                jsonArrayRendimento.add(gsonRend.toJsonTree(rendMMTO, rendMMTO.getClass()));
            }

            rendList.clear();

        }

        boletimMMList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("implemento", jsonArrayImplemento);

        JsonObject jsonRend = new JsonObject();
        jsonRend.add("rendimento", jsonArrayRendimento);

        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString() + "#" + jsonRend.toString();

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

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = bolAbertoSemEnvioMM();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();

        for (int i = 0; i < boletimMMList.size(); i++) {

            boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));

            ApontMMTO apontMMTO = new ApontMMTO();
            List apontaList = apontMMTO.get("idBolApontMM", boletimMMTO.getIdBolMM());

            for (int j = 0; j < apontaList.size(); j++) {

                apontMMTO = (ApontMMTO) apontaList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontMMTO, apontMMTO.getClass()));

                ImpleMMTO impleMMTO = new ImpleMMTO();
                List implementoList = impleMMTO.get("idApontImpleMM", apontMMTO.getIdApontMM());

                for (int l = 0; l < implementoList.size(); l++) {
                    impleMMTO = (ImpleMMTO) implementoList.get(l);
                    Gson gsonItemImp = new Gson();
                    jsonArrayImplemento.add(gsonItemImp.toJsonTree(impleMMTO, impleMMTO.getClass()));
                }

                implementoList.clear();

            }

            apontaList.clear();

        }

        boletimMMList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("implemento", jsonArrayImplemento);


        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString();

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

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();

        ApontMMTO apontMMTO = new ApontMMTO();
        List apontaList = apontMM();

        for (int i = 0; i < apontaList.size(); i++) {

            apontMMTO = (ApontMMTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontMMTO, apontMMTO.getClass()));

            ImpleMMTO impleMMTO = new ImpleMMTO();
            List implementoList = impleMMTO.get("idApontImpleMM", apontMMTO.getIdApontMM());

            for (int j = 0; j < implementoList.size(); j++) {
                impleMMTO = (ImpleMMTO) implementoList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayImplemento.add(gsonItem.toJsonTree(impleMMTO, impleMMTO.getClass()));
            }

            implementoList.clear();

        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("implemento", jsonArrayImplemento);

        String dados = jsonAponta.toString() + "_" + jsonImplemento.toString();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApontaMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    public void enviarBolFechadosFert() {

        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = bolFechadoFert();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayRecolhimento = new JsonArray();

        for (int i = 0; i < boletimFertList.size(); i++) {

            boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertTO, boletimFertTO.getClass()));

            ApontFertTO apontFertTO = new ApontFertTO();
            List apontaFertList = apontFertTO.get("idBolApontFert", boletimFertTO.getIdBolFert());

            for (int j = 0; j < apontaFertList.size(); j++) {

                apontFertTO = (ApontFertTO) apontaFertList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontFertTO, apontFertTO.getClass()));

            }

            apontaFertList.clear();

            RecolhFertTO recolhFertTO = new RecolhFertTO();
            List recolhimentoList = recolhFertTO.get("idBolRecolhFert", boletimFertTO.getIdBolFert());

            for (int j = 0; j < recolhimentoList.size(); j++) {
                recolhFertTO = (RecolhFertTO) recolhimentoList.get(j);
                Gson gsonRecol = new Gson();
                jsonArrayRecolhimento.add(gsonRecol.toJsonTree(recolhFertTO, recolhFertTO.getClass()));
            }

            recolhimentoList.clear();

        }

        boletimFertList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonRecolhimento = new JsonObject();
        jsonRecolhimento.add("recolhimento", jsonArrayRecolhimento);

        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonRecolhimento.toString();

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

        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = bolAbertoSemEnvioFert();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();

        for (int i = 0; i < boletimFertList.size(); i++) {

            boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertTO, boletimFertTO.getClass()));

            ApontFertTO apontFertTO = new ApontFertTO();

            ArrayList listaPesq = new ArrayList();
            EspecificaPesquisa pesquisa = new EspecificaPesquisa();
            pesquisa.setCampo("statusApontFert");
            pesquisa.setValor(2L);
            pesquisa.setTipo(1);
            listaPesq.add(pesquisa);

            EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
            pesquisa2.setCampo("idBolApontFert");
            pesquisa2.setValor(boletimFertTO.getIdBolFert());
            pesquisa2.setTipo(1);
            listaPesq.add(pesquisa2);

            List apontaList = apontFertTO.get(listaPesq);

            for (int j = 0; j < apontaList.size(); j++) {

                apontFertTO = (ApontFertTO) apontaList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontFertTO, apontFertTO.getClass()));

            }

            apontaList.clear();

        }

        boletimFertList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString();

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

        JsonArray jsonArrayAponta = new JsonArray();

        ApontFertTO apontFertTO = new ApontFertTO();
        List apontaList = apontFert();

        for (int i = 0; i < apontaList.size(); i++) {

            apontFertTO = (ApontFertTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontFertTO, apontFertTO.getClass()));

        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        String dados = jsonAponta.toString();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApontaFert()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    /////////////////////////////// DELETAR DADOS ///////////////////////////////////////////////

    public void delChecklist() {

        CabecCLTO cabecCLTO = new CabecCLTO();
        List cabecCheckList = cabecCLTO.get("statusCabCL", 2L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < cabecCheckList.size(); i++) {
            cabecCLTO = (CabecCLTO) cabecCheckList.get(i);
            rLista.add(cabecCLTO.getIdCabCL());
        }

        RespItemCLTO respItemCLTO = new RespItemCLTO();
        List respItemList = respItemCLTO.in("idCabItCL", rLista);

        for (int j = 0; j < respItemList.size(); j++) {
            respItemCLTO = (RespItemCLTO) respItemList.get(j);
            respItemCLTO.delete();
        }

        for (int i = 0; i < cabecCheckList.size(); i++) {
            cabecCLTO = (CabecCLTO) cabecCheckList.get(i);
            cabecCLTO.delete();
        }

    }

    public void updBolFechadoMM(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjBolMM = new JSONObject(objPrinc);
            JSONArray jsonArrayBolMM = jObjBolMM.getJSONArray("boletim");

            if (jsonArrayBolMM.length() > 0) {

                for (int i = 0; i < jsonArrayBolMM.length(); i++) {

                    JSONObject objBol = jsonArrayBolMM.getJSONObject(i);
                    Gson gsonBol = new Gson();
                    BoletimMMTO boletimMMTO = gsonBol.fromJson(objBol.toString(), BoletimMMTO.class);

                    List bolMMList = boletimMMTO.get("idBolMM", boletimMMTO.getIdBolMM());
                    BoletimMMTO boletimMMTOBD = (BoletimMMTO) bolMMList.get(0);
                    bolMMList.clear();

                    if(boletimMMTOBD.getQtdeApontBolMM() == boletimMMTO.getQtdeApontBolMM()){

                        ApontMMTO apontMMTO = new ApontMMTO();
                        List apontaList = apontMMTO.get("idBolApontMM", boletimMMTOBD.getIdBolMM());

                        for (int j = 0; j < apontaList.size(); j++) {

                            apontMMTO = (ApontMMTO) apontaList.get(j);
                            apontMMTO.delete();

                            ImpleMMTO impleMMTO = new ImpleMMTO();
                            List impleList = impleMMTO.get("idApontImpleMM", apontMMTO.getIdApontMM());

                            for (int l = 0; l < impleList.size(); l++) {
                                impleMMTO = (ImpleMMTO) impleList.get(l);
                                impleMMTO.delete();
                            }

                        }

                        apontaList.clear();

                        RendMMTO rendMMTO = new RendMMTO();
                        List rendList = rendMMTO.get("idBolRendMM", boletimMMTOBD.getIdBolMM());

                        for (int j = 0; j < rendList.size(); j++) {
                            rendMMTO = (RendMMTO) rendList.get(j);
                            rendMMTO.delete();
                        }

                        boletimMMTOBD.delete();

                    }

                }

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void updBolAbertoMM(String retorno){

        try{

            int pos1 = retorno.indexOf("_") + 1;
            int pos2 = retorno.indexOf("|") + 1;
            String objPrinc = retorno.substring(pos1, pos2);
            String objSeg = retorno.substring(pos2);

            JSONObject jObjBolMM = new JSONObject(objPrinc);
            JSONArray jsonArrayBolMM = jObjBolMM.getJSONArray("boletim");

            JSONObject objBol = jsonArrayBolMM.getJSONObject(0);
            Gson gsonBol = new Gson();
            BoletimMMTO boletimMMTO = gsonBol.fromJson(objBol.toString(), BoletimMMTO.class);

            List bolMMList = boletimMMTO.get("idBolMM", boletimMMTO.getIdBolMM());
            BoletimMMTO boletimMMTOBD = (BoletimMMTO) bolMMList.get(0);
            bolMMList.clear();

            boletimMMTOBD.setIdExtBolMM(boletimMMTO.getIdExtBolMM());
            boletimMMTOBD.update();

            JSONObject jObjApontMM = new JSONObject(objSeg);
            JSONArray jsonArrayApontMM = jObjApontMM.getJSONArray("apont");

            if (jsonArrayApontMM.length() > 0) {

                ArrayList<Long> rLista = new ArrayList<Long>();
                ApontMMTO apontMMTO = new ApontMMTO();

                for (int i = 0; i < jsonArrayApontMM.length(); i++) {

                    JSONObject objApont = jsonArrayApontMM.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontMMTO = gsonApont.fromJson(objApont.toString(), ApontMMTO.class);

                    rLista.add(apontMMTO.getIdApontMM());

                }

                List apontMMList = apontMMTO.in("idApontMM", rLista);

                for (int i = 0; i < apontMMList.size(); i++) {

                    apontMMTO = (ApontMMTO) apontMMList.get(0);
                    apontMMTO.setIdExtBolApontMM(boletimMMTO.getIdExtBolMM());
                    apontMMTO.setStatusApontMM(2L);
                    apontMMTO.update();

                }

                ImpleMMTO impleMMTO = new ImpleMMTO();
                List implementoList = impleMMTO.in("idApontImpleMM", rLista);

                for (int l = 0; l < implementoList.size(); l++) {
                    impleMMTO = (ImpleMMTO) implementoList.get(l);
                    impleMMTO.delete();
                }

                rLista.clear();

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void updApontMM(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjApontMM = new JSONObject(objPrinc);
            JSONArray jsonArrayApontMM = jObjApontMM.getJSONArray("apont");

            if (jsonArrayApontMM.length() > 0) {

                ArrayList<Long> rLista = new ArrayList<Long>();
                ApontMMTO apontMMTO = new ApontMMTO();

                for (int i = 0; i < jsonArrayApontMM.length(); i++) {

                    JSONObject objApont = jsonArrayApontMM.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontMMTO = gsonApont.fromJson(objApont.toString(), ApontMMTO.class);

                    rLista.add(apontMMTO.getIdApontMM());

                }

                List apontMMList = apontMMTO.in("idApontMM", rLista);

                for (int i = 0; i < apontMMList.size(); i++) {

                    apontMMTO = (ApontMMTO) apontMMList.get(0);
                    apontMMTO.setStatusApontMM(2L);
                    apontMMTO.update();

                }

                ImpleMMTO impleMMTO = new ImpleMMTO();
                List implementoList = impleMMTO.in("idApontImpleMM", rLista);

                for (int l = 0; l < implementoList.size(); l++) {
                    impleMMTO = (ImpleMMTO) implementoList.get(l);
                    impleMMTO.delete();
                }

                rLista.clear();

            }

        }
        catch(Exception e){
            Log.i("PMM", "FALHA = " + e.toString());
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void delBolFechadoFert() {

        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.get("statusBolFert", 2L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < boletimFertList.size(); i++) {
            boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
            rLista.add(boletimFertTO.getIdBolFert());
        }

        ApontFertTO apontFertTO = new ApontFertTO();
        List apontaList = apontFertTO.in("idBolApontFert", rLista);

        for (int j = 0; j < apontaList.size(); j++) {

            apontFertTO = (ApontFertTO) apontaList.get(j);
            apontFertTO.delete();

        }

        RecolhFertTO recolhFertTO = new RecolhFertTO();
        List recolhimentoList = recolhFertTO.in("idBolRecolhFert", rLista);

        for (int j = 0; j < recolhimentoList.size(); j++) {
            recolhFertTO = (RecolhFertTO) recolhimentoList.get(j);
            recolhFertTO.delete();
        }

        for (int i = 0; i < boletimFertList.size(); i++) {
            boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
            boletimFertTO.delete();
        }

    }

    public void atualDelBoletimFert(String retorno){

        try{

            int pos1 = retorno.indexOf("=") + 1;
            int pos2 = retorno.indexOf("_") + 1;
            String id = retorno.substring(pos1, (pos2 - 1));

            BoletimFertTO boletimFertTO = new BoletimFertTO();
            List boletimFertList = boletimFertTO.get("statusBolFert", 1L);
            boletimFertTO = (BoletimFertTO) boletimFertList.get(0);
            boletimFertTO.setIdExtBolFert(Long.parseLong(id.trim()));
            boletimFertTO.update();

            ApontFertTO apontFertTO = new ApontFertTO();
            List apontaList = apontFertTO.get("idBolApontFert", boletimFertTO.getIdBolFert());

            for (int j = 0; j < apontaList.size(); j++) {

                apontFertTO = (ApontFertTO) apontaList.get(j);
                apontFertTO.delete();

            }


        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void delApontaFert() {

        ApontFertTO apontFertTO = new ApontFertTO();
        List apontaList = apontFertTO.all();

        for (int j = 0; j < apontaList.size(); j++) {

            apontFertTO = (ApontFertTO) apontaList.get(j);
            apontFertTO.delete();

        }

    }

    //////////////////////////TRAZER DADOS////////////////////////////

    public List bolChecklist(){
        CabecCLTO cabecCLTO = new CabecCLTO();
        return cabecCLTO.get("statusCabCL", 2L);
    }

    public List bolFechadoMM() {
        BoletimMMTO boletimMMTO = new BoletimMMTO();
        return boletimMMTO.get("statusBolMM", 2L);
    }

    public List bolAbertoSemEnvioMM() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMM");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBolMM");
        pesquisa2.setValor(0);
        pesquisa2.setTipo(1);
        listaPesq.add(pesquisa2);

        return boletimMMTO.get(listaPesq);

    }

    public List apontMM() {
        ApontMMTO apontMMTO = new ApontMMTO();
        return apontMMTO.get("statusApontMM", 1L);
    }

    public List bolFechadoFert() {
        BoletimFertTO boletimFertTO = new BoletimFertTO();
        return boletimFertTO.get("statusBolFert", 2L);
    }

    public List bolAbertoSemEnvioFert() {

        BoletimFertTO boletimFertTO = new BoletimFertTO();

        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBolFert");
        pesquisa2.setValor(0);
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        return boletimFertTO.get(pesqList);

    }

    public List apontFert() {
        ApontFertTO apontFertTO = new ApontFertTO();
        return apontFertTO.get("statusApontFert", 1L);
    }

    public List dadosPerda(){
        ConfigTO configTO = new ConfigTO();
        return  configTO.get("visDadosConfig", 0L);
    }

    public boolean bolAberto(){

        boolean ver = false;
        BoletimMMTO boletimMMTO = new BoletimMMTO();
        if(boletimMMTO.get("statusBolMM", 1L).size() > 0){
            ver = true;
        }
        BoletimFertTO boletimFertTO = new BoletimFertTO();
        if(boletimFertTO.get("statusBolFert", 1L).size() > 0){
            ver = true;
        }
        return ver;

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public boolean verifChecklist() {
        return bolChecklist().size() > 0;
    }

    public Boolean verifBolFechadoMM() {
        return bolFechadoMM().size() > 0;
    }

    public Boolean verifBolAbertoSemEnvioMM() {
        return bolAbertoSemEnvioMM().size() > 0;
    }

    public Boolean verifApontMM() { return apontMM().size() > 0; }

    public Boolean verifBolFechadoFert() {
        return bolFechadoFert().size() > 0;
    }

    public Boolean verifBolAbertoSemEnvioFert() {
        return bolAbertoSemEnvioFert().size() > 0;
    }

    public Boolean verifApontaFert() { return apontFert().size() > 0; }

    public Boolean verifPerda() {
        if((dadosPerda().size() > 0) && (bolAberto())){
            return true;
        }
        else{
            return false;
        }
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
            ManipDadosVerif.getInstance().verDadosPerda();
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