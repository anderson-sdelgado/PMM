package br.com.usinasantafe.cmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.usinasantafe.cmm.model.bean.AtualAplicBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.cmm.model.dao.ApontMMFertDAO;
import br.com.usinasantafe.cmm.model.dao.AtividadeDAO;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.model.dao.BoletimMMFertDAO;
import br.com.usinasantafe.cmm.model.dao.CabecCheckListDAO;
import br.com.usinasantafe.cmm.model.dao.CarregCompDAO;
import br.com.usinasantafe.cmm.model.dao.ConfigDAO;
import br.com.usinasantafe.cmm.model.dao.EquipDAO;
import br.com.usinasantafe.cmm.model.dao.FrenteDAO;
import br.com.usinasantafe.cmm.model.dao.ImplementoMMDAO;
import br.com.usinasantafe.cmm.model.dao.LeiraDAO;
import br.com.usinasantafe.cmm.model.dao.LogErroDAO;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.model.dao.OSDAO;
import br.com.usinasantafe.cmm.model.dao.PropriedadeDAO;
import br.com.usinasantafe.cmm.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.cmm.model.dao.RecolhimentoFertDAO;
import br.com.usinasantafe.cmm.model.dao.RendimentoMMDAO;
import br.com.usinasantafe.cmm.model.dao.RespItemCheckListDAO;
import br.com.usinasantafe.cmm.util.AtualDadosServ;
import br.com.usinasantafe.cmm.util.Json;
import br.com.usinasantafe.cmm.util.VerifDadosServ;
import br.com.usinasantafe.cmm.view.TelaInicialActivity;

public class ConfigCTR {

    private int contDataHora;

    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;


    public ConfigCTR() {
    }

    /////////////////////////////////////// CONFIG ///////////////////////////////////////////////

