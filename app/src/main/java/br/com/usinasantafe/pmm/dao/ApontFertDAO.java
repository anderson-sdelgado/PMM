package br.com.usinasantafe.pmm.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bean.variaveis.ApontImpleMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.CabecPneuTO;
import br.com.usinasantafe.pmm.bean.variaveis.ItemPneuTO;
import br.com.usinasantafe.pmm.control.ApontInterface;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.bean.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.RecolhFertTO;

public class ApontFertDAO implements ApontInterface {

    public ApontFertDAO() {
    }

    @Override
    public boolean verApontAberto() {
        ApontFertTO apontFertTO = new ApontFertTO();
        List apontaFertList = apontFertTO.get("statusApontFert", 0L);
        boolean ret = (apontaFertList.size() > 0);
        apontaFertList.clear();
        return ret;
    }

    public ApontFertTO getApontFertAberto(){
        ApontFertTO apontFertTO = new ApontFertTO();
        List apontaFertList = apontFertTO.get("statusApontFert", 0L);
        apontFertTO = (ApontFertTO) apontaFertList.get(0);
        apontaFertList.clear();
        return apontFertTO;
    }

    @Override
    public Long getIdApontAberto() {
        ApontFertTO apontFertTO = getApontFertAberto();
        return apontFertTO.getIdApontFert();
    }

    public List getListAllApont(Long idBolMM){
        ApontFertTO apontFertTO = new ApontFertTO();
        return apontFertTO.getAndOrderBy("idBolApontFert", idBolMM, "idApontFert", true);
    }

    public void salvarApont(ApontFertTO apontFertTO){

        apontFertTO.insert();

        RecolhFertTO recolhFertTO = new RecolhFertTO();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolRecolhFert");
        pesquisa.setValor(apontFertTO.getIdBolApontFert());
        pesquisa.setTipo(1);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("nroOSRecolhFert");
        pesquisa2.setValor(apontFertTO.getOsApontFert());
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        List rendList = recolhFertTO.get(pesqList);

        if (rendList.size() == 0) {
            recolhFertTO.setIdBolRecolhFert(apontFertTO.getIdBolApontFert());
            recolhFertTO.setNroOSRecolhFert(apontFertTO.getOsApontFert());
            recolhFertTO.setValorRecolhFert(0L);
            recolhFertTO.insert();
            recolhFertTO.commit();
        }

    }

    public void updApont(ApontFertTO apontFertTO){

        apontFertTO.setStatusApontFert(1L);
        apontFertTO.update();

        RecolhFertTO recolhFertTO = new RecolhFertTO();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolRecolhFert");
        pesquisa.setValor(apontFertTO.getIdBolApontFert());
        pesquisa.setTipo(1);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("nroOSRecolhFert");
        pesquisa2.setValor(apontFertTO.getOsApontFert());
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        List rendList = recolhFertTO.get(pesqList);

        if (rendList.size() == 0) {
            recolhFertTO.setIdBolRecolhFert(apontFertTO.getIdBolApontFert());
            recolhFertTO.setNroOSRecolhFert(apontFertTO.getOsApontFert());
            recolhFertTO.setValorRecolhFert(0L);
            recolhFertTO.insert();
            recolhFertTO.commit();
        }

    }

    public List getListApontEnvio(Long idBolMM){

        ApontFertTO apontFertTO = new ApontFertTO();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idBolApontFert");
        pesquisa2.setValor(idBolMM);
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        return apontFertTO.get(pesqArrayList);

    }

    public List getListApontEnvio() {
        ApontFertTO apontFertTO = new ApontFertTO();
        return apontFertTO.get("statusApontFert", 1L);
    }

