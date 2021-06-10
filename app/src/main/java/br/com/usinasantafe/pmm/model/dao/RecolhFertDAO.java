package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.Tempo;

public class RecolhFertDAO {

    public RecolhFertDAO() {
    }

    public void insRecolh(Long idBol, Long nroOS){

        RecolhFertBean recolhFertBean = new RecolhFertBean();
        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("idBolMMFert");
        pesq1.setValor(idBol);
        pesq1.setTipo(1);
        pesquisaArrayList.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("nroOSRecolhFert");
        pesq2.setValor(nroOS);
        pesq2.setTipo(1);
        pesquisaArrayList.add(pesq2);

        List<RecolhFertBean> rendList = recolhFertBean.get(pesquisaArrayList);

        if (rendList.size() == 0) {
            recolhFertBean.setIdBolMMFert(idBol);
            recolhFertBean.setNroOSRecolhFert(nroOS);
            recolhFertBean.setValorRecolhFert(0L);
            recolhFertBean.insert();
            recolhFertBean.commit();
        }

    }

    public boolean verRecolh(Long idBol){
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List recolhList = recolhFertBean.get("idBolMMFert", idBol);
        Boolean ret = (recolhList.size() > 0);
        recolhList.clear();
        return ret;
    }

    public RecolhFertBean getRecolh(Long idBol, int pos){
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List recolhFertList = recolhFertBean.getAndOrderBy("idBolMMFert", idBol, "idRecolhFert", true);
        recolhFertBean = (RecolhFertBean) recolhFertList.get(pos);
        recolhFertList.clear();
        return recolhFertBean;
    }

    public int qtdeRecolh(Long idBol){
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List recolhList = recolhFertBean.get("idBolMMFert", idBol);
        return recolhList.size();
    }

    public void atualRecolh(RecolhFertBean recolhFertBean){
        recolhFertBean.setDthrRecolhFert(Tempo.getInstance().dataComHora());
        recolhFertBean.update();
        recolhFertBean.commit();
    }

    public List<RecolhFertBean> recolhList(Long idBol){
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        return recolhFertBean.getAndOrderBy("idBolMMFert",  idBol, "idRecolhFert", true);
    }

    public List<RecolhFertBean> recolhEnvioList(ArrayList<Long> idBolList){
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        return recolhFertBean.in("idBolMMFert", idBolList);
    }

    public String dadosEnvioRecolh(List<RecolhFertBean> recolhimentoList){

        JsonArray jsonArrayRecolhimento = new JsonArray();

        for (RecolhFertBean recolhFertBean : recolhimentoList) {
            Gson gsonRecol = new Gson();
            jsonArrayRecolhimento.add(gsonRecol.toJsonTree(recolhFertBean, recolhFertBean.getClass()));
        }

        recolhimentoList.clear();

        JsonObject jsonRecolhimento = new JsonObject();
        jsonRecolhimento.add("recolhimento", jsonArrayRecolhimento);

        return jsonRecolhimento.toString();

    }

    public void deleteRecolh(Long idBol){

        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List<RecolhFertBean> recolhFertList = recolhFertBean.get("idBolMMFert", idBol);

        for (int j = 0; j < recolhFertList.size(); j++) {
            recolhFertBean = recolhFertList.get(j);
            recolhFertBean.delete();
        }

        recolhFertList.clear();

    }

}
