package br.com.usinasantafe.pmm.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.RecolhFertTO;

public class ApontFertDAO {

    public ApontFertDAO() {
    }

    public void salvarApont(ApontFertTO apontFertTO, BoletimFertTO boletimFertTO){

        apontFertTO.setLatitudeApontFert(0D);
        apontFertTO.setLongitudeApontFert(0D);

        String datahora = Tempo.getInstance().dataComHora();
        apontFertTO.setDthrApontFert(datahora);

        apontFertTO.setIdBolApontFert(boletimFertTO.getIdBolFert());
        apontFertTO.setIdExtBolApontFert(boletimFertTO.getIdExtBolFert());
        apontFertTO.setStatusApontFert(1L);
        apontFertTO.insert();

        RecolhFertTO recolhFertTO = new RecolhFertTO();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolRecolhFert");
        pesquisa.setValor(boletimFertTO.getIdBolFert());
        pesquisa.setTipo(1);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("nroOSRecolhFert");
        pesquisa2.setValor(apontFertTO.getOsApontFert());
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        List rendList = recolhFertTO.get(pesqList);

        if (rendList.size() == 0) {
            recolhFertTO.setIdBolRecolhFert(boletimFertTO.getIdBolFert());
            recolhFertTO.setNroOSRecolhFert(apontFertTO.getOsApontFert());
            recolhFertTO.setValorRecolhFert(0L);
            recolhFertTO.insert();
            recolhFertTO.commit();
        }

    }

    public List apontList(Long idBolMM){
        ApontFertTO apontFertTO = new ApontFertTO();
        return apontFertTO.getAndOrderBy("idBolApontFert", idBolMM, "idApontFert", true);
    }

    public List apontAbertoList(Long idBolMM){

        ApontFertTO apontFertTO = new ApontFertTO();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontFert");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idBolApontFert");
        pesquisa2.setValor(idBolMM);
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        return apontFertTO.get(pesqArrayList);

    }

    public List apontAbertoFertList() {
        ApontFertTO apontFertTO = new ApontFertTO();
        return apontFertTO.get("statusApontFert", 1L);
    }


}
