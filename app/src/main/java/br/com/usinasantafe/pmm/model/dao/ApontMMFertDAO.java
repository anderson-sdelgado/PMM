package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;

public class ApontMMFertDAO {

    public ApontMMFertDAO() {
    }

    public void salvarApont(Long idBol, Long idMotoMec, Long parada
                                        , Long os, Long atividade
                                        , Double latitude, Double longitude
                                        , Long statusConBol, String dthr
                                        , Long idTransb, Double pressao
                                        , Long veloc, Long bocal, int tipo){

        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        apontMMFertBean.setIdBolMMFert(idBol);
        apontMMFertBean.setIdMotoMec(idMotoMec);
        apontMMFertBean.setOsApontMMFert(os);
        apontMMFertBean.setAtivApontMMFert(atividade);
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

    public ApontMMFertBean getApont(String dthr){
        List<ApontMMFertBean> apontaMMFertList = apontMMFertList(dthr);
        ApontMMFertBean apontMMFertBean = apontaMMFertList.get(0);
        apontaMMFertList.clear();
        return apontMMFertBean;
    }

    public List<ApontMMFertBean> apontMMFertList(Long idBol){
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.getAndOrderBy("idBolMMFert", idBol, "idApontMMFert", true);
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

    public List<ApontMMFertBean> apontListBolList(ApontMMFertBean apontMMFertBean){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBolApont(apontMMFertBean.getIdBolMMFert()));
        pesqArrayList.add(getPesqDthrApont(apontMMFertBean.getDthrApontMMFert()));
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

    private EspecificaPesquisa getPesqIdBolApont(Long idBolApontMM){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolMMFert");
        pesquisa.setValor(idBolApontMM);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    public int verTransbordo(String data, Long idBol) {

        int retorno = 0;

        List<ApontMMFertBean> apontMMFertList = apontMMFertList(idBol);

        if (apontMMFertList.size() > 0) {
            ApontMMFertBean apontaMMTO = apontMMFertList.get(apontMMFertList.size() - 1);
            if (apontaMMTO.getParadaApontMMFert() != 0L) {
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

    public List<ApontMMFertBean> apontEnvioList(ArrayList<Long> idBolList){

        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontMMFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        return apontMMFertBean.inAndGet("idBolMMFert", idBolList, pesqArrayList);

    }

    public List<ApontMMFertBean> apontMMFertList(ArrayList<Long> idBolList){
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        return apontMMFertBean.in("idBolMMFert", idBolList);
    }

    public ArrayList<Long> idApontArrayList(List<ApontMMFertBean> apontList){
        List<ApontMMFertBean> apontMMList = apontList;
        ArrayList<Long> idApontList = new ArrayList<Long>();
        for (ApontMMFertBean apontMMFertBean : apontMMList) {
            idApontList.add(apontMMFertBean.getIdApontMMFert());
        }
        return idApontList;
    }

    public ArrayList<Long> idApontArrayList(String objeto){

        ArrayList<Long> idApontArrayList = new ArrayList<Long>();

        try{

            JSONObject jObjApontMM = new JSONObject(objeto);
            JSONArray jsonArrayApontMM = jObjApontMM.getJSONArray("apont");

            for (int i = 0; i < jsonArrayApontMM.length(); i++) {

                JSONObject objApont = jsonArrayApontMM.getJSONObject(i);
                Gson gsonApont = new Gson();
                ApontMMFertBean apontMMFertBean = gsonApont.fromJson(objApont.toString(), ApontMMFertBean.class);

                idApontArrayList.add(apontMMFertBean.getIdApontMMFert());

            }

        }
        catch(Exception e){
            LogErroDAO.getInstance().insert(e);
            Tempo.getInstance().setEnvioDado(true);
        }

        return idApontArrayList;

    }

    public String dadosEnvioApontMM(List<ApontMMFertBean> apontMMFertList){

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

    public void updApont(ArrayList<Long> idApontArrayList){

        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        List<ApontMMFertBean> apontMMList = apontMMFertBean.in("idApontMMFert", idApontArrayList);

        for (int i = 0; i < apontMMList.size(); i++) {

            apontMMFertBean = apontMMList.get(i);
            apontMMFertBean.setStatusApontMMFert(2L);
            apontMMFertBean.update();

        }

        apontMMList.clear();

    }

    public void deleteApont(ArrayList<Long> idApontArrayList){

        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        List<ApontMMFertBean> apontMMFertList = apontMMFertBean.in("idApontMMFert", idApontArrayList);

        for (int i = 0; i < apontMMFertList.size(); i++) {
            apontMMFertBean = apontMMFertList.get(i);
            apontMMFertBean.delete();
        }

        apontMMFertList.clear();

    }


}
