package br.com.usinasantafe.cmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.model.bean.variaveis.RespItemCheckListBean;

public class RespItemCheckListDAO {

    public RespItemCheckListDAO() {
    }

    public void clearRespItem(Long idCabCL){
        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        if (respItemCheckListBean.hasElements()) {
            ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();
            pesquisaArrayList.add(getPesIdCab(idCabCL));
            List<RespItemCheckListBean> respItemCheckListList = respItemCheckListBean.get(pesquisaArrayList);
            for (RespItemCheckListBean respItemCheckListBeanBD : respItemCheckListList) {
                respItemCheckListBeanBD.delete();
            }
            respItemCheckListList.clear();
        }
    }

    public void setRespCheckList(Long idCabCL, RespItemCheckListBean respItemCheckListBean){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesIdCab(idCabCL));
        pesqArrayList.add(getPesIdItBD(respItemCheckListBean.getIdItBDItCL()));

        List<RespItemCheckListBean> respItemCheckListList = respItemCheckListBean.get(pesqArrayList);
        if(respItemCheckListList.size() > 0) {
            Long opcao = respItemCheckListBean.getOpItCL();
            respItemCheckListBean = (RespItemCheckListBean) respItemCheckListList.get(0);
            respItemCheckListBean.setOpItCL(opcao);
            respItemCheckListBean.update();
        }
        else{
            respItemCheckListBean.setIdCabItCL(idCabCL);
            respItemCheckListBean.insert();
        }
        respItemCheckListList.clear();
    }

    public ArrayList<String> respCheckListAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("ITEM CHECKLIST");
        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        List<RespItemCheckListBean> respItemCheckListList = respItemCheckListBean.orderBy("idItCL", true);
        for (RespItemCheckListBean respItemCheckListBeanBD : respItemCheckListList) {
            dadosArrayList.add(dadosRespCheckList(respItemCheckListBeanBD));
        }
        respItemCheckListList.clear();
        return dadosArrayList;
    }

    public String dadosRespCheckList(RespItemCheckListBean respItemCheckListBean){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(respItemCheckListBean, respItemCheckListBean.getClass()).toString();
    }

    public String dadosEnvioRespCheckList(List<RespItemCheckListBean> respItemCheckListBeanList){

        JsonArray jsonArrayRespCheckList = new JsonArray();

        for (RespItemCheckListBean respItemCheckListBean : respItemCheckListBeanList) {
            Gson gsonItemImp = new Gson();
            jsonArrayRespCheckList.add(gsonItemImp.toJsonTree(respItemCheckListBean, respItemCheckListBean.getClass()));
        }

        respItemCheckListBeanList.clear();

        JsonObject jsonItem = new JsonObject();
        jsonItem.add("item", jsonArrayRespCheckList);

        return jsonItem.toString();

    }

    public List<RespItemCheckListBean> respItemList(ArrayList<Long> idCabCLArrayList){
        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        return respItemCheckListBean.in("idCabItCL", idCabCLArrayList);
    }

    public List<RespItemCheckListBean> respItemList(Long idCabCL){
        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        return respItemCheckListBean.get("idCabItCL", idCabCL);
    }


    public void deleteRespItemCheckList(ArrayList<Long> idCabCLongArrayList){

        List<RespItemCheckListBean> respItemCheckListList = respItemList(idCabCLongArrayList);
        for(RespItemCheckListBean respItemCheckListBean : respItemCheckListList){
            respItemCheckListBean.delete();
        }
        respItemCheckListList.clear();

    }

    private EspecificaPesquisa getPesIdCab(Long idCab){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabItCL");
        pesquisa.setValor(idCab);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesIdItBD(Long idItBD){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idItBDItCL");
        pesquisa.setValor(idItBD);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
