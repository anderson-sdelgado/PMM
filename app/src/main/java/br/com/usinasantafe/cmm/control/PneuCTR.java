package br.com.usinasantafe.cmm.control;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.estaticas.REquipPneuBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimPneuBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ItemCalibPneuBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ItemManutPneuBean;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.model.dao.BoletimMMFertDAO;
import br.com.usinasantafe.cmm.model.dao.BoletimPneuDAO;
import br.com.usinasantafe.cmm.model.dao.EquipDAO;
import br.com.usinasantafe.cmm.model.dao.EquipPneuDAO;
import br.com.usinasantafe.cmm.model.dao.ItemCalibPneuDAO;
import br.com.usinasantafe.cmm.model.dao.ItemManutPneuDAO;
import br.com.usinasantafe.cmm.model.dao.LogErroDAO;
import br.com.usinasantafe.cmm.model.dao.PneuDAO;
import br.com.usinasantafe.cmm.util.Json;
import br.com.usinasantafe.cmm.util.VerifDadosServ;
import br.com.usinasantafe.cmm.util.workmanager.StartProcessEnvio;

public class PneuCTR {

    private ItemManutPneuBean itemManutPneuBean;
    private ItemCalibPneuBean itemCalibPneuBean;

    private Long tipoPneu;

    public PneuCTR() {
    }

    public void setItemManutPneuBean() {
        this.itemManutPneuBean = new ItemManutPneuBean();
    }

    public void setItemCalibPneuBean() {
        this.itemCalibPneuBean = new ItemCalibPneuBean();
    }

    public ItemManutPneuBean getItemManutPneuBean() {
        if (itemManutPneuBean == null)
            itemManutPneuBean = new ItemManutPneuBean();
        return itemManutPneuBean;
    }

    public ItemCalibPneuBean getItemCalibPneuBean() {
        if (itemCalibPneuBean == null)
            itemCalibPneuBean = new ItemCalibPneuBean();
        return itemCalibPneuBean;
    }

    public Long getTipoPneu() {
        return tipoPneu;
    }

    public void setTipoPneu(Long tipoPneu) {
        this.tipoPneu = tipoPneu;
    }

    public boolean verifBolPneuAberto(){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        return boletimPneuDAO.verifBolPneuAberto();
    }

    public BoletimPneuBean getBolPneuAberto(){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        return boletimPneuDAO.getBolPneuAberto();
    }

    public void abrirBolPneu(Long nroEquip){
        EquipPneuDAO equipPneuDAO = new EquipPneuDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = boletimMMFertDAO.getBolMMFertAberto(configCTR.getConfig().getIdEquipApontConfig());
        boletimPneuDAO.abrirBolPneu(boletimMMFertBean.getIdBolMMFert()
                , boletimMMFertBean.getMatricFuncBolMMFert()
                , equipPneuDAO.getEquipPneuNro(nroEquip).getIdEquip()
                , this.tipoPneu);
    }

    public void fecharBolPneu(@NonNull Application application, String activity){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        boletimPneuDAO.fecharBolPneu();

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        boletimMMFertDAO.updateBolMMFertEnviar(boletimPneuDAO.getBolPneuAberto().getIdBolMMFertPneu());

        StartProcessEnvio startProcessEnvio = new StartProcessEnvio();
        startProcessEnvio.startProcessEnvio(application);

    }

    public void deleteBolPneuAberto(){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();
        itemCalibPneuDAO.deleteItemMedPneuIdBol(boletimPneuDAO.getBolPneuAberto().getIdBolPneu());
        ItemManutPneuDAO itemManutPneuDAO = new ItemManutPneuDAO();
        itemManutPneuDAO.deleteItemManutPneuIdBol(boletimPneuDAO.getBolPneuAberto().getIdBolPneu());
        boletimPneuDAO.deleteBolPneuAberto();
    }

    public void salvarItemCalibPneu(){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();
        itemCalibPneuDAO.salvarItemCalibPneu(boletimPneuDAO.getBolPneuAberto().getIdBolPneu(), getItemCalibPneuBean());
    }

    public void salvarItemManutPneu(){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ItemManutPneuDAO itemManutPneuDAO = new ItemManutPneuDAO();
        itemManutPneuDAO.insertItemManutPneu(boletimPneuDAO.getBolPneuAberto().getIdBolPneu(), getItemManutPneuBean());
    }

    public void salvarItemManutPneu(ItemManutPneuBean itemManutPneuBean){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ItemManutPneuDAO itemManutPneuDAO = new ItemManutPneuDAO();
        itemManutPneuDAO.insertItemManutPneu(boletimPneuDAO.getBolPneuAberto().getIdBolPneu(), itemManutPneuBean);
    }

    public boolean verifFinalItemCalibPneuBolAberto(){
        EquipDAO equipDAO = new EquipDAO();
        ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        int qtde1 = equipDAO.rEquipPneuList(boletimPneuDAO.getBolPneuAberto().getIdEquipBolPneu()).size();
        int qtde2 = itemCalibPneuDAO.itemMedPneuIdBolList(boletimPneuDAO.getBolPneuAberto().getIdBolPneu()).size();
        return (qtde1 == qtde2);
    }

