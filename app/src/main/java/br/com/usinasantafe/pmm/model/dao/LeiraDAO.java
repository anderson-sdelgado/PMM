package br.com.usinasantafe.pmm.model.dao;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.LeiraBean;

public class LeiraDAO {

    public LeiraDAO() {
    }

    public boolean verLeiraCod(Long codLeira){
        List<LeiraBean> leiraList = leiraCodList(codLeira);
        boolean ret = leiraList.size() > 0;
        leiraList.clear();
        return ret;
    }

    public LeiraBean getLeiraCod(Long codLeira){
        List<LeiraBean> leiraList = leiraCodList(codLeira);
        LeiraBean leiraBean = (LeiraBean) leiraList.get(0);
        leiraList.clear();
        return leiraBean;
    }

    public List<LeiraBean> leiraCodList(Long codLeira){
        LeiraBean leiraBean = new LeiraBean();
        return leiraBean.get("codLeira", codLeira);
    }

}
