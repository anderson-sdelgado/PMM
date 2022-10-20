package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;

public class ApontMMFertDAO {

    public ApontMMFertDAO() {
    }

    public void salvarApont(ApontMMFertBean apontMMFertBean, int tipo, Long status){

        if(tipo == 1){
            List<ApontMMFertBean> apontMMFertList = apontMMFertList(apontMMFertBean.getIdBolMMFert());
            if (apontMMFertList.size() > 0) {
                ApontMMFertBean apontMMFertBeanBD = apontMMFertList.get(apontMMFertList.size() - 1);
                apontMMFertList.clear();
                apontMMFertBean.setLatitudeApontMMFert(apontMMFertBeanBD.getLatitudeApontMMFert());
                apontMMFertBean.setLongitudeApontMMFert(apontMMFertBeanBD.getLongitudeApontMMFert());
                apontMMFertBean.setStatusConApontMMFert(apontMMFertBeanBD.getStatusConApontMMFert());
            }
        }

        apontMMFertBean.setStatusApontMMFert(status);
        apontMMFertBean.insert();

    }

    public void fecharApont(Long idBol){
        ApontMMFertBean apontMMFertBean = getApontAberto(idBol);
        apontMMFertBean.setStatusApontMMFert(2L);
        apontMMFertBean.update();
    }

    public boolean verifBackupApont(Long idBol, Long idMotoMec) {
        boolean v = false;
        List<ApontMMFertBean> apontMMFertList = apontMMFertList(idBol);
        if(apontMMFertList.size() > 0){
            ApontMMFertBean apontMMFertBean = apontMMFertList.get(apontMMFertList.size() - 1);
            if ((idMotoMec.equals(apontMMFertBean.getIdMotoMec()))) {
                v = true;
            }
        }
        apontMMFertList.clear();
        return v;
    }

    public boolean verifBackupApont(Long idBol, Long os, Long atividade, Long idParada) {
        boolean v = false;
        List<ApontMMFertBean> apontMMFertList = apontMMFertList(idBol);
        if(apontMMFertList.size() > 0){
            ApontMMFertBean apontMMFertBean = apontMMFertList.get(apontMMFertList.size() - 1);
            if ((os.equals(apontMMFertBean.getOsApontMMFert()))
                    && (atividade.equals(apontMMFertBean.getAtivApontMMFert()))
                    && (idParada.equals(apontMMFertBean.getParadaApontMMFert()))) {
                v = true;
            }
        }
        apontMMFertList.clear();
        return v;
    }

    public boolean verifBackupApontTransb(Long idBol, Long os, Long atividade, Long idParada, Long idTransb) {
        boolean v = false;
        List<ApontMMFertBean> apontMMFertList = apontMMFertList(idBol);
        if(apontMMFertList.size() > 0){
            ApontMMFertBean apontMMFertBean = apontMMFertList.get(apontMMFertList.size() - 1);
            if ((os.equals(apontMMFertBean.getOsApontMMFert()))
                    && (atividade.equals(apontMMFertBean.getAtivApontMMFert()))
                    && (idParada.equals(apontMMFertBean.getParadaApontMMFert()))
                    && (idTransb.equals(apontMMFertBean.getTransbApontMMFert()))) {
                v = true;
            }
        }
        apontMMFertList.clear();
        return v;
    }

    public boolean hasApontBol(Long idBol){
        List<ApontMMFertBean> apontMMFertList = apontMMFertList(idBol);
        boolean ret = apontMMFertList.size() > 0;
        apontMMFertList.clear();
        return ret;
    }

    public ApontMMFertBean getUltApont(Long idBol){
        List<ApontMMFertBean> apontMMFertList = apontMMFertList(idBol);
        ApontMMFertBean apontMMFertBean = apontMMFertList.get(apontMMFertList.size() - 1);
        apontMMFertList.clear();
        return apontMMFertBean;
    }

    public ApontMMFertBean getApontDthr(String dthr){
        List<ApontMMFertBean> apontaMMFertList = apontMMFertList(dthr);
        ApontMMFertBean apontMMFertBean = apontaMMFertList.get(0);
        apontaMMFertList.clear();
        return apontMMFertBean;
    }

    public ApontMMFertBean getApontAberto(Long idBol){
        List<ApontMMFertBean> apontaMMFertList = apontAbertoList(idBol);
        ApontMMFertBean apontMMFertBean = apontaMMFertList.get(0);
        apontaMMFertList.clear();
        return apontMMFertBean;
    }

