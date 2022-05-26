package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.AtualAplicBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.PropriedadeBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pmm.model.dao.ApontMMFertDAO;
import br.com.usinasantafe.pmm.model.dao.AtividadeDAO;
import br.com.usinasantafe.pmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pmm.model.dao.BoletimMMFertDAO;
import br.com.usinasantafe.pmm.model.dao.CabecCheckListDAO;
import br.com.usinasantafe.pmm.model.dao.CarregCompDAO;
import br.com.usinasantafe.pmm.model.dao.ConfigDAO;
import br.com.usinasantafe.pmm.model.dao.EquipDAO;
import br.com.usinasantafe.pmm.model.dao.FrenteDAO;
import br.com.usinasantafe.pmm.model.dao.ImplementoMMDAO;
import br.com.usinasantafe.pmm.model.dao.LeiraDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.model.dao.OSDAO;
import br.com.usinasantafe.pmm.model.dao.PropriedadeDAO;
import br.com.usinasantafe.pmm.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.pmm.model.dao.RecolhimentoFertDAO;
import br.com.usinasantafe.pmm.model.dao.RendimentoMMDAO;
import br.com.usinasantafe.pmm.model.dao.RespItemCheckListDAO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;
import br.com.usinasantafe.pmm.util.Json;
import br.com.usinasantafe.pmm.util.VerifDadosServ;
import br.com.usinasantafe.pmm.view.TelaInicialActivity;

public class ConfigCTR {

    private int contDataHora;

    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;

    private Long idPropriedade;
    private Long idFrente;

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

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
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

    public void setFuncaoPCOMP(Long funcaoPCOMP) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setFuncaoPCOMP(funcaoPCOMP);
    }

    public Long getStatusRetVerif(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getStatusRetVerif();
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

    public void setEquipConfig(EquipBean equipBean){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setEquipConfig(equipBean);
    }

    public void verEquipAtualTodosDadosConfig(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity, int tipo){
        EquipDAO equipDAO = new EquipDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog);", activity);
        equipDAO.verEquip(getEquip().getNroEquip().toString(), telaAtual, telaProx, progressDialog, activity, tipo);
    }

    public void verEquipConfig(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity, int tipo){
        EquipDAO equipDAO = new EquipDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog);", activity);
        equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog, activity, tipo);
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip();
    }

    public void receberVerifEquip(String result, int tipo){

        try {

            if (!result.contains("exceeded")) {

                String[] retorno = result.split("_");

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(retorno[0]);

                if (jsonArray.length() > 0) {

                    EquipDAO equipDAO = new EquipDAO();
                    EquipBean equipBean = equipDAO.recDadosEquip(jsonArray);
                    equipDAO.recDadosREquipAtiv(json.jsonArray(retorno[1]));
                    equipDAO.recDadosREquipPneu(json.jsonArray(retorno[2]));

                    setEquipConfig(equipBean);

                    if(tipo == 1){
                        VerifDadosServ.getInstance().pulaTela();
                    } else {
                        VerifDadosServ.getInstance().atualTodosDados();
                    }

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

    public void setPosFluxoCarregComposto(Long posFluxoCarregComposto){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setPosFluxoCarregComposto(posFluxoCarregComposto);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////// ECM (FRENTE - PROPRIEDADE) ////////////////////////////////////

    public void setIdFrente(Long codFrente) {
        FrenteDAO frenteDAO = new FrenteDAO();
        this.idFrente = frenteDAO.getFrente(codFrente).getIdFrente();
    }

    public boolean verFrente(Long codFrente){
        FrenteDAO frenteDAO = new FrenteDAO();
        return frenteDAO.verFrente(codFrente);
    }

    public void setIdPropriedade(Long idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public boolean verPropriedade(Long codPropriedade){
        PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
        return propriedadeDAO.verPropriedade(codPropriedade);
    }

    public PropriedadeBean getIdPropriedade(){
        PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
        return propriedadeDAO.getPropriedadeId(idPropriedade);
    }

    public PropriedadeBean getCodPropriedade(Long codPropriedade){
        PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
        return propriedadeDAO.getPropriedadeCod(codPropriedade);
    }

    public void setFrentePropriedade(){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setFrentePropriedade(idFrente, idPropriedade);
    }

    public String getMsgPropriedade(){
        String retorno = "";
        if((getConfig().getNroOSConfig() == 0L) && (getConfig().getIdPropriedadeConfig() == 0L)){
            retorno = "NÃO POSSUE SEÇÃO AINDA";
        }
        else if(getConfig().getNroOSConfig() == 0L){
            PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
            PropriedadeBean propriedadeBean = propriedadeDAO.getPropriedadeId(getConfig().getIdPropriedadeConfig());
            retorno = "SEÇÃO " + propriedadeBean.getCodPropriedade() + " - " + propriedadeBean.getDescrPropriedade();
        }
        else{
            OSDAO osDAO = new OSDAO();
            PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
            PropriedadeBean propriedadeBean = propriedadeDAO.getPropriedadeId(osDAO.getOS(getConfig().getNroOSConfig()).getIdProprAgr());
            retorno = "SEÇÃO " + propriedadeBean.getCodPropriedade() + " - " + propriedadeBean.getDescrPropriedade();
        }
        return retorno;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// INFORMATIVO ////////////////////////////////////////////

    public void setVerInforConfig(Long tipo){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setVerInforConfig(tipo);
    }

    public Long getVerRecInformativo(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getVerRecInformativo();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// ATUALIZAR APLIC /////////////////////////////////////////

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
            LogErroDAO.getInstance().insertLogErro(e);
        }
        return atualAplicBean;
    }

    public void verAtualAplic(String versaoAplic, TelaInicialActivity telaInicialActivity, String activity) {
        EquipDAO equipDAO = new EquipDAO();
        EquipBean equipBean = equipDAO.getEquip();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(equipBean.getNroEquip(), equipBean.getIdCheckList(), versaoAplic)\n" +
                "                , telaInicialActivity, progressDialog);", activity);
        VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(equipBean.getNroEquip(), equipBean.getIdCheckList(), versaoAplic)
                , telaInicialActivity, activity);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// ATUALIZAR DADOS ////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog, activity);", activity);
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog, activity);
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
        dadosArrayList = boletimMMFertDAO.boletimAllArrayList(dadosArrayList);
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
