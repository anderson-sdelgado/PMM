package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.dao.CabecCheckListDAO;
import br.com.usinasantafe.pmm.dao.ItemCheckListDAO;
import br.com.usinasantafe.pmm.dao.RespItemCLDAO;
import br.com.usinasantafe.pmm.bean.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.bean.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.bean.variaveis.RespItemCLTO;

public class CheckListCTR {


    public CheckListCTR() {
    }

    public boolean verCabecAberto(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.verCabecAberto();
    }

    public void clearRespCabecAberto(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        CabecCLTO cabecCLTO = cabecCheckListDAO.getCabecAberto();
        RespItemCLDAO respItemCLDAO = new RespItemCLDAO();
        respItemCLDAO.clearRespItem(cabecCLTO.getIdCabCL());
    }

    public void createCabecAberto(BoletimCTR boletimCTR){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.createCabecAberto(boletimCTR);
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

    public ItemCheckListTO getItemCheckList(int pos){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.getItemCheckList(pos);
    }

    public void setRespCheckList(RespItemCLTO respItemCLTO){
        RespItemCLDAO respItemCLDAO = new RespItemCLDAO();
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        respItemCLDAO.setRespCheckList(cabecCheckListDAO.getCabecAberto().getIdCabCL(), respItemCLTO);
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
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return bolFechList().size() > 0;
    }

    public String dadosEnvio(){

        List cabecCheckListLista = bolFechList();

        JsonArray jsonArrayCabec = new JsonArray();
        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < cabecCheckListLista.size(); i++) {

            CabecCLTO cabecCLTO = (CabecCLTO) cabecCheckListLista.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecCLTO, cabecCLTO.getClass()));

            RespItemCLDAO respItemCLDAO = new RespItemCLDAO();
            List respItemList = respItemCLDAO.respItemList(cabecCLTO.getIdCabCL());

            for (int j = 0; j < respItemList.size(); j++) {
                RespItemCLTO respItemCLTO = (RespItemCLTO) respItemList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayItem.add(gsonItem.toJsonTree(respItemCLTO, respItemCLTO.getClass()));
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

        CabecCLTO cabecCLTO = new CabecCLTO();
        List cabecCheckList = cabecCLTO.get("statusCabCL", 2L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < cabecCheckList.size(); i++) {
            cabecCLTO = (CabecCLTO) cabecCheckList.get(i);
            rLista.add(cabecCLTO.getIdCabCL());
        }

        RespItemCLTO respItemCLTO = new RespItemCLTO();
        List respItemList = respItemCLTO.in("idCabItCL", rLista);

        for (int j = 0; j < respItemList.size(); j++) {
            respItemCLTO = (RespItemCLTO) respItemList.get(j);
            respItemCLTO.delete();
        }

        for (int i = 0; i < cabecCheckList.size(); i++) {
            cabecCLTO = (CabecCLTO) cabecCheckList.get(i);
            cabecCLTO.delete();
        }

    }

}
