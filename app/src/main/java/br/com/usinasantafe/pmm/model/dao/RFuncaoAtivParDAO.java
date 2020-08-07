package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;

public class RFuncaoAtivParDAO {

    public RFuncaoAtivParDAO() {
    }

    public List getListFuncaoAtividade(Long idAtiv){

        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("idAtivPar");
        pesquisa1.setValor(idAtiv);
        pesquisa1.setTipo(1);
        pesqList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("tipoFuncao");
        pesquisa2.setValor(1L);
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        return rFuncaoAtivParBean.get(pesqList);
    }

    public List getListFuncaoParada(Long idParada){

        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("idAtivPar");
        pesquisa1.setValor(idParada);
        pesquisa1.setTipo(1);
        pesqList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("tipoFuncao");
        pesquisa2.setValor(2L);
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        return rFuncaoAtivParBean.get(pesqList);
    }

    public Long idParadaCheckList(){

        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("codFuncao");
        pesq1.setValor(1L);
        pesq1.setTipo(1);
        pesqList.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("tipoFuncao");
        pesq2.setValor(2L);
        pesq2.setTipo(1);
        pesqList.add(pesq2);

        List rFuncaoAtivParList =   rFuncaoAtivParBean.get(pesqList);
        rFuncaoAtivParBean = (RFuncaoAtivParBean) rFuncaoAtivParList.get(0);
        rFuncaoAtivParList.clear();

        return rFuncaoAtivParBean.getIdAtivPar();

    }

    public Long idParadaImplemento(){

        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("codFuncao");
        pesq1.setValor(2L);
        pesq1.setTipo(1);
        pesqList.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("tipoFuncao");
        pesq2.setValor(2L);
        pesq2.setTipo(1);
        pesqList.add(pesq2);

        List rFuncaoAtivParList =   rFuncaoAtivParBean.get(pesqList);
        rFuncaoAtivParBean = (RFuncaoAtivParBean) rFuncaoAtivParList.get(0);
        rFuncaoAtivParList.clear();

        return rFuncaoAtivParBean.getIdAtivPar();

    }

}
