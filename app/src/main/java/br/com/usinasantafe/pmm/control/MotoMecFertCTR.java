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
        LogProcessoDAO.getInstance().insert("boletimMMFertDAO.salvarBolAbertoMMFert(configCTR.getEquip().getTipoEquip(), configBean.getNroOSConfig()\n" +
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
        LogProcessoDAO.getInstance().insert("if (boletimMMFertDAO == null) {\n" +
                "            boletimMMFertDAO = new BoletimMMFertDAO();}\n" +
                "        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();\n" +
                "        ConfigCTR configCTR = new ConfigCTR();\n" +
                "", activity);
        configCTR.setUltParadaBolConfig(apontMMFertDAO.getUltApont(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert()).getParadaApontMMFert());

        LogProcessoDAO.getInstance().insert("boletimMMFertDAO.salvarBolFechadoMMFert();", activity);
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
            LogErroDAO.getInstance().insert(e);
        }

    }

    public void delBolFechado(String retorno, String activity){

        try {

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
            ArrayList<BoletimMMFertBean> boletimArrayList = boletimMMFertDAO.bolMMFertArrayList(objPrinc);

            for (BoletimMMFertBean boletimMMFertBean : boletimArrayList) {

                ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
                List<ApontMMFertBean> apontMMFertList = apontMMFertDAO.apontMMFertList(boletimMMFertBean.getIdBolMMFert());

//                if (apontMMFertList.size() == boletimMMFertBean.getQtdeApontBolMMFert()) {
//
//                    ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(apontMMFertList);
//                    apontMMFertDAO.deleteApont(idApontArrayList);
//
//                    ImpleMMDAO impleMMDAO = new ImpleMMDAO();
//                    impleMMDAO.deleteImple(idApontArrayList);
//
//                    LeiraDAO leiraDAO = new LeiraDAO();
//                    leiraDAO.deleteMovLeira(leiraDAO.idMovLeiraArrayList(leiraDAO.movLeiraList(boletimMMFertBean.getIdBolMMFert())));
//
//                    RendMMDAO rendMMDAO = new RendMMDAO();
//                    rendMMDAO.deleteRend(boletimMMFertBean.getIdBolMMFert());
//
//                    RecolhFertDAO recolhFertDAO = new RecolhFertDAO();
//                    recolhFertDAO.deleteRecolh(boletimMMFertBean.getIdBolMMFert());
//
//                    boletimMMFertDAO.deleteBol(boletimMMFertBean.getIdBolMMFert());
//
//                }

                boletimMMFertDAO.updateBolMMFertEnvio(boletimMMFertBean.getIdBolMMFert());

            }

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insert(e);
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// ATIVIDADES ////////////////////////////////////////////

    public ArrayList getAtivArrayList(Long nroOS, String activity){
        ConfigCTR configCTR = new ConfigCTR();
        OSDAO osDAO = new OSDAO();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        LogProcessoDAO.getInstance().insert("if(PMMContext.aplic == 2){", activity);
        if(PMMContext.aplic == 2){
            LogProcessoDAO.getInstance().insert("return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), osDAO.idAtivArrayList(nroOS));", activity);
            return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), osDAO.idAtivArrayList(nroOS));
        }
        else {
            LogProcessoDAO.getInstance().insert("return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), nroOS);", activity);
            return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), nroOS);
        }
    }

    public List<RFuncaoAtivParBean> getFuncaoAtividadeList(String activity){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        ConfigCTR configCTR = new ConfigCTR();
        LogProcessoDAO.getInstance().insert("return rFuncaoAtivParDAO.getListFuncaoAtividade(" + configCTR.getConfig().getIdAtivConfig() + ");", activity);
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
        LogProcessoDAO.getInstance().insert("rendMMDAO.insRend(getBoletimMMFertAberto().getIdBolMMFert(), nroOS, activity);", activity);
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
        LogProcessoDAO.getInstance().insert("recolhFertDAO.insRecolh(" + getBoletimMMFertAberto().getIdBolMMFert() + ", " + configCTR.getConfig().getNroOSConfig() + ");", activity);
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
        LogProcessoDAO.getInstance().insert("return turnoDAO.getTurnoCodList(configCTR.getEquip().getCodTurno());", activity);
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
            if(configCTR.getOS().getTipoOS() == 1L){
                aplic = 2L;
            }
            else{
                aplic = 3L;
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
            if(configCTR.getOS().getTipoOS() == 1L){
                aplic = 2L;
            }
            else{
                aplic = 3L;
            }
        }
        return motoMecDAO.motoMecList(aplic);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual, int tipoReceb, String activity) {
        ArrayList classeArrayList = new ArrayList();
        LogProcessoDAO.getInstance().insert("ArrayList classeArrayList = new ArrayList();\n" +
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
        LogProcessoDAO.getInstance().insert("AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);", activity);
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
        LogProcessoDAO.getInstance().insert("salvarApont(rFuncaoAtivParDAO.idParadaCheckList(), 0L, activity);", activity);
        salvarApont(rFuncaoAtivParDAO.idParadaCheckList(), 0L, activity);
    }

    public void inserirApontTransb(Long idTransb, String activity){
        salvarApont(0L, idTransb, activity);
    }

    public void inserirParadaImplemento(String activity){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        salvarApont(rFuncaoAtivParDAO.idParadaImplemento(), 0L, activity);
    }

    private void salvarApont(Long idParada, Long idTransb, Long dthrLong, String activity){

        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        LogProcessoDAO.getInstance().insert("apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), 0L, idParada\n" +
                "                , boletimMMFertBean.getOsBolMMFert(), boletimMMFertBean.getAtivPrincBolMMFert()\n" +
                "                , configCTR.getConfig().getIdFrenteConfig(), configCTR.getConfig().getIdPropriedadeConfig()\n" +
                "                , boletimMMFertBean.getLatitudeBolMMFert(), boletimMMFertBean.getLongitudeBolMMFert()\n" +
                "                , Tempo.getInstance().dthrLongToString(dthrLong), dthrLong\n" +
                "                , idTransb, configCTR.getConfig().getPressaoConfig()\n" +
                "                , configCTR.getConfig().getVelocConfig(), configCTR.getConfig().getBocalConfig()\n" +
                "                , boletimMMFertBean.getStatusConBolMMFert(), 1);", activity);
        apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), 0L, idParada
                , boletimMMFertBean.getOsBolMMFert(), boletimMMFertBean.getAtivPrincBolMMFert()
                , configCTR.getConfig().getIdFrenteConfig(), configCTR.getConfig().getIdPropriedadeConfig()
                , boletimMMFertBean.getLatitudeBolMMFert(), boletimMMFertBean.getLongitudeBolMMFert()
                , Tempo.getInstance().dthrLongToString(dthrLong), dthrLong
                , idTransb, configCTR.getConfig().getPressaoConfig()
                , configCTR.getConfig().getVelocConfig(), configCTR.getConfig().getBocalConfig()
                , boletimMMFertBean.getStatusConBolMMFert(), 1);

        salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);

    }

    private void salvarApont(Long idParada, Long idTransb, String activity){

        Long dthrLong = Tempo.getInstance().dtHr();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        LogProcessoDAO.getInstance().insert("apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), 0L, idParada\n" +
                "                , boletimMMFertBean.getOsBolMMFert(), boletimMMFertBean.getAtivPrincBolMMFert()\n" +
                "                , configCTR.getConfig().getIdFrenteConfig(), configCTR.getConfig().getIdPropriedadeConfig()\n" +
                "                , boletimMMFertBean.getLatitudeBolMMFert(), boletimMMFertBean.getLongitudeBolMMFert()\n" +
                "                , Tempo.getInstance().dthrLongToString(dthrLong), dthrLong\n" +
                "                , idTransb, configCTR.getConfig().getPressaoConfig()\n" +
                "                , configCTR.getConfig().getVelocConfig(), configCTR.getConfig().getBocalConfig()\n" +
                "                , boletimMMFertBean.getStatusConBolMMFert(), 1);", activity);
        apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), 0L, idParada
                , boletimMMFertBean.getOsBolMMFert(), boletimMMFertBean.getAtivPrincBolMMFert()
                , configCTR.getConfig().getIdFrenteConfig(), configCTR.getConfig().getIdPropriedadeConfig()
                , boletimMMFertBean.getLatitudeBolMMFert(), boletimMMFertBean.getLongitudeBolMMFert()
                , Tempo.getInstance().dthrLongToString(dthrLong), dthrLong
                , idTransb, configCTR.getConfig().getPressaoConfig()
                , configCTR.getConfig().getVelocConfig(), configCTR.getConfig().getBocalConfig()
                , boletimMMFertBean.getStatusConBolMMFert(), 1);

        salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);

    }

    public void salvarApont(Long idParada, Long idTransb, Double longitude, Double latitude, String activity){

        Long dthrLong = Tempo.getInstance().dtHr();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();

        LogProcessoDAO.getInstance().insert("apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), 0L, idParada\n" +
                "                , configCTR.getConfig().getNroOSConfig(), configCTR.getConfig().getIdAtivConfig()\n" +
                "                , configCTR.getConfig().getIdFrenteConfig(), configCTR.getConfig().getIdPropriedadeConfig()\n" +
                "                , latitude, longitude\n" +
                "                , Tempo.getInstance().dthrLongToString(dthrLong), dthrLong\n" +
                "                , idTransb, configCTR.getConfig().getPressaoConfig()\n" +
                "                , configCTR.getConfig().getVelocConfig(), configCTR.getConfig().getBocalConfig()\n" +
                "                , boletimMMFertBean.getStatusConBolMMFert(), 2);", activity);
        apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), 0L, idParada
                , configCTR.getConfig().getNroOSConfig(), configCTR.getConfig().getIdAtivConfig()
                , configCTR.getConfig().getIdFrenteConfig(), configCTR.getConfig().getIdPropriedadeConfig()
                , latitude, longitude
                , Tempo.getInstance().dthrLongToString(dthrLong), dthrLong
                , idTransb, configCTR.getConfig().getPressaoConfig()
                , configCTR.getConfig().getVelocConfig(), configCTR.getConfig().getBocalConfig()
                , boletimMMFertBean.getStatusConBolMMFert(), 2);

        LogProcessoDAO.getInstance().insert("salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong));", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), null);

    }

    public void salvarApont(Double longitude, Double latitude, String activity){

        Long dthrLong = Tempo.getInstance().dtHr();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();

        LogProcessoDAO.getInstance().insert("        Long ativ = 0L;\n" +
                "        Long parada = 0L;\n" +
                "        if(motoMecBean.getFuncaoOperMotoMec() == 1){\n" +
                "            ativ = motoMecBean.getIdOperMotoMec();\n" +
                "            parada = 0L;\n" +
                "        }\n" +
                "        else if(motoMecBean.getFuncaoOperMotoMec() == 2){\n" +
                "            ativ = configCTR.getConfig().getIdAtivConfig();\n" +
                "            parada = motoMecBean.getIdOperMotoMec();\n" +
                "        }", activity);

        Long ativ = 0L;
        Long parada = 0L;

        if(motoMecBean.getFuncaoOperMotoMec() == 1){
            ativ = motoMecBean.getIdOperMotoMec();
            parada = 0L;
        }
        else if(motoMecBean.getFuncaoOperMotoMec() == 2){
            ativ = configCTR.getConfig().getIdAtivConfig();
            parada = motoMecBean.getIdOperMotoMec();
        }

        LogProcessoDAO.getInstance().insert("apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), motoMecBean.getIdMotoMec(), parada\n" +
                "                , configCTR.getConfig().getNroOSConfig(), ativ\n" +
                "                , configCTR.getConfig().getIdFrenteConfig(), configCTR.getConfig().getIdPropriedadeConfig()\n" +
                "                , latitude, longitude\n" +
                "                , Tempo.getInstance().dthrLongToString(dthrLong), dthrLong\n" +
                "                , 0L, configCTR.getConfig().getPressaoConfig()\n" +
                "                , configCTR.getConfig().getVelocConfig(), configCTR.getConfig().getBocalConfig()\n" +
                "                , boletimMMFertBean.getStatusConBolMMFert(), 2);", activity);
        apontMMFertDAO.salvarApont(boletimMMFertBean.getIdBolMMFert(), motoMecBean.getIdMotoMec(), parada
                , configCTR.getConfig().getNroOSConfig(), ativ
                , configCTR.getConfig().getIdFrenteConfig(), configCTR.getConfig().getIdPropriedadeConfig()
                , latitude, longitude
                , Tempo.getInstance().dthrLongToString(dthrLong), dthrLong
                , 0L, configCTR.getConfig().getPressaoConfig()
                , configCTR.getConfig().getVelocConfig(), configCTR.getConfig().getBocalConfig()
                , boletimMMFertBean.getStatusConBolMMFert(), 2);

        LogProcessoDAO.getInstance().insert("salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);

    }

    private void salvarAltCompApont(String dthr, String activity){

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ImpleMMDAO impleMMDAO = new ImpleMMDAO();
        LogProcessoDAO.getInstance().insert("impleMMDAO.salvarApontImple(apontMMFertDAO.getApontDthr(dthr).getIdApontMMFert(), dthr);", activity);
        impleMMDAO.salvarApontImple(apontMMFertDAO.getApontDthr(dthr).getIdApontMMFert(), dthr, activity);

        LogProcessoDAO.getInstance().insert("EnvioDadosServ.getInstance().envioDados(activity);", activity);
        EnvioDadosServ.getInstance().envioDados(activity);

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
        return apontMMDAO.verDataHoraApont(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert());
    }

    public boolean verDataHoraInsMovLeira(){
        LeiraDAO leiraDAO = new LeiraDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return leiraDAO.verDataHoraMovLeira(boletimMMFertDAO.getBolAbertoMMFert().getIdBolMMFert());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

}
