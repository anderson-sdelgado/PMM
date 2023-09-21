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

    public boolean verifBoletimPneuAberto(){
        List<BoletimPneuBean> boletimPneuList = boletimPneuAbertoList();
        boolean retorno = boletimPneuList.size() > 0;
        boletimPneuList.clear();
        return retorno;
    }

    public boolean verifBoletimPneuFechado(){
        List<BoletimPneuBean> boletimPneuList = boletimPneuFechadoList();
        boolean retorno = boletimPneuList.size() > 0;
        boletimPneuList.clear();
        return retorno;
    }

    public void abrirBoletimPneu(Long idApontMMFert, Long matricFunc, Long idEquip){
        Long dthr = Tempo.getInstance().dthrAtualLong();
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        boletimPneuBean.setIdBolMMPneu(idApontMMFert);
        boletimPneuBean.setMatricFuncBolPneu(matricFunc);
        boletimPneuBean.setIdEquipBolPneu(idEquip);
        boletimPneuBean.setDthrLongBolPneu(dthr);
        boletimPneuBean.setDthrBolPneu(Tempo.getInstance().dthrLongToString(dthr));
        boletimPneuBean.setStatusBolPneu(1L);
        boletimPneuBean.insert();
    }

    public void fecharBoletimPneu(){
        BoletimPneuBean boletimPneuBean = getBoletimPneuAberto();
        boletimPneuBean.setStatusBolPneu(2L);
        boletimPneuBean.update();
    }

    public void deleteBoletimPneuAberto(){
        BoletimPneuBean boletimPneuBean = getBoletimPneuAberto();
        boletimPneuBean.delete();
    }

    public void updateBoletimPneu(ArrayList<Long> idBoletimPneuArrayList){

        List<BoletimPneuBean> boletimPneuList = boletimPneuList(idBoletimPneuArrayList);

        for (BoletimPneuBean boletimPneuBean : boletimPneuList) {
            boletimPneuBean.setStatusBolPneu(3L);
            boletimPneuBean.update();
        }

        boletimPneuList.clear();
        idBoletimPneuArrayList.clear();

    }

    public BoletimPneuBean getBoletimPneuAberto(){
        List<BoletimPneuBean> boletimPneuList = boletimPneuAbertoList();
        BoletimPneuBean boletimPneuBean = boletimPneuList.get(0);
        boletimPneuList.clear();
        return boletimPneuBean;
    }

    public List<BoletimPneuBean> boletimPneuAbertoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBoletimPneuAberto());
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.get(pesqArrayList);
    }

    public List<BoletimPneuBean> boletimPneuFechadoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBoletimPneuFechado());
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.get(pesqArrayList);
    }

    public List<BoletimPneuBean> boletimPneuEnvioList(ArrayList<Long> idBolMMList){
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.in("idBolMMPneu", idBolMMList);
    }

    public List<BoletimPneuBean> boletimPneuList(ArrayList<Long> idBolPneuArrayList){
        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        return boletimPneuBean.in("idBolPneu", idBolPneuArrayList);
    }

    public ArrayList<Long> idBoletimPneuArrayList(List<BoletimPneuBean> boletimPneuList){
        ArrayList<Long> idBolList = new ArrayList<Long>();
        for (BoletimPneuBean boletimPneuBean : boletimPneuList) {
            idBolList.add(boletimPneuBean.getIdBolPneu());
        }
        boletimPneuList.clear();
        return idBolList;
    }

    public ArrayList<Long> idBoletimPneuArrayList(String objeto) throws Exception {

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

    public void deleteBoletimPneu(ArrayList<Long> idApontBolPneuArrayList){

        BoletimPneuBean boletimPneuBean = new BoletimPneuBean();
        List<BoletimPneuBean> boletimPneuList = boletimPneuBean.in("idApontBolPneu", idApontBolPneuArrayList);

        for (BoletimPneuBean boletimPneuBeanBD : boletimPneuList) {
            boletimPneuBeanBD.delete();
        }

        idApontBolPneuArrayList.clear();

    }

    public String dadosEnvioBoletimPneu(List<BoletimPneuBean> boletimPneuList){

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

    private EspecificaPesquisa getPesqBoletimPneuAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolPneu");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqBoletimPneuFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolPneu");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }
}
