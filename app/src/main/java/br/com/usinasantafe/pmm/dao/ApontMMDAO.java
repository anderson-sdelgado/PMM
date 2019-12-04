package br.com.usinasantafe.pmm.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.pmm.bean.variaveis.CabecPneuTO;
import br.com.usinasantafe.pmm.bean.variaveis.ItemPneuTO;
import br.com.usinasantafe.pmm.bean.variaveis.MovLeiraTO;
import br.com.usinasantafe.pmm.control.ApontInterface;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.bean.variaveis.ImpleMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontImpleMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontMMTO;

public class ApontMMDAO implements ApontInterface {

    public ApontMMDAO() {
    }

    @Override
    public boolean verApontAberto() {
        ApontMMTO apontMMTO = new ApontMMTO();
        List apontaMMList = apontMMTO.get("statusApontMM", 0L);
        boolean ret = (apontaMMList.size() > 0);
        apontaMMList.clear();
        return ret;
    }

    public ApontMMTO getApontMMAberto(){
        ApontMMTO apontMMTO = new ApontMMTO();
        List apontaMMList = apontMMTO.get("statusApontMM", 0L);
        apontMMTO = (ApontMMTO) apontaMMList.get(0);
        apontaMMList.clear();
        return apontMMTO;
    }

    @Override
    public Long getIdApontAberto() {
        ApontMMTO apontMMTO = getApontMMAberto();
        return apontMMTO.getIdApontMM();
    }

    public List getListAllApont(Long idBolMM){
        ApontMMTO apontMMTO = new ApontMMTO();
        return apontMMTO.getAndOrderBy("idBolApontMM", idBolMM, "idApontMM", true);
    }

    public void salvarApont(ApontMMTO apontMMTO){

        apontMMTO.insert();

        List apontaList = apontMMTO.get("dthrApontMM", apontMMTO.getDthrApontMM());
        apontMMTO = (ApontMMTO) apontaList.get(0);
        apontaList.clear();

        ImpleMMTO impleMMTO = new ImpleMMTO();
        List impleList = impleMMTO.all();

        for (int i = 0; i < impleList.size(); i++) {
            impleMMTO = (ImpleMMTO) impleList.get(i);
            ApontImpleMMTO apontImpleMMTO = new ApontImpleMMTO();
            apontImpleMMTO.setIdApontMM(apontMMTO.getIdApontMM());
            apontImpleMMTO.setCodEquipImpleMM(impleMMTO.getCodEquipImpleMM());
            apontImpleMMTO.setPosImpleMM(impleMMTO.getPosImpleMM());
            apontImpleMMTO.setDthrImpleMM(apontMMTO.getDthrApontMM());
            apontImpleMMTO.insert();
        }

    }

    public void updApont(ApontMMTO apontMMTO){

        apontMMTO.setStatusApontMM(1L);
        apontMMTO.update();

        List apontaList = apontMMTO.get("dthrApontMM", apontMMTO.getDthrApontMM());
        apontMMTO = (ApontMMTO) apontaList.get(0);
        apontaList.clear();

        ImpleMMTO impleMMTO = new ImpleMMTO();
        List impleList = impleMMTO.all();

        for (int i = 0; i < impleList.size(); i++) {
            impleMMTO = (ImpleMMTO) impleList.get(i);
            ApontImpleMMTO apontImpleMMTO = new ApontImpleMMTO();
            apontImpleMMTO.setIdApontMM(apontMMTO.getIdApontMM());
            apontImpleMMTO.setCodEquipImpleMM(impleMMTO.getCodEquipImpleMM());
            apontImpleMMTO.setPosImpleMM(impleMMTO.getPosImpleMM());
            apontImpleMMTO.setDthrImpleMM(apontMMTO.getDthrApontMM());
            apontImpleMMTO.insert();
        }

    }


