package br.com.usinasantafe.pmm.model.dao;

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
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;


public class BoletimFertDAO implements BoletimInterface {

    public BoletimFertDAO() {
    }

    @Override
    public boolean verBolAberto() {
        BoletimFertBean boletimFertBean = new BoletimFertBean();
        List boletimFertList = boletimFertBean.get("statusBolFert", 1L);
        Boolean ret = (boletimFertList.size() > 0);
        boletimFertList.clear();
        return ret;
    }

    public BoletimFertBean getBolFertAberto(){
        BoletimFertBean boletimFertBean = new BoletimFertBean();
        List boletimFertList = boletimFertBean.get("statusBolFert", 1L);
        boletimFertBean = (BoletimFertBean) boletimFertList.get(0);
        boletimFertList.clear();
        return boletimFertBean;
    }

    public void atualQtdeApontBol(){
        BoletimFertBean boletimFertBean = getBolFertAberto();
        boletimFertBean.setQtdeApontBolFert(boletimFertBean.getQtdeApontBolFert() + 1L);
        boletimFertBean.update();
    }

    @Override
    public Long getIdBolAberto() {
        BoletimFertBean boletimFertBean = getBolFertAberto();
        return boletimFertBean.getIdBolFert();
    }


    public boolean verRecolh(){
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List recolhList = recolhFertBean.get("idBolRecolhFert", getBolFertAberto().getIdBolFert());
        Boolean ret = (recolhList.size() > 0);
        recolhList.clear();
        return ret;
    }

    public RecolhFertBean getRecolh(int pos){
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List recolhFertList = recolhFertBean.getAndOrderBy("idBolRecolhFert", getBolFertAberto().getIdBolFert(), "idRecolhFert", true);
        recolhFertBean = (RecolhFertBean) recolhFertList.get(pos);
        recolhFertList.clear();
        return recolhFertBean;
    }

    public int qtdeRecolh(){
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List recolhList = recolhFertBean.get("idBolRecolhFert", getBolFertAberto().getIdBolFert());
        return recolhList.size();
    }

    public void atualRecolh(RecolhFertBean recolhFertBean){
        recolhFertBean.setDthrRecolhFert(Tempo.getInstance().dataComHora().getDataHora());
        recolhFertBean.update();
        recolhFertBean.commit();
    }

    public void salvarBolAberto(BoletimFertBean boletimFertBean){


        boletimFertBean.setStatusBolFert(1L);
        boletimFertBean.setDthrInicialBolFert(Tempo.getInstance().dataComHora().getDataHora());
        boletimFertBean.setStatusDtHrInicialBolFert(Tempo.getInstance().dataComHora().getStatus());
        boletimFertBean.setQtdeApontBolFert(0L);
        boletimFertBean.insert();

        CheckListCTR checkListCTR = new CheckListCTR();
        if(checkListCTR.verAberturaCheckList(boletimFertBean.getIdTurnoBolFert())) {

            BoletimCTR boletimCTR = new BoletimCTR();

            ApontFertBean apontFertBean = new ApontFertBean();
            apontFertBean.setDthrApontFert(Tempo.getInstance().dataComHora().getDataHora());
            apontFertBean.setIdBolApontFert(boletimFertBean.getIdBolFert());
            apontFertBean.setIdExtBolApontFert(boletimFertBean.getIdExtBolFert());
            apontFertBean.setOsApontFert(boletimFertBean.getOsBolFert());
            apontFertBean.setAtivApontFert(boletimFertBean.getAtivPrincBolFert());
            apontFertBean.setParadaApontFert(boletimCTR.getIdParadaCheckList());
            apontFertBean.setLatitudeApontFert(0D);
            apontFertBean.setLongitudeApontFert(0D);
            apontFertBean.setStatusConApontFert(boletimFertBean.getStatusConBolFert());
            apontFertBean.setStatusApontFert(1L);
            apontFertBean.setStatusDtHrApontFert(Tempo.getInstance().dataComHora().getStatus());
            apontFertBean.insert();

        }

    }

    public void salvarBolFechado(BoletimFertBean boletimFertBean) {

        BoletimFertBean boletimFertTOBD = new BoletimFertBean();
        List boletimFertList = boletimFertTOBD.get("statusBolFert", 1L);
        boletimFertTOBD = (BoletimFertBean) boletimFertList.get(0);
        boletimFertList.clear();

        boletimFertTOBD.setDthrFinalBolFert(Tempo.getInstance().dataComHora().getDataHora());
        boletimFertTOBD.setStatusDtHrFinalBolFert(Tempo.getInstance().dataComHora().getStatus());
        boletimFertTOBD.setStatusBolFert(2L);
        boletimFertTOBD.setHodometroFinalBolFert(boletimFertBean.getHodometroFinalBolFert());
        boletimFertTOBD.update();

    }

