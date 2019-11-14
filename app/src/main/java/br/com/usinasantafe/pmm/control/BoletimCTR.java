package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bean.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.dao.ApontFertDAO;
import br.com.usinasantafe.pmm.dao.ApontMMDAO;
import br.com.usinasantafe.pmm.dao.AtividadeDAO;
import br.com.usinasantafe.pmm.dao.BoletimFertDAO;
import br.com.usinasantafe.pmm.dao.BoletimMMDAO;
import br.com.usinasantafe.pmm.dao.EquipDAO;
import br.com.usinasantafe.pmm.dao.EquipSegDAO;
import br.com.usinasantafe.pmm.dao.OSDAO;
import br.com.usinasantafe.pmm.bean.estaticas.EquipTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.ImpleMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.RecolhFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.RendMMTO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;

public class BoletimCTR {

    private BoletimMMTO boletimMMTO;
    private BoletimFertTO boletimFertTO;
    private OSDAO osDAO;
    private AtividadeDAO atividadeDAO;
    private int tipoEquip;

    public BoletimCTR() {
        if (boletimMMTO == null)
            boletimMMTO = new BoletimMMTO();
        if (boletimFertTO == null)
            boletimFertTO = new BoletimFertTO();
    }

    //////////////// VERIFICAR BOLETIM ABERTO E SETAR O MESMO //////////////////////////////////////

    public boolean verBolABerto(){
        boolean ret = false;
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        if(boletimMMDAO.verBolAberto() || boletimFertDAO.verBolAberto()){
            ret = true;
            if(boletimMMDAO.verBolAberto()){
                boletimMMTO = getBolMMAberto();
            }
            if(boletimFertDAO.verBolAberto()){
                boletimFertTO = getBolFertAberto();
            }
        }
        return ret;
    }

