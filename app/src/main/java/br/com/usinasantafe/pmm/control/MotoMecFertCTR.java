package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.PressaoBocalBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.model.dao.ApontMMFertDAO;
import br.com.usinasantafe.pmm.model.dao.AtividadeDAO;
import br.com.usinasantafe.pmm.model.dao.BocalDAO;
import br.com.usinasantafe.pmm.model.dao.BoletimMMFertDAO;
import br.com.usinasantafe.pmm.model.dao.CarretaDAO;
import br.com.usinasantafe.pmm.model.dao.EquipSegDAO;
import br.com.usinasantafe.pmm.model.dao.FuncDAO;
import br.com.usinasantafe.pmm.model.dao.ImpleMMDAO;
import br.com.usinasantafe.pmm.model.dao.MotoMecDAO;
import br.com.usinasantafe.pmm.model.dao.OSDAO;
import br.com.usinasantafe.pmm.model.dao.ParadaDAO;
import br.com.usinasantafe.pmm.model.dao.PressaoFertDAO;
import br.com.usinasantafe.pmm.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.pmm.model.dao.RecolhFertDAO;
import br.com.usinasantafe.pmm.model.dao.RendMMDAO;
import br.com.usinasantafe.pmm.model.dao.TurnoDAO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;

public class MotoMecFertCTR {

    private BoletimMMFertDAO boletimMMFertDAO;

    public MotoMecFertCTR() {
    }

    public BoletimMMFertDAO getBoletimMMDAO(){
        if (boletimMMFertDAO == null)
            boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO;
    }

    public boolean verBolAberto(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.verBolMMAberto();
    }

    public BoletimMMFertBean getBoletimMMFertAberto(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.getBolMMFertAberto();
    }

    public void atualQtdeApontBol(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        boletimMMFertDAO.atualQtdeApontBol();
    }

    public void salvarBolMMFertAberto(){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        boletimMMFertDAO.salvarBolMMAberto(configCTR.getEquip().getTipoEquip(), configBean.getOsConfig()
                                            , configBean.getAtivConfig(), configBean.getStatusConConfig());
    }

    public void salvarBolMMFertFechado(){
        if (boletimMMFertDAO == null) {
            boletimMMFertDAO = new BoletimMMFertDAO();
        }
        boletimMMFertDAO.salvarBolMMFechado();
    }

    public Boolean verEnvioApont() {
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.apontEnvioList().size() > 0;
    }

    public boolean verEnvioBolFech() {
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.verBolMMFechado();
    }

