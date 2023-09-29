package br.com.usinasantafe.cmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.PressaoBocalBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.cmm.model.dao.ApontMMFertDAO;
import br.com.usinasantafe.cmm.model.dao.ApontMecanDAO;
import br.com.usinasantafe.cmm.model.dao.AtividadeDAO;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.model.dao.BocalDAO;
import br.com.usinasantafe.cmm.model.dao.BoletimMMFertDAO;
import br.com.usinasantafe.cmm.model.dao.BoletimPneuDAO;
import br.com.usinasantafe.cmm.model.dao.CarregCompDAO;
import br.com.usinasantafe.cmm.model.dao.CarretaDAO;
import br.com.usinasantafe.cmm.model.dao.EquipSegDAO;
import br.com.usinasantafe.cmm.model.dao.FuncDAO;
import br.com.usinasantafe.cmm.model.dao.ImplementoMMDAO;
import br.com.usinasantafe.cmm.model.dao.ItemCalibPneuDAO;
import br.com.usinasantafe.cmm.model.dao.ItemManutPneuDAO;
import br.com.usinasantafe.cmm.model.dao.LeiraDAO;
import br.com.usinasantafe.cmm.model.dao.LogErroDAO;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.model.dao.MotoMecDAO;
import br.com.usinasantafe.cmm.model.dao.OSDAO;
import br.com.usinasantafe.cmm.model.dao.ParadaDAO;
import br.com.usinasantafe.cmm.model.dao.PressaoFertDAO;
import br.com.usinasantafe.cmm.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.cmm.model.dao.RecolhimentoFertDAO;
import br.com.usinasantafe.cmm.model.dao.RendimentoMMDAO;
import br.com.usinasantafe.cmm.model.dao.TurnoDAO;
import br.com.usinasantafe.cmm.util.AtualDadosServ;
import br.com.usinasantafe.cmm.util.EnvioDadosServ;
import br.com.usinasantafe.cmm.util.Tempo;

public class MotoMecFertCTR {

    private MotoMecBean motoMecBean;
    private Long contImplemento;
    private int contRend;
    private int posRend;
    private int contRecolh;
    private int posRecolh;

    public MotoMecFertCTR() {
    }

    public boolean verBolAberto(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.verBoletimMMFertAberto();
    }

    public BoletimMMFertBean getBoletimMMFertAberto(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.getBoletimMMFertAberto();
    }

    public void salvarBolMMFertAberto(String activity){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        LogProcessoDAO.getInstance().insertLogProcesso("boletimMMFertDAO.salvarBoletimMMFertAberto(configCTR.getConfig().getMatricFuncConfig()\n" +
                "                , configCTR.getEquip().getIdEquip()\n" +
                "                , configCTR.getConfig().getIdEquipBombaBolConfig()\n" +
                "                , configCTR.getConfig().getIdTurnoConfig()\n" +
                "                , configCTR.getConfig().getHodometroInicialConfig()\n" +
                "                , configCTR.getConfig().getLongitudeConfig()\n" +
                "                , configCTR.getConfig().getLatitudeConfig()\n" +
                "                , configCTR.getEquip().getTipoEquip()\n" +
                "                , configBean.getNroOSConfig()\n" +
                "                , configBean.getIdAtivConfig()\n" +
                "                , configBean.getStatusConConfig()\n" +
                "                , activity);", activity);
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        boletimMMFertDAO.salvarBoletimMMFertAberto(configCTR.getConfig().getMatricFuncConfig()
                , configCTR.getEquip().getIdEquip()
                , configCTR.getConfig().getIdEquipBombaBolConfig()
                , configCTR.getConfig().getIdTurnoConfig()
                , configCTR.getConfig().getHodometroInicialConfig()
                , configCTR.getConfig().getLongitudeConfig()
                , configCTR.getConfig().getLatitudeConfig()
                , configCTR.getEquip().getTipoEquip()
                , configBean.getNroOSConfig()
                , configBean.getIdAtivConfig()
                , configBean.getStatusConConfig()
                , activity);
    }

