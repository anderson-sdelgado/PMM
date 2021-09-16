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

    public void salvarApont(Long idBol, Long idMotoMec, Long parada
                                        , Long os, Long atividade
                                        , Long frente, Long idPropriedade
                                        , Double latitude, Double longitude
                                        , String dthr, Long dthrLong
                                        , Long idTransb, Double pressao
                                        , Long veloc, Long bocal
                                        , Long statusConBol, int tipo){

        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        apontMMFertBean.setIdBolMMFert(idBol);
        apontMMFertBean.setIdMotoMec(idMotoMec);
        apontMMFertBean.setOsApontMMFert(os);
        apontMMFertBean.setAtivApontMMFert(atividade);
        apontMMFertBean.setIdFrenteApontMMFert(frente);
        apontMMFertBean.setIdProprApontMMFert(idPropriedade);
        apontMMFertBean.setLatitudeApontMMFert(latitude);
        apontMMFertBean.setLongitudeApontMMFert(longitude);
        apontMMFertBean.setStatusConApontMMFert(statusConBol);

        if(tipo == 1){
            List<ApontMMFertBean> apontMMFertList = apontMMFertList(idBol);
            if (apontMMFertList.size() > 0) {
                apontMMFertBean = apontMMFertList.get(apontMMFertList.size() - 1);
                apontMMFertList.clear();
            }
        }

        apontMMFertBean.setParadaApontMMFert(parada);
        apontMMFertBean.setDthrApontMMFert(dthr);
        apontMMFertBean.setDthrApontLongMMFert(dthrLong);
        apontMMFertBean.setTransbApontMMFert(idTransb);
        apontMMFertBean.setPressaoApontMMFert(pressao);
        apontMMFertBean.setVelocApontMMFert(veloc);
        apontMMFertBean.setBocalApontMMFert(bocal);
        apontMMFertBean.setStatusApontMMFert(1L);
        apontMMFertBean.insert();

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

    public List<ApontMMFertBean> apontMMFertList(Long idBol){
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.getAndOrderBy("idBolMMFert", idBol, "idApontMMFert", true);
    }

    public List<ApontMMFertBean> apontMMFertList(ArrayList<Long> idApontArrayList){
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.in("idApontMMFert", idApontArrayList);
    }

    public List<ApontMMFertBean> apontEnvioList() {
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.get("statusApontMMFert", 1L);
    }

    private List<ApontMMFertBean> apontMMFertList(String dthr){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDthrApont(dthr));
        ApontMMFertBean apontMMFertBeanBD = new ApontMMFertBean();
        return apontMMFertBeanBD.get(pesqArrayList);
    }

    private EspecificaPesquisa getPesqDthrApont(String dthrApontMM){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("dthrApontMMFert");
        pesquisa.setValor(dthrApontMM);
        pesquisa.setTipo(1);
        return pesquisa;
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
                if (Tempo.getInstance().dthrAddMinutoLong(apontMMFertBean.getDthrApontLongMMFert(), 10) > Tempo.getInstance().dtHr()) {
                    retorno = 2;
                }
            }
        }

        return retorno;
    }

    public boolean verDataHoraApont(Long idBol){

        boolean ret = true;


//        Log.i("ECM", "CHEGOU AKI 1");

        if(!hasApontBol(idBol)){
//            Log.i("ECM", "CHEGOU AKI 2");
            ret = false;
        }
        else{
//            Log.i("ECM", "CHEGOU AKI 3");
//            Log.i("ECM", "dtHrSemTZLong = " + Tempo.getInstance().dtHrSemTZLong());
//            Log.i("ECM", "getDthrApontLongMMFert() + 1 = " + Tempo.getInstance().dthrAddMinutoLong(getUltApont(idBol).getDthrApontLongMMFert(), 1));
//            Log.i("ECM", "getDthrApontLongMMFert() = " + getUltApont(idBol).getDthrApontLongMMFert());
            if ((Tempo.getInstance().dthrAddMinutoLong(getUltApont(idBol).getDthrApontLongMMFert(), 1) < Tempo.getInstance().dtHr())) {
//                Log.i("ECM", "CHEGOU AKI 4");
                ret = false;
            }
        }

        return ret;

    }

    public List<ApontMMFertBean> apontEnvioList(ArrayList<Long> idBolList){

        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontMMFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

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

        for (int i = 0; i < apontMMList.size(); i++) {
            ApontMMFertBean apontMMFertBean = apontMMList.get(i);
            apontMMFertBean.setStatusApontMMFert(2L);
            apontMMFertBean.update();
        }

        apontMMList.clear();
        idApontArrayList.clear();

    }

    public void deleteApont(ArrayList<Long> idApontArrayList){

        List<ApontMMFertBean> apontMMFertList = apontMMFertList(idApontArrayList);

        for (int i = 0; i < apontMMFertList.size(); i++) {
            ApontMMFertBean apontMMFertBean = apontMMFertList.get(i);
            apontMMFertBean.delete();
        }

        apontMMFertList.clear();
        idApontArrayList.clear();

    }


}
