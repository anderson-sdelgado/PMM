package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.LeiraBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.MovLeiraBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.Tempo;

public class LeiraDAO {

    public LeiraDAO() {
    }

    public void updateLeira(LeiraBean leiraBean, Long tipoMovLeira){
        if(tipoMovLeira == 1){
            leiraBean.setStatusLeira(1L);
        }
        else if(tipoMovLeira == 3){
            leiraBean.setStatusLeira(2L);
        }
        else{
            leiraBean.setStatusLeira(0L);
        }
        leiraBean.update();
    }

    public void inserirMovLeira(Long idLeira, Long tipo, Long idBol, Long idExtBol){

        String dthrString = Tempo.getInstance().dthr();
        Long dthrLong = Tempo.getInstance().dthrStringToLong(dthrString);

        MovLeiraBean movLeiraBean = new MovLeiraBean();
        movLeiraBean.setTipoMovLeira(tipo);
        movLeiraBean.setIdLeiraMovLeira(idLeira);
        movLeiraBean.setDthrMovLeira(dthrString);
        movLeiraBean.setDthrLongMovLeira(dthrLong);
        movLeiraBean.setIdBolMMFert(idBol);
        movLeiraBean.setIdExtBolMMFert(idExtBol);
        movLeiraBean.setStatusMovLeira(1L);
        movLeiraBean.insert();

    }

    public void updateMovLeira(ArrayList<Long> idMovLeiraArrayList){

        List<MovLeiraBean> movLeiraList = movLeiraList(idMovLeiraArrayList);

        for (int i = 0; i < movLeiraList.size(); i++) {
            MovLeiraBean movLeiraBean = movLeiraList.get(i);
            movLeiraBean.setStatusMovLeira(2L);
            movLeiraBean.update();
        }

        movLeiraList.clear();
        idMovLeiraArrayList.clear();

    }

    public void deleteMovLeira(ArrayList<Long> idMovLeiraArrayList){

        List<MovLeiraBean> movLeiraList = movLeiraList(idMovLeiraArrayList);

        for (int i = 0; i < movLeiraList.size(); i++) {
            MovLeiraBean movLeiraBean = movLeiraList.get(i);
            movLeiraBean.delete();
        }

        movLeiraList.clear();

    }

    public boolean verLeiraCod(Long codLeira){
        List<LeiraBean> leiraList = leiraCodList(codLeira);
        boolean ret = leiraList.size() > 0;
        leiraList.clear();
        return ret;
    }


    public boolean verDataHoraMovLeira(Long idBol){

        boolean ret = true;

        if(!hasMovLeiraBol(idBol)){
            ret = false;
        }
        else{
            if ((Tempo.getInstance().dthrAddMinutoLong(getUltMovLeira(idBol).getDthrLongMovLeira(), 1) < Tempo.getInstance().dtHr())) {
                ret = false;
            }
        }

        return ret;

    }

    public MovLeiraBean getUltMovLeira(Long idBol){
        List<MovLeiraBean> movLeiraList = movLeiraList(idBol);
        MovLeiraBean movLeiraBean = movLeiraList.get(movLeiraList.size() - 1);
        movLeiraList.clear();
        return movLeiraBean;
    }

    public LeiraBean getLeiraCod(Long codLeira){
        List<LeiraBean> leiraList = leiraCodList(codLeira);
        LeiraBean leiraBean = leiraList.get(0);
        leiraList.clear();
        return leiraBean;
    }

    public LeiraBean getLeiraId(Long idLeira){
        List<LeiraBean> leiraList = leiraIdList(idLeira);
        LeiraBean leiraBean = leiraList.get(0);
        leiraList.clear();
        return leiraBean;
    }

    public List<LeiraBean> leiraCodList(Long idLeira){
        LeiraBean leiraBean = new LeiraBean();
        return leiraBean.get("idLeira", idLeira);
    }