    public int verTransbordo(String data) {

        int retorno = 0;

        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        List apontMMList = getListAllApont(boletimMMDAO.getIdBolAberto());

        if (apontMMList.size() > 0) {
            ApontMMTO apontaMMTO = (ApontMMTO) apontMMList.get(apontMMList.size() - 1);
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

            dtStr = Tempo.getInstance().dataComHora();

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

        ApontMMTO apontMMTO = new ApontMMTO();

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

        return apontMMTO.get(pesqArrayList);

    }

    public List getListApontEnvio() {
        ApontMMTO apontMMTO = new ApontMMTO();
        return apontMMTO.get("statusApontMM", 1L);
    }

    public String dadosEnvioApontMM(List apontaList, List movLeiraList){

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();
        JsonArray jsonArrayMovLeira = new JsonArray();
        JsonArray jsonArrayCabecPneu = new JsonArray();
        JsonArray jsonArrayItemPneu = new JsonArray();

        for (int i = 0; i < apontaList.size(); i++) {

            ApontMMTO apontMMTO = (ApontMMTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontMMTO, apontMMTO.getClass()));

            CabecPneuTO cabecPneuTO = new CabecPneuTO();
            List cabecPneuList = cabecPneuTO.get("idApontCabecPneu", apontMMTO.getIdApontMM());

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

            ApontImpleMMTO apontImpleMMTO = new ApontImpleMMTO();
            List apontImpleList = apontImpleMMTO.get("idApontMM", apontMMTO.getIdApontMM());

            for (int l = 0; l < apontImpleList.size(); l++) {
                apontImpleMMTO = (ApontImpleMMTO) apontImpleList.get(l);
                Gson gsonItemImp = new Gson();
                jsonArrayImplemento.add(gsonItemImp.toJsonTree(apontImpleMMTO, apontImpleMMTO.getClass()));
            }

            apontImpleList.clear();

        }

        apontaList.clear();

        for (int j = 0; j < movLeiraList.size(); j++) {
            MovLeiraTO movLeiraTO = (MovLeiraTO) movLeiraList.get(j);
            Gson gsonRend = new Gson();
            jsonArrayMovLeira.add(gsonRend.toJsonTree(movLeiraTO, movLeiraTO.getClass()));
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
                ApontMMTO apontMMTO = new ApontMMTO();

                for (int i = 0; i < jsonArrayApontMM.length(); i++) {

                    JSONObject objApont = jsonArrayApontMM.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontMMTO = gsonApont.fromJson(objApont.toString(), ApontMMTO.class);

                    rList.add(apontMMTO.getIdApontMM());

                }

                List apontMMList = apontMMTO.in("idApontMM", rList);

                for (int i = 0; i < apontMMList.size(); i++) {

                    apontMMTO = (ApontMMTO) apontMMList.get(i);
                    apontMMTO.setStatusApontMM(2L);
                    apontMMTO.update();

                }

                ApontImpleMMTO apontImpleMMTO = new ApontImpleMMTO();
                List apontImpleList = apontImpleMMTO.in("idApontImpleMM", rList);

                for (int l = 0; l < apontImpleList.size(); l++) {
                    apontImpleMMTO = (ApontImpleMMTO) apontImpleList.get(l);
                    apontImpleMMTO.delete();
                }

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

                rList.clear();

            }

            JSONObject jObjMovLeira = new JSONObject(objSeg);
            JSONArray jsonArrayMovLeira = jObjMovLeira.getJSONArray("movLeira");

            if (jsonArrayMovLeira.length() > 0) {

                ArrayList<Long> movLeiraArrayList = new ArrayList<Long>();
                MovLeiraTO movLeiraTO = new MovLeiraTO();

                for (int i = 0; i < jsonArrayMovLeira.length(); i++) {

                    JSONObject objMovLeira = jsonArrayMovLeira.getJSONObject(i);
                    Gson gsonMovLeira = new Gson();
                    movLeiraTO = gsonMovLeira.fromJson(objMovLeira.toString(), MovLeiraTO.class);

                    movLeiraArrayList.add(movLeiraTO.getIdMovLeira());

                }

                List movLeiraList = movLeiraTO.in("idMovLeira", movLeiraArrayList);

                for (int i = 0; i < movLeiraList.size(); i++) {

                    movLeiraTO = (MovLeiraTO) movLeiraList.get(i);
                    movLeiraTO.setStatusMovLeira(2L);
                    movLeiraTO.update();

                }

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

}