    public List bolFechadoList() {
        BoletimFertBean boletimFertBean = new BoletimFertBean();
        return boletimFertBean.get("statusBolFert", 2L);
    }

    public List bolAbertoSemEnvioList() {

        BoletimFertBean boletimFertBean = new BoletimFertBean();

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

        return boletimFertBean.get(pesqList);

    }


    public String dadosEnvioBolAberto(BoletimFertBean boletimFertBean){

        JsonArray jsonArrayBoletim = new JsonArray();

        Gson gsonCabec = new Gson();
        jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertBean, boletimFertBean.getClass()));

        ApontCTR apontCTR = new ApontCTR();
        String dadosEnvioApont = apontCTR.dadosEnvioApontBolFert(boletimFertBean.getIdBolFert());

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

            BoletimFertBean boletimFertBean = (BoletimFertBean) boletimFertList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertBean, boletimFertBean.getClass()));

            ApontCTR apontCTR = new ApontCTR();
            dadosEnvioApont = apontCTR.dadosEnvioApontBolFert(boletimFertBean.getIdBolFert());

            RecolhFertBean recolhFertBean = new RecolhFertBean();
            List recolhimentoList = recolhFertBean.get("idBolRecolhFert", boletimFertBean.getIdBolFert());

            for (int j = 0; j < recolhimentoList.size(); j++) {
                recolhFertBean = (RecolhFertBean) recolhimentoList.get(j);
                Gson gsonRecol = new Gson();
                jsonArrayRecolhimento.add(gsonRecol.toJsonTree(recolhFertBean, recolhFertBean.getClass()));
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
            BoletimFertBean boletimFertBean = gsonBol.fromJson(objBol.toString(), BoletimFertBean.class);

            List bolFertList = boletimFertBean.get("idBolFert", boletimFertBean.getIdBolFert());
            BoletimFertBean boletimFertTOBD = (BoletimFertBean) bolFertList.get(0);
            bolFertList.clear();

            boletimFertTOBD.setIdExtBolFert(boletimFertBean.getIdExtBolFert());
            boletimFertTOBD.update();

            JSONObject jObjApontFert = new JSONObject(objSeg);
            JSONArray jsonArrayApontFert = jObjApontFert.getJSONArray("apont");

            if (jsonArrayApontFert.length() > 0) {

                ArrayList<Long> rList = new ArrayList<Long>();
                ApontFertBean apontFertBean = new ApontFertBean();

                for (int i = 0; i < jsonArrayApontFert.length(); i++) {

                    JSONObject objApont = jsonArrayApontFert.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontFertBean = gsonApont.fromJson(objApont.toString(), ApontFertBean.class);

                    rList.add(apontFertBean.getIdApontFert());

                }

                List apontFertList = apontFertBean.in("idApontFert", rList);

                for (int i = 0; i < apontFertList.size(); i++) {

                    apontFertBean = (ApontFertBean) apontFertList.get(0);
                    apontFertBean.setIdExtBolApontFert(boletimFertBean.getIdExtBolFert());
                    apontFertBean.setStatusApontFert(2L);
                    apontFertBean.update();

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
                BoletimFertBean boletimFertBean = gsonBol.fromJson(objBol.toString(), BoletimFertBean.class);

                List bolFertList = boletimFertBean.get("idBolFert", boletimFertBean.getIdBolFert());
                BoletimFertBean boletimFertTOBD = (BoletimFertBean) bolFertList.get(0);
                bolFertList.clear();

                if(boletimFertTOBD.getQtdeApontBolFert() == boletimFertBean.getQtdeApontBolFert()){

                    ApontFertBean apontFertBean = new ApontFertBean();
                    List apontList = apontFertBean.get("idBolApontFert", boletimFertTOBD.getIdBolFert());

                    for (int j = 0; j < apontList.size(); j++) {

                        apontFertBean = (ApontFertBean) apontList.get(j);
                        apontFertBean.delete();

                    }

                    apontList.clear();

                    RecolhFertBean recolhFertBean = new RecolhFertBean();
                    List recolhList = recolhFertBean.get("idBolRecolhFert", boletimFertTOBD.getIdBolFert());

                    for (int j = 0; j < recolhList.size(); j++) {
                        recolhFertBean = (RecolhFertBean) recolhList.get(j);
                        recolhFertBean.delete();
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
