package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.Tempo;

public class RecolhimentoFertDAO {

    public RecolhimentoFertDAO() {
    }

    public void insRecolh(Long idBol, Long nroOS, String activity){

        RecolhFertBean recolhFertBean = new RecolhFertBean();

        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();
        pesquisaArrayList.add(getPesqIdBol(idBol));
        pesquisaArrayList.add(getPesqNroOS(nroOS));

        List<RecolhFertBean> rendList = recolhFertBean.get(pesquisaArrayList);

        if (rendList.size() == 0) {
            LogProcessoDAO.getInstance().insertLogProcesso("RecolhFertBean recolhFertBean = new RecolhFertBean();\n" +
                    "        \n" +
                    "        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();\n" +
                    "        pesquisaArrayList.add(getPesqIdBol(idBol));\n" +
                    "        pesquisaArrayList.add(getPesqNroOS(nroOS));\n" +
                    "        \n" +
                    "        List<RecolhFertBean> rendList = recolhFertBean.get(pesquisaArrayList);\n" +
                    "        if (rendList.size() == 0) {\n" +
                    "            recolhFertBean.setIdBolMMFert(" + idBol + ");\n" +
                    "            recolhFertBean.setNroOSRecolhFert(" + nroOS + ");\n" +
                    "            recolhFertBean.setValorRecolhFert(" + 0L + ");", activity);
            recolhFertBean.setIdBolMMFert(idBol);
            recolhFertBean.setNroOSRecolhFert(nroOS);
            recolhFertBean.setValorRecolhFert(0L);
            recolhFertBean.insert();
            recolhFertBean.commit();
        }

    }

    public boolean verRecolh(Long idBol){
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List<RecolhFertBean> recolhList = recolhFertBean.get("idBolMMFert", idBol);
        Boolean ret = (recolhList.size() > 0);
        recolhList.clear();
        return ret;
    }

    public ArrayList<String> recolAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("RECOLHIMENTO");
        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List<RecolhFertBean> recolhMMFertList = recolhFertBean.orderBy("idRecolhFert", true);
        for (RecolhFertBean recolhFertBeanBD : recolhMMFertList) {
            dadosArrayList.add(dadosRecolh(recolhFertBeanBD));
        }
        recolhMMFertList.clear();
        return dadosArrayList;
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
        recolhFertBean.setDthrRecolhFert(Tempo.getInstance().dthr());
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

    public String dadosRecolh(RecolhFertBean recolhFertBean){
        Gson gsonRecol = new Gson();
        return gsonRecol.toJsonTree(recolhFertBean, recolhFertBean.getClass()).toString();
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

        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();
        pesquisaArrayList.add(getPesqIdBol(idBol));

        RecolhFertBean recolhFertBean = new RecolhFertBean();
        List<RecolhFertBean> recolhFertList = recolhFertBean.get(pesquisaArrayList);

        for (RecolhFertBean recolhFertBeanBD : recolhFertList) {
            recolhFertBeanBD.delete();
        }

        recolhFertList.clear();

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
        pesquisa.setCampo("nroOSRecolhFert");
        pesquisa.setValor(nroOS);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
