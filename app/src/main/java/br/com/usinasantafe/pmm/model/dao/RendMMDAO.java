package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.Tempo;

public class RendMMDAO {

    public RendMMDAO() {
    }

    public void insRend(Long idBol, Long nroOS){

        RendMMBean rendMMBean = new RendMMBean();
        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("idBolMMFert");
        pesq1.setValor(idBol);
        pesq1.setTipo(1);
        pesquisaArrayList.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("nroOSRendMM");
        pesq2.setValor(nroOS);
        pesq2.setTipo(1);
        pesquisaArrayList.add(pesq2);

        List<RendMMBean> rendList = rendMMBean.get(pesquisaArrayList);

        if (rendList.size() == 0) {
            rendMMBean.setIdBolMMFert(idBol);
            rendMMBean.setNroOSRendMM(nroOS);
            rendMMBean.setValorRendMM(0D);
            rendMMBean.insert();
            rendMMBean.commit();
        }

    }

    public boolean verRend(Long idBol){
        RendMMBean rendMMBean = new RendMMBean();
        List<RendMMBean> rendList = rendMMBean.get("idBolMMFert", idBol);
        Boolean ret = (rendList.size() > 0);
        rendList.clear();
        return ret;
    }

    public int qtdeRend(Long idBol){
        RendMMBean rendMMBean = new RendMMBean();
        List rendList = rendMMBean.get("idBolMMFert", idBol);
        return rendList.size();
    }

    public RendMMBean getRend(Long idBol, int pos){
        RendMMBean rendMMBean = new RendMMBean();
        List rendList = rendMMBean.getAndOrderBy("idBolMMFert", idBol, "idRendMM", true);
        rendMMBean = (RendMMBean) rendList.get(pos);
        rendList.clear();
        return rendMMBean;
    }

    public void atualRend(RendMMBean rendMMBean){
        rendMMBean.setDthrRendMM(Tempo.getInstance().dthr());
        rendMMBean.update();
        rendMMBean.commit();
    }

    public List<RendMMBean> rendList(Long idBol){
        RendMMBean rendMMBean = new RendMMBean();
        return rendMMBean.getAndOrderBy("idBolMMFert", idBol, "idRendMM", true);
    }

    public List<RendMMBean> rendEnvioList(ArrayList<Long> idBolList){
        RendMMBean rendMMBean = new RendMMBean();
        return rendMMBean.in("idBolMMFert", idBolList);
    }

    public String dadosEnvioRendMM(List<RendMMBean> rendMMList){

        JsonArray jsonArrayRendimento = new JsonArray();

        for (RendMMBean rendMMBean : rendMMList) {
            Gson gsonRend = new Gson();
            jsonArrayRendimento.add(gsonRend.toJsonTree(rendMMBean, rendMMBean.getClass()));
        }

        rendMMList.clear();

        JsonObject jsonRend = new JsonObject();
        jsonRend.add("rendimento", jsonArrayRendimento);

        return jsonRend.toString();

    }

    public void deleteRend(Long idBol){

        RendMMBean rendMMBean = new RendMMBean();
        List<RendMMBean> rendList = rendMMBean.get("idBolMMFert", idBol);

        for (int j = 0; j < rendList.size(); j++) {
            rendMMBean = rendList.get(j);
            rendMMBean.delete();
        }

        rendList.clear();

    }

}
