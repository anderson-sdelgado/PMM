package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.AtualAplicBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pmm.model.dao.AtividadeDAO;
import br.com.usinasantafe.pmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pmm.model.dao.ConfigDAO;
import br.com.usinasantafe.pmm.model.dao.EquipDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.OSDAO;
import br.com.usinasantafe.pmm.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Json;
import br.com.usinasantafe.pmm.util.VerifDadosServ;
import br.com.usinasantafe.pmm.view.MenuInicialActivity;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public boolean verSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.verSenha(senha);
    }

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public void clearDadosFert(){
        setBocalConfig(0L);
        setVelocConfig(0L);
        setPressaoConfig(0D);
    }

    /////////////////////////////////////// SET //////////////////////////////////////////////////

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

    public void setUltParadaBolConfig(Long idParada){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setUltParadaBolConfig(idParada);
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

    public void setHorimetroConfig(Double horimetro){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setHorimetroConfig(horimetro);
    }

    public void setCheckListConfig(Long idTurno){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setCheckListConfig(idTurno);
    }

    public void setPosFluxoViagem(Long posFluxoViagem){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setPosFluxoViagem(posFluxoViagem);
    }

    public void setDifDthrConfig(Long difDthrConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDifDthrConfig(difDthrConfig);
    }

    public void setPosicaoTela(Long posicaoTela){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setPosicaoTela(posicaoTela);
    }

    public void setStatusRetVerif(Long statusRetVerif){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusRetVerif(statusRetVerif);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// OS ////////////////////////////////////////////////////

    public boolean verOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        return (osDAO.verOS(nroOS) && atividadeDAO.verROSAtiv(nroOS));
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOSBean(getConfig().getOsConfig());
    }

    public void osDelAll(){
        OSDAO osDAO = new OSDAO();
        osDAO.osDelAll();
    }

    public void rOSAtivDelAll(){
        OSDAO osDAO = new OSDAO();
        osDAO.rOSAtivDelAll();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// EQUIP ///////////////////////////////////////////////////

    public void verAtualAplic(String versaoAplic, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {
        VerifDadosServ.getInstance().verifAtualAplic(dadosVerAtualAplicBean(versaoAplic), menuInicialActivity, progressDialog);
    }

    public void verEquipConfig(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog);
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// ATUALIZAR DADOS ////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    public void atualTodasTabelas(){
        AtualDadosServ.getInstance().atualTodasTabBD();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// INFORMAÇÃO /////////////////////////////////////////////

    public void setVerInforConfig(Long tipo){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setVerInforConfig(tipo);
    }

    public Long getVerRecInformativo(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getVerRecInformativo();
    }

    public Long getStatusRetVerif(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getStatusRetVerif();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// LOG ERRO //////////////////////////////////////////////

    public boolean verEnvioLogErro(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.verEnvioLogErro();
    }

    public String dadosEnvioLogErro(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.dadosEnvio();
    }

    public void updLogErro(String retorno){

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            Json json = new Json();
            LogErroDAO logErroDAO = new LogErroDAO();
            logErroDAO.updLogErro(json.jsonArray(objPrinc));
            logErroDAO.delLogErroFechado();

            EnvioDadosServ.getInstance().envioDados(6);

        }
        catch(Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insert(e);
        }


    }

    public void receberVerifEquip(String result){

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("#") + 1;
                int pos2 = result.indexOf("_") + 1;

                String objPrinc = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1, (pos2 - 1));

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(objPrinc);

                if (jsonArray.length() > 0) {

                    EquipDAO equipDAO = new EquipDAO();
                    EquipBean equipBean = equipDAO.recDadosEquip(jsonArray);
                    equipDAO.recDadosREquipAtiv(json.jsonArray(objSeg));

                    setEquipConfig(equipBean);

                    VerifDadosServ.getInstance().pulaTela();

                } else {
                    VerifDadosServ.getInstance().msg("EQUIPAMENTO INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
                }

            } else {
                VerifDadosServ.getInstance().msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            LogErroDAO.getInstance().insert(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE EQUIPAMENTO! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

    }

    public void receberVerifOS(String result){

        try {
            if (!result.contains("exceeded")) {

                int posicao = result.indexOf("#") + 1;
                String objPrinc = result.substring(0, result.indexOf("#"));
                String objSeg = result.substring(posicao);

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(objPrinc);

                if (jsonArray.length() > 0) {

                    OSDAO osDAO = new OSDAO();
                    osDAO.recDadosOS(jsonArray);
                    osDAO.recDadosROSAtiv(json.jsonArray(objSeg));

                    setStatusConConfig(1L);
                    VerifDadosServ.getInstance().pulaTela();

                } else {
                    setStatusConConfig(0L);
                    VerifDadosServ.getInstance().msg("OS INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
                }

            }
            else{
                setStatusConConfig(0L);
                VerifDadosServ.getInstance().msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            setStatusConConfig(0L);
            LogErroDAO.getInstance().insert(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE OS! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

    public void receberVerifAtiv(String result){

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("_") + 1;
                int pos2 = result.indexOf("|") + 1;
                int pos3 = result.indexOf("#") + 1;

                String objPrim = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1, (pos2 - 1));
                String objTerc = result.substring(pos2, (pos3 - 1));
                String objQuart = result.substring(pos3);

                Json json = new Json();

                EquipDAO equipDAO = new EquipDAO();
                equipDAO.recDadosREquipAtiv(json.jsonArray(objPrim));

                JSONArray jsonArray = json.jsonArray(objSeg);

                if (jsonArray.length() > 0) {
                    OSDAO osDAO = new OSDAO();
                    osDAO.recDadosROSAtiv(jsonArray);
                }

                AtividadeDAO atividadeDAO = new AtividadeDAO();
                atividadeDAO.recDadosAtiv(json.jsonArray(objTerc));

                RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
                rFuncaoAtivParDAO.recDadosRFuncaoAtivPar(json.jsonArray(objQuart));

                VerifDadosServ.getInstance().pulaTela();

            } else {
                VerifDadosServ.getInstance().msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }
        } catch (Exception e) {
            LogErroDAO.getInstance().insert(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

    public AtualAplicBean recAtual(String result) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {
                ConfigDAO configDAO = new ConfigDAO();
                atualAplicBean = configDAO.recAtual(jsonArray);
            }

        } catch (Exception e) {
            VerifDadosServ.status = 1;
            LogErroDAO.getInstance().insert(e);
        }
        return atualAplicBean;
    }

    public String dadosVerAtualAplicBean(String versaoAplic){
        EquipDAO equipDAO = new EquipDAO();
        EquipBean equipBean = equipDAO.getEquip();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        return atualAplicDAO.dadosVerAtualAplicBean(equipBean.getNroEquip(), equipBean.getIdCheckList(), versaoAplic);
    }

}
