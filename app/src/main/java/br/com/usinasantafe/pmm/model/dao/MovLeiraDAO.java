package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.variaveis.MovLeiraBean;

public class MovLeiraDAO {

    public MovLeiraDAO() {
    }

    public List getListMovLeiraAberto(Long idBolMM){

        MovLeiraBean movLeiraBean = new MovLeiraBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusMovLeira");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idBolMovLeira");
        pesquisa2.setValor(idBolMM);
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        return movLeiraBean.get(pesqArrayList);

    }

    public List getListMovLeiraAberto() {
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        return movLeiraBean.get("statusMovLeira", 1L);
    }

}
