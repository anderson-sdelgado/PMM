package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.dao.CabecalhoCLDAO;
import br.com.usinasantafe.pmm.dao.ItemCheckListDAO;
import br.com.usinasantafe.pmm.dao.RespItemCLDAO;
import br.com.usinasantafe.pmm.to.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.RespItemCLTO;

public class CheckListCTR {


    public CheckListCTR() {
    }

    public boolean verCabecAberto(){
        CabecalhoCLDAO cabecalhoCLDAO = new CabecalhoCLDAO();
        return cabecalhoCLDAO.verCabecAberto();
    }

    public void clearRespCabecAberto(){
        CabecalhoCLDAO cabecalhoCLDAO = new CabecalhoCLDAO();
        CabecCLTO cabecCLTO = cabecalhoCLDAO.getCabecAberto();
        RespItemCLDAO respItemCLDAO = new RespItemCLDAO();
        respItemCLDAO.clearRespItem(cabecCLTO.getIdCabCL());
    }

    public void createCabecAberto(BoletimCTR boletimCTR){
        CabecalhoCLDAO cabecalhoCLDAO = new CabecalhoCLDAO();
        cabecalhoCLDAO.createCabecAberto(boletimCTR);
    }

    public void salvarBolFechado(){
        CabecalhoCLDAO cabecalhoCLDAO = new CabecalhoCLDAO();
        cabecalhoCLDAO.salvarFechCheckList();
    }

    public boolean verAberturaCheckList(Long turno){
        CabecalhoCLDAO cabecalhoCLDAO = new CabecalhoCLDAO();
        return cabecalhoCLDAO.verAberturaCheckList(turno);
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
        CabecalhoCLDAO cabecalhoCLDAO = new CabecalhoCLDAO();
        return cabecalhoCLDAO.getItemCheckList(pos);
    }

    public void setRespCheckList(RespItemCLTO respItemCLTO){
        RespItemCLDAO respItemCLDAO = new RespItemCLDAO();
        CabecalhoCLDAO cabecalhoCLDAO = new CabecalhoCLDAO();
        respItemCLDAO.setRespCheckList(cabecalhoCLDAO.getCabecAberto().getIdCabCL(), respItemCLTO);
    }

    public int qtdeItemCheckList(){
        ConfigCTR configCTR = new ConfigCTR();
        ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
        return itemCheckListDAO.qtdeItem(configCTR.getEquip().getIdCheckList());
    }

    public List bolFechList(){
        CabecalhoCLDAO cabecalhoCLDAO = new CabecalhoCLDAO();
        return cabecalhoCLDAO.bolFechList();
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
