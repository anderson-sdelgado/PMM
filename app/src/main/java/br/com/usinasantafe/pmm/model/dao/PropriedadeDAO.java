package br.com.usinasantafe.pmm.model.dao;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.PropriedadeBean;


public class PropriedadeDAO {

    public boolean verPropriedade(Long idPropriedade){
        List<PropriedadeBean> propriedadeList = propriedadeList(idPropriedade);
        boolean ret = propriedadeList.size() > 0;
        propriedadeList.clear();
        return ret;
    }

    public PropriedadeBean getPropriedade(Long idPropriedade){
        List<PropriedadeBean> propriedadeList = propriedadeList(idPropriedade);
        PropriedadeBean propriedadeBean = propriedadeList.get(0);
        propriedadeList.clear();
        return propriedadeBean;
    }

    private List<PropriedadeBean> propriedadeList(Long idPropriedade){
        PropriedadeBean propriedadeBean = new PropriedadeBean();
        return propriedadeBean.get("idPropriedade", idPropriedade);
    }

}
