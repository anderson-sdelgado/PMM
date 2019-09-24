package br.com.usinasantafe.pmm.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.RecolhFertTO;


public class BoletimFertDAO {

    public BoletimFertDAO() {
    }

    public boolean verBolFertAberto(){
        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.get("statusBolFert", 1L);
        Boolean ret = (boletimFertList.size() > 0);
        boletimFertList.clear();
        return ret;
    }

    public void atualQtdeApontBol(){
        BoletimFertTO boletimFertTO = getBolFertAberto();
        boletimFertTO.setQtdeApontBolFert(boletimFertTO.getQtdeApontBolFert() + 1L);
        boletimFertTO.update();
    }

    public BoletimFertTO getBolFertAberto(){
        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.get("statusBolFert", 1L);
        boletimFertTO = (BoletimFertTO) boletimFertList.get(0);
        boletimFertList.clear();
        return boletimFertTO;
    }

    public Long getIdBolFertAberto(){
        BoletimFertTO boletimFertTO = getBolFertAberto();
        return boletimFertTO.getIdBolFert();
    }

    public boolean verRecolh(){
        RecolhFertTO recolhFertTO = new RecolhFertTO();
        List recolhList = recolhFertTO.get("idBolRecolhFert", getBolFertAberto().getIdBolFert());
        Boolean ret = (recolhList.size() > 0);
        recolhList.clear();
        return ret;
    }

    public RecolhFertTO getRecolh(int pos){
        RecolhFertTO recolhFertTO = new RecolhFertTO();
        List recolhFertList = recolhFertTO.getAndOrderBy("idBolRecolhFert", getBolFertAberto().getIdBolFert(), "idRecolhFert", true);
        recolhFertTO = (RecolhFertTO) recolhFertList.get(pos);
        recolhFertList.clear();
        return recolhFertTO;
    }

    public int qtdeRecolh(){
        RecolhFertTO recolhFertTO = new RecolhFertTO();
        List recolhList = recolhFertTO.get("idBolRecolhFert", getBolFertAberto().getIdBolFert());
        return recolhList.size();
    }

    public void atualRecolh(RecolhFertTO recolhFertTO){
        recolhFertTO.setDthrRecolhFert(Tempo.getInstance().dataComHora());
        recolhFertTO.update();
        recolhFertTO.commit();
    }

    public void salvarBolAberto(BoletimFertTO boletimFertTO){

        boletimFertTO.setStatusBolFert(1L);
        boletimFertTO.setDthrInicialBolFert(Tempo.getInstance().dataComHora());

        String dataComHora = Tempo.getInstance().dataComHora();

        CheckListCTR checkListCTR = new CheckListCTR();
        if(checkListCTR.verAberturaCheckList(boletimFertTO.getIdTurnoBolFert())) {

            boletimFertTO.setQtdeApontBolFert(1L);

            BoletimCTR boletimCTR = new BoletimCTR();

            ApontFertTO apontFertTO = new ApontFertTO();
            apontFertTO.setDthrApontFert(dataComHora);
            apontFertTO.setIdBolApontFert(boletimFertTO.getIdBolFert());
            apontFertTO.setIdExtBolApontFert(boletimFertTO.getIdExtBolFert());
            apontFertTO.setOsApontFert(boletimFertTO.getOsBolFert());
            apontFertTO.setAtivApontFert(boletimFertTO.getAtivPrincBolFert());
            apontFertTO.setParadaApontFert(boletimCTR.getIdParadaCheckList());
            apontFertTO.setLatitudeApontFert(0D);
            apontFertTO.setLongitudeApontFert(0D);
            apontFertTO.setStatusConApontFert(boletimFertTO.getStatusConBolFert());
            apontFertTO.setStatusApontFert(1L);
            apontFertTO.insert();

            ConfigCTR configCTR = new ConfigCTR();
            configCTR.atualCheckListConfig(boletimCTR.getTurno(), dataComHora);

        }else{

            boletimFertTO.setQtdeApontBolFert(0L);

        }

        boletimFertTO.insert();

    }

    public void salvarBolFechado() {

        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.get("statusBolFert", 1L);
        boletimFertTO = (BoletimFertTO) boletimFertList.get(0);
        boletimFertList.clear();

        boletimFertTO.setDthrFinalBolFert(Tempo.getInstance().dataComHora());
        boletimFertTO.setStatusBolFert(2L);
        boletimFertTO.update();

    }

    public List bolFechList() {
        BoletimFertTO boletimFertTO = new BoletimFertTO();
        return boletimFertTO.get("statusBolFert", 2L);
    }

    public List bolAbertoSemEnvioList() {

        BoletimFertTO boletimFertTO = new BoletimFertTO();

        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBolFert");
        pesquisa2.setValor(0);
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        return boletimFertTO.get(pesqList);

    }

}
