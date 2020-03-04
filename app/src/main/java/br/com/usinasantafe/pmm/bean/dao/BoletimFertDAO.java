package br.com.usinasantafe.pmm.bean.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.control.ApontCTR;
import br.com.usinasantafe.pmm.control.BoletimInterface;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.bean.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.RecolhFertTO;


public class BoletimFertDAO implements BoletimInterface {

    public BoletimFertDAO() {
    }

    @Override
    public boolean verBolAberto() {
        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.get("statusBolFert", 1L);
        Boolean ret = (boletimFertList.size() > 0);
        boletimFertList.clear();
        return ret;
    }

    public BoletimFertTO getBolFertAberto(){
        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.get("statusBolFert", 1L);
        boletimFertTO = (BoletimFertTO) boletimFertList.get(0);
        boletimFertList.clear();
        return boletimFertTO;
    }

    public void atualQtdeApontBol(){
        BoletimFertTO boletimFertTO = getBolFertAberto();
        boletimFertTO.setQtdeApontBolFert(boletimFertTO.getQtdeApontBolFert() + 1L);
        boletimFertTO.update();
    }

    @Override
    public Long getIdBolAberto() {
        BoletimFertTO boletimFertTO = getBolFertAberto();
        return boletimFertTO.getIdBolFert();
    }


    public boolean verRecolh(){
        RecolhFertTO recolhFertTO = new RecolhFertTO();
        List recolhList = recolhFertTO.get("idBolRecolhFert", getBolFertAberto().getIdBolFert());
        Boolean ret = (recolhList.size() > 0);
        recolhList.clear();
        return ret;
    }

    public RecolhFertTO getRecolh(int pos){
        RecolhFertTO recolhFertTO = new RecolhFertTO();
        List recolhFertList = recolhFertTO.getAndOrderBy("idBolRecolhFert", getBolFertAberto().getIdBolFert(), "idRecolhFert", true);
        recolhFertTO = (RecolhFertTO) recolhFertList.get(pos);
        recolhFertList.clear();
        return recolhFertTO;
    }

    public int qtdeRecolh(){
        RecolhFertTO recolhFertTO = new RecolhFertTO();
        List recolhList = recolhFertTO.get("idBolRecolhFert", getBolFertAberto().getIdBolFert());
        return recolhList.size();
    }

    public void atualRecolh(RecolhFertTO recolhFertTO){
        recolhFertTO.setDthrRecolhFert(Tempo.getInstance().dataComHora().getDataHora());
        recolhFertTO.update();
        recolhFertTO.commit();
    }

    public void salvarBolAberto(BoletimFertTO boletimFertTO){


        boletimFertTO.setStatusBolFert(1L);
        boletimFertTO.setDthrInicialBolFert(Tempo.getInstance().dataComHora().getDataHora());
        boletimFertTO.setStatusDtHrInicialBolFert(Tempo.getInstance().dataComHora().getStatus());
        boletimFertTO.setQtdeApontBolFert(0L);
        boletimFertTO.insert();

        CheckListCTR checkListCTR = new CheckListCTR();
        if(checkListCTR.verAberturaCheckList(boletimFertTO.getIdTurnoBolFert())) {

            BoletimCTR boletimCTR = new BoletimCTR();

            ApontFertTO apontFertTO = new ApontFertTO();
            apontFertTO.setDthrApontFert(Tempo.getInstance().dataComHora().getDataHora());
            apontFertTO.setIdBolApontFert(boletimFertTO.getIdBolFert());
            apontFertTO.setIdExtBolApontFert(boletimFertTO.getIdExtBolFert());
            apontFertTO.setOsApontFert(boletimFertTO.getOsBolFert());
            apontFertTO.setAtivApontFert(boletimFertTO.getAtivPrincBolFert());
            apontFertTO.setParadaApontFert(boletimCTR.getIdParadaCheckList());
            apontFertTO.setLatitudeApontFert(0D);
            apontFertTO.setLongitudeApontFert(0D);
            apontFertTO.setStatusConApontFert(boletimFertTO.getStatusConBolFert());
            apontFertTO.setStatusApontFert(1L);
            apontFertTO.setStatusDtHrApontFert(Tempo.getInstance().dataComHora().getStatus());
            apontFertTO.insert();

        }

    }

    public void salvarBolFechado(BoletimFertTO boletimFertTO) {

        BoletimFertTO boletimFertTOBD = new BoletimFertTO();
        List boletimFertList = boletimFertTOBD.get("statusBolFert", 1L);
        boletimFertTOBD = (BoletimFertTO) boletimFertList.get(0);
        boletimFertList.clear();

        boletimFertTOBD.setDthrFinalBolFert(Tempo.getInstance().dataComHora().getDataHora());
        boletimFertTOBD.setStatusDtHrFinalBolFert(Tempo.getInstance().dataComHora().getStatus());
        boletimFertTOBD.setStatusBolFert(2L);
        boletimFertTOBD.setHodometroFinalBolFert(boletimFertTO.getHodometroFinalBolFert());
        boletimFertTOBD.update();

    }

    public List bolFechadoList() {
        BoletimFertTO boletimFertTO = new BoletimFertTO();
        return boletimFertTO.get("statusBolFert", 2L);
    }

