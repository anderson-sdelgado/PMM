package br.com.usinasantafe.pmm.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.bean.variaveis.MovLeiraTO;

public class MovLeiraDAO {

    public MovLeiraDAO() {
    }

    public List getListMovLeiraAberto(Long idBolMM){

        MovLeiraTO movLeiraTO = new MovLeiraTO();

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

        return movLeiraTO.get(pesqArrayList);

    }

    public List getListMovLeiraAberto() {
        MovLeiraTO movLeiraTO = new MovLeiraTO();
        return movLeiraTO.get("statusMovLeira", 1L);
    }

}