    public void salvarBolMMFertFechado(String activity){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();

        ConfigCTR configCTR = new ConfigCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("boletimMMFertDAO = new BoletimMMFertDAO();\n" +
                "        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();\n" +
                "        ConfigCTR configCTR = new ConfigCTR();", activity);
        configCTR.setUltParadaBolConfig(apontMMFertDAO.getUltApont(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert()).getParadaApontMMFert());

        LogProcessoDAO.getInstance().insertLogProcesso("boletimMMFertDAO.salvarBoletimMMFertFechado(configCTR.getConfig().getHodometroFinalConfig(), activity);", activity);
        boletimMMFertDAO.salvarBoletimMMFertFechado(configCTR.getConfig().getHodometroFinalConfig(), activity);

        EnvioDadosServ.getInstance().envioDados(activity);

    }

    public boolean verEnvioApont() {
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        int qtde = apontMMFertDAO.apontEnvioList().size();
        return qtde > 0;
    }

    public boolean verEnvioMovLeira() {
        LeiraDAO leiraDAO = new LeiraDAO();
        int qtde = leiraDAO.movLeiraEnvioList().size();
        return qtde > 0;
    }

    public boolean verEnvioBolFech() {
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.verBoletimMMFertFechado();
    }

    public boolean verEnvioBolPneu() {
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        return boletimPneuDAO.verifBolPneuFechado();
    }

    public String dadosEnvioBolAbertoMMFert(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        String dadosEnvioBoletim = boletimMMFertDAO.dadosEnvioBoletimMMFertAberto();

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ArrayList<Long> idBoletimArrayList = boletimMMFertDAO.idBoletimArrayList(boletimMMFertDAO.boletimMMFertAbertoList());
        ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(apontMMFertDAO.apontEnvioList(idBoletimArrayList));
        ArrayList<Long> idBoletimPneuArrayList = boletimPneuDAO.idBolPneuArrayList(boletimPneuDAO.bolPneuEnvioList(idBoletimArrayList));

        String dadosEnvioApont = apontMMFertDAO.dadosEnvioApont(apontMMFertDAO.apontEnvioList(idBoletimArrayList));

        ImplementoMMDAO implementoMMDAO = new ImplementoMMDAO();
        String dadosEnvioApontImpl = implementoMMDAO.dadosEnvioApontImplMM(implementoMMDAO.apontImplEnvioList(idApontArrayList));

        LeiraDAO leiraDAO = new LeiraDAO();
        String dadosEnvioMovLeira = leiraDAO.dadosEnvioMovLeira(leiraDAO.movLeiraEnvioList(idBoletimArrayList));

        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        String dadosEnvioApontMecan = apontMecanDAO.dadosEnvioApontMecan(apontMecanDAO.apontMecanEnvioList(idBoletimArrayList));

        String dadosEnvioBoletimPneu = boletimPneuDAO.dadosEnvioBolPneu(boletimPneuDAO.bolPneuEnvioList(idBoletimArrayList));

        ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();
        String dadosEnvioItemMedPneu = itemCalibPneuDAO.dadosEnvioItemMedPneu(itemCalibPneuDAO.itemMedPneuIdBolList(idBoletimPneuArrayList));

        ItemManutPneuDAO itemManutPneuDAO = new ItemManutPneuDAO();
        String dadosEnvioItemManutPneu = itemManutPneuDAO.dadosEnvioItemManutPneu(itemManutPneuDAO.itemManutPneuIdBolList(idBoletimPneuArrayList));

        CarregCompDAO carregCompDAO = new CarregCompDAO();
        String dadosCarregComp = carregCompDAO.dadosEnvioCarreg(carregCompDAO.carregCompostoDescarregInsumo(idApontArrayList));

        idBoletimArrayList.clear();
        idApontArrayList.clear();

        return dadosEnvioBoletim + "_" + dadosEnvioApont + "_" + dadosEnvioApontImpl + "_" + dadosEnvioMovLeira + "_" + dadosEnvioApontMecan + "_" + dadosEnvioBoletimPneu + "_" + dadosEnvioItemMedPneu +  "_" + dadosEnvioItemManutPneu + "_" + dadosCarregComp;
    }

