package br.com.usinasantafe.pmm.control;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bean.estaticas.EquipTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.dao.AtividadeDAO;
import br.com.usinasantafe.pmm.dao.CabecPneuDAO;
import br.com.usinasantafe.pmm.dao.EquipDAO;
import br.com.usinasantafe.pmm.dao.ItemPneuDAO;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.dao.ApontFertDAO;
import br.com.usinasantafe.pmm.dao.ApontMMDAO;
import br.com.usinasantafe.pmm.dao.BoletimFertDAO;
import br.com.usinasantafe.pmm.dao.BoletimMMDAO;
import br.com.usinasantafe.pmm.dao.MovLeiraDAO;
import br.com.usinasantafe.pmm.bean.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.bean.estaticas.RAtivParadaTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontMMTO;

public class ApontCTR {

    private ApontMMTO apontMMTO;
    private ApontFertTO apontFertTO;
    private int tipoEquip = 0;

    public ApontCTR() {
        if (apontMMTO == null)
            apontMMTO = new ApontMMTO();
        if (apontFertTO == null)
            apontFertTO = new ApontFertTO();
    }

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

    public void setOSApont(Long os){
        if(tipoEquip == 1) {
            apontMMTO.setOsApontMM(os);
        }
        else{
            apontFertTO.setOsApontFert(os);
        }
    }

    public void setAtivApont(Long ativ){
        ConfigCTR configCTR = new ConfigCTR();
        if(tipoEquip == 1) {
            apontMMTO.setAtivApontMM(ativ);
            apontMMTO.setStatusConApontMM(configCTR.getConfig().getStatusConConfig());
            apontMMTO.setParadaApontMM(0L);
            apontMMTO.setTransbApontMM(0L);
        }
        else{
            apontFertTO.setAtivApontFert(ativ);
            apontFertTO.setStatusConApontFert(configCTR.getConfig().getStatusConConfig());
            apontFertTO.setParadaApontFert(0L);
        }
    }

    public void setParadaApont(Long parada){
        if(tipoEquip == 1) {
            apontMMTO.setParadaApontMM(parada);
        }
        else{
            apontFertTO.setBocalApontFert(0L);
            apontFertTO.setPressaoApontFert(0D);
            apontFertTO.setVelocApontFert(0L);
            apontFertTO.setParadaApontFert(parada);
        }
    }

    public void setTransb(Long transb){
        apontMMTO.setTransbApontMM(transb);
    }

    public void setBocal(Long bocal){
        apontFertTO.setBocalApontFert(bocal);
    }

    public void setPressaoBocal(Double pressaoBocal){
        apontFertTO.setPressaoApontFert(pressaoBocal);
    }

