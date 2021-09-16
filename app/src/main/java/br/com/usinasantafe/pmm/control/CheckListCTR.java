package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.dao.CabecCheckListDAO;
import br.com.usinasantafe.pmm.model.dao.EquipDAO;
import br.com.usinasantafe.pmm.model.dao.ItemCheckListDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.RespItemCheckListDAO;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCheckListBean;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Json;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class CheckListCTR {

    private int posCheckList;

    public CheckListCTR() {
    }

    public int getPosCheckList() {
        return posCheckList;
    }

    public void setPosCheckList(int posCheckList) {
        this.posCheckList = posCheckList;
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

    public void receberVerifCheckList(String result) {

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("_") + 1;
                String objPrinc = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1);

                Json json = new Json();

                EquipDAO equipDAO = new EquipDAO();
                equipDAO.recDadosEquip(json.jsonArray(objPrinc));

                ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
                itemCheckListDAO.recDadosCheckList(json.jsonArray(objSeg));

                VerifDadosServ.getInstance().pulaTela();

            } else {
                VerifDadosServ.getInstance().pulaTela();
            }

        } catch (Exception e) {
            LogErroDAO.getInstance().insert(e);
            VerifDadosServ.getInstance().pulaTela();
        }

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

    public List<CabecCheckListBean> cabecCheckListList(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.cabecCheckListFechList();
    }

    public boolean verEnvioDados(){
        return cabecCheckListList().size() > 0;
    }

    public String dadosEnvio(){

        List cabecCheckListList = cabecCheckListList();

        JsonArray jsonArrayCabec = new JsonArray();
        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < cabecCheckListList.size(); i++) {

            CabecCheckListBean cabecCheckListBean = (CabecCheckListBean) cabecCheckListList.get(i);
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

        cabecCheckListList.clear();

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabecalho", jsonArrayCabec);

        JsonObject jsonItem = new JsonObject();
        jsonItem.add("item", jsonArrayItem);

        return jsonCabec.toString() + "_" + jsonItem.toString();

    }

    public void delChecklist() {

        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        respItemCheckListDAO.delRespItem(cabecCheckListDAO.idCabecCLFechArrayList());
        cabecCheckListDAO.delCabecCLFech();

        EnvioDadosServ.getInstance().envioDados();

    }

}
