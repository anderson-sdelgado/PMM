package br.com.usinasantafe.pmm.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.control.ApontMMMovLeiraCTR;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.bean.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.MovLeiraTO;
import br.com.usinasantafe.pmm.bean.variaveis.RendMMTO;

public class BoletimMMDAO {

    public BoletimMMDAO() {
    }

    public boolean verBolMMAberto(){
        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = boletimMMTO.get("statusBolMM", 1L);
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public void atualQtdeApontBol(){
        BoletimMMTO boletimMMTO = getBolMMAberto();
        boletimMMTO.setQtdeApontBolMM(boletimMMTO.getQtdeApontBolMM() + 1L);
        boletimMMTO.update();
    }

    public BoletimMMTO getBolMMAberto(){
        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = boletimMMTO.get("statusBolMM", 1L);
        boletimMMTO = (BoletimMMTO) boletimMMList.get(0);
        boletimMMList.clear();
        return boletimMMTO;
    }

    public Long getIdBolMMAberto(){
        BoletimMMTO boletimMMTO = getBolMMAberto();
        return boletimMMTO.getIdBolMM();
    }

    public void insRend(Long nroOS){

        BoletimMMTO boletimMMTO = getBolMMAberto();

        RendMMTO rendMMTO = new RendMMTO();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("idBolRendMM");
        pesq1.setValor(boletimMMTO.getIdBolMM());
        pesq1.setTipo(1);
        listaPesq.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("nroOSRendMM");
        pesq2.setValor(nroOS);
        pesq2.setTipo(1);
        listaPesq.add(pesq2);

        List rendList = rendMMTO.get(listaPesq);

        if (rendList.size() == 0) {
            rendMMTO.setIdBolRendMM(boletimMMTO.getIdBolMM());
            rendMMTO.setIdExtBolRendMM(boletimMMTO.getIdExtBolMM());
            rendMMTO.setNroOSRendMM(nroOS);
            rendMMTO.setValorRendMM(0D);
            rendMMTO.insert();
            rendMMTO.commit();
        }

    }

    public void insMovLeira(Long idLeira, Long tipo){
        BoletimMMTO boletimMMTO = getBolMMAberto();
        MovLeiraTO movLeiraTO = new MovLeiraTO();
        movLeiraTO.setTipoMovLeira(tipo);
        movLeiraTO.setIdLeira(idLeira);
        movLeiraTO.setDataHoraMovLeira(Tempo.getInstance().dataComHora());
        movLeiraTO.setIdBolMovLeira(boletimMMTO.getIdBolMM());
        movLeiraTO.setIdExtBolMovLeira(boletimMMTO.getIdExtBolMM());
        movLeiraTO.setStatusMovLeira(1L);
        movLeiraTO.insert();
    }

    public boolean verRend(){
        RendMMTO rendMMTO = new RendMMTO();
        List rendList = rendMMTO.get("idBolRendMM", getBolMMAberto().getIdBolMM());
        Boolean ret = (rendList.size() > 0);
        rendList.clear();
        return ret;
    }

    public int qtdeRend(){
        RendMMTO rendMMTO = new RendMMTO();
        List rendList = rendMMTO.get("idBolRendMM", getBolMMAberto().getIdBolMM());
        return rendList.size();
    }

    public RendMMTO getRend(int pos){
        RendMMTO rendMMTO = new RendMMTO();
        List rendList = rendMMTO.getAndOrderBy("idBolRendMM", getBolMMAberto().getIdBolMM(), "idRendMM", true);
        rendMMTO = (RendMMTO) rendList.get(pos);
        rendList.clear();
        return rendMMTO;
    }

    public void atualRend(RendMMTO rendMMTO){
        rendMMTO.setDthrRendMM(Tempo.getInstance().dataComHora());
        rendMMTO.update();
        rendMMTO.commit();
    }

    public void salvarBolAberto(BoletimMMTO boletimMMTO){

        boletimMMTO.setStatusBolMM(1L);
        boletimMMTO.setDthrInicialBolMM(Tempo.getInstance().dataComHora());
        boletimMMTO.setQtdeApontBolMM(0L);
        boletimMMTO.insert();

        String dataComHora = Tempo.getInstance().dataComHora();

        CheckListCTR checkListCTR = new CheckListCTR();
        if(checkListCTR.verAberturaCheckList(boletimMMTO.getIdTurnoBolMM())) {

            BoletimCTR boletimCTR = new BoletimCTR();

            ApontMMTO apontMMTO = new ApontMMTO();
            apontMMTO.setDthrApontMM(dataComHora);
            apontMMTO.setIdBolApontMM(boletimMMTO.getIdBolMM());
            apontMMTO.setIdExtBolApontMM(boletimMMTO.getIdExtBolMM());
            apontMMTO.setOsApontMM(boletimMMTO.getOsBolMM());
            apontMMTO.setAtivApontMM(boletimMMTO.getAtivPrincBolMM());
            apontMMTO.setParadaApontMM(boletimCTR.getIdParadaCheckList());
            apontMMTO.setStatusConApontMM(boletimMMTO.getStatusConBolMM());
            apontMMTO.setTransbApontMM(0L);
            apontMMTO.setStatusApontMM(1L);

            ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
            apontMMMovLeiraCTR.salvarApontMM(apontMMTO);

        }

    }

    public void salvarBolFechado(BoletimMMTO boletimMMTO) {

        BoletimMMTO boletimMMTOBD = new BoletimMMTO();
        List listBoletim = boletimMMTOBD.get("statusBolMM", 1L);
        boletimMMTOBD = (BoletimMMTO) listBoletim.get(0);
        listBoletim.clear();

        boletimMMTOBD.setDthrFinalBolMM(Tempo.getInstance().dataComHora());
        boletimMMTOBD.setStatusBolMM(2L);
        boletimMMTOBD.setHodometroFinalBolMM(boletimMMTO.getHodometroFinalBolMM());
        boletimMMTOBD.update();

    }

    public List bolFechList() {
        BoletimMMTO boletimMMTO = new BoletimMMTO();
        return boletimMMTO.get("statusBolMM", 2L);
    }

    public List bolAbertoSemEnvioList() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMM");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBolMM");
        pesquisa2.setValor(0);
        pesquisa2.setTipo(1);
        listaPesq.add(pesquisa2);

        return boletimMMTO.get(listaPesq);

    }

}
