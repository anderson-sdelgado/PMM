package br.com.usinasantafe.cmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.util.Tempo;

public class BoletimMMFertDAO {

    public BoletimMMFertDAO() {
    }

    public boolean verBolMMFertAberto(){
        List<BoletimMMFertBean> boletimMMList = bolMMFertAbertoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public boolean verBolMMFertEnviar(){
        List<BoletimMMFertBean> boletimMMList = bolMMFertListEnviar();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public BoletimMMFertBean getBolMMFert(Long idBol){
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMList = boletimMMFertBean.get("idBolMMFert", idBol);
        BoletimMMFertBean boletimMMFertBeanBD = boletimMMList.get(0);
        boletimMMList.clear();
        return boletimMMFertBeanBD;
    }

    public BoletimMMFertBean getBolMMFertAberto(Long idEquip){
        List<BoletimMMFertBean> boletimMMList = bolMMFertAbertoList(idEquip);
        BoletimMMFertBean boletimMMFertBean = boletimMMList.get(0);
        boletimMMList.clear();
        return boletimMMFertBean;
    }

    public List<BoletimMMFertBean> bolMMFertAbertoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBolAberto());
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get(pesqArrayList);
    }

    public List<BoletimMMFertBean> bolMMFertAbertoList(Long idEquip){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBolAberto());
        pesqArrayList.add(getPesqIdEquip(idEquip));
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get(pesqArrayList);
    }

    public List<BoletimMMFertBean> bolMMFertAbertoSegList(Long idBolPrinc){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBolAberto());
        pesqArrayList.add(getPesqIdBolPrinc(idBolPrinc));
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get(pesqArrayList);
    }

    public List<BoletimMMFertBean> bolMMFertListEnviar(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBolEnviar());
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        return boletimMMFertBean.get(pesqArrayList);
    }

    public ArrayList<String> bolAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("BOLETIM");
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMList = boletimMMFertBean.orderBy("idBolMMFert", true);
        for (BoletimMMFertBean boletimMMFertBeanBD : boletimMMList) {
            dadosArrayList.add(dadosBolMMFert(boletimMMFertBeanBD));
        }
        boletimMMList.clear();
        return dadosArrayList;
    }

    public void salvarBolMMFertAberto(Long matricFunc, Long idEquip, Long idEquipBomba, Long idTurno
            , Double hodometroInicial, Double longitude, Double latitude
            , Long tipoEquip, Long os, Long ativ, Long statusCon, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBolAbertoMMFert(Long tipoEquip, Long os, Long ativ, Long statusCon, String activity){", activity);
        if(!verBolMMFertAberto()){
            String dthr = Tempo.getInstance().dthrAtualString();
            LogProcessoDAO.getInstance().insertLogProcesso("if(!verBolAbertoMMFert()){ + Data Inicial Boletim = " + dthr, activity);
            BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
            boletimMMFertBean.setMatricFuncBolMMFert(matricFunc);
            boletimMMFertBean.setIdEquipBolMMFert(idEquip);
            boletimMMFertBean.setIdEquipBombaBolMMFert(idEquipBomba);
            boletimMMFertBean.setIdTurnoBolMMFert(idTurno);
            boletimMMFertBean.setHodometroInicialBol(hodometroInicial, longitude, latitude);
            boletimMMFertBean.setTipoBolMMFert(tipoEquip);
            boletimMMFertBean.setOsBolMMFert(os);
            boletimMMFertBean.setAtivPrincBolMMFert(ativ);
            boletimMMFertBean.setStatusConBolMMFert(statusCon);
            boletimMMFertBean.setDthrInicialBolMMFert(dthr);
            boletimMMFertBean.setDthrLongFinalBolMMFert(0L);
            boletimMMFertBean.setStatusBolMMFert(1L);
            boletimMMFertBean.setStatusEnviarMMFert(2L);
            boletimMMFertBean.setIdBolPrincMMFert(0L);
            boletimMMFertBean.insert();
        }
    }

    public void salvarBolMMFertAbertoCarretel(Long matricFunc, Long idEquip, Long idEquipBomba, Long idTurno
            , Double hodometroInicial, Double longitude, Double latitude
            , Long tipoEquip, Long os, Long ativ, Long statusCon, Long idBolPrinc){
            String dthr = Tempo.getInstance().dthrAtualString();
        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        boletimMMFertBean.setMatricFuncBolMMFert(matricFunc);
        boletimMMFertBean.setIdEquipBolMMFert(idEquip);
        boletimMMFertBean.setIdEquipBombaBolMMFert(idEquipBomba);
        boletimMMFertBean.setIdTurnoBolMMFert(idTurno);
        boletimMMFertBean.setHodometroInicialBol(hodometroInicial, longitude, latitude);
        boletimMMFertBean.setTipoBolMMFert(tipoEquip);
        boletimMMFertBean.setOsBolMMFert(os);
        boletimMMFertBean.setAtivPrincBolMMFert(ativ);
        boletimMMFertBean.setStatusConBolMMFert(statusCon);
        boletimMMFertBean.setDthrInicialBolMMFert(dthr);
        boletimMMFertBean.setDthrLongFinalBolMMFert(0L);
        boletimMMFertBean.setStatusBolMMFert(1L);
        boletimMMFertBean.setStatusEnviarMMFert(2L);
        boletimMMFertBean.setIdBolPrincMMFert(idBolPrinc);
        boletimMMFertBean.insert();
    }

    public void salvarBolMMFertFechado(Double hodometroFinal, String activity) {

        List<BoletimMMFertBean> boletimMMList = bolMMFertAbertoList();

        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBolFechadoMMFert(String activity) {\n" +
                "        List<BoletimMMFertBean> boletimMMList = bolAbertoMMFertList();", activity);
        for(BoletimMMFertBean boletimMMFertBeanBD : boletimMMList){
            LogProcessoDAO.getInstance().insertLogProcesso("for(BoletimMMFertBean boletimMMFertBeanBD : boletimMMList){ + Boletins = " + boletimMMFertBeanBD.getIdBolMMFert() , activity);
            boletimMMFertBeanBD.setDthrFinalBolMMFert(Tempo.getInstance().dthrAtualString());
            boletimMMFertBeanBD.setStatusBolMMFert(2L);
            boletimMMFertBeanBD.setStatusEnviarMMFert(1L);
            if(boletimMMFertBeanBD.getIdBolPrincMMFert() == 0L){
                boletimMMFertBeanBD.setHodometroFinalBolMMFert(hodometroFinal);
            } else {
                boletimMMFertBeanBD.setHodometroFinalBolMMFert(0.0);
            }
            boletimMMFertBeanBD.setDthrLongFinalBolMMFert(Tempo.getInstance().dthrStringToLong(Tempo.getInstance().dthrAtualString()));
            boletimMMFertBeanBD.update();
        }
        boletimMMList.clear();
    }

    public void updateBolMMFertEnviar(Long idBoletim){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(idBoletim));

        BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
        List<BoletimMMFertBean> boletimMMFertList = boletimMMFertBean.get(pesqArrayList);
        BoletimMMFertBean boletimMMFertDB = boletimMMFertList.get(0);
        boletimMMFertDB.setStatusEnviarMMFert(1L);
        boletimMMFertDB.update();
        boletimMMFertList.clear();

    }

    public void updateBolMMFertEnviar(BoletimMMFertBean boletimMMFertBean){

        boletimMMFertBean.setStatusEnviarMMFert(1L);
        boletimMMFertBean.update();

    }

    public void updateBolMMFertEnvio(BoletimMMFertBean boletimMMFertBean){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(boletimMMFertBean.getIdBolMMFert()));

        List<BoletimMMFertBean> boletimMMFertList = boletimMMFertBean.get(pesqArrayList);
        BoletimMMFertBean boletimMMFertDB = boletimMMFertList.get(0);
        if(boletimMMFertDB.getStatusBolMMFert() == 2L){
            boletimMMFertDB.setStatusBolMMFert(3L);
        }
        boletimMMFertDB.setStatusEnviarMMFert(2L);
        boletimMMFertDB.update();
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
            if(boletimMMFertBeanBD.getDthrLongFinalBolMMFert() < Tempo.getInstance().dthrLongDiaMenos(15)) {
                boletimMMFertArrayList.add(boletimMMFertBeanBD);
            }
        }
        boletimMMFertList.clear();
        return boletimMMFertArrayList;

    }

    private String dadosBolMMFert(BoletimMMFertBean boletimMMFert){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(boletimMMFert, boletimMMFert.getClass()).toString();
    }

    private EspecificaPesquisa getPesqIdBol(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolMMFert");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdEquip(Long idEquip){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idEquipBolMMFert");
        pesquisa.setValor(idEquip);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdBolPrinc(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolPrincMMFert");
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

    private EspecificaPesquisa getPesqBolEnviado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMMFert");
        pesquisa.setValor(3L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqBolEnviar(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusEnviarMMFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
