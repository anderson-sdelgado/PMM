package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.DataHoraTO;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecPneuBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ItemPneuBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.MovLeiraBean;
import br.com.usinasantafe.pmm.control.ApontInterface;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.variaveis.ImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMBean;

public class ApontMMDAO implements ApontInterface {

    public ApontMMDAO() {
    }

    @Override
    public boolean verApontAberto() {
        ApontMMBean apontMMBean = new ApontMMBean();
        List apontaMMList = apontMMBean.get("statusApontMM", 0L);
        boolean ret = (apontaMMList.size() > 0);
        apontaMMList.clear();
        return ret;
    }

    public ApontMMBean getApontMMAberto(){
        ApontMMBean apontMMBean = new ApontMMBean();
        List apontaMMList = apontMMBean.get("statusApontMM", 0L);
        apontMMBean = (ApontMMBean) apontaMMList.get(0);
        apontaMMList.clear();
        return apontMMBean;
    }

    @Override
    public Long getIdApontAberto() {
        ApontMMBean apontMMBean = getApontMMAberto();
        return apontMMBean.getIdApontMM();
    }

    public List getListAllApont(Long idBolMM){
        ApontMMBean apontMMBean = new ApontMMBean();
        return apontMMBean.getAndOrderBy("idBolApontMM", idBolMM, "idApontMM", true);
    }

    public void salvarApont(ApontMMBean apontMMBean){

        apontMMBean.insert();

        List apontaList = apontMMBean.get("dthrApontMM", apontMMBean.getDthrApontMM());
        apontMMBean = (ApontMMBean) apontaList.get(0);
        apontaList.clear();

        ImpleMMBean impleMMBean = new ImpleMMBean();
        List impleList = impleMMBean.all();

        for (int i = 0; i < impleList.size(); i++) {
            impleMMBean = (ImpleMMBean) impleList.get(i);
            ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
            apontImpleMMBean.setIdApontMM(apontMMBean.getIdApontMM());
            apontImpleMMBean.setCodEquipImpleMM(impleMMBean.getCodEquipImpleMM());
            apontImpleMMBean.setPosImpleMM(impleMMBean.getPosImpleMM());
            apontImpleMMBean.setDthrImpleMM(apontMMBean.getDthrApontMM());
            apontImpleMMBean.insert();
        }

    }

    public void updApont(ApontMMBean apontMMBean){

        apontMMBean.setStatusApontMM(1L);
        apontMMBean.update();

        List apontaList = apontMMBean.get("dthrApontMM", apontMMBean.getDthrApontMM());
        apontMMBean = (ApontMMBean) apontaList.get(0);
        apontaList.clear();

        ImpleMMBean impleMMBean = new ImpleMMBean();
        List impleList = impleMMBean.all();

        for (int i = 0; i < impleList.size(); i++) {
            impleMMBean = (ImpleMMBean) impleList.get(i);
            ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
            apontImpleMMBean.setIdApontMM(apontMMBean.getIdApontMM());
            apontImpleMMBean.setCodEquipImpleMM(impleMMBean.getCodEquipImpleMM());
            apontImpleMMBean.setPosImpleMM(impleMMBean.getPosImpleMM());
            apontImpleMMBean.setDthrImpleMM(apontMMBean.getDthrApontMM());
            apontImpleMMBean.insert();
        }

    }