    public List bolAbertoSemEnvioList() {

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


    public String dadosEnvioBolAberto(BoletimFertTO boletimFertTO){

        JsonArray jsonArrayBoletim = new JsonArray();

        Gson gsonCabec = new Gson();
        jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertTO, boletimFertTO.getClass()));

        ApontCTR apontCTR = new ApontCTR();
        String dadosEnvioApont = apontCTR.dadosEnvioApontBolFert(boletimFertTO.getIdBolFert());

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        return jsonBoletim.toString() + "_" + dadosEnvioApont;

    }

    public String dadosEnvioBolFechado(){

        List boletimFertList = bolFechadoList();

        JsonArray jsonArrayBoletim = new JsonArray();
        String dadosEnvioApont = "";
        JsonArray jsonArrayRecolhimento = new JsonArray();

        for (int i = 0; i < boletimFertList.size(); i++) {

            BoletimFertTO boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertTO, boletimFertTO.getClass()));

            ApontCTR apontCTR = new ApontCTR();
            dadosEnvioApont = apontCTR.dadosEnvioApontBolFert(boletimFertTO.getIdBolFert());

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


        JsonObject jsonRecolhimento = new JsonObject();
        jsonRecolhimento.add("recolhimento", jsonArrayRecolhimento);

        return jsonBoletim.toString() + "_" + dadosEnvioApont + "|" + jsonRecolhimento.toString();
    }

    @Override
    public void updateBolAberto(String retorno) {
        try{

            int pos1 = retorno.indexOf("_") + 1;
            int pos2 = retorno.indexOf("|") + 1;
            String objPrinc = retorno.substring(pos1, pos2);
            String objSeg = retorno.substring(pos2);

            JSONObject jObjBolFert = new JSONObject(objPrinc);
            JSONArray jsonArrayBolFert = jObjBolFert.getJSONArray("boletim");

            JSONObject objBol = jsonArrayBolFert.getJSONObject(0);
            Gson gsonBol = new Gson();
            BoletimFertTO boletimFertTO = gsonBol.fromJson(objBol.toString(), BoletimFertTO.class);

            List bolFertList = boletimFertTO.get("idBolFert", boletimFertTO.getIdBolFert());
            BoletimFertTO boletimFertTOBD = (BoletimFertTO) bolFertList.get(0);
            bolFertList.clear();

            boletimFertTOBD.setIdExtBolFert(boletimFertTO.getIdExtBolFert());
            boletimFertTOBD.update();

            JSONObject jObjApontFert = new JSONObject(objSeg);
            JSONArray jsonArrayApontFert = jObjApontFert.getJSONArray("apont");

            if (jsonArrayApontFert.length() > 0) {

                ArrayList<Long> rList = new ArrayList<Long>();
                ApontFertTO apontFertTO = new ApontFertTO();

                for (int i = 0; i < jsonArrayApontFert.length(); i++) {

                    JSONObject objApont = jsonArrayApontFert.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontFertTO = gsonApont.fromJson(objApont.toString(), ApontFertTO.class);

                    rList.add(apontFertTO.getIdApontFert());

                }

                List apontFertList = apontFertTO.in("idApontFert", rList);

                for (int i = 0; i < apontFertList.size(); i++) {

                    apontFertTO = (ApontFertTO) apontFertList.get(0);
                    apontFertTO.setIdExtBolApontFert(boletimFertTO.getIdExtBolFert());
                    apontFertTO.setStatusApontFert(2L);
                    apontFertTO.update();

                }

                rList.clear();

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }
    }

    @Override
    public void deleteBolFechado(String retorno) {
        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjBolFert = new JSONObject(objPrinc);
            JSONArray jsonArrayBolFert = jObjBolFert.getJSONArray("boletim");

            for (int i = 0; i < jsonArrayBolFert.length(); i++) {

                JSONObject objBol = jsonArrayBolFert.getJSONObject(i);
                Gson gsonBol = new Gson();
                BoletimFertTO boletimFertTO = gsonBol.fromJson(objBol.toString(), BoletimFertTO.class);

                List bolFertList = boletimFertTO.get("idBolFert", boletimFertTO.getIdBolFert());
                BoletimFertTO boletimFertTOBD = (BoletimFertTO) bolFertList.get(0);
                bolFertList.clear();

                if(boletimFertTOBD.getQtdeApontBolFert() == boletimFertTO.getQtdeApontBolFert()){

                    ApontFertTO apontFertTO = new ApontFertTO();
                    List apontList = apontFertTO.get("idBolApontFert", boletimFertTOBD.getIdBolFert());

                    for (int j = 0; j < apontList.size(); j++) {

                        apontFertTO = (ApontFertTO) apontList.get(j);
                        apontFertTO.delete();

                    }

                    apontList.clear();

                    RecolhFertTO recolhFertTO = new RecolhFertTO();
                    List recolhList = recolhFertTO.get("idBolRecolhFert", boletimFertTOBD.getIdBolFert());

                    for (int j = 0; j < recolhList.size(); j++) {
                        recolhFertTO = (RecolhFertTO) recolhList.get(j);
                        recolhFertTO.delete();
                    }

                    recolhList.clear();

                    boletimFertTOBD.delete();

                }

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }
    }

}
