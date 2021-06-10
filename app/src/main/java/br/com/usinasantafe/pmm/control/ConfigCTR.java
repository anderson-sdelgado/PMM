package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import br.com.usinasantafe.pmm.model.bean.AtualAplicBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pmm.model.dao.AtividadeDAO;
import br.com.usinasantafe.pmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pmm.model.dao.ConfigDAO;
import br.com.usinasantafe.pmm.model.dao.EquipDAO;
import br.com.usinasantafe.pmm.model.dao.FuncDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.OSDAO;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.util.AtualDadosServ;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public boolean verSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.verSenha(senha);
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

    public void setAtivConfig(Long idAtiv){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setAtivConfig(idAtiv);
    }

    public void setPressaoConfig(Double pressao){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setPressaoConfig(pressao);
    }

    public void setVelocConfig(Long veloc){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setVelocConfig(veloc);
    }

    public void setBocalConfig(Long bocal){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setBocalConfig(bocal);
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

    public void atualTodasTabelas(){
        AtualDadosServ.getInstance().atualTodasTabBD();
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

    public boolean verOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        return osDAO.verOS(nroOS);
    }

    public boolean verEnvioLogErro(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.verEnvioLogErro();
    }

    public String dadosEnvioLogErro(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.dadosEnvio();
    }

    public void updLogErro(String retorno){
        LogErroDAO logErroDAO = new LogErroDAO();
        logErroDAO.updLogErro(retorno);
    }

    public void clearDadosFert(){
        setBocalConfig(0L);
        setVelocConfig(0L);
        setPressaoConfig(0D);
    }

    ///////////////////////////// FUNCIONARIO ////////////////////////////////////////////

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(matricFunc);
    }

    public FuncBean getFunc(Long matricColab){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(matricColab);
    }

    public boolean verEquipMotoMec(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipoEquip() == 1) {
            return true;
        }
        else{
            return false;
        }
    }

    public void recDadosEquip(String result){

        int pos1 = result.indexOf("#") + 1;
        int pos2 = result.indexOf("_") + 1;
        String objPrinc = result.substring(0, (pos1 - 1));
        String objSeg = result.substring(pos1, (pos2 - 1));

        EquipDAO equipDAO = new EquipDAO();
        equipDAO.recDadosEquip(objPrinc, objSeg);

    }

    public void recDadosOS(String result){
        if (!result.contains("exceeded")) {
            OSDAO osDAO = new OSDAO();
            osDAO.recDadosOS(result);
        } else {
            VerifDadosServ.getInstance().msgSemTerm("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
        }
    }

    public void recDadosAtiv(String result){

        if (!result.contains("exceeded")) {

            int pos1 = result.indexOf("_") + 1;
            int pos2 = result.indexOf("|") + 1;
            int pos3 = result.indexOf("#") + 1;

            String objPrim = result.substring(0, (pos1 - 1));
            String objSeg = result.substring(pos1, (pos2 - 1));
            String objTerc = result.substring(pos2, (pos3 - 1));
            String objQuart = result.substring(pos3);

            AtividadeDAO atividadeDAO = new AtividadeDAO();
            atividadeDAO.recDadosAtiv(objPrim, objSeg, objTerc, objQuart);

        } else {
            VerifDadosServ.getInstance().msgSemTerm("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
        }

    }

    public AtualAplicBean recAtual(String result) {
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.recAtual(result);
    }

    public String dadosVerAtualAplicBean(String versaoAplic){
        EquipDAO equipDAO = new EquipDAO();
        EquipBean equipBean = equipDAO.getEquip();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        return atualAplicDAO.dadosVerAtualAplicBean(equipBean.getNroEquip(), equipBean.getIdCheckList(), versaoAplic);
    }


}
