package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;


public class MotoMecDAO {

    public MotoMecDAO() {
    }

    public List<MotoMecBean> motoMecList(Long aplic){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBeanAplic(aplic));
        pesqArrayList.add(getPesqBeanMotoMec());

        MotoMecBean motoMecBean = new MotoMecBean();
        return motoMecBean.getAndOrderBy(pesqArrayList, "posOperMotoMec", true);

    }

    public List<MotoMecBean> paradaList(Long aplic){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBeanAplic(aplic));
        pesqArrayList.add(getPesqBeanParada());
        MotoMecBean motoMecBean = new MotoMecBean();
        return motoMecBean.getAndOrderBy(pesqArrayList, "posOperMotoMec", true);

    }

    private EspecificaPesquisa getPesqBeanAplic(Long aplic){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("aplicOperMotoMec");
        especificaPesquisa.setValor(aplic);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqBeanMotoMec(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoOperMotoMec");
        especificaPesquisa.setValor(1L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqBeanParada(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoOperMotoMec");
        especificaPesquisa.setValor(2L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
