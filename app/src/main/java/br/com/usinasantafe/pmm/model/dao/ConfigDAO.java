package br.com.usinasantafe.pmm.model.dao;

import java.util.List;

import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public ConfigBean getConfig(){
        ConfigBean configBean = new ConfigBean();
        List listConfigTO = configBean.all();
        configBean = (ConfigBean) listConfigTO.get(0);
        listConfigTO.clear();
        return configBean;
    }

    public void salvarConfig(String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setUltTurnoCLConfig(0L);
        configBean.setDtUltCLConfig("");
        configBean.setDtUltApontConfig("");
        configBean.setDtServConfig("");
        configBean.setDifDthrConfig(0L);
        configBean.setVerInforConfig(0L);
        configBean.setSenhaConfig(senha);
        configBean.insert();
        configBean.commit();
    }

    public void setEquipConfig(EquipBean equipBean){
        ConfigBean configBean = getConfig();
        configBean.setEquipConfig(equipBean.getIdEquip());
        configBean.setHorimetroConfig(equipBean.getHorimetroEquip());
        configBean.update();
    }

    public void setDtServConfig(String data){
        ConfigBean configBean = getConfig();
        configBean.setDtServConfig(data);
        configBean.update();
    }

    public void setStatusConConfig(Long status){
        ConfigBean configBean = getConfig();
        configBean.setStatusConConfig(status);
        configBean.update();
    }

    public void setOsConfig(Long nroOS){
        ConfigBean configBean = getConfig();
        configBean.setOsConfig(nroOS);
        configBean.update();
    }

    public void setDtUltApontConfig(String data){
        ConfigBean configBean = getConfig();
        configBean.setDtUltApontConfig(data);
        configBean.update();
    }

    public void setHorimetroConfig(Double horimetro){
        ConfigBean configBean = getConfig();
        configBean.setHorimetroConfig(horimetro);
        configBean.setDtUltApontConfig("");
        configBean.update();
    }

    public void setCheckListConfig(Long idTurno){
        ConfigBean configBean = getConfig();
        configBean.setUltTurnoCLConfig(idTurno);
        configBean.setDtUltCLConfig(Tempo.getInstance().dataSHora());
        configBean.update();
    }

    public void setVerInforConfig(Long tipo){
        ConfigBean configBean = getConfig();
        configBean.setVerInforConfig(tipo);
        configBean.update();
    }

    public Long getVerInforConfig(){
        ConfigBean configBean = getConfig();
        return configBean.getVerInforConfig();
    }

    public void setDifDthrConfig(Long status){
        ConfigBean configBean = getConfig();
        configBean.setDifDthrConfig(status);
        configBean.update();
    }

}