    public BoletimMMTO getBolMMAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getBolMMAberto();
    }

    public BoletimFertTO getBolFertAberto(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.getBolFertAberto();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// SETAR CAMPOS ///////////////////////////////////////////////

    public void setTipoEquip(){
        EquipDAO equipDAO = new EquipDAO();
        EquipTO equipTO = equipDAO.getEquip();
        if(equipTO.getTipo() == 1) {
            tipoEquip = 1;
        }
        else{
            tipoEquip = 2;
        }
    }

    public void setFuncBol(Long matric){
        if(tipoEquip == 1) {
            boletimMMTO.setMatricFuncBolMM(matric);
        }
        else{
            boletimFertTO.setMatricFuncBolFert(matric);
        }
    }

    public void setEquipBol(){
        EquipDAO equipDAO = new EquipDAO();
        EquipTO equipTO = equipDAO.getEquip();
        if(equipTO.getTipo() == 1) {
            boletimMMTO.setIdEquipBolMM(equipTO.getIdEquip());
        }
        else{
            boletimFertTO.setIdEquipBolFert(equipTO.getIdEquip());
        }
    }

    public void setTurnoBol(Long idTurno){
        if(tipoEquip == 1) {
            boletimMMTO.setIdTurnoBolMM(idTurno);
        }
        else{
            boletimFertTO.setIdTurnoBolFert(idTurno);
        }
    }

    public void setOSBol(Long os){
        if(tipoEquip == 1) {
            boletimMMTO.setOsBolMM(os);
        }
        else{
            boletimFertTO.setOsBolFert(os);
        }
    }

    public void setAtivBol(Long ativ){
        ConfigCTR configCTR = new ConfigCTR();
        if(tipoEquip == 1) {
            boletimMMTO.setAtivPrincBolMM(ativ);
            boletimMMTO.setStatusConBolMM(configCTR.getConfig().getStatusConConfig());
        }
        else{
            boletimFertTO.setAtivPrincBolFert(ativ);
            boletimFertTO.setStatusConBolFert(configCTR.getConfig().getStatusConConfig());
        }
    }


    public void setHodometroInicialBol(Double horimetroNum, Double longitude, Double latitude){
        if(tipoEquip == 1) {
            boletimMMTO.setHodometroInicialBolMM(horimetroNum);
            boletimMMTO.setHodometroFinalBolMM(0D);
            boletimMMTO.setIdExtBolMM(0L);
            boletimMMTO.setLongitudeBolMM(longitude);
            boletimMMTO.setLatitudeBolMM(latitude);
        }
        else{
            boletimFertTO.setHodometroInicialBolFert(horimetroNum);
            boletimFertTO.setHodometroFinalBolFert(0D);
            boletimFertTO.setIdExtBolFert(0L);
            boletimFertTO.setLongitudeBolFert(longitude);
            boletimFertTO.setLongitudeBolFert(latitude);
        }
    }

    public void setHodometroFinalBol(Double horimetroNum){
        if(tipoEquip == 1) {
            boletimMMTO.setHodometroFinalBolMM(horimetroNum);
        }
        else{
            boletimFertTO.setHodometroFinalBolFert(horimetroNum);
        }
    }

    public void setIdEquipBombaBol(Long idEquip){
        boletimFertTO.setIdEquipBombaBolFert(idEquip);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public int getTipoEquip() {
        return tipoEquip;
    }

    public Long getAtiv(){
        if(tipoEquip == 1) {
            return boletimMMTO.getAtivPrincBolMM();
        }
        else{
            return boletimFertTO.getAtivPrincBolFert();
        }
    }

    public Long getTurno(){
        if(tipoEquip == 1) {
            return boletimMMTO.getIdTurnoBolMM();
        }
        else{
            return boletimFertTO.getIdTurnoBolFert();
        }
    }

    public Long getFunc(){
        if(tipoEquip == 1) {
            return boletimMMTO.getMatricFuncBolMM();
        }
        else{
            return boletimFertTO.getMatricFuncBolFert();
        }
    }

    public Long getIdExtBol(){
        if(tipoEquip == 1) {
            return boletimMMTO.getIdExtBolMM();
        }
        else{
            return boletimFertTO.getIdExtBolFert();
        }
    }

    public Long getStatusConBol(){
        if(tipoEquip == 1) {
            return boletimMMTO.getStatusConBolMM();
        }
        else{
            return boletimFertTO.getStatusConBolFert();
        }
    }

    public Long getEquipBol(){
        if(tipoEquip == 1) {
            return boletimMMTO.getIdEquipBolMM();
        }
        else{
            return boletimFertTO.getIdEquipBolFert();
        }
    }

    public Long getOS() {
        if(tipoEquip == 1) {
            return boletimMMTO.getOsBolMM();
        }
        else{
            return boletimFertTO.getOsBolFert();
        }
    }

    public Long getIdBol(){
        if(tipoEquip == 1) {
            BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
            return boletimMMDAO.getIdBolAberto();
        }
        else{
            BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
            return boletimFertDAO.getIdBolAberto();
        }
    }

    public Double getLongitude(){
        if(tipoEquip == 1) {
            return boletimMMTO.getLongitudeBolMM();
        }
        else{
            return boletimFertTO.getLongitudeBolFert();
        }
    }

    public Double getLatitude(){
        if(tipoEquip == 1) {
            return boletimMMTO.getLatitudeBolMM();
        }
        else{
            return boletimFertTO.getLatitudeBolFert();
        }
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

    public RendMMTO getRend(int pos){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getRend(pos);
    }

    public void atualRend(RendMMTO rendMMTO){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.atualRend(rendMMTO);
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

    public void setImplemento(ImpleMMTO impleMMTO){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        equipSegDAO.setImplemento(impleMMTO);
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

    public RecolhFertTO getRecolh(int pos) {
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.getRecolh(pos);
    }

    public void atualRecolh(RecolhFertTO recolhFertTO){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        boletimFertDAO.atualRecolh(recolhFertTO);
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
        atividadeDAO = new AtividadeDAO();
        return atividadeDAO.idParadaCheckList();
    }

    //////////////////////////////////////////////////////////////////////////////////////

    //////////////// RETORNO DE LISTA DAS FUNÇÕES DA ATIVIDADE /////////////////////////////

    /////////// ATIVIDADE /////////

    public List getFuncaoAtividadeList(Long idAtiv){
        atividadeDAO = new AtividadeDAO();
        return atividadeDAO.getListFuncaoAtividade(idAtiv);
    }

    /////////// PARADA /////////

    public List getFuncaoParadaList(Long idParada){
        atividadeDAO = new AtividadeDAO();
        return atividadeDAO.getListFuncaoParada(idParada);
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    /////////////////// ATUALIZAR QTDE DE APONTAMENTO DO BOLETIM ///////////////////////////

    public void atualQtdeApontBol(){
        EquipDAO equipDAO = new EquipDAO();
        EquipTO equipTO = equipDAO.getEquip();
        if(equipTO.getTipo() == 1) {
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
        boletimMMDAO.salvarBolAberto(boletimMMTO);
    }

    public void salvarBolFechadoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolFechado(boletimMMTO);
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
        boletimFertDAO.salvarBolAberto(boletimFertTO);
    }

    public void salvarBolFechadoFert(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        boletimFertDAO.salvarBolFechado(boletimFertTO);
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

    public Long ultAtivBolMenu(){
        if(tipoEquip == 1) {
            ApontMMDAO apontMMDAO = new ApontMMDAO();
            List apontList = apontMMDAO.getListAllApont(getIdBol());
            if (apontList.size() == 0) {
                return boletimMMTO.getAtivPrincBolMM();
            } else {
                ApontMMTO apontTO = (ApontMMTO) apontList.get(apontList.size() - 1);
                return apontTO.getAtivApontMM();
            }
        }
        else{
            ApontFertDAO apontFertDAO = new ApontFertDAO();
            List apontList = apontFertDAO.getListAllApont(getIdBol());
            if (apontList.size() == 0) {
                return boletimFertTO.getAtivPrincBolFert();
            } else {
                ApontFertTO apontTO = (ApontFertTO) apontList.get(apontList.size() - 1);
                return apontTO.getAtivApontFert();
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////////

    public void atualDadosOperador(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList operadorArrayList = new ArrayList();
        operadorArrayList.add("FuncionarioTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, operadorArrayList);
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList turnoArrayList = new ArrayList();
        turnoArrayList.add("TurnoTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, turnoArrayList);
    }

    public void atualDadosEquipSeg(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList equipSegArrayList = new ArrayList();
        equipSegArrayList.add("EquipSegTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, equipSegArrayList);
    }

    public void atualDadosParada(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList paradaArrayList = new ArrayList();
        paradaArrayList.add("RAtivParadaTO");
        paradaArrayList.add("ParadaTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, paradaArrayList);
    }

    public void atualDadosBocal(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList bocalArrayList = new ArrayList();
        bocalArrayList.add("BocalTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, bocalArrayList);
    }

    public void atualDadosPressao(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList bocalArrayList = new ArrayList();
        bocalArrayList.add("PressaoBocalTO");
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
