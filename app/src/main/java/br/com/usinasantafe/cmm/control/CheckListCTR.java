package br.com.usinasantafe.cmm.control;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.model.dao.CabecCheckListDAO;
import br.com.usinasantafe.cmm.model.dao.EquipDAO;
import br.com.usinasantafe.cmm.model.dao.ItemCheckListDAO;
import br.com.usinasantafe.cmm.model.dao.LogErroDAO;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.model.dao.RespItemCheckListDAO;
import br.com.usinasantafe.cmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.RespItemCheckListBean;
import br.com.usinasantafe.cmm.util.EnvioDadosServ;
import br.com.usinasantafe.cmm.util.Json;
import br.com.usinasantafe.cmm.util.VerifDadosServ;
import br.com.usinasantafe.cmm.util.workmanager.StartProcessEnvio;

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
        CabecCheckListBean cabecCheckListBean = cabecCheckListDAO.getCabecCheckListAberto();
        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        respItemCheckListDAO.clearRespItem(cabecCheckListBean.getIdCabCL());
    }

    public void createCabecAberto(String activity){
        ConfigCTR configCTR = new ConfigCTR();
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("cabecCheckListDAO.createCabecAberto(" + configCTR.getEquip().getNroEquip() + " , " + motoMecFertCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert() + ", " + motoMecFertCTR.getBoletimMMFertAberto().getIdTurnoBolMMFert() + ");", activity);
        cabecCheckListDAO.createCabecAberto(configCTR.getEquip().getNroEquip() , motoMecFertCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert(), motoMecFertCTR.getBoletimMMFertAberto().getIdTurnoBolMMFert());
    }

    public void salvarBolFechado(@NonNull Application application){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.salvarFechCheckList();
        StartProcessEnvio startProcessEnvio = new StartProcessEnvio();
        startProcessEnvio.startProcessEnvio(application);
    }

    public boolean verAberturaCheckList(Long turno){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        ConfigCTR configCTR = new ConfigCTR();
        configCTR.getConfig();
        return cabecCheckListDAO.verAberturaCheckList(turno, configCTR.getEquip().getIdCheckList()
                , configCTR.getConfig().getUltTurnoCLConfig(), configCTR.getConfig().getDtUltCLConfig());
    }

    public void atualCheckList(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        ConfigCTR configCTR = new ConfigCTR();
        itemCheckListDAO.atualCheckList(atualAplicDAO.getAtualNroEquip(configCTR.getEquip().getNroEquip()), telaAtual, telaProx, progressDialog, activity);
    }

    public void receberVerifCheckList(String result) {

        try {

            if (!result.contains("exceeded")) {

                String[] retorno = result.split("_");

                Json json = new Json();

                EquipDAO equipDAO = new EquipDAO();
                equipDAO.recDadosEquip(json.jsonArray(retorno[0]));

                ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
                itemCheckListDAO.recDadosCheckList(json.jsonArray(retorno[1]));

            }
            VerifDadosServ.getInstance().pulaTela();

        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().pulaTela();
        }

    }

    public List<ItemCheckListBean> getItemList(){
        ConfigCTR configCTR = new ConfigCTR();
        ItemCheckListDAO itemCheckListDAO = new ItemCheckListDAO();
        List<ItemCheckListBean> itemCheckListList = itemCheckListDAO.getItemList(configCTR.getEquip());
        return itemCheckListList;
    }

    public void setRespCheckList(RespItemCheckListBean respItemCheckListBean){
        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        respItemCheckListDAO.setRespCheckList(cabecCheckListDAO.getCabecCheckListAberto().getIdCabCL(), respItemCheckListBean);
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
        return !cabecCheckListList().isEmpty();
    }

    public String dadosEnvio(){

        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        String dadosEnvioCabecCheckList = cabecCheckListDAO.dadosEnvioCabecCheckList();

        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        String dadosEnvioRespItemCheckList = respItemCheckListDAO.dadosEnvioRespCheckList(respItemCheckListDAO.respItemList(cabecCheckListDAO.idCabecCheckListArrayList(cabecCheckListDAO.cabecCheckListFechList())));

        return dadosEnvioCabecCheckList + "_" + dadosEnvioRespItemCheckList;

    }

    public List<CabecCheckListBean> dadosEnvioRetrofit(){

        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        List<CabecCheckListBean> cabecCheckList = cabecCheckListDAO.cabecCheckListFechList();

        for(int i = 0; i < cabecCheckList.size(); i++){
            List<RespItemCheckListBean> respItemCheckListList = respItemCheckListDAO.respItemList(cabecCheckList.get(i).getIdCabCL());
            cabecCheckList.get(i).setRespItemCheckListList(respItemCheckListList);
        }

        return cabecCheckList;

    }

    public void updateRecebChecklist(String activity) {

        LogProcessoDAO.getInstance().insertLogProcesso("        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();\n" +
                "        cabecCheckListDAO.updateCabecCLEnviado();\n" +
                "        EnvioDadosServ.getInstance().envioDados(activity);", activity);

        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.updateCabecCLEnviado();

    }

    public void updateRecebChecklist(List<CabecCheckListBean> cabecCheckListBeanList) {

        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.updateCabecCLEnviado(cabecCheckListBeanList);


    }

    public void deleteChecklist(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        ArrayList<Long> idCabecCheckListArrayList = cabecCheckListDAO.idCabecCheckListEnviadoArrayList();
        respItemCheckListDAO.deleteRespItemCheckList(idCabecCheckListArrayList);
        cabecCheckListDAO.deleteCabecCheckListEnviado(idCabecCheckListArrayList);
    }

}
