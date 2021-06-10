package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.pmm.util.Tempo;

public class BoletimMMFertDAO {

    private BoletimMMFertBean boletimMMFertBean;

    public BoletimMMFertDAO() {
    }

    public void setBoletimMMBean(){
        boletimMMFertBean = new BoletimMMFertBean();
    }

    public BoletimMMFertBean getBoletimMMBean() {
        if (boletimMMFertBean == null)
            boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean;
    }

    public boolean verBolMMAberto(){
        List<BoletimMMFertBean> boletimMMList = bolMMFertAbertoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public boolean verBolMMFechado(){
        List<BoletimMMFertBean> boletimMMList = bolMMFertFechadoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public BoletimMMFertBean getBolMMFertAberto(){
        List<BoletimMMFertBean> boletimMMList = bolMMFertAbertoList();
        BoletimMMFertBean boletimMMFertBean = boletimMMList.get(0);
        boletimMMList.clear();
        return boletimMMFertBean;
    }

    public List<BoletimMMFertBean> bolMMFertAbertoList(){
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get("statusBolMMFert", 1L);
    }

    public List<BoletimMMFertBean> bolMMFertFechadoList(){
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get("statusBolMMFert", 2L);
    }

    public void atualQtdeApontBol(){
        BoletimMMFertBean boletimMMFertBean = getBolMMFertAberto();
        boletimMMFertBean.setQtdeApontBolMMFert(boletimMMFertBean.getQtdeApontBolMMFert() + 1L);
        boletimMMFertBean.update();
    }

    public void salvarBolMMAberto(Long tipoEquip, Long os, Long ativ, Long statusCon){
        if(!verBolMMAberto()){
            boletimMMFertBean.setTipoBolMMFert(tipoEquip);
            boletimMMFertBean.setOsBolMMFert(os);
            boletimMMFertBean.setAtivPrincBolMMFert(ativ);
            boletimMMFertBean.setStatusConBolMMFert(statusCon);
            boletimMMFertBean.setDthrInicialBolMMFert(Tempo.getInstance().dataComHora());
            boletimMMFertBean.insert();
        }
    }

    public void salvarBolMMFechado() {

        List<BoletimMMFertBean> boletimMMList = bolMMFertAbertoList();

        for(BoletimMMFertBean boletimMMFertBeanBD : boletimMMList){
            boletimMMFertBeanBD.setDthrFinalBolMMFert(Tempo.getInstance().dataComHora());
            boletimMMFertBeanBD.setStatusBolMMFert(2L);
            boletimMMFertBeanBD.setHodometroFinalBolMMFert(boletimMMFertBean.getHodometroFinalBolMMFert());
            boletimMMFertBeanBD.update();
        }

        boletimMMList.clear();

    }

    public void deleteBol(Long idBol){

        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMFertList = boletimMMFertBean.get("idBolMMFert", idBol);
        boletimMMFertBean = boletimMMFertList.get(0);
        boletimMMFertBean.delete();
        boletimMMFertList.clear();

    }


    public ArrayList<Long> idBolArrayList(List<BoletimMMFertBean> boletimMMList){
        ArrayList<Long> idBolList = new ArrayList<Long>();
        for (BoletimMMFertBean boletimMMFertBean : boletimMMList) {
            idBolList.add(boletimMMFertBean.getIdBolMMFert());
        }
        boletimMMList.clear();
        return idBolList;
    }

    public ArrayList<BoletimMMFertBean> boletimArrayList(String objeto){

        ArrayList<BoletimMMFertBean> boletimArrayList = new ArrayList<>();

        try{

            JSONObject jObjBolMM = new JSONObject(objeto);
            JSONArray jsonArrayBolMM = jObjBolMM.getJSONArray("boletim");

            for (int i = 0; i < jsonArrayBolMM.length(); i++) {

                JSONObject objBol = jsonArrayBolMM.getJSONObject(i);
                Gson gsonBol = new Gson();
                BoletimMMFertBean boletimMMFertBean = gsonBol.fromJson(objBol.toString(), BoletimMMFertBean.class);

                boletimArrayList.add(boletimMMFertBean);

            }

        }
        catch(Exception e){
            LogErroDAO.getInstance().insert(e);
            Tempo.getInstance().setEnvioDado(true);
        }

        return boletimArrayList;

    }

    public String dadosEnvioBolAberto(){
        return dadosEnvioBol(bolMMFertAbertoList());
    }

    public String dadosEnvioBolFechado(){
        return dadosEnvioBol(bolMMFertFechadoList());
    }

    private String dadosEnvioBol(List<BoletimMMFertBean> boletimMMFertList){

        JsonArray jsonArrayBoletim = new JsonArray();

        for (BoletimMMFertBean boletimMMFertBean : boletimMMFertList) {
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMFertBean, boletimMMFertBean.getClass()));
        }

        boletimMMFertList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        return jsonBoletim.toString();
    }

    public void updateBolAberto(String objeto) {

        try{

            JSONObject jObjBolMM = new JSONObject(objeto);
            JSONArray jsonArrayBolMM = jObjBolMM.getJSONArray("boletim");

            for (int i = 0; i < jsonArrayBolMM.length(); i++) {

                JSONObject objBol = jsonArrayBolMM.getJSONObject(i);
                Gson gsonBol = new Gson();
                BoletimMMFertBean boletimMMFertBean = gsonBol.fromJson(objBol.toString(), BoletimMMFertBean.class);

                List<BoletimMMFertBean> boletimMMFertList = boletimMMFertBean.get("idBolMMFert", boletimMMFertBean.getIdBolMMFert());
                BoletimMMFertBean boletimMMFertBD = boletimMMFertList.get(0);
                boletimMMFertList.clear();

                boletimMMFertBD.setIdExtBolMMFert(boletimMMFertBean.getIdExtBolMMFert());
                boletimMMFertBD.update();

            }

        }
        catch(Exception e){
            LogErroDAO.getInstance().insert(e);
            Tempo.getInstance().setEnvioDado(true);
        }
    }

}
