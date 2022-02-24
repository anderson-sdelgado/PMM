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

    public BoletimMMFertBean getBolMMFert() {
        if (boletimMMFertBean == null)
            boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean;
    }

    public boolean verBolAbertoMMFert(){
        List<BoletimMMFertBean> boletimMMList = bolAbertoMMFertList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public boolean verBolFechadoMMFert(){
        List<BoletimMMFertBean> boletimMMList = bolFechadoMMFertList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public BoletimMMFertBean getBolAbertoMMFert(){
        List<BoletimMMFertBean> boletimMMList = bolAbertoMMFertList();
        BoletimMMFertBean boletimMMFertBean = boletimMMList.get(0);
        boletimMMList.clear();
        return boletimMMFertBean;
    }

    public List<BoletimMMFertBean> bolAbertoMMFertList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBolAberto());
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get(pesqArrayList);
    }

    public List<BoletimMMFertBean> bolFechadoMMFertList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBolFechado());
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get(pesqArrayList);
    }

    public ArrayList<String> boletimAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("BOLETIM");
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMList = boletimMMFertBean.orderBy("idBolMMFert", true);
        for (BoletimMMFertBean boletimMMFertBeanBD : boletimMMList) {
            dadosArrayList.add(dadosBolMMFert(boletimMMFertBeanBD));
        }
        boletimMMList.clear();
        return dadosArrayList;
    }

    public void salvarBolAbertoMMFert(Long tipoEquip, Long os, Long ativ, Long statusCon, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBolAbertoMMFert(Long tipoEquip, Long os, Long ativ, Long statusCon, String activity){", activity);
        if(!verBolAbertoMMFert()){
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

    public void salvarBolFechadoMMFert(String activity) {

        List<BoletimMMFertBean> boletimMMList = bolAbertoMMFertList();

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

        EnvioDadosServ.getInstance().envioDados(activity);

    }

    public void updateBolMMFertEnvio(Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(idBol));

        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMFertList = boletimMMFertBean.get(pesqArrayList);
        boletimMMFertBean = boletimMMFertList.get(0);
        boletimMMFertBean.setStatusBolMMFert(3L);
        boletimMMFertBean.update();
        boletimMMFertList.clear();

    }

    public void deleteBolMMFert(Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(idBol));

        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMFertList = boletimMMFertBean.get(pesqArrayList);
        boletimMMFertBean = boletimMMFertList.get(0);
        boletimMMFertBean.delete();
        boletimMMFertList.clear();

    }

    public ArrayList<BoletimMMFertBean> bolExcluirArrayList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBolEnviado());

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

    public ArrayList<Long> idBolArrayList(List<BoletimMMFertBean> boletimMMList){
        ArrayList<Long> idBolList = new ArrayList<Long>();
        for (BoletimMMFertBean boletimMMFertBean : boletimMMList) {
            idBolList.add(boletimMMFertBean.getIdBolMMFert());
        }
        boletimMMList.clear();
        return idBolList;
    }

    public ArrayList<BoletimMMFertBean> bolMMFertArrayList(String objeto) throws Exception {

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

    public String dadosEnvioBolMMFertAberto(){
        return dadosBolMMFert(bolAbertoMMFertList());
    }

    public String dadosEnvioBolMMFertFechado(){
        return dadosBolMMFert(bolFechadoMMFertList());
    }

    private String dadosBolMMFert(BoletimMMFertBean boletimMMFert){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(boletimMMFert, boletimMMFert.getClass()).toString();
    }

    private String dadosBolMMFert(List<BoletimMMFertBean> boletimMMFertList){

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

    public void updateBolAbertoMMFert(String objeto) throws Exception {

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

    private EspecificaPesquisa getPesqIdBol(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolMMFert");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqBolAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMMFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqBolFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMMFert");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqBolEnviado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMMFert");
        pesquisa.setValor(3L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
