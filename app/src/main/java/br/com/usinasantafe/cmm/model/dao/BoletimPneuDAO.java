package br.com.usinasantafe.cmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimPneuBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.util.Tempo;

public class BoletimPneuDAO {

    public BoletimPneuDAO() {
    }

    public boolean verifBolPneuAberto() {
        List<BoletimPneuBean> boletimPneuList = bolPneuAbertoList();
        boolean retorno = boletimPneuList.size() > 0;
        boletimPneuList.clear();
        return retorno;
    }

    public BoletimPneuBean getBolPneuAberto() {
        List<BoletimPneuBean> boletimPneuList = bolPneuAbertoList();
        BoletimPneuBean boletimPneuBean = boletimPneuList.get(0);
        boletimPneuList.clear();
        return boletimPneuBean;
    }


    public boolean verifBolPneuFechado() {
        List<BoletimPneuBean> boletimPneuList = bolPneuFechadoList();
        boolean retorno = boletimPneuList.size() > 0;
        boletimPneuList.clear();
        return retorno;
    }

    public void abrirBolPneu(Long idBolMMFert, Long matricFunc, Long idEquip, Long tipo) {
        Long dthr = Tempo.getInstance().dthrAtualLong();
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        boletimPneuBean.setIdBolMMFertPneu(idBolMMFert);
        boletimPneuBean.setMatricFuncBolPneu(matricFunc);
        boletimPneuBean.setIdEquipBolPneu(idEquip);
        boletimPneuBean.setTipoBolPneu(tipo);
        boletimPneuBean.setDthrLongBolPneu(dthr);
        boletimPneuBean.setDthrBolPneu(Tempo.getInstance().dthrLongToString(dthr));
        boletimPneuBean.setStatusBolPneu(1L);
        boletimPneuBean.insert();
    }

    public void fecharBolPneu() {
        BoletimPneuBean boletimPneuBean = getBolPneuAberto();
        boletimPneuBean.setStatusBolPneu(2L);
        boletimPneuBean.update();
    }

    public void deleteBolPneuAberto() {
        BoletimPneuBean boletimPneuBean = getBolPneuAberto();
        boletimPneuBean.delete();
    }

    public void updateBolPneu(ArrayList<Long> idBoletimPneuArrayList) {

        List<BoletimPneuBean> boletimPneuList = bolPneuList(idBoletimPneuArrayList);

        for (BoletimPneuBean boletimPneuBean : boletimPneuList) {
            boletimPneuBean.setStatusBolPneu(3L);
            boletimPneuBean.update();
        }

        boletimPneuList.clear();
        idBoletimPneuArrayList.clear();

    }

    public void updateBolPneu(Long idBoletimPneu) {

        List<BoletimPneuBean> boletimPneuList = bolPneuList(idBoletimPneu);
        BoletimPneuBean boletimPneuBean = boletimPneuList.get(0);
        boletimPneuBean.setStatusBolPneu(3L);
        boletimPneuBean.update();

    }

    public List<BoletimPneuBean> bolPneuAbertoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBolPneuAberto());
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.get(pesqArrayList);
    }

    public List<BoletimPneuBean> bolPneuFechadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBolPneuFechado());
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.get(pesqArrayList);
    }

    public List<BoletimPneuBean> bolPneuList(Long idBolMMFert) {
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.get("idBolMMFertPneu", idBolMMFert);
    }

    public List<BoletimPneuBean> bolPneuEnvioList(ArrayList<Long> idBolMMFertList) {
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.in("idBolMMFertPneu", idBolMMFertList);
    }

    public List<BoletimPneuBean> bolPneuEnvioListRetrofit(Long idBolMMFert) {
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.get("idBolMMFertPneu", idBolMMFert);
    }


    public List<BoletimPneuBean> bolPneuList(ArrayList<Long> idBolPneuArrayList) {
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.in("idBolPneu", idBolPneuArrayList);
    }

    public ArrayList<Long> idBolPneuArrayList(List<BoletimPneuBean> boletimPneuList) {
        ArrayList<Long> idBolList = new ArrayList<Long>();
        for (BoletimPneuBean boletimPneuBean : boletimPneuList) {
            idBolList.add(boletimPneuBean.getIdBolPneu());
        }
        boletimPneuList.clear();
        return idBolList;
    }

    public ArrayList<Long> idBolPneuArrayList(String objeto) throws Exception {

        ArrayList<Long> idBoletimPneuArrayList = new ArrayList<Long>();

        JSONObject jObjBoletimPneu = new JSONObject(objeto);
        JSONArray jsonArrayBoletimPneu = jObjBoletimPneu.getJSONArray("boletimpneu");

        for (int i = 0; i < jsonArrayBoletimPneu.length(); i++) {

            JSONObject objBoletimPneu = jsonArrayBoletimPneu.getJSONObject(i);
            Gson gsonBoletimPneu = new Gson();
            BoletimPneuBean boletimPneuBean = gsonBoletimPneu.fromJson(objBoletimPneu.toString(), BoletimPneuBean.class);

            idBoletimPneuArrayList.add(boletimPneuBean.getIdBolPneu());

        }

        return idBoletimPneuArrayList;

    }

    public void deleteBolPneu(Long idBolMMFert) {

        List<BoletimPneuBean> boletimPneuList = bolPneuList(idBolMMFert);

        for (BoletimPneuBean boletimPneuBeanBD : boletimPneuList) {
            boletimPneuBeanBD.delete();
        }

        boletimPneuList.clear();

    }

    public String dadosEnvioBolPneu(List<BoletimPneuBean> boletimPneuList) {

        JsonArray jsonArrayBoletimPneu = new JsonArray();

        for (BoletimPneuBean boletimPneuBean : boletimPneuList) {
            Gson gsonItemImp = new Gson();
            jsonArrayBoletimPneu.add(gsonItemImp.toJsonTree(boletimPneuBean, boletimPneuBean.getClass()));
        }

        boletimPneuList.clear();

        JsonObject jsonBoletimPneu = new JsonObject();
        jsonBoletimPneu.add("boletimpneu", jsonArrayBoletimPneu);

        return jsonBoletimPneu.toString();

    }

    private EspecificaPesquisa getPesqBolPneuAberto() {
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolPneu");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqBolPneuFechado() {
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolPneu");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }
}