    public String dadosEnvioBolAbertoMMFert(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        String dadosEnvioBoletim = boletimMMFertDAO.dadosEnvioBolAberto();

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        String dadosEnvioApont = apontMMFertDAO.dadosEnvioApontMM(apontMMFertDAO.apontEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolMMFertAbertoList())));

        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        String dadosEnvioApontImpl = impleMMDAO.dadosEnvioApontImpleMM(impleMMDAO.apontImpleEnvioList(apontMMFertDAO.idApontArrayList(apontMMFertDAO.apontEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolMMFertAbertoList())))));

        return dadosEnvioBoletim + "_" + dadosEnvioApont + "|" + dadosEnvioApontImpl;
    }

    public String dadosEnvioBolFechadoMMFert(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        String dadosEnvioBoletim = boletimMMFertDAO.dadosEnvioBolFechado();

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        String dadosEnvioApont = apontMMFertDAO.dadosEnvioApontMM(apontMMFertDAO.apontEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolMMFertFechadoList())));

        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        String dadosEnvioApontImpl = impleMMDAO.dadosEnvioApontImpleMM(impleMMDAO.apontImpleEnvioList(apontMMFertDAO.idApontArrayList(apontMMFertDAO.apontEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolMMFertFechadoList())))));

        RendMMDAO rendMMDAO = new RendMMDAO();
        String dadosEnvioRend = rendMMDAO.dadosEnvioRendMM(rendMMDAO.rendEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolMMFertFechadoList())));

        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        String dadosEnvioRecolh = recolhFertDAO.dadosEnvioRecolh(recolhFertDAO.recolhEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolMMFertFechadoList())));

        return dadosEnvioBoletim + "_" + dadosEnvioApont + "|" + dadosEnvioApontImpl + "#" + dadosEnvioRend + "?" + dadosEnvioRecolh;
    }

    public void updateBolAberto(String retorno){

        int pos1 = retorno.indexOf("_") + 1;
        int pos2 = retorno.indexOf("|") + 1;
        String objPrinc = retorno.substring(pos1, pos2);
        String objSeg = retorno.substring(pos2);

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        boletimMMFertDAO.updateBolAberto(objPrinc);

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(objSeg);
        apontMMFertDAO.updApont(idApontArrayList);

        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        impleMMDAO.deleteImple(idApontArrayList);

    }

    public void deleteBolFechado(String retorno){

        int pos1 = retorno.indexOf("_") + 1;
        String objPrinc = retorno.substring(pos1);

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ArrayList<BoletimMMFertBean> boletimArrayList = boletimMMFertDAO.boletimArrayList(objPrinc);

        for(BoletimMMFertBean boletimMMFertBean : boletimArrayList){

            ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
            List<ApontMMFertBean> apontMMFertList = apontMMFertDAO.apontMMFertList(boletimMMFertBean.getIdBolMMFert());

            if(apontMMFertList.size() == boletimMMFertBean.getQtdeApontBolMMFert()){

                ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(apontMMFertList);
                apontMMFertDAO.deleteApont(idApontArrayList);

                ImpleMMDAO impleMMDAO = new ImpleMMDAO();
                impleMMDAO.deleteImple(idApontArrayList);

                RendMMDAO rendMMDAO = new RendMMDAO();
                rendMMDAO.deleteRend(boletimMMFertBean.getIdBolMMFert());

                RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
                recolhFertDAO.deleteRecolh(boletimMMFertBean.getIdBolMMFert());

                boletimMMFertDAO.deleteBol(boletimMMFertBean.getIdBolMMFert());

            }

        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// ATIVIDADES ////////////////////////////////////////////

    public ArrayList getAtivArrayList(Long nroOS){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), nroOS);
    }

    public List<RFuncaoAtivParBean> getFuncaoAtividadeList(){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return rFuncaoAtivParDAO.getListFuncaoAtividade(configCTR.getConfig().getAtivConfig());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////// PARADA ///////////////////////////////////////////

    public List<RFuncaoAtivParBean> getFuncaoParadaList(Long idParada){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        return rFuncaoAtivParDAO.getListFuncaoParada(idParada);
    }


    public List<ApontMMFertBean> apontList(){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.apontMMFertList(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public List getListParada(){
        ConfigCTR configCTR = new ConfigCTR();
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getListParada(configCTR.getConfig().getAtivConfig());
    }

    public ParadaBean getParadaBean(String paradaString){
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getParadaBean(paradaString);
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////// RENDIMENTO //////////////////////////////////////////

    public void insRendBD(Long nroOS){
        RendMMDAO rendMMDAO = new RendMMDAO();
        rendMMDAO.insRend(getBoletimMMFertAberto().getIdBolMMFert(), nroOS);
    }

    public boolean verRendMM(){
        RendMMDAO rendMMDAO = new RendMMDAO();
        return rendMMDAO.verRend(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public RendMMBean getRend(int pos){
        RendMMDAO rendMMDAO = new RendMMDAO();
        return rendMMDAO.getRend(getBoletimMMFertAberto().getIdBolMMFert(), pos);
    }

    public void atualRend(RendMMBean rendMMBean){
        RendMMDAO rendMMDAO = new RendMMDAO();
        rendMMDAO.atualRend(rendMMBean);
    }

    public int qtdeRend(){
        RendMMDAO rendMMDAO = new RendMMDAO();
        return rendMMDAO.qtdeRend(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public List<RendMMBean> rendList(){
        RendMMDAO rendMMDAO = new RendMMDAO();
        return rendMMDAO.rendList(getBoletimMMFertAberto().getIdBolMMFert());
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// IMPLEMENTO ///////////////////////////////////////////

    public boolean verImplemento(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verImple(nroEquip);
    }

    public void setImplemento(Long pos, Long imple){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        equipSegDAO.setImplemento(pos, imple);
    }

    public boolean verDuplicImpleMM(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verDuplicImple(nroEquip);
    }

    public void impleMMDelAll(){
        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        impleMMDAO.impleMMDelAll();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// TRANSBORDO //////////////////////////////////////////

    public boolean verMotoBomba(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verMotoBomba(nroEquip);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// MOTOBOMBA ///////////////////////////////////////////

    public boolean verTransb(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verTransb(nroEquip);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// RECOLHIMENTO ////////////////////////////////////////

    public void insRecolh(){
        ConfigCTR configCTR = new ConfigCTR();
        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        recolhFertDAO.insRecolh(getBoletimMMFertAberto().getIdBolMMFert(), configCTR.getConfig().getOsConfig());
    }

    public boolean verRecolh(){
        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        return recolhFertDAO.verRecolh(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public RecolhFertBean getRecolh(int pos) {
        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        return recolhFertDAO.getRecolh(getBoletimMMFertAberto().getIdBolMMFert(), pos);
    }

    public void atualRecolh(RecolhFertBean recolhFertBean){
        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        recolhFertDAO.atualRecolh(recolhFertBean);
    }

    public int qtdeRecolh(){
        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        return recolhFertDAO.qtdeRecolh(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public List<RecolhFertBean> recolhList(){
        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        return recolhFertDAO.recolhList(getBoletimMMFertAberto().getIdBolMMFert());
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// FUNCIONARIOS ///////////////////////////////////////////////

    public boolean hasElemFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.hasElements();
    }

    public FuncBean getMatricFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(getBoletimMMFertAberto().getMatricFuncBolMMFert());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// EQUIP SEG ///////////////////////////////////////////////

    public EquipSegBean getEquipSeg(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.getEquipSeg(nroEquip);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// TURNO ///////////////////////////////////////////////

    public List<TurnoBean> getTurnoCodList(){
        ConfigCTR configCTR = new ConfigCTR();
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurnoCodList(configCTR.getEquip().getCodTurno());
    }

    public TurnoBean getTurnoId(Long idTurno){
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurnoId(idTurno);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////// BOCAL /////////////////////////////////////////

    public BocalBean getBocalBean(Long idBocal){
        BocalDAO bocalDAO = new BocalDAO();
        return bocalDAO.getBocal(idBocal);
    }

    //////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////// PRESSAO /////////////////////////////////////////

    public List<PressaoBocalBean> pressaoBocalList(){
        ConfigCTR configCTR = new ConfigCTR();
        PressaoFertDAO pressaoFertDAO = new PressaoFertDAO();
        return pressaoFertDAO.pressaoBocalList(configCTR.getConfig().getBocalConfig());
    }

    //////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////// VELOCIDADE /////////////////////////////////////////

    public ArrayList<String> velocArrayList() {
        ConfigCTR configCTR = new ConfigCTR();
        PressaoFertDAO pressaoFertDAO = new PressaoFertDAO();
        return pressaoFertDAO.velocArrayList(configCTR.getConfig().getBocalConfig(), configCTR.getConfig().getPressaoConfig());
    }

    //////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// CARRETA /////////////////////////////////////////////

    public void delCarreta(){
        CarretaDAO carretaDAO = new CarretaDAO();
        carretaDAO.delCarreta();
    }

    public int verCarr(Long nroCarreta){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.verCarr(nroCarreta);
    }

    public void insCarreta(Long nroCarreta){
        CarretaDAO carretaDAO = new CarretaDAO();
        carretaDAO.insCarreta(nroCarreta);
    }

    public boolean hasElemCarreta(){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.hasElements();
    }

    public int qtdeCarreta(){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.getQtdeCarreta();
    }

    public String getDescrCarreta(){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.getDescrCarreta();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////// MOTOMEC ////////////////////////////////////////////

    public List<MotoMecBean> paradaList() {
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.paradaList();
    }

    public List<MotoMecBean> motoMecList() {
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.motoMecList();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

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
        OSDAO osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog);
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        atividadeDAO.verAtiv(dado  + "_" + configCTR.getEquip().getNroEquip(), telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////APONTAMENTO /////////////////////////////

    public void inserirParadaCheckList(){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        salvarApont(rFuncaoAtivParDAO.idParadaCheckList(), 0L);
    }

    public void inserirApontTransb(Long idTransb){
        salvarApont(0L, idTransb);
    }

    public void inserirParadaImplemento(){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        salvarApont(rFuncaoAtivParDAO.idParadaImplemento(), 0L);
    }

    private void salvarApont(Long idParada, Long idTransb){

        String dthr = Tempo.getInstance().dataComHora();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), idParada
                , boletimMMFertBean.getOsBolMMFert(), boletimMMFertBean.getAtivPrincBolMMFert()
                , boletimMMFertBean.getLatitudeBolMMFert(), boletimMMFertBean.getLongitudeBolMMFert()
                , boletimMMFertBean.getStatusConBolMMFert(), dthr, idTransb
                , configCTR.getConfig().getPressaoConfig(), configCTR.getConfig().getVelocConfig()
                , configCTR.getConfig().getBocalConfig(), 1);

        salvarAltCompApont(dthr);

    }

    public void salvarApont(Long idParada, Long idTransb, Double latitude, Double longitude){

        String dthr = Tempo.getInstance().dataComHora();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), idParada
                , configCTR.getConfig().getOsConfig(), configCTR.getConfig().getAtivConfig()
                , latitude, longitude, boletimMMFertBean.getStatusConBolMMFert(), dthr, idTransb
                , configCTR.getConfig().getPressaoConfig(), configCTR.getConfig().getVelocConfig()
                , configCTR.getConfig().getBocalConfig(), 2);

        salvarAltCompApont(dthr);

    }

    public void salvarAltCompApont(String dthr){

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        impleMMDAO.salvarApontImple(apontMMFertDAO.getApont(dthr).getIdApontMMFert(), dthr);

        atualQtdeApontBol();

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setDtUltApontConfig(dthr);

    }


    ///////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO APONT ///////////////////////////////////////////

    public boolean verifBackupApont(Long idParada) {
        boolean v = false;
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertDAO boletimMMDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.verifBackupApont(boletimMMDAO.getBolMMFertAberto().getIdBolMMFert(), configCTR.getConfig().getOsConfig()
                                                , configCTR.getConfig().getAtivConfig(), idParada);
    }

    public boolean verifBackupApontTransb(Long idParada, Long idTransb) {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertDAO boletimMMDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        return apontMMDAO.verifBackupApontTransb(boletimMMDAO.getBolMMFertAberto().getIdBolMMFert(), configCTR.getConfig().getOsConfig()
                                                    , configCTR.getConfig().getAtivConfig(), idParada, idTransb);
    }

    public int verTrocaTransb(){
        ConfigCTR configCTR = new ConfigCTR();
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return apontMMDAO.verTransbordo(configCTR.getConfig().getDtUltApontConfig(), boletimMMFertDAO.getBolMMFertAberto().getIdBolMMFert());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

}
