package br.com.usinasantafe.cmm.model.dao;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.estaticas.BocalBean;

public class BocalDAO {

    public BocalDAO() {
    }

    public List<BocalBean> bocalList(){
        BocalBean bocalBean = new BocalBean();
        return bocalBean.orderBy("codBocal", true);
    }

    public BocalBean getBocal(Long idBocal){
        BocalBean bocalBean = new BocalBean();
        List<BocalBean> bocalList = bocalBean.get("idBocal", idBocal);
        bocalBean = bocalList.get(0);
        bocalList.clear();
        return bocalBean;
    }

}
