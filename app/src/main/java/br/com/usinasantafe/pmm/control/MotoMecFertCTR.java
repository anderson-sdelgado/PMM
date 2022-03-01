package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
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
import br.com.usinasantafe.pmm.model.dao.LeiraDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.model.dao.MotoMecDAO;
import br.com.usinasantafe.pmm.model.dao.OSDAO;
import br.com.usinasantafe.pmm.model.dao.ParadaDAO;
import br.com.usinasantafe.pmm.model.dao.PressaoFertDAO;
import br.com.usinasantafe.pmm.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.pmm.model.dao.RecolhFertDAO;
import br.com.usinasantafe.pmm.model.dao.RendMMDAO;
import br.com.usinasantafe.pmm.model.dao.TurnoDAO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;

public class MotoMecFertCTR {

    private BoletimMMFertDAO boletimMMFertDAO;
    private MotoMecBean motoMecBean;
    private Long contImplemento;
    private int contRend;
    private int posRend;
    private int contRecolh;
    private int posRecolh;

    public MotoMecFertCTR() {
    }

    public BoletimMMFertDAO getBoletimMMFertDAO(){
        if (boletimMMFertDAO == null)
            boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO;
    }

    public boolean verBolAberto(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.verBolAbertoMMFert();
    }

    public BoletimMMFertBean getBoletimMMFertAberto(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.getBolAbertoMMFert();
    }

    public void salvarBolMMFertAberto(String activity){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        LogProcessoDAO.getInstance().insertLogProcesso("boletimMMFertDAO.salvarBolAbertoMMFert(configCTR.getEquip().getTipoEquip(), configBean.getNroOSConfig()\n" +
                "                                            , configBean.getIdAtivConfig(), configBean.getStatusConConfig(), activity);", activity);
        boletimMMFertDAO.salvarBolAbertoMMFert(configCTR.getEquip().getTipoEquip(), configBean.getNroOSConfig()
                                            , configBean.getIdAtivConfig(), configBean.getStatusConConfig(), activity);
    }

    public void salvarBolMMFertFechado(String activity){

        if (boletimMMFertDAO == null) {
            boletimMMFertDAO = new BoletimMMFertDAO();
        }

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();

        ConfigCTR configCTR = new ConfigCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("if (boletimMMFertDAO == null) {\n" +
                "            boletimMMFertDAO = new BoletimMMFertDAO();}\n" +
                "        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();\n" +
                "        ConfigCTR configCTR = new ConfigCTR();\n" +
                "", activity);
        configCTR.setUltParadaBolConfig(apontMMFertDAO.getUltApont(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert()).getParadaApontMMFert());

        LogProcessoDAO.getInstance().insertLogProcesso("boletimMMFertDAO.salvarBolFechadoMMFert();", activity);
        boletimMMFertDAO.salvarBolFechadoMMFert(activity);

    }

    public Boolean verEnvioApont() {
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.apontEnvioList().size() > 0;
    }

    public Boolean verEnvioMovLeira() {
        LeiraDAO leiraDAO = new LeiraDAO();
        return leiraDAO.movLeiraEnvioList().size() > 0;
    }

    public boolean verEnvioBolFech() {
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.verBolFechadoMMFert();
    }

