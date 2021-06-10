package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.PressaoBocalBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;

public class PressaoFertDAO {

    public PressaoFertDAO() {
    }

    public List<PressaoBocalBean> pressaoBocalList(Long idBocal){
        PressaoBocalBean pressaoBocalBean = new PressaoBocalBean();
        return pressaoBocalBean.getAndOrderBy("idBocal", idBocal, "valorPressao", true);
    }

    public ArrayList<String> velocArrayList(Long idBocal, Double pressao){

        PressaoBocalBean pressaoBocalBean = new PressaoBocalBean();

        ArrayList pesqList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBocal");
        pesquisa.setValor(idBocal);
        pesquisa.setTipo(1);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("valorPressao");
        pesquisa2.setValor(pressao);
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        List<PressaoBocalBean> velocList = pressaoBocalBean.getAndOrderBy(pesqList, "valorVeloc", true);

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < velocList.size(); i++){
            pressaoBocalBean = velocList.get(i);
            itens.add("" + pressaoBocalBean.getValorVeloc());
        }

        HashSet<String> hashSet = new HashSet<String>(itens);
        itens.clear();
        itens.addAll(hashSet);
        Collections.sort(itens);

        return itens;

    }

}