    public String dadosEnvioApontFert(List apontaList){

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayCabecPneu = new JsonArray();
        JsonArray jsonArrayItemPneu = new JsonArray();

        for (int i = 0; i < apontaList.size(); i++) {

            ApontFertTO apontFertTO = (ApontFertTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontFertTO, apontFertTO.getClass()));

            CabecPneuTO cabecPneuTO = new CabecPneuTO();
            List cabecPneuList = cabecPneuTO.get("idApontCabecPneu", apontFertTO.getIdApontFert());

            for (int l = 0; l < cabecPneuList.size(); l++) {

                cabecPneuTO = (CabecPneuTO) cabecPneuList.get(l);
                Gson gsonCabecPneu = new Gson();
                jsonArrayCabecPneu.add(gsonCabecPneu.toJsonTree(cabecPneuTO, cabecPneuTO.getClass()));

                ItemPneuTO itemPneuTO = new ItemPneuTO();
                List itemPneuList = itemPneuTO.get("idCabecItemPneu", cabecPneuTO.getIdCabecPneu());

                for (int m = 0; m < itemPneuList.size(); m++) {
                    itemPneuTO = (ItemPneuTO) itemPneuList.get(m);
                    Gson gsonItemPneu = new Gson();
                    jsonArrayItemPneu.add(gsonItemPneu.toJsonTree(itemPneuTO, itemPneuTO.getClass()));
                }

                itemPneuList.clear();

            }

            cabecPneuList.clear();

        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonCabecPneu = new JsonObject();
        jsonCabecPneu.add("cabecpneu", jsonArrayCabecPneu);

        JsonObject jsonItemPneu = new JsonObject();
        jsonItemPneu.add("itempneu", jsonArrayItemPneu);

        return jsonAponta.toString() + "?" + jsonCabecPneu.toString() + "@" + jsonItemPneu.toString();

    }

    public void updateApont(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjApontFert = new JSONObject(objPrinc);
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
                    apontFertTO.setStatusApontFert(2L);
                    apontFertTO.update();

                    CabecPneuTO cabecPneuTO = new CabecPneuTO();
                    List cabecPneuList = cabecPneuTO.in("idApontCabecPneu", rList);

                    for (int l = 0; l < cabecPneuList.size(); l++) {

                        cabecPneuTO = (CabecPneuTO) cabecPneuList.get(l);

                        ItemPneuTO itemPneuTO = new ItemPneuTO();
                        List itemPneuList = itemPneuTO.get("idCabecItemPneu", cabecPneuTO.getIdCabecPneu());

                        for (int m = 0; m < itemPneuList.size(); m++) {
                            itemPneuTO = (ItemPneuTO) itemPneuList.get(m);
                            itemPneuTO.delete();
                        }

                        cabecPneuTO.delete();

                    }

                }

                rList.clear();

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public ApontFertTO createApont(BoletimCTR boletimCTR){
        ApontFertTO apontFertTO = new ApontFertTO();
        List apontList = getListAllApont(boletimCTR.getIdBol());
        if (apontList.size() == 0) {
            apontFertTO.setIdBolApontFert(boletimCTR.getIdBol());
            apontFertTO.setIdExtBolApontFert(boletimCTR.getIdExtBol());
            apontFertTO.setOsApontFert(boletimCTR.getIdExtBol());
            apontFertTO.setAtivApontFert(boletimCTR.getAtiv());
            apontFertTO.setParadaApontFert(0L);
            apontFertTO.setDthrApontFert(Tempo.getInstance().dataComHora().getDataHora());
            apontFertTO.setStatusConApontFert(boletimCTR.getStatusConBol());
            apontFertTO.setStatusApontFert(1L);
            apontFertTO.setStatusDtHrApontFert(Tempo.getInstance().dataComHora().getStatus());
            apontFertTO.setLongitudeApontFert(boletimCTR.getLongitude());
            apontFertTO.setLatitudeApontFert(boletimCTR.getLatitude());
        } else {
            ApontFertTO ultApontTO = (ApontFertTO) apontList.get(apontList.size() - 1);
            apontFertTO = ultApontTO;
            apontFertTO.setStatusApontFert(1L);
        }
        apontFertTO.setPressaoApontFert(0D);
        apontFertTO.setVelocApontFert(0L);
        apontFertTO.setBocalApontFert(0L);
        apontList.clear();
        return apontFertTO;
    }

}