    public boolean hasItemPneuManutBolAberto(){
        ItemManutPneuDAO itemManutPneuDAO = new ItemManutPneuDAO();
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        return itemManutPneuDAO.itemManutPneuIdBolList(boletimPneuDAO.getBolPneuAberto().getIdBolPneu()).size() > 0;
    }

    public ArrayList<REquipPneuBean> rEquipPneuCalibList(){

        ArrayList<REquipPneuBean> rEquipPneuArrayList = new ArrayList();
        EquipDAO equipDAO = new EquipDAO();
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();

        List<REquipPneuBean> rEquipPneuList = equipDAO.rEquipPneuList(boletimPneuDAO.getBolPneuAberto().getIdEquipBolPneu());
        for (REquipPneuBean rEquipPneuBean : rEquipPneuList){
            if(!itemCalibPneuDAO.verItemMedPneuIdBolIdPosConf(boletimPneuDAO.getBolPneuAberto().getIdBolPneu()
                    , rEquipPneuBean.getIdPosConfPneu())){
                rEquipPneuBean.setStatusPneu(1L);
                rEquipPneuArrayList.add(rEquipPneuBean);
            }
        }

        for (REquipPneuBean rEquipPneuBean : rEquipPneuList){
            if(itemCalibPneuDAO.verItemMedPneuIdBolIdPosConf(boletimPneuDAO.getBolPneuAberto().getIdBolPneu()
                    , rEquipPneuBean.getIdPosConfPneu())){
                rEquipPneuBean.setStatusPneu(2L);
                rEquipPneuArrayList.add(rEquipPneuBean);
            }
        }

        return rEquipPneuArrayList;
    }

    public ArrayList<REquipPneuBean> rEquipPneuManutList(){

        ArrayList<REquipPneuBean> rEquipPneuArrayList = new ArrayList();
        EquipDAO equipDAO = new EquipDAO();
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ItemManutPneuDAO itemManutPneuDAO = new ItemManutPneuDAO();

        List<REquipPneuBean> rEquipPneuList = equipDAO.rEquipPneuList(boletimPneuDAO.getBolPneuAberto().getIdEquipBolPneu());
        for (REquipPneuBean rEquipPneuBean : rEquipPneuList){
            if(!itemManutPneuDAO.verItemManutPneuIdBolIdPosConf(boletimPneuDAO.getBolPneuAberto().getIdBolPneu()
                    , rEquipPneuBean.getIdPosConfPneu())){
                rEquipPneuBean.setStatusPneu(1L);
                rEquipPneuArrayList.add(rEquipPneuBean);
            }
        }

        for (REquipPneuBean rEquipPneuBean : rEquipPneuList){
            if(itemManutPneuDAO.verItemManutPneuIdBolIdPosConf(boletimPneuDAO.getBolPneuAberto().getIdBolPneu()
                    , rEquipPneuBean.getIdPosConfPneu())){
                rEquipPneuBean.setStatusPneu(2L);
                rEquipPneuArrayList.add(rEquipPneuBean);
            }
        }

        return rEquipPneuArrayList;

    }

    public boolean verEquipPneuNro(Long nroEquip){
        EquipPneuDAO equipPneuDAO = new EquipPneuDAO();
        return equipPneuDAO.verEquipPneuNro(nroEquip);
    }

    public boolean verPneuNro(String nroPneu){
        PneuDAO pneuDAO = new PneuDAO();
        return pneuDAO.verPneuNro(nroPneu);
    }

    public boolean verPneuRepetidoNro(String nroPneu){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();
        return itemCalibPneuDAO.verItemMedPneuIdBolNroPneu(boletimPneuDAO.getBolPneuAberto().getIdBolPneu(), nroPneu);
    }

    public void verPneu(String codPneu, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        PneuDAO pneuDAO = new PneuDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        pneuDAO.verPneu(atualAplicDAO.getAtualCodPneu(codPneu), telaAtual, telaProx, progressDialog, itemManutPneuBean, activity);
    }

    public void receberVerifPneu(String result, ItemManutPneuBean itemManutPneuBean, String activity){

        try {

            if (!result.contains("exceeded")) {

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(result);

                if (jsonArray.length() > 0) {

                    PneuDAO pneuDAO = new PneuDAO();
                    pneuDAO.recDadosPneu(jsonArray);

                    if(activity.contains("PneuColActivity")){
                        salvarItemManutPneu(itemManutPneuBean);
                    }

                    VerifDadosServ.getInstance().pulaTela();

                } else {
                    VerifDadosServ.getInstance().msg("PNEU INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
                }

            } else {
                VerifDadosServ.getInstance().msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }
        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

    public boolean verItemMedPneuBolAberto(){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();
        return itemCalibPneuDAO.verItemMedPneuIdBolIdPosConf(boletimPneuDAO.getBolPneuAberto().getIdBolPneu()
                , getItemCalibPneuBean().getIdPosConfItemCalibPneu());
    }

    public ItemCalibPneuBean getItemMedPneuBolAberto(){
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();
        return itemCalibPneuDAO.getItemMedPneuIdBolIdPosConf(boletimPneuDAO.getBolPneuAberto().getIdBolPneu()
                , getItemCalibPneuBean().getIdPosConfItemCalibPneu());
    }

}
