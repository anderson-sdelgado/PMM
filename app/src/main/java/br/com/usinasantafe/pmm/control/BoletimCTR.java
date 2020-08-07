package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.dao.ApontFertDAO;
import br.com.usinasantafe.pmm.model.dao.ApontMMDAO;
import br.com.usinasantafe.pmm.model.dao.AtividadeDAO;
import br.com.usinasantafe.pmm.model.dao.BoletimFertDAO;
import br.com.usinasantafe.pmm.model.dao.BoletimMMDAO;
import br.com.usinasantafe.pmm.model.dao.EquipSegDAO;
import br.com.usinasantafe.pmm.model.dao.OSDAO;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.pmm.model.dao.TurnoDAO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;

public class BoletimCTR {

    private BoletimMMBean boletimMMBean;
    private BoletimFertBean boletimFertBean;
    private OSDAO osDAO;
    private AtividadeDAO atividadeDAO;

    public BoletimCTR() {
        if (boletimMMBean == null)
            boletimMMBean = new BoletimMMBean();
        if (boletimFertBean == null)
            boletimFertBean = new BoletimFertBean();
    }

    //////////////// VERIFICAR BOLETIM ABERTO E SETAR O MESMO //////////////////////////////////////

    public boolean verBolAberto(){
        boolean ret = false;
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        if(boletimMMDAO.verBolAberto() || boletimFertDAO.verBolAberto()){
            ret = true;
            if(boletimMMDAO.verBolAberto()){
                boletimMMBean = getBolMMAberto();
            }
            if(boletimFertDAO.verBolAberto()){
                boletimFertBean = getBolFertAberto();
            }
        }
        return ret;
    }

    public BoletimMMBean getBolMMAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getBolMMAberto();
    }

    public BoletimFertBean getBolFertAberto(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.getBolFertAberto();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// SETAR CAMPOS ///////////////////////////////////////////////

    public void setFuncBol(Long matric){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            boletimMMBean.setMatricFuncBolMM(matric);
        }
        else{
            boletimFertBean.setMatricFuncBolFert(matric);
        }
    }

    public void setEquipBol(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            boletimMMBean.setIdEquipBolMM(configCTR.getEquip().getIdEquip());
        }
        else{
            boletimFertBean.setIdEquipBolFert(configCTR.getEquip().getIdEquip());
        }
    }

    public void setTurnoBol(Long idTurno){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            boletimMMBean.setIdTurnoBolMM(idTurno);
        }
        else{
            boletimFertBean.setIdTurnoBolFert(idTurno);
        }
    }

