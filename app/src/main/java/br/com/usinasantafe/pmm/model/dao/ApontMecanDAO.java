package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMecanBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.Tempo;

public class ApontMecanDAO {

    private ApontMecanBean apontMecanBean;

    public ApontMecanDAO() {
    }

    public void setApontMecanBean() {
        this.apontMecanBean = new ApontMecanBean();
    }

    public ApontMecanBean getApontMecanBean() {
        if (apontMecanBean == null)
            apontMecanBean = new ApontMecanBean();
        return apontMecanBean;
    }

    public void salvarApontMecan(Long seqItemOS, Long idBolMMFert){
        apontMecanBean.setIdBolApontMecan(idBolMMFert);
        apontMecanBean.setItemOSApontMecan(seqItemOS);
        apontMecanBean.setDthrInicialApontMecan(Tempo.getInstance().dthr());
        apontMecanBean.setStatusApontMecan(1L);
        apontMecanBean.insert();
    }

    public void finalizarApont(Long idBolMMFert){

        List<ApontMecanBean> apontMecanList = apontMecanList(idBolMMFert);
        ApontMecanBean apontMecanBean = apontMecanList.get(0);
        apontMecanList.clear();

        apontMecanBean.setDthrFinalApontMecan(Tempo.getInstance().dthr());
        apontMecanBean.setStatusApontMecan(3L);
        apontMecanBean.update();

    }

    public void updateApontMecan(ArrayList<Long> idApontMecanArrayList){

        List<ApontMecanBean> apontMecanList = apontMecanList(idApontMecanArrayList);

        for (ApontMecanBean apontMecanBean : apontMecanList) {
            if(apontMecanBean.getStatusApontMecan() == 1L){
                apontMecanBean.setStatusApontMecan(2L);
            }
            else if(apontMecanBean.getStatusApontMecan() == 3L){
                apontMecanBean.setStatusApontMecan(4L);
            }
            apontMecanBean.update();
        }

        apontMecanList.clear();
        idApontMecanArrayList.clear();

    }

    public List<ApontMecanBean> apontMecanAbertoNEnviadoList() {
        ApontMecanBean apontMecanBean = new ApontMecanBean();
        return apontMecanBean.get("statusApontMecan", 1L);
    }

    public List<ApontMecanBean> apontMecanFechadoNEnviadoList() {
        ApontMecanBean apontMecanBean = new ApontMecanBean();
        return apontMecanBean.get("statusApontMecan", 3L);
    }

    public boolean verApontAberto(Long idBolMMFert){
        boolean retorno = false;
        List<ApontMecanBean> apontMecanList = apontMecanList(idBolMMFert);
        if(apontMecanList.size() > 0){
            ApontMecanBean apontMecanBean = apontMecanList.get(0);
            if(apontMecanBean.getStatusApontMecan() < 3L){
                retorno = true;
            }
        }
        apontMecanList.clear();
        return retorno;
    }

    public List<ApontMecanBean> apontMecanList(Long idBolMMFert){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBol(idBolMMFert));
        ApontMecanBean apontMecanBean = new ApontMecanBean();
        return apontMecanBean.getAndOrderBy(pesqArrayList, "idApontMecan", false);
    }

    public List<ApontMecanBean> apontMecanList(ArrayList<Long> idApontMecanArrayList){
        ApontMecanBean apontMecanBean = new ApontMecanBean();
        return apontMecanBean.in("idApontMecan", idApontMecanArrayList);
    }

    public List<ApontMecanBean> apontMecanEnvioList(ArrayList<Long> idBolList){
        ApontMecanBean apontMecanBean = new ApontMecanBean();
        return apontMecanBean.inAndOrderBy("idBolApontMecan", idBolList, "idApontMecan", true);
    }

    public ArrayList<Long> idApontMecanArrayList(String objeto) throws Exception {

        ArrayList<Long> idApontMecanArrayList = new ArrayList<Long>();

        JSONObject jObjApont = new JSONObject(objeto);
        JSONArray jsonArrayApont = jObjApont.getJSONArray("apontmecan");

        for (int i = 0; i < jsonArrayApont.length(); i++) {

            JSONObject objApont = jsonArrayApont.getJSONObject(i);
            Gson gsonApont = new Gson();
            ApontMecanBean apontMecanBean = gsonApont.fromJson(objApont.toString(), ApontMecanBean.class);

            idApontMecanArrayList.add(apontMecanBean.getIdApontMecan());

        }

        return idApontMecanArrayList;

    }

    public String dadosEnvioApontMecan(List<ApontMecanBean> apontMecanList){

        JsonArray jsonArrayApont = new JsonArray();

        for (ApontMecanBean apontMecanBean : apontMecanList) {
            Gson gsonItemApontMecan = new Gson();
            if((apontMecanBean.getStatusApontMecan() == 1)
                    || (apontMecanBean.getStatusApontMecan() == 3)){
                jsonArrayApont.add(gsonItemApontMecan.toJsonTree(apontMecanBean, apontMecanBean.getClass()));
            }
        }

        apontMecanList.clear();

        JsonObject jsonApont = new JsonObject();
        jsonApont.add("apontmecan", jsonArrayApont);

        return jsonApont.toString();

    }

    public void deleteApontMecan(Long idBol){

        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();
        pesquisaArrayList.add(getPesqIdBol(idBol));

        ApontMecanBean apontMecanBean = new ApontMecanBean();
        List<ApontMecanBean> apontMecanList = apontMecanBean.get(pesquisaArrayList);

        for (ApontMecanBean apontMecanBeanBD : apontMecanList) {
            apontMecanBeanBD.delete();
        }

        apontMecanList.clear();

    }

    private EspecificaPesquisa getPesqIdBol(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolApontMecan");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
