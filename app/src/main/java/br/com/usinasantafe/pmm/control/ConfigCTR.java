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
import br.com.usinasantafe.pmm.model.dao.ConfigDAO;
import br.com.usinasantafe.pmm.model.dao.EquipDAO;
import br.com.usinasantafe.pmm.model.dao.FrenteDAO;
import br.com.usinasantafe.pmm.model.dao.ImpleMMDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.model.dao.OSDAO;
import br.com.usinasantafe.pmm.model.dao.PropriedadeDAO;
import br.com.usinasantafe.pmm.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.pmm.model.dao.RecolhFertDAO;
import br.com.usinasantafe.pmm.model.dao.RendMMDAO;
import br.com.usinasantafe.pmm.model.dao.RespItemCheckListDAO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;
import br.com.usinasantafe.pmm.util.Json;
import br.com.usinasantafe.pmm.util.VerifDadosServ;
import br.com.usinasantafe.pmm.view.MenuInicialActivity;

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

    public void verEquipConfig(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        EquipDAO equipDAO = new EquipDAO();
        LogProcessoDAO.getInstance().insert("equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog);", activity);
        equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog, activity);
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip();
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
    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// OS - ATIVIDADE /////////////////////////////////////////////

    public void clearOSAtiv(){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setOsConfig(0L);
        configDAO.setAtivConfig(0L);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// OS ////////////////////////////////////////////////////

    public void setOsConfig(Long nroOS){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setOsConfig(nroOS);
    }

    public boolean verROSAtiv(Long nroOS){
        OSDAO osDAO = new OSDAO();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        return (osDAO.verOS(nroOS) && atividadeDAO.verROSAtiv(nroOS));
    }

    public boolean verOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        return osDAO.verOS(nroOS);
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

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// ATIVIDADE //////////////////////////////////////////////

    public void setAtivConfig(Long idAtiv){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setAtivConfig(idAtiv);
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

    public boolean verPropriedade(Long idPropriedade){
        PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
        return propriedadeDAO.verPropriedade(idPropriedade);
    }

    public PropriedadeBean getPropriedade(){
        PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
        return propriedadeDAO.getPropriedade(idPropriedade);
    }

    public void setFrentePropriedade(){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setFrentePropriedade(idFrente, idPropriedade);
    }

    public String getMsgPropriedade(){
        String retorno = "";
        if((getConfig().getNroOSConfig() == 0L) && (getConfig().getIdPropriedadeConfig() == 0L)){
            retorno = "NÃO POSSUE FAZENDA AINDA";
        }
        else if(getConfig().getNroOSConfig() == 0L){
            PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
            PropriedadeBean propriedadeBean = propriedadeDAO.getPropriedade(getConfig().getIdPropriedadeConfig());
            retorno = "FAZENDA = " + propriedadeBean.getIdPropriedade() + " - " + propriedadeBean.getDescrPropriedade();
        }
        else{
            OSDAO osDAO = new OSDAO();
            PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
            PropriedadeBean propriedadeBean = propriedadeDAO.getPropriedade(osDAO.getOS(getConfig().getNroOSConfig()).getIdProprAgr());
            retorno = "FAZENDA = " + propriedadeBean.getIdPropriedade() + " - " + propriedadeBean.getDescrPropriedade();
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
            LogErroDAO.getInstance().insert(e);
        }
        return atualAplicBean;
    }

    public void verAtualAplic(String versaoAplic, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog, String activity) {
        EquipDAO equipDAO = new EquipDAO();
        EquipBean equipBean = equipDAO.getEquip();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        LogProcessoDAO.getInstance().insert("VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(equipBean.getNroEquip(), equipBean.getIdCheckList(), versaoAplic)\n" +
                "                , menuInicialActivity, progressDialog);", activity);
        VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(equipBean.getNroEquip(), equipBean.getIdCheckList(), versaoAplic)
                , menuInicialActivity, progressDialog, activity);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// ATUALIZAR DADOS ////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog, String activity){
        LogProcessoDAO.getInstance().insert("AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog, activity);", activity);
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
        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        RendMMDAO rendMMDAO = new RendMMDAO();
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        RespItemCheckListDAO respItemCheckListDAO = new RespItemCheckListDAO();
        dadosArrayList = boletimMMFertDAO.boletimAllArrayList(dadosArrayList);
        dadosArrayList = apontMMFertDAO.apontAllArrayList(dadosArrayList);
        dadosArrayList = impleMMDAO.impleAllArrayList(dadosArrayList);
        dadosArrayList = recolhFertDAO.recolAllArrayList(dadosArrayList);
        dadosArrayList = rendMMDAO.rendAllArrayList(dadosArrayList);
        dadosArrayList = cabecCheckListDAO.cabecCheckListAllArrayList(dadosArrayList);
        dadosArrayList = respItemCheckListDAO.respCheckListAllArrayList(dadosArrayList);
        return dadosArrayList;
    }

    public List<LogErroBean> logErroList(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.logErroBeanList();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////


}
