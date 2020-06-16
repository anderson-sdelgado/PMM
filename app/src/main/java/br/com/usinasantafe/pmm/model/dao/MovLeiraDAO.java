package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.variaveis.MovLeiraBean;

public class MovLeiraDAO {

    public MovLeiraDAO() {
    }

    public List getListMovLeiraAberto(ArrayList<Long> idBolList){

        MovLeiraBean movLeiraBean = new MovLeiraBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusMovLeira");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        return movLeiraBean.inAndGet("idBolMovLeira", idBolList, pesqArrayList);

    }

    public List getListMovLeiraAberto() {
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        return movLeiraBean.get("statusMovLeira", 1L);
    }

}
