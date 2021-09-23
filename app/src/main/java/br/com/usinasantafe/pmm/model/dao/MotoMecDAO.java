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


    public MotoMecBean getVoltaTrabalho(Long aplic, Long codFuncao){

        MotoMecBean motoMecBean = new MotoMecBean();
        ArrayList pesqArrayList = new ArrayList();

        pesqArrayList.add(getPesqBeanAplic(aplic));
        pesqArrayList.add(getPesqBeanInvisivel());
        pesqArrayList.add(getPesqBeanFuncaoOper(codFuncao));

        List motoMecList = motoMecBean.get(pesqArrayList);
        motoMecBean = (MotoMecBean) motoMecList.get(0);
        motoMecList.clear();

        return motoMecBean;

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

    private EspecificaPesquisa getPesqBeanInvisivel(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoOperMotoMec");
        especificaPesquisa.setValor(0L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqBeanFuncaoOper(Long codFuncao){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("codFuncaoOperMotoMec");
        especificaPesquisa.setValor(codFuncao);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