    public List<ApontMMFertBean> apontAbertoList(Long idBol){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBolApont(idBol));
        pesqArrayList.add(getPesqStatusAbertoApont());

        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.get(pesqArrayList);
    }

    public List<ApontMMFertBean> apontMMFertList(Long idBol){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBolApont(idBol));

        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.getAndOrderBy(pesqArrayList, "idApontMMFert", true);
    }

    public List<ApontMMFertBean> apontMMFertList(ArrayList<Long> idApontArrayList){
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.in("idApontMMFert", idApontArrayList);
    }

    public List<ApontMMFertBean> apontEnvioList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusEnvioApont());
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.get(pesqArrayList);
    }

    private List<ApontMMFertBean> apontMMFertList(String dthr){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDthrApont(dthr));
        ApontMMFertBean apontMMFertBeanBD = new ApontMMFertBean();
        return apontMMFertBeanBD.get(pesqArrayList);
    }

    public ArrayList<String> apontAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("APONTAMENTO");
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        List<ApontMMFertBean> apontMMFertList = apontMMFertBean.orderBy("idApontMMFert", true);
        for (ApontMMFertBean apontMMFertBeanBD : apontMMFertList) {
            dadosArrayList.add(dadosApont(apontMMFertBeanBD));
        }
        apontMMFertList.clear();
        return dadosArrayList;
    }

    public int verTransbordo(Long idBol) {

        int retorno = 0;

        if(!hasApontBol(idBol)){
            retorno = 1;
        }
        else{
            ApontMMFertBean apontMMFertBean = getUltApont(idBol);
            if ((apontMMFertBean.getParadaApontMMFert() != 0L)){
                retorno = 1;
            }
            else{
                if (Tempo.getInstance().dthrAddMinutoLong(apontMMFertBean.getDthrApontLongMMFert(), 10) > Tempo.getInstance().dthrAtualLong()) {
                    retorno = 2;
                }
            }
        }

        return retorno;
    }

    public boolean verUltApontAtiv(Long idBol) {

        boolean retorno = true;

        if(!hasApontBol(idBol)){
            retorno = false;
        }
        else{
            ApontMMFertBean apontMMFertBean = getUltApont(idBol);
            if (apontMMFertBean.getParadaApontMMFert() == 0L){
                retorno = false;
            }
        }

        return retorno;
    }

    public boolean verDataHoraApont(Long idBol){
        boolean ret = true;
        if(!hasApontBol(idBol)){
            ret = false;
        } else {
            if ((Tempo.getInstance().dthrAddMinutoLong(getUltApont(idBol).getDthrApontLongMMFert(), 1) < Tempo.getInstance().dthrAtualLong())) {
                ret = false;
            }
        }
        return ret;
    }

    public List<ApontMMFertBean> apontEnvioList(ArrayList<Long> idBolList){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusEnvioApont());
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.inAndGetAndOrderBy("idBolMMFert", idBolList, pesqArrayList, "idApontMMFert", true);

    }

    public ArrayList<Long> idApontArrayList(List<ApontMMFertBean> apontMMFertList){
        ArrayList<Long> idApontList = new ArrayList<Long>();
        for (ApontMMFertBean apontMMFertBean : apontMMFertList) {
            idApontList.add(apontMMFertBean.getIdApontMMFert());
        }
        return idApontList;
    }

    public ArrayList<Long> idApontArrayList(String objeto) throws Exception {

        ArrayList<Long> idApontArrayList = new ArrayList<Long>();

        JSONObject jObjApont = new JSONObject(objeto);
        JSONArray jsonArrayApont = jObjApont.getJSONArray("apont");

        for (int i = 0; i < jsonArrayApont.length(); i++) {

            JSONObject objApont = jsonArrayApont.getJSONObject(i);
            Gson gsonApont = new Gson();
            ApontMMFertBean apontMMFertBean = gsonApont.fromJson(objApont.toString(), ApontMMFertBean.class);

            idApontArrayList.add(apontMMFertBean.getIdApontMMFert());

        }

        return idApontArrayList;

    }

    private String dadosApont(ApontMMFertBean apontMMFert){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(apontMMFert, apontMMFert.getClass()).toString();
    }

    public String dadosEnvioApont(List<ApontMMFertBean> apontMMFertList){

        JsonArray jsonArrayApont = new JsonArray();

        for (ApontMMFertBean apontMMFertBean : apontMMFertList) {
            Gson gsonItemImp = new Gson();
            jsonArrayApont.add(gsonItemImp.toJsonTree(apontMMFertBean, apontMMFertBean.getClass()));
        }

        apontMMFertList.clear();

        JsonObject jsonApont = new JsonObject();
        jsonApont.add("apont", jsonArrayApont);

        return jsonApont.toString();

    }

    public void updateApont(ArrayList<Long> idApontArrayList){

        List<ApontMMFertBean> apontMMList = apontMMFertList(idApontArrayList);

        for (ApontMMFertBean apontMMFertBean : apontMMList) {
            apontMMFertBean.setStatusApontMMFert(3L);
            apontMMFertBean.update();
        }

        apontMMList.clear();
        idApontArrayList.clear();

    }

    public void deleteApont(ArrayList<Long> idApontArrayList){

        List<ApontMMFertBean> apontMMFertList = apontMMFertList(idApontArrayList);

        for (ApontMMFertBean apontMMFertBean : apontMMFertList) {
            apontMMFertBean.delete();
        }

        apontMMFertList.clear();
        idApontArrayList.clear();

    }

    private EspecificaPesquisa getPesqDthrApont(String dthrApont){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("dthrApontMMFert");
        pesquisa.setValor(dthrApont);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusEnvioApont(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontMMFert");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusAbertoApont(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontMMFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdBolApont(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolMMFert");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