    public String dadosEnvioBolFechadoMMFert(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ArrayList<Long> idBoletimArrayList = boletimMMFertDAO.idBoletimArrayList(boletimMMFertDAO.boletimMMFertFechadoList());
        String dadosEnvioBoletim = boletimMMFertDAO.dadosEnvioBoletimMMFertFechado();

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
        ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(apontMMFertDAO.apontEnvioList(idBoletimArrayList));
        ArrayList<Long> idBoletimPneuArrayList = boletimPneuDAO.idBolPneuArrayList(boletimPneuDAO.bolPneuEnvioList(idBoletimArrayList));

        String dadosEnvioApont = apontMMFertDAO.dadosEnvioApont(apontMMFertDAO.apontEnvioList(idBoletimArrayList));

        ImplementoMMDAO implementoMMDAO = new ImplementoMMDAO();
        String dadosEnvioApontImpl = implementoMMDAO.dadosEnvioApontImplMM(implementoMMDAO.apontImplEnvioList(idApontArrayList));

        LeiraDAO leiraDAO = new LeiraDAO();
        String dadosEnvioMovLeira = leiraDAO.dadosEnvioMovLeira(leiraDAO.movLeiraEnvioList(idBoletimArrayList));

        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        String dadosEnvioApontMecan = apontMecanDAO.dadosEnvioApontMecan(apontMecanDAO.apontMecanEnvioList(idBoletimArrayList));

        String dadosEnvioBoletimPneu = boletimPneuDAO.dadosEnvioBolPneu(boletimPneuDAO.bolPneuEnvioList(idApontArrayList));

        ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();
        String dadosEnvioItemMedPneu = itemCalibPneuDAO.dadosEnvioItemMedPneu(itemCalibPneuDAO.itemMedPneuIdBolList(boletimPneuDAO.idBolPneuArrayList(boletimPneuDAO.bolPneuEnvioList(idApontArrayList))));

        ItemManutPneuDAO itemManutPneuDAO = new ItemManutPneuDAO();
        String dadosEnvioItemManutPneu = itemManutPneuDAO.dadosEnvioItemManutPneu(itemManutPneuDAO.itemManutPneuIdBolList(idBoletimPneuArrayList));

        CarregCompDAO carregCompDAO = new CarregCompDAO();
        String dadosCarregComp = carregCompDAO.dadosEnvioCarreg(carregCompDAO.carregCompostoDescarregInsumo(idApontArrayList));

        RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
        String dadosEnvioRend = rendimentoMMDAO.dadosEnvioRendMM(rendimentoMMDAO.rendEnvioList(idBoletimArrayList));

        RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
        String dadosEnvioRecolh = recolhimentoFertDAO.dadosEnvioRecolh(recolhimentoFertDAO.recolhEnvioList(idBoletimArrayList));

        idBoletimArrayList.clear();
        idApontArrayList.clear();

        return dadosEnvioBoletim + "_" + dadosEnvioApont + "_" + dadosEnvioApontImpl + "_" + dadosEnvioMovLeira + "_" + dadosEnvioApontMecan + "_" + dadosEnvioBoletimPneu + "_" + dadosEnvioItemMedPneu + "_" + dadosEnvioItemManutPneu + "_" + dadosCarregComp + "_" + dadosEnvioRend + "_" + dadosEnvioRecolh;
    }

