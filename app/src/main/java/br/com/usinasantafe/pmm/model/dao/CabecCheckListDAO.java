package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCheckListBean;

public class CabecCheckListDAO {

    public CabecCheckListDAO() {
    }

    public boolean verCabecAberto(){

        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();
        pesquisaArrayList.add(getPesqCabecAberto());

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List<CabecCheckListBean> cabecList = cabecCheckListBean.get(pesquisaArrayList);
        Boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;

    }

    public CabecCheckListBean getCabecCheckListAberto(){

        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();
        pesquisaArrayList.add(getPesqCabecAberto());

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List<CabecCheckListBean> cabecList = cabecCheckListBean.get(pesquisaArrayList);
        cabecCheckListBean = (CabecCheckListBean) cabecList.get(0);
        cabecList.clear();
        return cabecCheckListBean;

    }

    public void createCabecAberto(Long nroEquip, Long matricFunc, Long idTurno){
        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        cabecCheckListBean.setDtCabCL(Tempo.getInstance().dthr());
        cabecCheckListBean.setEquipCabCL(nroEquip);
        cabecCheckListBean.setFuncCabCL(matricFunc);
        cabecCheckListBean.setTurnoCabCL(idTurno);
        cabecCheckListBean.setStatusCabCL(1L);
        cabecCheckListBean.insert();
    }

    public boolean verAberturaCheckList(Long idTurno, Long idCheckList
            , Long ultTurnoCL, String dtUltCL){

        if ((idCheckList > 0) &&
                ((ultTurnoCL != idTurno)
                        || ((ultTurnoCL == idTurno)
                                    && (!dtUltCL.equals(Tempo.getInstance().dt()))))) {
            return true;
        }
        else{
            return false;
        }

    }

    public void salvarFechCheckList(String activity) {
        CabecCheckListBean cabecCheckListBean = getCabecCheckListAberto();
        cabecCheckListBean.setStatusCabCL(2L);
        cabecCheckListBean.update();
        EnvioDadosServ.getInstance().envioDados(activity);
    }

    public ArrayList<String> cabecCheckListAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("CABEC CHECKLIST");
        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List<CabecCheckListBean> cabecCheckListList = cabecCheckListBean.orderBy("idCabCL", true);
        for (CabecCheckListBean cabecCheckListBeanBD : cabecCheckListList) {
            dadosArrayList.add(dadosCabecChechList(cabecCheckListBeanBD));
        }
        cabecCheckListList.clear();
        return dadosArrayList;
    }

    public String dadosEnvioCabecCheckList(){
        return dadosCabecChechList(cabecCheckListFechList());
    }

    private String dadosCabecChechList(CabecCheckListBean cabecCheckListBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(cabecCheckListBean, cabecCheckListBean.getClass()).toString();
    }

    private String dadosCabecChechList(List<CabecCheckListBean> cabecCheckListList){

        JsonArray jsonArrayCabecCheckList = new JsonArray();

        for (CabecCheckListBean cabecCheckListBean : cabecCheckListList) {
            Gson gsonCabec = new Gson();
            jsonArrayCabecCheckList.add(gsonCabec.toJsonTree(cabecCheckListBean, cabecCheckListBean.getClass()));
        }

        JsonObject jsonCabecCheckList = new JsonObject();
        jsonCabecCheckList.add("cabecalho", jsonArrayCabecCheckList);

        return jsonCabecCheckList.toString();
    }

    public List<CabecCheckListBean> cabecCheckListFechList(){

        ArrayList<EspecificaPesquisa> pesquisaArrayList = new ArrayList();
        pesquisaArrayList.add(getPesqCabecFechado());

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        return cabecCheckListBean.get(pesquisaArrayList);

    }

    public ArrayList<Long> idCabecCheckListArrayList(List<CabecCheckListBean> cabecCheckListList) {
        ArrayList<Long> idCabecCheckListArrayList = new ArrayList<Long>();
        for (CabecCheckListBean cabecCheckListBean : cabecCheckListList) {
            idCabecCheckListArrayList.add(cabecCheckListBean.getIdCabCL());
        }
        return idCabecCheckListArrayList;
    }

    public void updateCabecCLEnviado(){
        List<CabecCheckListBean> cabecCheckListList = cabecCheckListFechList();
        for(CabecCheckListBean cabecCheckListBean : cabecCheckListList){
            cabecCheckListBean.setStatusCabCL(3L);
            cabecCheckListBean.update();
        }
        cabecCheckListList.clear();
    }

    public void delCabecCLFech(){
        List<CabecCheckListBean> cabecCheckListList = cabecCheckListFechList();
        for(CabecCheckListBean cabecCheckListBean : cabecCheckListList){
            cabecCheckListBean.delete();
        }
        cabecCheckListList.clear();
    }

    private EspecificaPesquisa getPesqCabecAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabCL");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabCL");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