    public void setVelocApont(Long velocApont){
        apontFertTO.setVelocApontFert(velocApont);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public Long getAtivApont(){
        if(tipoEquip == 1) {
            return apontMMTO.getAtivApontMM();
        }
        else{
            return apontFertTO.getAtivApontFert();
        }
    }

    public Long getBocalApontFert(){
        return apontFertTO.getBocalApontFert();
    }

    public Double getPressaoApontFert(){
        return apontFertTO.getPressaoApontFert();
    }

    public List getListParada(){
        Long ativ;
        if(tipoEquip == 1) {
            ativ = apontMMTO.getAtivApontMM();
        }
        else{
            ativ = apontFertTO.getAtivApontFert();
        }
        RAtivParadaTO rAtivParadaTO = new RAtivParadaTO();
        List rAtivParadaList = rAtivParadaTO.get("idAtiv", ativ);
        ArrayList<Long> rLista = new ArrayList<Long>();
        for (int i = 0; i < rAtivParadaList.size(); i++) {
            rAtivParadaTO = (RAtivParadaTO) rAtivParadaList.get(i);
            rLista.add(rAtivParadaTO.getIdParada());
        }
        rAtivParadaList.clear();
        ParadaTO paradaTO = new ParadaTO();
        return paradaTO.inAndOrderBy("idParada", rLista, "codParada", true);
    }

    public Long getIdApont(){
        if(tipoEquip == 1) {
            ApontMMDAO apontMMDAO = new ApontMMDAO();
            return apontMMDAO.getIdApontAberto();
        }
        else{
            ApontFertDAO apontFertDAO = new ApontFertDAO();
            return apontFertDAO.getIdApontAberto();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// CRIARE ATUALIZAR APONTAMENTO ////////////////////////////////////

    private ApontMMTO createApontMM(BoletimCTR boletimCTR){
        ApontMMTO apontMMTO = new ApontMMTO();
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        List apontList = apontMMDAO.getListAllApont(boletimCTR.getIdBol());
        if (apontList.size() == 0) {
            apontMMTO.setIdBolApontMM(boletimCTR.getIdBol());
            apontMMTO.setIdExtBolApontMM(boletimCTR.getIdExtBol());
            apontMMTO.setOsApontMM(boletimCTR.getOS());
            apontMMTO.setAtivApontMM(boletimCTR.getAtiv());
            apontMMTO.setParadaApontMM(0L);
            apontMMTO.setDthrApontMM(Tempo.getInstance().dataComHora());
            apontMMTO.setStatusConApontMM(boletimCTR.getStatusConBol());
            apontMMTO.setStatusApontMM(1L);
            apontMMTO.setLongitudeApontMM(boletimCTR.getLongitude());
            apontMMTO.setLatitudeApontMM(boletimCTR.getLatitude());
        } else {
            ApontMMTO ultApontTO = (ApontMMTO) apontList.get(apontList.size() - 1);
            apontMMTO = ultApontTO;
            apontMMTO.setStatusApontMM(1L);
        }
        apontMMTO.setTransbApontMM(0L);
        apontList.clear();
        return apontMMTO;
    }

    private ApontFertTO createApontFert(BoletimCTR boletimCTR){
        ApontFertTO apontFertTO = new ApontFertTO();
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        List apontList = apontFertDAO.getListAllApont(boletimCTR.getIdBol());
        if (apontList.size() == 0) {
            apontFertTO.setIdBolApontFert(boletimCTR.getIdBol());
            apontFertTO.setIdExtBolApontFert(boletimCTR.getIdExtBol());
            apontFertTO.setOsApontFert(boletimCTR.getIdExtBol());
            apontFertTO.setAtivApontFert(boletimCTR.getAtiv());
            apontFertTO.setParadaApontFert(0L);
            apontFertTO.setDthrApontFert(Tempo.getInstance().dataComHora());
            apontFertTO.setStatusConApontFert(boletimCTR.getStatusConBol());
            apontFertTO.setStatusApontFert(1L);
            apontFertTO.setLongitudeApontFert(boletimCTR.getLongitude());
            apontFertTO.setLatitudeApontFert(boletimCTR.getLatitude());
        } else {
            ApontFertTO ultApontTO = (ApontFertTO) apontList.get(apontList.size() - 1);
            apontFertTO = ultApontTO;
            apontFertTO.setStatusApontFert(1L);
        }
        apontFertTO.setPressaoApontFert(0D);
        apontFertTO.setVelocApontFert(0L);
        apontFertTO.setBocalApontFert(0L);
        apontList.clear();
        return apontFertTO;
    }


    public void salvarApont(Long status, Double longitude, Double latitude){
        BoletimCTR boletimCTR = new BoletimCTR();
        Long func = 0L;
        Long equip = 0L;
        if(tipoEquip == 1) {
            BoletimMMTO boletimMMTO = boletimCTR.getBolMMAberto();
            apontMMTO.setIdBolApontMM(boletimMMTO.getIdBolMM());
            apontMMTO.setIdExtBolApontMM(boletimMMTO.getIdExtBolMM());
            apontMMTO.setStatusApontMM(status);
            apontMMTO.setLongitudeApontMM(longitude);
            apontMMTO.setLatitudeApontMM(latitude);
            apontMMTO.setDthrApontMM(Tempo.getInstance().dataComHora());
            salvarApontMM(apontMMTO);
            func = boletimMMTO.getMatricFuncBolMM();
            equip = boletimMMTO.getIdEquipBolMM();
        }
        else{
            BoletimFertTO boletimFertTO = boletimCTR.getBolFertAberto();
            apontFertTO.setIdBolApontFert(boletimFertTO.getIdBolFert());
            apontFertTO.setIdExtBolApontFert(boletimFertTO.getIdExtBolFert());
            apontFertTO.setStatusApontFert(status);
            apontFertTO.setLongitudeApontFert(longitude);
            apontFertTO.setLatitudeApontFert(latitude);
            apontFertTO.setDthrApontFert(Tempo.getInstance().dataComHora());
            salvarApontFert(apontFertTO);
            func = boletimFertTO.getMatricFuncBolFert();
            equip = boletimFertTO.getIdEquipBolFert();
        }
        if(status == 0L){
            CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
            cabecPneuDAO.salvarDados(func, equip, getIdApont());
        }
    }

    public void atualApont(){
        if(tipoEquip == 1) {
            ApontMMDAO apontMMDAO = new ApontMMDAO();
            ApontMMTO apontMMTO = apontMMDAO.getApontMMAberto();
            apontMMDAO.updApont(apontMMTO);
        }
        else{
            ApontFertDAO apontFertDAO = new ApontFertDAO();
            ApontFertTO apontFertTO = apontFertDAO.getApontFertAberto();
            apontFertDAO.updApont(apontFertTO);
        }
    }

    private void salvarApontMM(ApontMMTO apontMMTO){

        BoletimCTR boletimCTR = new BoletimCTR();
        boletimCTR.atualQtdeApontBol();

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        apontMMDAO.salvarApont(apontMMTO);

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora());
    }

    private void salvarApontFert(ApontFertTO apontFertTO){

        BoletimCTR boletimCTR = new BoletimCTR();
        boletimCTR.atualQtdeApontBol();

        ApontFertDAO apontFertDAO = new ApontFertDAO();
        apontFertDAO.salvarApont(apontFertTO);

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora());
    }

    public void inserirParadaImplemento(BoletimCTR boletimCTR){
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        if(tipoEquip == 1) {
            ApontMMTO apontMMTO = createApontMM(boletimCTR);
            apontMMTO.setDthrApontMM(Tempo.getInstance().dataComHora());
            apontMMTO.setParadaApontMM(atividadeDAO.idParadaImplemento());
            salvarApontMM(apontMMTO);
        }
        else{
            ApontFertTO apontFertTO = createApontFert(boletimCTR);
            apontFertTO.setDthrApontFert(Tempo.getInstance().dataComHora());
            apontFertTO.setParadaApontFert(atividadeDAO.idParadaImplemento());
            salvarApontFert(apontFertTO);
        }

    }

    public void inserirParadaCheckList(BoletimCTR boletimCTR){
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        if(tipoEquip == 1) {
            ApontMMTO apontMMTO = createApontMM(boletimCTR);
            apontMMTO.setDthrApontMM(Tempo.getInstance().dataComHora());
            apontMMTO.setParadaApontMM(atividadeDAO.idParadaCheckList());
            salvarApontMM(apontMMTO);
        }
        else{
            ApontFertTO apontFertTO = createApontFert(boletimCTR);
            apontFertTO.setDthrApontFert(Tempo.getInstance().dataComHora());
            apontFertTO.setParadaApontFert(atividadeDAO.idParadaCheckList());
            salvarApontFert(apontFertTO);
        }
    }

    public void inserirApontTransb(BoletimCTR boletimCTR){
        ApontMMTO apontMMTO = createApontMM(boletimCTR);
        apontMMTO.setDthrApontMM(Tempo.getInstance().dataComHora());
        apontMMTO.setTransbApontMM(this.apontMMTO.getTransbApontMM());
        salvarApontMM(apontMMTO);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO APONT ///////////////////////////////////////////

    public boolean verifBackupApont() {

        boolean v = false;
        ConfigCTR configCTR = new ConfigCTR();

        if(configCTR.getEquip().getTipo() == 1){
            BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
            ApontMMDAO apontMMDAO = new ApontMMDAO();
            List apontMMList = apontMMDAO.getListAllApont(boletimMMDAO.getIdBolAberto());
            if(apontMMList.size() > 0){
                ApontMMTO apontMMTO = (ApontMMTO) apontMMList.get(apontMMList.size() - 1);
                if ((this.apontMMTO.getOsApontMM().equals(apontMMTO.getOsApontMM()))
                        && (this.apontMMTO.getAtivApontMM().equals(apontMMTO.getAtivApontMM()))
                        && (this.apontMMTO.getParadaApontMM().equals(apontMMTO.getParadaApontMM()))) {
                    v = true;
                }
            }
            apontMMList.clear();
        }
        else{
            BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
            ApontFertDAO apontFertDAO = new ApontFertDAO();
            List apontFertList = apontFertDAO.getListAllApont(boletimFertDAO.getIdBolAberto());
            if(apontFertList.size() > 0){
                ApontFertTO apontFertTO = (ApontFertTO) apontFertList.get(apontFertList.size() - 1);
                if ((this.apontFertTO.getOsApontFert().equals(apontFertTO.getOsApontFert()))
                        && (this.apontFertTO.getAtivApontFert().equals(apontFertTO.getAtivApontFert()))
                        && (this.apontFertTO.getParadaApontFert().equals(apontFertTO.getParadaApontFert()))) {
                    v = true;
                }
            }
            apontFertList.clear();
        }
        return v;
    }

    public boolean verifBackupApontTransb() {
        boolean v = false;
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        List apontMMList = apontMMDAO.getListAllApont(boletimMMDAO.getIdBolAberto());
        if(apontMMList.size() > 0){
            ApontMMTO apontMMTO = (ApontMMTO) apontMMList.get(apontMMList.size() - 1);
            if ((this.apontMMTO.getOsApontMM().equals(apontMMTO.getOsApontMM()))
                    && (this.apontMMTO.getAtivApontMM().equals(apontMMTO.getAtivApontMM()))
                    && (this.apontMMTO.getParadaApontMM().equals(apontMMTO.getParadaApontMM()))
                    && (this.apontMMTO.getTransbApontMM().equals(apontMMTO.getTransbApontMM()))) {
                v = true;
            }
        }
        apontMMList.clear();
        return v;
    }

    public int verTrocaTransb(){
        ConfigCTR configCTR = new ConfigCTR();
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.verTransbordo(configCTR.getConfig().getDtUltApontConfig());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////// RETORNA TODOS APONTAMENTO PARA HISTORICO ////////////////////////////////

    public List getListAllApontHist(Long idBolAberto){
        if(tipoEquip == 1) {
            ApontMMDAO apontMMDAO = new ApontMMDAO();
            List apontMMList = apontMMDAO.getListAllApont(idBolAberto);
            return  apontMMList;
        }
        else{
            ApontFertDAO apontFertDAO = new ApontFertDAO();
            List apontFertList = apontFertDAO.getListAllApont(idBolAberto);
            return  apontFertList;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR APONT DADOS MOTOMEC ////////////////////////////////////

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public Boolean verEnvioDadosApontMM(){
        Boolean retorno = false;
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        MovLeiraDAO movLeiraDAO = new MovLeiraDAO();
        if((apontMMDAO.getListApontEnvio().size() > 0) || movLeiraDAO.getListMovLeiraAberto().size() > 0){
            retorno = true;
        }
        return retorno;
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioApontBolMM(Long idBol){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        MovLeiraDAO movLeiraDAO = new MovLeiraDAO();
        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio(idBol), movLeiraDAO.getListMovLeiraAberto(idBol));
    }

    public String dadosEnvioApontMM(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        MovLeiraDAO movLeiraDAO = new MovLeiraDAO();
        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio(), movLeiraDAO.getListMovLeiraAberto());
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updateApontMM(String retorno) {
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        apontMMDAO.updateApont(retorno);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR APONT DADOS MOTOMEC ////////////////////////////////////

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public boolean verEnvioDadosApontFert(){
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        return apontFertDAO.getListApontEnvio().size() > 0;
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioApontBolFert(Long idBol){
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        return apontFertDAO.dadosEnvioApontFert(apontFertDAO.getListApontEnvio(idBol));
    }

    public String dadosEnvioApontFert(){
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        return apontFertDAO.dadosEnvioApontFert(apontFertDAO.getListApontEnvio());
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updateApontaFert(String retorno) {
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        apontFertDAO.updateApont(retorno);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

}