    public List<LeiraBean> leiraIdList(Long codLeira){
        LeiraBean leiraBean = new LeiraBean();
        return leiraBean.get("codLeira", codLeira);
    }

    public List<LeiraBean> leiraStatusList(Long tipoMov){
        Long statusLeira;
        if(tipoMov == 2){
            statusLeira = 1L;
        }
        else if(tipoMov == 4){
            statusLeira = 2L;
        }
        else{
            statusLeira = 0L;
        }
        LeiraBean leiraBean = new LeiraBean();
        return leiraBean.getAndOrderBy("statusLeira", statusLeira, "codLeira", true);
    }

    public List<MovLeiraBean> movLeiraList(Long idBol){
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        return movLeiraBean.getAndOrderBy("idBolMMFert", idBol, "idMovLeira", true);
    }

    public List<MovLeiraBean> movLeiraList(ArrayList<Long> idMovLeiraArrayList){
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        return movLeiraBean.in("idMovLeira", idMovLeiraArrayList);
    }

    public boolean hasMovLeiraBol(Long idBol){
        List<MovLeiraBean> apontMMFertList = movLeiraList(idBol);
        boolean ret = apontMMFertList.size() > 0;
        apontMMFertList.clear();
        return ret;
    }

    public List<MovLeiraBean> movLeiraEnvioList() {
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        return movLeiraBean.get("statusMovLeira", 1L);
    }

    public List<MovLeiraBean> movLeiraEnvioList(ArrayList<Long> idBolList){

        MovLeiraBean movLeiraBean = new MovLeiraBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusMovLeira");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        return movLeiraBean.inAndGet("idBolMMFert", idBolList, pesqArrayList);

    }

    public ArrayList<Long> idMovLeiraArrayList(List<MovLeiraBean> movLeiraList){
        ArrayList<Long> idMovLeiraList = new ArrayList<Long>();
        for (MovLeiraBean movLeiraBean : movLeiraList) {
            idMovLeiraList.add(movLeiraBean.getIdMovLeira());
        }
        return idMovLeiraList;
    }

    public ArrayList<Long> idMovLeiraArrayList(String objeto) throws Exception {

        ArrayList<Long> idMovLeiraArrayList = new ArrayList<Long>();

        JSONObject jObjMovLeira = new JSONObject(objeto);
        JSONArray jsonArrayMovLeira = jObjMovLeira.getJSONArray("movleira");

        for (int i = 0; i < jsonArrayMovLeira.length(); i++) {

            JSONObject objMovLeira = jsonArrayMovLeira.getJSONObject(i);
            Gson gson = new Gson();
            MovLeiraBean movLeiraBean = gson.fromJson(objMovLeira.toString(), MovLeiraBean.class);

            idMovLeiraArrayList.add(movLeiraBean.getIdMovLeira());

        }

        return idMovLeiraArrayList;

    }

    public String dadosEnvioMovLeira(List<MovLeiraBean> movLeiraList){

        JsonArray jsonArrayMovLeira = new JsonArray();

        for (MovLeiraBean movLeiraBean : movLeiraList) {
            Gson gson = new Gson();
            jsonArrayMovLeira.add(gson.toJsonTree(movLeiraBean, movLeiraBean.getClass()));
        }

        movLeiraList.clear();

        JsonObject jsonMovLeira = new JsonObject();
        jsonMovLeira.add("movleira", jsonArrayMovLeira);

        return jsonMovLeira.toString();

    }

    private String dadosMovLeira(MovLeiraBean movLeiraBean){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(movLeiraBean, movLeiraBean.getClass()).toString();
    }

    public ArrayList<String> movLeiraAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("MOV LEIRA");
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        List<MovLeiraBean> movLeiraList = movLeiraBean.orderBy("idMovLeira", true);
        for (MovLeiraBean movLeiraBeanBD : movLeiraList) {
            dadosArrayList.add(dadosMovLeira(movLeiraBeanBD));
        }
        movLeiraList.clear();
        return dadosArrayList;
    }

}