    public int verTransbordo(String data) {

        int retorno = 0;

        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        List apontMMList = getListAllApont(boletimMMDAO.getIdBolAberto());

        if (apontMMList.size() > 0) {
            ApontMMBean apontaMMTO = (ApontMMBean) apontMMList.get(apontMMList.size() - 1);
            if (apontaMMTO.getParadaApontMM() != 0L) {
                retorno = 1;
            }
        }

        if (data.isEmpty()) {
            retorno = 1;
        }

        if (retorno == 0) {

            String dtStr = data;

            String diaStr = dtStr.substring(0, 2);
            String mesStr = dtStr.substring(3, 5);
            String anoStr = dtStr.substring(6, 10);
            String horaStr = dtStr.substring(11, 13);
            String minutoStr = dtStr.substring(14, 16);

            Calendar calBase = Calendar.getInstance();
            calBase.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            calBase.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            calBase.set(Calendar.YEAR, Integer.parseInt(anoStr));
            calBase.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            calBase.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            calBase.add(Calendar.MINUTE, +9);

            DataHoraTO dataHoraTO = Tempo.getInstance().dataComHora();
            dtStr = dataHoraTO.getDataHora();

            diaStr = dtStr.substring(0, 2);
            mesStr = dtStr.substring(3, 5);
            anoStr = dtStr.substring(6, 10);
            horaStr = dtStr.substring(11, 13);
            minutoStr = dtStr.substring(14, 16);

            Calendar calTimer = Calendar.getInstance();
            calTimer.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            calTimer.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            calTimer.set(Calendar.YEAR, Integer.parseInt(anoStr));
            calTimer.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            calTimer.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            if (calBase.after(calTimer)) {
                retorno = 2;
            }

        }
        return retorno;
    }

