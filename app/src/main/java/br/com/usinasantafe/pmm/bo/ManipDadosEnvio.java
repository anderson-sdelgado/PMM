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

import br.com.usinasantafe.pmm.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.pmm.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafProdPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimPneuTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ImplementoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ItemMedPneuTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RendimentoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RespItemCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;

public class ManipDadosEnvio {

    private static ManipDadosEnvio instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private List listDatasFrenteTO;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public ManipDadosEnvio() {
        // TODO Auto-generated constructor stub
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

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        List cabecCheckListLista = cabecCheckListTO.get("statusCab", 1L);
        cabecCheckListTO = (CabecCheckListTO) cabecCheckListLista.get(0);
        cabecCheckListLista.clear();

        cabecCheckListTO.setStatusCab(2L);
        cabecCheckListTO.update();

        enviarChecklist();

    }

    public void salvaBoletimFechadoMM() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List listBoletim = boletimMMTO.get("statusBoletim", 1L);
        boletimMMTO = (BoletimMMTO) listBoletim.get(0);
        listBoletim.clear();

        boletimMMTO.setDthrFimBoletim(Tempo.getInstance().datahora());
        boletimMMTO.setStatusBoletim(2L);
        boletimMMTO.update();

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        backupApontaTO.deleteAll();

    }

    public void salvaBoletimAbertoMM(BoletimMMTO boletimMMTO, boolean checklist, Double latitude, Double longitude) {

        boletimMMTO.setDthrInicioBoletim(Tempo.getInstance().datahora());
        boletimMMTO.insert();

        ConfiguracaoTO configTO = new ConfiguracaoTO();
        List listConfigTO = configTO.all();
        configTO = (ConfiguracaoTO) listConfigTO.get(0);
        listConfigTO.clear();

        if(checklist){

            String datahora = Tempo.getInstance().datahora();
            ApontaMMTO apontaMMTO = new ApontaMMTO();
            apontaMMTO.setDthrAponta(datahora);
            apontaMMTO.setIdBolAponta(boletimMMTO.getIdBoletim());
            apontaMMTO.setIdExtBolAponta(boletimMMTO.getIdExtBoletim());
            apontaMMTO.setOsAponta(boletimMMTO.getOsBoletim());
            apontaMMTO.setAtividadeAponta(boletimMMTO.getAtivPrincBoletim());

            ParadaTO paradaTO = new ParadaTO();
            List paradaList = paradaTO.get("flagCheckList",1L);
            paradaTO = (ParadaTO) paradaList.get(0);
            paradaList.clear();

            apontaMMTO.setParadaAponta(paradaTO.getIdParada());
            apontaMMTO.setTransbordoAponta(0L);
            apontaMMTO.setLatitudeAponta(latitude);
            apontaMMTO.setLongitudeAponta(longitude);
            apontaMMTO.setStatusConAponta(configTO.getStatusConConfig());
            apontaMMTO.setStatusAponta(2L);
            apontaMMTO.insert();

            BackupApontaTO backupApontaTO = new BackupApontaTO();
            backupApontaTO.setDthrAponta(datahora);
            backupApontaTO.setOsAponta(boletimMMTO.getOsBoletim());
            backupApontaTO.setAtividadeAponta(boletimMMTO.getAtivPrincBoletim());
            backupApontaTO.setParadaAponta(180L);
            backupApontaTO.setTransbAponta(0L);
            backupApontaTO.insert();

            ImplementoTO implementoTO = new ImplementoTO();
            List implementoList = implementoTO.get("idApontImplemento", 0L);

            for (int i = 0; i < implementoList.size(); i++) {
                implementoTO = (ImplementoTO) implementoList.get(i);
                ImplementoTO cadImplTO = new ImplementoTO();
                cadImplTO.setIdApontImplemento(apontaMMTO.getIdAponta());
                cadImplTO.setCodEquipImplemento(implementoTO.getCodEquipImplemento());
                cadImplTO.setPosImplemento(implementoTO.getPosImplemento());
                cadImplTO.setDthrImplemento(datahora);
                cadImplTO.insert();
            }


            configTO.setUltTurnoCLConfig(boletimMMTO.getCodTurnoBoletim());
            configTO.setDtUltCLConfig(Tempo.getInstance().dataSHora());
            configTO.setDtUltApontConfig(datahora);
            configTO.update();

        }

    }

    public void salvaApontaMM(ApontaMMTO apontaMMTO, Long status) {

        String datahora = Tempo.getInstance().datahora();
        apontaMMTO.setDthrAponta(datahora);

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List lBol = boletimMMTO.get("statusBoletim", 1L);
        boletimMMTO = (BoletimMMTO) lBol.get(0);
        lBol.clear();

        apontaMMTO.setIdBolAponta(boletimMMTO.getIdBoletim());
        apontaMMTO.setIdExtBolAponta(boletimMMTO.getIdExtBoletim());
        apontaMMTO.setStatusAponta(status);
        apontaMMTO.insert();

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        backupApontaTO.setDthrAponta(apontaMMTO.getDthrAponta());
        backupApontaTO.setOsAponta(apontaMMTO.getOsAponta());
        backupApontaTO.setAtividadeAponta(apontaMMTO.getAtividadeAponta());
        backupApontaTO.setParadaAponta(apontaMMTO.getParadaAponta());
        backupApontaTO.setTransbAponta(apontaMMTO.getTransbordoAponta());
        backupApontaTO.insert();

        ImplementoTO implementoTO = new ImplementoTO();
        List implementoList = implementoTO.get("idApontImplemento", 0L);

        List apontaList = apontaMMTO.get("dthrAponta", datahora);
        apontaMMTO = (ApontaMMTO) apontaList.get(0);

        for (int i = 0; i < implementoList.size(); i++) {
            implementoTO = (ImplementoTO) implementoList.get(i);
            ImplementoTO cadImplTO = new ImplementoTO();
            cadImplTO.setIdApontImplemento(apontaMMTO.getIdAponta());
            cadImplTO.setCodEquipImplemento(implementoTO.getCodEquipImplemento());
            cadImplTO.setPosImplemento(implementoTO.getPosImplemento());
            cadImplTO.setDthrImplemento(datahora);
            cadImplTO.insert();
        }

        envioDadosPrinc();

    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void enviarChecklist() {

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        List cabecCheckListLista = boletinsChecklist();

        JsonArray jsonArrayCabec = new JsonArray();
        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < cabecCheckListLista.size(); i++) {

            cabecCheckListTO = (CabecCheckListTO) cabecCheckListLista.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecCheckListTO, cabecCheckListTO.getClass()));

            RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();
            List listaItem = respItemCheckListTO.get("idCabIt", cabecCheckListTO.getIdCab());

            for (int j = 0; j < listaItem.size(); j++) {

                respItemCheckListTO = (RespItemCheckListTO) listaItem.get(j);
                Gson gsonItem = new Gson();
                jsonArrayItem.add(gsonItem.toJsonTree(respItemCheckListTO, respItemCheckListTO.getClass()));

            }

        }

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabecalho", jsonArrayCabec);

        JsonObject jsonItem = new JsonObject();
        jsonItem.add("item", jsonArrayItem);

        String dados = jsonCabec.toString() + "_" + jsonItem.toString();

        Log.i("PMM", "CHECKLIST = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsApontChecklist()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolFechados() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = boletinsFechado();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();
        JsonArray jsonArrayRendimento = new JsonArray();
        JsonArray jsonArrayBolPneu = new JsonArray();
        JsonArray jsonArrayItemPneu = new JsonArray();

        for (int i = 0; i < boletimMMList.size(); i++) {

            boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));

            ApontaMMTO apontaMMTO = new ApontaMMTO();
            List apontaMMList = apontaMMTO.get("idBolAponta", boletimMMTO.getIdBoletim());

            for (int j = 0; j < apontaMMList.size(); j++) {

                apontaMMTO = (ApontaMMTO) apontaMMList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontaMMTO, apontaMMTO.getClass()));

                ImplementoTO implementoTO = new ImplementoTO();
                List implementoList = implementoTO.get("idApontImplemento", apontaMMTO.getIdAponta());

                for (int l = 0; l < implementoList.size(); l++) {
                    implementoTO = (ImplementoTO) implementoList.get(l);
                    Gson gsonItemImp = new Gson();
                    jsonArrayImplemento.add(gsonItemImp.toJsonTree(implementoTO, implementoTO.getClass()));
                }

                implementoList.clear();

                BoletimPneuTO boletimPneuTO = new BoletimPneuTO();
                List boletimPneuList = boletimPneuTO.get("idApontBolPneu", apontaMMTO.getIdAponta());

                for (int l = 0; l < boletimPneuList.size(); l++) {

                    boletimPneuTO = (BoletimPneuTO) boletimPneuList.get(l);
                    Gson gsonBItem = new Gson();
                    jsonArrayBolPneu.add(gsonBItem.toJsonTree(boletimPneuTO, boletimPneuTO.getClass()));

                    ItemMedPneuTO itemMedPneuTO = new ItemMedPneuTO();
                    List itemMedPneuList = itemMedPneuTO.get("idBolItemMedPneu", boletimPneuTO.getIdBolPneu());

                    for (int m = 0; m < itemMedPneuList.size(); m++) {

                        itemMedPneuTO = (ItemMedPneuTO) itemMedPneuList.get(m);
                        Gson gsonItemPneu = new Gson();
                        jsonArrayItemPneu.add(gsonItemPneu.toJsonTree(itemMedPneuTO, itemMedPneuTO.getClass()));

                    }

                    itemMedPneuList.clear();

                }

                boletimPneuList.clear();

            }

            apontaMMList.clear();

            RendimentoTO rendimentoTO = new RendimentoTO();
            List rendList = rendimentoTO.get("idBolRendimento", boletimMMTO.getIdBoletim());

            for (int j = 0; j < rendList.size(); j++) {

                rendimentoTO = (RendimentoTO) rendList.get(j);
                Gson gsonRend = new Gson();
                jsonArrayRendimento.add(gsonRend.toJsonTree(rendimentoTO, rendimentoTO.getClass()));

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

        JsonObject jsonBolPneu = new JsonObject();
        jsonBolPneu.add("bolpneu", jsonArrayBolPneu);

        JsonObject jsonItemPneu = new JsonObject();
        jsonItemPneu.add("itempneu", jsonArrayItemPneu);

        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString() + "#" + jsonRend.toString() + "?" + jsonBolPneu.toString() + "@" + jsonItemPneu.toString();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechadoMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAberto() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = boletinsAbertoSemEnvio();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();
        JsonArray jsonArrayBolPneu = new JsonArray();
        JsonArray jsonArrayItemPneu = new JsonArray();

        for (int i = 0; i < boletimMMList.size(); i++) {

            boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));

            ApontaMMTO apontaMMTO = new ApontaMMTO();

            ArrayList listaPesq = new ArrayList();
            EspecificaPesquisa pesquisa = new EspecificaPesquisa();
            pesquisa.setCampo("statusAponta");
            pesquisa.setValor(2L);
            listaPesq.add(pesquisa);

            EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
            pesquisa2.setCampo("idBolAponta");
            pesquisa2.setValor(boletimMMTO.getIdBoletim());
            listaPesq.add(pesquisa2);

            List apontaList = apontaMMTO.get(listaPesq);

            for (int j = 0; j < apontaList.size(); j++) {

                apontaMMTO = (ApontaMMTO) apontaList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontaMMTO, apontaMMTO.getClass()));

                ImplementoTO implementoTO = new ImplementoTO();
                List implementoList = implementoTO.get("idApontImplemento", apontaMMTO.getIdAponta());

                for (int l = 0; l < implementoList.size(); l++) {
                    implementoTO = (ImplementoTO) implementoList.get(l);
                    Gson gsonItemImp = new Gson();
                    jsonArrayImplemento.add(gsonItemImp.toJsonTree(implementoTO, implementoTO.getClass()));
                }

                implementoList.clear();

                BoletimPneuTO boletimPneuTO = new BoletimPneuTO();
                List boletimPneuList = boletimPneuTO.get("idApontBolPneu", apontaMMTO.getIdAponta());

                for (int l = 0; l < boletimPneuList.size(); l++) {

                    boletimPneuTO = (BoletimPneuTO) boletimPneuList.get(l);
                    Gson gsonBItem = new Gson();
                    jsonArrayBolPneu.add(gsonBItem.toJsonTree(boletimPneuTO, boletimPneuTO.getClass()));

                    ItemMedPneuTO itemMedPneuTO = new ItemMedPneuTO();
                    List itemMedPneuList = itemMedPneuTO.get("idBolItemMedPneu", boletimPneuTO.getIdBolPneu());

                    for (int m = 0; m < itemMedPneuList.size(); m++) {

                        itemMedPneuTO = (ItemMedPneuTO) itemMedPneuList.get(m);
                        Gson gsonItemPneu = new Gson();
                        jsonArrayItemPneu.add(gsonItemPneu.toJsonTree(itemMedPneuTO, itemMedPneuTO.getClass()));

                    }

                    itemMedPneuList.clear();

                }

                boletimPneuList.clear();

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

        JsonObject jsonBolPneu = new JsonObject();
        jsonBolPneu.add("bolpneu", jsonArrayBolPneu);

        JsonObject jsonItemPneu = new JsonObject();
        jsonItemPneu.add("itempneu", jsonArrayItemPneu);

        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString() + "#" + jsonBolPneu.toString() + "?" + jsonItemPneu.toString();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAbertoMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioApontaMM() {

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();
        JsonArray jsonArrayBolPneu = new JsonArray();
        JsonArray jsonArrayItemPneu = new JsonArray();

        ApontaMMTO apontaMMTO = new ApontaMMTO();
        List apontaList = apontamentos();

        for (int i = 0; i < apontaList.size(); i++) {

            apontaMMTO = (ApontaMMTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontaMMTO, apontaMMTO.getClass()));

            ImplementoTO implementoTO = new ImplementoTO();
            List implementoList = implementoTO.get("idApontImplemento", apontaMMTO.getIdAponta());

            for (int j = 0; j < implementoList.size(); j++) {
                implementoTO = (ImplementoTO) implementoList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayImplemento.add(gsonItem.toJsonTree(implementoTO, implementoTO.getClass()));
            }

            implementoList.clear();

            BoletimPneuTO boletimPneuTO = new BoletimPneuTO();
            List boletimPneuList = boletimPneuTO.get("idApontBolPneu", apontaMMTO.getIdAponta());

            for (int l = 0; l < boletimPneuList.size(); l++) {

                boletimPneuTO = (BoletimPneuTO) boletimPneuList.get(l);
                Gson gsonBItem = new Gson();
                jsonArrayBolPneu.add(gsonBItem.toJsonTree(boletimPneuTO, boletimPneuTO.getClass()));

                ItemMedPneuTO itemMedPneuTO = new ItemMedPneuTO();
                List itemMedPneuList = itemMedPneuTO.get("idBolItemMedPneu", boletimPneuTO.getIdBolPneu());

                for (int m = 0; m < itemMedPneuList.size(); m++) {

                    itemMedPneuTO = (ItemMedPneuTO) itemMedPneuList.get(m);
                    Gson gsonItem = new Gson();
                    jsonArrayItemPneu.add(gsonItem.toJsonTree(itemMedPneuTO, itemMedPneuTO.getClass()));

                }

                itemMedPneuList.clear();

            }

            boletimPneuList.clear();

        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("implemento", jsonArrayImplemento);

        JsonObject jsonBolPneu = new JsonObject();
        jsonBolPneu.add("bolpneu", jsonArrayBolPneu);

        JsonObject jsonItemPneu = new JsonObject();
        jsonItemPneu.add("itempneu", jsonArrayItemPneu);

        String dados = jsonAponta.toString() + "_" + jsonImplemento.toString() + "|" + jsonBolPneu.toString() + "#" + jsonItemPneu.toString();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApontaMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }


    /////////////////////////////// DELETAR DADOS ///////////////////////////////////////////////

    public void delChecklist() {

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        List cabecCheckList = cabecCheckListTO.get("statusCab", 2L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < cabecCheckList.size(); i++) {
            cabecCheckListTO = (CabecCheckListTO) cabecCheckList.get(i);
            rLista.add(cabecCheckListTO.getIdCab());
        }

        RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();
        List respItemList = respItemCheckListTO.in("idCabIt", rLista);

        for (int j = 0; j < respItemList.size(); j++) {
            respItemCheckListTO = (RespItemCheckListTO) respItemList.get(j);
            respItemCheckListTO.delete();
        }

        for (int i = 0; i < cabecCheckList.size(); i++) {
            cabecCheckListTO = (CabecCheckListTO) cabecCheckList.get(i);
            cabecCheckListTO.delete();
        }

    }

    public void delBolFechado() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = boletimMMTO.get("statusBoletim", 2L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < boletimMMList.size(); i++) {
            boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            rLista.add(boletimMMTO.getIdBoletim());
        }

        ApontaMMTO apontaMMTO = new ApontaMMTO();
        List apontaList = apontaMMTO.in("idBolAponta", rLista);

        for (int j = 0; j < apontaList.size(); j++) {

            apontaMMTO = (ApontaMMTO) apontaList.get(j);
            apontaMMTO.delete();

            ImplementoTO implementoTO = new ImplementoTO();
            List implementoList = implementoTO.get("idApontImplemento", apontaMMTO.getIdAponta());

            for (int l = 0; l < implementoList.size(); l++) {
                implementoTO = (ImplementoTO) implementoList.get(l);
                implementoTO.delete();
            }

            BoletimPneuTO boletimPneuTO = new BoletimPneuTO();
            List boletimPneuList = boletimPneuTO.get("idApontBolPneu", apontaMMTO.getIdAponta());

            for (int l = 0; l < boletimPneuList.size(); l++) {

                boletimPneuTO = (BoletimPneuTO) boletimPneuList.get(l);
                boletimPneuTO.delete();

                ItemMedPneuTO itemMedPneuTO = new ItemMedPneuTO();
                List itemMedPneuList = itemMedPneuTO.get("idBolItemMedPneu", boletimPneuTO.getIdBolPneu());

                for (int m = 0; m < itemMedPneuList.size(); m++) {

                    itemMedPneuTO = (ItemMedPneuTO) itemMedPneuList.get(m);
                    itemMedPneuTO.delete();

                }

                itemMedPneuList.clear();

            }

        }

        RendimentoTO rendimentoTO = new RendimentoTO();
        List rendimentoList = rendimentoTO.in("idBolRendimento", rLista);

        for (int j = 0; j < rendimentoList.size(); j++) {
            rendimentoTO = (RendimentoTO) rendimentoList.get(j);
            rendimentoTO.delete();
        }

        for (int i = 0; i < boletimMMList.size(); i++) {
            boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            boletimMMTO.delete();
        }

    }

    public void atualDelBoletimMM(String retorno){

        try{

            int pos1 = retorno.indexOf("=") + 1;
            int pos2 = retorno.indexOf("_") + 1;
            String id = retorno.substring(pos1, (pos2 - 1));

            BoletimMMTO boletimMMTO = new BoletimMMTO();
            List listBoletim = boletimMMTO.get("statusBoletim", 1L);
            boletimMMTO = (BoletimMMTO) listBoletim.get(0);
            boletimMMTO.setIdExtBoletim(Long.parseLong(id.trim()));
            boletimMMTO.update();

            ApontaMMTO apontaMMTO = new ApontaMMTO();
            List apontaList = apontaMMTO.get("idBolAponta", boletimMMTO.getIdBoletim());

            for (int j = 0; j < apontaList.size(); j++) {

                apontaMMTO = (ApontaMMTO) apontaList.get(j);
                apontaMMTO.delete();

                ImplementoTO implementoTO = new ImplementoTO();
                List implementoList = implementoTO.get("idApontImplemento", apontaMMTO.getIdAponta());

                for (int l = 0; l < implementoList.size(); l++) {
                    implementoTO = (ImplementoTO) implementoList.get(l);
                    implementoTO.delete();
                }

                BoletimPneuTO boletimPneuTO = new BoletimPneuTO();
                List boletimPneuList = boletimPneuTO.get("idApontBolPneu", apontaMMTO.getIdAponta());

                for (int l = 0; l < boletimPneuList.size(); l++) {

                    boletimPneuTO = (BoletimPneuTO) boletimPneuList.get(l);
                    boletimPneuTO.delete();

                    ItemMedPneuTO itemMedPneuTO = new ItemMedPneuTO();
                    List itemMedPneuList = itemMedPneuTO.get("idBolItemMedPneu", boletimPneuTO.getIdBolPneu());

                    for (int m = 0; m < itemMedPneuList.size(); m++) {

                        itemMedPneuTO = (ItemMedPneuTO) itemMedPneuList.get(m);
                        itemMedPneuTO.delete();

                    }

                    itemMedPneuList.clear();

                }

            }


        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void delApontaMM() {

        ApontaMMTO apontaMMTO = new ApontaMMTO();
        List apontaList = apontaMMTO.all();

        for (int j = 0; j < apontaList.size(); j++) {

            apontaMMTO = (ApontaMMTO) apontaList.get(j);
            apontaMMTO.delete();

            ImplementoTO implementoTO = new ImplementoTO();
            List implementoList = implementoTO.get("idApontImplemento", apontaMMTO.getIdAponta());

            for (int l = 0; l < implementoList.size(); l++) {
                implementoTO = (ImplementoTO) implementoList.get(l);
                implementoTO.delete();
            }

            BoletimPneuTO boletimPneuTO = new BoletimPneuTO();
            List boletimPneuList = boletimPneuTO.get("idApontBolPneu", apontaMMTO.getIdAponta());

            for (int l = 0; l < boletimPneuList.size(); l++) {

                boletimPneuTO = (BoletimPneuTO) boletimPneuList.get(l);
                boletimPneuTO.delete();

                ItemMedPneuTO itemMedPneuTO = new ItemMedPneuTO();
                List itemMedPneuList = itemMedPneuTO.get("idBolItemMedPneu", boletimPneuTO.getIdBolPneu());

                for (int m = 0; m < itemMedPneuList.size(); m++) {

                    itemMedPneuTO = (ItemMedPneuTO) itemMedPneuList.get(m);
                    itemMedPneuTO.delete();

                }

                itemMedPneuList.clear();

            }

        }

    }


    //////////////////////////TRAZER DADOS////////////////////////////

    public List boletinsChecklist(){
        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        return cabecCheckListTO.get("statusCab", 2L);
    }

    public List boletinsFechado() {
        BoletimMMTO boletimMMTO = new BoletimMMTO();
        return boletimMMTO.get("statusBoletim", 2L);
    }

    public List boletinsAbertoSemEnvio() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBoletim");
        pesquisa.setValor(1L);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBoletim");
        pesquisa2.setValor(0);
        listaPesq.add(pesquisa2);

        return boletimMMTO.get(listaPesq);

    }

    public List apontamentos() {
        ApontaMMTO apontaMMTO = new ApontaMMTO();
        return apontaMMTO.get("statusAponta", 2L);
    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public boolean verifDadosChecklist() {
        return boletinsChecklist().size() > 0;
    }

    public Boolean verifBolFechado() {
        return boletinsFechado().size() > 0;
    }

    public Boolean verifBolAbertoSemEnvio() {
        return boletinsAbertoSemEnvio().size() > 0;
    }

    public Boolean verifAponta() { return apontamentos().size() > 0; }

    public Boolean verifDadosGraf() {
        if(!(new GrafProdPlantioTO().hasElements())){
            return true;
        }
        else {
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

//        if(verifDadosGraf()){
//            ManipDadosVerif.getInstance().verDadosGraf();
//        }
////        else {
            if (verifDadosChecklist()) {
                enviarChecklist();
            }
            else {
                if (verifBolFechado()) {
                    enviarBolFechados();
                }
                else {
                    if (verifBolAbertoSemEnvio()) {
                        enviarBolAberto();
                    }
                    else {
                        if (verifAponta()) {
                            envioApontaMM();
                        }
                    }
                }
            }
////        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifBolFechado())
                && (!verifBolAbertoSemEnvio())
                && (!verifAponta())
                && (!verifDadosChecklist())){
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