    public void updateBolAberto(String result, String activity){

        try {

            String[] retorno = result.split("_");

            BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
            boletimMMFertDAO.updateBoletimMMFertAberto(retorno[1]);

            ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
            ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(retorno[2]);
            apontMMFertDAO.updateApont(idApontArrayList);

            ImplementoMMDAO implementoMMDAO = new ImplementoMMDAO();
            ArrayList<Long> idApontImplArrayList = implementoMMDAO.idApontImplArrayList(retorno[3]);
            implementoMMDAO.updateApontImpl(idApontImplArrayList);

            BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
            ArrayList<Long> idBoletimPneuArrayList = boletimPneuDAO.idBolPneuArrayList(retorno[4]);
            boletimPneuDAO.updateBolPneu(idBoletimPneuArrayList);

            CarregCompDAO carregCompDAO = new CarregCompDAO();
            ArrayList<Long> idCarregArrayList = carregCompDAO.idCarregArrayList(retorno[5]);
            carregCompDAO.updDescarregInsumoCarregComposto(idCarregArrayList);

            LeiraDAO leiraDAO = new LeiraDAO();
            ArrayList<Long> idMovLeiraArrayList = leiraDAO.idMovLeiraArrayList(retorno[6]);
            leiraDAO.updateMovLeira(idMovLeiraArrayList);

            ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
            ArrayList<Long> idApontMecanArrayList = apontMecanDAO.idApontMecanArrayList(retorno[7]);
            apontMecanDAO.updateApontMecan(idApontMecanArrayList);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void updateBolFechado(String result, String activity){

        try {

            String[] retorno = result.split("_");

            BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
            ArrayList<BoletimMMFertBean> boletimArrayList = boletimMMFertDAO.boletimMMFertArrayList(retorno[1]);
            boletimMMFertDAO.updateBoletimMMFertEnvio(boletimArrayList);

            ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
            ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(retorno[2]);
            apontMMFertDAO.updateApont(idApontArrayList);

            ImplementoMMDAO implementoMMDAO = new ImplementoMMDAO();
            ArrayList<Long> idApontImplArrayList = implementoMMDAO.idApontImplArrayList(retorno[3]);
            implementoMMDAO.updateApontImpl(idApontImplArrayList);

            BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
            ArrayList<Long> idBoletimPneuArrayList = boletimPneuDAO.idBolPneuArrayList(retorno[4]);
            boletimPneuDAO.updateBolPneu(idBoletimPneuArrayList);

            CarregCompDAO carregCompDAO = new CarregCompDAO();
            ArrayList<Long> idCarregArrayList = carregCompDAO.idCarregArrayList(retorno[5]);
            carregCompDAO.updDescarregInsumoCarregComposto(idCarregArrayList);

            LeiraDAO leiraDAO = new LeiraDAO();
            ArrayList<Long> idMovLeiraArrayList = leiraDAO.idMovLeiraArrayList(retorno[6]);
            leiraDAO.updateMovLeira(idMovLeiraArrayList);

            ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
            ArrayList<Long> idApontMecanArrayList = apontMecanDAO.idApontMecanArrayList(retorno[7]);
            apontMecanDAO.updateApontMecan(idApontMecanArrayList);

            RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
            ArrayList<Long> idRendArrayList = rendimentoMMDAO.idRendArrayList(retorno[8]);
            rendimentoMMDAO.updateRend(idRendArrayList);

            RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
            ArrayList<Long> idRecolhArrayList = recolhimentoFertDAO.idRecolhArrayList(retorno[9]);
            recolhimentoFertDAO.updateRecolh(idRecolhArrayList);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void deleteBolEnviado(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ArrayList<BoletimMMFertBean> boletimMMFertArrayList = boletimMMFertDAO.boletimExcluirArrayList();

        for (BoletimMMFertBean boletimMMFertBean : boletimMMFertArrayList) {

            ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
            ArrayList<Long> idApontArrayList = apontMMFertDAO.idApontArrayList(apontMMFertDAO.apontMMFertList(boletimMMFertBean.getIdBolMMFert()));
            apontMMFertDAO.deleteApont(idApontArrayList);

            ImplementoMMDAO implementoMMDAO = new ImplementoMMDAO();
            implementoMMDAO.deleteApontImpl(idApontArrayList);

            BoletimPneuDAO boletimPneuDAO = new BoletimPneuDAO();
            ItemCalibPneuDAO itemCalibPneuDAO = new ItemCalibPneuDAO();
            itemCalibPneuDAO.deleteItemMedPneu(boletimPneuDAO.idBolPneuArrayList(boletimPneuDAO.bolPneuList(boletimMMFertBean.getIdBolMMFert())));
            ItemManutPneuDAO itemManutPneuDAO = new ItemManutPneuDAO();
            itemManutPneuDAO.deleteItemManutPneu(boletimPneuDAO.idBolPneuArrayList(boletimPneuDAO.bolPneuList(boletimMMFertBean.getIdBolMMFert())));
            boletimPneuDAO.deleteBolPneu(boletimMMFertBean.getIdBolMMFert());

            CarregCompDAO carregCompDAO = new CarregCompDAO();
            carregCompDAO.deleteCarreg(idApontArrayList);

            idApontArrayList.clear();

            LeiraDAO leiraDAO = new LeiraDAO();
            leiraDAO.deleteMovLeira(boletimMMFertBean.getIdBolMMFert());

            ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
            apontMecanDAO.deleteApontMecan(boletimMMFertBean.getIdBolMMFert());

            RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
            rendimentoMMDAO.deleteRend(boletimMMFertBean.getIdBolMMFert());

            RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
            recolhimentoFertDAO.deleteRecolh(boletimMMFertBean.getIdBolMMFert());

            boletimMMFertDAO.deleteBoletimMMFert(boletimMMFertBean.getIdBolMMFert());

        }

        boletimMMFertArrayList.clear();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// ATIVIDADES ///////////////////////////////////////////////

    public AtividadeBean getAtividade(Long idAtiv){
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        return atividadeDAO.getAtividade(idAtiv);
    }

    public ArrayList getAtivArrayList(Long nroOS, String activity){
        ConfigCTR configCTR = new ConfigCTR();
        OSDAO osDAO = new OSDAO();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        Long idOS = 0L;
        if(osDAO.verOS(nroOS)){
            idOS = osDAO.getOS(nroOS).getIdOS();
        }
        LogProcessoDAO.getInstance().insertLogProcesso("Long idOS = 0L;\n" +
                "        if(osDAO.verOS(nroOS)){\n" +
                "            idOS = osDAO.getOS(nroOS).getIdOS();\n" +
                "        }\n" +
                "        return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), idOS);", activity);
        return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), idOS);
    }

    public List<RFuncaoAtivParBean> getFuncaoAtividadeList(String activity){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        ConfigCTR configCTR = new ConfigCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("return rFuncaoAtivParDAO.getListFuncaoAtividade(" + configCTR.getConfig().getIdAtivConfig() + ");", activity);
        return rFuncaoAtivParDAO.getListFuncaoAtividade(configCTR.getConfig().getIdAtivConfig());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////// PARADA //////////////////////////////////////////////

    public ParadaBean getParada(Long idParada){
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getParada(idParada);
    }

    public List<ParadaBean> getListParada(){
        ConfigCTR configCTR = new ConfigCTR();
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getListParada(configCTR.getConfig().getIdAtivConfig());
    }

    public ParadaBean getParadaBean(String paradaString){
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getParadaBean(paradaString);
    }

    public List<RFuncaoAtivParBean> getFuncaoParadaList(Long idParada, String activity){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("rFuncaoAtivParDAO.getListFuncaoParada(" + idParada + ");", activity);
        return rFuncaoAtivParDAO.getListFuncaoParada(idParada);
    }

    public boolean verParadaCalibragem(){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        List<ParadaBean> listParada = getListParada();
        boolean calibragem = false;
        for (ParadaBean paradaBean : listParada) {
            if (rFuncaoAtivParDAO.verParadaCalibragem(paradaBean.getIdParada())) {
                calibragem = true;
                break;
            }
        }
        listParada.clear();
        return calibragem;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////// RENDIMENTO ////////////////////////////////////////

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
        RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("rendMMDAO.insRend(getBoletimMMFertAberto().getIdBolMMFert(), nroOS, activity);", activity);
        rendimentoMMDAO.insRend(getBoletimMMFertAberto().getIdBolMMFert(), nroOS, activity);
    }

    public boolean verRendMM(){
        RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
        return rendimentoMMDAO.verRend(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public RendMMBean getRend(int pos){
        RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
        return rendimentoMMDAO.getRend(getBoletimMMFertAberto().getIdBolMMFert(), pos);
    }

    public void atualRend(RendMMBean rendMMBean){
        RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
        rendimentoMMDAO.atualRend(rendMMBean);
    }

    public int qtdeRend(){
        RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
        return rendimentoMMDAO.qtdeRend(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public List<RendMMBean> rendList(){
        RendimentoMMDAO rendimentoMMDAO = new RendimentoMMDAO();
        return rendimentoMMDAO.rendList(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public Double rendOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        return osDAO.rendOS(nroOS);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// IMPLEMENTO //////////////////////////////////////////////////

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
        ImplementoMMDAO implementoMMDAO = new ImplementoMMDAO();
        implementoMMDAO.implementoMMDelAll();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// TRANSBORDO //////////////////////////////////////////////////

    public boolean verMotoBomba(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verMotoBomba(nroEquip);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// LEIRA /////////////////////////////////////////////////////

    public void inserirMovLeira(Long idLeira, Long tipo){
        BoletimMMFertBean boletimMMFertBean = getBoletimMMFertAberto();
        LeiraDAO leiraDAO = new LeiraDAO();
        leiraDAO.inserirMovLeira(idLeira, tipo, boletimMMFertBean.getIdBolMMFert(), boletimMMFertBean.getIdExtBolMMFert());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// MOTOBOMBA ///////////////////////////////////////////////////

    public boolean verTransb(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verTransb(nroEquip);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// RECOLHIMENTO ////////////////////////////////////////////////

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
        RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("recolhFertDAO.insRecolh(" + getBoletimMMFertAberto().getIdBolMMFert() + ", " + configCTR.getConfig().getNroOSConfig() + ");", activity);
        recolhimentoFertDAO.insRecolh(getBoletimMMFertAberto().getIdBolMMFert(), configCTR.getConfig().getNroOSConfig(), activity);
    }

    public boolean verRecolh(){
        RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
        return recolhimentoFertDAO.verRecolh(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public RecolhFertBean getRecolh(int pos) {
        RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
        return recolhimentoFertDAO.getRecolh(getBoletimMMFertAberto().getIdBolMMFert(), pos);
    }

    public void atualRecolh(RecolhFertBean recolhFertBean){
        RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
        recolhimentoFertDAO.atualRecolh(recolhFertBean);
    }

    public int qtdeRecolh(){
        RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
        return recolhimentoFertDAO.qtdeRecolh(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public List<RecolhFertBean> recolhList(){
        RecolhimentoFertDAO recolhimentoFertDAO = new RecolhimentoFertDAO();
        return recolhimentoFertDAO.recolhList(getBoletimMMFertAberto().getIdBolMMFert());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// EQUIP SEG //////////////////////////////////////////////////

    public EquipSegBean getEquipSeg(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.getEquipSeg(nroEquip);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// TURNO /////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////// BOCAL /////////////////////////////////////////

    public BocalBean getBocal(Long idBocal){
        BocalDAO bocalDAO = new BocalDAO();
        return bocalDAO.getBocal(idBocal);
    }

    public List<BocalBean> bocalList(){
        BocalDAO bocalDAO = new BocalDAO();
        return bocalDAO.bocalList();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////// PRESSAO ///////////////////////////////////////

    public List<PressaoBocalBean> pressaoBocalList(){
        ConfigCTR configCTR = new ConfigCTR();
        PressaoFertDAO pressaoFertDAO = new PressaoFertDAO();
        return pressaoFertDAO.pressaoBocalList(configCTR.getConfig().getBocalConfig());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////// VELOCIDADE //////////////////////////////////////

    public ArrayList<String> velocArrayList() {
        ConfigCTR configCTR = new ConfigCTR();
        PressaoFertDAO pressaoFertDAO = new PressaoFertDAO();
        return pressaoFertDAO.velocArrayList(configCTR.getConfig().getBocalConfig(), configCTR.getConfig().getPressaoConfig());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// CARRETA ////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////// MOTOMEC ////////////////////////////////////////////

    public void setMotoMecBean(MotoMecBean motoMecBean) {
        this.motoMecBean = motoMecBean;
    }

    public List<MotoMecBean> motoMecList() {
        ConfigCTR configCTR = new ConfigCTR();
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.motoMecList(BuildConfig.FLAVOR.equals("ecm") ? 1L : configCTR.getConfig().getFuncaoComposto());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////

    public void atualDados(String tipoAtual, int tipoReceb, String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = classeAtual(tipoAtual, activity);\n" +
                "        AtualDadosServ.getInstance().atualGenericoBD(classeArrayList, tipoReceb, activity);", activity);
        ArrayList classeArrayList = classeAtual(tipoAtual, activity);
        AtualDadosServ.getInstance().atualGenericoBD(classeArrayList, tipoReceb, activity);
    }

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual, int tipoReceb, String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = classeAtual(tipoAtual, activity);\n" +
                "        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);", activity);
        ArrayList classeArrayList = classeAtual(tipoAtual, activity);
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);
    }

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual, int tipoReceb, String activity, Class telaProxAlt, String dado) {
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = classeAtual(tipoAtual, activity);\n" +
                "        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity, telaProxAlt, dado);", activity);
        ArrayList classeArrayList = classeAtual(tipoAtual, activity);
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity, telaProxAlt, dado);
    }

    public ArrayList<String> classeAtual(String tipoAtual, String activity){
        ArrayList<String> classeArrayList = new ArrayList();
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
                classeArrayList.add("OSBean");
                classeArrayList.add("PropriedadeBean");
                break;
            case "OS":
                classeArrayList.add("OSBean");
                classeArrayList.add("AtividadeBean");
                classeArrayList.add("EquipSegBean");
                classeArrayList.add("FrenteBean");
                classeArrayList.add("MotoMecBean");
                classeArrayList.add("PropriedadeBean");
                break;
            case "EquipPneu":
                classeArrayList.add("EquipPneuBean");
                break;
        }
        return classeArrayList;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String nroOS, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        OSDAO osDAO = new OSDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        osDAO.verOS(atualAplicDAO.getAtualNroOS(Long.parseLong(nroOS)), telaAtual, telaProx, progressDialog, activity);
    }

    public void verAtiv(Long nroOS, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        atividadeDAO.verAtiv(atualAplicDAO.getAtualNroOSIdEquip(nroOS, configCTR.getEquip().getIdEquip()), telaAtual, telaProx, progressDialog);
    }

    public void verAtivECM(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        atividadeDAO.verAtivECM(atualAplicDAO.getAtualIdEquip(configCTR.getEquip().getIdEquip()), telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////APONTAMENTO /////////////////////////////////////////////

    public List<ApontMMFertBean> apontList(){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.apontMMFertList(getBoletimMMFertAberto().getIdBolMMFert());
    }

    public ApontMMFertBean getApontAberto(Long idBol){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.getApontAberto(idBol);
    }

    public String getUltApont(){
        String retorno = "STATUS: ";
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        if(apontMMFertDAO.hasApontBol(getBoletimMMFertAberto().getIdBolMMFert())){
            List<ApontMMFertBean> apontMMFertList = apontMMFertDAO.apontMMFertList(getBoletimMMFertAberto().getIdBolMMFert());
            Long idMotoMec = 0L;
            for(ApontMMFertBean apontMMFertBean : apontMMFertList){
                if(apontMMFertBean.getIdMotoMec() > 0L){
                    idMotoMec = apontMMFertBean.getIdMotoMec();
                }
            }
            if(idMotoMec > 0L){
                MotoMecDAO motoMecDAO = new MotoMecDAO();
                retorno = retorno + motoMecDAO.getMotoMec(idMotoMec).getDescrOperMotoMec();
            }
        }
        return retorno;
    }

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
        if(!verifBackupApont(rFuncaoAtivParDAO.idParadaCheckList())){
            salvarApont(rFuncaoAtivParDAO.idParadaCheckList(), 0L, activity);
        }
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
        if(BuildConfig.FLAVOR.equals("ecm") && (motoMecBean != null)){
            apontMMFertBean.setIdMotoMec(motoMecBean.getIdMotoMec());
        }
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
        apontMMFertDAO.salvarApont(apontMMFertBean, 1, 2L);
        LogProcessoDAO.getInstance().insertLogProcesso("salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);
    }

    private void salvarApont(Long idParada, Long idTransb, String activity){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ApontMMFertBean apontMMFertBean = apontMMFertDefault();
        if(BuildConfig.FLAVOR.equals("ecm") && (motoMecBean != null)){
            apontMMFertBean.setIdMotoMec(motoMecBean.getIdMotoMec());
        }
        apontMMFertBean.setParadaApontMMFert(idParada);
        apontMMFertBean.setTransbApontMMFert(idTransb);
        LogProcessoDAO.getInstance().insertLogProcesso("private void salvarApont(Long idParada, Long idTransb, String activity){\n" +
                "        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();\n" +
                "        ApontMMFertBean apontMMFertBean = apontMMFertDefault();\n" +
                "        apontMMFertBean.setParadaApontMMFert(idParada);\n" +
                "        apontMMFertBean.setTransbApontMMFert(idTransb);\n" +
                "        apontMMFertDAO.salvarApont(apontMMFertBean, 1);", activity);
        apontMMFertDAO.salvarApont(apontMMFertBean, 1, 2L);
        LogProcessoDAO.getInstance().insertLogProcesso("salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);
    }

    public void salvarApont(Long idParada, Long idTransb, Double longitude, Double latitude, String activity){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ApontMMFertBean apontMMFertBean = apontMMFertDefault();
        if(BuildConfig.FLAVOR.equals("ecm") && (motoMecBean != null)){
            apontMMFertBean.setIdMotoMec(motoMecBean.getIdMotoMec());
        }
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
        apontMMFertDAO.salvarApont(apontMMFertBean, 2, 2L);
        LogProcessoDAO.getInstance().insertLogProcesso("salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);
    }

    public void salvarParadaCalibragem(Long idParada, Long idTransb, Double longitude, Double latitude, String activity){
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
        apontMMFertDAO.salvarApont(apontMMFertBean, 2, 1L);
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
        apontMMFertDAO.salvarApont(apontMMFertBean, 2, 2L);

        LogProcessoDAO.getInstance().insertLogProcesso("salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);

    }

    public void salvarApontLeira(Double longitude, Double latitude, String activity){

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
        apontMMFertDAO.salvarApont(apontMMFertBean, 2, 1L);

        LogProcessoDAO.getInstance().insertLogProcesso("salvarAltCompApont(Tempo.getInstance().dthrLongToString(dthrLong), activity);", activity);
        salvarAltCompApont(Tempo.getInstance().dthrLongToString(apontMMFertBean.getDthrApontLongMMFert()), activity);

    }

    private void salvarAltCompApont(String dthr, String activity){

        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        ImplementoMMDAO implementoMMDAO = new ImplementoMMDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("impleMMDAO.salvarApontImple(apontMMFertDAO.getApontDthr(dthr).getIdApontMMFert(), dthr);", activity);
        implementoMMDAO.salvarApontImpl(apontMMFertDAO.getApontDthr(dthr).getIdApontMMFert(), dthr, activity);

        LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().envioDados(activity);", activity);
        EnvioDadosServ.getInstance().envioDados(activity);

    }

    private ApontMMFertBean apontMMFertDefault(){
        Long dthrLong = Tempo.getInstance().dthrAtualLong();
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

    public void fecharApont(String activity){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        apontMMFertDAO.fecharApont(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert());

        LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().envioDados(activity);", activity);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO APONT /////////////////////////////////////////////

    public boolean hasApontBolAberto(){
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return apontMMDAO.hasApontBol(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert());
    }

    public boolean verUltApontAtiv() {
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return apontMMDAO.verUltApontAtiv(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert());
    }

    public boolean verifBackupApont() {
        BoletimMMFertDAO boletimMMDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.verifBackupApont(boletimMMDAO.getBoletimMMFertAberto().getIdBolMMFert(), motoMecBean.getIdMotoMec());
    }

    public boolean verifBackupApont(Long idParada) {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertDAO boletimMMDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.verifBackupApont(boletimMMDAO.getBoletimMMFertAberto().getIdBolMMFert(), configCTR.getConfig().getNroOSConfig()
                                                , configCTR.getConfig().getIdAtivConfig(), idParada);
    }

    public boolean verifBackupApontTransb(Long idParada, Long idTransb) {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertDAO boletimMMDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        return apontMMDAO.verifBackupApontTransb(boletimMMDAO.getBoletimMMFertAberto().getIdBolMMFert(), configCTR.getConfig().getNroOSConfig()
                                                    , configCTR.getConfig().getIdAtivConfig(), idParada, idTransb);
    }

    public int verTrocaTransb(){
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return apontMMDAO.verTransbordo(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert());
    }

    public boolean verDataHoraInsApontMMFert(){
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return apontMMDAO.verDataHoraApont(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert());
    }

    public boolean verDataHoraInsMovLeira(){
        LeiraDAO leiraDAO = new LeiraDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return leiraDAO.verDataHoraMovLeira(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