    public String dadosEnvioBolAbertoMMFert(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        String dadosEnvioBoletim = boletimMMFertDAO.dadosEnvioBolMMFertAberto();

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        String dadosEnvioApont = apontMMFertDAO.dadosEnvioApont(apontMMFertDAO.apontEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolAbertoMMFertList())));

        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        String dadosEnvioApontImpl = impleMMDAO.dadosEnvioApontImpleMM(impleMMDAO.apontImpleEnvioList(apontMMFertDAO.idApontArrayList(apontMMFertDAO.apontEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolAbertoMMFertList())))));

        LeiraDAO leiraDAO = new LeiraDAO();
        String dadosEnvioMovLeira = leiraDAO.dadosEnvioMovLeira(leiraDAO.movLeiraEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolAbertoMMFertList())));

        return dadosEnvioBoletim + "_" + dadosEnvioApont + "|" + dadosEnvioApontImpl + "#" + dadosEnvioMovLeira;
    }

    public String dadosEnvioBolFechadoMMFert(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        String dadosEnvioBoletim = boletimMMFertDAO.dadosEnvioBolMMFertFechado();

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        String dadosEnvioApont = apontMMFertDAO.dadosEnvioApont(apontMMFertDAO.apontEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolFechadoMMFertList())));

        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        String dadosEnvioApontImpl = impleMMDAO.dadosEnvioApontImpleMM(impleMMDAO.apontImpleEnvioList(apontMMFertDAO.idApontArrayList(apontMMFertDAO.apontEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolFechadoMMFertList())))));

        LeiraDAO leiraDAO = new LeiraDAO();
        String dadosEnvioMovLeira = leiraDAO.dadosEnvioMovLeira(leiraDAO.movLeiraEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolAbertoMMFertList())));

        RendMMDAO rendMMDAO = new RendMMDAO();
        String dadosEnvioRend = rendMMDAO.dadosEnvioRendMM(rendMMDAO.rendEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolFechadoMMFertList())));

        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        String dadosEnvioRecolh = recolhFertDAO.dadosEnvioRecolh(recolhFertDAO.recolhEnvioList(boletimMMFertDAO.idBolArrayList(boletimMMFertDAO.bolFechadoMMFertList())));

        return dadosEnvioBoletim + "_" + dadosEnvioApont + "|" + dadosEnvioApontImpl + "#" + dadosEnvioMovLeira + "?" + dadosEnvioRend + "=" + dadosEnvioRecolh;
    }

    public void updBolAberto(String retorno, String activity){

        try {

            int pos1 = retorno.indexOf("_") + 1;
            int pos2 = retorno.indexOf("|") + 1;
            int pos3 = retorno.indexOf("#") + 1;

            String objPrinc = retorno.substring(pos1, pos2);
            String objSeg = retorno.substring(pos2, pos3);
            String objTerc = retorno.substring(pos3);

            BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
            boletimMMFertDAO.updateBolAbertoMMFert(objPrinc);

            ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
            ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(objSeg);
            apontMMFertDAO.updateApont(idApontArrayList);

            LeiraDAO leiraDAO = new LeiraDAO();
            ArrayList<Long> idMovLeiraArrayList = leiraDAO.idMovLeiraArrayList(objTerc);
            leiraDAO.updateMovLeira(idMovLeiraArrayList);

            ImpleMMDAO impleMMDAO = new ImpleMMDAO();
            impleMMDAO.deleteImple(idApontArrayList);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void updateBolFechado(String retorno, String activity){

        try {

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
            ArrayList<BoletimMMFertBean> boletimArrayList = boletimMMFertDAO.bolMMFertArrayList(objPrinc);

            for (BoletimMMFertBean boletimMMFertBean : boletimArrayList) {
                boletimMMFertDAO.updateBolMMFertEnvio(boletimMMFertBean.getIdBolMMFert());
                ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
                ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(apontMMFertDAO.apontMMFertList(boletimMMFertBean.getIdBolMMFert()));
                apontMMFertDAO.updateApont(idApontArrayList);
            }

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void deleteBolEnviado(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ArrayList<BoletimMMFertBean> boletimMMFertArrayList = boletimMMFertDAO.bolExcluirArrayList();

        for (BoletimMMFertBean boletimMMFertBean : boletimMMFertArrayList) {

            ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
            List<ApontMMFertBean> apontMMFertList = apontMMFertDAO.apontMMFertList(boletimMMFertBean.getIdBolMMFert());
            ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(apontMMFertList);
            apontMMFertDAO.deleteApont(idApontArrayList);

            ImpleMMDAO impleMMDAO = new ImpleMMDAO();
            impleMMDAO.deleteImple(idApontArrayList);

            idApontArrayList.clear();

            LeiraDAO leiraDAO = new LeiraDAO();
            leiraDAO.deleteMovLeira(leiraDAO.idMovLeiraArrayList(leiraDAO.movLeiraList(boletimMMFertBean.getIdBolMMFert())));

            RendMMDAO rendMMDAO = new RendMMDAO();
            rendMMDAO.deleteRend(boletimMMFertBean.getIdBolMMFert());

            RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
            recolhFertDAO.deleteRecolh(boletimMMFertBean.getIdBolMMFert());

            boletimMMFertDAO.deleteBolMMFert(boletimMMFertBean.getIdBolMMFert());

        }

        boletimMMFertArrayList.clear();


    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// ATIVIDADES ////////////////////////////////////////////

    public ArrayList getAtivArrayList(Long nroOS, String activity){
        ConfigCTR configCTR = new ConfigCTR();
        OSDAO osDAO = new OSDAO();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 2){", activity);
        if(PMMContext.aplic == 2){
            LogProcessoDAO.getInstance().insertLogProcesso("return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), osDAO.idAtivArrayList(nroOS), nroOS);", activity);
            return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), osDAO.idAtivArrayList(nroOS), nroOS);
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), nroOS);", activity);
            return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), nroOS);
        }
    }

    public List<RFuncaoAtivParBean> getFuncaoAtividadeList(String activity){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        ConfigCTR configCTR = new ConfigCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("return rFuncaoAtivParDAO.getListFuncaoAtividade(" + configCTR.getConfig().getIdAtivConfig() + ");", activity);
        return rFuncaoAtivParDAO.getListFuncaoAtividade(configCTR.getConfig().getIdAtivConfig());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////// PARADA ///////////////////////////////////////////

    public List<ApontMMFertBean> apontList(){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.apontMMFertList(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public List getListParada(){
        ConfigCTR configCTR = new ConfigCTR();
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getListParada(configCTR.getConfig().getIdAtivConfig());
    }

    public ParadaBean getParadaBean(String paradaString){
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getParadaBean(paradaString);
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////// RENDIMENTO //////////////////////////////////////////

    public int getContRend() {
        return contRend;
    }

    public void setContRend(int contRend) {
        this.contRend = contRend;
    }

    public int getPosRend() {
        return posRend;
    }

    public void setPosRend(int posRend) {
        this.posRend = posRend;
    }

    public void insRendBD(Long nroOS, String activity){
        RendMMDAO rendMMDAO = new RendMMDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("rendMMDAO.insRend(getBoletimMMFertAberto().getIdBolMMFert(), nroOS, activity);", activity);
        rendMMDAO.insRend(getBoletimMMFertAberto().getIdBolMMFert(), nroOS, activity);
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

    public Double rendOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        return osDAO.rendOS(nroOS);
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// IMPLEMENTO ///////////////////////////////////////////

    public Long getContImplemento() {
        return contImplemento;
    }

    public void setContImplemento(Long contImplemento) {
        this.contImplemento = contImplemento;
    }

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

    //////////////////////////////////// LEIRA //////////////////////////////////////////////

    public void inserirMovLeira(Long idLeira, Long tipo){
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        LeiraDAO leiraDAO = new LeiraDAO();
        leiraDAO.inserirMovLeira(idLeira, tipo, boletimMMFertBean.getIdBolMMFert(), boletimMMFertBean.getIdExtBolMMFert());
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// MOTOBOMBA ///////////////////////////////////////////

    public boolean verTransb(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verTransb(nroEquip);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// RECOLHIMENTO ////////////////////////////////////////

    public int getContRecolh() {
        return contRecolh;
    }

    public void setContRecolh(int contRecolh) {
        this.contRecolh = contRecolh;
    }

    public int getPosRecolh() {
        return posRecolh;
    }

    public void setPosRecolh(int posRecolh) {
        this.posRecolh = posRecolh;
    }

    public void insRecolh(String activity){
        ConfigCTR configCTR = new ConfigCTR();
        RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("recolhFertDAO.insRecolh(" + getBoletimMMFertAberto().getIdBolMMFert() + ", " + configCTR.getConfig().getNroOSConfig() + ");", activity);
        recolhFertDAO.insRecolh(getBoletimMMFertAberto().getIdBolMMFert(), configCTR.getConfig().getNroOSConfig(), activity);
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

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(matricFunc);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// EQUIP SEG ///////////////////////////////////////////////

    public EquipSegBean getEquipSeg(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.getEquipSeg(nroEquip);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// TURNO ///////////////////////////////////////////////

    public List<TurnoBean> getTurnoCodList(String activity){
        ConfigCTR configCTR = new ConfigCTR();
        TurnoDAO turnoDAO = new TurnoDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("return turnoDAO.getTurnoCodList(configCTR.getEquip().getCodTurno());", activity);
        return turnoDAO.getTurnoCodList(configCTR.getEquip().getCodTurno());
    }

    public TurnoBean getTurnoId(Long idTurno){
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurnoId(idTurno);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////// BOCAL /////////////////////////////////////////

    public BocalBean getBocal(Long idBocal){
        BocalDAO bocalDAO = new BocalDAO();
        return bocalDAO.getBocal(idBocal);
    }

    public List<BocalBean> bocalList(){
        BocalDAO bocalDAO = new BocalDAO();
        return bocalDAO.bocalList();
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

    public int verCarreta(Long nroCarreta){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.verCarreta(nroCarreta);
    }

    public void insCarreta(Long nroCarreta){
        CarretaDAO carretaDAO = new CarretaDAO();
        carretaDAO.insCarreta(nroCarreta);
    }

    public boolean hasElemCarreta(){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.hasElemCarreta();
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

    public void setMotoMecBean(MotoMecBean motoMecBean) {
        this.motoMecBean = motoMecBean;
    }

    public List<MotoMecBean> paradaList() {
        Long aplic;
        ConfigCTR configCTR = new ConfigCTR();
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        if(PMMContext.aplic == 2){
            aplic = 1l;
        }
        else{
            if(configCTR.getOS().getTipoOS() == 0L){
                aplic = 3L;
            }
            else{
                aplic = 2L;
            }
        }
        return motoMecDAO.paradaList(aplic);
    }

    public List<MotoMecBean> motoMecList() {
        Long aplic;
        ConfigCTR configCTR = new ConfigCTR();
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        if(PMMContext.aplic == 2){
            aplic = 1l;
        }
        else{
            if(configCTR.getOS().getTipoOS() == 0L){
                aplic = 3L;
            }
            else{
                aplic = 2L;
            }
        }
        return motoMecDAO.motoMecList(aplic);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual, int tipoReceb, String activity) {
        ArrayList classeArrayList = new ArrayList();
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = new ArrayList();\n" +
                "        switch (" + tipoAtual + ") {", activity);
        switch (tipoAtual) {
            case "Leira":
                classeArrayList.add("LeiraBean");
                break;
            case "Operador":
                classeArrayList.add("FuncBean");
                break;
            case "Turno":
                classeArrayList.add("TurnoBean");
                break;
            case "EquipSeg":
                classeArrayList.add("EquipSegBean");
                break;
            case "Parada":
                classeArrayList.add("RAtivParadaBean");
                classeArrayList.add("ParadaBean");
                break;
            case "Bocal":
                classeArrayList.add("BocalBean");
                break;
            case "Pressao":
                classeArrayList.add("PressaoBocalBean");
                break;
            case "Frente":
                classeArrayList.add("FrenteBean");
                break;
            case "Propriedade":
                classeArrayList.add("PropriedadeBean");
                break;
            case "OS":
                classeArrayList.add("OSBean");
                classeArrayList.add("AtividadeBean");
                classeArrayList.add("EquipSegBean");
                classeArrayList.add("FrenteBean");
                classeArrayList.add("MotoMecBean");
                break;
        }
        LogProcessoDAO.getInstance().insertLogProcesso("AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);", activity);
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        OSDAO osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog, activity);
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        atividadeDAO.verAtiv(dado  + "_" + configCTR.getEquip().getNroEquip(), telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////APONTAMENTO /////////////////////////////////////////////

    public void inserirApontBolAnterior(String activity){

        CECCTR cecCTR = new CECCTR();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();

        if(((configCTR.getConfig().getUltParadaBolConfig() == 0L)
                || (configCTR.getConfig().getUltParadaBolConfig() == 72L)) && cecCTR.hasPreCEC()) {

            if(apontMMFertDAO.verDataHoraApont(boletimMMFertBean.getIdBolMMFert())){
                salvarApont(configCTR.getConfig().getUltParadaBolConfig(), 0L, Tempo.getInstance().dthrAddMinutoLong(apontMMFertDAO.getUltApont(boletimMMFertBean.getIdBolMMFert()).getDthrApontLongMMFert(), 1), activity);
            }
            else{
                salvarApont(configCTR.getConfig().getUltParadaBolConfig(), 0L, activity);
            }

        }

    }

    public void inserirParadaCheckList(String activity){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("public void inserirParadaCheckList(String activity){\n" +
                "        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();\n" +
                "        salvarApont(rFuncaoAtivParDAO.idParadaCheckList(), 0L, activity);", activity);
        salvarApont(rFuncaoAtivParDAO.idParadaCheckList(), 0L, activity);
    }

    public void inserirApontTransb(Long idTransb, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void inserirApontTransb(Long idTransb, String activity){\n" +
                "        salvarApont(0L, idTransb, activity);", activity);
        salvarApont(0L, idTransb, activity);
    }

    public void inserirParadaImplemento(String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("    public void inserirParadaImplemento(String activity){\n" +
                "        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();\n" +
                "        salvarApont(rFuncaoAtivParDAO.idParadaImplemento(), 0L, activity);", activity);
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        salvarApont(rFuncaoAtivParDAO.idParadaImplemento(), 0L, activity);
    }

    public void inserirParadaTrocaMotorista(String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("    public void inserirParadaTrocaMotorista(String activity){\n" +
                "        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();\n" +
                "        salvarApont(rFuncaoAtivParDAO.idParadaImplemento(), 0L, activity);", activity);
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        salvarApont(rFuncaoAtivParDAO.idParadaTrocaMotorista(), 0L, activity);
    }

    private void salvarApont(Long idParada, Long idTransb, Long dthrLong, String activity){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ApontMMFertBean apontMMFertBean = apontMMFertDefault();
        apontMMFertBean.setParadaApontMMFert(idParada);
        apontMMFertBean.setTransbApontMMFert(idTransb);
        apontMMFertBean.setDthrApontMMFert(Tempo.getInstance().dthrLongToString(dthrLong));
        apontMMFertBean.setDthrApontLongMMFert(dthrLong);
        LogProcessoDAO.getInstance().insertLogProcesso("ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();\n" +
                "        ApontMMFertBean apontMMFertBean = apontMMFertDefault();\n" +
                "        apontMMFertBean.setParadaApontMMFert(idParada);\n" +
                "        apontMMFertBean.setTransbApontMMFert(idTransb);\n" +
                "        apontMMFertBean.setDthrApontMMFert(Tempo.getInstance().dthrLongToString(dthrLong));\n" +
                "        apontMMFertBean.setDthrApontLongMMFert(dthrLong);\n" +
                "        apontMMFertDAO.salvarApont(apontMMFertBean, 1);", activity);
        apontMMFertDAO.salvarApont(apontMMFertBean, 1);
        LogProcessoDAO.getInstance().insertLogProcesso("salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);
    }

    private void salvarApont(Long idParada, Long idTransb, String activity){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ApontMMFertBean apontMMFertBean = apontMMFertDefault();
        apontMMFertBean.setParadaApontMMFert(idParada);
        apontMMFertBean.setTransbApontMMFert(idTransb);
        LogProcessoDAO.getInstance().insertLogProcesso("private void salvarApont(Long idParada, Long idTransb, String activity){\n" +
                "        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();\n" +
                "        ApontMMFertBean apontMMFertBean = apontMMFertDefault();\n" +
                "        apontMMFertBean.setParadaApontMMFert(idParada);\n" +
                "        apontMMFertBean.setTransbApontMMFert(idTransb);\n" +
                "        apontMMFertDAO.salvarApont(apontMMFertBean, 1);", activity);
        apontMMFertDAO.salvarApont(apontMMFertBean, 1);
        LogProcessoDAO.getInstance().insertLogProcesso("salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);
    }

    public void salvarApont(Long idParada, Long idTransb, Double longitude, Double latitude, String activity){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ApontMMFertBean apontMMFertBean = apontMMFertDefault();
        apontMMFertBean.setParadaApontMMFert(idParada);
        apontMMFertBean.setTransbApontMMFert(idTransb);
        apontMMFertBean.setLongitudeApontMMFert(longitude);
        apontMMFertBean.setLatitudeApontMMFert(latitude);
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarApont(Long idParada, Long idTransb, Double longitude, Double latitude, String activity){\n" +
                "        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();\n" +
                "        ApontMMFertBean apontMMFertBean = apontMMFertDefault();\n" +
                "        apontMMFertBean.setParadaApontMMFert(idParada);\n" +
                "        apontMMFertBean.setTransbApontMMFert(idTransb);\n" +
                "        apontMMFertBean.setLongitudeApontMMFert(longitude);\n" +
                "        apontMMFertBean.setLatitudeApontMMFert(latitude);\n" +
                "apontMMFertDAO.salvarApont(apontMMFertBean, 2);", activity);
        apontMMFertDAO.salvarApont(apontMMFertBean, 2);
        LogProcessoDAO.getInstance().insertLogProcesso("salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);

    }

    public void salvarApont(Double longitude, Double latitude, String activity){

        ConfigCTR configCTR = new ConfigCTR();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();

        LogProcessoDAO.getInstance().insertLogProcesso("Long idParada = 0L;\n" +
                "        if(motoMecBean.getFuncaoOperMotoMec() == 1){\n" +
                "            idParada = 0L;\n" +
                "        }\n" +
                "        else if(motoMecBean.getFuncaoOperMotoMec() == 2){\n" +
                "            idParada = motoMecBean.getIdOperMotoMec();\n" +
                "        }", activity);

        Long idParada = 0L;

        if(motoMecBean.getFuncaoOperMotoMec() == 1){
            idParada = 0L;
        }
        else if(motoMecBean.getFuncaoOperMotoMec() == 2){
            idParada = motoMecBean.getIdOperMotoMec();
        }

        ApontMMFertBean apontMMFertBean = apontMMFertDefault();
        apontMMFertBean.setIdMotoMec(motoMecBean.getIdMotoMec());
        apontMMFertBean.setParadaApontMMFert(idParada);
        apontMMFertBean.setAtivApontMMFert(configCTR.getConfig().getIdAtivConfig());
        apontMMFertBean.setLongitudeApontMMFert(longitude);
        apontMMFertBean.setLatitudeApontMMFert(latitude);
        LogProcessoDAO.getInstance().insertLogProcesso("ApontMMFertBean apontMMFertBean = apontMMFertDefault();\n" +
                "        apontMMFertBean.setIdMotoMec(motoMecBean.getIdMotoMec());\n" +
                "        apontMMFertBean.setParadaApontMMFert(idParada);\n" +
                "        apontMMFertBean.setAtivApontMMFert(idAtiv);\n" +
                "        apontMMFertBean.setLongitudeApontMMFert(longitude);\n" +
                "        apontMMFertBean.setLatitudeApontMMFert(latitude);\n" +
                "        apontMMFertDAO.salvarApont(apontMMFertBean, 2);", activity);
        apontMMFertDAO.salvarApont(apontMMFertBean, 2);

        LogProcessoDAO.getInstance().insertLogProcesso("salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);

    }

    private void salvarAltCompApont(String dthr, String activity){

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("impleMMDAO.salvarApontImple(apontMMFertDAO.getApontDthr(dthr).getIdApontMMFert(), dthr);", activity);
        impleMMDAO.salvarApontImple(apontMMFertDAO.getApontDthr(dthr).getIdApontMMFert(), dthr, activity);

        LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().envioDados(activity);", activity);
        EnvioDadosServ.getInstance().envioDados(activity);

    }

    private ApontMMFertBean apontMMFertDefault(){
        Long dthrLong = Tempo.getInstance().dtHr();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
        apontMMFertBean.setIdBolMMFert(boletimMMFertBean.getIdBolMMFert());
        apontMMFertBean.setIdMotoMec(0L);
        apontMMFertBean.setLatitudeApontMMFert(boletimMMFertBean.getLatitudeBolMMFert());
        apontMMFertBean.setLongitudeApontMMFert(boletimMMFertBean.getLongitudeBolMMFert());
        apontMMFertBean.setStatusConApontMMFert(boletimMMFertBean.getStatusConBolMMFert());
        apontMMFertBean.setOsApontMMFert(configCTR.getConfig().getNroOSConfig());
        apontMMFertBean.setAtivApontMMFert(configCTR.getConfig().getIdAtivConfig());
        apontMMFertBean.setIdFrenteApontMMFert(configCTR.getConfig().getIdFrenteConfig());
        apontMMFertBean.setIdProprApontMMFert(configCTR.getConfig().getIdPropriedadeConfig());
        apontMMFertBean.setParadaApontMMFert(0L);
        apontMMFertBean.setDthrApontMMFert(Tempo.getInstance().dthrLongToString(dthrLong));
        apontMMFertBean.setDthrApontLongMMFert(dthrLong);
        apontMMFertBean.setTransbApontMMFert(0L);
        apontMMFertBean.setPressaoApontMMFert(configCTR.getConfig().getPressaoConfig());
        apontMMFertBean.setVelocApontMMFert(configCTR.getConfig().getVelocConfig());
        apontMMFertBean.setBocalApontMMFert(configCTR.getConfig().getBocalConfig());
        return apontMMFertBean;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO APONT ///////////////////////////////////////////

    public boolean hasApontBolAberto(){
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return apontMMDAO.hasApontBol(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert());
    }

    public boolean verifBackupApont() {
        BoletimMMFertDAO boletimMMDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.verifBackupApont(boletimMMDAO.getBolAbertoMMFert().getIdBolMMFert(), motoMecBean.getIdMotoMec());
    }

    public boolean verifBackupApont(Long idParada) {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertDAO boletimMMDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.verifBackupApont(boletimMMDAO.getBolAbertoMMFert().getIdBolMMFert(), configCTR.getConfig().getNroOSConfig()
                                                , configCTR.getConfig().getIdAtivConfig(), idParada);
    }

    public boolean verifBackupApontTransb(Long idParada, Long idTransb) {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertDAO boletimMMDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        return apontMMDAO.verifBackupApontTransb(boletimMMDAO.getBolAbertoMMFert().getIdBolMMFert(), configCTR.getConfig().getNroOSConfig()
                                                    , configCTR.getConfig().getIdAtivConfig(), idParada, idTransb);
    }

    public int verTrocaTransb(){
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return apontMMDAO.verTransbordo(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert());
    }

    public boolean verDataHoraInsApontMMFert(){
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        if(Tempo.getInstance().dif() > 0){
            return false;
        }
        else{
            return apontMMDAO.verDataHoraApont(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert());
        }
    }

    public boolean verDataHoraInsMovLeira(){
        LeiraDAO leiraDAO = new LeiraDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return leiraDAO.verDataHoraMovLeira(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

}
