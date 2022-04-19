package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;

public class BoletimMMFertDAO {

    private BoletimMMFertBean boletimMMFertBean;

    public BoletimMMFertDAO() {
    }

    public void setBolMMFert(){
        boletimMMFertBean = new BoletimMMFertBean();
    }

    public BoletimMMFertBean getBoletimMMFert() {
        if (boletimMMFertBean == null)
            boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean;
    }

    public boolean verBoletimMMFertAberto(){
        List<BoletimMMFertBean> boletimMMList = boletimMMFertAbertoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public boolean verBoletimMMFertFechado(){
        List<BoletimMMFertBean> boletimMMList = boletimMMFertFechadoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public BoletimMMFertBean getBoletimMMFertAberto(){
        List<BoletimMMFertBean> boletimMMList = boletimMMFertAbertoList();
        BoletimMMFertBean boletimMMFertBean = boletimMMList.get(0);
        boletimMMList.clear();
        return boletimMMFertBean;
    }

    public List<BoletimMMFertBean> boletimMMFertAbertoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBoletimAberto());
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get(pesqArrayList);
    }

    public List<BoletimMMFertBean> boletimMMFertFechadoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBoletimFechado());
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get(pesqArrayList);
    }

    public ArrayList<String> boletimAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("BOLETIM");
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMList = boletimMMFertBean.orderBy("idBolMMFert", true);
        for (BoletimMMFertBean boletimMMFertBeanBD : boletimMMList) {
            dadosArrayList.add(dadosBoletimMMFert(boletimMMFertBeanBD));
        }
        boletimMMList.clear();
        return dadosArrayList;
    }

    public void salvarBoletimMMFertAberto(Long tipoEquip, Long os, Long ativ, Long statusCon, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBolAbertoMMFert(Long tipoEquip, Long os, Long ativ, Long statusCon, String activity){", activity);
        if(!verBoletimMMFertAberto()){
            String dthr = Tempo.getInstance().dthr();
            LogProcessoDAO.getInstance().insertLogProcesso("if(!verBolAbertoMMFert()){ + Data Inicial Boletim = " + dthr, activity);
            boletimMMFertBean.setTipoBolMMFert(tipoEquip);
            boletimMMFertBean.setOsBolMMFert(os);
            boletimMMFertBean.setAtivPrincBolMMFert(ativ);
            boletimMMFertBean.setStatusConBolMMFert(statusCon);
            boletimMMFertBean.setDthrInicialBolMMFert(dthr);
            boletimMMFertBean.setDthrLongFinalBolMMFert(0L);
            boletimMMFertBean.insert();
        }
    }

    public void salvarBoletimMMFertFechado(String activity) {

        List<BoletimMMFertBean> boletimMMList = boletimMMFertAbertoList();

        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBolFechadoMMFert(String activity) {\n" +
                "        List<BoletimMMFertBean> boletimMMList = bolAbertoMMFertList();", activity);
        for(BoletimMMFertBean boletimMMFertBeanBD : boletimMMList){
            LogProcessoDAO.getInstance().insertLogProcesso("for(BoletimMMFertBean boletimMMFertBeanBD : boletimMMList){ + Boletins = " + boletimMMFertBeanBD.getIdBolMMFert() , activity);
            boletimMMFertBeanBD.setDthrFinalBolMMFert(Tempo.getInstance().dthr());
            boletimMMFertBeanBD.setStatusBolMMFert(2L);
            boletimMMFertBeanBD.setHodometroFinalBolMMFert(boletimMMFertBean.getHodometroFinalBolMMFert());
            boletimMMFertBeanBD.setDthrLongFinalBolMMFert(Tempo.getInstance().dthrStringToLong(Tempo.getInstance().dthr()));
            boletimMMFertBeanBD.update();
        }

        boletimMMList.clear();


    }

    public void updateBoletimMMFertEnvio(ArrayList<BoletimMMFertBean> boletimArrayList){

        for (BoletimMMFertBean boletimMMFertBean : boletimArrayList) {

            ArrayList pesqArrayList = new ArrayList();
            pesqArrayList.add(getPesqIdBoletim(boletimMMFertBean.getIdBolMMFert()));

            List<BoletimMMFertBean> boletimMMFertList = boletimMMFertBean.get(pesqArrayList);
            BoletimMMFertBean boletimMMFertDB = boletimMMFertList.get(0);
            boletimMMFertDB.setStatusBolMMFert(3L);
            boletimMMFertDB.update();
            boletimMMFertList.clear();
        }



    }

    public void deleteBoletimMMFert(Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBoletim(idBol));

        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMFertList = boletimMMFertBean.get(pesqArrayList);
        boletimMMFertBean = boletimMMFertList.get(0);
        boletimMMFertBean.delete();
        boletimMMFertList.clear();

    }

    public ArrayList<BoletimMMFertBean> boletimExcluirArrayList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBoletimEnviado());

        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMFertList =  boletimMMFertBean.get(pesqArrayList);

        ArrayList<BoletimMMFertBean> boletimMMFertArrayList = new ArrayList<>();
        for (BoletimMMFertBean boletimMMFertBeanBD : boletimMMFertList) {
            if(boletimMMFertBeanBD.getDthrLongFinalBolMMFert() < Tempo.getInstance().dthrLongDiaMenos(3)) {
                boletimMMFertArrayList.add(boletimMMFertBeanBD);
            }
        }
        boletimMMFertList.clear();
        return boletimMMFertArrayList;

    }

    public ArrayList<Long> idBoletimArrayList(List<BoletimMMFertBean> boletimMMList){
        ArrayList<Long> idBolList = new ArrayList<Long>();
        for (BoletimMMFertBean boletimMMFertBean : boletimMMList) {
            idBolList.add(boletimMMFertBean.getIdBolMMFert());
        }
        boletimMMList.clear();
        return idBolList;
    }

    public ArrayList<BoletimMMFertBean> boletimMMFertArrayList(String objeto) throws Exception {

        ArrayList<BoletimMMFertBean> boletimArrayList = new ArrayList<>();

        JSONObject jObjBolMM = new JSONObject(objeto);
        JSONArray jsonArrayBolMM = jObjBolMM.getJSONArray("boletim");

        for (int i = 0; i < jsonArrayBolMM.length(); i++) {

            JSONObject objBol = jsonArrayBolMM.getJSONObject(i);
            Gson gsonBol = new Gson();
            BoletimMMFertBean boletimMMFertBean = gsonBol.fromJson(objBol.toString(), BoletimMMFertBean.class);

            boletimArrayList.add(boletimMMFertBean);

        }

        return boletimArrayList;

    }

    public String dadosEnvioBoletimMMFertAberto(){
        return dadosBoletimMMFert(boletimMMFertAbertoList());
    }

    public String dadosEnvioBoletimMMFertFechado(){
        return dadosBoletimMMFert(boletimMMFertFechadoList());
    }

    private String dadosBoletimMMFert(BoletimMMFertBean boletimMMFert){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(boletimMMFert, boletimMMFert.getClass()).toString();
    }

    private String dadosBoletimMMFert(List<BoletimMMFertBean> boletimMMFertList){

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

    public void updateBoletimMMFertAberto(String objeto) throws Exception {

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

    private EspecificaPesquisa getPesqIdBoletim(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolMMFert");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqBoletimAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMMFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqBoletimFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMMFert");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqBoletimEnviado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMMFert");
        pesquisa.setValor(3L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
