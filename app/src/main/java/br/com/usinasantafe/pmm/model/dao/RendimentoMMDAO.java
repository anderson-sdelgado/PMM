package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.Tempo;

public class RendimentoMMDAO {

    public RendimentoMMDAO() {
    }

    public void insRend(Long idBol, Long nroOS, String activity){

        RendMMBean rendMMBean = new RendMMBean();

        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();
        pesquisaArrayList.add(getPesqIdBol(idBol));
        pesquisaArrayList.add(getPesqNroOS(nroOS));

        List<RendMMBean> rendList = rendMMBean.get(pesquisaArrayList);

        if (rendList.size() == 0) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (rendList.size() == 0) {", activity);
            rendMMBean.setIdBolMMFert(idBol);
            rendMMBean.setNroOSRendMM(nroOS);
            rendMMBean.setValorRendMM(0D);
            rendMMBean.insert();
            rendMMBean.commit();
        }

    }

    public boolean verRend(Long idBol){
        List<RendMMBean> rendList = rendList(idBol);
        Boolean ret = (rendList.size() > 0);
        rendList.clear();
        return ret;
    }

    public int qtdeRend(Long idBol){
        List<RendMMBean> rendList = rendList(idBol);
        int qtde = rendList.size();
        rendList.clear();
        return qtde;
    }

    public RendMMBean getRend(Long idBol, int pos){
        List<RendMMBean> rendList = rendList(idBol);
        RendMMBean rendMMBean = rendList.get(pos);
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

    public ArrayList<String> rendAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("RENDIMENTO");
        RendMMBean rendMMBean = new RendMMBean();
        List<RendMMBean> rendMMList = rendMMBean.orderBy("idRendMM", true);
        for (RendMMBean rendMMBeanBD : rendMMList) {
            dadosArrayList.add(dadosRendMM(rendMMBeanBD));
        }
        rendMMList.clear();
        return dadosArrayList;
    }

    public String dadosRendMM(RendMMBean rendMMBean){
        Gson gsonRend = new Gson();
        return gsonRend.toJsonTree(rendMMBean, rendMMBean.getClass()).toString();
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

        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();
        pesquisaArrayList.add(getPesqIdBol(idBol));

        RendMMBean rendMMBean = new RendMMBean();
        List<RendMMBean> rendList = rendMMBean.get(pesquisaArrayList);

        for (RendMMBean rendMMBeanBD : rendList) {
            rendMMBeanBD.delete();
        }

        rendList.clear();

    }

    private EspecificaPesquisa getPesqIdBol(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolMMFert");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqNroOS(Long nroOS){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("nroOSRendMM");
        pesquisa.setValor(nroOS);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
