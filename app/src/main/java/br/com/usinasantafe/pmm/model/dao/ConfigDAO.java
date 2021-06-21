package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
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
        List listConfigTO = configBean.all();
        configBean = (ConfigBean) listConfigTO.get(0);
        listConfigTO.clear();
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
        configBean.setDtUltApontConfig("");
        configBean.setDtServConfig("");
        configBean.setDifDthrConfig(0L);
        configBean.setVerInforConfig(0L);
        configBean.setFlagLogErro(0L);
        configBean.setFlagLogEnvio(0L);
        configBean.setPressaoConfig(0D);
        configBean.setVelocConfig(0L);
        configBean.setBocalConfig(0L);
        configBean.setAplic(2L);  // 1 - PMM; 2 - ECM; 3 - PCOMP
        configBean.setSenhaConfig(senha);
        configBean.setPosFluxoViagem(0L);
        configBean.setPosicaoTela(0L);
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

    public void setAtivConfig(Long idAtiv){
        ConfigBean configBean = getConfig();
        configBean.setAtivConfig(idAtiv);
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

    public void setPosFluxoViagem(Long posFluxoViagem){
        ConfigBean configBean = getConfig();
        configBean.setPosFluxoViagem(posFluxoViagem);
        configBean.update();
    }

    public void setPosicaoTela(Long posicaoTela){
        ConfigBean configBean = getConfig();
        configBean.setPosicaoTela(posicaoTela);
        configBean.update();
    }

    public AtualAplicBean recAtual(String result) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                atualAplicBean = gson.fromJson(objeto.toString(), AtualAplicBean.class);

                ConfigBean configBean = getConfig();
                configBean.setFlagLogEnvio(atualAplicBean.getFlagLogEnvio());
                configBean.setFlagLogErro(atualAplicBean.getFlagLogErro());
                configBean.setDtServConfig(atualAplicBean.getDthr());
                configBean.setAtualCheckList(atualAplicBean.getFlagAtualCheckList());
                configBean.update();

            }

        } catch (Exception e) {
            LogErroDAO.getInstance().insert(e);
        }

        return atualAplicBean;

    }

}