    public List getListApontEnvio(Long idBolMM){

        ApontMMBean apontMMBean = new ApontMMBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontMM");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idBolApontMM");
        pesquisa2.setValor(idBolMM);
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        return apontMMBean.get(pesqArrayList);

    }

    public List getListApontEnvio() {
        ApontMMBean apontMMBean = new ApontMMBean();
        return apontMMBean.get("statusApontMM", 1L);
    }

    public String dadosEnvioApontMM(List apontaList, List movLeiraList){

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();
        JsonArray jsonArrayMovLeira = new JsonArray();
        JsonArray jsonArrayCabecPneu = new JsonArray();
        JsonArray jsonArrayItemPneu = new JsonArray();

        for (int i = 0; i < apontaList.size(); i++) {

            ApontMMBean apontMMBean = (ApontMMBean) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontMMBean, apontMMBean.getClass()));

            CabecPneuBean cabecPneuBean = new CabecPneuBean();
            List cabecPneuList = cabecPneuBean.get("idApontCabecPneu", apontMMBean.getIdApontMM());

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

            ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
            List apontImpleList = apontImpleMMBean.get("idApontMM", apontMMBean.getIdApontMM());

            for (int l = 0; l < apontImpleList.size(); l++) {
                apontImpleMMBean = (ApontImpleMMBean) apontImpleList.get(l);
                Gson gsonItemImp = new Gson();
                jsonArrayImplemento.add(gsonItemImp.toJsonTree(apontImpleMMBean, apontImpleMMBean.getClass()));
            }

            apontImpleList.clear();

        }

        apontaList.clear();

        for (int j = 0; j < movLeiraList.size(); j++) {
            MovLeiraBean movLeiraBean = (MovLeiraBean) movLeiraList.get(j);
            Gson gsonRend = new Gson();
            jsonArrayMovLeira.add(gsonRend.toJsonTree(movLeiraBean, movLeiraBean.getClass()));
        }

        movLeiraList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("implemento", jsonArrayImplemento);

        JsonObject jsonMovLeira = new JsonObject();
        jsonMovLeira.add("movleira", jsonArrayMovLeira);

        JsonObject jsonCabecPneu = new JsonObject();
        jsonCabecPneu.add("cabecpneu", jsonArrayCabecPneu);

        JsonObject jsonItemPneu = new JsonObject();
        jsonItemPneu.add("itempneu", jsonArrayItemPneu);

        return jsonAponta.toString() + "|" + jsonImplemento.toString() + "#" + jsonMovLeira.toString() + "?" + jsonCabecPneu.toString() + "@" + jsonItemPneu.toString();

    }

    public void updateApont(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            int pos2 = retorno.indexOf("|") + 1;

            String objPrinc = retorno.substring(pos1, pos2);
            String objSeg = retorno.substring(pos2);

            JSONObject jObjApontMM = new JSONObject(objPrinc);
            JSONArray jsonArrayApontMM = jObjApontMM.getJSONArray("apont");

            if (jsonArrayApontMM.length() > 0) {

                ArrayList<Long> rList = new ArrayList<Long>();
                ApontMMBean apontMMBean = new ApontMMBean();

                for (int i = 0; i < jsonArrayApontMM.length(); i++) {

                    JSONObject objApont = jsonArrayApontMM.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontMMBean = gsonApont.fromJson(objApont.toString(), ApontMMBean.class);

                    rList.add(apontMMBean.getIdApontMM());

                }

                List apontMMList = apontMMBean.in("idApontMM", rList);

                for (int i = 0; i < apontMMList.size(); i++) {

                    apontMMBean = (ApontMMBean) apontMMList.get(i);
                    apontMMBean.setStatusApontMM(2L);
                    apontMMBean.update();

                }

                ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
                List apontImpleList = apontImpleMMBean.in("idApontImpleMM", rList);

                for (int l = 0; l < apontImpleList.size(); l++) {
                    apontImpleMMBean = (ApontImpleMMBean) apontImpleList.get(l);
                    apontImpleMMBean.delete();
                }

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

                rList.clear();

            }

            JSONObject jObjMovLeira = new JSONObject(objSeg);
            JSONArray jsonArrayMovLeira = jObjMovLeira.getJSONArray("movLeira");

            if (jsonArrayMovLeira.length() > 0) {

                ArrayList<Long> movLeiraArrayList = new ArrayList<Long>();
                MovLeiraBean movLeiraBean = new MovLeiraBean();

                for (int i = 0; i < jsonArrayMovLeira.length(); i++) {

                    JSONObject objMovLeira = jsonArrayMovLeira.getJSONObject(i);
                    Gson gsonMovLeira = new Gson();
                    movLeiraBean = gsonMovLeira.fromJson(objMovLeira.toString(), MovLeiraBean.class);

                    movLeiraArrayList.add(movLeiraBean.getIdMovLeira());

                }

                List movLeiraList = movLeiraBean.in("idMovLeira", movLeiraArrayList);

                for (int i = 0; i < movLeiraList.size(); i++) {

                    movLeiraBean = (MovLeiraBean) movLeiraList.get(i);
                    movLeiraBean.setStatusMovLeira(2L);
                    movLeiraBean.update();

                }

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public ApontMMBean createApont(BoletimCTR boletimCTR){
        ApontMMBean apontMMBean = new ApontMMBean();
        List apontList = getListAllApont(boletimCTR.getIdBol());
        if (apontList.size() == 0) {
            apontMMBean.setIdBolApontMM(boletimCTR.getIdBol());
            apontMMBean.setIdExtBolApontMM(boletimCTR.getIdExtBol());
            apontMMBean.setOsApontMM(boletimCTR.getOS());
            apontMMBean.setAtivApontMM(boletimCTR.getAtiv());
            apontMMBean.setParadaApontMM(0L);
            apontMMBean.setDthrApontMM(Tempo.getInstance().dataComHora().getDataHora());
            apontMMBean.setStatusConApontMM(boletimCTR.getStatusConBol());
            apontMMBean.setStatusApontMM(1L);
            apontMMBean.setStatusDtHrApontMM(Tempo.getInstance().dataComHora().getStatus());
            apontMMBean.setLongitudeApontMM(boletimCTR.getLongitude());
            apontMMBean.setLatitudeApontMM(boletimCTR.getLatitude());
        } else {
            ApontMMBean ultApontTO = (ApontMMBean) apontList.get(apontList.size() - 1);
            apontMMBean = ultApontTO;
            apontMMBean.setStatusApontMM(1L);
        }
        apontMMBean.setTransbApontMM(0L);
        apontList.clear();
        return apontMMBean;
    }

}