    public boolean hasElemConfig(){
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

    public void setStatusConConfig(Long status){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusConConfig(status);
    }

    public void setPosicaoTela(Long posicaoTela){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setPosicaoTela(posicaoTela);
    }

    public void setStatusRetVerif(Long statusRetVerif){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusRetVerif(statusRetVerif);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// DATA HORA ///////////////////////////////////////////////

    public int getContDataHora() {
        return contDataHora;
    }

    public void setContDataHora(int contDataHora) {
        this.contDataHora = contDataHora;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public void setDifDthrConfig(Long difDthr){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDifDthrConfig(difDthr);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// EQUIP ///////////////////////////////////////////////////

    public void salvarConfigInicial(String senha, Long nroEquip){
        ConfigDAO configDAO = new ConfigDAO();
        EquipDAO equipDAO = new EquipDAO();
        EquipBean equipBean = equipDAO.getEquipNro(nroEquip);
        configDAO.salvarConfig(senha, equipBean);
    }

    public void verEquipConfig(String senha, String versao, String nroEquip, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity, int tipo){
        EquipDAO equipDAO = new EquipDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("equipDAO.verEquip(equipDAO.dadosVerEquip(Long.parseLong(nroEquip), versao), telaAtual, telaProx, progressDialog, activity, tipo);", activity);
        equipDAO.verEquip(Long.parseLong(nroEquip), senha, equipDAO.dadosVerEquip(Long.parseLong(nroEquip), versao), telaAtual, telaProx, progressDialog, activity, tipo);
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquipId(getConfig().getIdEquipApontConfig());
    }

    public EquipBean getEquip(Long idEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquipId(idEquip);
    }

    public void receberVerifEquip(Long nroEquip, String senha, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String result, int tipo){

        try {

            if (!result.contains("exceeded")) {

                String[] retorno = result.split("_");

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(retorno[0]);

                if (jsonArray.length() > 0) {

                    EquipDAO equipDAO = new EquipDAO();
                    equipDAO.recDadosEquip(jsonArray);
                    equipDAO.recDadosREquipAtiv(json.jsonArray(retorno[1]));

                    salvarConfigInicial(senha, nroEquip);

                    progressDialog.dismiss();
                    progressDialog = new ProgressDialog(telaAtual);
                    progressDialog.setCancelable(true);
                    progressDialog.setMessage("ATUALIZANDO ...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setProgress(0);
                    progressDialog.setMax(100);
                    progressDialog.show();

                    AtualDadosServ.getInstance().atualTodasTabBD(telaAtual, telaProx, progressDialog, "ConfigActivity", tipo);

                } else {
                    VerifDadosServ.getInstance().msg("EQUIPAMENTO INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
                }

            } else {
                VerifDadosServ.getInstance().msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE EQUIPAMENTO! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

    }

    public boolean verifEquip(Long nroEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.verifEquip(nroEquip);
    }

    public boolean verifEquipApont(){
        return Objects.equals(getConfig().getIdEquipConfig(), getConfig().getIdEquipApontConfig());
    }

    public boolean verifEquipApontCarretel(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        BoletimMMFertBean boletimMMFertBean = boletimMMFertDAO.getBolMMFertAberto(getConfig().getIdEquipApontConfig());
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        List<ApontMMFertBean> apontMMFertList = apontMMFertDAO.apontMMFertListIdBol(boletimMMFertBean.getIdBolMMFert());
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        Long idParadaApontaCarretel = rFuncaoAtivParDAO.idParadaApontaCarretel();
        for(ApontMMFertBean apontMMFertBean : apontMMFertList){
            if(Objects.equals(idParadaApontaCarretel, apontMMFertBean.getParadaApontMMFert())) return true;
        }
        return false;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// BOLETIM MM /////////////////////////////////////////////////

    public void setMatricFuncConfig(Long matricFuncConfig) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setMatricFuncConfig(matricFuncConfig);
    }

    public void setIdTurnoConfig(Long idTurnoConfig) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdTurnoConfig(idTurnoConfig);
    }

    public void setIdEquipBombaBolConfig(Long idEquipBombaBolConfig) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdEquipBombaBolConfig(idEquipBombaBolConfig);
    }

    public void setIdEquipApontConfig() {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdEquipApontConfig(configDAO.getConfig().getIdEquipConfig());
    }

    public void setIdEquipApontConfigNro(Long nroEquip) {
        ConfigDAO configDAO = new ConfigDAO();
        EquipDAO equipDAO = new EquipDAO();
        Long idEquip = equipDAO.getEquipNro(nroEquip).getIdEquip();
        configDAO.setIdEquipApontConfig(idEquip);
    }

    public void setIdEquipApontConfigId(Long idEquip) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdEquipApontConfig(idEquip);
    }

    public void setHodometroInicialConfig(Double hodometroInicialBolMMFert, Double longitudeBolMMFert, Double latitudeBolMMFert) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setHodometroInicialConfig(hodometroInicialBolMMFert, longitudeBolMMFert, latitudeBolMMFert);
    }

    public void setHodometroFinalConfig(Double hodometroFinalBolMMFert) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setHodometroFinalConfig(hodometroFinalBolMMFert);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// OS - ATIVIDADE /////////////////////////////////////////////

    public void clearOSAtiv(){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setNroOSConfig(0L);
        configDAO.setAtivConfig(0L);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// OS ////////////////////////////////////////////////////

    public void setNroOSConfig(Long nroOS){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setNroOSConfig(nroOS);
    }

    public boolean verOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        boolean retorno = false;
        if(osDAO.verOS(nroOS)){
            if(atividadeDAO.verROSAtiv(osDAO.getOS(nroOS).getIdOS())){
                retorno = true;
            }
        }
        return retorno;
    }

    public boolean verLib(Long idLib){
        OSDAO osDAO = new OSDAO();
        return osDAO.verLib(getConfig().getNroOSConfig(), idLib);
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(getConfig().getNroOSConfig());
    }

    public void osDelAll(){
        OSDAO osDAO = new OSDAO();
        osDAO.osDelAll();
    }

    public void rOSAtivDelAll(){
        OSDAO osDAO = new OSDAO();
        osDAO.rOSAtivDelAll();
    }

    public void receberVerifOS(String result){

        try {
            if (!result.contains("exceeded")) {

                String[] retorno = result.split("_");

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(retorno[0]);

                if (jsonArray.length() > 0) {

                    OSDAO osDAO = new OSDAO();
                    osDAO.recDadosOS(jsonArray);
                    osDAO.recDadosROSAtiv(json.jsonArray(retorno[1]));

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
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE OS! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// ATIVIDADE //////////////////////////////////////////////

    public void setAtivConfig(Long idAtiv){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setAtivConfig(idAtiv);
    }

    public void receberVerifAtiv(String result){

        try {

            if (!result.contains("exceeded")) {

                String[] retorno = result.split("_");

                Json json = new Json();

                EquipDAO equipDAO = new EquipDAO();
                equipDAO.recDadosREquipAtiv(json.jsonArray(retorno[0]));

                JSONArray jsonArray = json.jsonArray(retorno[1]);

                if (jsonArray.length() > 0) {
                    OSDAO osDAO = new OSDAO();
                    osDAO.recDadosROSAtiv(jsonArray);
                }

                AtividadeDAO atividadeDAO = new AtividadeDAO();
                atividadeDAO.recDadosAtiv(json.jsonArray(retorno[2]));

                RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
                rFuncaoAtivParDAO.recDadosRFuncaoAtivPar(json.jsonArray(retorno[3]));

                VerifDadosServ.getInstance().pulaTela();

            } else {
                VerifDadosServ.getInstance().msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }
        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

    public void receberVerifAtivECM(String result){

        try {

            if (!result.contains("exceeded")) {

                String[] retorno = result.split("_");

                Json json = new Json();

                EquipDAO equipDAO = new EquipDAO();
                equipDAO.recDadosREquipAtiv(json.jsonArray(retorno[0]));

                AtividadeDAO atividadeDAO = new AtividadeDAO();
                atividadeDAO.recDadosAtiv(json.jsonArray(retorno[1]));

                RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
                rFuncaoAtivParDAO.recDadosRFuncaoAtivPar(json.jsonArray(retorno[2]));

                VerifDadosServ.getInstance().pulaTela();

            } else {
                VerifDadosServ.getInstance().msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }
        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// PARADA ///////////////////////////////////////////////

    public void setUltParadaBolConfig(Long idParada){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setUltParadaBolConfig(idParada);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// HORIMETRO /////////////////////////////////////////////

    public void setHorimetroConfig(Double horimetro){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setHorimetroConfig(horimetro);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// CHECKLIST /////////////////////////////////////////////

    public void setCheckListConfig(Long idTurno){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setCheckListConfig(idTurno);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// FERTIRRIGAÇÃO ///////////////////////////////////////////

    public void clearDadosFert(){
        setBocalConfig(0L);
        setVelocConfig(0L);
        setPressaoConfig(0D);
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

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// COMPOSTAGEM ////////////////////////////////////////////


    public void setFuncaoComposto(Long funcaoComposto) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setFuncaoComposto(funcaoComposto);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////// ECM (FRENTE - PROPRIEDADE) ////////////////////////////////////

    public String getMsgPropriedade() {
        if (getConfig().getIdPropriedadeConfig() == 0L) {
            return "";
        } else {
            return "SEÇÃO " + getConfig().getCodPropriedadeConfig() + " - " + getConfig().getDescrPropriedadeConfig();
        }
    }

    public void setFrentePropriedade(String codFrente, String codPropriedade){
        FrenteDAO frenteDAO = new FrenteDAO();
        PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
        ConfigDAO configDAO = new ConfigDAO();
        if(frenteDAO.verFrente(Long.parseLong(codFrente))){
            configDAO.setFrente(frenteDAO.getFrente(Long.parseLong(codFrente)).getIdFrente());
        }
        if(propriedadeDAO.verPropriedade(Long.parseLong(codPropriedade))){
            configDAO.setPropriedade(propriedadeDAO.getPropriedadeCod(Long.parseLong(codPropriedade)));
        }
    }

    public void setCarreta(Long carreta){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setCarreta(carreta);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// ATUALIZAR APLIC /////////////////////////////////////////

    public AtualAplicBean recAtual(String result) {

        setStatusRetVerif(0L);
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
            LogErroDAO.getInstance().insertLogErro(e);
        }
        return atualAplicBean;
    }

    public void verAtualAplic(String versaoAplic, TelaInicialActivity telaInicialActivity, String activity) {
        EquipDAO equipDAO = new EquipDAO();
        EquipBean equipBean = equipDAO.getEquipId(getConfig().getIdEquipConfig());
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(equipBean.getNroEquip(), equipBean.getIdCheckList(), versaoAplic)\n" +
                "                , telaInicialActivity, progressDialog);", activity);
        VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(equipBean.getIdEquip(), equipBean.getIdCheckList(), versaoAplic)
                , telaInicialActivity, activity);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// ATUALIZAR DADOS ////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog, activity);", activity);
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog, activity, 1);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////// LOG ///////////////////////////////////////////////

    public List<LogProcessoBean> logProcessoList(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        return logProcessoDAO.logProcessoList();
    }

    public ArrayList<String> logBaseDadoList(){
        ArrayList<String> dadosArrayList = new ArrayList<>();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ImplementoMMDAO implementoMMDAO = new ImplementoMMDAO();
        RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
        RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
        LeiraDAO leiraDAO = new LeiraDAO();
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        dadosArrayList = boletimMMFertDAO.bolAllArrayList(dadosArrayList);
        dadosArrayList = apontMMFertDAO.apontAllArrayList(dadosArrayList);
        dadosArrayList = implementoMMDAO.apontImplAllArrayList(dadosArrayList);
        dadosArrayList = recolhimentoFertDAO.recolAllArrayList(dadosArrayList);
        dadosArrayList = rendimentoMMDAO.rendAllArrayList(dadosArrayList);
        dadosArrayList = leiraDAO.movLeiraAllArrayList(dadosArrayList);
        dadosArrayList = carregCompDAO.carregCompAllArrayList(dadosArrayList);
        dadosArrayList = cabecCheckListDAO.cabecCheckListAllArrayList(dadosArrayList);
        dadosArrayList = respItemCheckListDAO.respCheckListAllArrayList(dadosArrayList);
        return dadosArrayList;
    }

    public List<LogErroBean> logErroList(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.logErroBeanList();
    }

    public void deleteLogs(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        LogErroDAO logErroDAO = new LogErroDAO();
        logProcessoDAO.deleteLogProcesso();
        logErroDAO.deleteLogErro();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
