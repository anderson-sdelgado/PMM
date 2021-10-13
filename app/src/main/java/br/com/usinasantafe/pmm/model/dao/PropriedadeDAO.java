package br.com.usinasantafe.pmm.model.dao;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.PropriedadeBean;


public class PropriedadeDAO {

    public boolean verPropriedade(Long codPropriedade){
        List<PropriedadeBean> propriedadeList = propriedadeCodList(codPropriedade);
        boolean ret = propriedadeList.size() > 0;
        propriedadeList.clear();
        return ret;
    }

    public PropriedadeBean getPropriedadeCod(Long codPropriedade){
        List<PropriedadeBean> propriedadeList = propriedadeCodList(codPropriedade);
        PropriedadeBean propriedadeBean = propriedadeList.get(0);
        propriedadeList.clear();
        return propriedadeBean;
    }

    public PropriedadeBean getPropriedadeId(Long idPropriedade){
        List<PropriedadeBean> propriedadeList = propriedadeIdList(idPropriedade);
        PropriedadeBean propriedadeBean = propriedadeList.get(0);
        propriedadeList.clear();
        return propriedadeBean;
    }

    private List<PropriedadeBean> propriedadeIdList(Long idPropriedade){
        PropriedadeBean propriedadeBean = new PropriedadeBean();
        return propriedadeBean.get("idPropriedade", idPropriedade);
    }

    private List<PropriedadeBean> propriedadeCodList(Long codPropriedade){
        PropriedadeBean propriedadeBean = new PropriedadeBean();
        return propriedadeBean.get("codPropriedade", codPropriedade);
    }

}
