package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;


public class MotoMecDAO {

    public MotoMecDAO() {
    }

    public MotoMecBean getMotoMec(Long idMotoMec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqId(idMotoMec));
        MotoMecBean motoMecBean = new MotoMecBean();
        List<MotoMecBean> motoMecList =  motoMecBean.get(pesqArrayList);
        motoMecBean = motoMecList.get(0);
        motoMecList.clear();
        return motoMecBean;
    }

    public List<MotoMecBean> motoMecList(Long aplic){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqAplic(aplic));
        pesqArrayList.add(getPesqMotoMec());

        MotoMecBean motoMecBean = new MotoMecBean();
        return motoMecBean.getAndOrderBy(pesqArrayList, "posOperMotoMec", true);

    }

    public List<MotoMecBean> paradaList(Long aplic){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqAplic(aplic));
        pesqArrayList.add(getPesqParada());
        MotoMecBean motoMecBean = new MotoMecBean();
        return motoMecBean.getAndOrderBy(pesqArrayList, "posOperMotoMec", true);

    }

    private EspecificaPesquisa getPesqAplic(Long aplic){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("aplicOperMotoMec");
        especificaPesquisa.setValor(aplic);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqMotoMec(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoOperMotoMec");
        especificaPesquisa.setValor(1L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqParada(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoOperMotoMec");
        especificaPesquisa.setValor(2L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqId(Long idMotoMec){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idMotoMec");
        especificaPesquisa.setValor(idMotoMec);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