//    public void setAtivBol(Long ativ){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            boletimMMBean.setAtivPrincBolMM(ativ);
//            boletimMMBean.setStatusConBolMM(configCTR.getConfig().getStatusConConfig());
//        }
//        else{
//            boletimFertBean.setAtivPrincBolFert(ativ);
//            boletimFertBean.setStatusConBolFert(configCTR.getConfig().getStatusConConfig());
//        }
//    }

    public void setHodometroInicialBol(Double horimetroNum, Double longitude, Double latitude){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            boletimMMBean.setHodometroInicialBolMM(horimetroNum);
            boletimMMBean.setHodometroFinalBolMM(0D);
            boletimMMBean.setIdExtBolMM(0L);
            boletimMMBean.setLongitudeBolMM(longitude);
            boletimMMBean.setLatitudeBolMM(latitude);
        }
        else{
            boletimFertBean.setHodometroInicialBolFert(horimetroNum);
            boletimFertBean.setHodometroFinalBolFert(0D);
            boletimFertBean.setIdExtBolFert(0L);
            boletimFertBean.setLongitudeBolFert(longitude);
            boletimFertBean.setLongitudeBolFert(latitude);
        }
    }

    public void setHodometroFinalBol(Double horimetroNum){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            boletimMMBean.setHodometroFinalBolMM(horimetroNum);
        }
        else{
            boletimFertBean.setHodometroFinalBolFert(horimetroNum);
        }
    }

    public void setIdEquipBombaBol(Long idEquip){
        boletimFertBean.setIdEquipBombaBolFert(idEquip);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public Long getAtiv(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            return boletimMMBean.getAtivPrincBolMM();
        }
        else{
            return boletimFertBean.getAtivPrincBolFert();
        }
    }

    public Long getTurno(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            return boletimMMBean.getIdTurnoBolMM();
        }
        else{
            return boletimFertBean.getIdTurnoBolFert();
        }
    }

    public Long getFunc(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            return boletimMMBean.getMatricFuncBolMM();
        }
        else{
            return boletimFertBean.getMatricFuncBolFert();
        }
    }

    public Long getIdExtBol(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            return boletimMMBean.getIdExtBolMM();
        }
        else{
            return boletimFertBean.getIdExtBolFert();
        }
    }

    public Long getStatusConBol(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            return boletimMMBean.getStatusConBolMM();
        }
        else{
            return boletimFertBean.getStatusConBolFert();
        }
    }

    public Long getOS() {
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            return boletimMMBean.getOsBolMM();
        }
        else{
            return boletimFertBean.getOsBolFert();
        }
    }

    public Long getIdBol(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
            return boletimMMDAO.getIdBolAberto();
        }
        else{
            BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
            return boletimFertDAO.getIdBolAberto();
        }
    }

    public Double getLongitude(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            return boletimMMBean.getLongitudeBolMM();
        }
        else{
            return boletimFertBean.getLongitudeBolFert();
        }
    }

    public Double getLatitude(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            return boletimMMBean.getLatitudeBolMM();
        }
        else{
            return boletimFertBean.getLatitudeBolFert();
        }
    }

    public EquipSegBean getEquipSeg(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.getEquipSeg(nroEquip);
    }

    public List getTurnoList(){
        ConfigCTR configCTR = new ConfigCTR();
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurnoList(configCTR.getEquip().getCodTurno());
    }

    /////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// RENDIMENTO ///////////////////////////////////////

    public void insRendBD(Long nroOS){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.insRend(nroOS);
    }

    public boolean verRendMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.verRend();
    }

    public RendMMBean getRend(int pos){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getRend(pos);
    }

    public void atualRend(RendMMBean rendMMBean){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.atualRend(rendMMBean);
    }

    public int qtdeRend(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.qtdeRend();
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// IMPLEMENTO ///////////////////////////////////////////

    public boolean verImplemento(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verImple(nroEquip);
    }

    public void setImplemento(ImpleMMBean impleMMBean){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        equipSegDAO.setImplemento(impleMMBean);
    }

    public boolean verDuplicImpleMM(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verDuplicImple(nroEquip);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// TRANSBORDO //////////////////////////////////////////

    public boolean verMotoBomba(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verMotoBomba(nroEquip);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// LEIRA ///////////////////////////////////////////

    public void insMovLeira(Long idLeira, int tipo){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.insMovLeira(idLeira, Long.valueOf(tipo));
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// MOTOBOMBA ///////////////////////////////////////////

    public boolean verTransb(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verTransb(nroEquip);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// RECOLHIMENTO ////////////////////////////////////////

    public boolean verRecolh(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.verRecolh();
    }

    public RecolhFertBean getRecolh(int pos) {
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.getRecolh(pos);
    }

    public void atualRecolh(RecolhFertBean recolhFertBean){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        boletimFertDAO.atualRecolh(recolhFertBean);
    }

    public int qtdeRecolh(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.qtdeRecolh();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    //////////////////// RETORNO DE LISTA DAS ATIVIDADES DA OS /////////////////////////////

    public ArrayList getAtivArrayList(Long nroOS){
        ConfigCTR configCTR = new ConfigCTR();
        atividadeDAO = new AtividadeDAO();
        return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), nroOS);
    }

    //////////////////////////////////////////////////////////////////////////////////////

    //////////////////// RETORNO DA PARADA DE CHECKLIST /////////////////////////////////////

    public Long getIdParadaCheckList(){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        return rFuncaoAtivParDAO.idParadaCheckList();
    }

    //////////////////////////////////////////////////////////////////////////////////////

    //////////////// RETORNO DE LISTA DAS FUNÇÕES DA ATIVIDADE /////////////////////////////

    /////////// ATIVIDADE /////////

    public List getFuncaoAtividadeList(){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return rFuncaoAtivParDAO.getListFuncaoAtividade(configCTR.getConfig().getAtivConfig());
    }

    /////////// PARADA /////////

    public List getFuncaoParadaList(Long idParada){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        return rFuncaoAtivParDAO.getListFuncaoParada(idParada);
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    /////////////////// ATUALIZAR QTDE DE APONTAMENTO DO BOLETIM ///////////////////////////

    public void atualQtdeApontBol(){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.getEquip().getTipo() == 1) {
            BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
            boletimMMDAO.atualQtdeApontBol();
        }
        else{
            BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
            boletimFertDAO.atualQtdeApontBol();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR DADOS MOTOMEC ////////////////////////////////////////

    ///////////// SALVAR DADOS ///////////////

    public void salvarBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        boletimMMBean.setOsBolMM(configBean.getOsConfig());
        boletimMMBean.setAtivPrincBolMM(configBean.getAtivConfig());
        boletimMMBean.setStatusConBolMM(configBean.getStatusConConfig());
        boletimMMDAO.salvarBolAberto(boletimMMBean);
    }

    public void salvarBolFechadoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolFechado(boletimMMBean);
    }

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public boolean verEnvioBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolAbertoSemEnvioList().size() > 0;
    }

    public boolean verEnvioBolFechMM() {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolFechadoList().size() > 0;
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.dadosEnvioBolAberto(getBolMMAberto());
    }

    public String dadosEnvioBolFechadoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.dadosEnvioBolFechado();
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updBolAbertoMM(String retorno){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.updateBolAberto(retorno);
    }

    public void delBolFechadoMM(String retorno) {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.deleteBolFechado(retorno);
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR DADOS FERTIRRIGAÇÃO ////////////////////////////////////

    ///////////// SALVAR DADOS ///////////////

    public void salvarBolAbertoFert(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        boletimFertBean.setOsBolFert(configBean.getOsConfig());
        boletimFertBean.setAtivPrincBolFert(configBean.getAtivConfig());
        boletimFertBean.setStatusConBolFert(configBean.getStatusConConfig());
        boletimFertDAO.salvarBolAberto(boletimFertBean);
    }

    public void salvarBolFechadoFert(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        boletimFertDAO.salvarBolFechado(boletimFertBean);
    }

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public boolean verEnvioBolAbertoFert(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.bolAbertoSemEnvioList().size() > 0;
    }

    public boolean verEnvioBolFechFert() {
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.bolFechadoList().size() > 0;
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioBolAbertoFert(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.dadosEnvioBolAberto(getBolFertAberto());
    }

    public String dadosEnvioBolFechadoFert(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.dadosEnvioBolFechado();
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updBolAbertoFert(String retorno){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        boletimFertDAO.updateBolAberto(retorno);
    }

    public void delBolFechadoFert(String retorno) {
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        boletimFertDAO.deleteBolFechado(retorno);
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////////

    public void atualDadosOperador(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList operadorArrayList = new ArrayList();
        operadorArrayList.add("FuncBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, operadorArrayList);
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList turnoArrayList = new ArrayList();
        turnoArrayList.add("TurnoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, turnoArrayList);
    }

    public void atualDadosEquipSeg(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList equipSegArrayList = new ArrayList();
        equipSegArrayList.add("EquipSegBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, equipSegArrayList);
    }

    public void atualDadosParada(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList paradaArrayList = new ArrayList();
        paradaArrayList.add("RAtivParadaBean");
        paradaArrayList.add("ParadaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, paradaArrayList);
    }

    public void atualDadosBocal(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList bocalArrayList = new ArrayList();
        bocalArrayList.add("BocalBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, bocalArrayList);
    }

    public void atualDadosPressao(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList bocalArrayList = new ArrayList();
        bocalArrayList.add("PressaoBocalBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, bocalArrayList);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog);
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        atividadeDAO = new AtividadeDAO();
        atividadeDAO.verAtiv(dado  + "_" + configCTR.getEquip().getNroEquip(), telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
