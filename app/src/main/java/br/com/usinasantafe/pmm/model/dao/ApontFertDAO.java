package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.CabecPneuBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ItemPneuBean;
import br.com.usinasantafe.pmm.control.ApontInterface;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;

public class ApontFertDAO implements ApontInterface {

    public ApontFertDAO() {
    }

    @Override
    public boolean verApontAberto() {
        ApontFertBean apontFertBean = new ApontFertBean();
        List apontaFertList = apontFertBean.get("statusApontFert", 0L);
        boolean ret = (apontaFertList.size() > 0);
        apontaFertList.clear();
        return ret;
    }

    public ApontFertBean getApontFertAberto(){
        ApontFertBean apontFertBean = new ApontFertBean();
        List apontaFertList = apontFertBean.get("statusApontFert", 0L);
        apontFertBean = (ApontFertBean) apontaFertList.get(0);
        apontaFertList.clear();
        return apontFertBean;
    }

    @Override
    public Long getIdApontAberto() {
        ApontFertBean apontFertBean = getApontFertAberto();
        return apontFertBean.getIdApontFert();
    }

    public List getListAllApont(Long idBolMM){
        ApontFertBean apontFertBean = new ApontFertBean();
        return apontFertBean.getAndOrderBy("idBolApontFert", idBolMM, "idApontFert", true);
    }

    public void salvarApont(ApontFertBean apontFertBean){

        apontFertBean.insert();

        RecolhFertBean recolhFertBean = new RecolhFertBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolRecolhFert");
        pesquisa.setValor(apontFertBean.getIdBolApontFert());
        pesquisa.setTipo(1);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("nroOSRecolhFert");
        pesquisa2.setValor(apontFertBean.getOsApontFert());
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        List rendList = recolhFertBean.get(pesqList);

        if (rendList.size() == 0) {
            recolhFertBean.setIdBolRecolhFert(apontFertBean.getIdBolApontFert());
            recolhFertBean.setNroOSRecolhFert(apontFertBean.getOsApontFert());
            recolhFertBean.setValorRecolhFert(0L);
            recolhFertBean.insert();
            recolhFertBean.commit();
        }

    }

    public void updApont(ApontFertBean apontFertBean){

        apontFertBean.setStatusApontFert(1L);
        apontFertBean.update();

        RecolhFertBean recolhFertBean = new RecolhFertBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolRecolhFert");
        pesquisa.setValor(apontFertBean.getIdBolApontFert());
        pesquisa.setTipo(1);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("nroOSRecolhFert");
        pesquisa2.setValor(apontFertBean.getOsApontFert());
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        List rendList = recolhFertBean.get(pesqList);

        if (rendList.size() == 0) {
            recolhFertBean.setIdBolRecolhFert(apontFertBean.getIdBolApontFert());
            recolhFertBean.setNroOSRecolhFert(apontFertBean.getOsApontFert());
            recolhFertBean.setValorRecolhFert(0L);
            recolhFertBean.insert();
            recolhFertBean.commit();
        }

    }

    public List getListApontEnvio(Long idBolMM){

        ApontFertBean apontFertBean = new ApontFertBean();

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

        return apontFertBean.get(pesqArrayList);

    }

    public List getListApontEnvio() {
        ApontFertBean apontFertBean = new ApontFertBean();
        return apontFertBean.get("statusApontFert", 1L);
    }

    public String dadosEnvioApontFert(List apontaList){

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayCabecPneu = new JsonArray();
        JsonArray jsonArrayItemPneu = new JsonArray();

        for (int i = 0; i < apontaList.size(); i++) {

            ApontFertBean apontFertBean = (ApontFertBean) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontFertBean, apontFertBean.getClass()));

            CabecPneuBean cabecPneuBean = new CabecPneuBean();
            List cabecPneuList = cabecPneuBean.get("idApontCabecPneu", apontFertBean.getIdApontFert());

            for (int l = 0; l < cabecPneuList.size(); l++) {

                cabecPneuBean = (CabecPneuBean) cabecPneuList.get(l);
                Gson gsonCabecPneu = new Gson();
                jsonArrayCabecPneu.add(gsonCabecPneu.toJsonTree(cabecPneuBean, cabecPneuBean.getClass()));

                ItemPneuBean itemPneuBean = new ItemPneuBean();
                List itemPneuList = itemPneuBean.get("idCabecItemPneu", cabecPneuBean.getIdCabecPneu());

                for (int m = 0; m < itemPneuList.size(); m++) {
                    itemPneuBean = (ItemPneuBean) itemPneuList.get(m);
                    Gson gsonItemPneu = new Gson();
                    jsonArrayItemPneu.add(gsonItemPneu.toJsonTree(itemPneuBean, itemPneuBean.getClass()));
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
                    apontFertBean.setStatusApontFert(2L);
                    apontFertBean.update();

                    CabecPneuBean cabecPneuBean = new CabecPneuBean();
                    List cabecPneuList = cabecPneuBean.in("idApontCabecPneu", rList);

                    for (int l = 0; l < cabecPneuList.size(); l++) {

                        cabecPneuBean = (CabecPneuBean) cabecPneuList.get(l);

                        ItemPneuBean itemPneuBean = new ItemPneuBean();
                        List itemPneuList = itemPneuBean.get("idCabecItemPneu", cabecPneuBean.getIdCabecPneu());

                        for (int m = 0; m < itemPneuList.size(); m++) {
                            itemPneuBean = (ItemPneuBean) itemPneuList.get(m);
                            itemPneuBean.delete();
                        }

                        cabecPneuBean.delete();

                    }

                }

                rList.clear();

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public ApontFertBean createApont(BoletimCTR boletimCTR){
        ApontFertBean apontFertBean = new ApontFertBean();
        List apontList = getListAllApont(boletimCTR.getIdBol());
        if (apontList.size() == 0) {
            apontFertBean.setIdBolApontFert(boletimCTR.getIdBol());
            apontFertBean.setIdExtBolApontFert(boletimCTR.getIdExtBol());
            apontFertBean.setOsApontFert(boletimCTR.getIdExtBol());
            apontFertBean.setAtivApontFert(boletimCTR.getAtiv());
            apontFertBean.setParadaApontFert(0L);
            apontFertBean.setDthrApontFert(Tempo.getInstance().dataComHora().getDataHora());
            apontFertBean.setStatusConApontFert(boletimCTR.getStatusConBol());
            apontFertBean.setStatusApontFert(1L);
            apontFertBean.setStatusDtHrApontFert(Tempo.getInstance().dataComHora().getStatus());
            apontFertBean.setLongitudeApontFert(boletimCTR.getLongitude());
            apontFertBean.setLatitudeApontFert(boletimCTR.getLatitude());
        } else {
            ApontFertBean ultApontTO = (ApontFertBean) apontList.get(apontList.size() - 1);
            apontFertBean = ultApontTO;
            apontFertBean.setStatusApontFert(1L);
        }
        apontFertBean.setPressaoApontFert(0D);
        apontFertBean.setVelocApontFert(0L);
        apontFertBean.setBocalApontFert(0L);
        apontList.clear();
        return apontFertBean;
    }

}
