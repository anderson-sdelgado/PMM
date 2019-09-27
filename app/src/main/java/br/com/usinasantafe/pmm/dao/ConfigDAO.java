package br.com.usinasantafe.pmm.dao;

import java.util.List;

import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public ConfigTO getConfig(){
        ConfigTO configTO = new ConfigTO();
        List listConfigTO = configTO.all();
        configTO = (ConfigTO) listConfigTO.get(0);
        listConfigTO.clear();
        return configTO;
    }

    public void salvarConfig(String senha){
        ConfigTO configTO = new ConfigTO();
        configTO.deleteAll();
        configTO.setUltTurnoCLConfig(0L);
        configTO.setDtUltCLConfig("");
        configTO.setDtUltApontConfig("");
        configTO.setDtServConfig("");
        configTO.setDifDthrConfig(0L);
        configTO.setVisDadosColhConfig(0L);
        configTO.setSenhaConfig(senha);
        configTO.insert();
        configTO.commit();
    }

    public void setEquipConfig(EquipTO equipTO){
        ConfigTO configTO = getConfig();
        configTO.setEquipConfig(equipTO.getIdEquip());
        configTO.setHorimetroConfig(equipTO.getHorimetroEquip());
        configTO.update();
    }

    public void setDtServConfig(String data){
        ConfigTO configTO = getConfig();
        configTO.setDtServConfig(data);
        configTO.update();
    }

    public void setStatusConConfig(Long status){
        ConfigTO configTO = getConfig();
        configTO.setStatusConConfig(status);
        configTO.update();
    }

    public void setOsConfig(Long nroOS){
        ConfigTO configTO = getConfig();
        configTO.setOsConfig(nroOS);
        configTO.update();
    }

    public void setDtUltApontConfig(String data){
        ConfigTO configTO = getConfig();
        configTO.setDtUltApontConfig(data);
        configTO.update();
    }

    public void setHorimetroConfig(Double horimetro){
        ConfigTO configTO = getConfig();
        configTO.setHorimetroConfig(horimetro);
        configTO.setDtUltApontConfig("");
        configTO.update();
    }

    public void setCheckListConfig(Long idTurno){
        ConfigTO configTO = getConfig();
        configTO.setUltTurnoCLConfig(idTurno);
        configTO.setDtUltCLConfig(Tempo.getInstance().dataSHora());
        configTO.update();
    }

    public void atualVisDadosColhConfig(Long tipo){
        ConfigTO configTO = getConfig();
        configTO.setVisDadosColhConfig(tipo);
        configTO.update();
    }

    public Long getVisDadosColhConfig(){
        ConfigTO configTO = getConfig();
        return configTO.getVisDadosColhConfig();
    }

}