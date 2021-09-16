package br.com.usinasantafe.pmm.model.dao;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.FrenteBean;

public class FrenteDAO {

    public FrenteDAO() {
    }

    public boolean verFrente(Long codFrente){
        List<FrenteBean> frenteList = frenteList(codFrente);
        boolean ret = frenteList.size() > 0;
        frenteList.clear();
        return ret;
    }

    public FrenteBean getFrente(Long codFrente){
        List<FrenteBean> frenteList = frenteList(codFrente);
        FrenteBean frenteBean = frenteList.get(0);
        frenteList.clear();
        return frenteBean;
    }

    private List<FrenteBean> frenteList(Long codFrente){
        FrenteBean frenteBean = new FrenteBean();
        return frenteBean.get("codFrente", codFrente);
    }

}
