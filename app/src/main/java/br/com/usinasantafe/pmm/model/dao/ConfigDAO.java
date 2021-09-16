package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.AtualAplicBean;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.all();
        configBean = configList.get(0);
        configList.clear();
        return configBean;
    }

    public boolean verSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.get("senhaConfig", senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    public void salvarConfig(String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setUltTurnoCLConfig(0L);
        configBean.setDtUltCLConfig("");
        configBean.setDtServConfig("");
        configBean.setDifDthrConfig(0L);
        configBean.setVerRecInformativo(0L);
        configBean.setFlagLogErro(0L);
        configBean.setFlagLogEnvio(0L);
        configBean.setOsConfig(0L);
        configBean.setAtivConfig(0L);
        configBean.setUltParadaBolConfig(0L);
        configBean.setPressaoConfig(0D);
        configBean.setVelocConfig(0L);
        configBean.setBocalConfig(0L);
        configBean.setSenhaConfig(senha);
        configBean.setPosFluxoCarregComposto(0L);
        configBean.setPosicaoTela(0L);
        configBean.setStatusRetVerif(0L);
        configBean.setIdFrenteConfig(0L);
        configBean.setIdPropriedadeConfig(0L);
        configBean.insert();
        configBean.commit();
    }

    public void setEquipConfig(EquipBean equipBean){
        ConfigBean configBean = getConfig();
        configBean.setEquipConfig(equipBean.getIdEquip());
        configBean.setHorimetroConfig(equipBean.getHorimetroEquip());
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

    public void setAtivConfig(Long idAtiv){
        ConfigBean configBean = getConfig();
        configBean.setAtivConfig(idAtiv);
        configBean.update();
    }

    public void setUltParadaBolConfig(Long idParada){
        ConfigBean configBean = getConfig();
        configBean.setUltParadaBolConfig(idParada);
        configBean.update();
    }

    public void setPressaoConfig(Double pressao){
        ConfigBean configBean = getConfig();
        configBean.setPressaoConfig(pressao);
        configBean.update();
    }

    public void setVelocConfig(Long veloc){
        ConfigBean configBean = getConfig();
        configBean.setVelocConfig(veloc);
        configBean.update();
    }

    public void setBocalConfig(Long bocal){
        ConfigBean configBean = getConfig();
        configBean.setBocalConfig(bocal);
        configBean.update();
    }

    public void setHorimetroConfig(Double horimetro){
        ConfigBean configBean = getConfig();
        configBean.setHorimetroConfig(horimetro);
        configBean.update();
    }

    public void setCheckListConfig(Long idTurno){
        ConfigBean configBean = getConfig();
        configBean.setUltTurnoCLConfig(idTurno);
        configBean.setDtUltCLConfig(Tempo.getInstance().dt());
        configBean.update();
    }

    public void setVerInforConfig(Long tipo){
        ConfigBean configBean = getConfig();
        configBean.setVerRecInformativo(tipo);
        configBean.update();
    }

    public void setDifDthrConfig(Long difDthr){
        ConfigBean configBean = getConfig();
        configBean.setDifDthrConfig(difDthr);
        configBean.update();
    }

    public void setPosFluxoCarregComposto(Long posFluxoCarregComposto){
        ConfigBean configBean = getConfig();
        configBean.setPosFluxoCarregComposto(posFluxoCarregComposto);
        configBean.update();
    }

    public void setPosicaoTela(Long posicaoTela){
        ConfigBean configBean = getConfig();
        configBean.setPosicaoTela(posicaoTela);
        configBean.update();
    }

    public void setStatusRetVerif(Long statusRetVerif){
        ConfigBean configBean = getConfig();
        configBean.setStatusRetVerif(statusRetVerif);
        configBean.update();
    }

    public void setFrentePropriedade(Long idFrente, Long idPropriedade){
        ConfigBean configBean = getConfig();
        configBean.setIdFrenteConfig(idFrente);
        configBean.setIdPropriedadeConfig(idPropriedade);
        configBean.update();
    }

    public Long getVerRecInformativo(){
        ConfigBean configBean = getConfig();
        return configBean.getVerRecInformativo();
    }

    public Long getStatusRetVerif(){
        ConfigBean configBean = getConfig();
        return configBean.getStatusRetVerif();
    }

    public AtualAplicBean recAtual(JSONArray jsonArray) throws JSONException {

        JSONObject objeto = jsonArray.getJSONObject(0);
        Gson gson = new Gson();
        AtualAplicBean atualAplicBean = gson.fromJson(objeto.toString(), AtualAplicBean.class);

        ConfigBean configBean = getConfig();
        configBean.setFlagLogEnvio(atualAplicBean.getFlagLogEnvio());
        configBean.setFlagLogErro(atualAplicBean.getFlagLogErro());
        configBean.setDtServConfig(atualAplicBean.getDthr());
        configBean.setAtualCheckList(atualAplicBean.getFlagAtualCheckList());
        configBean.update();

        return atualAplicBean;

    }

}
