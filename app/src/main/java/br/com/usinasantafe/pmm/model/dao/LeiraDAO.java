package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.LeiraBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.MovLeiraBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.Tempo;

public class LeiraDAO {

    public LeiraDAO() {
    }

    public void inserirMovLeira(Long idLeira, Long tipo, Long idBol, Long idExtBol){
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        movLeiraBean.setTipoMovLeiraMM(tipo);
        movLeiraBean.setIdLeiraMovLeiraMM(idLeira);
        movLeiraBean.setDthrMovLeiraMM(Tempo.getInstance().dthrSemTZ());
        movLeiraBean.setIdBolMMFert(idBol);
        movLeiraBean.setIdExtBolMMFert(idExtBol);
        movLeiraBean.setStatusMovLeiraMM(1L);
        movLeiraBean.insert();
    }

    public void updateMovLeira(ArrayList<Long> idMovLeiraArrayList){

        List<MovLeiraBean> movLeiraList = movLeiraList(idMovLeiraArrayList);

        for (int i = 0; i < movLeiraList.size(); i++) {
            MovLeiraBean movLeiraBean = movLeiraList.get(i);
            movLeiraBean.setStatusMovLeiraMM(2L);
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

    public LeiraBean getLeiraCod(Long codLeira){
        List<LeiraBean> leiraList = leiraCodList(codLeira);
        LeiraBean leiraBean = (LeiraBean) leiraList.get(0);
        leiraList.clear();
        return leiraBean;
    }

    public List<LeiraBean> leiraCodList(Long codLeira){
        LeiraBean leiraBean = new LeiraBean();
        return leiraBean.get("codLeira", codLeira);
    }

    public List<MovLeiraBean> movLeiraList(Long idBol){
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        return movLeiraBean.getAndOrderBy("idBolMMFert", idBol, "idMovLeiraMM", true);
    }

    public List<MovLeiraBean> movLeiraList(ArrayList<Long> idMovLeiraArrayList){
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        return movLeiraBean.in("idMovLeiraMM", idMovLeiraArrayList);
    }

    public List<MovLeiraBean> movLeiraEnvioList(ArrayList<Long> idBolList){

        MovLeiraBean movLeiraBean = new MovLeiraBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusMovLeiraMM");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        return movLeiraBean.inAndGet("idBolMMFert", idBolList, pesqArrayList);

    }

    public ArrayList<Long> idMovLeiraArrayList(List<MovLeiraBean> movLeiraList){
        ArrayList<Long> idMovLeiraList = new ArrayList<Long>();
        for (MovLeiraBean movLeiraBean : movLeiraList) {
            idMovLeiraList.add(movLeiraBean.getIdMovLeiraMM());
        }
        return idMovLeiraList;
    }

    public ArrayList<Long> idMovLeiraArrayList(String objeto){

        ArrayList<Long> idMovLeiraArrayList = new ArrayList<Long>();

        try{

            JSONObject jObjMovLeira = new JSONObject(objeto);
            JSONArray jsonArrayMovLeira = jObjMovLeira.getJSONArray("movleira");

            for (int i = 0; i < jsonArrayMovLeira.length(); i++) {

                JSONObject objMovLeira = jsonArrayMovLeira.getJSONObject(i);
                Gson gson = new Gson();
                MovLeiraBean movLeiraBean = gson.fromJson(objMovLeira.toString(), MovLeiraBean.class);

                idMovLeiraArrayList.add(movLeiraBean.getIdMovLeiraMM());

            }

        }
        catch(Exception e){
            LogErroDAO.getInstance().insert(e);
            Tempo.getInstance().setEnvioDado(true);
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

}
