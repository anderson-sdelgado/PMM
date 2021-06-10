package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.dao.CabecCheckListDAO;
import br.com.usinasantafe.pmm.model.dao.ItemCheckListDAO;
import br.com.usinasantafe.pmm.model.dao.RespItemCheckListDAO;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCheckListBean;

public class CheckListCTR {


    public CheckListCTR() {
    }

    public boolean verCabecAberto(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.verCabecAberto();
    }

    public void clearRespCabecAberto(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        CabecCheckListBean cabecCheckListBean = cabecCheckListDAO.getCabecAberto();
        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        respItemCheckListDAO.clearRespItem(cabecCheckListBean.getIdCabCL());
    }

    public void createCabecAberto(){
        ConfigCTR configCTR = new ConfigCTR();
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.createCabecAberto(configCTR.getEquip().getNroEquip() , motoMecFertCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert(), motoMecFertCTR.getBoletimMMFertAberto().getIdTurnoBolMMFert());
    }

    public void salvarBolFechado(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.salvarFechCheckList();
    }

    public boolean verAberturaCheckList(Long turno){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.verAberturaCheckList(turno);
    }

    public void atualCheckList(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
        itemCheckListDAO.atualCheckList(dado, telaAtual, telaProx, progressDialog);
    }

    public void recDadosCheckList(String result) {
        ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
        itemCheckListDAO.recDadosCheckList(result);
    }

    public List<ItemCheckListBean> getItemList(){
        ConfigCTR configCTR = new ConfigCTR();
        ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
        List<ItemCheckListBean> itemCheckListList = itemCheckListDAO.getItemList(configCTR.getEquip());
        return itemCheckListList;
    }

    public ItemCheckListBean getItemCheckList(int pos){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.getItemCheckList(pos);
    }

    public void setRespCheckList(RespItemCheckListBean respItemCheckListBean){
        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        respItemCheckListDAO.setRespCheckList(cabecCheckListDAO.getCabecAberto().getIdCabCL(), respItemCheckListBean);
    }

    public int qtdeItemCheckList(){
        ConfigCTR configCTR = new ConfigCTR();
        ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
        return itemCheckListDAO.qtdeItem(configCTR.getEquip().getIdCheckList());
    }

    public List bolFechList(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.bolFechList();
    }

    public boolean verEnvioDados(){
        return bolFechList().size() > 0;
    }

    public String dadosEnvio(){

        List cabecCheckListLista = bolFechList();

        JsonArray jsonArrayCabec = new JsonArray();
        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < cabecCheckListLista.size(); i++) {

            CabecCheckListBean cabecCheckListBean = (CabecCheckListBean) cabecCheckListLista.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecCheckListBean, cabecCheckListBean.getClass()));

            RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
            List respItemList = respItemCheckListDAO.respItemList(cabecCheckListBean.getIdCabCL());

            for (int j = 0; j < respItemList.size(); j++) {
                RespItemCheckListBean respItemCheckListBean = (RespItemCheckListBean) respItemList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayItem.add(gsonItem.toJsonTree(respItemCheckListBean, respItemCheckListBean.getClass()));
            }

            respItemList.clear();

        }

        cabecCheckListLista.clear();

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabecalho", jsonArrayCabec);

        JsonObject jsonItem = new JsonObject();
        jsonItem.add("item", jsonArrayItem);

        return jsonCabec.toString() + "_" + jsonItem.toString();

    }

    public void delChecklist() {

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List cabecCheckList = cabecCheckListBean.get("statusCabCL", 2L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < cabecCheckList.size(); i++) {
            cabecCheckListBean = (CabecCheckListBean) cabecCheckList.get(i);
            rLista.add(cabecCheckListBean.getIdCabCL());
        }

        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        List respItemList = respItemCheckListBean.in("idCabItCL", rLista);

        for (int j = 0; j < respItemList.size(); j++) {
            respItemCheckListBean = (RespItemCheckListBean) respItemList.get(j);
            respItemCheckListBean.delete();
        }

        for (int i = 0; i < cabecCheckList.size(); i++) {
            cabecCheckListBean = (CabecCheckListBean) cabecCheckList.get(i);
            cabecCheckListBean.delete();
        }

    }

}
