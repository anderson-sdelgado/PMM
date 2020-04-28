package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import br.com.usinasantafe.pmm.model.dao.ConfigDAO;
import br.com.usinasantafe.pmm.model.dao.EquipDAO;
import br.com.usinasantafe.pmm.model.dao.OSDAO;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.util.AtualDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
    }

    public void setEquipConfig(EquipBean equipBean){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setEquipConfig(equipBean);
    }

    public void setDtServConfig(String data){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDtServConfig(data);
    }

    public void setStatusConConfig(Long status){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusConConfig(status);
    }

    public void setOsConfig(Long nroOS){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setOsConfig(nroOS);
    }

    public void setDtUltApontConfig(String data){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDtUltApontConfig(data);
    }

    public void setHorimetroConfig(Double horimetro){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setHorimetroConfig(horimetro);
    }

    public void setCheckListConfig(Long idTurno){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setCheckListConfig(idTurno);
    }

    public void verEquipConfig(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog);
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip();
    }

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    public void atualVerInforConfig(Long tipo){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setVerInforConfig(tipo);
    }

    public Long getVerInforConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getVerInforConfig();
    }

    public boolean verTipoOS(){
        ConfigDAO configDAO = new ConfigDAO();
        OSDAO osDAO = new OSDAO();
        return osDAO.verTipoOS(configDAO.getConfig().getOsConfig());
    }

    public void setDifDthrConfig(Long status){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDifDthrConfig(status);
    }



}
