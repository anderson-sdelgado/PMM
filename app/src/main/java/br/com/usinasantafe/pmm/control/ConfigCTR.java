package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import br.com.usinasantafe.pmm.dao.ConfigDAO;
import br.com.usinasantafe.pmm.dao.EquipDAO;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigTO configTO = new ConfigTO();
        return configTO.hasElements();
    }

    public ConfigTO getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
    }

    public void atualEquipConfig(EquipTO equipTO){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.atualEquipConfig(equipTO);
    }

    public void atualDtServConfig(String data){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.atualDtServConfig(data);
    }

    public void atualStatusConConfig(Long status){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.atualStatusConConfig(status);
    }

    public void atualOsConfig(Long nroOS){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.atualOsConfig(nroOS);
    }

    public void atualDtUltApontConfig(String data){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.atualDtUltApontConfig(data);
    }

    public void atualHorimetroConfig(Double horimetro){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.atualHorimetroConfig(horimetro);
    }

    public void atualCheckListConfig(Long idTurno, String dataComHora){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.atualCheckListConfig(idTurno, dataComHora);
    }

    public void verEquipConfig(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog);
    }

    public EquipTO getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip();
    }

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    public void atualVisDadosColhConfig(Long tipo){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.atualVisDadosColhConfig(tipo);
    }

    public Long getVisDadosColhConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getVisDadosColhConfig();
    }

}
